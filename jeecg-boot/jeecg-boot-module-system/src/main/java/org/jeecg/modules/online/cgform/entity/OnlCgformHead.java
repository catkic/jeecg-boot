/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotation.IdType
 *  com.baomidou.mybatisplus.annotation.TableId
 *  com.baomidou.mybatisplus.annotation.TableName
 *  com.fasterxml.jackson.annotation.JsonFormat
 *  org.springframework.format.annotation.DateTimeFormat
 */
package org.jeecg.modules.online.cgform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@TableName(value = "onl_cgform_head")
@Data
public class OnlCgformHead implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.UUID)
    private String id;
    private String tableName;
    private Integer tableType;
    private Integer tableVersion;
    private String tableTxt;
    private String isCheckbox;
    private String isDbSynch;
    private String isPage;
    private String isTree;
    private String idSequence;
    private String idType;
    private String queryMode;
    private Integer relationType;
    private String subTableStr;
    private Integer tabOrderNum;
    private String treeParentIdField;
    private String treeIdField;
    private String treeFieldname;
    private String formCategory;
    private String formTemplate;
    private String themeTemplate;
    private String formTemplateMobile;
    private String updateBy;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String createBy;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer copyType;
    private Integer copyVersion;
    private String physicId;
    private transient Integer hascopy;
    private Integer scroll;
    private transient String taskId;
    private String isDesForm;
    private String desFormCode;

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OnlCgformHead)) {
            return false;
        }
        OnlCgformHead onlCgformHead = (OnlCgformHead) o;
        if (!onlCgformHead.canEqual(this)) {
            return false;
        }
        Integer n2 = this.getTableType();
        Integer n3 = onlCgformHead.getTableType();
        if (n2 == null ? n3 != null : !((Object) n2).equals(n3)) {
            return false;
        }
        Integer n4 = this.getTableVersion();
        Integer n5 = onlCgformHead.getTableVersion();
        if (n4 == null ? n5 != null : !((Object) n4).equals(n5)) {
            return false;
        }
        Integer n6 = this.getRelationType();
        Integer n7 = onlCgformHead.getRelationType();
        if (n6 == null ? n7 != null : !((Object) n6).equals(n7)) {
            return false;
        }
        Integer n8 = this.getTabOrderNum();
        Integer n9 = onlCgformHead.getTabOrderNum();
        if (n8 == null ? n9 != null : !((Object) n8).equals(n9)) {
            return false;
        }
        Integer n10 = this.getCopyType();
        Integer n11 = onlCgformHead.getCopyType();
        if (n10 == null ? n11 != null : !((Object) n10).equals(n11)) {
            return false;
        }
        Integer n12 = this.getCopyVersion();
        Integer n13 = onlCgformHead.getCopyVersion();
        if (n12 == null ? n13 != null : !((Object) n12).equals(n13)) {
            return false;
        }
        Integer n14 = this.getScroll();
        Integer n15 = onlCgformHead.getScroll();
        if (n14 == null ? n15 != null : !((Object) n14).equals(n15)) {
            return false;
        }
        String string = this.getId();
        String string2 = onlCgformHead.getId();
        if (string == null ? string2 != null : !string.equals(string2)) {
            return false;
        }
        String string3 = this.getTableName();
        String string4 = onlCgformHead.getTableName();
        if (string3 == null ? string4 != null : !string3.equals(string4)) {
            return false;
        }
        String string5 = this.getTableTxt();
        String string6 = onlCgformHead.getTableTxt();
        if (string5 == null ? string6 != null : !string5.equals(string6)) {
            return false;
        }
        String string7 = this.getIsCheckbox();
        String string8 = onlCgformHead.getIsCheckbox();
        if (string7 == null ? string8 != null : !string7.equals(string8)) {
            return false;
        }
        String string9 = this.getIsDbSynch();
        String string10 = onlCgformHead.getIsDbSynch();
        if (string9 == null ? string10 != null : !string9.equals(string10)) {
            return false;
        }
        String string11 = this.getIsPage();
        String string12 = onlCgformHead.getIsPage();
        if (string11 == null ? string12 != null : !string11.equals(string12)) {
            return false;
        }
        String string13 = this.getIsTree();
        String string14 = onlCgformHead.getIsTree();
        if (string13 == null ? string14 != null : !string13.equals(string14)) {
            return false;
        }
        String string15 = this.getIdSequence();
        String string16 = onlCgformHead.getIdSequence();
        if (string15 == null ? string16 != null : !string15.equals(string16)) {
            return false;
        }
        String string17 = this.getIdType();
        String string18 = onlCgformHead.getIdType();
        if (string17 == null ? string18 != null : !string17.equals(string18)) {
            return false;
        }
        String string19 = this.getQueryMode();
        String string20 = onlCgformHead.getQueryMode();
        if (string19 == null ? string20 != null : !string19.equals(string20)) {
            return false;
        }
        String string21 = this.getSubTableStr();
        String string22 = onlCgformHead.getSubTableStr();
        if (string21 == null ? string22 != null : !string21.equals(string22)) {
            return false;
        }
        String string23 = this.getTreeParentIdField();
        String string24 = onlCgformHead.getTreeParentIdField();
        if (string23 == null ? string24 != null : !string23.equals(string24)) {
            return false;
        }
        String string25 = this.getTreeIdField();
        String string26 = onlCgformHead.getTreeIdField();
        if (string25 == null ? string26 != null : !string25.equals(string26)) {
            return false;
        }
        String string27 = this.getTreeFieldname();
        String string28 = onlCgformHead.getTreeFieldname();
        if (string27 == null ? string28 != null : !string27.equals(string28)) {
            return false;
        }
        String string29 = this.getFormCategory();
        String string30 = onlCgformHead.getFormCategory();
        if (string29 == null ? string30 != null : !string29.equals(string30)) {
            return false;
        }
        String string31 = this.getFormTemplate();
        String string32 = onlCgformHead.getFormTemplate();
        if (string31 == null ? string32 != null : !string31.equals(string32)) {
            return false;
        }
        String string33 = this.getThemeTemplate();
        String string34 = onlCgformHead.getThemeTemplate();
        if (string33 == null ? string34 != null : !string33.equals(string34)) {
            return false;
        }
        String string35 = this.getFormTemplateMobile();
        String string36 = onlCgformHead.getFormTemplateMobile();
        if (string35 == null ? string36 != null : !string35.equals(string36)) {
            return false;
        }
        String string37 = this.getUpdateBy();
        String string38 = onlCgformHead.getUpdateBy();
        if (string37 == null ? string38 != null : !string37.equals(string38)) {
            return false;
        }
        Date date = this.getUpdateTime();
        Date date2 = onlCgformHead.getUpdateTime();
        if (date == null ? date2 != null : !((Object) date).equals(date2)) {
            return false;
        }
        String string39 = this.getCreateBy();
        String string40 = onlCgformHead.getCreateBy();
        if (string39 == null ? string40 != null : !string39.equals(string40)) {
            return false;
        }
        Date date3 = this.getCreateTime();
        Date date4 = onlCgformHead.getCreateTime();
        if (date3 == null ? date4 != null : !((Object) date3).equals(date4)) {
            return false;
        }
        String string41 = this.getPhysicId();
        String string42 = onlCgformHead.getPhysicId();
        if (string41 == null ? string42 != null : !string41.equals(string42)) {
            return false;
        }
        String string43 = this.getIsDesForm();
        String string44 = onlCgformHead.getIsDesForm();
        if (string43 == null ? string44 != null : !string43.equals(string44)) {
            return false;
        }
        String string45 = this.getDesFormCode();
        String string46 = onlCgformHead.getDesFormCode();
        return !(string45 == null ? string46 != null : !string45.equals(string46));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OnlCgformHead;
    }

    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        Integer n4 = this.getTableType();
        n3 = n3 * 59 + (n4 == null ? 43 : ((Object) n4).hashCode());
        Integer n5 = this.getTableVersion();
        n3 = n3 * 59 + (n5 == null ? 43 : ((Object) n5).hashCode());
        Integer n6 = this.getRelationType();
        n3 = n3 * 59 + (n6 == null ? 43 : ((Object) n6).hashCode());
        Integer n7 = this.getTabOrderNum();
        n3 = n3 * 59 + (n7 == null ? 43 : ((Object) n7).hashCode());
        Integer n8 = this.getCopyType();
        n3 = n3 * 59 + (n8 == null ? 43 : ((Object) n8).hashCode());
        Integer n9 = this.getCopyVersion();
        n3 = n3 * 59 + (n9 == null ? 43 : ((Object) n9).hashCode());
        Integer n10 = this.getScroll();
        n3 = n3 * 59 + (n10 == null ? 43 : ((Object) n10).hashCode());
        String string = this.getId();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        String string2 = this.getTableName();
        n3 = n3 * 59 + (string2 == null ? 43 : string2.hashCode());
        String string3 = this.getTableTxt();
        n3 = n3 * 59 + (string3 == null ? 43 : string3.hashCode());
        String string4 = this.getIsCheckbox();
        n3 = n3 * 59 + (string4 == null ? 43 : string4.hashCode());
        String string5 = this.getIsDbSynch();
        n3 = n3 * 59 + (string5 == null ? 43 : string5.hashCode());
        String string6 = this.getIsPage();
        n3 = n3 * 59 + (string6 == null ? 43 : string6.hashCode());
        String string7 = this.getIsTree();
        n3 = n3 * 59 + (string7 == null ? 43 : string7.hashCode());
        String string8 = this.getIdSequence();
        n3 = n3 * 59 + (string8 == null ? 43 : string8.hashCode());
        String string9 = this.getIdType();
        n3 = n3 * 59 + (string9 == null ? 43 : string9.hashCode());
        String string10 = this.getQueryMode();
        n3 = n3 * 59 + (string10 == null ? 43 : string10.hashCode());
        String string11 = this.getSubTableStr();
        n3 = n3 * 59 + (string11 == null ? 43 : string11.hashCode());
        String string12 = this.getTreeParentIdField();
        n3 = n3 * 59 + (string12 == null ? 43 : string12.hashCode());
        String string13 = this.getTreeIdField();
        n3 = n3 * 59 + (string13 == null ? 43 : string13.hashCode());
        String string14 = this.getTreeFieldname();
        n3 = n3 * 59 + (string14 == null ? 43 : string14.hashCode());
        String string15 = this.getFormCategory();
        n3 = n3 * 59 + (string15 == null ? 43 : string15.hashCode());
        String string16 = this.getFormTemplate();
        n3 = n3 * 59 + (string16 == null ? 43 : string16.hashCode());
        String string17 = this.getThemeTemplate();
        n3 = n3 * 59 + (string17 == null ? 43 : string17.hashCode());
        String string18 = this.getFormTemplateMobile();
        n3 = n3 * 59 + (string18 == null ? 43 : string18.hashCode());
        String string19 = this.getUpdateBy();
        n3 = n3 * 59 + (string19 == null ? 43 : string19.hashCode());
        Date date = this.getUpdateTime();
        n3 = n3 * 59 + (date == null ? 43 : ((Object) date).hashCode());
        String string20 = this.getCreateBy();
        n3 = n3 * 59 + (string20 == null ? 43 : string20.hashCode());
        Date date2 = this.getCreateTime();
        n3 = n3 * 59 + (date2 == null ? 43 : ((Object) date2).hashCode());
        String string21 = this.getPhysicId();
        n3 = n3 * 59 + (string21 == null ? 43 : string21.hashCode());
        String string22 = this.getIsDesForm();
        n3 = n3 * 59 + (string22 == null ? 43 : string22.hashCode());
        String string23 = this.getDesFormCode();
        n3 = n3 * 59 + (string23 == null ? 43 : string23.hashCode());
        return n3;
    }

    public String toString() {
        return "OnlCgformHead(id=" + this.getId() + ", tableName=" + this.getTableName() + ", tableType=" + this.getTableType() + ", tableVersion=" + this.getTableVersion() + ", tableTxt=" + this.getTableTxt() + ", isCheckbox=" + this.getIsCheckbox() + ", isDbSynch=" + this.getIsDbSynch() + ", isPage=" + this.getIsPage() + ", isTree=" + this.getIsTree() + ", idSequence=" + this.getIdSequence() + ", idType=" + this.getIdType() + ", queryMode=" + this.getQueryMode() + ", relationType=" + this.getRelationType() + ", subTableStr=" + this.getSubTableStr() + ", tabOrderNum=" + this.getTabOrderNum() + ", treeParentIdField=" + this.getTreeParentIdField() + ", treeIdField=" + this.getTreeIdField() + ", treeFieldname=" + this.getTreeFieldname() + ", formCategory=" + this.getFormCategory() + ", formTemplate=" + this.getFormTemplate() + ", themeTemplate=" + this.getThemeTemplate() + ", formTemplateMobile=" + this.getFormTemplateMobile() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", copyType=" + this.getCopyType() + ", copyVersion=" + this.getCopyVersion() + ", physicId=" + this.getPhysicId() + ", hascopy=" + this.getHascopy() + ", scroll=" + this.getScroll() + ", taskId=" + this.getTaskId() + ", isDesForm=" + this.getIsDesForm() + ", desFormCode=" + this.getDesFormCode() + ")";
    }
}

