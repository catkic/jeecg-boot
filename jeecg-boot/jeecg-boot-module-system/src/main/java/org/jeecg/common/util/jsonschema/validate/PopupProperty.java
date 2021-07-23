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

public class PopupProperty
        extends CommonProperty {
    private String code;
    private String destFields;
    private String orgFields;
    private Boolean popupMulti;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDestFields() {
        return this.destFields;
    }

    public void setDestFields(String destFields) {
        this.destFields = destFields;
    }

    public String getOrgFields() {
        return this.orgFields;
    }

    public void setOrgFields(String orgFields) {
        this.orgFields = orgFields;
    }

    public Boolean getPopupMulti() {
        return this.popupMulti;
    }

    public void setPopupMulti(Boolean popupMulti) {
        this.popupMulti = popupMulti;
    }

    public PopupProperty() {
    }

    public PopupProperty(String key, String title, String code, String destFields, String orgFields) {
        this.view = "popup";
        this.type = "string";
        this.key = key;
        this.title = title;
        this.code = code;
        this.destFields = destFields;
        this.orgFields = orgFields;
        this.popupMulti = true;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        if (this.code != null) {
            jSONObject.put("code", (Object) this.code);
        }
        if (this.destFields != null) {
            jSONObject.put("destFields", (Object) this.destFields);
        }
        if (this.orgFields != null) {
            jSONObject.put("orgFields", (Object) this.orgFields);
        }
        jSONObject.put("popupMulti", (Object) this.popupMulti);
        hashMap.put("prop", (Object) jSONObject);
        return hashMap;
    }
}

