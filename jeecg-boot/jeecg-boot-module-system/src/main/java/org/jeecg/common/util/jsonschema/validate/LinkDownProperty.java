/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 */
package org.jeecg.common.util.jsonschema.validate;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.util.jsonschema.CommonProperty;
import org.jeecg.common.util.jsonschema.BaseColumn;

public class LinkDownProperty
extends CommonProperty {
    String l;
    List<BaseColumn> m;

    public String getDictTable() {
        return this.l;
    }

    public void setDictTable(String dictTable) {
        this.l = dictTable;
    }

    public List<BaseColumn> getOtherColumns() {
        return this.m;
    }

    public void setOtherColumns(List<BaseColumn> otherColumns) {
        this.m = otherColumns;
    }

    public LinkDownProperty() {
    }

    public LinkDownProperty(String key, String title, String dictTable) {
        this.b = "string";
        this.e = "link_down";
        this.a = key;
        this.f = title;
        this.l = dictTable;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        JSONObject jSONObject2 = JSONObject.parseObject((String)this.l);
        jSONObject.put("config", (Object)jSONObject2);
        jSONObject.put("others", this.m);
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

