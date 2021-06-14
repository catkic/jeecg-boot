/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.model;

public class c {
    private String a;

    public c() {
    }

    public c(String string) {
        this.a = string;
    }

    public String getCustomRender() {
        return this.a;
    }

    public void setCustomRender(String customRender) {
        this.a = customRender;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof c)) {
            return false;
        }
        c c2 = (c)o;
        if (!c2.a(this)) {
            return false;
        }
        String string = this.getCustomRender();
        String string2 = c2.getCustomRender();
        return !(string == null ? string2 != null : !string.equals(string2));
    }

    protected boolean a(Object object) {
        return object instanceof c;
    }

    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        String string = this.getCustomRender();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        return n3;
    }

    public String toString() {
        return "ScopedSlots(customRender=" + this.getCustomRender() + ")";
    }
}

