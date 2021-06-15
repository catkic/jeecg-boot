/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package org.jeecg.modules.online.config.service.handler;

import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.online.config.util.ColumnProperty;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class MysqlTableHandler
implements DbTableHandleI {
    @Override
    public String getAddColumnSql(ColumnProperty columnMeta) {
        return " ADD COLUMN " + this.a(columnMeta) + ";";
    }

    @Override
    public String getReNameFieldName(ColumnProperty columnMeta) {
        return "CHANGE COLUMN " + columnMeta.getOldColumnName() + " " + this.b(columnMeta) + " ;";
    }

    @Override
    public String getUpdateColumnSql(ColumnProperty cgformcolumnMeta, ColumnProperty datacolumnMeta) {
        return " MODIFY COLUMN " + this.b(cgformcolumnMeta, datacolumnMeta) + ";";
    }

    @Override
    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String string = "";
        if (dataType.equalsIgnoreCase("varchar")) {
            string = "string";
        } else if (dataType.equalsIgnoreCase("double")) {
            string = "double";
        } else if (dataType.equalsIgnoreCase("int")) {
            string = "int";
        } else if (dataType.equalsIgnoreCase("Date")) {
            string = "date";
        } else if (dataType.equalsIgnoreCase("Datetime")) {
            string = "date";
        } else if (dataType.equalsIgnoreCase("decimal")) {
            string = "bigdecimal";
        } else if (dataType.equalsIgnoreCase("text")) {
            string = "text";
        } else if (dataType.equalsIgnoreCase("blob")) {
            string = "blob";
        }
        return string;
    }

    @Override
    public String dropTableSQL(String tableName) {
        return " DROP TABLE IF EXISTS " + tableName + " ;";
    }

    @Override
    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName + ";";
    }

    private String a(ColumnProperty columnProperty2, ColumnProperty columnProperty3) {
        String string = "";
        if (columnProperty2.getColumnType().equalsIgnoreCase("string")) {
            string = columnProperty2.getColumnName() + " varchar(" + columnProperty2.getColumnSize() + ") " + ("Y".equals(columnProperty2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("date")) {
            string = columnProperty2.getColumnName() + " datetime " + ("Y".equals(columnProperty2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("int")) {
            string = columnProperty2.getColumnName() + " int(" + columnProperty2.getColumnSize() + ") " + ("Y".equals(columnProperty2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("double")) {
            string = columnProperty2.getColumnName() + " double(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ") " + ("Y".equals(columnProperty2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("bigdecimal")) {
            string = columnProperty2.getColumnName() + " decimal(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ") " + ("Y".equals(columnProperty2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("text")) {
            string = columnProperty2.getColumnName() + " text " + ("Y".equals(columnProperty2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("blob")) {
            string = columnProperty2.getColumnName() + " blob " + ("Y".equals(columnProperty2.getIsNullable()) ? "NULL" : "NOT NULL");
        }
        string = string + (StringUtils.isNotEmpty((String) columnProperty2.getComment()) ? " COMMENT '" + columnProperty2.getComment() + "'" : " ");
        string = string + (StringUtils.isNotEmpty((String) columnProperty2.getFieldDefault()) ? " DEFAULT " + columnProperty2.getFieldDefault() : " ");
        String string2 = columnProperty2.getPkType();
        if ("id".equalsIgnoreCase(columnProperty2.getColumnName()) && string2 != null && ("SEQUENCE".equalsIgnoreCase(string2) || "NATIVE".equalsIgnoreCase(string2))) {
            string = string + " AUTO_INCREMENT ";
        }
        return string;
    }

    private String b(ColumnProperty columnProperty2, ColumnProperty columnProperty3) {
        String string = this.a(columnProperty2, columnProperty3);
        return string;
    }

    private String a(ColumnProperty columnProperty2) {
        String string = this.a(columnProperty2, null);
        return string;
    }

    private String b(ColumnProperty columnProperty2) {
        String string = this.a(columnProperty2, null);
        return string;
    }

    @Override
    public String getCommentSql(ColumnProperty columnMeta) {
        return "";
    }

    @Override
    public String getSpecialHandle(ColumnProperty cgformcolumnMeta, ColumnProperty datacolumnMeta) {
        return null;
    }

    @Override
    public String dropIndexs(String indexName, String tableName) {
        return "DROP INDEX " + indexName + " ON " + tableName;
    }

    @Override
    public String countIndex(String indexName, String tableName) {
        return "select COUNT(*) from information_schema.statistics where table_name = '" + tableName + "'  AND index_name = '" + indexName + "'";
    }
}

