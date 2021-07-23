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

public class HiddenProperty
extends CommonProperty {

    public HiddenProperty() {
    }

    public HiddenProperty(String key, String title) {
        this.type = "string";
        this.view = "hidden";
        this.key = key;
        this.title = title;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        jSONObject.put("hidden", (Object)true);
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

