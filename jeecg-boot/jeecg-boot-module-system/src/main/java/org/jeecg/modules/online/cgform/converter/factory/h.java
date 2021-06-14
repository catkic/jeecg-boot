/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONArray
 *  org.jeecg.common.system.vo.DictModel
 */
package org.jeecg.modules.online.cgform.converter.factory;

import com.alibaba.fastjson.JSONArray;

import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgform.converter.field.DictModelConverter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

import java.util.ArrayList;
import java.util.List;

public class h
        extends DictModelConverter {
    public h(OnlCgformField onlCgformField) {
        JSONArray jSONArray;
        String string = onlCgformField.getFieldExtendJson();
        String string2 = "Y";
        String string3 = "N";
        if (string != null && !"".equals(string) && (jSONArray = JSONArray.parseArray((String) string)) != null && jSONArray.size() == 2) {
            string2 = jSONArray.get(0).toString();
            string3 = jSONArray.get(1).toString();
        }
        List<DictModel> list = new ArrayList<>();
        DictModel dictModel = new DictModel(string2, "是");
        DictModel dictModel2 = new DictModel(string3, "否");
        list.add(dictModel);
        list.add(dictModel2);
        this.dictList = list;
        this.filed = onlCgformField.getDbFieldName();
    }
}

