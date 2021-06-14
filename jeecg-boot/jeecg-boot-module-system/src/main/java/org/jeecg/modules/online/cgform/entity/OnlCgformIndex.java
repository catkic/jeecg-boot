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
import org.springframework.format.annotation.DateTimeFormat;

@TableName(value="onl_cgform_index")
public class OnlCgformIndex
implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type=IdType.UUID)
    private String id;
    private String cgformHeadId;
    private String indexName;
    private String indexField;
    private String isDbSynch;
    private Integer delFlag;
    private String indexType;
    private String createBy;
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String updateBy;
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public String getId() {
        return this.id;
    }

    public String getCgformHeadId() {
        return this.cgformHeadId;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public String getIndexField() {
        return this.indexField;
    }

    public String getIsDbSynch() {
        return this.isDbSynch;
    }

    public Integer getDelFlag() {
        return this.delFlag;
    }

    public String getIndexType() {
        return this.indexType;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCgformHeadId(String cgformHeadId) {
        this.cgformHeadId = cgformHeadId;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public void setIndexField(String indexField) {
        this.indexField = indexField;
    }

    public void setIsDbSynch(String isDbSynch) {
        this.isDbSynch = isDbSynch;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OnlCgformIndex)) {
            return false;
        }
        OnlCgformIndex onlCgformIndex = (OnlCgformIndex)o;
        if (!onlCgformIndex.canEqual(this)) {
            return false;
        }
        Integer n2 = this.getDelFlag();
        Integer n3 = onlCgformIndex.getDelFlag();
        if (n2 == null ? n3 != null : !((Object)n2).equals(n3)) {
            return false;
        }
        String string = this.getId();
        String string2 = onlCgformIndex.getId();
        if (string == null ? string2 != null : !string.equals(string2)) {
            return false;
        }
        String string3 = this.getCgformHeadId();
        String string4 = onlCgformIndex.getCgformHeadId();
        if (string3 == null ? string4 != null : !string3.equals(string4)) {
            return false;
        }
        String string5 = this.getIndexName();
        String string6 = onlCgformIndex.getIndexName();
        if (string5 == null ? string6 != null : !string5.equals(string6)) {
            return false;
        }
        String string7 = this.getIndexField();
        String string8 = onlCgformIndex.getIndexField();
        if (string7 == null ? string8 != null : !string7.equals(string8)) {
            return false;
        }
        String string9 = this.getIsDbSynch();
        String string10 = onlCgformIndex.getIsDbSynch();
        if (string9 == null ? string10 != null : !string9.equals(string10)) {
            return false;
        }
        String string11 = this.getIndexType();
        String string12 = onlCgformIndex.getIndexType();
        if (string11 == null ? string12 != null : !string11.equals(string12)) {
            return false;
        }
        String string13 = this.getCreateBy();
        String string14 = onlCgformIndex.getCreateBy();
        if (string13 == null ? string14 != null : !string13.equals(string14)) {
            return false;
        }
        Date date = this.getCreateTime();
        Date date2 = onlCgformIndex.getCreateTime();
        if (date == null ? date2 != null : !((Object)date).equals(date2)) {
            return false;
        }
        String string15 = this.getUpdateBy();
        String string16 = onlCgformIndex.getUpdateBy();
        if (string15 == null ? string16 != null : !string15.equals(string16)) {
            return false;
        }
        Date date3 = this.getUpdateTime();
        Date date4 = onlCgformIndex.getUpdateTime();
        return !(date3 == null ? date4 != null : !((Object)date3).equals(date4));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OnlCgformIndex;
    }

    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        Integer n4 = this.getDelFlag();
        n3 = n3 * 59 + (n4 == null ? 43 : ((Object)n4).hashCode());
        String string = this.getId();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        String string2 = this.getCgformHeadId();
        n3 = n3 * 59 + (string2 == null ? 43 : string2.hashCode());
        String string3 = this.getIndexName();
        n3 = n3 * 59 + (string3 == null ? 43 : string3.hashCode());
        String string4 = this.getIndexField();
        n3 = n3 * 59 + (string4 == null ? 43 : string4.hashCode());
        String string5 = this.getIsDbSynch();
        n3 = n3 * 59 + (string5 == null ? 43 : string5.hashCode());
        String string6 = this.getIndexType();
        n3 = n3 * 59 + (string6 == null ? 43 : string6.hashCode());
        String string7 = this.getCreateBy();
        n3 = n3 * 59 + (string7 == null ? 43 : string7.hashCode());
        Date date = this.getCreateTime();
        n3 = n3 * 59 + (date == null ? 43 : ((Object)date).hashCode());
        String string8 = this.getUpdateBy();
        n3 = n3 * 59 + (string8 == null ? 43 : string8.hashCode());
        Date date2 = this.getUpdateTime();
        n3 = n3 * 59 + (date2 == null ? 43 : ((Object)date2).hashCode());
        return n3;
    }

    public String toString() {
        return "OnlCgformIndex(id=" + this.getId() + ", cgformHeadId=" + this.getCgformHeadId() + ", indexName=" + this.getIndexName() + ", indexField=" + this.getIndexField() + ", isDbSynch=" + this.getIsDbSynch() + ", delFlag=" + this.getDelFlag() + ", indexType=" + this.getIndexType() + ", createBy=" + this.getCreateBy() + ", createTime=" + this.getCreateTime() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ")";
    }
}

