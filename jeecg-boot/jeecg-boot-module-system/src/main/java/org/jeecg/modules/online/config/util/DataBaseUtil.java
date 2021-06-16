/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.jeecg.common.util.SpringContextUtils
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package org.jeecg.modules.online.config.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.config.db.DataBaseConfig;
import org.jeecg.modules.online.config.exception.DBException;
import org.jeecg.modules.online.config.service.DbTableHandleI;
import org.jeecg.modules.online.config.service.handler.*;
import org.jeecg.modules.online.config.service.handler.DMTableHandler;
import org.jeecg.modules.online.config.service.handler.MysqlTableHandler;
import org.jeecg.modules.online.config.service.handler.SqlServerTableHandler;
import org.springframework.util.StringUtils;

@Slf4j
public class DataBaseUtil {
    public static String DATABASE_TYPE = "";

    public static DbTableHandleI getDbTableHandle() throws SQLException, DBException {
        DbTableHandleI dbTableHandleI;
        switch (DataBaseUtil.getDatabaseType()) {
            case "MYSQL":
            case "MARIADB": {
                dbTableHandleI = new MysqlTableHandler();
                break;
            }
            case "ORACLE": {
                dbTableHandleI = new OracleTableHandler();
                break;
            }
            case "DM": {
                dbTableHandleI = new DMTableHandler();
                break;
            }
            case "SQLSERVER": {
                dbTableHandleI = new SqlServerTableHandler();
                break;
            }
            case "POSTGRESQL": {
                dbTableHandleI = new PostgreTableHandler();
                break;
            }
            default: {
                dbTableHandleI = new MysqlTableHandler();
            }
        }
        return dbTableHandleI;
    }

    public static Connection getConnection() throws SQLException {
        DataSource dataSource = (DataSource) SpringContextUtils.getApplicationContext().getBean(DataSource.class);
        return dataSource.getConnection();
    }

    public static String getDatabaseType() throws SQLException, DBException {
        if (oConvertUtils.isNotEmpty(DATABASE_TYPE)) {
            return DATABASE_TYPE;
        }
        DataSource dataSource = SpringContextUtils.getApplicationContext().getBean(DataSource.class);
        return DataBaseUtil.getDataBaseType(dataSource);
    }

    public static boolean isOracle() {
        try {
            return "ORACLE".equals(DataBaseUtil.getDatabaseType());
        } catch (SQLException | DBException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getDataBaseType(DataSource dataSource) throws SQLException, DBException {
        if ("".equals(DATABASE_TYPE)) {
            Connection connection = dataSource.getConnection();
            try {
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                String string = databaseMetaData.getDatabaseProductName().toLowerCase();
                if (string.contains("mysql")) {
                    DATABASE_TYPE = "MYSQL";
                } else if (string.contains("oracle")) {
                    DATABASE_TYPE = "ORACLE";
                } else if (string.contains("dm")) {
                    DATABASE_TYPE = "DM";
                } else if (string.contains("sqlserver") || string.contains("sql server")) {
                    DATABASE_TYPE = "SQLSERVER";
                } else if (string.contains("postgresql")) {
                    DATABASE_TYPE = "POSTGRESQL";
                } else if (string.contains("mariadb")) {
                    DATABASE_TYPE = "MARIADB";
                } else {
                    log.error("数据库类型:[" + string + "]不识别!");
                }
            } catch (Exception exception) {
                log.error(exception.getMessage(), exception);
            } finally {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            }
        }
        return DATABASE_TYPE;
    }

    public static String getDataBaseType(Connection connection) throws SQLException {
        if (StringUtils.isEmpty(DATABASE_TYPE)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String string = databaseMetaData.getDatabaseProductName().toLowerCase();
            if (string.contains("mysql")) {
                DATABASE_TYPE = "MYSQL";
            } else if (string.contains("oracle")) {
                DATABASE_TYPE = "ORACLE";
            } else if (string.contains("sqlserver") || string.contains("sql server")) {
                DATABASE_TYPE = "SQLSERVER";
            } else if (string.contains("postgresql")) {
                DATABASE_TYPE = "POSTGRESQL";
            } else if (string.contains("mariadb")) {
                DATABASE_TYPE = "MARIADB";
            } else {
                log.error("数据库类型:[" + string + "]不识别!");
            }
        }
        return DATABASE_TYPE;
    }

    public static String CaseSensitive(String tableName, String dbType) {
        switch (dbType) {
            case "ORACLE": {
                return tableName.toUpperCase();
            }
            case "POSTGRESQL": {
                return tableName.toLowerCase();
            }
        }
        return tableName;
    }

    /**
     * 检查表名是否存在
     *
     * @param tableName
     * @return 存在就是true
     */
    public static Boolean checkTableExist(String tableName) {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            String[] checkTypes = new String[]{"TABLE"};
            connection = DataBaseUtil.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String dbType = null;
            try {
                dbType = DataBaseUtil.getDatabaseType();
            } catch (DBException dBException) {
                dBException.printStackTrace();
            }
            String caseTableName = DataBaseUtil.CaseSensitive(tableName, dbType);
            DataBaseConfig dataBaseConfig = SpringContextUtils.getBean(DataBaseConfig.class);
            String dbUsername = dataBaseConfig.getUsername();
            if ("ORACLE".equals(dbType) || "DM".equals(dbType)) {
                dbUsername = dbUsername != null ? dbUsername.toUpperCase() : null;
            }
            resultSet = "SQLSERVER".equals(dbType) ?
                    databaseMetaData.getTables(connection.getCatalog(), null, caseTableName, checkTypes)
                    :
                    databaseMetaData.getTables(connection.getCatalog(), dbUsername, caseTableName, checkTypes);
            if (resultSet.next()) {
                log.info("数据库表：【" + tableName + "】已存在");
                return true;
            }
            return false;
        } catch (SQLException sQLException) {
            throw new RuntimeException();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException exception) {
                log.error(exception.getMessage(), exception);
            }
        }
    }

    public static Map<String, Object> a(List<Map<String, Object>> list) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (int i2 = 0; i2 < list.size(); ++i2) {
            hashMap.put(list.get(i2).get("column_name").toString(), list.get(i2));
        }
        return hashMap;
    }

    public static String getDialect() throws SQLException, DBException {
        String string = DataBaseUtil.getDatabaseType();
        return DataBaseUtil.getDataBaseDialect(string);
    }

    public static String getDataBaseDialect(String databaseType) throws SQLException, DBException {
        String dialect;
        switch (databaseType) {
            case "SQLSERVER":
                dialect = "org.hibernate.dialect.SQLServerDialect";
                break;
            case "POSTGRESQL":
                dialect = "org.hibernate.dialect.PostgreSQLDialect";
                break;
            case "ORACLE":
                dialect = "org.hibernate.dialect.OracleDialect";
                break;
            case "DM":
                dialect = "org.hibernate.dialect.DmDialect";
                break;
            default:
                dialect = "org.hibernate.dialect.MySQL5InnoDBDialect";
                break;
        }
        return dialect;
    }

    public static String c(String string) {
        return string;
    }
}

