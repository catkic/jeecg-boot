/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.auth.vo;

import java.io.Serializable;

public class AuthPageVO
implements Serializable {
    private static final long serialVersionUID = 724713901683956568L;
    private String id;
    private String code;
    private String title;
    private Integer page;
    private Integer control;
    private String relId;
    private Boolean checked;

    public Boolean isChecked() {
        return this.relId != null && this.relId.length() > 0;
    }

    public String getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getControl() {
        return this.control;
    }

    public String getRelId() {
        return this.relId;
    }

    public Boolean getChecked() {
        return this.checked;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setControl(Integer control) {
        this.control = control;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AuthPageVO)) {
            return false;
        }
        AuthPageVO authPageVO = (AuthPageVO)o;
        if (!authPageVO.canEqual(this)) {
            return false;
        }
        Integer n2 = this.getPage();
        Integer n3 = authPageVO.getPage();
        if (n2 == null ? n3 != null : !((Object)n2).equals(n3)) {
            return false;
        }
        Integer n4 = this.getControl();
        Integer n5 = authPageVO.getControl();
        if (n4 == null ? n5 != null : !((Object)n4).equals(n5)) {
            return false;
        }
        Boolean bl = this.getChecked();
        Boolean bl2 = authPageVO.getChecked();
        if (bl == null ? bl2 != null : !((Object)bl).equals(bl2)) {
            return false;
        }
        String string = this.getId();
        String string2 = authPageVO.getId();
        if (string == null ? string2 != null : !string.equals(string2)) {
            return false;
        }
        String string3 = this.getCode();
        String string4 = authPageVO.getCode();
        if (string3 == null ? string4 != null : !string3.equals(string4)) {
            return false;
        }
        String string5 = this.getTitle();
        String string6 = authPageVO.getTitle();
        if (string5 == null ? string6 != null : !string5.equals(string6)) {
            return false;
        }
        String string7 = this.getRelId();
        String string8 = authPageVO.getRelId();
        return !(string7 == null ? string8 != null : !string7.equals(string8));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AuthPageVO;
    }

    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        Integer n4 = this.getPage();
        n3 = n3 * 59 + (n4 == null ? 43 : ((Object)n4).hashCode());
        Integer n5 = this.getControl();
        n3 = n3 * 59 + (n5 == null ? 43 : ((Object)n5).hashCode());
        Boolean bl = this.getChecked();
        n3 = n3 * 59 + (bl == null ? 43 : ((Object)bl).hashCode());
        String string = this.getId();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        String string2 = this.getCode();
        n3 = n3 * 59 + (string2 == null ? 43 : string2.hashCode());
        String string3 = this.getTitle();
        n3 = n3 * 59 + (string3 == null ? 43 : string3.hashCode());
        String string4 = this.getRelId();
        n3 = n3 * 59 + (string4 == null ? 43 : string4.hashCode());
        return n3;
    }

    public String toString() {
        return "AuthPageVO(id=" + this.getId() + ", code=" + this.getCode() + ", title=" + this.getTitle() + ", page=" + this.getPage() + ", control=" + this.getControl() + ", relId=" + this.getRelId() + ", checked=" + this.getChecked() + ")";
    }
}

