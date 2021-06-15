/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package org.jeecg.modules.online.config.util;

import org.apache.commons.lang.StringUtils;

public class ColumnProperty {
    private String tableName;
    private String columnId;
    private String columnName;
    private int columnSize;
    private String columnType;
    private String comment;
    private String fieldDefault;
    private int decimalDigits;
    private String isNullable;
    private String pkType;
    private String oldColumnName;
    private String realDbType;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ColumnProperty)) {
            return false;
        }
        ColumnProperty columnProperty2 = (ColumnProperty) obj;
        if (this.columnType.contains("date") || this.columnType.contains("blob") || this.columnType.contains("text")) {
            return this.columnName.equals(columnProperty2.getColumnName()) && this.isNullable.equals(columnProperty2.isNullable) && this.strEqual(this.comment, columnProperty2.getComment()) && this.strEqual(this.fieldDefault, columnProperty2.getFieldDefault());
        }
        return this.columnType.equals(columnProperty2.getColumnType()) && this.isNullable.equals(columnProperty2.isNullable) && this.columnSize == columnProperty2.getColumnSize() && this.strEqual(this.comment, columnProperty2.getComment()) && this.strEqual(this.fieldDefault, columnProperty2.getFieldDefault());
    }

    public boolean strEqual(Object object, String dataBaseType) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof ColumnProperty)) {
            return false;
        }
        ColumnProperty columnProperty2 = (ColumnProperty) object;
        if ("SQLSERVER".equals(dataBaseType)) {
            if (this.columnType.contains("date") || this.columnType.contains("blob") || this.columnType.contains("text")) {
                return this.columnName.equals(columnProperty2.getColumnName()) && this.isNullable.equals(columnProperty2.isNullable);
            }
            return this.columnType.equals(columnProperty2.getColumnType()) && this.isNullable.equals(columnProperty2.isNullable) && this.columnSize == columnProperty2.getColumnSize() && this.strEqual(this.fieldDefault, columnProperty2.getFieldDefault());
        }
        if ("POSTGRESQL".equals(dataBaseType)) {
            if (this.columnType.contains("date") || this.columnType.contains("blob") || this.columnType.contains("text")) {
                return this.columnName.equals(columnProperty2.getColumnName()) && this.isNullable.equals(columnProperty2.isNullable);
            }
            return this.columnType.equals(columnProperty2.getColumnType()) && this.isNullable.equals(columnProperty2.isNullable) && this.columnSize == columnProperty2.getColumnSize() && this.strEqual(this.fieldDefault, columnProperty2.getFieldDefault());
        }
        if ("ORACLE".equals(dataBaseType) || "DM".equals(dataBaseType)) {
            if (this.columnType.contains("date") || this.columnType.contains("blob") || this.columnType.contains("text")) {
                return this.columnName.equals(columnProperty2.getColumnName()) && this.isNullable.equals(columnProperty2.isNullable);
            }
            return this.columnType.equals(columnProperty2.getColumnType()) && this.isNullable.equals(columnProperty2.isNullable) && this.columnSize == columnProperty2.getColumnSize() && this.strEqual(this.fieldDefault, columnProperty2.getFieldDefault());
        }
        if (this.columnType.contains("date") || this.columnType.contains("blob") || this.columnType.contains("text")) {
            return this.columnType.equals(columnProperty2.getColumnType()) && this.columnName.equals(columnProperty2.getColumnName()) && this.isNullable.equals(columnProperty2.isNullable) && this.strEqual(this.comment, columnProperty2.getComment()) && this.strEqual(this.fieldDefault, columnProperty2.getFieldDefault());
        }
        return this.columnType.equals(columnProperty2.getColumnType()) && this.isNullable.equals(columnProperty2.isNullable) && this.columnSize == columnProperty2.getColumnSize() && this.strEqual(this.comment, columnProperty2.getComment()) && this.strEqual(this.fieldDefault, columnProperty2.getFieldDefault());
    }

    public boolean strEqual(ColumnProperty columnProperty2) {
        if (columnProperty2 == this) {
            return true;
        }
        return this.strEqual(this.comment, columnProperty2.getComment());
    }

    public boolean columnCommentEqual(ColumnProperty columnProperty2) {
        if (columnProperty2 == this) {
            return true;
        }
        return this.strEqual(this.comment, columnProperty2.getComment());
    }

    private boolean strEqual(String string, String string2) {
        // 一个空另外一个不空时，两个不等
        if (StringUtils.isNotEmpty(string) != StringUtils.isNotEmpty(string2)) {
            return false;
        }
        if (StringUtils.isNotEmpty(string)) {
            return string.equals(string2);
        }
        return true;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public int getColumnSize() {
        return this.columnSize;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public String getComment() {
        return this.comment;
    }

    public int getDecimalDigits() {
        return this.decimalDigits;
    }

    public String getIsNullable() {
        return this.isNullable;
    }

    public String getOldColumnName() {
        return this.oldColumnName;
    }

    public int hashCode() {
        return this.columnSize + this.columnType.hashCode() * this.columnName.hashCode();
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public void setOldColumnName(String oldColumnName) {
        this.oldColumnName = oldColumnName;
    }

    public String toString() {
        return this.columnName + "," + this.columnType + "," + this.isNullable + "," + this.columnSize;
    }

    public String getColumnId() {
        return this.columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldDefault() {
        return this.fieldDefault;
    }

    public void setFieldDefault(String fieldDefault) {
        this.fieldDefault = fieldDefault;
    }

    public String getPkType() {
        return this.pkType;
    }

    public void setPkType(String pkType) {
        this.pkType = pkType;
    }

    public String getRealDbType() {
        return this.realDbType;
    }

    public void setRealDbType(String realDbType) {
        this.realDbType = realDbType;
    }
}

