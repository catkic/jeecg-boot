/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package org.jeecg.modules.online.config.service.handler;

import org.apache.commons.lang.StringUtils;
import org.jeecg.modules.online.config.util.ColumnProperty;
import org.jeecg.modules.online.config.exception.DBException;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class PostgreTableHandler implements DbTableHandleI {
    @Override
    public String getAddColumnSql(ColumnProperty columnMeta) {
        return " ADD COLUMN " + this.a(columnMeta) + ";";
    }

    @Override
    public String getReNameFieldName(ColumnProperty columnMeta) {
        return " RENAME  COLUMN  " + columnMeta.getOldColumnName() + " to " + columnMeta.getColumnName() + ";";
    }

    @Override
    public String getUpdateColumnSql(ColumnProperty cgformcolumnMeta, ColumnProperty datacolumnMeta) throws DBException {
        return "  ALTER  COLUMN   " + this.a(cgformcolumnMeta, datacolumnMeta) + ";";
    }

    @Override
    public String getSpecialHandle(ColumnProperty cgformcolumnMeta, ColumnProperty datacolumnMeta) {
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

    private String a(ColumnProperty columnProperty2, ColumnProperty columnProperty3) throws DBException {
        String string = "";
        if (columnProperty2.getColumnType().equalsIgnoreCase("string")) {
            string = columnProperty2.getColumnName() + "  type character varying(" + columnProperty2.getColumnSize() + ") ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("date")) {
            string = columnProperty2.getColumnName() + "  type timestamp ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("int")) {
            string = columnProperty2.getColumnName() + " type int4 ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("double")) {
            string = columnProperty2.getColumnName() + " type  numeric(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ") ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("BigDecimal")) {
            string = columnProperty2.getColumnName() + " type  decimal(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ") ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("text")) {
            string = columnProperty2.getColumnName() + " text ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("blob")) {
            throw new DBException("blob类型不可修改");
        }
        return string;
    }

    private String b(ColumnProperty columnProperty2, ColumnProperty columnProperty3) {
        String string = "";
        if (!columnProperty2.strEqual(columnProperty3)) {
            if (columnProperty2.getColumnType().equalsIgnoreCase("string")) {
                string = columnProperty2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String) columnProperty2.getFieldDefault()) ? " SET DEFAULT " + columnProperty2.getFieldDefault() : " DROP DEFAULT");
            } else if (columnProperty2.getColumnType().equalsIgnoreCase("date")) {
                string = columnProperty2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String) columnProperty2.getFieldDefault()) ? " SET DEFAULT " + columnProperty2.getFieldDefault() : " DROP DEFAULT");
            } else if (columnProperty2.getColumnType().equalsIgnoreCase("int")) {
                string = columnProperty2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String) columnProperty2.getFieldDefault()) ? " SET DEFAULT " + columnProperty2.getFieldDefault() : " DROP DEFAULT");
            } else if (columnProperty2.getColumnType().equalsIgnoreCase("double")) {
                string = columnProperty2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String) columnProperty2.getFieldDefault()) ? " SET DEFAULT " + columnProperty2.getFieldDefault() : " DROP DEFAULT");
            } else if (columnProperty2.getColumnType().equalsIgnoreCase("bigdecimal")) {
                string = columnProperty2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String) columnProperty2.getFieldDefault()) ? " SET DEFAULT " + columnProperty2.getFieldDefault() : " DROP DEFAULT");
            } else if (columnProperty2.getColumnType().equalsIgnoreCase("text")) {
                string = columnProperty2.getColumnName();
                string = string + (StringUtils.isNotEmpty((String) columnProperty2.getFieldDefault()) ? " SET DEFAULT " + columnProperty2.getFieldDefault() : " DROP DEFAULT");
            }
        }
        return string;
    }

    private String a(ColumnProperty columnProperty2) {
        String string = "";
        if (columnProperty2.getColumnType().equalsIgnoreCase("string")) {
            string = columnProperty2.getColumnName() + " character varying(" + columnProperty2.getColumnSize() + ") ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("date")) {
            string = columnProperty2.getColumnName() + " timestamp ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("int")) {
            string = columnProperty2.getColumnName() + " int4";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("double")) {
            string = columnProperty2.getColumnName() + " numeric(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ") ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("bigdecimal")) {
            string = columnProperty2.getColumnName() + " decimal(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ") ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("blob")) {
            string = columnProperty2.getColumnName() + " bytea(" + columnProperty2.getColumnSize() + ") ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("text")) {
            string = columnProperty2.getColumnName() + " text ";
        }
        string = string + (StringUtils.isNotEmpty((String) columnProperty2.getFieldDefault()) ? " DEFAULT " + columnProperty2.getFieldDefault() : " ");
        return string;
    }

    private String b(ColumnProperty columnProperty2) {
        String string = "";
        if (columnProperty2.getColumnType().equalsIgnoreCase("string")) {
            string = columnProperty2.getColumnName() + " character varying(" + columnProperty2.getColumnSize() + ") ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("date")) {
            string = columnProperty2.getColumnName() + " datetime ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("int")) {
            string = columnProperty2.getColumnName() + " int(" + columnProperty2.getColumnSize() + ") ";
        } else if (columnProperty2.getColumnType().equalsIgnoreCase("double")) {
            string = columnProperty2.getColumnName() + " numeric(" + columnProperty2.getColumnSize() + "," + columnProperty2.getDecimalDigits() + ") ";
        }
        return string;
    }

    @Override
    public String getCommentSql(ColumnProperty columnMeta) {
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

