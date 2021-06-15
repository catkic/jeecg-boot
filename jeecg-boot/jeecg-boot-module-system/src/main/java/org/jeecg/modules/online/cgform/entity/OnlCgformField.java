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
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@TableName(value="onl_cgform_field")
@Data
@EqualsAndHashCode
@ToString
public class OnlCgformField implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(type=IdType.UUID)
    private String id;
    private String cgformHeadId;
    private String dbFieldName;
    private String dbFieldTxt;
    /**
     * 这个可能是原来的名字
     * catkic
     * 就原来表单是可以编辑的，但是编辑了之后就是新的名字
     * 这个用来存老的名字
     */
    private String dbFieldNameOld;
    private Integer dbIsKey;
    private Integer dbIsNull;
    private String dbType;
    private Integer dbLength;
    private Integer dbPointLength;
    private String dbDefaultVal;
    private String dictField;
    private String dictTable;
    private String dictText;
    private String fieldShowType;
    private String fieldHref;
    private Integer fieldLength;
    private String fieldValidType;
    private String fieldMustInput;
    private String fieldExtendJson;
    private String fieldDefaultValue;
    private Integer isQuery;
    private Integer isShowForm;
    private Integer isShowList;
    private Integer isReadOnly;
    private String queryMode;
    private String mainTable;
    private String mainField;
    private Integer orderNum;
    private String updateBy;
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @JsonFormat(timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String createBy;
    private String converter;
    private String queryConfigFlag;
    private String queryDefVal;
    private String queryDictText;
    private String queryDictField;
    private String queryDictTable;
    private String queryShowType;
    private String queryValidType;
    private String queryMustInput;
    private String sortFlag;
}

