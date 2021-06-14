/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  cn.hutool.core.util.ReUtil
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
 *  com.baomidou.mybatisplus.core.metadata.IPage
 *  com.baomidou.mybatisplus.extension.plugins.pagination.Page
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  org.apache.commons.lang.StringUtils
 *  org.jeecg.common.api.vo.Result
 *  org.jeecg.common.exception.JeecgBootException
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.system.query.QueryGenerator
 *  org.jeecg.common.system.vo.DictModel
 *  org.jeecg.common.system.vo.DynamicDataSourceModel
 *  org.jeecg.common.util.dynamic.db.DataSourceCachePool
 *  org.jeecg.common.util.dynamic.db.DynamicDBUtil
 *  org.jeecg.common.util.dynamic.db.SqlUtils
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.cache.annotation.Cacheable
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package org.jeecg.modules.online.cgreport.service.impl;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.DynamicDataSourceModel;
import org.jeecg.common.util.dynamic.db.DataSourceCachePool;
import org.jeecg.common.util.dynamic.db.DynamicDBUtil;
import org.jeecg.common.util.dynamic.db.SqlUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.enums.DataBaseEnum;
import org.jeecg.modules.online.cgform.util.b;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportHeadMapper;
import org.jeecg.modules.online.cgreport.model.OnlCgreportModel;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportHeadService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.jeecg.modules.online.cgreport.util.SqlUtil;
import org.jeecg.modules.online.config.b.d;
import org.jeecg.modules.online.config.exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "onlCgreportHeadServiceImpl")
@Slf4j
public class OnlCgreportHeadServiceImpl extends ServiceImpl<OnlCgreportHeadMapper, OnlCgreportHead>
        implements IOnlCgreportHeadService {
    @Autowired
    private IOnlCgreportParamService onlCgreportParamService;
    @Autowired
    private IOnlCgreportItemService onlCgreportItemService;
    @Autowired
    private OnlCgreportHeadMapper mapper;
    @Autowired
    private ISysBaseAPI sysBaseAPI;

    public Map<String, Object> executeSelectSql(String sql, final String onlCgreportHeadId, JSONObject params) throws SQLException {
        String databaseType = null;
        try {
            databaseType = d.getDatabaseType();
        } catch (DBException ex) {
            ex.printStackTrace();
        }
        LambdaQueryWrapper<OnlCgreportParam> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgreportParam>()
                .eq(OnlCgreportParam::getCgrheadId, onlCgreportHeadId);
        List<OnlCgreportParam> list = this.onlCgreportParamService.list(lambdaQueryWrapper);
        if (list != null && list.size() > 0) {
            for (final OnlCgreportParam onlCgreportParam : list) {
                final Object value = params.get("self_" + onlCgreportParam.getParamName());
                String replacement = "";
                if (value != null) {
                    replacement = value.toString();
                } else if (value == null && oConvertUtils.isNotEmpty((Object) onlCgreportParam.getParamValue())) {
                    replacement = onlCgreportParam.getParamValue();
                }
                final String string = "${" + onlCgreportParam.getParamName() + "}";
                if (sql.indexOf(string) > 0) {
                    if (replacement != null && replacement.startsWith("'") && replacement.endsWith("'")) {
                        replacement = replacement.substring(1, replacement.length() - 1);
                    }
                    sql = sql.replace(string, replacement);
                } else {
                    if (!oConvertUtils.isNotEmpty((Object) replacement)) {
                        continue;
                    }
                    params.put("popup_param_pre__" + onlCgreportParam.getParamName(), replacement);
                }
            }
        }
        Page page = new Page(oConvertUtils.getInt(params.get("pageNo"), 1), oConvertUtils.getInt(params.get("pageSize"), 10));


        LambdaQueryWrapper<OnlCgreportItem> lambdaQueryWrapper2 = new LambdaQueryWrapper<OnlCgreportItem>()
                .eq(OnlCgreportItem::getCgrheadId, onlCgreportHeadId);

        List<String> list2 = new ArrayList<>();
        for (final String s : params.keySet().toArray(new String[0])) {
            if (s.startsWith("force_")) {
                final String substring = s.substring("force_".length());
                list2.add(substring);
                params.put(substring, params.get(s));
            }
        }
        List<OnlCgreportItem> list3;
        if (list2.size() > 0) {
            lambdaQueryWrapper2.in(OnlCgreportItem::getFieldName, list2);
            list3 = this.onlCgreportItemService.list(lambdaQueryWrapper2);
            list3.forEach(onlCgreportItem -> onlCgreportItem.setSearch(true));
        } else {
            lambdaQueryWrapper2.eq(OnlCgreportItem::isSearch, true);
            list3 = this.onlCgreportItemService.list(lambdaQueryWrapper2);
        }
        final String a = org.jeecg.modules.online.cgreport.util.a.a(list3, params, "jeecg_rp_temp.", null);
        if (ReUtil.contains(" order\\s+by ", (CharSequence) sql.toLowerCase()) && "SQLSERVER".equalsIgnoreCase(databaseType)) {
            throw new JeecgBootException("SqlServer\u4e0d\u652f\u6301SQL\u5185\u6392\u5e8f!");
        }
        String s2 = SqlUtil.b("select * from (" + sql + ") jeecg_rp_temp  where 1=1 " + a);
        final Object value2 = params.get("column");
        if (value2 != null) {
            s2 = s2 + " order by jeecg_rp_temp." + value2 + " " + params.get("order").toString();
        }
        log.info("\u62a5\u8868\u67e5\u8be2sql=>\r\n" + s2);
        IPage selectPageBySql;
        if (Boolean.parseBoolean(String.valueOf(params.get("getAll")))) {
            final List<Map<String, Object>> executeSelect = this.mapper.executeSelect(s2);
            selectPageBySql = new Page();
            selectPageBySql.setRecords(executeSelect);

            selectPageBySql.setTotal(executeSelect.size());
        } else {
            selectPageBySql = this.mapper.selectPageBySql(page, s2);
        }
        JSONObject result = new JSONObject();

        result.put("total", selectPageBySql.getTotal());
        result.put("records", b.d(selectPageBySql.getRecords()));
        return result;
    }

    @Override
    public Map<String, Object> executeSelectSqlDynamic(String dbKey, String sql, Map<String, Object> params, String onlCgreportHeadId) {
        String string;
        Object object;
        Object object22;
        DynamicDataSourceModel dynamicDataSourceModel = DataSourceCachePool.getCacheDynamicDataSourceModel((String) dbKey);
        String string2 = (String) params.get("order");
        String string3 = (String) params.get("column");
        int n2 = oConvertUtils.getInt((Object) params.get("pageNo"), (int) 1);
        int n3 = oConvertUtils.getInt((Object) params.get("pageSize"), (int) 10);
        log.info("\u3010Online\u591a\u6570\u636e\u6e90\u903b\u8f91\u3011\u62a5\u8868\u67e5\u8be2\u53c2\u6570params: " + JSON.toJSONString(params));
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgreportParam::getCgrheadId, (Object) onlCgreportHeadId);
        List list = this.onlCgreportParamService.list((Wrapper) lambdaQueryWrapper);
        if (list != null && list.size() > 0) {
            for (Object object22 : list) {
                object = params.get("self_" + ((OnlCgreportParam) object22).getParamName());
                string = "";
                if (object != null) {
                    string = object.toString();
                } else if (object == null && oConvertUtils.isNotEmpty((Object) ((OnlCgreportParam) object22).getParamValue())) {
                    string = ((OnlCgreportParam) object22).getParamValue();
                }
                sql = sql.replace("${" + ((OnlCgreportParam) object22).getParamName() + "}", string);
            }
        }
        LambdaQueryWrapper<OnlCgreportItem> lambdaQueryWrapper2 = new LambdaQueryWrapper<OnlCgreportItem>()
                .eq(OnlCgreportItem::getCgrheadId, onlCgreportHeadId)
                .eq(OnlCgreportItem::isSearch, true);

        object22 = this.onlCgreportItemService.list(lambdaQueryWrapper2);
        if (ReUtil.contains((String) " order\\s+by ", (CharSequence) sql.toLowerCase()) && "3".equalsIgnoreCase(dynamicDataSourceModel.getDbType())) {
            throw new JeecgBootException("SqlServer\u4e0d\u652f\u6301SQL\u5185\u6392\u5e8f!");
        }
        object = "jeecg_rp_temp.";
        string = DataBaseEnum.getDataBaseNameByValue(dynamicDataSourceModel.getDbType());
        String string4 = org.jeecg.modules.online.cgreport.util.a.a((List<OnlCgreportItem>) object22, params, (String) object, string);
        String string5 = "select * from (" + sql + ") jeecg_rp_temp  where 1=1 " + string4;
        string5 = SqlUtil.b(string5);
        String string6 = SqlUtils.getCountSql((String) string5);
        Object object3 = params.get("column");
        if (object3 != null) {
            string5 = string5 + " order by jeecg_rp_temp." + object3.toString() + " " + params.get("order").toString();
        }
        String string7 = string5;
        if (!Boolean.valueOf(String.valueOf(params.get("getAll"))).booleanValue()) {
            string7 = SqlUtils.createPageSqlByDBType((String) dynamicDataSourceModel.getDbType(), (String) string5, (int) n2, (int) n3);
        }
        log.info("\u591a\u6570\u636e\u6e90 \u62a5\u8868\u67e5\u8be2sql=>querySql: " + string5);
        log.info("\u591a\u6570\u636e\u6e90 \u62a5\u8868\u67e5\u8be2sql=>pageSQL: " + string7);
        log.info("\u591a\u6570\u636e\u6e90 \u62a5\u8868\u67e5\u8be2sql=>countSql: " + string6);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        Map map = (Map) DynamicDBUtil.findOne((String) dbKey, (String) string6, (Object[]) new Object[0]);
        hashMap.put("total", map.get("total"));
        List list2 = DynamicDBUtil.findList((String) dbKey, (String) string7, (Object[]) new Object[0]);
        hashMap.put("records", b.d(list2));
        return hashMap;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result<?> editAll(OnlCgreportModel values) {
        OnlCgreportHead onlCgreportHead = values.getHead();
        OnlCgreportHead onlCgreportHead2 = (OnlCgreportHead) super.getById((Serializable) ((Object) onlCgreportHead.getId()));
        if (onlCgreportHead2 == null) {
            return Result.error((String) "\u672a\u627e\u5230\u5bf9\u5e94\u5b9e\u4f53");
        }
        super.updateById((Object) onlCgreportHead);
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgreportItem::getCgrheadId, (Object) onlCgreportHead.getId());
        this.onlCgreportItemService.remove((Wrapper) lambdaQueryWrapper);
        LambdaQueryWrapper lambdaQueryWrapper2 = new LambdaQueryWrapper();
        lambdaQueryWrapper2.eq(OnlCgreportParam::getCgrheadId, (Object) onlCgreportHead.getId());
        this.onlCgreportParamService.remove((Wrapper) lambdaQueryWrapper2);
        for (OnlCgreportParam serializable : values.getParams()) {
            serializable.setCgrheadId(onlCgreportHead.getId());
        }
        for (OnlCgreportItem onlCgreportItem : values.getItems()) {
            onlCgreportItem.setFieldName(onlCgreportItem.getFieldName().trim().toLowerCase());
            onlCgreportItem.setCgrheadId(onlCgreportHead.getId());
        }
        this.onlCgreportItemService.saveBatch(values.getItems());
        this.onlCgreportParamService.saveBatch(values.getParams());
        return Result.ok((String) "\u5168\u90e8\u4fee\u6539\u6210\u529f");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result<?> delete(String id) {
        boolean bl = super.removeById((Serializable) ((Object) id));
        if (bl) {
            LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(OnlCgreportItem::getCgrheadId, (Object) id);
            this.onlCgreportItemService.remove((Wrapper) lambdaQueryWrapper);
            LambdaQueryWrapper lambdaQueryWrapper2 = new LambdaQueryWrapper();
            lambdaQueryWrapper2.eq(OnlCgreportParam::getCgrheadId, (Object) id);
            this.onlCgreportParamService.remove((Wrapper) lambdaQueryWrapper2);
        }
        return Result.ok((String) "\u5220\u9664\u6210\u529f");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result<?> bathDelete(String[] ids) {
        for (String string : ids) {
            boolean bl = super.removeById((Serializable) ((Object) string));
            if (!bl) continue;
            LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(OnlCgreportItem::getCgrheadId, (Object) string);
            this.onlCgreportItemService.remove((Wrapper) lambdaQueryWrapper);
            LambdaQueryWrapper lambdaQueryWrapper2 = new LambdaQueryWrapper();
            lambdaQueryWrapper2.eq(OnlCgreportParam::getCgrheadId, (Object) string);
            this.onlCgreportParamService.remove((Wrapper) lambdaQueryWrapper2);
        }
        return Result.ok((String) "\u5220\u9664\u6210\u529f");
    }

    @Override
    public List<String> getSqlFields(String sql, String dbKey) throws SQLException {
        List<String> list = null;
        list = StringUtils.isNotBlank((String) dbKey) ? this.a(sql, dbKey) : this.a(sql, null);
        return list;
    }

    @Override
    public List<String> getSqlParams(String sql) {
        if (oConvertUtils.isEmpty((Object) sql)) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        String string = "\\$\\{\\w+\\}";
        Pattern pattern = Pattern.compile(string);
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            String string2 = matcher.group();
            arrayList.add(string2.substring(string2.indexOf("{") + 1, string2.indexOf("}")));
        }
        return arrayList;
    }

    private List<String> a(String string, String string2) throws SQLException {
        Set set;
        if (oConvertUtils.isEmpty((Object) string)) {
            return null;
        }
        string = string.replace("=", " = ");
        if ((string = string.trim()).endsWith(";")) {
            string = string.substring(0, string.length() - 1);
        }
        string = QueryGenerator.convertSystemVariables((String) string);
        string = SqlUtil.a(string);
        if (StringUtils.isNotBlank((String) string2)) {
            a.info("parse sql : " + string);
            DynamicDataSourceModel dynamicDataSourceModel = DataSourceCachePool.getCacheDynamicDataSourceModel((String) string2);
            if (ReUtil.contains((String) " order\\s+by ", (CharSequence) string.toLowerCase()) && "3".equalsIgnoreCase(dynamicDataSourceModel.getDbType())) {
                throw new JeecgBootException("SqlServer\u4e0d\u652f\u6301SQL\u5185\u6392\u5e8f!");
            }
            if ("1".equals(dynamicDataSourceModel.getDbType())) {
                string = "SELECT * FROM (" + string + ") temp LIMIT 1";
            } else if ("2".equals(dynamicDataSourceModel.getDbType())) {
                string = "SELECT * FROM (" + string + ") temp WHERE ROWNUM <= 1";
            } else if ("3".equals(dynamicDataSourceModel.getDbType())) {
                string = "SELECT TOP 1 * FROM (" + string + ") temp";
            }
            a.info("parse sql with page : " + string);
            Map map = (Map) DynamicDBUtil.findOne((String) string2, (String) string, (Object[]) new Object[0]);
            if (map == null) {
                throw new JeecgBootException("\u8be5\u62a5\u8868sql\u6ca1\u6709\u6570\u636e");
            }
            set = map.keySet();
        } else {
            a.info("parse sql: " + string);
            String string3 = null;
            try {
                string3 = d.getDatabaseType();
            } catch (DBException dBException) {
                dBException.printStackTrace();
            }
            if (ReUtil.contains((String) " order\\s+by ", (CharSequence) string.toLowerCase()) && "SQLSERVER".equalsIgnoreCase(string3)) {
                throw new JeecgBootException("SqlServer\u4e0d\u652f\u6301SQL\u5185\u6392\u5e8f!");
            }
            IPage<Map<String, Object>> iPage = this.mapper.selectPageBySql((Page<Map<String, Object>>) new Page(1L, 1L), string);
            List list = iPage.getRecords();
            if (list.size() < 1) {
                throw new JeecgBootException("\u8be5\u62a5\u8868sql\u6ca1\u6709\u6570\u636e");
            }
            set = ((Map) list.get(0)).keySet();
        }
        if (set != null) {
            set.remove("ROW_ID");
        }
        return new ArrayList<String>(set);
    }

    @Override
    public Map<String, Object> queryCgReportConfig(String reportId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>(0);
        Map<String, Object> map = this.mapper.queryCgReportMainConfig(reportId);
        List<Map<String, Object>> list = this.mapper.queryCgReportItems(reportId);
        List<OnlCgreportParam> list2 = this.mapper.queryCgReportParams(reportId);
        if (d.a()) {
            hashMap.put("main", b.b(map));
            hashMap.put("items", b.d(list));
        } else {
            hashMap.put("main", map);
            hashMap.put("items", list);
        }
        hashMap.put("params", list2);
        return hashMap;
    }

    @Override
    public List<Map<?, ?>> queryByCgReportSql(String sql, Map params, Map paramData, int pageNo, int pageSize) {
        String string = SqlUtil.a(sql, params);
        List<Map<?, ?>> list = null;
        if (paramData != null && paramData.size() == 0) {
            paramData = null;
        }
        if (pageNo == -1 && pageSize == -1) {
            list = this.mapper.executeSelete(string);
        } else {
            Page page = new Page((long) pageNo, (long) pageSize);
            IPage<Map<String, Object>> iPage = this.mapper.selectPageBySql((Page<Map<String, Object>>) page, string);
            if (iPage.getRecords() != null && iPage.getRecords().size() > 0) {
                list.addAll(iPage.getRecords());
            }
        }
        return list;
    }

    @Override
    public List<DictModel> queryDictSelectData(String sql, String keyword) {
        List list;
        Object object;
        List<Object> list2 = new ArrayList<DictModel>();
        Page page = new Page();
        page.setSearchCount(false);
        page.setCurrent(1L);
        page.setSize(10L);
        sql = sql.trim();
        int n2 = sql.lastIndexOf(";");
        if (n2 == sql.length() - 1) {
            sql = sql.substring(0, n2);
        }
        if (keyword != null && !"".equals(keyword)) {
            object = " like '%" + keyword + "%'";
            sql = "select * from (" + sql + ") t where t.value " + object + " or " + "t.text" + object;
        }
        if ((list = (object = ((OnlCgreportHeadMapper) this.baseMapper).selectPageBySql((Page<Map<String, Object>>) page, sql)).getRecords()) != null && list.size() != 0) {
            String string = JSON.toJSONString((Object) list);
            list2 = JSON.parseArray((String) string, DictModel.class);
        }
        return list2;
    }

    @Override
    @Cacheable(value = {"sys:cache:online:rp"}, key = "'column-'+#code+'-'+#queryDict")
    public Map<String, Object> queryColumnInfo(String code, boolean queryDict) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        QueryWrapper queryWrapper = new QueryWrapper();
        ((QueryWrapper) ((QueryWrapper) queryWrapper.eq((Object) "cgrhead_id", (Object) code)).eq((Object) "is_show", (Object) 1)).orderByAsc((Object) "order_num");
        List list = this.onlCgreportItemService.list((Wrapper) queryWrapper);
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        HashMap<String, JSONObject> hashMap2 = new HashMap<String, JSONObject>();
        boolean bl = false;
        for (OnlCgreportItem onlCgreportItem : list) {
            Object object;
            String string;
            String string2;
            JSONObject jSONObject = new JSONObject(4);
            jSONObject.put("title", (Object) onlCgreportItem.getFieldTxt());
            jSONObject.put("dataIndex", (Object) onlCgreportItem.getFieldName());
            jSONObject.put("fieldType", (Object) onlCgreportItem.getFieldType());
            jSONObject.put("align", (Object) "center");
            jSONObject.put("sorter", (Object) "true");
            jSONObject.put("isTotal", (Object) onlCgreportItem.getIsTotal());
            jSONObject.put("groupTitle", (Object) onlCgreportItem.getGroupTitle());
            if (oConvertUtils.isNotEmpty((Object) onlCgreportItem.getGroupTitle())) {
                bl = true;
            }
            if ("Integer".equals(string2 = onlCgreportItem.getFieldType()) || "Date".equals(string2) || "Long".equals(string2)) {
                jSONObject.put("sorter", (Object) "true");
            }
            if (StringUtils.isNotBlank((String) onlCgreportItem.getFieldHref())) {
                string = "fieldHref_" + onlCgreportItem.getFieldName();
                object = new JSONObject();
                object.put("customRender", (Object) string);
                jSONObject.put("scopedSlots", object);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("slotName", (Object) string);
                jSONObject2.put("href", (Object) onlCgreportItem.getFieldHref());
                jSONArray.add((Object) jSONObject2);
            }
            if ((string = onlCgreportItem.getDictCode()) != null && !"".equals(string)) {
                if (queryDict) {
                    object = this.queryColumnDict(onlCgreportItem.getDictCode(), null, null);
                    hashMap2.put(onlCgreportItem.getFieldName(), (JSONObject) object);
                    jSONObject.put("customRender", (Object) onlCgreportItem.getFieldName());
                } else {
                    jSONObject.put("dictCode", (Object) string);
                }
            }
            jSONArray2.add((Object) jSONObject);
        }
        if (queryDict) {
            hashMap.put("dictOptions", hashMap2);
        }
        hashMap.put("columns", (Object) jSONArray2);
        hashMap.put("fieldHrefSlots", (Object) jSONArray);
        hashMap.put("isGroupTitle", bl);
        return hashMap;
    }

    @Override
    public List<DictModel> queryColumnDict(String dictCode, JSONArray records, String fieldName) {
        List list = null;
        if (oConvertUtils.isNotEmpty((Object) dictCode)) {
            if (dictCode.trim().toLowerCase().indexOf("select ") == 0 && (fieldName == null || records.size() > 0)) {
                String string;
                List<Object> list2;
                int n2 = (dictCode = dictCode.trim()).lastIndexOf(";");
                if (n2 == dictCode.length() - 1) {
                    dictCode = dictCode.substring(0, n2);
                }
                String string2 = "SELECT * FROM (" + dictCode + ") temp ";
                if (records != null) {
                    list2 = new ArrayList();
                    for (int i2 = 0; i2 < records.size(); ++i2) {
                        JSONObject jSONObject = records.getJSONObject(i2);
                        String string3 = jSONObject.getString(fieldName);
                        if (!StringUtils.isNotBlank((String) string3)) continue;
                        list2.add(string3);
                    }
                    string = "'" + StringUtils.join(list2, (String) "','") + "'";
                    string2 = string2 + "WHERE temp.value IN (" + string + ")";
                }
                if ((list2 = ((OnlCgreportHeadMapper) this.getBaseMapper()).executeSelete(string2)) != null && list2.size() != 0) {
                    string = JSON.toJSONString(list2);
                    list = JSON.parseArray((String) string, DictModel.class);
                }
            } else {
                list = this.sysBaseAPI.queryDictItemsByCode(dictCode);
            }
        }
        return list;
    }

    private static /* synthetic */ Object a(SerializedLambda serializedLambda) {
        switch (serializedLambda.getImplMethodName()) {
            case "getIsSearch": {
                if (serializedLambda.getImplMethodKind() == 5 && serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") && serializedLambda.getFunctionalInterfaceMethodName().equals("apply") && serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportItem") && serializedLambda.getImplMethodSignature().equals("()Ljava/lang/Integer;")) {
                    return OnlCgreportItem::getIsSearch;
                }
                if (serializedLambda.getImplMethodKind() != 5 || !serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") || !serializedLambda.getFunctionalInterfaceMethodName().equals("apply") || !serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") || !serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportItem") || !serializedLambda.getImplMethodSignature().equals("()Ljava/lang/Integer;"))
                    break;
                return OnlCgreportItem::getIsSearch;
            }
            case "getCgrheadId": {
                if (serializedLambda.getImplMethodKind() == 5 && serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") && serializedLambda.getFunctionalInterfaceMethodName().equals("apply") && serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportParam") && serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) {
                    return OnlCgreportParam::getCgrheadId;
                }
                if (serializedLambda.getImplMethodKind() == 5 && serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") && serializedLambda.getFunctionalInterfaceMethodName().equals("apply") && serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportItem") && serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) {
                    return OnlCgreportItem::getCgrheadId;
                }
                if (serializedLambda.getImplMethodKind() == 5 && serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") && serializedLambda.getFunctionalInterfaceMethodName().equals("apply") && serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportParam") && serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) {
                    return OnlCgreportParam::getCgrheadId;
                }
                if (serializedLambda.getImplMethodKind() == 5 && serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") && serializedLambda.getFunctionalInterfaceMethodName().equals("apply") && serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportItem") && serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) {
                    return OnlCgreportItem::getCgrheadId;
                }
                if (serializedLambda.getImplMethodKind() == 5 && serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") && serializedLambda.getFunctionalInterfaceMethodName().equals("apply") && serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportItem") && serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) {
                    return OnlCgreportItem::getCgrheadId;
                }
                if (serializedLambda.getImplMethodKind() == 5 && serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") && serializedLambda.getFunctionalInterfaceMethodName().equals("apply") && serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportParam") && serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) {
                    return OnlCgreportParam::getCgrheadId;
                }
                if (serializedLambda.getImplMethodKind() == 5 && serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") && serializedLambda.getFunctionalInterfaceMethodName().equals("apply") && serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportItem") && serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) {
                    return OnlCgreportItem::getCgrheadId;
                }
                if (serializedLambda.getImplMethodKind() == 5 && serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") && serializedLambda.getFunctionalInterfaceMethodName().equals("apply") && serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportParam") && serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) {
                    return OnlCgreportParam::getCgrheadId;
                }
                if (serializedLambda.getImplMethodKind() == 5 && serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") && serializedLambda.getFunctionalInterfaceMethodName().equals("apply") && serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") && serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportItem") && serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;")) {
                    return OnlCgreportItem::getCgrheadId;
                }
                if (serializedLambda.getImplMethodKind() != 5 || !serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") || !serializedLambda.getFunctionalInterfaceMethodName().equals("apply") || !serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") || !serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportParam") || !serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;"))
                    break;
                return OnlCgreportParam::getCgrheadId;
            }
            case "getFieldName": {
                if (serializedLambda.getImplMethodKind() != 5 || !serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") || !serializedLambda.getFunctionalInterfaceMethodName().equals("apply") || !serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") || !serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportItem") || !serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;"))
                    break;
                return OnlCgreportItem::getFieldName;
            }
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }
}

