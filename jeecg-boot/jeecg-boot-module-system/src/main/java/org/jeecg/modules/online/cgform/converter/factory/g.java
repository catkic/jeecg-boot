/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jeecg.common.constant.ProvinceCityArea
 *  org.jeecg.common.util.SpringContextUtils
 *  org.jeecg.common.util.oConvertUtils
 */
package org.jeecg.modules.online.cgform.converter.factory;

import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.field.DictModelConverter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class g
extends DictModelConverter {
    ProvinceCityArea c;

    public g(OnlCgformField onlCgformField) {
        this.filed = onlCgformField.getDbFieldName();
        this.c = (ProvinceCityArea)SpringContextUtils.getBean(ProvinceCityArea.class);
    }

    @Override
    public String converterToVal(String txt) {
        if (oConvertUtils.isEmpty((Object)txt)) {
            return null;
        }
        return this.c.getCode(txt);
    }

    @Override
    public String converterToTxt(String val) {
        if (oConvertUtils.isEmpty((Object)val)) {
            return null;
        }
        return this.c.getText(val);
    }
}

