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
    private String extendStr;

    public SwitchProperty() {
    }

    public SwitchProperty(String key, String title, String extendStr) {
        this.type = "string";
        this.view = "switch";
        this.key = key;
        this.title = title;
        this.extendStr = extendStr;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        JSONArray jSONArray = new JSONArray();
        if (this.extendStr != null) {
            jSONArray = JSONArray.parseArray(this.extendStr);
            jSONObject.put("extendOption", jSONArray);
        }
        hashMap.put("prop", jSONObject);
        return hashMap;
    }
}

