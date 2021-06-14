/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jeecg.common.util.SpringContextUtils
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package org.jeecg.modules.online.config.b;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.config.a.b;
import org.jeecg.modules.online.config.exception.DBException;
import org.jeecg.modules.online.config.service.DbTableHandleI;
import org.jeecg.modules.online.config.service.a.a;
import org.jeecg.modules.online.config.service.a.c;
import org.jeecg.modules.online.config.service.a.e;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class d {
    private static final Logger b = LoggerFactory.getLogger(d.class);
    public static String a = "";

    public static DbTableHandleI getTableHandle() throws SQLException, DBException {
        String string;
        DbTableHandleI dbTableHandleI = null;
        switch (string = d.getDatabaseType()) {
            case "MYSQL": {
                dbTableHandleI = new org.jeecg.modules.online.config.service.a.b();
                break;
            }
            case "MARIADB": {
                dbTableHandleI = new org.jeecg.modules.online.config.service.a.b();
                break;
            }
            case "ORACLE": {
                dbTableHandleI = new c();
                break;
            }
            case "DM": {
                dbTableHandleI = new a();
                break;
            }
            case "SQLSERVER": {
                dbTableHandleI = new e();
                break;
            }
            case "POSTGRESQL": {
                dbTableHandleI = new org.jeecg.modules.online.config.service.a.d();
                break;
            }
            default: {
                dbTableHandleI = new org.jeecg.modules.online.config.service.a.b();
            }
        }
        return dbTableHandleI;
    }

    public static Connection getConnection() throws SQLException {
        DataSource dataSource = (DataSource)SpringContextUtils.getApplicationContext().getBean(DataSource.class);
        return dataSource.getConnection();
    }

    public static String getDatabaseType() throws SQLException, DBException {
        if (oConvertUtils.isNotEmpty((Object)a)) {
            return a;
        }
        DataSource dataSource = (DataSource)SpringContextUtils.getApplicationContext().getBean(DataSource.class);
        return d.a(dataSource);
    }

    public static boolean a() {
        try {
            return "ORACLE".equals(d.getDatabaseType());
        }
        catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        catch (DBException dBException) {
            dBException.printStackTrace();
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String a(DataSource dataSource) throws SQLException, DBException {
        if ("".equals(a)) {
            Connection connection = dataSource.getConnection();
            try {
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                String string = databaseMetaData.getDatabaseProductName().toLowerCase();
                if (string.indexOf("mysql") >= 0) {
                    a = "MYSQL";
                } else if (string.indexOf("oracle") >= 0) {
                    a = "ORACLE";
                } else if (string.indexOf("dm") >= 0) {
                    a = "DM";
                } else if (string.indexOf("sqlserver") >= 0 || string.indexOf("sql server") >= 0) {
                    a = "SQLSERVER";
                } else if (string.indexOf("postgresql") >= 0) {
                    a = "POSTGRESQL";
                } else if (string.indexOf("mariadb") >= 0) {
                    a = "MARIADB";
                } else {
                    b.error("数据库类型:[" + string + "]不识别!");
                }
            }
            catch (Exception exception) {
                b.error(exception.getMessage(), (Throwable)exception);
            }
            finally {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            }
        }
        return a;
    }

    public static String a(Connection connection) throws SQLException, DBException {
        if ("".equals(a)) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String string = databaseMetaData.getDatabaseProductName().toLowerCase();
            if (string.indexOf("mysql") >= 0) {
                a = "MYSQL";
            } else if (string.indexOf("oracle") >= 0) {
                a = "ORACLE";
            } else if (string.indexOf("sqlserver") >= 0 || string.indexOf("sql server") >= 0) {
                a = "SQLSERVER";
            } else if (string.indexOf("postgresql") >= 0) {
                a = "POSTGRESQL";
            } else if (string.indexOf("mariadb") >= 0) {
                a = "MARIADB";
            } else {
                b.error("数据库类型:[" + string + "]不识别!");
            }
        }
        return a;
    }

    public static String a(String string, String string2) {
        switch (string2) {
            case "ORACLE": {
                return string.toUpperCase();
            }
            case "POSTGRESQL": {
                return string.toLowerCase();
            }
        }
        return string;
    }

    public static Boolean a(String string) {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            String[] arrstring = new String[]{"TABLE"};
            connection = d.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String string2 = null;
            try {
                string2 = d.getDatabaseType();
            }
            catch (DBException dBException) {
                dBException.printStackTrace();
            }
            String string3 = d.a(string, string2);
            b b2 = (b)SpringContextUtils.getBean(b.class);
            String string4 = b2.getUsername();
            if ("ORACLE".equals(string2) || "DM".equals(string2)) {
                string4 = string4 != null ? string4.toUpperCase() : null;
            }
            resultSet = databaseMetaData.getTables(connection.getCatalog(), string4, string3, arrstring);
            resultSet = "SQLSERVER".equals(string2) ? databaseMetaData.getTables(connection.getCatalog(), null, string3, arrstring) : databaseMetaData.getTables(connection.getCatalog(), string4, string3, arrstring);
            if (resultSet.next()) {
                b.info("数据库表：【" + string + "】已存在");
                Boolean bl = true;
                return bl;
            }
            Boolean bl = false;
            return bl;
        }
        catch (SQLException sQLException) {
            throw new RuntimeException();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException sQLException) {
                b.error(sQLException.getMessage(), (Throwable)sQLException);
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
        String string = d.getDatabaseType();
        return d.b(string);
    }

    public static String b(String string) throws SQLException, DBException {
        String string2 = "org.hibernate.dialect.MySQL5InnoDBDialect";
        switch (string) {
            case "SQLSERVER": {
                string2 = "org.hibernate.dialect.SQLServerDialect";
                break;
            }
            case "POSTGRESQL": {
                string2 = "org.hibernate.dialect.PostgreSQLDialect";
                break;
            }
            case "ORACLE": {
                string2 = "org.hibernate.dialect.OracleDialect";
                break;
            }
            case "DM": {
                string2 = "org.hibernate.dialect.DmDialect";
                break;
            }
        }
        return string2;
    }

    public static String c(String string) {
        return string;
    }
}

