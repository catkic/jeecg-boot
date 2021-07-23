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
    private String dict;
    private String pidField;
    private String pidValue;
    private String hasChildField;
    private String textField;
    private Integer pidComponent = 0;

    public String getDict() {
        return this.dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public String getPidField() {
        return this.pidField;
    }

    public void setPidField(String pidField) {
        this.pidField = pidField;
    }

    public String getPidValue() {
        return this.pidValue;
    }

    public void setPidValue(String pidValue) {
        this.pidValue = pidValue;
    }

    public String getHasChildField() {
        return this.hasChildField;
    }

    public void setHasChildField(String hasChildField) {
        this.hasChildField = hasChildField;
    }

    public TreeSelectProperty() {
    }

    public String getTextField() {
        return this.textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public Integer getPidComponent() {
        return this.pidComponent;
    }

    public void setPidComponent(Integer pidComponent) {
        this.pidComponent = pidComponent;
    }

    public TreeSelectProperty(String key, String title, String dict, String pidField, String pidValue) {
        this.type = "string";
        this.view = "sel_tree";
        this.key = key;
        this.title = title;
        this.dict = dict;
        this.pidField = pidField;
        this.pidValue = pidValue;

    }

    public TreeSelectProperty(String key, String title, String pidValue) {
        this.type = "string";
        this.view = "cat_tree";
        this.key = key;
        this.title = title;
        this.pidValue = pidValue;
    }

    public TreeSelectProperty(String key, String title, String pidValue, String textField) {
        this(key, title, pidValue);
        this.textField = textField;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        if (this.dict != null) {
            jSONObject.put("dict", (Object)this.dict);
        }
        if (this.pidField != null) {
            jSONObject.put("pidField", (Object)this.pidField);
        }
        if (this.pidValue != null) {
            jSONObject.put("pidValue", (Object)this.pidValue);
        }
        if (this.textField != null) {
            jSONObject.put("textField", (Object)this.textField);
        }
        if (this.hasChildField != null) {
            jSONObject.put("hasChildField", (Object)this.hasChildField);
        }
        if (this.pidComponent != null) {
            jSONObject.put("pidComponent", (Object)this.pidComponent);
        }
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

