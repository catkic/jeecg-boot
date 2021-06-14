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
    private static final long l = 3786503639885610767L;
    private String m;
    private String n;
    private String o;

    public String getDictCode() {
        return this.m;
    }

    public void setDictCode(String dictCode) {
        this.m = dictCode;
    }

    public String getDictTable() {
        return this.n;
    }

    public void setDictTable(String dictTable) {
        this.n = dictTable;
    }

    public String getDictText() {
        return this.o;
    }

    public void setDictText(String dictText) {
        this.o = dictText;
    }

    public DictProperty() {
    }

    public DictProperty(String key, String title, String dictTable, String dictCode, String dictText) {
        this.b = "string";
        this.e = "sel_search";
        this.a = key;
        this.f = title;
        this.m = dictCode;
        this.n = dictTable;
        this.o = dictText;
    }

    public DictProperty(String key, String view, String title, String dictTable, String dictCode, String dictText) {
        this.b = "string";
        this.e = view;
        this.a = key;
        this.f = title;
        this.m = dictCode;
        this.n = dictTable;
        this.o = dictText;
    }

    @Override
    public Map<String, Object> getPropertyJson() {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("key", this.getKey());
        JSONObject jSONObject = this.getCommonJson();
        if (this.m != null) {
            jSONObject.put("dictCode", (Object)this.m);
        }
        if (this.n != null) {
            jSONObject.put("dictTable", (Object)this.n);
        }
        if (this.o != null) {
            jSONObject.put("dictText", (Object)this.o);
        }
        hashMap.put("prop", (Object)jSONObject);
        return hashMap;
    }
}

