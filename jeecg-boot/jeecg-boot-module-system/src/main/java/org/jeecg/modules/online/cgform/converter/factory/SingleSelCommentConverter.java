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

public class SingleSelCommentConverter
extends DictModelConverter {
    public SingleSelCommentConverter(OnlCgformField onlCgformField) {
        ISysBaseAPI iSysBaseAPI = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
        String string = onlCgformField.getDictTable();
        String string2 = onlCgformField.getDictText();
        String string3 = onlCgformField.getDictField();
        List list = new ArrayList();
        if (oConvertUtils.isNotEmpty((Object)string)) {
            list = iSysBaseAPI.queryTableDictItemsByCode(string, string2, string3);
        } else if (oConvertUtils.isNotEmpty((Object)string3)) {
            list = iSysBaseAPI.queryDictItemsByCode(string3);
        }
        this.dictList = list;
        this.filed = onlCgformField.getDbFieldName();
    }
}

