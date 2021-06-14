/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package org.jeecg.modules.online.config.b;

import org.apache.commons.lang.StringUtils;

public class a {
    private String a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String f;
    private String g;
    private int h;
    private String i;
    private String j;
    private String k;
    private String l;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a a2 = (a)obj;
        if (this.e.contains("date") || this.e.contains("blob") || this.e.contains("text")) {
            return this.c.equals(a2.getColumnName()) && this.i.equals(a2.i) && this.a(this.f, a2.getComment()) && this.a(this.g, a2.getFieldDefault());
        }
        return this.e.equals(a2.getColunmType()) && this.i.equals(a2.i) && this.d == a2.getColumnSize() && this.a(this.f, a2.getComment()) && this.a(this.g, a2.getFieldDefault());
    }

    public boolean a(Object object, String string) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof a)) {
            return false;
        }
        a a2 = (a)object;
        if ("SQLSERVER".equals(string)) {
            if (this.e.contains("date") || this.e.contains("blob") || this.e.contains("text")) {
                return this.c.equals(a2.getColumnName()) && this.i.equals(a2.i);
            }
            return this.e.equals(a2.getColunmType()) && this.i.equals(a2.i) && this.d == a2.getColumnSize() && this.a(this.g, a2.getFieldDefault());
        }
        if ("POSTGRESQL".equals(string)) {
            if (this.e.contains("date") || this.e.contains("blob") || this.e.contains("text")) {
                return this.c.equals(a2.getColumnName()) && this.i.equals(a2.i);
            }
            return this.e.equals(a2.getColunmType()) && this.i.equals(a2.i) && this.d == a2.getColumnSize() && this.a(this.g, a2.getFieldDefault());
        }
        if ("ORACLE".equals(string) || "DM".equals(string)) {
            if (this.e.contains("date") || this.e.contains("blob") || this.e.contains("text")) {
                return this.c.equals(a2.getColumnName()) && this.i.equals(a2.i);
            }
            return this.e.equals(a2.getColunmType()) && this.i.equals(a2.i) && this.d == a2.getColumnSize() && this.a(this.g, a2.getFieldDefault());
        }
        if (this.e.contains("date") || this.e.contains("blob") || this.e.contains("text")) {
            return this.e.equals(a2.getColunmType()) && this.c.equals(a2.getColumnName()) && this.i.equals(a2.i) && this.a(this.f, a2.getComment()) && this.a(this.g, a2.getFieldDefault());
        }
        return this.e.equals(a2.getColunmType()) && this.i.equals(a2.i) && this.d == a2.getColumnSize() && this.a(this.f, a2.getComment()) && this.a(this.g, a2.getFieldDefault());
    }

    public boolean a(a a2) {
        if (a2 == this) {
            return true;
        }
        return this.a(this.f, a2.getComment());
    }

    public boolean b(a a2) {
        if (a2 == this) {
            return true;
        }
        return this.a(this.f, a2.getComment());
    }

    private boolean a(String string, String string2) {
        boolean bl;
        boolean bl2 = StringUtils.isNotEmpty((String)string);
        if (bl2 != (bl = StringUtils.isNotEmpty((String)string2))) {
            return false;
        }
        if (bl2) {
            return string.equals(string2);
        }
        return true;
    }

    public String getColumnName() {
        return this.c;
    }

    public int getColumnSize() {
        return this.d;
    }

    public String getColunmType() {
        return this.e;
    }

    public String getComment() {
        return this.f;
    }

    public int getDecimalDigits() {
        return this.h;
    }

    public String getIsNullable() {
        return this.i;
    }

    public String getOldColumnName() {
        return this.k;
    }

    public int hashCode() {
        return this.d + this.e.hashCode() * this.c.hashCode();
    }

    public void setColumnName(String columnName) {
        this.c = columnName;
    }

    public void setColumnSize(int columnSize) {
        this.d = columnSize;
    }

    public void setColunmType(String colunmType) {
        this.e = colunmType;
    }

    public void setComment(String comment) {
        this.f = comment;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.h = decimalDigits;
    }

    public void setIsNullable(String isNullable) {
        this.i = isNullable;
    }

    public void setOldColumnName(String oldColumnName) {
        this.k = oldColumnName;
    }

    public String toString() {
        return this.c + "," + this.e + "," + this.i + "," + this.d;
    }

    public String getColumnId() {
        return this.b;
    }

    public void setColumnId(String columnId) {
        this.b = columnId;
    }

    public String getTableName() {
        return this.a;
    }

    public void setTableName(String tableName) {
        this.a = tableName;
    }

    public String getFieldDefault() {
        return this.g;
    }

    public void setFieldDefault(String fieldDefault) {
        this.g = fieldDefault;
    }

    public String getPkType() {
        return this.j;
    }

    public void setPkType(String pkType) {
        this.j = pkType;
    }

    public String getRealDbType() {
        return this.l;
    }

    public void setRealDbType(String realDbType) {
        this.l = realDbType;
    }
}

