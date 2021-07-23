/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 *  org.jeecg.common.system.vo.DictModel
 */
package org.jeecg.common.util.jsonschema.validate;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.jsonschema.CommonProperty;

public class StringProperty
extends CommonProperty {
    private Integer maxLength;
    private Integer minLength;
    private String pattern;
    private String errorInfo;

    public Integer getMaxLength() {
        return this.maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getMinLength() {
        return this.minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getErrorInfo() {
        return this.errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public StringProperty() {
    }

    public StringProperty(String key, String title, String view, Integer maxLength) {
        this.maxLength = maxLength;
        this.key = key;
        this.view = view;
        this.title = title;
        this.type = "string";
    }

    public StringProperty(String key, String title, String view, Integer maxLength, List<DictModel> include) {
        this.maxLength = maxLength;
        this.key = key;
        this.view = view;
        this.title = title;
        this.type = "string";
        this.include = include;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        if (this.maxLength != null) {
            jSONObject.put("maxLength", (Object)this.maxLength);
        }
        if (this.minLength != null) {
            jSONObject.put("minLength", (Object)this.minLength);
        }
        if (this.pattern != null) {
            jSONObject.put("pattern", (Object)this.pattern);
        }
        if (this.errorInfo != null) {
            jSONObject.put("errorInfo", (Object)this.errorInfo);
        }
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

