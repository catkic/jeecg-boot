/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONObject
 */
package org.jeecg.common.util.jsonschema.validate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.jeecg.common.util.jsonschema.CommonProperty;

public class SwitchProperty
extends CommonProperty {
    private String l;

    public SwitchProperty() {
    }

    public SwitchProperty(String key, String title, String extendStr) {
        this.b = "string";
        this.e = "switch";
        this.a = key;
        this.f = title;
        this.l = extendStr;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        JSONArray jSONArray = new JSONArray();
        if (this.l != null) {
            jSONArray = JSONArray.parseArray((String)this.l);
            jSONObject.put("extendOption", (Object)jSONArray);
        }
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

