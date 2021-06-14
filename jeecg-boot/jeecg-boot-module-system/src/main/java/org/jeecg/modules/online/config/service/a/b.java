/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package org.jeecg.modules.online.config.service.a;

import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.online.config.b.a;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class b
implements DbTableHandleI {
    @Override
    public String getAddColumnSql(a columnMeta) {
        return " ADD COLUMN " + this.a(columnMeta) + ";";
    }

    @Override
    public String getReNameFieldName(a columnMeta) {
        return "CHANGE COLUMN " + columnMeta.getOldColumnName() + " " + this.b(columnMeta) + " ;";
    }

    @Override
    public String getUpdateColumnSql(a cgformcolumnMeta, a datacolumnMeta) {
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

    private String a(a a2, a a3) {
        String string = "";
        if (a2.getColunmType().equalsIgnoreCase("string")) {
            string = a2.getColumnName() + " varchar(" + a2.getColumnSize() + ") " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("date")) {
            string = a2.getColumnName() + " datetime " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("int")) {
            string = a2.getColumnName() + " int(" + a2.getColumnSize() + ") " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("double")) {
            string = a2.getColumnName() + " double(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("bigdecimal")) {
            string = a2.getColumnName() + " decimal(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("text")) {
            string = a2.getColumnName() + " text " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("blob")) {
            string = a2.getColumnName() + " blob " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        }
        string = string + (StringUtils.isNotEmpty((String)a2.getComment()) ? " COMMENT '" + a2.getComment() + "'" : " ");
        string = string + (StringUtils.isNotEmpty((String)a2.getFieldDefault()) ? " DEFAULT " + a2.getFieldDefault() : " ");
        String string2 = a2.getPkType();
        if ("id".equalsIgnoreCase(a2.getColumnName()) && string2 != null && ("SEQUENCE".equalsIgnoreCase(string2) || "NATIVE".equalsIgnoreCase(string2))) {
            string = string + " AUTO_INCREMENT ";
        }
        return string;
    }

    private String b(a a2, a a3) {
        String string = this.a(a2, a3);
        return string;
    }

    private String a(a a2) {
        String string = this.a(a2, null);
        return string;
    }

    private String b(a a2) {
        String string = this.a(a2, null);
        return string;
    }

    @Override
    public String getCommentSql(a columnMeta) {
        return "";
    }

    @Override
    public String getSpecialHandle(a cgformcolumnMeta, a datacolumnMeta) {
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

