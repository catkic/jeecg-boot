//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.jeecg.modules.online.config.util;

import com.alibaba.druid.filter.config.ConfigTools;
import freemarker.template.TemplateException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.config.db.TableConfig;
import org.jeecg.modules.online.config.db.DataBaseConfig;
import org.jeecg.modules.online.config.exception.DBException;
import org.jeecg.modules.online.config.service.DbTableHandleI;

@Slf4j
public class TableUtil {
    private static final String TABLE_TEMPLATE_FTL = "org/jeecg/modules/online/config/engine/tableTemplate.ftl";
    private static DbTableHandleI DB_TABLE_HANDLER;

    public TableUtil() throws SQLException, DBException {
        DB_TABLE_HANDLER = DataBaseUtil.getDbTableHandle();
    }

    public static void createTable(TableConfig tableConfig) throws IOException, TemplateException, HibernateException, SQLException, DBException {
        String databaseType = DataBaseUtil.getDatabaseType();
        if ("ORACLE".equals(databaseType)) {
            ArrayList var2 = new ArrayList();

            OnlCgformField var4;
            for (Iterator var3 = tableConfig.getColumns().iterator(); var3.hasNext(); var2.add(var4)) {
                var4 = (OnlCgformField) var3.next();
                if ("int".equals(var4.getDbType())) {
                    var4.setDbType("double");
                    var4.setDbPointLength(0);
                }
            }

            tableConfig.setColumns(var2);
        }

        String createDtd = TemplateUtil.renderTemplate(TABLE_TEMPLATE_FTL, getTemplateDataModel(tableConfig, databaseType));
        log.info(createDtd);
        HashMap var17 = new HashMap();
        DataBaseConfig var18 = tableConfig.getDbConfig();
        var17.put("hibernate.connection.driver_class", var18.getDriverClassName());
        var17.put("hibernate.connection.url", var18.getUrl());
        var17.put("hibernate.connection.username", var18.getUsername());
        String var5 = var18.getPassword();
        if (var5 != null) {
            if (var18.getDruid() != null && oConvertUtils.isNotEmpty(var18.getDruid().getPublicKey())) {
                log.info(" dbconfig.getDruid().getPublicKey() = " + var18.getDruid().getPublicKey());

                try {
                    String var6 = ConfigTools.decrypt(var18.getDruid().getPublicKey(), var5);
                    log.info(" 解密密码 decryptPassword = " + var6);
                    var17.put("hibernate.connection.password", var6);
                } catch (Exception var15) {
                    var15.printStackTrace();
                }
            } else {
                var17.put("hibernate.connection.password", var5);
            }
        }

        var17.put("hibernate.show_sql", true);
        var17.put("hibernate.format_sql", true);
        var17.put("hibernate.temp.use_jdbc_metadata_defaults", false);
        var17.put("hibernate.dialect", DataBaseUtil.b(databaseType));
        var17.put("hibernate.hbm2ddl.auto", "create");
        var17.put("hibernate.connection.autocommit", false);
        var17.put("hibernate.current_session_context_class", "thread");
        StandardServiceRegistry var19 = (new StandardServiceRegistryBuilder()).applySettings(var17).build();
        MetadataSources var7 = new MetadataSources(var19);
        ByteArrayInputStream var8 = new ByteArrayInputStream(createDtd.getBytes("utf-8"));
        var7.addInputStream(var8);
        Metadata var9 = var7.buildMetadata();
        SchemaExport var10 = new SchemaExport();
        var10.create(EnumSet.of(TargetType.DATABASE), var9);
        var8.close();
        List var11 = var10.getExceptions();
        Iterator var12 = var11.iterator();

        while (var12.hasNext()) {
            Exception var13 = (Exception) var12.next();
            if ("java.sql.SQLSyntaxErrorException".equals(var13.getCause().getClass().getName())) {
                SQLSyntaxErrorException var14 = (SQLSyntaxErrorException) var13.getCause();
                if ("42000".equals(var14.getSQLState())) {
                    if (1064 != var14.getErrorCode() && 903 != var14.getErrorCode()) {
                        continue;
                    }

                    throw new DBException("请确认表名是否为关键字。");
                }
            } else {
                if ("com.microsoft.sqlserver.jdbc.SQLServerException".equals(var13.getCause().getClass().getName())) {
                    if (var13.getCause().toString().indexOf("Incorrect syntax near the keyword") != -1) {
                        var13.printStackTrace();
                        throw new DBException(var13.getCause().getMessage());
                    }

                    log.error(var13.getMessage());
                    continue;
                }

                if ("DM".equals(databaseType)) {
                    String var20 = var13.getMessage();
                    if (var20 != null && var20.indexOf("Error executing DDL \"drop table") >= 0) {
                        log.error(var20);
                        continue;
                    }
                }
            }

            throw new DBException(var13.getMessage());
        }

    }

    public List<String> getDbUpdateSql(TableConfig tableConfig) throws DBException, SQLException {
        String databaseType = DataBaseUtil.getDatabaseType();
        String tableName = DataBaseUtil.CaseSensitive(tableConfig.getTableName(), databaseType);
        String alertSql = "alter table  " + tableName + " ";
        List<String> updateSql = new ArrayList<>();

        // 这部分大改也不知道对不对等下检查一下
        try {
            Map<String, ColumnProperty> columnMetadataFormDataBase = this.getColumnMetadataFormDataBase(null, tableName);
            Map<String, ColumnProperty> columnMetadataFormCgForm = this.getColumnMetadataFormCgForm(tableConfig);

            // 提交上来的表的
            Map<String, String> fieldNameAndOldMap = this.fieldNameAndOld(tableConfig.getColumns());
            // 循环提交上来的Form每一列
            for (String cgFormField : columnMetadataFormCgForm.keySet()) {
                if (!columnMetadataFormDataBase.containsKey(cgFormField)) {
                    // 如果原来的数据库没有提交上来的这个字段，那就是新增
                    ColumnProperty property = columnMetadataFormCgForm.get(cgFormField);
                    // 老名字？？？？
                    String oldField = fieldNameAndOldMap.get(cgFormField);

                    // 如果新上来的都有这个字段就是更新
                    if (fieldNameAndOldMap.containsKey(cgFormField) && columnMetadataFormDataBase.containsKey(oldField)) {
                        ColumnProperty oldProperty = columnMetadataFormDataBase.get(oldField);

                        String reNameFieldName = DB_TABLE_HANDLER.getReNameFieldName(property);
                        if ("SQLSERVER".equals(databaseType)) {
                            updateSql.add(reNameFieldName);
                        } else {
                            updateSql.add(alertSql + reNameFieldName);
                        }

                        String updateCgFormFieldSql = this.updateCgFormFieldSql(cgFormField, property.getColumnId());
                        updateSql.add(updateCgFormFieldSql);
                        if (!oldProperty.equals(property)) {
                            updateSql.add(alertSql + this.getUpdateColumnSql(property, oldProperty));
                            if ("POSTGRESQL".equals(databaseType)) {
                                // 特殊处理
                                updateSql.add(alertSql + this.getSpecialHandle(property, oldProperty));
                            }
                        }

                        // 更新备注
                        if (!"SQLSERVER".equals(databaseType) && !oldProperty.columnCommentEqual(property)) {
                            updateSql.add(this.getCommentSql(property));
                        }
                    } else {
                        // 新增一列
                        updateSql.add(alertSql + this.getAddColumnSql(property));
                        if (!"SQLSERVER".equals(databaseType) && StringUtils.isNotEmpty(property.getComment())) {
                            updateSql.add(this.getCommentSql(property));
                        }
                    }
                } else {
                    ColumnProperty property = columnMetadataFormDataBase.get(cgFormField);
                    ColumnProperty submitProperty = columnMetadataFormCgForm.get(cgFormField);
                    if (!property.strEqual(submitProperty, databaseType)) {
                        updateSql.add(alertSql + this.getUpdateColumnSql(submitProperty, property));
                    }

                    if (!"SQLSERVER".equals(databaseType) && !"ORACLE".equals(databaseType) && !property.columnCommentEqual(submitProperty)) {
                        updateSql.add(this.getCommentSql(submitProperty));
                    }
                }
            }

            // 删掉提交上来字段？？？？？？？？
            for (String field : columnMetadataFormCgForm.keySet()) {
                if (!columnMetadataFormCgForm.containsKey(field.toLowerCase()) && !fieldNameAndOldMap.containsValue(field.toLowerCase())) {
                    updateSql.add(alertSql + this.getDropColumnSql(field));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        log.info(" db update sql : " + updateSql);
        return updateSql;
    }

    private static Map<String, Object> getTemplateDataModel(TableConfig tableConfig, String databaseType) {
        Map<String, Object> res = new HashMap<>();
        for (OnlCgformField column : tableConfig.getColumns())
            column.setDbDefaultVal(dealWithDefaultVal(column.getDbDefaultVal()));

        res.put("entity", tableConfig);
        res.put("dataType", databaseType);
        return res;
    }

    private Map<String, ColumnProperty> getColumnMetadataFormDataBase(String var1, String var2) throws SQLException {
        HashMap var3 = new HashMap();
        Connection connection = null;

        try {
            connection = DataBaseUtil.getConnection();
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }

        DatabaseMetaData var5 = connection.getMetaData();
        DataBaseConfig dataBaseConfig = SpringContextUtils.getBean(DataBaseConfig.class);
        String var7 = null;

        try {
            var7 = DataBaseUtil.getDatabaseType();
        } catch (DBException var20) {
            var20.printStackTrace();
        }

        String var8 = dataBaseConfig.getUsername();
        if ("ORACLE".equals(var7)) {
            var8 = var8.toUpperCase();
        }

        ResultSet var9 = null;
        if ("SQLSERVER".equals(var7)) {
            var9 = var5.getColumns(connection.getCatalog(), (String) null, var2, "%");
        } else {
            var9 = var5.getColumns(connection.getCatalog(), var8, var2, "%");
        }

        while (var9.next()) {
            ColumnProperty var10 = new ColumnProperty();
            var10.setTableName(var2);
            String var11 = var9.getString("COLUMN_NAME").toLowerCase();
            var10.setColumnName(var11);
            String var12 = var9.getString("TYPE_NAME");
            int var13 = var9.getInt("DECIMAL_DIGITS");
            String var14 = DB_TABLE_HANDLER.getMatchClassTypeByDataType(var12, var13);
            var10.setColumnType(var14);
            var10.setRealDbType(var12);
            int var15 = var9.getInt("COLUMN_SIZE");
            var10.setColumnSize(var15);
            var10.setDecimalDigits(var13);
            String var16 = var9.getInt("NULLABLE") == 1 ? "Y" : "N";
            var10.setIsNullable(var16);
            String var17 = var9.getString("REMARKS");
            var10.setComment(var17);
            String var18 = var9.getString("COLUMN_DEF");
            String var19 = dealWithDefaultVal(var18) == null ? "" : dealWithDefaultVal(var18);
            var10.setFieldDefault(var19);
            log.info("getColumnMetadataFormDataBase --->COLUMN_NAME:" + var11.toUpperCase() + " TYPE_NAME :" + var12 + " DECIMAL_DIGITS:" + var13 + " COLUMN_SIZE:" + var15);
            var3.put(var11, var10);
        }

        return var3;
    }

    private Map<String, ColumnProperty> getColumnMetadataFormCgForm(TableConfig tableConfig) {
        Map<String, ColumnProperty> propertyMap = new HashMap<>();
        List<OnlCgformField> columns = tableConfig.getColumns();
        for (OnlCgformField column : columns) {
            ColumnProperty property = new ColumnProperty();
            property.setTableName(tableConfig.getTableName().toLowerCase());
            property.setColumnId(column.getId());
            property.setColumnName(column.getDbFieldName().toLowerCase());
            property.setColumnSize(column.getDbLength());
            property.setColumnType(column.getDbType().toLowerCase());
            property.setIsNullable(column.getDbIsNull() == 1 ? "Y" : "N");
            property.setComment(column.getDbFieldTxt());
            property.setDecimalDigits(column.getDbPointLength());
            property.setFieldDefault(dealWithDefaultVal(column.getDbDefaultVal()));
            property.setPkType(tableConfig.getJformPkType() == null ? "UUID" : tableConfig.getJformPkType());
            property.setOldColumnName(column.getDbFieldNameOld() != null ? column.getDbFieldNameOld().toLowerCase() : null);
            log.info("getColumnMetadataFormCgForm ----> DbFieldName: " + column.getDbFieldName().toLowerCase() + " | DbType: " + column.getDbType().toLowerCase() + " | DbPointLength:" + column.getDbPointLength() + " | DbLength:" + column.getDbLength());
            propertyMap.put(column.getDbFieldName().toLowerCase(), property);
        }
        return propertyMap;
    }

    private Map<String, String> fieldNameAndOld(List<OnlCgformField> columns) {
        Map<String, String> res = new HashMap<>();
        for (OnlCgformField column : columns) {
            res.put(column.getDbFieldName(), column.getDbFieldNameOld());
        }
        return res;
    }

    private String getDropColumnSql(String fieldName) {
        return DB_TABLE_HANDLER.getDropColumnSql(fieldName);
    }

    private String getUpdateColumnSql(ColumnProperty newProperty, ColumnProperty oldProperty) throws DBException {
        return DB_TABLE_HANDLER.getUpdateColumnSql(newProperty, oldProperty);
    }

    private String getSpecialHandle(ColumnProperty var1, ColumnProperty var2) {
        return DB_TABLE_HANDLER.getSpecialHandle(var1, var2);
    }

    private String a(ColumnProperty var1) {
        return DB_TABLE_HANDLER.getReNameFieldName(var1);
    }

    private String getAddColumnSql(ColumnProperty var1) {
        return DB_TABLE_HANDLER.getAddColumnSql(var1);
    }

    private String getCommentSql(ColumnProperty var1) {
        return DB_TABLE_HANDLER.getCommentSql(var1);
    }

    private String updateCgFormFieldSql(String var1, String var2) {
        return "update onl_cgform_field set DB_FIELD_NAME_OLD = '" + var1 + "' where ID ='" + var2 + "'";
    }

    private int createSQLQuery(String var1, String var2, Session var3) {
        return var3.createSQLQuery("update onl_cgform_field set DB_FIELD_NAME_OLD= '" + var1 + "' where ID ='" + var2 + "'").executeUpdate();
    }

    private static String dealWithDefaultVal(String defaultValue) {
        if (StringUtils.isNotEmpty(defaultValue)) {
            try {
                // 如果默认值是一个小数那直接返回
                Double.valueOf(defaultValue);
            } catch (Exception e) {
                // 不是小数，不以单引号开头或者不以单引号结尾就包起来
                if (!defaultValue.startsWith("'") || !defaultValue.endsWith("'")) {
                    return "'" + defaultValue + "'";
                }
            }
        }

        return defaultValue;
    }

    public String dropIndicesSql(String indexName, String tableName) {
        return DB_TABLE_HANDLER.dropIndexs(indexName, tableName);
    }

    public String countIndexSql(String indexName, String tableName) {
        return DB_TABLE_HANDLER.countIndex(indexName, tableName);
    }

    public static List<String> a(String var0) throws SQLException {
        Connection var1 = null;
        ResultSet var2 = null;
        ArrayList var3 = new ArrayList();

        try {
            var1 = DataBaseUtil.getConnection();
            DatabaseMetaData var4 = var1.getMetaData();
            var2 = var4.getIndexInfo((String) null, (String) null, var0, false, false);
            ResultSetMetaData var5 = var2.getMetaData();

            while (var2.next()) {
                String var6 = var2.getString("INDEX_NAME");
                if (oConvertUtils.isEmpty(var6)) {
                    var6 = var2.getString("index_name");
                }

                if (oConvertUtils.isNotEmpty(var6)) {
                    var3.add(var6);
                }
            }
        } catch (SQLException var10) {
            log.error(var10.getMessage(), var10);
        } finally {
            if (var1 != null) {
                var1.close();
            }

        }

        return var3;
    }
}
