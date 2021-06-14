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

public class NumberProperty extends CommonProperty {
    private static final long l = -558615331436437200L;
    private Integer m;
    private Integer n;
    private Integer o;
    private Integer p;
    private Integer q;
    private String r;

    public Integer getMultipleOf() {
        return this.m;
    }

    public void setMultipleOf(Integer multipleOf) {
        this.m = multipleOf;
    }

    public Integer getMaxinum() {
        return this.n;
    }

    public void setMaxinum(Integer maxinum) {
        this.n = maxinum;
    }

    public Integer getExclusiveMaximum() {
        return this.o;
    }

    public void setExclusiveMaximum(Integer exclusiveMaximum) {
        this.o = exclusiveMaximum;
    }

    public Integer getMinimum() {
        return this.p;
    }

    public void setMinimum(Integer minimum) {
        this.p = minimum;
    }

    public Integer getExclusiveMinimum() {
        return this.q;
    }

    public void setExclusiveMinimum(Integer exclusiveMinimum) {
        this.q = exclusiveMinimum;
    }

    public String getPattern() {
        return this.r;
    }

    public void setPattern(String pattern) {
        this.r = pattern;
    }

    public NumberProperty() {
    }

    public NumberProperty(String key, String title, String type) {
        this.a = key;
        this.b = type;
        this.f = title;
        this.e = "number";
    }

    public NumberProperty(String key, String title, String view, List<DictModel> include) {
        this.b = "integer";
        this.a = key;
        this.e = view;
        this.f = title;
        this.c = include;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        if (this.m != null) {
            jSONObject.put("multipleOf", (Object) this.m);
        }
        if (this.n != null) {
            jSONObject.put("maxinum", (Object) this.n);
        }
        if (this.o != null) {
            jSONObject.put("exclusiveMaximum", (Object) this.o);
        }
        if (this.p != null) {
            jSONObject.put("minimum", (Object) this.p);
        }
        if (this.q != null) {
            jSONObject.put("exclusiveMinimum", (Object) this.q);
        }
        if (this.r != null) {
            jSONObject.put("pattern", (Object) this.r);
        }
        hashMap.put("prop", (Object) jSONObject);
        return hashMap;
    }
}

