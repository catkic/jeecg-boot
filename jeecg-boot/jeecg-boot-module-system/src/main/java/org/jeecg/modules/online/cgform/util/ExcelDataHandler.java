/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.util.SpringContextUtils
 *  org.jeecgframework.poi.handler.impl.ExcelDataHandlerDefaultImpl
 *  org.jeecgframework.poi.util.PoiPublicUtil
 */
package org.jeecg.modules.online.cgform.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecgframework.poi.handler.impl.ExcelDataHandlerDefaultImpl;
import org.jeecgframework.poi.util.PoiPublicUtil;

public class ExcelDataHandler
extends ExcelDataHandlerDefaultImpl {
    Map<String, OnlCgformField> data;
    ISysBaseAPI sysBaseAPI;
    String basePath;
    String bizPath;
    String uploadType;

    public ExcelDataHandler(List<OnlCgformField> list, String basePath, String uploadType) {
        this.data = this.a(list);
        this.basePath = basePath;
        this.bizPath = "online";
        this.uploadType = uploadType;
        this.sysBaseAPI = SpringContextUtils.getBean(ISysBaseAPI.class);
    }

    private Map<String, OnlCgformField> a(List<OnlCgformField> list) {
        HashMap<String, OnlCgformField> hashMap = new HashMap<>();
        for (OnlCgformField onlCgformField : list) {
            hashMap.put(onlCgformField.getDbFieldTxt(), onlCgformField);
        }
        return hashMap;
    }

    public void setMapValue(Map<String, Object> map, String originKey, Object value) {
        String string = this.a(originKey);
        if (value instanceof Double) {
            map.put(string, PoiPublicUtil.doubleToString((Double)value));
        } else if (value instanceof byte[]) {
            byte[] arrby = (byte[])value;
            String path = DataBaseUtils.uploadImage(arrby, this.basePath, this.bizPath, this.uploadType);
            if (path != null) {
                map.put(string, path);
            }
        } else {
            map.put(string, value == null ? "" : value.toString());
        }
    }

    private String a(String string) {
        if (this.data.containsKey(string)) {
            return "$mainTable$" + this.data.get(string).getDbFieldName();
        }
        return "$subTable$" + string;
    }
}

