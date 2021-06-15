/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  org.apache.commons.lang.StringUtils
 *  org.apache.shiro.SecurityUtils
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.system.vo.DictModel
 *  org.jeecg.common.system.vo.LoginUser
 *  org.jeecg.common.util.oConvertUtils
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.cache.annotation.Cacheable
 *  org.springframework.stereotype.Service
 */
package org.jeecg.modules.online.cgform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.cgform.link.LinkDown;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.model.HrefSlots;
import org.jeecg.modules.online.cgform.model.OnlColumn;
import org.jeecg.modules.online.cgform.model.OnlComplexModel;
import org.jeecg.modules.online.cgform.model.TableModel;
import org.jeecg.modules.online.cgform.model.ScopedSlots;
import org.jeecg.modules.online.cgform.model.TablePidModel;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgform.service.IOnlineService;
import org.jeecg.modules.online.cgform.util.DbConstant;
import org.jeecg.modules.online.cgform.util.EnhanceJsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service(value = "onlineService")
public class OnlineServiceImpl implements IOnlineService {
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IOnlAuthPageService onlAuthPageService;

    @Override
    public OnlComplexModel queryOnlineConfig(OnlCgformHead head, String username) {
        String string = head.getId();
        List<OnlCgformField> list = this.a(string);
        List<String> list2 = this.onlAuthPageService.queryHideCode(string, true);
        ArrayList<OnlColumn> arrayList = new ArrayList<OnlColumn>();
        HashMap<String, List<DictModel>> hashMap = new HashMap<String, List<DictModel>>();
        ArrayList<HrefSlots> hrefSlots = new ArrayList<HrefSlots>();
        ArrayList<TableModel> tableModels = new ArrayList<>();
        ArrayList<String> arrayList4 = new ArrayList<String>();
        for (OnlCgformField onlCgformField : list) {
            List list3;
            Object object4;
            String dbFieldName = onlCgformField.getDbFieldName();
            String mainTable = onlCgformField.getMainTable();
            String mainField = onlCgformField.getMainField();
            if (oConvertUtils.isNotEmpty(mainField) && oConvertUtils.isNotEmpty(mainTable)) {
                tableModels.add(new TableModel(dbFieldName, mainField));
            }
            if (1 != onlCgformField.getIsShowList() || "id".equals(dbFieldName) || list2.contains(dbFieldName) || arrayList4.contains(dbFieldName))
                continue;
            OnlColumn onlColumn = new OnlColumn(onlCgformField.getDbFieldTxt(), dbFieldName);
            String dictField = onlCgformField.getDictField();
            String fieldShowType = onlCgformField.getFieldShowType();
            if (oConvertUtils.isNotEmpty(dictField) && !"popup".equals(fieldShowType)) {
                List<DictModel> object5 = new ArrayList<>();
                if (oConvertUtils.isNotEmpty(onlCgformField.getDictTable())) {
                    object5 = this.sysBaseAPI.queryTableDictItemsByCode(onlCgformField.getDictTable(), onlCgformField.getDictText(), dictField);
                } else if (oConvertUtils.isNotEmpty(onlCgformField.getDictField())) {
                    object5 = this.sysBaseAPI.queryDictItemsByCode(dictField);
                }
                hashMap.put(dbFieldName, object5);
                onlColumn.setCustomRender(dbFieldName);
            }
            if ("switch".equals(fieldShowType)) {
                hashMap.put(dbFieldName, DbConstant.a(onlCgformField));
                onlColumn.setCustomRender(dbFieldName);
            }
            if ("link_down".equals(fieldShowType)) {
                LinkDown linkDown = JSONObject.parseObject(onlCgformField.getDictTable(), LinkDown.class);
                List<DictModel> dictModels = this.sysBaseAPI.queryTableDictItemsByCode(linkDown.getTable(), linkDown.getTxt(), linkDown.getKey());
                hashMap.put(dbFieldName, dictModels);
                onlColumn.setCustomRender(dbFieldName);
                arrayList.add(onlColumn);
                String string4 = linkDown.getLinkField();
                this.a(list, arrayList4, arrayList, dbFieldName, string4);
            }
            if ("sel_tree".equals(fieldShowType)) {
                String[] dictTextSplit = onlCgformField.getDictText().split(",");
                List<DictModel> dictModelList = this.sysBaseAPI.queryTableDictItemsByCode(onlCgformField.getDictTable(), dictTextSplit[2], dictTextSplit[0]);
                hashMap.put(dbFieldName, dictModelList);
                onlColumn.setCustomRender(dbFieldName);
            }
            if ("cat_tree".equals(fieldShowType)) {
                String dictText = onlCgformField.getDictText();
                if (oConvertUtils.isEmpty(dictText)) {
                    object4 = DbConstant.e(onlCgformField.getDictField());
                    List<DictModel> dictModels = this.sysBaseAPI.queryFilterTableDictInfo("SYS_CATEGORY", "NAME", "ID", (String) object4);
                    hashMap.put(dbFieldName, dictModels);
                    onlColumn.setCustomRender(dbFieldName);
                } else {
                    onlColumn.setCustomRender("_replace_text_" + dictText);
                }
            }
            if ("sel_depart".equals(fieldShowType)) {
                hashMap.put(dbFieldName, this.sysBaseAPI.queryAllDepartBackDictModel());
                onlColumn.setCustomRender(dbFieldName);
            }
            if ("sel_user".equals(onlCgformField.getFieldShowType())) {
                hashMap.put(dbFieldName, this.sysBaseAPI.queryTableDictItemsByCode("SYS_USER", "REALNAME", "USERNAME"));
                onlColumn.setCustomRender(dbFieldName);
            }
            if (fieldShowType.contains("file")) {
                onlColumn.setScopedSlots(new ScopedSlots("fileSlot"));
            } else if (fieldShowType.contains("image")) {
                onlColumn.setScopedSlots(new ScopedSlots("imgSlot"));
            } else if (fieldShowType.contains("editor")) {
                onlColumn.setScopedSlots(new ScopedSlots("htmlSlot"));
            } else if (fieldShowType.equals("date")) {
                onlColumn.setScopedSlots(new ScopedSlots("dateSlot"));
            } else if (fieldShowType.equals("pca")) {
                onlColumn.setScopedSlots(new ScopedSlots("pcaSlot"));
            }
            if (StringUtils.isNotBlank(onlCgformField.getFieldHref())) {
                String href = "fieldHref_" + dbFieldName;
                onlColumn.setHrefSlotName(href);
                hrefSlots.add(new HrefSlots(href, onlCgformField.getFieldHref()));
            }
            if ("1".equals(onlCgformField.getSortFlag())) {
                onlColumn.setSorter(true);
            }
            String fieldExtendJson = onlCgformField.getFieldExtendJson();
            if (oConvertUtils.isNotEmpty(fieldExtendJson) && fieldExtendJson.indexOf("showLength") > 0) {
                JSONObject jsonObject = JSON.parseObject(fieldExtendJson);
                if (jsonObject != null && jsonObject.getInteger("showLength") != null) {
                    onlColumn.setShowLength(jsonObject.getInteger("showLength"));
                }
            }
            if ("link_down".equals(fieldShowType)) continue;
            arrayList.add(onlColumn);
        }
        OnlComplexModel onlComplexModel = new OnlComplexModel();
        onlComplexModel.setCode(string);
        onlComplexModel.setTableType(head.getTableType());
        onlComplexModel.setFormTemplate(head.getFormTemplate());
        onlComplexModel.setDescription(head.getTableTxt());
        onlComplexModel.setCurrentTableName(head.getTableName());
        onlComplexModel.setPaginationFlag(head.getIsPage());
        onlComplexModel.setCheckboxFlag(head.getIsCheckbox());
        onlComplexModel.setScrollFlag(head.getScroll());
        onlComplexModel.setRelationType(head.getRelationType());
        onlComplexModel.setColumns(arrayList);
        onlComplexModel.setDictOptions(hashMap);
        onlComplexModel.setFieldHrefSlots(hrefSlots);
        onlComplexModel.setForeignKeys(tableModels);
        onlComplexModel.setHideColumns(list2);
        List<OnlCgformButton> list4 = this.onlCgformHeadService.queryButtonList(string, true);
        List<OnlCgformButton> list1 = new ArrayList();
        for (OnlCgformButton object7 : list4) {
            if (list2.contains(object7.getButtonCode())) continue;
            list1.add(object7);
        }
        onlComplexModel.setCgButtonList(list1);
        OnlCgformEnhanceJs onlCgformEnhanceJs = this.onlCgformHeadService.queryEnhanceJs(string, "list");
        if (onlCgformEnhanceJs != null && oConvertUtils.isNotEmpty(onlCgformEnhanceJs.getCgJs())) {
            onlComplexModel.setEnhanceJs(EnhanceJsUtil.b(onlCgformEnhanceJs.getCgJs(), list4));
        }
        if ("Y".equals(head.getIsTree())) {
            onlComplexModel.setPidField(head.getTreeParentIdField());
            onlComplexModel.setHasChildrenField(head.getTreeIdField());
            onlComplexModel.setTextField(head.getTreeFieldname());
        }
        return onlComplexModel;
    }

    private void a(List<OnlCgformField> list, List<String> list2, List<OnlColumn> list3, String string, String string2) {
        if (oConvertUtils.isNotEmpty(string2)) {
            block0:
            for (String string3 : string2.split(",")) {
                for (OnlCgformField onlCgformField : list) {
                    String string4 = onlCgformField.getDbFieldName();
                    if (1 != onlCgformField.getIsShowList() || !string3.equals(string4)) continue;
                    list2.add(string3);
                    OnlColumn onlColumn = new OnlColumn(onlCgformField.getDbFieldTxt(), string4);
                    onlColumn.setCustomRender(string);
                    list3.add(onlColumn);
                    continue block0;
                }
            }
        }
    }

    @Override
    public JSONObject queryOnlineFormObj(OnlCgformHead head, OnlCgformEnhanceJs onlCgformEnhanceJs) {
        List<String> list;
        JSONObject jSONObject = new JSONObject();
        String string = head.getId();
        String string2 = head.getTaskId();
        List<OnlCgformField> list2 = this.onlCgformFieldService.queryAvailableFields(string, head.getTableName(), string2, false);
        ArrayList<String> arrayList = new ArrayList<String>();
        if (oConvertUtils.isEmpty((Object) string2)) {
            list = this.onlAuthPageService.queryFormDisabledCode(head.getId());
            if (list != null && list.size() > 0 && list.get(0) != null) {
                arrayList.addAll(list);
            }
        } else {
            list = this.onlCgformFieldService.queryDisabledFields(head.getTableName(), string2);
            if (list != null && list.size() > 0 && list.get(0) != null) {
                arrayList.addAll(list);
            }
        }
        EnhanceJsUtil.a(onlCgformEnhanceJs, head.getTableName(), list2);
        TablePidModel tablePidModel = new TablePidModel();

        if ("Y".equals(head.getIsTree())) {
            tablePidModel.setCodeField("id");
            tablePidModel.setFieldName(head.getTreeParentIdField());
            tablePidModel.setPidField(head.getTreeParentIdField());
            tablePidModel.setPidValue("0");
            tablePidModel.setHsaChildField(head.getTreeIdField());
            tablePidModel.setTableName(DbConstant.f(head.getTableName()));
            tablePidModel.setTextField(head.getTreeFieldname());
        }
        JSONObject jSONObject2 = DbConstant.a(list2, arrayList, tablePidModel);
        jSONObject2.put("table", (Object) head.getTableName());
        jSONObject2.put("describe", (Object) head.getTableTxt());
        jSONObject.put("schema", (Object) jSONObject2);
        jSONObject.put("head", (Object) head);
        List<OnlCgformButton> list3 = this.queryFormValidButton(string);
        if (list3 != null && list3.size() > 0) {
            jSONObject.put("cgButtonList", list3);
        }
        if (onlCgformEnhanceJs != null && oConvertUtils.isNotEmpty((Object) onlCgformEnhanceJs.getCgJs())) {
            String string3 = EnhanceJsUtil.c(onlCgformEnhanceJs.getCgJs(), list3);
            onlCgformEnhanceJs.setCgJs(string3);
            jSONObject.put("enhanceJs", (Object) EnhanceJsUtil.a(onlCgformEnhanceJs.getCgJs()));
        }
        return jSONObject;
    }

    @Override
    @Cacheable(value = {"sys:cache:online:form"}, key = "'erp'+ #head.id+'-'+#username")
    public JSONObject queryOnlineFormObj(OnlCgformHead head, String username) {
        OnlCgformEnhanceJs onlCgformEnhanceJs = this.onlCgformHeadService.queryEnhanceJs(head.getId(), "form");
        return this.queryOnlineFormObj(head, onlCgformEnhanceJs);
    }

    @Override
    public List<OnlCgformButton> queryFormValidButton(String headId) {
        List<OnlCgformButton> list = this.onlCgformHeadService.queryButtonList(headId, false);
        List list2 = null;
        if (list != null && list.size() > 0) {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String string = loginUser.getId();
            List<String> list3 = this.onlAuthPageService.queryFormHideButton(string, headId);
            list2 = list.stream().filter(onlCgformButton -> list3 == null || list3.indexOf(onlCgformButton.getButtonCode()) < 0).collect(Collectors.toList());
        }
        return list2;
    }

    @Override
    @Cacheable(value = {"sys:cache:online:form"}, key = "#head.id+'-'+#username")
    public JSONObject queryOnlineFormItem(OnlCgformHead head, String username) {
        head.setTaskId(null);
        return this.a(head);
    }

    @Override
    @Cacheable(value = {"sys:cache:online:form"}, key = "'flow' + #head.id + '-' + #username + '-' + #taskId")
    public JSONObject queryFlowOnlineFormItem(OnlCgformHead head, String username, String taskId) {
        head.setTaskId(taskId);
        return this.a(head);
    }

    @Override
    @Cacheable(value = {"sys:cache:online:form"}, key = "'enhancejs' + #code + '-' + #type")
    public String queryEnahcneJsString(String code, String type) {
        String string = "";
        OnlCgformEnhanceJs onlCgformEnhanceJs = this.onlCgformHeadService.queryEnhanceJs(code, type);
        if (onlCgformEnhanceJs != null && oConvertUtils.isNotEmpty((Object) onlCgformEnhanceJs.getCgJs())) {
            string = EnhanceJsUtil.b(onlCgformEnhanceJs.getCgJs(), "");
        }
        return string;
    }

    private List<OnlCgformField> a(String string) {
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) string);
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        return this.onlCgformFieldService.list((Wrapper) lambdaQueryWrapper);
    }

    // 这个地方改得存疑
    private JSONObject a(OnlCgformHead onlCgformHead) {
        OnlCgformEnhanceJs onlCgformEnhanceJs = this.onlCgformHeadService.queryEnhanceJs(onlCgformHead.getId(), "form");
        JSONObject jSONObject = this.queryOnlineFormObj(onlCgformHead, onlCgformEnhanceJs);
        jSONObject.put("formTemplate", onlCgformHead.getFormTemplate());
        if (onlCgformHead.getTableType() == 2) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("schema");
            String subTableStr = onlCgformHead.getSubTableStr();
            if (oConvertUtils.isNotEmpty(subTableStr)) {
                ArrayList<OnlCgformHead> arrayList = new ArrayList<OnlCgformHead>();
                for (String string2 : subTableStr.split(",")) {
                    OnlCgformHead one = this.onlCgformHeadService.getOne(new LambdaQueryWrapper<OnlCgformHead>().eq(OnlCgformHead::getTableName, string2));
                    if (one != null) {
                        arrayList.add(one);
                    }
                }
                if (!arrayList.isEmpty()) {
                    // 不知道这段行不行还没测试过
//                    Collections.sort(arrayList, Comparator.nullsFirst(Comparator.comparing(OnlCgformHead::getTabOrderNum)));
                    Collections.sort(arrayList, (onlCgformHead1, onlCgformHead2) -> {
                        Integer n2 = onlCgformHead2.getTabOrderNum();
                        Integer n3 = onlCgformHead1.getTabOrderNum();
                        if (n3 == null) {
                            n3 = 0;
                        }
                        if (n2 == null) {
                            n2 = 0;
                        }
                        return n3.compareTo(n2);
                    });
                    for (OnlCgformHead onlCgformHead2 : arrayList) {
                        List<OnlCgformField> list = this.onlCgformFieldService.queryAvailableFields(onlCgformHead2.getId(), onlCgformHead2.getTableName(), onlCgformHead.getTaskId(), false);
                        EnhanceJsUtil.b(onlCgformEnhanceJs, onlCgformHead2.getTableName(), list);
                        JSONObject jsonObjectString2 = new JSONObject();
                        List<String> string;
                        if (oConvertUtils.isNotEmpty(onlCgformHead.getTaskId())) {
                            string = this.onlCgformFieldService.queryDisabledFields(onlCgformHead2.getTableName(), onlCgformHead.getTaskId());
                        } else {
                            string = this.onlAuthPageService.queryFormDisabledCode(onlCgformHead2.getId());
                        }
                        if (1 == onlCgformHead2.getRelationType()) {
                            jsonObjectString2 = DbConstant.a(list, string, null);
                        } else {
                            jsonObjectString2.put("columns", DbConstant.a(list, string));
                            jsonObjectString2.put("hideButtons", this.onlAuthPageService.queryListHideButton(null, onlCgformHead2.getId()));
                        }
                        String object2 = this.onlCgformFieldService.queryForeignKey(onlCgformHead2.getId(), onlCgformHead.getTableName());
                        jsonObjectString2.put("foreignKey", object2);
                        jsonObjectString2.put("id", onlCgformHead2.getId());
                        jsonObjectString2.put("describe", onlCgformHead2.getTableTxt());
                        jsonObjectString2.put("key", onlCgformHead2.getTableName());
                        jsonObjectString2.put("view", "tab");
                        jsonObjectString2.put("order", onlCgformHead2.getTabOrderNum());
                        jsonObjectString2.put("relationType", onlCgformHead2.getRelationType());
                        jsonObjectString2.put("formTemplate", onlCgformHead2.getFormTemplate());
                        jSONObject2.getJSONObject("properties").put(onlCgformHead2.getTableName(), jsonObjectString2);
                    }
                }
            }
            if (onlCgformEnhanceJs != null && oConvertUtils.isNotEmpty(onlCgformEnhanceJs.getCgJs())) {
                jSONObject.put("enhanceJs", EnhanceJsUtil.a(onlCgformEnhanceJs.getCgJs()));
            }
        }
        return jSONObject;
    }

}

