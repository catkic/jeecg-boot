/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jeecg.common.util.oConvertUtils
 */
package org.jeecg.modules.online.config.service.a;

import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.config.b.a;
import org.jeecg.modules.online.config.service.DbTableHandleI;

public class e
implements DbTableHandleI {
    @Override
    public String getAddColumnSql(a columnMeta) {
        return " ADD  " + this.a(columnMeta) + ";";
    }

    @Override
    public String getReNameFieldName(a columnMeta) {
        return "  sp_rename '" + columnMeta.getTableName() + "." + columnMeta.getOldColumnName() + "', '" + columnMeta.getColumnName() + "', 'COLUMN';";
    }

    @Override
    public String getUpdateColumnSql(a cgformcolumnMeta, a datacolumnMeta) {
        return " ALTER COLUMN  " + this.a(cgformcolumnMeta, datacolumnMeta) + ";";
    }

    @Override
    public String getMatchClassTypeByDataType(String dataType, int digits) {
        String string = "";
        if (dataType.equalsIgnoreCase("varchar") || dataType.equalsIgnoreCase("nvarchar")) {
            string = "string";
        } else if (dataType.equalsIgnoreCase("float")) {
            string = "double";
        } else if (dataType.equalsIgnoreCase("int")) {
            string = "int";
        } else if (dataType.equalsIgnoreCase("Date")) {
            string = "date";
        } else if (dataType.equalsIgnoreCase("Datetime")) {
            string = "date";
        } else if (dataType.equalsIgnoreCase("numeric")) {
            string = "bigdecimal";
        } else if (dataType.equalsIgnoreCase("varbinary") || dataType.equalsIgnoreCase("image")) {
            string = "blob";
        } else if (dataType.equalsIgnoreCase("text") || dataType.equalsIgnoreCase("ntext")) {
            string = "text";
        }
        return string;
    }

    @Override
    public String dropTableSQL(String tableName) {
        return " DROP TABLE " + tableName + " ;";
    }

    @Override
    public String getDropColumnSql(String fieldName) {
        return " DROP COLUMN " + fieldName + ";";
    }

    private String a(a a2, a a3) {
        String string = "";
        if (a2.getColunmType().equalsIgnoreCase("string")) {
            string = a2.getColumnName() + " nvarchar(" + a2.getColumnSize() + ") " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("date")) {
            string = a2.getColumnName() + " datetime " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("int")) {
            string = a2.getColumnName() + " int " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("double")) {
            string = a2.getColumnName() + " float " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("bigdecimal")) {
            string = a2.getColumnName() + " numeric(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("text")) {
            string = a2.getColumnName() + " ntext " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("blob")) {
            string = a2.getColumnName() + " image";
        }
        return string;
    }

    private String a(a a2) {
        String string = "";
        if (a2.getColunmType().equalsIgnoreCase("string")) {
            string = a2.getColumnName() + " nvarchar(" + a2.getColumnSize() + ") " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("date")) {
            string = a2.getColumnName() + " datetime " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("int")) {
            string = a2.getColumnName() + " int " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("double")) {
            string = a2.getColumnName() + " float " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("bigdecimal")) {
            string = a2.getColumnName() + " numeric(" + a2.getColumnSize() + "," + a2.getDecimalDigits() + ") " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("text")) {
            string = a2.getColumnName() + " ntext " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("blob")) {
            string = a2.getColumnName() + " image";
        }
        return string;
    }

    private String b(a a2) {
        String string = "";
        if (a2.getColunmType().equalsIgnoreCase("string")) {
            string = a2.getColumnName() + " nvarchar(" + a2.getColumnSize() + ") " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("date")) {
            string = a2.getColumnName() + " datetime " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("int")) {
            string = a2.getColumnName() + " int " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        } else if (a2.getColunmType().equalsIgnoreCase("double")) {
            string = a2.getColumnName() + " float " + ("Y".equals(a2.getIsNullable()) ? "NULL" : "NOT NULL");
        }
        return string;
    }

    @Override
    public String getCommentSql(a columnMeta) {
        StringBuffer stringBuffer = new StringBuffer("EXECUTE ");
        if (oConvertUtils.isEmpty((Object)columnMeta.getOldColumnName())) {
            stringBuffer.append("sp_addextendedproperty");
        } else {
            stringBuffer.append("sp_updateextendedproperty");
        }
        stringBuffer.append(" N'MS_Description', '");
        stringBuffer.append(columnMeta.getComment());
        stringBuffer.append("', N'SCHEMA', N'dbo', N'TABLE', N'");
        stringBuffer.append(columnMeta.getTableName());
        stringBuffer.append("', N'COLUMN', N'");
        stringBuffer.append(columnMeta.getColumnName() + "'");
        return stringBuffer.toString();
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
        return "SELECT count(*) FROM sys.indexes WHERE object_id=OBJECT_ID('" + tableName + "') and NAME= '" + indexName + "'";
    }
}

