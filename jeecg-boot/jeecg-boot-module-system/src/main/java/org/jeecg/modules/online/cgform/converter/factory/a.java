/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.jeecg.common.util.oConvertUtils
 */
package org.jeecg.modules.online.cgform.converter.factory;

import java.util.HashMap;
import java.util.Map;

import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.field.StringModelConverter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class a extends StringModelConverter {
    private String f;

    public String getTreeText() {
        return this.f;
    }

    public void setTreeText(String treeText) {
        this.f = treeText;
    }

    public a(OnlCgformField onlCgformField) {
        super("SYS_CATEGORY", "ID", "NAME");
        this.f = onlCgformField.getDictText();
        this.field = onlCgformField.getDbFieldName();
    }

    @Override
    public Map<String, String> getConfig() {
        if (oConvertUtils.isEmpty((Object) this.f)) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("treeText", this.f);
        hashMap.put("field", this.field);
        return hashMap;
    }
}

