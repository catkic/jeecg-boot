/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotation.IdType
 *  com.baomidou.mybatisplus.annotation.TableId
 *  com.baomidou.mybatisplus.annotation.TableName
 *  com.fasterxml.jackson.annotation.JsonFormat
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  io.swagger.annotations.ApiModel
 *  io.swagger.annotations.ApiModelProperty
 *  org.jeecgframework.poi.excel.annotation.Excel
 *  org.springframework.format.annotation.DateTimeFormat
 */
package org.jeecg.modules.online.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

@TableName(value = "onl_auth_page")
@ApiModel(value = "onl_auth_page对象", description = "onl_auth_page")
public class OnlAuthPage
        implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = " 主键")
    private String id;
    @Excel(name = "online表id", width = 15.0)
    @ApiModelProperty(value = "online表id")
    private String cgformId;
    @Excel(name = "字段名/按钮编码", width = 15.0)
    @ApiModelProperty(value = "字段名/按钮编码")
    private String code;
    @Excel(name = "1字段 2按钮", width = 15.0)
    @ApiModelProperty(value = "1字段 2按钮")
    private Integer type;
    @Excel(name = "3可编辑 5可见", width = 15.0)
    @ApiModelProperty(value = "3可编辑 5可见")
    private Integer control;
    @Excel(name = "3列表 5表单", width = 15.0)
    @ApiModelProperty(value = "3列表 5表单")
    private Integer page;
    @Excel(name = "1有效 0无效", width = 15.0)
    @ApiModelProperty(value = "1有效 0无效")
    private Integer status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    @JsonIgnore
    private Date createTime;
    @ApiModelProperty(value = "创建人")
    @JsonIgnore
    private String createBy;
    @ApiModelProperty(value = "更新人")
    @JsonIgnore
    private String updateBy;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    @JsonIgnore
    private Date updateTime;

    public OnlAuthPage() {
    }

    public OnlAuthPage(String cgformId, String code, int page, int control) {
        this.type = 1;
        this.cgformId = cgformId;
        this.code = code;
        this.control = control;
        this.page = page;
        this.status = 1;
    }

    public String getId() {
        return this.id;
    }

    public String getCgformId() {
        return this.cgformId;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getType() {
        return this.type;
    }

    public Integer getControl() {
        return this.control;
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public OnlAuthPage setId(String id) {
        this.id = id;
        return this;
    }

    public OnlAuthPage setCgformId(String cgformId) {
        this.cgformId = cgformId;
        return this;
    }

    public OnlAuthPage setCode(String code) {
        this.code = code;
        return this;
    }

    public OnlAuthPage setType(Integer type) {
        this.type = type;
        return this;
    }

    public OnlAuthPage setControl(Integer control) {
        this.control = control;
        return this;
    }

    public OnlAuthPage setPage(Integer page) {
        this.page = page;
        return this;
    }

    public OnlAuthPage setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @JsonIgnore
    public OnlAuthPage setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @JsonIgnore
    public OnlAuthPage setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    @JsonIgnore
    public OnlAuthPage setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @JsonIgnore
    public OnlAuthPage setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String toString() {
        return "OnlAuthPage(id=" + this.getId() + ", cgformId=" + this.getCgformId() + ", code=" + this.getCode() + ", type=" + this.getType() + ", control=" + this.getControl() + ", page=" + this.getPage() + ", status=" + this.getStatus() + ", createTime=" + this.getCreateTime() + ", createBy=" + this.getCreateBy() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OnlAuthPage)) {
            return false;
        }
        OnlAuthPage onlAuthPage = (OnlAuthPage) o;
        if (!onlAuthPage.canEqual(this)) {
            return false;
        }
        Integer n2 = this.getType();
        Integer n3 = onlAuthPage.getType();
        if (n2 == null ? n3 != null : !((Object) n2).equals(n3)) {
            return false;
        }
        Integer n4 = this.getControl();
        Integer n5 = onlAuthPage.getControl();
        if (n4 == null ? n5 != null : !((Object) n4).equals(n5)) {
            return false;
        }
        Integer n6 = this.getPage();
        Integer n7 = onlAuthPage.getPage();
        if (n6 == null ? n7 != null : !((Object) n6).equals(n7)) {
            return false;
        }
        Integer n8 = this.getStatus();
        Integer n9 = onlAuthPage.getStatus();
        if (n8 == null ? n9 != null : !((Object) n8).equals(n9)) {
            return false;
        }
        String string = this.getId();
        String string2 = onlAuthPage.getId();
        if (string == null ? string2 != null : !string.equals(string2)) {
            return false;
        }
        String string3 = this.getCgformId();
        String string4 = onlAuthPage.getCgformId();
        if (string3 == null ? string4 != null : !string3.equals(string4)) {
            return false;
        }
        String string5 = this.getCode();
        String string6 = onlAuthPage.getCode();
        if (string5 == null ? string6 != null : !string5.equals(string6)) {
            return false;
        }
        Date date = this.getCreateTime();
        Date date2 = onlAuthPage.getCreateTime();
        if (date == null ? date2 != null : !((Object) date).equals(date2)) {
            return false;
        }
        String string7 = this.getCreateBy();
        String string8 = onlAuthPage.getCreateBy();
        if (string7 == null ? string8 != null : !string7.equals(string8)) {
            return false;
        }
        String string9 = this.getUpdateBy();
        String string10 = onlAuthPage.getUpdateBy();
        if (string9 == null ? string10 != null : !string9.equals(string10)) {
            return false;
        }
        Date date3 = this.getUpdateTime();
        Date date4 = onlAuthPage.getUpdateTime();
        return !(date3 == null ? date4 != null : !((Object) date3).equals(date4));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OnlAuthPage;
    }

    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        Integer n4 = this.getType();
        n3 = n3 * 59 + (n4 == null ? 43 : ((Object) n4).hashCode());
        Integer n5 = this.getControl();
        n3 = n3 * 59 + (n5 == null ? 43 : ((Object) n5).hashCode());
        Integer n6 = this.getPage();
        n3 = n3 * 59 + (n6 == null ? 43 : ((Object) n6).hashCode());
        Integer n7 = this.getStatus();
        n3 = n3 * 59 + (n7 == null ? 43 : ((Object) n7).hashCode());
        String string = this.getId();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        String string2 = this.getCgformId();
        n3 = n3 * 59 + (string2 == null ? 43 : string2.hashCode());
        String string3 = this.getCode();
        n3 = n3 * 59 + (string3 == null ? 43 : string3.hashCode());
        Date date = this.getCreateTime();
        n3 = n3 * 59 + (date == null ? 43 : ((Object) date).hashCode());
        String string4 = this.getCreateBy();
        n3 = n3 * 59 + (string4 == null ? 43 : string4.hashCode());
        String string5 = this.getUpdateBy();
        n3 = n3 * 59 + (string5 == null ? 43 : string5.hashCode());
        Date date2 = this.getUpdateTime();
        n3 = n3 * 59 + (date2 == null ? 43 : ((Object) date2).hashCode());
        return n3;
    }
}

