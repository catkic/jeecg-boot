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
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.cgform.a.LinkDown;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.model.HrefSlots;
import org.jeecg.modules.online.cgform.model.OnlColumn;
import org.jeecg.modules.online.cgform.model.OnlComplexModel;
import org.jeecg.modules.online.cgform.model.b;
import org.jeecg.modules.online.cgform.model.c;
import org.jeecg.modules.online.cgform.model.d;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgform.service.IOnlineService;
import org.jeecg.modules.online.cgform.util.EnhanceJsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service(value="onlineService")
public class i
implements IOnlineService {
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
        Object object7;
        Object object2;
        Object object3;
        String string = head.getId();
        List<OnlCgformField> list = this.a(string);
        List<String> list2 = this.onlAuthPageService.queryHideCode(string, true);
        ArrayList<OnlColumn> arrayList = new ArrayList<OnlColumn>();
        HashMap<String, List<DictModel>> hashMap = new HashMap<String, List<DictModel>>();
        ArrayList<HrefSlots> arrayList2 = new ArrayList<HrefSlots>();
        ArrayList<b> arrayList3 = new ArrayList<b>();
        ArrayList<String> arrayList4 = new ArrayList<String>();
        for (OnlCgformField object42 : list) {
            List list3;
            Object object4;
            Object object5;
            Object object6;
            object3 = object42.getDbFieldName();
            object2 = object42.getMainTable();
            object7 = object42.getMainField();
            if (oConvertUtils.isNotEmpty((Object)object7) && oConvertUtils.isNotEmpty((Object)object2)) {
                object6 = new b((String)object3, (String)object7);
                arrayList3.add((b)object6);
            }
            if (1 != object42.getIsShowList() || "id".equals(object3) || list2.contains(object3) || arrayList4.contains(object3)) continue;
            object6 = new OnlColumn(object42.getDbFieldTxt(), (String)object3);
            String string2 = object42.getDictField();
            String string3 = object42.getFieldShowType();
            if (oConvertUtils.isNotEmpty((Object)string2) && !"popup".equals(string3)) {
                object5 = new ArrayList<DictModel>();
                if (oConvertUtils.isNotEmpty((Object)object42.getDictTable())) {
                    object5 = this.sysBaseAPI.queryTableDictItemsByCode(object42.getDictTable(), object42.getDictText(), string2);
                } else if (oConvertUtils.isNotEmpty((Object)object42.getDictField())) {
                    object5 = this.sysBaseAPI.queryDictItemsByCode(string2);
                }
                hashMap.put((String)object3, (List<DictModel>)object5);
                ((OnlColumn)object6).setCustomRender((String)object3);
            }
            if ("switch".equals(string3)) {
                object5 = org.jeecg.modules.online.cgform.util.b.a(object42);
                hashMap.put((String)object3, (List<DictModel>)object5);
                ((OnlColumn)object6).setCustomRender((String)object3);
            }
            if ("link_down".equals(string3)) {
                object5 = object42.getDictTable();
                object4 = (LinkDown)JSONObject.parseObject((String)object5, LinkDown.class);
                list3 = this.sysBaseAPI.queryTableDictItemsByCode(((LinkDown)object4).getTable(), ((LinkDown)object4).getTxt(), ((LinkDown)object4).getKey());
                hashMap.put((String)object3, list3);
                ((OnlColumn)object6).setCustomRender((String)object3);
                arrayList.add((OnlColumn)object6);
                String string4 = ((LinkDown)object4).getLinkField();
                this.a(list, arrayList4, arrayList, (String)object3, string4);
            }
            if ("sel_tree".equals(string3)) {
                object5 = object42.getDictText().split(",");
                object4 = this.sysBaseAPI.queryTableDictItemsByCode(object42.getDictTable(), object5[2], object5[0]);
                hashMap.put((String)object3, (List<DictModel>)object4);
                ((OnlColumn)object6).setCustomRender((String)object3);
            }
            if ("cat_tree".equals(string3)) {
                object5 = object42.getDictText();
                if (oConvertUtils.isEmpty((Object)object5)) {
                    object4 = org.jeecg.modules.online.cgform.util.b.e(object42.getDictField());
                    list3 = this.sysBaseAPI.queryFilterTableDictInfo("SYS_CATEGORY", "NAME", "ID", (String)object4);
                    hashMap.put((String)object3, list3);
                    ((OnlColumn)object6).setCustomRender((String)object3);
                } else {
                    ((OnlColumn)object6).setCustomRender("_replace_text_" + (String)object5);
                }
            }
            if ("sel_depart".equals(string3)) {
                object5 = this.sysBaseAPI.queryAllDepartBackDictModel();
                hashMap.put((String)object3, (List<DictModel>)object5);
                ((OnlColumn)object6).setCustomRender((String)object3);
            }
            if ("sel_user".equals(object42.getFieldShowType())) {
                object5 = this.sysBaseAPI.queryTableDictItemsByCode("SYS_USER", "REALNAME", "USERNAME");
                hashMap.put((String)object3, (List<DictModel>)object5);
                ((OnlColumn)object6).setCustomRender((String)object3);
            }
            if (string3.indexOf("file") >= 0) {
                ((OnlColumn)object6).setScopedSlots(new c("fileSlot"));
            } else if (string3.indexOf("image") >= 0) {
                ((OnlColumn)object6).setScopedSlots(new c("imgSlot"));
            } else if (string3.indexOf("editor") >= 0) {
                ((OnlColumn)object6).setScopedSlots(new c("htmlSlot"));
            } else if (string3.equals("date")) {
                ((OnlColumn)object6).setScopedSlots(new c("dateSlot"));
            } else if (string3.equals("pca")) {
                ((OnlColumn)object6).setScopedSlots(new c("pcaSlot"));
            }
            if (StringUtils.isNotBlank((String)object42.getFieldHref())) {
                object5 = "fieldHref_" + (String)object3;
                ((OnlColumn)object6).setHrefSlotName((String)object5);
                arrayList2.add(new HrefSlots((String)object5, object42.getFieldHref()));
            }
            if ("1".equals(object42.getSortFlag())) {
                ((OnlColumn)object6).setSorter(true);
            }
            if (oConvertUtils.isNotEmpty((Object)(object5 = object42.getFieldExtendJson())) && ((String)object5).indexOf("showLength") > 0 && (object4 = JSON.parseObject((String)object5)) != null && object4.get((Object)"showLength") != null) {
                ((OnlColumn)object6).setShowLength(oConvertUtils.getInt((Object)object4.get((Object)"showLength")));
            }
            if ("link_down".equals(string3)) continue;
            arrayList.add((OnlColumn)object6);
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
        onlComplexModel.setFieldHrefSlots(arrayList2);
        onlComplexModel.setForeignKeys(arrayList3);
        onlComplexModel.setHideColumns(list2);
        List<OnlCgformButton> list4 = this.onlCgformHeadService.queryButtonList(string, true);
        object3 = new ArrayList();
        for (Object object7 : list4) {
            if (list2.contains(((OnlCgformButton)object7).getButtonCode())) continue;
            object3.add(object7);
        }
        onlComplexModel.setCgButtonList((List<OnlCgformButton>)object3);
        object2 = this.onlCgformHeadService.queryEnhanceJs(string, "list");
        if (object2 != null && oConvertUtils.isNotEmpty((Object)((OnlCgformEnhanceJs)object2).getCgJs())) {
            object7 = EnhanceJsUtil.b(((OnlCgformEnhanceJs)object2).getCgJs(), list4);
            onlComplexModel.setEnhanceJs((String)object7);
        }
        if ("Y".equals(head.getIsTree())) {
            onlComplexModel.setPidField(head.getTreeParentIdField());
            onlComplexModel.setHasChildrenField(head.getTreeIdField());
            onlComplexModel.setTextField(head.getTreeFieldname());
        }
        return onlComplexModel;
    }

    private void a(List<OnlCgformField> list, List<String> list2, List<OnlColumn> list3, String string, String string2) {
        if (oConvertUtils.isNotEmpty((Object)string2)) {
            String[] arrstring;
            block0: for (String string3 : arrstring = string2.split(",")) {
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
        if (oConvertUtils.isEmpty((Object)string2)) {
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
        list = null;
        if ("Y".equals(head.getIsTree())) {
            list = new d();
            ((d)((Object)list)).setCodeField("id");
            ((d)((Object)list)).setFieldName(head.getTreeParentIdField());
            ((d)((Object)list)).setPidField(head.getTreeParentIdField());
            ((d)((Object)list)).setPidValue("0");
            ((d)((Object)list)).setHsaChildField(head.getTreeIdField());
            ((d)((Object)list)).setTableName(org.jeecg.modules.online.cgform.util.b.f(head.getTableName()));
            ((d)((Object)list)).setTextField(head.getTreeFieldname());
        }
        JSONObject jSONObject2 = org.jeecg.modules.online.cgform.util.b.a(list2, arrayList, list);
        jSONObject2.put("table", (Object)head.getTableName());
        jSONObject2.put("describe", (Object)head.getTableTxt());
        jSONObject.put("schema", (Object)jSONObject2);
        jSONObject.put("head", (Object)head);
        List<OnlCgformButton> list3 = this.queryFormValidButton(string);
        if (list3 != null && list3.size() > 0) {
            jSONObject.put("cgButtonList", list3);
        }
        if (onlCgformEnhanceJs != null && oConvertUtils.isNotEmpty((Object)onlCgformEnhanceJs.getCgJs())) {
            String string3 = EnhanceJsUtil.c(onlCgformEnhanceJs.getCgJs(), list3);
            onlCgformEnhanceJs.setCgJs(string3);
            jSONObject.put("enhanceJs", (Object)EnhanceJsUtil.a(onlCgformEnhanceJs.getCgJs()));
        }
        return jSONObject;
    }

    @Override
    @Cacheable(value={"sys:cache:online:form"}, key="'erp'+ #head.id+'-'+#username")
    public JSONObject queryOnlineFormObj(OnlCgformHead head, String username) {
        OnlCgformEnhanceJs onlCgformEnhanceJs = this.onlCgformHeadService.queryEnhanceJs(head.getId(), "form");
        return this.queryOnlineFormObj(head, onlCgformEnhanceJs);
    }

    @Override
    public List<OnlCgformButton> queryFormValidButton(String headId) {
        List<OnlCgformButton> list = this.onlCgformHeadService.queryButtonList(headId, false);
        List list2 = null;
        if (list != null && list.size() > 0) {
            LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
            String string = loginUser.getId();
            List<String> list3 = this.onlAuthPageService.queryFormHideButton(string, headId);
            list2 = list.stream().filter(onlCgformButton -> list3 == null || list3.indexOf(onlCgformButton.getButtonCode()) < 0).collect(Collectors.toList());
        }
        return list2;
    }

    @Override
    @Cacheable(value={"sys:cache:online:form"}, key="#head.id+'-'+#username")
    public JSONObject queryOnlineFormItem(OnlCgformHead head, String username) {
        head.setTaskId(null);
        return this.a(head);
    }

    @Override
    @Cacheable(value={"sys:cache:online:form"}, key="'flow' + #head.id + '-' + #username + '-' + #taskId")
    public JSONObject queryFlowOnlineFormItem(OnlCgformHead head, String username, String taskId) {
        head.setTaskId(taskId);
        return this.a(head);
    }

    @Override
    @Cacheable(value={"sys:cache:online:form"}, key="'enhancejs' + #code + '-' + #type")
    public String queryEnahcneJsString(String code, String type) {
        String string = "";
        OnlCgformEnhanceJs onlCgformEnhanceJs = this.onlCgformHeadService.queryEnhanceJs(code, type);
        if (onlCgformEnhanceJs != null && oConvertUtils.isNotEmpty((Object)onlCgformEnhanceJs.getCgJs())) {
            string = EnhanceJsUtil.b(onlCgformEnhanceJs.getCgJs(), null);
        }
        return string;
    }

    private List<OnlCgformField> a(String string) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object)string);
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        return this.onlCgformFieldService.list((Wrapper)lambdaQueryWrapper);
    }

    private JSONObject a(OnlCgformHead onlCgformHead) {
        OnlCgformEnhanceJs onlCgformEnhanceJs = this.onlCgformHeadService.queryEnhanceJs(onlCgformHead.getId(), "form");
        JSONObject jSONObject = this.queryOnlineFormObj(onlCgformHead, onlCgformEnhanceJs);
        jSONObject.put("formTemplate", (Object)onlCgformHead.getFormTemplate());
        if (onlCgformHead.getTableType() == 2) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("schema");
            String string = onlCgformHead.getSubTableStr();
            if (oConvertUtils.isNotEmpty((Object)string)) {
                Object object;
                ArrayList<OnlCgformHead> arrayList = new ArrayList<OnlCgformHead>();
                for (String string2 : string.split(",")) {
                    object = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string2));
                    if (object == null) continue;
                    arrayList.add((OnlCgformHead)object);
                }
                if (arrayList.size() > 0) {
                    Collections.sort(arrayList, new Comparator<OnlCgformHead>(){

                        public int a(OnlCgformHead onlCgformHead, OnlCgformHead onlCgformHead2) {
                            Integer n2;
                            Integer n3 = onlCgformHead.getTabOrderNum();
                            if (n3 == null) {
                                n3 = 0;
                            }
                            if ((n2 = onlCgformHead2.getTabOrderNum()) == null) {
                                n2 = 0;
                            }
                            return n3.compareTo(n2);
                        }

                        @Override
                        public /* synthetic */ int compare(Object object, Object object2) {
                            return this.a((OnlCgformHead)object, (OnlCgformHead)object2);
                        }
                    });
                    for (OnlCgformHead onlCgformHead2 : arrayList) {
                        Object object2;
                        String string2;
                        List<OnlCgformField> list = this.onlCgformFieldService.queryAvailableFields(onlCgformHead2.getId(), onlCgformHead2.getTableName(), onlCgformHead.getTaskId(), false);
                        EnhanceJsUtil.b(onlCgformEnhanceJs, onlCgformHead2.getTableName(), list);
                        string2 = new JSONObject();
                        object = new ArrayList();
                        object = oConvertUtils.isNotEmpty((Object)onlCgformHead.getTaskId()) ? this.onlCgformFieldService.queryDisabledFields(onlCgformHead2.getTableName(), onlCgformHead.getTaskId()) : this.onlAuthPageService.queryFormDisabledCode(onlCgformHead2.getId());
                        if (1 == onlCgformHead2.getRelationType()) {
                            string2 = org.jeecg.modules.online.cgform.util.b.a(list, object, null);
                        } else {
                            string2.put("columns", (Object)org.jeecg.modules.online.cgform.util.b.a(list, object));
                            object2 = this.onlAuthPageService.queryListHideButton(null, onlCgformHead2.getId());
                            string2.put("hideButtons", object2);
                        }
                        object2 = this.onlCgformFieldService.queryForeignKey(onlCgformHead2.getId(), onlCgformHead.getTableName());
                        string2.put("foreignKey", object2);
                        string2.put("id", (Object)onlCgformHead2.getId());
                        string2.put("describe", (Object)onlCgformHead2.getTableTxt());
                        string2.put("key", (Object)onlCgformHead2.getTableName());
                        string2.put("view", (Object)"tab");
                        string2.put("order", (Object)onlCgformHead2.getTabOrderNum());
                        string2.put("relationType", (Object)onlCgformHead2.getRelationType());
                        string2.put("formTemplate", (Object)onlCgformHead2.getFormTemplate());
                        jSONObject2.getJSONObject("properties").put(onlCgformHead2.getTableName(), (Object)string2);
                    }
                }
            }
            if (onlCgformEnhanceJs != null && oConvertUtils.isNotEmpty((Object)onlCgformEnhanceJs.getCgJs())) {
                jSONObject.put("enhanceJs", (Object)EnhanceJsUtil.a(onlCgformEnhanceJs.getCgJs()));
            }
        }
        return jSONObject;
    }

    private static /* synthetic */ Object a(SerializedLambda serializedLambda) {
        switch (serializedLambda.getImplMethodName()) {
            case "getOrderNum": {
                if (serializedLambda.getImplMethodKind() != 5 || !serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") || !serializedLambda.getFunctionalInterfaceMethodName().equals("apply") || !serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") || !serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgform/entity/OnlCgformField") || !serializedLambda.getImplMethodSignature().equals("()Ljava/lang/Integer;")) break;
                return OnlCgformField::getOrderNum;
            }
            case "getCgformHeadId": {
                if (serializedLambda.getImplMethodKind() != 5 || !serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") || !serializedLambda.getFunctionalInterfaceMethodName().equals("apply") || !serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") || !serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgform/entity/OnlCgformField") || !serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) break;
                return OnlCgformField::getCgformHeadId;
            }
            case "getTableName": {
                if (serializedLambda.getImplMethodKind() != 5 || !serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") || !serializedLambda.getFunctionalInterfaceMethodName().equals("apply") || !serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") || !serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgform/entity/OnlCgformHead") || !serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) break;
                return OnlCgformHead::getTableName;
            }
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }
}

