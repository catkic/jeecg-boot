/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.core.metadata.IPage
 *  com.baomidou.mybatisplus.extension.plugins.pagination.Page
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  org.apache.shiro.SecurityUtils
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.system.util.JeecgDataAutorUtils
 *  org.jeecg.common.system.vo.LoginUser
 *  org.jeecg.common.system.vo.SysPermissionDataRuleModel
 *  org.jeecg.common.system.vo.SysUserCacheInfo
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package org.jeecg.modules.online.cgform.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.util.JeecgDataAutorUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.system.vo.SysPermissionDataRuleModel;
import org.jeecg.common.system.vo.SysUserCacheInfo;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.auth.service.IOnlAuthDataService;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.cgform.link.LinkDown;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.mapper.OnlCgformFieldMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.model.TreeModel;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.util.DataBaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OnlCgformFieldServiceImpl extends ServiceImpl<OnlCgformFieldMapper, OnlCgformField> implements IOnlCgformFieldService {
    @Autowired
    private OnlCgformFieldMapper onlCgformFieldMapper;
    @Autowired
    private OnlCgformHeadMapper cgformHeadMapper;
    @Autowired
    private IOnlAuthDataService onlAuthDataService;
    @Autowired
    private IOnlAuthPageService onlAuthPageService;
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    private static final String b = "0";

    @Override
    public Map<String, Object> queryAutolistPage(String tbname, String headId, Map<String, Object> params, List<String> needList) {
        HashMap<String, Object> hashMap = new HashMap<>();
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, headId);
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        List<OnlCgformField> list = this.list(lambdaQueryWrapper);
        List<OnlCgformField> list2 = this.queryAvailableFields(headId, tbname, true, list, needList);
        StringBuffer stringBuffer = new StringBuffer();
        DataBaseUtils.a(tbname, list2, stringBuffer);
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String string = loginUser.getId();
        List<SysPermissionDataRuleModel> list3 = this.onlAuthDataService.queryUserOnlineAuthData(string, headId);
        if (list3 != null && list3.size() > 0) {
            JeecgDataAutorUtils.installUserInfo(this.sysBaseAPI.getCacheUser(loginUser.getUsername()));
        }
        String string2 = DataBaseUtils.a(list, params, needList, list3);
        String string3 = DataBaseUtils.a(params);
        stringBuffer.append(" where 1=1  " + string2 + string3);
        Object object3 = params.get("column");
        if (object3 != null) {
            String object2 = object3.toString();
            String object = params.get("order").toString();
            if (this.a((String) object2, list)) {
                stringBuffer.append(" ORDER BY " + oConvertUtils.camelToUnderline((String) object2));
                if ("asc".equals(object)) {
                    stringBuffer.append(" asc");
                } else {
                    stringBuffer.append(" desc");
                }
            }
        }
        Integer object2;
        if ((object2 = Integer.valueOf(params.get("pageSize") == null ? 10 : Integer.parseInt(params.get("pageSize").toString()))) == -521) {
            List<Map<String, Object>> object = this.onlCgformFieldMapper.queryListBySql(stringBuffer.toString());
            log.debug("---Online查询sql 不分页 :>>" + stringBuffer.toString());
            if (object == null || object.size() == 0) {
                hashMap.put("total", 0);
                hashMap.put("fieldList", list2);
            } else {
                hashMap.put("total", object.size());
                hashMap.put("fieldList", list2);
                hashMap.put("records", DataBaseUtils.d(object));
            }
        } else {
            int object = params.get("pageNo") == null ? 1 : Integer.parseInt(params.get("pageNo").toString());
            Page page = new Page(((Integer) object).intValue(), object2);
            log.debug("---Online查询sql:>>" + stringBuffer.toString());
            IPage<Map<String, Object>> iPage = this.onlCgformFieldMapper.selectPageBySql((Page<Map<String, Object>>) page, stringBuffer.toString());
            hashMap.put("total", iPage.getTotal());
            hashMap.put("records", DataBaseUtils.d(iPage.getRecords()));
        }
        return hashMap;
    }

    @Override
    public Map<String, Object> queryAutoTreeNoPage(String tbname, String headId, Map<String, Object> params, List<String> needList, String pidField) {
        Object object;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) headId);
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        List list = this.list((Wrapper) lambdaQueryWrapper);
        List<OnlCgformField> list2 = this.queryAvailableFields(headId, tbname, true, list, needList);
        StringBuffer stringBuffer = new StringBuffer();
        DataBaseUtils.a(tbname, list2, stringBuffer);
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String string = loginUser.getId();
        List<SysPermissionDataRuleModel> list3 = this.onlAuthDataService.queryUserOnlineAuthData(string, headId);
        if (list3 != null && list3.size() > 0) {
            JeecgDataAutorUtils.installUserInfo(this.sysBaseAPI.getCacheUser(loginUser.getUsername()));
        }
        String string2 = DataBaseUtils.a(list, params, needList, list3);
        String string3 = DataBaseUtils.a(params);
        stringBuffer.append(" where 1=1  " + string2 + string3);
        Object object2 = params.get("column");
        if (object2 != null) {
            object = object2.toString();
            String arrayList = params.get("order").toString();
            if (this.a((String) object, list)) {
                stringBuffer.append(" ORDER BY " + oConvertUtils.camelToUnderline((String) object));
                if ("asc".equals(arrayList)) {
                    stringBuffer.append(" asc");
                } else {
                    stringBuffer.append(" desc");
                }
            }
        }
        if ((Integer) (object = Integer.valueOf(params.get("pageSize") == null ? 10 : Integer.parseInt(params.get("pageSize").toString()))) == -521) {
            List<Map<String, Object>> arrayList = this.onlCgformFieldMapper.queryListBySql(stringBuffer.toString());
            if ("true".equals(params.get("hasQuery"))) {
                ArrayList<Map<String, Object>> arrayList2 = new ArrayList<Map<String, Object>>();
                for (Map map : arrayList) {
                    String string4 = map.get(pidField).toString();
                    if (string4 != null && !b.equals(string4)) {
                        Map<String, Object> map2 = this.a(string4, tbname, headId, needList, pidField);
                        if (map2 == null || map2.size() <= 0 || arrayList2.contains(map2)) continue;
                        arrayList2.add(map2);
                        continue;
                    }
                    if (arrayList2.contains(map)) continue;
                    arrayList2.add(map);
                }
                arrayList = arrayList2;
            }
            log.debug("---Online查询sql 不分页 :>>" + stringBuffer.toString());
            if (arrayList == null || arrayList.size() == 0) {
                hashMap.put("total", 0);
                hashMap.put("fieldList", list2);
            } else {
                hashMap.put("total", arrayList.size());
                hashMap.put("fieldList", list2);
                hashMap.put("records", DataBaseUtils.d(arrayList));
            }
        } else {
            int arrayList = params.get("pageNo") == null ? 1 : Integer.parseInt(params.get("pageNo").toString());
            Page page = new Page((long) ((Integer) ((Object) arrayList)).intValue(), (long) ((Integer) object).intValue());
            log.debug("---Online查询sql:>>" + stringBuffer.toString());
            IPage<Map<String, Object>> iPage = this.onlCgformFieldMapper.selectPageBySql((Page<Map<String, Object>>) page, stringBuffer.toString());
            hashMap.put("total", iPage.getTotal());
            hashMap.put("records", DataBaseUtils.d(iPage.getRecords()));
        }
        return hashMap;
    }

    private Map<String, Object> a(String string, String string2, String string3, List<String> list, String string4) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("id", string);
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) string3);
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        List list2 = this.list((Wrapper) lambdaQueryWrapper);
        List<OnlCgformField> list3 = this.queryAvailableFields(string3, string2, true, list2, list);
        StringBuffer stringBuffer = new StringBuffer();
        DataBaseUtils.a(string2, list3, stringBuffer);
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String string5 = loginUser.getId();
        List<SysPermissionDataRuleModel> list4 = this.onlAuthDataService.queryUserOnlineAuthData(string5, string3);
        if (list4 != null && list4.size() > 0) {
            JeecgDataAutorUtils.installUserInfo((SysUserCacheInfo) this.sysBaseAPI.getCacheUser(loginUser.getUsername()));
        }
        String string6 = DataBaseUtils.a(list2, hashMap, list, list4);
        stringBuffer.append(" where 1=1  " + string6 + "and id='" + string + "'");
        List<Map<String, Object>> list5 = this.onlCgformFieldMapper.queryListBySql(stringBuffer.toString());
        if (list5 != null && list5.size() > 0) {
            Map<String, Object> map = list5.get(0);
            if (map != null && map.get(string4) != null && !b.equals(map.get(string4))) {
                return this.a(map.get(string4).toString(), string2, string3, list, string4);
            }
            return map;
        }
        return null;
    }

    @Override
    public void saveFormData(String code, String tbname, JSONObject json, boolean isCrazy) {
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) code);
        List list = this.list((Wrapper) lambdaQueryWrapper);
        if (isCrazy) {
            ((OnlCgformFieldMapper) this.baseMapper).executeInsertSQL(DataBaseUtils.c(tbname, list, json));
        } else {
            ((OnlCgformFieldMapper) this.baseMapper).executeInsertSQL(DataBaseUtils.a(tbname, (List<OnlCgformField>) list, json));
        }
    }

    @Override
    public void saveTreeFormData(String code, String tbname, JSONObject json, String hasChildField, String pidField) {
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) code);
        List<OnlCgformField> list = this.list(lambdaQueryWrapper);
        for (OnlCgformField onlCgformField : list) {
            if (hasChildField.equals(onlCgformField.getDbFieldName()) && onlCgformField.getIsShowForm() != 1) {
                onlCgformField.setIsShowForm(1);
                json.put(hasChildField, (Object) b);
                continue;
            }
            if (!pidField.equals(onlCgformField.getDbFieldName()) || !oConvertUtils.isEmpty((Object) json.get((Object) pidField)))
                continue;
            onlCgformField.setIsShowForm(1);
            json.put(pidField, (Object) b);
        }
        Map<String, Object> map = DataBaseUtils.a(tbname, (List<OnlCgformField>) list, json);
        ((OnlCgformFieldMapper) this.baseMapper).executeInsertSQL(map);
        if (!b.equals(json.getString(pidField))) {
            ((OnlCgformFieldMapper) this.baseMapper).editFormData("update " + tbname + " set " + hasChildField + " = '1' where id = '" + json.getString(pidField) + "'");
        }
    }

    @Override
    public void saveFormData(List<OnlCgformField> fieldList, String tbname, JSONObject json) {
        Map<String, Object> map = DataBaseUtils.a(tbname, fieldList, json);
        ((OnlCgformFieldMapper) this.baseMapper).executeInsertSQL(map);
    }

    @Override
    public void editFormData(String code, String tbname, JSONObject json, boolean isCrazy) {
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) code);
        List list = this.list((Wrapper) lambdaQueryWrapper);
        if (isCrazy) {
            ((OnlCgformFieldMapper) this.baseMapper).executeUpdatetSQL(DataBaseUtils.d(tbname, list, json));
        } else {
            ((OnlCgformFieldMapper) this.baseMapper).executeUpdatetSQL(DataBaseUtils.b(tbname, list, json));
        }
    }

    @Override
    public void editTreeFormData(String code, String tbname, JSONObject json, String hasChildField, String pidField) {
        String string = DataBaseUtils.f(tbname);
        String string2 = "select * from " + string + " where id = '" + json.getString("id") + "'";
        Map<String, Object> map = ((OnlCgformFieldMapper) this.baseMapper).queryFormData(string2);
        Map<String, Object> map2 = DataBaseUtils.b(map);
        String string3 = map2.get(pidField).toString();
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) code);
        List list = this.list((Wrapper) lambdaQueryWrapper);
        for (Object object : list) {
            if (!pidField.equals(((OnlCgformField) object).getDbFieldName()) || !oConvertUtils.isEmpty((Object) json.get((Object) pidField)))
                continue;
            ((OnlCgformField) object).setIsShowForm(1);
            json.put(pidField, (Object) b);
        }
        Map<String, Object> map3 = DataBaseUtils.b(tbname, list, json);
        ((OnlCgformFieldMapper) this.baseMapper).executeUpdatetSQL(map3);
        if (!string3.equals(json.getString(pidField))) {
            Integer n2;
            Object object;
            if (!(b.equals(string3) || (n2 = ((OnlCgformFieldMapper) this.baseMapper).queryCountBySql((String) (object = "select count(*) from " + string + " where " + pidField + " = '" + string3 + "'"))) != null && n2 != 0)) {
                ((OnlCgformFieldMapper) this.baseMapper).editFormData("update " + string + " set " + hasChildField + " = '0' where id = '" + string3 + "'");
            }
            if (!b.equals(json.getString(pidField))) {
                ((OnlCgformFieldMapper) this.baseMapper).editFormData("update " + string + " set " + hasChildField + " = '1' where id = '" + json.getString(pidField) + "'");
            }
        }
    }

    @Override
    public Map<String, Object> queryFormData(String code, String tbname, String id) {
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) code);
        lambdaQueryWrapper.eq(OnlCgformField::getIsShowForm, (Object) 1);
        List list = this.list((Wrapper) lambdaQueryWrapper);
        String string = DataBaseUtils.a(tbname, (List<OnlCgformField>) list, id);
        return this.onlCgformFieldMapper.queryFormData(string);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteAutoListMainAndSub(OnlCgformHead head, String ids) {
        if (head.getTableType() == 2) {
            String string = head.getId();
            String string2 = head.getTableName();
            String string3 = "tableName";
            String string4 = "linkField";
            String string5 = "linkValueStr";
            String string6 = "mainField";
            ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
            if (oConvertUtils.isNotEmpty((Object) head.getSubTableStr())) {
                Map<String, Object> map;
                Object object;
                for (Object object2 : head.getSubTableStr().split(",")) {
                    LambdaQueryWrapper lambdaQueryWrapper;
                    List object3;
                    OnlCgformHead onlCgformHead = this.cgformHeadMapper.selectOne(new LambdaQueryWrapper<OnlCgformHead>().eq(OnlCgformHead::getTableName, object2));
                    if (onlCgformHead == null || (object3 = this.list(new LambdaQueryWrapper<OnlCgformField>()
                            .eq(OnlCgformField::getCgformHeadId, (Object) onlCgformHead.getId())
                            .eq(OnlCgformField::getMainTable, (Object) head.getTableName()))
                    ) == null || object3.size() == 0) continue;
                    object = (OnlCgformField) object3.get(0);
                    map = new HashMap<String, Object>();
                    map.put(string4, ((OnlCgformField) object).getDbFieldName());
                    map.put(string6, ((OnlCgformField) object).getMainField());
                    map.put(string3, object2);
                    map.put(string5, "");
                    arrayList.add(map);
                }
                LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
                lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) string);
                List list = this.list((Wrapper) lambdaQueryWrapper);
                String[] arrstring = ids.split(",");
                for (String string7 : arrstring) {
                    object = DataBaseUtils.a(string2, (List<OnlCgformField>) list, string7);
                    map = this.onlCgformFieldMapper.queryFormData((String) object);
                    ArrayList arrayList2 = new ArrayList();
                    for (Map map2 : arrayList) {
                        Object object2 = map.get(((String) map2.get(string6)).toLowerCase());
                        if (object2 == null) {
                            object2 = map.get(((String) map2.get(string6)).toUpperCase());
                        }
                        if (object2 == null) continue;
                        String string8 = (String) map2.get(string5) + String.valueOf(object2) + ",";
                        map2.put(string5, string8);
                    }
                }
                for (Map map3 : arrayList) {
                    this.deleteAutoList((String) map3.get(string3), (String) map3.get(string4), (String) map3.get(string5));
                }
            }
            this.deleteAutoListById(head.getTableName(), ids);
        }
    }

    @Override
    public void deleteAutoListById(String tbname, String ids) {
        this.deleteAutoList(tbname, "id", ids);
    }

    @Override
    public void deleteAutoList(String tbname, String linkField, String linkValue) {
        if (linkValue != null && !"".equals(linkValue)) {
            String[] arrstring = linkValue.split(",");
            StringBuffer stringBuffer = new StringBuffer();
            for (String string : arrstring) {
                if (string == null || "".equals(string)) continue;
                stringBuffer.append("'" + string + "',");
            }
            String string = stringBuffer.toString();
            String string2 = "DELETE FROM " + DataBaseUtils.f(tbname) + " where " + linkField + " in(" + string.substring(0, string.length() - 1) + ")";
            log.debug("--删除sql-->" + string2);
            this.onlCgformFieldMapper.deleteAutoList(string2);
        }
    }

    @Override
    public List<Map<String, String>> getAutoListQueryInfo(String code) {
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) code);
        lambdaQueryWrapper.eq(OnlCgformField::getIsQuery, (Object) 1);
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        List<OnlCgformField> list = this.list(lambdaQueryWrapper);
        ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
        int n2 = 0;
        for (OnlCgformField onlCgformField : list) {
            String string;
            String[] arrstring;
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("label", onlCgformField.getDbFieldTxt());
            hashMap.put("field", onlCgformField.getDbFieldName());
            hashMap.put("mode", onlCgformField.getQueryMode());
            if ("1".equals(onlCgformField.getQueryConfigFlag())) {
                hashMap.put("config", "1");
                hashMap.put("view", onlCgformField.getQueryShowType());
                hashMap.put("defValue", onlCgformField.getQueryDefVal());
                if ("cat_tree".equals(onlCgformField.getFieldShowType())) {
                    hashMap.put("pcode", onlCgformField.getQueryDictField());
                } else if ("sel_tree".equals(onlCgformField.getFieldShowType())) {
                    arrstring = onlCgformField.getQueryDictText().split(",");
                    string = onlCgformField.getQueryDictTable() + "," + arrstring[2] + "," + arrstring[0];
                    hashMap.put("dict", string);
                    hashMap.put("pidField", arrstring[1]);
                    hashMap.put("hasChildField", arrstring[3]);
                    hashMap.put("pidValue", onlCgformField.getQueryDictField());
                } else {
                    hashMap.put("dictTable", onlCgformField.getQueryDictTable());
                    hashMap.put("dictCode", onlCgformField.getQueryDictField());
                    hashMap.put("dictText", onlCgformField.getQueryDictText());
                }
            } else {
                hashMap.put("view", onlCgformField.getFieldShowType());
                if ("cat_tree".equals(onlCgformField.getFieldShowType())) {
                    hashMap.put("pcode", onlCgformField.getDictField());
                } else if ("sel_tree".equals(onlCgformField.getFieldShowType())) {
                    arrstring = onlCgformField.getDictText().split(",");
                    string = onlCgformField.getDictTable() + "," + arrstring[2] + "," + arrstring[0];
                    hashMap.put("dict", string);
                    hashMap.put("pidField", arrstring[1]);
                    hashMap.put("hasChildField", arrstring[3]);
                    hashMap.put("pidValue", onlCgformField.getDictField());
                } else if ("popup".equals(onlCgformField.getFieldShowType())) {
                    hashMap.put("dictTable", onlCgformField.getDictTable());
                    hashMap.put("dictCode", onlCgformField.getDictField());
                    hashMap.put("dictText", onlCgformField.getDictText());
                } else if ("sel_search".equals(onlCgformField.getFieldShowType())) {
                    hashMap.put("dictTable", onlCgformField.getDictTable());
                    hashMap.put("dictCode", onlCgformField.getDictField());
                    hashMap.put("dictText", onlCgformField.getDictText());
                }
                hashMap.put("mode", onlCgformField.getQueryMode());
            }
            if (++n2 > 2) {
                hashMap.put("hidden", "1");
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    @Override
    public List<OnlCgformField> queryFormFields(String code, boolean isform) {
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) code);
        if (isform) {
            lambdaQueryWrapper.eq(OnlCgformField::getIsShowForm, (Object) 1);
        }
        return this.list((Wrapper) lambdaQueryWrapper);
    }

    @Override
    public List<OnlCgformField> queryFormFieldsByTableName(String tableName) {
        OnlCgformHead onlCgformHead = this.cgformHeadMapper.selectOne(new LambdaQueryWrapper<OnlCgformHead>().eq(OnlCgformHead::getTableName, (Object) tableName));
        if (onlCgformHead != null) {
            LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
            lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) onlCgformHead.getId());
            return this.list(lambdaQueryWrapper);
        }
        return null;
    }

    @Override
    public OnlCgformField queryFormFieldByTableNameAndField(String tableName, String fieldName) {
        OnlCgformHead onlCgformHead = (OnlCgformHead) this.cgformHeadMapper.selectOne(new LambdaQueryWrapper<OnlCgformHead>().eq(OnlCgformHead::getTableName, (Object) tableName));
        if (onlCgformHead != null) {
            LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
            lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) onlCgformHead.getId());
            lambdaQueryWrapper.eq(OnlCgformField::getDbFieldName, (Object) fieldName);
            if (this.list((Wrapper) lambdaQueryWrapper) != null && this.list((Wrapper) lambdaQueryWrapper).size() > 0) {
                return (OnlCgformField) this.list((Wrapper) lambdaQueryWrapper).get(0);
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> queryFormData(List<OnlCgformField> fieldList, String tbname, String id) {
        String string = DataBaseUtils.a(tbname, fieldList, id);
        return this.onlCgformFieldMapper.queryFormData(string);
    }

    @Override
    public List<Map<String, Object>> querySubFormData(List<OnlCgformField> fieldList, String tbname, String linkField, String value) {
        String string = DataBaseUtils.a(tbname, fieldList, linkField, value);
        return this.onlCgformFieldMapper.queryListData(string);
    }

    @Override
    public IPage<Map<String, Object>> selectPageBySql(Page<Map<String, Object>> page, String sql) {
        return ((OnlCgformFieldMapper) this.baseMapper).selectPageBySql(page, sql);
    }

    @Override
    public List<String> selectOnlineHideColumns(String tbname) {
        String string = "online:" + tbname + ":%";
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String string2 = loginUser.getId();
        List<String> list = ((OnlCgformFieldMapper) this.baseMapper).selectOnlineHideColumns(string2, string);
        return this.a(list);
    }

    @Override
    public List<OnlCgformField> queryAvailableFields(String cgFormId, String tbname, String taskId, boolean isList) {
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) cgFormId);
        if (isList) {
            lambdaQueryWrapper.eq(OnlCgformField::getIsShowList, (Object) 1);
        } else {
            lambdaQueryWrapper.eq(OnlCgformField::getIsShowForm, (Object) 1);
        }
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        List<OnlCgformField> list2 = this.list(lambdaQueryWrapper);
        String string = "online:" + tbname + "%";
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String string2 = loginUser.getId();
        ArrayList<String> arrayList = new ArrayList<String>();
        List<String> list;

        if (oConvertUtils.isEmpty((Object) taskId)) {
            list = this.onlAuthPageService.queryHideCode(string2, cgFormId, isList);
            if (list != null && list.size() != 0 && list.get(0) != null) {
                arrayList.addAll(list);
            }
        } else if (oConvertUtils.isNotEmpty(taskId) && (list = this.baseMapper.selectFlowAuthColumns(tbname, taskId, "1")) != null && list.size() > 0 && list.get(0) != null) {
            arrayList.addAll(list);
        }

        if (arrayList.size() == 0) {
            return list2;
        }
        List<OnlCgformField> listt = new ArrayList<>();
        for (int i2 = 0; i2 < list2.size(); ++i2) {
            OnlCgformField onlCgformField = (OnlCgformField) list2.get(i2);
            if (!this.b(onlCgformField.getDbFieldName(), arrayList)) continue;
            listt.add(onlCgformField);
        }
        return listt;
    }

    @Override
    public List<String> queryDisabledFields(String tbname) {
        String string = "online:" + tbname + "%";
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String string2 = loginUser.getId();
        List<String> list = ((OnlCgformFieldMapper) this.baseMapper).selectOnlineDisabledColumns(string2, string);
        return this.a(list);
    }

    @Override
    public List<String> queryDisabledFields(String tbname, String taskId) {
        if (oConvertUtils.isEmpty((Object) taskId)) {
            return null;
        }
        List<String> list = ((OnlCgformFieldMapper) this.baseMapper).selectFlowAuthColumns(tbname, taskId, "2");
        return this.a(list);
    }

    private List<String> a(List<String> list) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if (list == null || list.size() == 0 || list.get(0) == null) {
            return arrayList;
        }
        for (String string : list) {
            String string2;
            if (oConvertUtils.isEmpty((Object) string) || oConvertUtils.isEmpty((Object) (string2 = string.substring(string.lastIndexOf(":") + 1))))
                continue;
            arrayList.add(string2);
        }
        return arrayList;
    }

    @Override
    public List<OnlCgformField> queryAvailableFields(String tbname, boolean isList, List<OnlCgformField> List2, List<String> needList) {
        String string = "online:" + tbname + "%";
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String string2 = loginUser.getId();
        List<String> list = ((OnlCgformFieldMapper) this.baseMapper).selectOnlineHideColumns(string2, string);
        return this.a(list, isList, List2, needList);
    }

    @Override
    public List<OnlCgformField> queryAvailableFields(String cgformId, String tbname, boolean isList, List<OnlCgformField> List2, List<String> needList) {
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String string = loginUser.getId();
        List<String> list = this.onlAuthPageService.queryListHideColumn(string, cgformId);
        return this.a(list, isList, List2, needList);
    }

    private List<OnlCgformField> a(List<String> list, boolean bl, List<OnlCgformField> list2, List<String> list3) {
        ArrayList<OnlCgformField> arrayList = new ArrayList<OnlCgformField>();
        boolean bl2 = true;
        if (list == null || list.size() == 0 || list.get(0) == null) {
            bl2 = false;
        }
        for (OnlCgformField onlCgformField : list2) {
            String string = onlCgformField.getDbFieldName();
            if (list3 != null && list3.contains(string)) {
                onlCgformField.setIsQuery(1);
                arrayList.add(onlCgformField);
                continue;
            }
            if (bl) {
                if (onlCgformField.getIsShowList() != 1) {
                    if (!oConvertUtils.isNotEmpty((Object) onlCgformField.getMainTable()) || !oConvertUtils.isNotEmpty((Object) onlCgformField.getMainField()))
                        continue;
                    arrayList.add(onlCgformField);
                    continue;
                }
            } else if (onlCgformField.getIsShowForm() != 1) continue;
            if (bl2) {
                if (!this.b(string, list)) continue;
                arrayList.add(onlCgformField);
                continue;
            }
            arrayList.add(onlCgformField);
        }
        return arrayList;
    }

    private boolean b(String string, List<String> list) {
        boolean bl = true;
        for (int i2 = 0; i2 < list.size(); ++i2) {
            String string2;
            String string3 = list.get(i2);
            if (oConvertUtils.isEmpty((Object) string3) || oConvertUtils.isEmpty((Object) (string2 = string3.substring(string3.lastIndexOf(":") + 1))) || !string2.equals(string))
                continue;
            bl = false;
        }
        return bl;
    }

    public boolean a(String string, List<OnlCgformField> list) {
        boolean bl = false;
        for (OnlCgformField onlCgformField : list) {
            if (!oConvertUtils.camelToUnderline((String) string).equals(onlCgformField.getDbFieldName())) continue;
            bl = true;
            break;
        }
        return bl;
    }

    @Override
    public void executeInsertSQL(Map<String, Object> params) {
        ((OnlCgformFieldMapper) this.baseMapper).executeInsertSQL(params);
    }

    @Override
    public void executeUpdatetSQL(Map<String, Object> params) {
        ((OnlCgformFieldMapper) this.baseMapper).executeUpdatetSQL(params);
    }

    @Override
    public List<TreeModel> queryDataListByLinkDown(LinkDown linkDown) {
        return ((OnlCgformFieldMapper) this.baseMapper).queryDataListByLinkDown(linkDown);
    }

    @Override
    public void updateTreeNodeNoChild(String tableName, String filed, String id) {
        Map<String, Object> map = DataBaseUtils.a(tableName, filed, id);
        ((OnlCgformFieldMapper) this.baseMapper).executeUpdatetSQL(map);
    }

    @Override
    public String queryTreeChildIds(OnlCgformHead head, String ids) {
        String string = head.getTreeParentIdField();
        String string2 = head.getTableName();
        String[] arrstring = ids.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        for (String string3 : arrstring) {
            if (string3 == null || stringBuffer.toString().contains(string3)) continue;
            if (stringBuffer.toString().length() > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(string3);
            this.a(string3, string, string2, stringBuffer);
        }
        return stringBuffer.toString();
    }

    @Override
    public String queryTreePids(OnlCgformHead head, String ids) {
        String[] arrstring;
        String string = head.getTreeParentIdField();
        String string2 = head.getTableName();
        StringBuffer stringBuffer = new StringBuffer();
        for (String string3 : arrstring = ids.split(",")) {
            if (string3 == null) continue;
            String string4 = DataBaseUtils.f(string2);
            String string5 = "select * from " + string4 + " where id = '" + string3 + "'";
            Map<String, Object> map = ((OnlCgformFieldMapper) this.baseMapper).queryFormData(string5);
            Map<String, Object> map2 = DataBaseUtils.b(map);
            String string6 = map2.get(string).toString();
            String string7 = "select * from " + DataBaseUtils.f(string2) + " where " + string + "= '" + string6 + "' and id not in(" + ids + ")";
            List<Map<String, Object>> list = this.onlCgformFieldMapper.queryListBySql(string7);
            if (list != null && list.size() != 0 || Arrays.asList(arrstring).contains(string6) || stringBuffer.toString().contains(string6))
                continue;
            stringBuffer.append(string6).append(",");
        }
        return stringBuffer.toString();
    }

    @Override
    public String queryForeignKey(String cgFormId, String mainTable) {
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object) cgFormId);
        lambdaQueryWrapper.eq(OnlCgformField::getMainTable, (Object) mainTable);
        List list = this.list((Wrapper) lambdaQueryWrapper);
        if (list != null && list.size() > 0) {
            return ((OnlCgformField) list.get(0)).getMainField();
        }
        return null;
    }

    private StringBuffer a(String string, String string2, String string3, StringBuffer stringBuffer) {
        String string4 = "select * from " + DataBaseUtils.f(string3) + " where " + string2 + "= '" + string + "'";
        List<Map<String, Object>> list = this.onlCgformFieldMapper.queryListBySql(string4);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                Map<String, Object> map2 = DataBaseUtils.b(map);
                if (!stringBuffer.toString().contains(map2.get("id").toString())) {
                    stringBuffer.append(",").append(map2.get("id"));
                }
                this.a(map2.get("id").toString(), string2, string3, stringBuffer);
            }
        }
        return stringBuffer;
    }

}

