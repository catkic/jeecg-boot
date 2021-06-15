/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package org.jeecg.modules.online.config.service.handler;

import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.online.config.service.DbTableHandleI;
import org.jeecg.modules.online.config.util.ColumnProperty;

public class DMTableHandler
implements DbTableHandleI {
    @Override
    public String getAddColumnSql(ColumnProperty columnMeta) {
        return " ADD COLUMN " + this.a(columnMeta) + "";
    }

    @Override
    public String getReNameFieldName(ColumnProperty columnMeta) {
        return "RENAME COLUMN " + columnMeta.getOldColumnName() + " TO " + columnMeta.getColumnName() + "";
    }

    @Override
    public String getUpdateColumnSql(ColumnProperty cgformcolumnMeta, ColumnProperty datacolumnMeta) {
        return " MODIFY " + this.a(cgformcolumnMeta, datacolumnMeta) + "";
    }

    @Override
    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String string = "";
        if (dataType.equalsIgnoreCase("varchar2")) {
            string = "string";
        } else if (dataType.equalsIgnoreCase("varchar")) {
            string = "string";
        } else if (dataType.equalsIgnoreCase("nvarchar2")) {
            string = "string";
        } else if (dataType.equalsIgnoreCase("double")) {
            string = "double";
        } else if (dataType.equalsIgnoreCase("number") && digits == 0) {
            string = "int";
        } else if (dataType.equalsIgnoreCase("number") && digits != 0) {
            string = "double";
        } else if (dataType.equalsIgnoreCase("int")) {
            string = "int";
        } else if (dataType.equalsIgnoreCase("Date")) {
            string = "date";
        } else if (dataType.equalsIgnoreCase("Datetime")) {
            string = "date";
        } else if (dataType.equalsIgnoreCase("blob")) {
            string = "blob";
        } else if (dataType.equalsIgnoreCase("clob")) {
            string = "text";
        }
        return string;
    }

    @Override
    public String dropTableSQL(String tableName) {
        return " DROP TABLE  " + tableName.toLowerCase() + " ";
    }

    @Override
    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName.toUpperCase() + "";
    }

    private String a(ColumnProperty columnProperty2) {
        String string = "(\"" + columnProperty2.getColumnName() + "\"";
        if (columnProperty2.getColumnType().equalsIgnoreCase("string")) {
            string = string + " varchar2(" + columnProperty2.getColumnSize() + ")";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("date")) {
            string = string + " date";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("int")) {
            string = string + " INT";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("double")) {
            string = string + " NUMBER(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ")";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("bigdecimal")) {
            string = string + " DECIMAL(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ")";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("text")) {
            string = string + " CLOB ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("blob")) {
            string = string + " BLOB ";
        }
        string = string + (StringUtils.isNotEmpty((String) columnProperty2.getFieldDefault()) ? " DEFAULT " + columnProperty2.getFieldDefault() : " ");
        string = string + ("Y".equals(columnProperty2.getIsNullable()) ? " NULL" : " NOT NULL");
        string = string + ")";
        return string;
    }

    private String a(ColumnProperty columnProperty2, ColumnProperty columnProperty3) {
        String string = "";
        String string2 = "";
        if (!columnProperty3.getIsNullable().equals(columnProperty2.getIsNullable())) {
            String string3 = string2 = columnProperty2.getIsNullable().equals("Y") ? "NULL" : "NOT NULL";
        }
        if (columnProperty2.getColumnType().equalsIgnoreCase("string")) {
            string = columnProperty2.getColumnName() + " varchar2(" + columnProperty2.getColumnSize() + ")" + string2;
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("date")) {
            string = columnProperty2.getColumnName() + " date " + string2;
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("int")) {
            string = columnProperty2.getColumnName() + " INT " + string2;
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("double")) {
            string = columnProperty2.getColumnName() + " NUMBER(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ") " + string2;
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("bigdecimal")) {
            string = columnProperty2.getColumnName() + " DECIMAL(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ") " + string2;
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("blob")) {
            string = columnProperty2.getColumnName() + " BLOB " + string2;
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("text")) {
            string = columnProperty2.getColumnName() + " CLOB " + string2;
        }
        string = string + (StringUtils.isNotEmpty((String) columnProperty2.getFieldDefault()) ? " DEFAULT " + columnProperty2.getFieldDefault() : " ");
        string = string + string2;
        return string;
    }

    @Override
    public String getCommentSql(ColumnProperty columnMeta) {
        return "COMMENT ON COLUMN " + columnMeta.getTableName() + "." + columnMeta.getColumnName() + " IS '" + columnMeta.getComment() + "'";
    }

    @Override
    public String getSpecialHandle(ColumnProperty cgformcolumnMeta, ColumnProperty datacolumnMeta) {
        return null;
    }

    @Override
    public String dropIndexs(String indexName, String tableName) {
        return "DROP INDEX " + indexName;
    }

    @Override
    public String countIndex(String indexName, String tableName) {
        return "select count(*) from user_ind_columns where index_name=upper('" + indexName + "')";
    }
}

