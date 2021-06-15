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
import org.jeecg.modules.online.config.db.DbConfig;
import org.jeecg.modules.online.config.db.DataBaseConfig;
import org.jeecg.modules.online.config.exception.DBException;
import org.jeecg.modules.online.config.service.DbTableHandleI;

@Slf4j
public class TableUtil {
    private static final String b = "org/jeecg/modules/online/config/engine/tableTemplate.ftl";
    private static DbTableHandleI DB_TABLE_HANDLER;

    public TableUtil() throws SQLException, DBException {
        DB_TABLE_HANDLER = DataBaseUtil.getDbTableHandle();
    }

    public static void a(DbConfig var0) throws IOException, TemplateException, HibernateException, SQLException, DBException {
        String var1 = DataBaseUtil.getDatabaseType();
        if ("ORACLE".equals(var1)) {
            ArrayList var2 = new ArrayList();

            OnlCgformField var4;
            for(Iterator var3 = var0.getColumns().iterator(); var3.hasNext(); var2.add(var4)) {
                var4 = (OnlCgformField)var3.next();
                if ("int".equals(var4.getDbType())) {
                    var4.setDbType("double");
                    var4.setDbPointLength(0);
                }
            }

            var0.setColumns(var2);
        }

        String var16 = TemplateUtil.renderTemplate("org/jeecg/modules/online/config/engine/tableTemplate.ftl", a(var0, var1));
        log.info(var16);
        HashMap var17 = new HashMap();
        DataBaseConfig var18 = var0.getDbConfig();
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
        var17.put("hibernate.dialect", DataBaseUtil.b(var1));
        var17.put("hibernate.hbm2ddl.auto", "create");
        var17.put("hibernate.connection.autocommit", false);
        var17.put("hibernate.current_session_context_class", "thread");
        StandardServiceRegistry var19 = (new StandardServiceRegistryBuilder()).applySettings(var17).build();
        MetadataSources var7 = new MetadataSources(var19);
        ByteArrayInputStream var8 = new ByteArrayInputStream(var16.getBytes("utf-8"));
        var7.addInputStream(var8);
        Metadata var9 = var7.buildMetadata();
        SchemaExport var10 = new SchemaExport();
        var10.create(EnumSet.of(TargetType.DATABASE), var9);
        var8.close();
        List var11 = var10.getExceptions();
        Iterator var12 = var11.iterator();

        while(var12.hasNext()) {
            Exception var13 = (Exception)var12.next();
            if ("java.sql.SQLSyntaxErrorException".equals(var13.getCause().getClass().getName())) {
                SQLSyntaxErrorException var14 = (SQLSyntaxErrorException)var13.getCause();
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

                if ("DM".equals(var1)) {
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

    public List<String> b(DbConfig dbConfig) throws DBException, SQLException {
        String databaseType = DataBaseUtil.getDatabaseType();
        String tableName = DataBaseUtil.CaseSensitive(dbConfig.getTableName(), databaseType);
        String alertSql = "alter table  " + tableName + " ";
        List<String > var5 = new ArrayList();

        try {
            Map var6 = this.c(null, tableName);
            Map var7 = this.getPropertyMap(dbConfig);
            Map var8 = this.a(dbConfig.getColumns());
            Iterator var9 = var7.keySet().iterator();

            label72:
            while(true) {
                while(true) {
                    String var10;
                    while(var9.hasNext()) {
                        var10 = (String)var9.next();
                        ColumnProperty var11;
                        if (!var6.containsKey(var10)) {
                            var11 = (ColumnProperty)var7.get(var10);
                            String var17 = (String)var8.get(var10);
                            if (var8.containsKey(var10) && var6.containsKey(var17)) {
                                ColumnProperty var13 = (ColumnProperty)var6.get(var17);
                                String var14 = DB_TABLE_HANDLER.getReNameFieldName(var11);
                                if ("SQLSERVER".equals(databaseType)) {
                                    var5.add(var14);
                                } else {
                                    var5.add(alertSql + var14);
                                }

                                String var15 = this.d(var10, var11.getColumnId());
                                var5.add(var15);
                                if (!var13.equals(var11)) {
                                    var5.add(alertSql + this.a(var11, var13));
                                    if ("POSTGRESQL".equals(databaseType)) {
                                        var5.add(alertSql + this.b(var11, var13));
                                    }
                                }

                                if (!"SQLSERVER".equals(databaseType) && !var13.b(var11)) {
                                    var5.add(this.c(var11));
                                }
                            } else {
                                var5.add(alertSql + this.b(var11));
                                if (!"SQLSERVER".equals(databaseType) && StringUtils.isNotEmpty(var11.getComment())) {
                                    var5.add(this.c(var11));
                                }
                            }
                        } else {
                            var11 = (ColumnProperty)var6.get(var10);
                            ColumnProperty var12 = (ColumnProperty)var7.get(var10);
                            if (!var11.a(var12, databaseType)) {
                                var5.add(alertSql + this.a(var12, var11));
                            }

                            if (!"SQLSERVER".equals(databaseType) && !"ORACLE".equals(databaseType) && !var11.b(var12)) {
                                var5.add(this.c(var12));
                            }
                        }
                    }

                    var9 = var6.keySet().iterator();

                    while(var9.hasNext()) {
                        var10 = (String)var9.next();
                        if (!var7.containsKey(var10.toLowerCase()) && !var8.containsValue(var10.toLowerCase())) {
                            var5.add(alertSql + this.b(var10));
                        }
                    }
                    break label72;
                }
            }
        } catch (SQLException var16) {
            throw new RuntimeException();
        }

        log.info(" db update sql : " + var5.toString());
        return var5;
    }

    private static Map<String, Object> a(DbConfig var0, String var1) {
        HashMap var2 = new HashMap();
        Iterator var3 = var0.getColumns().iterator();

        while(var3.hasNext()) {
            OnlCgformField var4 = (OnlCgformField)var3.next();
            var4.setDbDefaultVal(c(var4.getDbDefaultVal()));
        }

        var2.put("entity", var0);
        var2.put("dataType", var1);
        return var2;
    }

    private Map<String, ColumnProperty> c(String var1, String var2) throws SQLException {
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
            var9 = var5.getColumns(connection.getCatalog(), (String)null, var2, "%");
        } else {
            var9 = var5.getColumns(connection.getCatalog(), var8, var2, "%");
        }

        while(var9.next()) {
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
            String var19 = c(var18) == null ? "" : c(var18);
            var10.setFieldDefault(var19);
            log.info("getColumnMetadataFormDataBase --->COLUMN_NAME:" + var11.toUpperCase() + " TYPE_NAME :" + var12 + " DECIMAL_DIGITS:" + var13 + " COLUMN_SIZE:" + var15);
            var3.put(var11, var10);
        }

        return var3;
    }

    private Map<String, ColumnProperty> getPropertyMap(DbConfig dbConfig) {
        Map<String, ColumnProperty> propertyMap = new HashMap();
        List<OnlCgformField> columns = dbConfig.getColumns();
        for (OnlCgformField column : columns) {
            ColumnProperty property = new ColumnProperty();
            property.setTableName(dbConfig.getTableName().toLowerCase());
            property.setColumnId(column.getId());
            property.setColumnName(column.getDbFieldName().toLowerCase());
            property.setColumnSize(column.getDbLength());
            property.setColumnType(column.getDbType().toLowerCase());
            property.setIsNullable(column.getDbIsNull() == 1 ? "Y" : "N");
            property.setComment(column.getDbFieldTxt());
            property.setDecimalDigits(column.getDbPointLength());
            property.setFieldDefault(c(column.getDbDefaultVal()));
            property.setPkType(dbConfig.getJformPkType() == null ? "UUID" : dbConfig.getJformPkType());
            property.setOldColumnName(column.getDbFieldNameOld() != null ? column.getDbFieldNameOld().toLowerCase() : null);
            log.info("getColumnMetadataFormCgForm ----> DbFieldName: " + column.getDbFieldName().toLowerCase() + " | DbType: " + column.getDbType().toLowerCase() + " | DbPointLength:" + column.getDbPointLength() + " | DbLength:" + column.getDbLength());
            propertyMap.put(column.getDbFieldName().toLowerCase(), property);
        }
        return propertyMap;
    }

    private Map<String, String> a(List<OnlCgformField> var1) {
        HashMap var2 = new HashMap();
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            OnlCgformField var4 = (OnlCgformField)var3.next();
            var2.put(var4.getDbFieldName(), var4.getDbFieldNameOld());
        }

        return var2;
    }

    private String b(String var1) {
        return DB_TABLE_HANDLER.getDropColumnSql(var1);
    }

    private String a(ColumnProperty var1, ColumnProperty var2) throws DBException {
        return DB_TABLE_HANDLER.getUpdateColumnSql(var1, var2);
    }

    private String b(ColumnProperty var1, ColumnProperty var2) {
        return DB_TABLE_HANDLER.getSpecialHandle(var1, var2);
    }

    private String a(ColumnProperty var1) {
        return DB_TABLE_HANDLER.getReNameFieldName(var1);
    }

    private String b(ColumnProperty var1) {
        return DB_TABLE_HANDLER.getAddColumnSql(var1);
    }

    private String c(ColumnProperty var1) {
        return DB_TABLE_HANDLER.getCommentSql(var1);
    }

    private String d(String var1, String var2) {
        return "update onl_cgform_field set DB_FIELD_NAME_OLD = '" + var1 + "' where ID ='" + var2 + "'";
    }

    private int a(String var1, String var2, Session var3) {
        return var3.createSQLQuery("update onl_cgform_field set DB_FIELD_NAME_OLD= '" + var1 + "' where ID ='" + var2 + "'").executeUpdate();
    }

    private static String c(String var0) {
        if (StringUtils.isNotEmpty(var0)) {
            try {
                Double.valueOf(var0);
            } catch (Exception var2) {
                if (!var0.startsWith("'") || !var0.endsWith("'")) {
                    var0 = "'" + var0 + "'";
                }
            }
        }

        return var0;
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
            var2 = var4.getIndexInfo((String)null, (String)null, var0, false, false);
            ResultSetMetaData var5 = var2.getMetaData();

            while(var2.next()) {
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
