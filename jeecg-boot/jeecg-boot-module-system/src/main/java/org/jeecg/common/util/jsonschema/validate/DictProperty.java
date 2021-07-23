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

public class DictProperty
extends CommonProperty {
    private String dictCode;
    private String dictTable;
    private String dictText;

    public String getDictCode() {
        return this.dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictTable() {
        return this.dictTable;
    }

    public void setDictTable(String dictTable) {
        this.dictTable = dictTable;
    }

    public String getDictText() {
        return this.dictText;
    }

    public void setDictText(String dictText) {
        this.dictText = dictText;
    }

    public DictProperty() {
    }

    public DictProperty(String key, String title, String dictTable, String dictCode, String dictText) {
        this.type = "string";
        this.view = "sel_search";
        this.key = key;
        this.title = title;
        this.dictCode = dictCode;
        this.dictTable = dictTable;
        this.dictText = dictText;
    }

    public DictProperty(String key, String view, String title, String dictTable, String dictCode, String dictText) {
        this.type = "string";
        this.view = view;
        this.key = key;
        this.title = title;
        this.dictCode = dictCode;
        this.dictTable = dictTable;
        this.dictText = dictText;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        if (this.dictCode != null) {
            jSONObject.put("dictCode", (Object)this.dictCode);
        }
        if (this.dictTable != null) {
            jSONObject.put("dictTable", (Object)this.dictTable);
        }
        if (this.dictText != null) {
            jSONObject.put("dictText", (Object)this.dictText);
        }
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

