/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.system.vo.SysCategoryModel
 *  org.jeecg.common.util.oConvertUtils
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 */
package org.jeecg.modules.online.cgform.enhance.export;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.SysCategoryModel;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaListInter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "cgformEnhanceExportDemo")
public class CgformEnhanceExportDemo implements CgformEnhanceJavaListInter {
    @Autowired
    ISysBaseAPI sysBaseAPI;
    @Autowired
    IOnlCgformFieldService onlCgformFieldService;

    @Override
    public void execute(String tableName, List<Map<String, Object>> data) throws BusinessException {
        List<SysCategoryModel> list = this.sysBaseAPI.queryAllDSysCategory();
        for (Map<String, Object> map : data) {
            OnlCgformField onlCgformField;
            String string;
            String string2 = oConvertUtils.getString(map.get("fen_tree"));
            if (oConvertUtils.isEmpty(string2)) continue;
            List<SysCategoryModel> list2 = list.stream().filter(sysCategoryModel -> sysCategoryModel.getId().equals(string2)).collect(Collectors.toList());
            if (list2.size() != 0) {
                map.put("fen_tree", list2.get(0).getName());
            }
            if (oConvertUtils.isEmpty(string = oConvertUtils.getString(map.get("sel_search"))) || (onlCgformField = this.onlCgformFieldService.queryFormFieldByTableNameAndField(tableName, "sel_search")) == null || oConvertUtils.isEmpty(onlCgformField.getDictTable()))
                continue;
            List list3 = this.sysBaseAPI.queryTableDictByKeys(onlCgformField.getDictTable(), onlCgformField.getDictText(), onlCgformField.getDictField(), new String[]{string});
            if (list3 == null || list3.size() <= 0) continue;
            map.put("sel_search", list3.get(0));
        }
    }
}

