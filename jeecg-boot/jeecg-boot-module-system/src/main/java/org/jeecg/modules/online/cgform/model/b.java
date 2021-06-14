/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.model;

public class b {
    private String a;
    private String b;
    private String c;

    public b() {
    }

    public b(String string, String string2) {
        this.c = string2;
        this.a = string;
    }

    public String getField() {
        return this.a;
    }

    public String getTable() {
        return this.b;
    }

    public String getKey() {
        return this.c;
    }

    public void setField(String field) {
        this.a = field;
    }

    public void setTable(String table) {
        this.b = table;
    }

    public void setKey(String key) {
        this.c = key;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof b)) {
            return false;
        }
        b b2 = (b)o;
        if (!b2.a(this)) {
            return false;
        }
        String string = this.getField();
        String string2 = b2.getField();
        if (string == null ? string2 != null : !string.equals(string2)) {
            return false;
        }
        String string3 = this.getTable();
        String string4 = b2.getTable();
        if (string3 == null ? string4 != null : !string3.equals(string4)) {
            return false;
        }
        String string5 = this.getKey();
        String string6 = b2.getKey();
        return !(string5 == null ? string6 != null : !string5.equals(string6));
    }

    protected boolean a(Object object) {
        return object instanceof b;
    }

    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        String string = this.getField();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        String string2 = this.getTable();
        n3 = n3 * 59 + (string2 == null ? 43 : string2.hashCode());
        String string3 = this.getKey();
        n3 = n3 * 59 + (string3 == null ? 43 : string3.hashCode());
        return n3;
    }

    public String toString() {
        return "OnlForeignKey(field=" + this.getField() + ", table=" + this.getTable() + ", key=" + this.getKey() + ")";
    }
}

