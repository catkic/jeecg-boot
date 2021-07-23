/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 *  org.jeecg.common.system.vo.DictModel
 */
package org.jeecg.common.util.jsonschema;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;
import org.jeecg.common.system.vo.DictModel;

@Data
public abstract class CommonProperty implements Serializable {
    protected String key;
    protected String type;
    protected List<DictModel> include;
    protected Object constant;
    protected String view;
    protected String title;
    protected Integer order;
    protected boolean disabled;
    protected String defVal;
    protected String fieldExtendJson;
    protected Integer dbPointLength;

    public abstract Map<String, Object> getPropertyJson();

    public JSONObject getCommonJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", (Object)this.type);
        if (this.include != null && this.include.size() > 0) {
            jSONObject.put("enum", this.include);
        }
        if (this.constant != null) {
            jSONObject.put("const", this.constant);
        }
        if (this.title != null) {
            jSONObject.put("title", this.title);
        }
        if (this.order != null) {
            jSONObject.put("order", this.order);
        }
        if (this.view == null) {
            jSONObject.put("view", "input");
        } else {
            jSONObject.put("view", this.view);
        }
        if (this.disabled) {
            String string = "{\"widgetattrs\":{\"disabled\":true}}";
            JSONObject jSONObject2 = JSONObject.parseObject(string);
            jSONObject.put("ui", jSONObject2);
        }
        if (this.defVal != null && this.defVal.length() > 0) {
            jSONObject.put("defVal", this.defVal);
        }
        if (this.fieldExtendJson != null) {
            jSONObject.put("fieldExtendJson", this.fieldExtendJson);
        }
        if (this.dbPointLength != null) {
            jSONObject.put("dbPointLength", this.dbPointLength);
        }
        return jSONObject;
    }
}

