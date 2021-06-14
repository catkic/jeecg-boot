/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package org.jeecg.modules.online.config.service.a;

import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.online.config.b.a;
import org.jeecg.modules.online.config.exception.DBException;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class d
implements DbTableHandleI {
    @Override
    public String getAddColumnSql(a columnMeta) {
        return " ADD COLUMN " + this.a(columnMeta) + ";";
    }

    @Override
    public String getReNameFieldName(a columnMeta) {
        return " RENAME  COLUMN  " + columnMeta.getOldColumnName() + " to " + columnMeta.getColumnName() + ";";
    }

    @Override
    public String getUpdateColumnSql(a cgformcolumnMeta, a datacolumnMeta) throws DBException {
        return "  ALTER  COLUMN   " + this.a(cgformcolumnMeta, datacolumnMeta) + ";";
    }

    @Override
    public String getSpecialHandle(a cgformcolumnMeta, a datacolumnMeta) {
        return "  ALTER  COLUMN   " + this.b(cgformcolumnMeta, datacolumnMeta) + ";";
    }

    @Override
    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String string = "";
        if (dataType.equalsIgnoreCase("varchar")) {
            string = "string";
        } else if (dataType.equalsIgnoreCase("double")) {
            string = "double";
        } else if (dataType.contains("int")) {
            string = "int";
        } else if (dataType.equalsIgnoreCase("Date")) {
            string = "date";
        } else if (dataType.equalsIgnoreCase("timestamp")) {
            string = "date";
        } else if (dataType.equalsIgnoreCase("bytea")) {
            string = "blob";
        } else if (dataType.equalsIgnoreCase("text")) {
            string = "text";
        } else if (dataType.equalsIgnoreCase("decimal")) {
            string = "bigdecimal";
        } else if (dataType.equalsIgnoreCase("numeric")) {
            string = "bigdecimal";
        }
        return string;
    }

    @Override
    public String dropTableSQL(String tableName) {
        return " DROP TABLE  " + tableName + " ;";
    }

    @Override
    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName + ";";
    }

    private String a(a a2, a a3) throws DBException {
        String string = "";
        if (a2.getColunmType().equalsIgnoreCase("string")) {
            string = a2.getColumnName() + "  type character varying(" + a2.getColumnSize() + ") ";
        } else if (a2.getColunmType().equalsIgnoreCase("date")) {
            string = a2.getColumnName() + "  type timestamp ";
        } else if (a2.getColunmType().equalsIgnoreCase("int")) {
            string = a2.getColumnName() + " type int4 ";
        } else if (a2.getColunmType().equalsIgnoreCase("double")) {
            string = a2.getColumnName() + " type  numeric(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") ";
        } else if (a2.getColunmType().equalsIgnoreCase("BigDecimal")) {
            string = a2.getColumnName() + " type  decimal(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") ";
        } else if (a2.getColunmType().equalsIgnoreCase("text")) {
            string = a2.getColumnName() + " text ";
        } else if (a2.getColunmType().equalsIgnoreCase("blob")) {
            throw new DBException("blob类型不可修改");
        }
        return string;
    }

    private String b(a a2, a a3) {
        String string = "";
        if (!a2.a(a3)) {
            if (a2.getColunmType().equalsIgnoreCase("string")) {
                string = a2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String)a2.getFieldDefault()) ? " SET DEFAULT " + a2.getFieldDefault() : " DROP DEFAULT");
            } else if (a2.getColunmType().equalsIgnoreCase("date")) {
                string = a2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String)a2.getFieldDefault()) ? " SET DEFAULT " + a2.getFieldDefault() : " DROP DEFAULT");
            } else if (a2.getColunmType().equalsIgnoreCase("int")) {
                string = a2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String)a2.getFieldDefault()) ? " SET DEFAULT " + a2.getFieldDefault() : " DROP DEFAULT");
            } else if (a2.getColunmType().equalsIgnoreCase("double")) {
                string = a2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String)a2.getFieldDefault()) ? " SET DEFAULT " + a2.getFieldDefault() : " DROP DEFAULT");
            } else if (a2.getColunmType().equalsIgnoreCase("bigdecimal")) {
                string = a2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String)a2.getFieldDefault()) ? " SET DEFAULT " + a2.getFieldDefault() : " DROP DEFAULT");
            } else if (a2.getColunmType().equalsIgnoreCase("text")) {
                string = a2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String)a2.getFieldDefault()) ? " SET DEFAULT " + a2.getFieldDefault() : " DROP DEFAULT");
            }
        }
        return string;
    }

    private String a(a a2) {
        String string = "";
        if (a2.getColunmType().equalsIgnoreCase("string")) {
            string = a2.getColumnName() + " character varying(" + a2.getColumnSize() + ") ";
        } else if (a2.getColunmType().equalsIgnoreCase("date")) {
            string = a2.getColumnName() + " timestamp ";
        } else if (a2.getColunmType().equalsIgnoreCase("int")) {
            string = a2.getColumnName() + " int4";
        } else if (a2.getColunmType().equalsIgnoreCase("double")) {
            string = a2.getColumnName() + " numeric(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") ";
        } else if (a2.getColunmType().equalsIgnoreCase("bigdecimal")) {
            string = a2.getColumnName() + " decimal(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") ";
        } else if (a2.getColunmType().equalsIgnoreCase("blob")) {
            string = a2.getColumnName() + " bytea(" + a2.getColumnSize() + ") ";
        } else if (a2.getColunmType().equalsIgnoreCase("text")) {
            string = a2.getColumnName() + " text ";
        }
        string = string + (StringUtils.isNotEmpty((String)a2.getFieldDefault()) ? " DEFAULT " + a2.getFieldDefault() : " ");
        return string;
    }

    private String b(a a2) {
        String string = "";
        if (a2.getColunmType().equalsIgnoreCase("string")) {
            string = a2.getColumnName() + " character varying(" + a2.getColumnSize() + ") ";
        } else if (a2.getColunmType().equalsIgnoreCase("date")) {
            string = a2.getColumnName() + " datetime ";
        } else if (a2.getColunmType().equalsIgnoreCase("int")) {
            string = a2.getColumnName() + " int(" + a2.getColumnSize() + ") ";
        } else if (a2.getColunmType().equalsIgnoreCase("double")) {
            string = a2.getColumnName() + " numeric(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") ";
        }
        return string;
    }

    @Override
    public String getCommentSql(a columnMeta) {
        return "COMMENT ON COLUMN " + columnMeta.getTableName() + "." + columnMeta.getColumnName() + " IS '" + columnMeta.getComment() + "'";
    }

    @Override
    public String dropIndexs(String indexName, String tableName) {
        return "DROP INDEX " + indexName;
    }

    @Override
    public String countIndex(String indexName, String tableName) {
        return "SELECT count(*) FROM pg_indexes WHERE indexname = '" + indexName + "' and tablename = '" + tableName + "'";
    }
}

