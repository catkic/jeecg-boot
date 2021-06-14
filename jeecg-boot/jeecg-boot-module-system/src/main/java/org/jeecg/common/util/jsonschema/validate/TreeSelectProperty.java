/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 */
package org.jeecg.common.util.jsonschema.validate;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.jeecg.common.util.jsonschema.CommonProperty;

public class TreeSelectProperty
extends CommonProperty {
    private static final long l = 3786503639885610767L;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private Integer r = 0;

    public String getDict() {
        return this.m;
    }

    public void setDict(String dict) {
        this.m = dict;
    }

    public String getPidField() {
        return this.n;
    }

    public void setPidField(String pidField) {
        this.n = pidField;
    }

    public String getPidValue() {
        return this.o;
    }

    public void setPidValue(String pidValue) {
        this.o = pidValue;
    }

    public String getHasChildField() {
        return this.p;
    }

    public void setHasChildField(String hasChildField) {
        this.p = hasChildField;
    }

    public TreeSelectProperty() {
    }

    public String getTextField() {
        return this.q;
    }

    public void setTextField(String textField) {
        this.q = textField;
    }

    public Integer getPidComponent() {
        return this.r;
    }

    public void setPidComponent(Integer pidComponent) {
        this.r = pidComponent;
    }

    public TreeSelectProperty(String key, String title, String dict, String pidField, String pidValue) {
        this.b = "string";
        this.e = "sel_tree";
        this.a = key;
        this.f = title;
        this.m = dict;
        this.n = pidField;
        this.o = pidValue;
    }

    public TreeSelectProperty(String key, String title, String pidValue) {
        this.b = "string";
        this.e = "cat_tree";
        this.a = key;
        this.f = title;
        this.o = pidValue;
    }

    public TreeSelectProperty(String key, String title, String pidValue, String textField) {
        this(key, title, pidValue);
        this.q = textField;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        if (this.m != null) {
            jSONObject.put("dict", (Object)this.m);
        }
        if (this.n != null) {
            jSONObject.put("pidField", (Object)this.n);
        }
        if (this.o != null) {
            jSONObject.put("pidValue", (Object)this.o);
        }
        if (this.q != null) {
            jSONObject.put("textField", (Object)this.q);
        }
        if (this.p != null) {
            jSONObject.put("hasChildField", (Object)this.p);
        }
        if (this.r != null) {
            jSONObject.put("pidComponent", (Object)this.r);
        }
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

