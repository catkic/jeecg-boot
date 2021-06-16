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
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;
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
import org.jeecg.modules.online.cgform.util.DataBaseUtils;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportHeadMapper;
import org.jeecg.modules.online.cgreport.model.OnlCgreportModel;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportHeadService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.jeecg.modules.online.cgreport.util.SqlUtil;
import org.jeecg.modules.online.cgreport.util.FieldUtil;
import org.jeecg.modules.online.config.exception.DBException;
import org.jeecg.modules.online.config.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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

    public Map<String, Object> executeSelectSql(String sql, String onlCgreportHeadId, JSONObject params) throws SQLException {
        String databaseType = null;
        try {
            databaseType = DataBaseUtil.getDatabaseType();
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
                } else if (value == null && oConvertUtils.isNotEmpty(onlCgreportParam.getParamValue())) {
                    replacement = onlCgreportParam.getParamValue();
                }
                final String string = "${" + onlCgreportParam.getParamName() + "}";
                if (sql.indexOf(string) > 0) {
                    if (replacement != null && replacement.startsWith("'") && replacement.endsWith("'")) {
                        replacement = replacement.substring(1, replacement.length() - 1);
                    }
                    sql = sql.replace(string, replacement);
                } else {
                    if (!oConvertUtils.isNotEmpty(replacement)) {
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
            list3.forEach(onlCgreportItem -> onlCgreportItem.setIsSearch(true));
        } else {
            lambdaQueryWrapper2.eq(OnlCgreportItem::getIsSearch, true);
            list3 = this.onlCgreportItemService.list(lambdaQueryWrapper2);
        }
        final String a = FieldUtil.a(list3, params, "jeecg_rp_temp.", null);
        if (ReUtil.contains(" order\\s+by ", sql.toLowerCase()) && "SQLSERVER".equalsIgnoreCase(databaseType)) {
            throw new JeecgBootException("SqlServer不支持SQL内排序!");
        }
        String s2 = SqlUtil.b("select * from (" + sql + ") jeecg_rp_temp  where 1=1 " + a);
        final Object value2 = params.get("column");
        if (value2 != null) {
            s2 = s2 + " order by jeecg_rp_temp." + value2 + " " + params.get("order").toString();
        }
        log.info("报表查询sql=>\n" + s2);
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
        result.put("records", DataBaseUtils.lobAndNull(selectPageBySql.getRecords()));
        return result;
    }

    @Override
    public Map<String, Object> executeSelectSqlDynamic(String dbKey, String sql, Map<String, Object> params, String onlCgreportHeadId) {
        DynamicDataSourceModel dynamicDataSourceModel = DataSourceCachePool.getCacheDynamicDataSourceModel(dbKey);
        String string2 = (String) params.get("order");
        String string3 = (String) params.get("column");
        int n2 = oConvertUtils.getInt(params.get("pageNo"), 1);
        int n3 = oConvertUtils.getInt(params.get("pageSize"), 10);
        log.info("【Online多数据源逻辑】报表查询参数params: " + JSON.toJSONString(params));
        LambdaQueryWrapper<OnlCgreportParam> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgreportParam::getCgrheadId, onlCgreportHeadId);
        List<OnlCgreportParam> list = this.onlCgreportParamService.list(lambdaQueryWrapper);
        if (list != null && list.size() > 0) {
            for (OnlCgreportParam param : list) {
                Object object = params.get("self_" + param.getParamName());
                String string = "";
                if (object != null) {
                    string = object.toString();
                } else if (oConvertUtils.isNotEmpty(param.getParamValue())) {
                    string = param.getParamValue();
                }
                sql = sql.replace("${" + param.getParamName() + "}", string);
            }
        }
        LambdaQueryWrapper<OnlCgreportItem> lambdaQueryWrapper2 = new LambdaQueryWrapper<OnlCgreportItem>()
                .eq(OnlCgreportItem::getCgrheadId, onlCgreportHeadId)
                .eq(OnlCgreportItem::getIsSearch, true);

        List<OnlCgreportItem> object22 = this.onlCgreportItemService.list(lambdaQueryWrapper2);
        if (ReUtil.contains(" order\\s+by ", sql.toLowerCase()) && "3".equalsIgnoreCase(dynamicDataSourceModel.getDbType())) {
            throw new JeecgBootException("SqlServer不支持SQL内排序!");
        }
        String tmpTable = "jeecg_rp_temp.";
        String dbName = DataBaseEnum.getDataBaseNameByValue(dynamicDataSourceModel.getDbType());
        String string4 = FieldUtil.a(object22, params, tmpTable, dbName);
        String string5 = "select * from (" + sql + ") jeecg_rp_temp  where 1=1 " + string4;
        string5 = SqlUtil.b(string5);
        String string6 = SqlUtils.getCountSql(string5);
        Object object3 = params.get("column");
        if (object3 != null) {
            string5 = string5 + " order by jeecg_rp_temp." + object3.toString() + " " + params.get("order").toString();
        }
        String pageSql = string5;
        if (!Boolean.parseBoolean(String.valueOf(params.get("getAll")))) {
            pageSql = SqlUtils.createPageSqlByDBType(dynamicDataSourceModel.getDbType(), string5, n2, n3);
        }
        log.info("多数据源 报表查询sql=>querySql: " + string5);
        log.info("多数据源 报表查询sql=>pageSQL: " + pageSql);
        log.info("多数据源 报表查询sql=>countSql: " + string6);
        HashMap<String, Object> hashMap = new HashMap<>();
        Map<String, Object> map = DynamicDBUtil.findOne(dbKey, string6, 0);
        hashMap.put("total", map.get("total"));
        hashMap.put("records", DataBaseUtils.lobAndNull(DynamicDBUtil.findList(dbKey, pageSql, 0)));
        return hashMap;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result<?> editAll(OnlCgreportModel values) {
        OnlCgreportHead onlCgreportHead = values.getHead();
        OnlCgreportHead onlCgreportHead2 = super.getById(onlCgreportHead.getId());
        if (onlCgreportHead2 == null) {
            return Result.error("未找到对应实体");
        }
        super.updateById(onlCgreportHead);
        LambdaQueryWrapper<OnlCgreportItem> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgreportItem::getCgrheadId, onlCgreportHead.getId());
        this.onlCgreportItemService.remove(lambdaQueryWrapper);
        LambdaQueryWrapper<OnlCgreportParam> lambdaQueryWrapper2 = new LambdaQueryWrapper();
        lambdaQueryWrapper2.eq(OnlCgreportParam::getCgrheadId, onlCgreportHead.getId());
        this.onlCgreportParamService.remove(lambdaQueryWrapper2);
        for (OnlCgreportParam serializable : values.getParams()) {
            serializable.setCgrheadId(onlCgreportHead.getId());
        }
        for (OnlCgreportItem onlCgreportItem : values.getItems()) {
            onlCgreportItem.setFieldName(onlCgreportItem.getFieldName().trim().toLowerCase());
            onlCgreportItem.setCgrheadId(onlCgreportHead.getId());
        }
        this.onlCgreportItemService.saveBatch(values.getItems());
        this.onlCgreportParamService.saveBatch(values.getParams());
        return Result.ok("全部修改成功");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result<?> delete(String id) {
        boolean bl = super.removeById((Serializable) id);
        if (bl) {
            LambdaQueryWrapper<OnlCgreportItem> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(OnlCgreportItem::getCgrheadId, id);
            this.onlCgreportItemService.remove(lambdaQueryWrapper);
            LambdaQueryWrapper<OnlCgreportParam> lambdaQueryWrapper2 = new LambdaQueryWrapper();
            lambdaQueryWrapper2.eq(OnlCgreportParam::getCgrheadId, id);
            this.onlCgreportParamService.remove(lambdaQueryWrapper2);
        }
        return Result.ok("删除成功");
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result<?> bathDelete(String[] ids) {
        for (String string : ids) {
            boolean bl = super.removeById((Serializable) string);
            if (!bl) continue;
            LambdaQueryWrapper<OnlCgreportItem> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(OnlCgreportItem::getCgrheadId, string);
            this.onlCgreportItemService.remove(lambdaQueryWrapper);
            LambdaQueryWrapper<OnlCgreportParam> lambdaQueryWrapper2 = new LambdaQueryWrapper();
            lambdaQueryWrapper2.eq(OnlCgreportParam::getCgrheadId, string);
            this.onlCgreportParamService.remove(lambdaQueryWrapper2);
        }
        return Result.ok("删除成功");
    }

    @Override
    public Map<String, Object> executeSelectSql(String sql, String onlCgreportHeadId, Map<String, Object> params) throws SQLException {
        return this.executeSelectSql(sql, onlCgreportHeadId, new JSONObject(params));
    }

    @Override
    public List<String> getSqlFields(String sql, String dbKey) throws SQLException {
        return StringUtils.isNotBlank(dbKey) ? this.a(sql, dbKey) : this.a(sql, null);
    }

    @Override
    public List<String> getSqlParams(String sql) {
        if (oConvertUtils.isEmpty(sql)) {
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
        if (oConvertUtils.isEmpty(string)) {
            return null;
        }
        string = string.replace("=", " = ");
        if ((string = string.trim()).endsWith(";")) {
            string = string.substring(0, string.length() - 1);
        }
        string = QueryGenerator.convertSystemVariables(string);
        string = SqlUtil.a(string);
        if (StringUtils.isNotBlank(string2)) {
            log.info("parse sql : " + string);
            DynamicDataSourceModel dynamicDataSourceModel = DataSourceCachePool.getCacheDynamicDataSourceModel(string2);
            if (ReUtil.contains(" order\\s+by ", string.toLowerCase()) && "3".equalsIgnoreCase(dynamicDataSourceModel.getDbType())) {
                throw new JeecgBootException("SqlServer不支持SQL内排序!");
            }
            if ("1".equals(dynamicDataSourceModel.getDbType())) {
                string = "SELECT * FROM (" + string + ") temp LIMIT 1";
            } else if ("2".equals(dynamicDataSourceModel.getDbType())) {
                string = "SELECT * FROM (" + string + ") temp WHERE ROWNUM <= 1";
            } else if ("3".equals(dynamicDataSourceModel.getDbType())) {
                string = "SELECT TOP 1 * FROM (" + string + ") temp";
            }
            log.info("parse sql with page : " + string);
            Map map = DynamicDBUtil.findOne(string2, string, new Object[0]);
            if (map == null) {
                throw new JeecgBootException("该报表sql没有数据");
            }
            set = map.keySet();
        } else {
            log.info("parse sql: " + string);
            String string3 = null;
            try {
                string3 = DataBaseUtil.getDatabaseType();
            } catch (DBException dBException) {
                dBException.printStackTrace();
            }
            if (ReUtil.contains(" order\\s+by ", string.toLowerCase()) && "SQLSERVER".equalsIgnoreCase(string3)) {
                throw new JeecgBootException("SqlServer不支持SQL内排序!");
            }
            IPage<Map<String, Object>> iPage = this.mapper.selectPageBySql((Page<Map<String, Object>>) new Page(1L, 1L), string);
            List list = iPage.getRecords();
            if (list.size() < 1) {
                throw new JeecgBootException("该报表sql没有数据");
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
        if (DataBaseUtil.isOracle()) {
            hashMap.put("main", DataBaseUtils.lobAndNull(map));
            hashMap.put("items", DataBaseUtils.lobAndNull(list));
        } else {
            hashMap.put("main", map);
            hashMap.put("items", list);
        }
        hashMap.put("params", list2);
        return hashMap;
    }

    @Override
    public List<Map<String, Object>> queryByCgReportSql(String sql, Map params, Map paramData, int pageNo, int pageSize) {
        String string = SqlUtil.a(sql, params);
        List<Map<String, Object>> list;
        if (paramData != null && paramData.size() == 0) {
            paramData = null;
        }
        if (pageNo == -1 && pageSize == -1) {
            return this.mapper.executeSelete(string);
        } else {
            Page page = new Page(pageNo, pageSize);
            IPage<Map<String, Object>> iPage = this.mapper.selectPageBySql(page, string);
            if (iPage.getRecords() != null && iPage.getRecords().size() > 0) {
                return iPage.getRecords();
            }
        }
        return null;
    }

    @Override
    public List<DictModel> queryDictSelectData(String sql, String keyword) {
        Object object;
        Page<Map<String, Object>> page = new Page<>();
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
        List<Map<String, Object>> records = this.baseMapper.selectPageBySql(page, sql).getRecords();
        if (records != null && !records.isEmpty()) {
            String string = JSON.toJSONString(records);
            return JSON.parseArray(string, DictModel.class);
        }
        return new ArrayList<>();
    }

    @Override
    @Cacheable(value = {"sys:cache:online:rp"}, key = "'column-'+#code+'-'+#queryDict")
    public Map<String, Object> queryColumnInfo(String code, boolean queryDict) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        QueryWrapper<OnlCgreportItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cgrhead_id", code)
                .eq("is_show", 1)
                .orderByAsc("order_num");
        List<OnlCgreportItem> list = this.onlCgreportItemService.list(queryWrapper);
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        HashMap<String, JSONObject> hashMap2 = new HashMap<String, JSONObject>();
        boolean bl = false;
        for (OnlCgreportItem onlCgreportItem : list) {
            String string;
            String string2;
            JSONObject jSONObject = new JSONObject(4);
            jSONObject.put("title", onlCgreportItem.getFieldTxt());
            jSONObject.put("dataIndex", onlCgreportItem.getFieldName());
            jSONObject.put("fieldType", onlCgreportItem.getFieldType());
            jSONObject.put("align", "center");
            jSONObject.put("sorter", "true");
            jSONObject.put("isTotal", onlCgreportItem.getIsTotal());
            jSONObject.put("groupTitle", onlCgreportItem.getGroupTitle());
            if (oConvertUtils.isNotEmpty(onlCgreportItem.getGroupTitle())) {
                bl = true;
            }
            if ("Integer".equals(string2 = onlCgreportItem.getFieldType()) || "Date".equals(string2) || "Long".equals(string2)) {
                jSONObject.put("sorter", "true");
            }
            if (StringUtils.isNotBlank(onlCgreportItem.getFieldHref())) {
                string = "fieldHref_" + onlCgreportItem.getFieldName();
                JSONObject object = new JSONObject();
                object.put("customRender", (Object) string);
                jSONObject.put("scopedSlots", object);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("slotName", string);
                jSONObject2.put("href", onlCgreportItem.getFieldHref());
                jSONArray.add(jSONObject2);
            }

            String dictCode = onlCgreportItem.getDictCode();
            if (dictCode != null && !"".equals(dictCode)) {
                if (queryDict) {
                    // 这里不一定能转对
                    List<DictModel> object = this.queryColumnDict(onlCgreportItem.getDictCode(), null, null);
                    hashMap2.put(onlCgreportItem.getFieldName(), (JSONObject) object);
                    jSONObject.put("customRender", onlCgreportItem.getFieldName());
                } else {
                    jSONObject.put("dictCode", dictCode);
                }
            }
            jSONArray2.add(jSONObject);
        }
        if (queryDict) {
            hashMap.put("dictOptions", hashMap2);
        }
        hashMap.put("columns", jSONArray2);
        hashMap.put("fieldHrefSlots", jSONArray);
        hashMap.put("isGroupTitle", bl);
        return hashMap;
    }

    @Override
    public List<DictModel> queryColumnDict(String dictCode, JSONArray records, String fieldName) {
        List list = null;
        if (oConvertUtils.isNotEmpty(dictCode)) {
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
                        if (!StringUtils.isNotBlank(string3)) continue;
                        list2.add(string3);
                    }
                    string = "'" + StringUtils.join(list2, "','") + "'";
                    string2 = string2 + "WHERE temp.value IN (" + string + ")";
                }
                if ((list2 = Collections.singletonList(this.getBaseMapper().executeSelete(string2))) != null && list2.size() != 0) {
                    string = JSON.toJSONString(list2);
                    list = JSON.parseArray(string, DictModel.class);
                }
            } else {
                list = this.sysBaseAPI.queryDictItemsByCode(dictCode);
            }
        }
        return list;
    }

}


