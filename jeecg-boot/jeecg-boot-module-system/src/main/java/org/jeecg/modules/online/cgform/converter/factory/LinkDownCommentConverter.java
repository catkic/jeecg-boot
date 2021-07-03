/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 */
package org.jeecg.modules.online.cgform.converter.factory;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.jeecg.modules.online.cgform.link.LinkDown;
import org.jeecg.modules.online.cgform.converter.field.StringModelConverter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class LinkDownCommentConverter
extends StringModelConverter {
    private String f;

    public String getLinkField() {
        return this.f;
    }

    public void setLinkField(String linkField) {
        this.f = linkField;
    }

    public LinkDownCommentConverter(OnlCgformField onlCgformField) {
        String string = onlCgformField.getDictTable();
        LinkDown a2 = (LinkDown)JSONObject.parseObject((String)string, LinkDown.class);
        this.setTable(a2.getTable());
        this.setCode(a2.getKey());
        this.setText(a2.getTxt());
        this.f = a2.getLinkField();
    }

    @Override
    public Map<String, String> getConfig() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("linkField", this.f);
        return hashMap;
    }
}

