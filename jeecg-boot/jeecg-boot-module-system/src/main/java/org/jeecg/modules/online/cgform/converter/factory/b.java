/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.util.SpringContextUtils
 *  org.jeecg.common.util.oConvertUtils
 */
package org.jeecg.modules.online.cgform.converter.factory;

import java.util.ArrayList;
import java.util.List;

import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.field.DictModelConverter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class b extends DictModelConverter {
    public b(OnlCgformField onlCgformField) {
        ISysBaseAPI iSysBaseAPI = (ISysBaseAPI) SpringContextUtils.getBean(ISysBaseAPI.class);
        String string = "SYS_DEPART";
        String string2 = "DEPART_NAME";
        String string3 = "ID";
        this.dictList = iSysBaseAPI.queryTableDictItemsByCode(string, string2, string3);
        this.filed = onlCgformField.getDbFieldName();
    }

    @Override
    public String converterToVal(String txt) {
        if (oConvertUtils.isEmpty(txt)) {
            return null;
        }
        List<String> arrayList = new ArrayList<>();
        for (String string : txt.split(",")) {
            String string2 = super.converterToVal(string);
            if (string2 != null) {
                arrayList.add(string2);
            }
        }
        return String.join(",", arrayList);
    }

    @Override
    public String converterToTxt(String val) {
        if (oConvertUtils.isEmpty(val)) {
            return null;
        }
        List<String> arrayList = new ArrayList<String>();
        for (String string : val.split(",")) {
            String string2 = super.converterToTxt(string);
            if (string2 != null) {
                arrayList.add(string2);
            }
        }
        return String.join(",", arrayList);
    }
}

