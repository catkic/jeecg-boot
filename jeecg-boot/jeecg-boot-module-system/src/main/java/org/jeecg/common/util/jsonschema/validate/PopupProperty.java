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
    private static final long l = -3200493311633999539L;
    private String m;
    private String n;
    private String o;
    private Boolean p;

    public String getCode() {
        return this.m;
    }

    public void setCode(String code) {
        this.m = code;
    }

    public String getDestFields() {
        return this.n;
    }

    public void setDestFields(String destFields) {
        this.n = destFields;
    }

    public String getOrgFields() {
        return this.o;
    }

    public void setOrgFields(String orgFields) {
        this.o = orgFields;
    }

    public Boolean getPopupMulti() {
        return this.p;
    }

    public void setPopupMulti(Boolean popupMulti) {
        this.p = popupMulti;
    }

    public PopupProperty() {
    }

    public PopupProperty(String key, String title, String code, String destFields, String orgFields) {
        this.e = "popup";
        this.b = "string";
        this.a = key;
        this.f = title;
        this.m = code;
        this.n = destFields;
        this.o = orgFields;
        this.p = true;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        if (this.m != null) {
            jSONObject.put("code", (Object)this.m);
        }
        if (this.n != null) {
            jSONObject.put("destFields", (Object)this.n);
        }
        if (this.o != null) {
            jSONObject.put("orgFields", (Object)this.o);
        }
        jSONObject.put("popupMulti", (Object)this.p);
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

