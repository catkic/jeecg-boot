/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.common.util.jsonschema;

public class a {
    private String a;
    private String b;

    public a() {
    }

    public a(String string, String string2) {
        this.a = string;
        this.b = string2;
    }

    public String getTitle() {
        return this.a;
    }

    public String getField() {
        return this.b;
    }

    public void setTitle(String title) {
        this.a = title;
    }

    public void setField(String field) {
        this.b = field;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof a)) {
            return false;
        }
        a a2 = (a)o;
        if (!a2.a(this)) {
            return false;
        }
        String string = this.getTitle();
        String string2 = a2.getTitle();
        if (string == null ? string2 != null : !string.equals(string2)) {
            return false;
        }
        String string3 = this.getField();
        String string4 = a2.getField();
        return !(string3 == null ? string4 != null : !string3.equals(string4));
    }

    protected boolean a(Object object) {
        return object instanceof a;
    }

    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        String string = this.getTitle();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        String string2 = this.getField();
        n3 = n3 * 59 + (string2 == null ? 43 : string2.hashCode());
        return n3;
    }

    public String toString() {
        return "BaseColumn(title=" + this.getTitle() + ", field=" + this.getField() + ")";
    }
}

