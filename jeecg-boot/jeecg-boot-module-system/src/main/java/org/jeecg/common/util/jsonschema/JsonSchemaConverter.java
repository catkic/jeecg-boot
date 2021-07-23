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
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonSchemaConverter {

    public static JSONObject schemeToList(JsonSchemaDescrip jsonSchemaDescrip, List<CommonProperty> list) {
        JSONObject jSONObject = new JSONObject();

        jSONObject.put("$schema", jsonSchemaDescrip.getSchema());
        jSONObject.put("type", jsonSchemaDescrip.getType());
        jSONObject.put("title", jsonSchemaDescrip.getTitle());
        List<String> required = jsonSchemaDescrip.getRequired();
        jSONObject.put("required", required);
        JSONObject properties = new JSONObject();
        for (CommonProperty commonProperty : list) {
            Map<String, Object> map = commonProperty.getPropertyJson();
            properties.put(map.get("key").toString(), map.get("prop"));
        }
        jSONObject.put("properties", properties);
        return jSONObject;
    }

    public static JSONObject listToList(String title, List<String> required, List<CommonProperty> list2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "object");
        jSONObject.put("view", "tab");
        jSONObject.put("title", title);
        if (required == null) {
            jSONObject.put("required", new ArrayList<>());
        }else {
            jSONObject.put("required", required);
        }
        JSONObject properties = new JSONObject();
        for (CommonProperty commonProperty : list2) {
            Map<String, Object> map = commonProperty.getPropertyJson();
            properties.put(map.get("key").toString(), map.get("prop"));
        }
        jSONObject.put("properties", properties);
        return jSONObject;
    }
}

