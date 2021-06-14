/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.annotation.IdType
 *  com.baomidou.mybatisplus.annotation.TableId
 *  com.baomidou.mybatisplus.annotation.TableName
 *  com.fasterxml.jackson.annotation.JsonFormat
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

@TableName(value="onl_auth_data")
@ApiModel(value="onl_auth_data对象", description="onl_auth_data")
public class OnlAuthData
implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type=IdType.ASSIGN_ID)
    @ApiModelProperty(value="主键")
    private String id;
    @Excel(name="online表ID", width=15.0)
    @ApiModelProperty(value="online表ID")
    private String cgformId;
    @Excel(name="规则名", width=15.0)
    @ApiModelProperty(value="规则名")
    private String ruleName;
    @Excel(name="规则列", width=15.0)
    @ApiModelProperty(value="规则列")
    private String ruleColumn;
    @Excel(name="规则条件 大于小于like", width=15.0)
    @ApiModelProperty(value="规则条件 大于小于like")
    private String ruleOperator;
    @Excel(name="规则值", width=15.0)
    @ApiModelProperty(value="规则值")
    private String ruleValue;
    @Excel(name="1有效 0无效", width=15.0)
    @ApiModelProperty(value="1有效 0无效")
    private Integer status;
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    @ApiModelProperty(value="创建人")
    private String createBy;
    @ApiModelProperty(value="更新人")
    private String updateBy;
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value="更新日期")
    private Date updateTime;

    public String getId() {
        return this.id;
    }

    public String getCgformId() {
        return this.cgformId;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public String getRuleColumn() {
        return this.ruleColumn;
    }

    public String getRuleOperator() {
        return this.ruleOperator;
    }

    public String getRuleValue() {
        return this.ruleValue;
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

    public OnlAuthData setId(String id) {
        this.id = id;
        return this;
    }

    public OnlAuthData setCgformId(String cgformId) {
        this.cgformId = cgformId;
        return this;
    }

    public OnlAuthData setRuleName(String ruleName) {
        this.ruleName = ruleName;
        return this;
    }

    public OnlAuthData setRuleColumn(String ruleColumn) {
        this.ruleColumn = ruleColumn;
        return this;
    }

    public OnlAuthData setRuleOperator(String ruleOperator) {
        this.ruleOperator = ruleOperator;
        return this;
    }

    public OnlAuthData setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue;
        return this;
    }

    public OnlAuthData setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    public OnlAuthData setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public OnlAuthData setCreateBy(String createBy) {
        this.createBy = createBy;
        return this;
    }

    public OnlAuthData setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd")
    public OnlAuthData setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String toString() {
        return "OnlAuthData(id=" + this.getId() + ", cgformId=" + this.getCgformId() + ", ruleName=" + this.getRuleName() + ", ruleColumn=" + this.getRuleColumn() + ", ruleOperator=" + this.getRuleOperator() + ", ruleValue=" + this.getRuleValue() + ", status=" + this.getStatus() + ", createTime=" + this.getCreateTime() + ", createBy=" + this.getCreateBy() + ", updateBy=" + this.getUpdateBy() + ", updateTime=" + this.getUpdateTime() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OnlAuthData)) {
            return false;
        }
        OnlAuthData onlAuthData = (OnlAuthData)o;
        if (!onlAuthData.canEqual(this)) {
            return false;
        }
        Integer n2 = this.getStatus();
        Integer n3 = onlAuthData.getStatus();
        if (n2 == null ? n3 != null : !((Object)n2).equals(n3)) {
            return false;
        }
        String string = this.getId();
        String string2 = onlAuthData.getId();
        if (string == null ? string2 != null : !string.equals(string2)) {
            return false;
        }
        String string3 = this.getCgformId();
        String string4 = onlAuthData.getCgformId();
        if (string3 == null ? string4 != null : !string3.equals(string4)) {
            return false;
        }
        String string5 = this.getRuleName();
        String string6 = onlAuthData.getRuleName();
        if (string5 == null ? string6 != null : !string5.equals(string6)) {
            return false;
        }
        String string7 = this.getRuleColumn();
        String string8 = onlAuthData.getRuleColumn();
        if (string7 == null ? string8 != null : !string7.equals(string8)) {
            return false;
        }
        String string9 = this.getRuleOperator();
        String string10 = onlAuthData.getRuleOperator();
        if (string9 == null ? string10 != null : !string9.equals(string10)) {
            return false;
        }
        String string11 = this.getRuleValue();
        String string12 = onlAuthData.getRuleValue();
        if (string11 == null ? string12 != null : !string11.equals(string12)) {
            return false;
        }
        Date date = this.getCreateTime();
        Date date2 = onlAuthData.getCreateTime();
        if (date == null ? date2 != null : !((Object)date).equals(date2)) {
            return false;
        }
        String string13 = this.getCreateBy();
        String string14 = onlAuthData.getCreateBy();
        if (string13 == null ? string14 != null : !string13.equals(string14)) {
            return false;
        }
        String string15 = this.getUpdateBy();
        String string16 = onlAuthData.getUpdateBy();
        if (string15 == null ? string16 != null : !string15.equals(string16)) {
            return false;
        }
        Date date3 = this.getUpdateTime();
        Date date4 = onlAuthData.getUpdateTime();
        return !(date3 == null ? date4 != null : !((Object)date3).equals(date4));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OnlAuthData;
    }

    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        Integer n4 = this.getStatus();
        n3 = n3 * 59 + (n4 == null ? 43 : ((Object)n4).hashCode());
        String string = this.getId();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        String string2 = this.getCgformId();
        n3 = n3 * 59 + (string2 == null ? 43 : string2.hashCode());
        String string3 = this.getRuleName();
        n3 = n3 * 59 + (string3 == null ? 43 : string3.hashCode());
        String string4 = this.getRuleColumn();
        n3 = n3 * 59 + (string4 == null ? 43 : string4.hashCode());
        String string5 = this.getRuleOperator();
        n3 = n3 * 59 + (string5 == null ? 43 : string5.hashCode());
        String string6 = this.getRuleValue();
        n3 = n3 * 59 + (string6 == null ? 43 : string6.hashCode());
        Date date = this.getCreateTime();
        n3 = n3 * 59 + (date == null ? 43 : ((Object)date).hashCode());
        String string7 = this.getCreateBy();
        n3 = n3 * 59 + (string7 == null ? 43 : string7.hashCode());
        String string8 = this.getUpdateBy();
        n3 = n3 * 59 + (string8 == null ? 43 : string8.hashCode());
        Date date2 = this.getUpdateTime();
        n3 = n3 * 59 + (date2 == null ? 43 : ((Object)date2).hashCode());
        return n3;
    }
}

