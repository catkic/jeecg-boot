/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package org.jeecg.common.util.jsonschema;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jeecg.common.util.jsonschema.CommonProperty;
import org.jeecg.common.util.jsonschema.JsonSchemaDescrip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class b {
    private static final Logger a = LoggerFactory.getLogger(b.class);

    public static JSONObject a(JsonSchemaDescrip jsonSchemaDescrip, List<CommonProperty> list) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("$schema", (Object)jsonSchemaDescrip.get$schema());
        jSONObject.put("type", (Object)jsonSchemaDescrip.getType());
        jSONObject.put("title", (Object)jsonSchemaDescrip.getTitle());
        List<String> list2 = jsonSchemaDescrip.getRequired();
        jSONObject.put("required", list2);
        JSONObject jSONObject2 = new JSONObject();
        for (CommonProperty commonProperty : list) {
            Map<String, Object> map = commonProperty.getPropertyJson();
            jSONObject2.put(map.get("key").toString(), map.get("prop"));
        }
        jSONObject.put("properties", (Object)jSONObject2);
        return jSONObject;
    }

    public static JSONObject a(String string, List<String> list, List<CommonProperty> list2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", (Object)"object");
        jSONObject.put("view", (Object)"tab");
        jSONObject.put("title", (Object)string);
        if (list == null) {
            list = new ArrayList<String>();
        }
        jSONObject.put("required", list);
        JSONObject jSONObject2 = new JSONObject();
        for (CommonProperty commonProperty : list2) {
            Map<String, Object> map = commonProperty.getPropertyJson();
            jSONObject2.put(map.get("key").toString(), map.get("prop"));
        }
        jSONObject.put("properties", (Object)jSONObject2);
        return jSONObject;
    }
}

