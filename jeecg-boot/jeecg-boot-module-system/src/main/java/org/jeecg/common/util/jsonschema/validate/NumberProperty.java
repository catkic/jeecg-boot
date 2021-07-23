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
    private Integer multipleOf;
    private Integer maximum;
    private Integer exclusiveMaximum;
    private Integer minimum;
    private Integer exclusiveMinimum;
    private String pattern;

    public Integer getMultipleOf() {
        return this.multipleOf;
    }

    public void setMultipleOf(Integer multipleOf) {
        this.multipleOf = multipleOf;
    }

    public Integer getMaximum() {
        return this.maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getExclusiveMaximum() {
        return this.exclusiveMaximum;
    }

    public void setExclusiveMaximum(Integer exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    public Integer getMinimum() {
        return this.minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getExclusiveMinimum() {
        return this.exclusiveMinimum;
    }

    public void setExclusiveMinimum(Integer exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public NumberProperty() {
    }

    public NumberProperty(String key, String title, String type) {
        this.key = key;
        this.type = type;
        this.title = title;
        this.view = "number";
    }

    public NumberProperty(String key, String title, String view, List<DictModel> include) {
        this.type = "integer";
        this.key = key;
        this.view = view;
        this.title = title;
        this.include = include;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        if (this.multipleOf != null) {
            jSONObject.put("multipleOf", (Object) this.multipleOf);
        }
        if (this.maximum != null) {
            jSONObject.put("maxinum", (Object) this.maximum);
        }
        if (this.exclusiveMaximum != null) {
            jSONObject.put("exclusiveMaximum", (Object) this.exclusiveMaximum);
        }
        if (this.minimum != null) {
            jSONObject.put("minimum", (Object) this.minimum);
        }
        if (this.exclusiveMinimum != null) {
            jSONObject.put("exclusiveMinimum", (Object) this.exclusiveMinimum);
        }
        if (this.pattern != null) {
            jSONObject.put("pattern", (Object) this.pattern);
        }
        hashMap.put("prop", (Object) jSONObject);
        return hashMap;
    }
}

