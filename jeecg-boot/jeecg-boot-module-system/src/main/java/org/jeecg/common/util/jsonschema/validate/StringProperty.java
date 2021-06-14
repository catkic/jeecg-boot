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
    private static final long l = -3200493311633999539L;
    private Integer m;
    private Integer n;
    private String o;
    private String p;

    public Integer getMaxLength() {
        return this.m;
    }

    public void setMaxLength(Integer maxLength) {
        this.m = maxLength;
    }

    public Integer getMinLength() {
        return this.n;
    }

    public void setMinLength(Integer minLength) {
        this.n = minLength;
    }

    public String getPattern() {
        return this.o;
    }

    public void setPattern(String pattern) {
        this.o = pattern;
    }

    public String getErrorInfo() {
        return this.p;
    }

    public void setErrorInfo(String errorInfo) {
        this.p = errorInfo;
    }

    public StringProperty() {
    }

    public StringProperty(String key, String title, String view, Integer maxLength) {
        this.m = maxLength;
        this.a = key;
        this.e = view;
        this.f = title;
        this.b = "string";
    }

    public StringProperty(String key, String title, String view, Integer maxLength, List<DictModel> include) {
        this.m = maxLength;
        this.a = key;
        this.e = view;
        this.f = title;
        this.b = "string";
        this.c = include;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        if (this.m != null) {
            jSONObject.put("maxLength", (Object)this.m);
        }
        if (this.n != null) {
            jSONObject.put("minLength", (Object)this.n);
        }
        if (this.o != null) {
            jSONObject.put("pattern", (Object)this.o);
        }
        if (this.p != null) {
            jSONObject.put("errorInfo", (Object)this.p);
        }
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

