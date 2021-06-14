/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package org.jeecg.modules.online.config.service.a;

import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class a
implements DbTableHandleI {
    @Override
    public String getAddColumnSql(org.jeecg.modules.online.config.b.a columnMeta) {
        return " ADD COLUMN " + this.a(columnMeta) + "";
    }

    @Override
    public String getReNameFieldName(org.jeecg.modules.online.config.b.a columnMeta) {
        return "RENAME COLUMN " + columnMeta.getOldColumnName() + " TO " + columnMeta.getColumnName() + "";
    }

    @Override
    public String getUpdateColumnSql(org.jeecg.modules.online.config.b.a cgformcolumnMeta, org.jeecg.modules.online.config.b.a datacolumnMeta) {
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

    private String a(org.jeecg.modules.online.config.b.a a2) {
        String string = "(\"" + a2.getColumnName() + "\"";
        if (a2.getColunmType().equalsIgnoreCase("string")) {
            string = string + " varchar2(" + a2.getColumnSize() + ")";
        } else if (a2.getColunmType().equalsIgnoreCase("date")) {
            string = string + " date";
        } else if (a2.getColunmType().equalsIgnoreCase("int")) {
            string = string + " INT";
        } else if (a2.getColunmType().equalsIgnoreCase("double")) {
            string = string + " NUMBER(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ")";
        } else if (a2.getColunmType().equalsIgnoreCase("bigdecimal")) {
            string = string + " DECIMAL(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ")";
        } else if (a2.getColunmType().equalsIgnoreCase("text")) {
            string = string + " CLOB ";
        } else if (a2.getColunmType().equalsIgnoreCase("blob")) {
            string = string + " BLOB ";
        }
        string = string + (StringUtils.isNotEmpty((String)a2.getFieldDefault()) ? " DEFAULT " + a2.getFieldDefault() : " ");
        string = string + ("Y".equals(a2.getIsNullable()) ? " NULL" : " NOT NULL");
        string = string + ")";
        return string;
    }

    private String a(org.jeecg.modules.online.config.b.a a2, org.jeecg.modules.online.config.b.a a3) {
        String string = "";
        String string2 = "";
        if (!a3.getIsNullable().equals(a2.getIsNullable())) {
            String string3 = string2 = a2.getIsNullable().equals("Y") ? "NULL" : "NOT NULL";
        }
        if (a2.getColunmType().equalsIgnoreCase("string")) {
            string = a2.getColumnName() + " varchar2(" + a2.getColumnSize() + ")" + string2;
        } else if (a2.getColunmType().equalsIgnoreCase("date")) {
            string = a2.getColumnName() + " date " + string2;
        } else if (a2.getColunmType().equalsIgnoreCase("int")) {
            string = a2.getColumnName() + " INT " + string2;
        } else if (a2.getColunmType().equalsIgnoreCase("double")) {
            string = a2.getColumnName() + " NUMBER(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") " + string2;
        } else if (a2.getColunmType().equalsIgnoreCase("bigdecimal")) {
            string = a2.getColumnName() + " DECIMAL(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") " + string2;
        } else if (a2.getColunmType().equalsIgnoreCase("blob")) {
            string = a2.getColumnName() + " BLOB " + string2;
        } else if (a2.getColunmType().equalsIgnoreCase("text")) {
            string = a2.getColumnName() + " CLOB " + string2;
        }
        string = string + (StringUtils.isNotEmpty((String)a2.getFieldDefault()) ? " DEFAULT " + a2.getFieldDefault() : " ");
        string = string + string2;
        return string;
    }

    @Override
    public String getCommentSql(org.jeecg.modules.online.config.b.a columnMeta) {
        return "COMMENT ON COLUMN " + columnMeta.getTableName() + "." + columnMeta.getColumnName() + " IS '" + columnMeta.getComment() + "'";
    }

    @Override
    public String getSpecialHandle(org.jeecg.modules.online.config.b.a cgformcolumnMeta, org.jeecg.modules.online.config.b.a datacolumnMeta) {
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

