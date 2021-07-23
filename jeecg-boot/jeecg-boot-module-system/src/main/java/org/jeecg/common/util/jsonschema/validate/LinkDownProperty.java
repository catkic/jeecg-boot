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
    String dictTable;
    List<BaseColumn> otherColumns;

    public String getDictTable() {
        return this.dictTable;
    }

    public void setDictTable(String dictTable) {
        this.dictTable = dictTable;
    }

    public List<BaseColumn> getOtherColumns() {
        return this.otherColumns;
    }

    public void setOtherColumns(List<BaseColumn> otherColumns) {
        this.otherColumns = otherColumns;
    }

    public LinkDownProperty() {
    }

    public LinkDownProperty(String key, String title, String dictTable) {
        this.type = "string";
        this.view = "link_down";
        this.key = key;
        this.title = title;
        this.dictTable = dictTable;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        JSONObject jSONObject2 = JSONObject.parseObject((String)this.dictTable);
        jSONObject.put("config", (Object)jSONObject2);
        jSONObject.put("others", this.otherColumns);
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

