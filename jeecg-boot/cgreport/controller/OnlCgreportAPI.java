/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.jeecg.common.api.vo.Result
 *  org.jeecg.common.aspect.annotation.PermissionData
 *  org.jeecg.common.exception.JeecgBootException
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.system.vo.DictModel
 *  org.jeecg.common.system.vo.DynamicDataSourceModel
 *  org.jeecg.common.util.BrowserUtils
 *  org.jeecg.common.util.oConvertUtils
 *  org.jeecgframework.poi.excel.ExcelExportUtil
 *  org.jeecgframework.poi.excel.entity.ExportParams
 *  org.jeecgframework.poi.excel.entity.params.ExcelExportEntity
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package org.jeecg.modules.online.cgreport.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.io.OutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.DynamicDataSourceModel;
import org.jeecg.common.util.BrowserUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgreport.a.a;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportHeadMapper;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.jeecg.modules.online.cgreport.service.impl.OnlCgreportAPIServiceImpl;
import org.jeecg.modules.online.cgreport.service.impl.OnlCgreportHeadServiceImpl;
import org.jeecg.modules.online.cgreport.util.SqlUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "onlCgreportAPI")
@RequestMapping(value = {"/online/cgreport/api"})
@Slf4j
public class OnlCgreportAPI {
    @Autowired
    private OnlCgreportAPIServiceImpl cgreportAPIService;
    @Autowired
    private OnlCgreportHeadServiceImpl onlCgreportHeadService;
    @Autowired
    private IOnlCgreportItemService onlCgreportItemService;
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IOnlCgreportParamService onlCgreportParamService;

    @GetMapping(value = {"/getColumnsAndData/{code}"})
    @PermissionData
    public Result<?> a(@PathVariable(value = "code") String string, HttpServletRequest httpServletRequest) {
        OnlCgreportHead onlCgreportHead = (OnlCgreportHead) this.onlCgreportHeadService.getById((Serializable) ((Object) string));
        if (onlCgreportHead == null) {
            return Result.error((String) "\u5b9e\u4f53\u4e0d\u5b58\u5728");
        }
        Result<?> result = this.b(string, httpServletRequest);
        if (result.getCode().equals(200)) {
            JSONObject jSONObject = JSON.parseObject((String) JSONObject.toJSONString((Object) result.getResult()));
            JSONArray jSONArray = jSONObject.getJSONArray("records");
            Map<String, Object> map = this.onlCgreportHeadService.queryColumnInfo(string, false);
            JSONArray jSONArray2 = (JSONArray) map.get("columns");
            HashMap<String, List<DictModel>> hashMap = new HashMap<String, List<DictModel>>();
            if (jSONArray2 != null) {
                for (int i2 = 0; i2 < jSONArray2.size(); ++i2) {
                    String string2;
                    String string3;
                    List<DictModel> list;
                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                    Object object = jSONObject2.get((Object) "dictCode");
                    if (object == null || (list = this.onlCgreportHeadService.queryColumnDict(string3 = object.toString(), jSONArray, string2 = jSONArray2.getJSONObject(i2).getString("dataIndex"))) == null)
                        continue;
                    hashMap.put(string2, list);
                    jSONObject2.put("customRender", (Object) string2);
                }
            }
            map.put("cgreportHeadName", onlCgreportHead.getName());
            map.put("data", result.getResult());
            map.put("dictOptions", hashMap);
            return Result.ok(map);
        }
        return result;
    }

    @Deprecated
    @GetMapping(value = {"/getColumns/{code}"})
    public Result<?> a(@PathVariable(value = "code") String string) {
        OnlCgreportHead onlCgreportHead = this.onlCgreportHeadService.getById(string);
        if (onlCgreportHead == null) {
            return Result.error("\u5b9e\u4f53\u4e0d\u5b58\u5728");
        }
        QueryWrapper<OnlCgreportItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cgrheadId", string);
        queryWrapper.eq("show", true);
        queryWrapper.orderByAsc("order_num");
        List<OnlCgreportItem> list = this.onlCgreportItemService.list(queryWrapper);
        ArrayList arrayList = new ArrayList();
        HashMap<String, List> hashMap = new HashMap<String, List>();
        for (OnlCgreportItem onlCgreportItem : list) {
            HashMap<String, String> hashMap2 = new HashMap<String, String>(3);
            hashMap2.put("title", onlCgreportItem.getFieldTxt());
            hashMap2.put("dataIndex", onlCgreportItem.getFieldName());
            hashMap2.put("align", "center");
            hashMap2.put("sorter", "true");
            arrayList.add(hashMap2);
            String string2 = onlCgreportItem.getDictCode();
            if (!oConvertUtils.isNotEmpty(string2)) continue;
            List list2 = null;
            if (string2.toLowerCase().indexOf("select ") == 0) {
                List<Map<String, Object>> list3 = this.onlCgreportHeadService.getBaseMapper().executeSelete(string2);
                if (list3 != null && list3.size() != 0) {
                    String string3 = JSON.toJSONString(list3);
                    list2 = JSON.parseArray(string3, DictModel.class);
                }
            } else {
                list2 = this.sysBaseAPI.queryDictItemsByCode(string2);
            }
            if (list2 == null) continue;
            hashMap.put(onlCgreportItem.getFieldName(), list2);
            hashMap2.put("customRender", onlCgreportItem.getFieldName());
        }
        HashMap hashMap3 = new HashMap(1);
        hashMap3.put("columns", arrayList);
        hashMap3.put("dictOptions", hashMap);
        hashMap3.put("cgreportHeadName", onlCgreportHead.getName());
        return Result.ok((Object) hashMap3);
    }

    @GetMapping(value = {"/getData/{code}"})
    @PermissionData
    public Result<?> b(@PathVariable(value = "code") String string, HttpServletRequest httpServletRequest) {
        Map<String, Object> map = SqlUtil.a(httpServletRequest);
        map.put("getAll", httpServletRequest.getAttribute("getAll"));
        try {
            return Result.OK(this.cgreportAPIService.getDataById(string, map));
        } catch (JeecgBootException jeecgBootException) {
            return Result.error((String) jeecgBootException.getMessage());
        }
    }

    @GetMapping(value = {"/getDataOrderByValue/{code}"})
    @PermissionData
    public Result<?> c(@PathVariable(value = "code") String string, HttpServletRequest httpServletRequest) {
        OnlCgreportHead onlCgreportHead = (OnlCgreportHead) this.onlCgreportHeadService.getById((Serializable) ((Object) string));
        if (onlCgreportHead == null) {
            return Result.error((String) "\u5b9e\u4f53\u4e0d\u5b58\u5728");
        }
        String string2 = onlCgreportHead.getCgrSql().trim();
        String string3 = onlCgreportHead.getDbSource();
        try {
            Map<String, Object> map = SqlUtil.a(httpServletRequest);
            Object object = map.get("order_field");
            Object object2 = map.get("order_value");
            if (oConvertUtils.isEmpty((Object) object) || oConvertUtils.isEmpty((Object) object2)) {
                return Result.error((String) "order_field \u548c order_value \u53c2\u6570\u4e0d\u80fd\u4e3a\u7a7a\uff01");
            }
            String string4 = "force_" + object;
            map.put(string4, object2);
            map.put("getAll", true);
            Map<String, Object> map2 = this.cgreportAPIService.executeSelectSqlRoute(string3, string2, map, onlCgreportHead.getId());
            JSONArray jSONArray = JSON.parseArray((String) JSON.toJSONString((Object) map2.get("records")));
            map.remove(object.toString());
            map.remove(string4);
            map.remove("order_field");
            map.remove("order_value");
            map.put("getAll", httpServletRequest.getAttribute("getAll"));
            Map<String, Object> map3 = this.cgreportAPIService.executeSelectSqlRoute(string3, string2, map, onlCgreportHead.getId());
            JSONArray jSONArray2 = JSON.parseArray((String) JSON.toJSONString((Object) map3.get("records")));
            this.a(jSONArray, jSONArray2);
            map3.put("records", (Object) jSONArray2);
            return Result.ok(map3);
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return Result.error((String) ("SQL\u6267\u884c\u5931\u8d25\uff1a" + exception.getMessage()));
        }
    }

    private void a(JSONArray jSONArray, JSONArray jSONArray2) {
        for (int i2 = 0; i2 < jSONArray.size(); ++i2) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            String string = jSONObject.getString("id");
            int n2 = (int) jSONArray2.stream().filter(object -> string.equals(((JSONObject) object).getString("id"))).count();
            if (n2 != 0) continue;
            jSONArray2.add(0, (Object) jSONObject);
        }
    }

    @GetMapping(value = {"/getQueryInfo/{code}"})
    public Result<?> b(@PathVariable(value = "code") String string) {
        try {
            List<Map<String, String>> list = this.onlCgreportItemService.getAutoListQueryInfo(string);
            return Result.ok(list);
        } catch (Exception exception) {
            log.info(exception.getMessage(), (Throwable) exception);
            return Result.error((String) "\u67e5\u8be2\u5931\u8d25");
        }
    }

    @GetMapping(value = {"/getParamsInfo/{code}"})
    public Result<?> c(@PathVariable(value = "code") String string) {
        try {
            LambdaQueryWrapper<OnlCgreportParam> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgreportParam>()
                    .eq(OnlCgreportParam::getCgrheadId, string);
            List<OnlCgreportParam> list = this.onlCgreportParamService.list(lambdaQueryWrapper);
            return Result.ok(list);
        } catch (Exception exception) {
            log.info(exception.getMessage(), exception);
            return Result.error("\u67e5\u8be2\u5931\u8d25");
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @PermissionData
    @RequestMapping(value = {"/exportXls/{reportId}"})
    public void a(@PathVariable(value = "reportId") String string, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Object object;
        Serializable serializable;
        Object object2222222222;
        Object object3;
        String string2;
        Object object4;
        String string3 = "\u62a5\u8868";
        String string4 = "\u5bfc\u51fa\u4fe1\u606f";
        if (!oConvertUtils.isNotEmpty((Object) string)) throw new JeecgBootException("\u53c2\u6570\u9519\u8bef");
        Map<String, Object> map = null;
        try {
            map = this.onlCgreportHeadService.queryCgReportConfig(string);
        } catch (Exception exception) {
            throw new JeecgBootException("\u52a8\u6001\u62a5\u8868\u914d\u7f6e\u4e0d\u5b58\u5728!");
        }
        List list = (List) map.get("items");
        httpServletRequest.setAttribute("getAll", (Object) true);
        Result<?> result = this.b(string, httpServletRequest);
        List list2 = null;
        if (result.getCode().equals(200)) {
            object4 = (Map) result.getResult();
            list2 = (List) object4.get("records");
        }
        object4 = new ArrayList();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        HashMap<Object, Object> hashMap2 = new HashMap<Object, Object>();
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i2 = 0; i2 < list.size(); ++i2) {
            if ("1".equals(oConvertUtils.getString(((Map) list.get(i2)).get("is_show")))) {
                Object object5;
                Object object6;
                String iterator2 = ((Map) list.get(i2)).get("field_name").toString();
                string2 = new ExcelExportEntity(((Map) list.get(i2)).get("field_txt").toString(), (Object) iterator2, 15);
                object3 = ((Map) list.get(i2)).get("dict_code");
                Object object7 = JSONObject.parseArray((String) JSONObject.toJSONString((Object) list2));
                object2222222222 = this.onlCgreportHeadService.queryColumnDict(oConvertUtils.getString((Object) object3), (JSONArray) object7, iterator2);
                if (object2222222222 != null && object2222222222.size() > 0) {
                    serializable = new ArrayList();
                    object6 = object2222222222.iterator();
                    while (object6.hasNext()) {
                        object5 = (DictModel) object6.next();
                        serializable.add(object5.getText() + "_" + object5.getValue());
                    }
                    string2.setReplace(serializable.toArray(new String[serializable.size()]));
                }
                if (oConvertUtils.isNotEmpty((Object) (serializable = ((Map) list.get(i2)).get("replace_val")))) {
                    string2.setReplace(serializable.toString().split(","));
                }
                if (oConvertUtils.isNotEmpty(((Map) list.get(i2)).get("group_title"))) {
                    object6 = ((Map) list.get(i2)).get("group_title").toString();
                    object5 = new ArrayList();
                    if (hashMap2.containsKey(object6)) {
                        object5 = (List) hashMap2.get(object6);
                        object5.add(iterator2);
                    } else {
                        ExcelExportEntity excelExportEntity = new ExcelExportEntity((String) object6, object6, true);
                        arrayList.add((Object) excelExportEntity);
                        object5.add(iterator2);
                    }
                    hashMap2.put(object6, object5);
                    string2.setColspan(true);
                }
                arrayList.add(string2);
            }
            if (!"1".equals(oConvertUtils.getString(((Map) list.get(i2)).get("is_total")))) continue;
            object4.add(((Map) list.get(i2)).get("field_name").toString());
        }
        for (Map.Entry exception : hashMap2.entrySet()) {
            string2 = (String) exception.getKey();
            object3 = (List) exception.getValue();
            for (Object object2222222222 : arrayList) {
                if (!string2.equals(object2222222222.getName()) || !object2222222222.isColspan()) continue;
                object2222222222.setSubColumnList((List) object3);
            }
        }
        if (object4 != null && object4.size() > 0) {
            object = new BigDecimal(0.0);
            Iterator exception2 = object4.iterator();
            while (exception2.hasNext()) {
                string2 = (String) exception2.next();
                for (Object object7 : list2) {
                    object2222222222 = object7.get(string2).toString();
                    if (!((String) object2222222222).matches("\\d+(.\\d+)?")) continue;
                    serializable = new BigDecimal((String) object2222222222);
                    object = ((BigDecimal) object).add((BigDecimal) serializable);
                }
                hashMap.put(string2, object);
            }
            list2.add(hashMap);
        }
        httpServletResponse.setContentType("application/vnd.ms-excel");
        object = null;
        String string5 = BrowserUtils.checkBrowse((HttpServletRequest) httpServletRequest);
        if ("MSIE".equalsIgnoreCase(string5.substring(0, 4))) {
            httpServletResponse.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(string3, "UTF-8") + ".xls");
        } else {
            string2 = new String(string3.getBytes("UTF-8"), "ISO8859-1");
            httpServletResponse.setHeader("content-disposition", "attachment;filename=" + string2 + ".xls");
        }
        string2 = ExcelExportUtil.exportExcel((ExportParams) new ExportParams(null, string4), arrayList, (Collection) list2);
        object = httpServletResponse.getOutputStream();
        string2.write((OutputStream) object);
        try {
            ((OutputStream) object).flush();
            ((OutputStream) object).close();
            return;
        } catch (Exception exception) {
            return;
        } catch (Exception exception) {
            try {
                ((OutputStream) object).flush();
                ((OutputStream) object).close();
                return;
            } catch (Exception exception2) {
                return;
            } catch (Throwable throwable) {
                try {
                    ((OutputStream) object).flush();
                    ((OutputStream) object).close();
                    throw throwable;
                } catch (Exception exception3) {
                    // empty catch block
                }
                throw throwable;
            }
        }
    }

    @PermissionData
    @RequestMapping({ "/exportXls/{reportId}" })
    public void a(@PathVariable("reportId") final String reportId, final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {
        final String s = "\u62a5\u8868";
        final String s2 = "\u5bfc\u51fa\u4fe1\u606f";
        if (oConvertUtils.isNotEmpty(reportId)) {
            Map<String, Object> queryCgReportConfig;
            try {
                queryCgReportConfig = this.onlCgreportHeadService.queryCgReportConfig(reportId);
            }
            catch (Exception ex) {
                throw new JeecgBootException("\u52a8\u6001\u62a5\u8868\u914d\u7f6e\u4e0d\u5b58\u5728!");
            }
            List<Map<String, Object>> list = (List<Map<String, Object>>) queryCgReportConfig.get("items");
            httpServletRequest.setAttribute("getAll", (Object)true);
            final Result<?> b = this.b(reportId, httpServletRequest);
            Object o = null;
            if (b.getCode().equals(200)) {
                o = ((Map)b.getResult()).get("records");
            }
            final ArrayList<String> list2 = new ArrayList<String>();
            final HashMap<String, BigDecimal> hashMap = new HashMap<String, BigDecimal>();
            final HashMap<Object, Object> hashMap2 = new HashMap<Object, Object>();
            final ArrayList<ExcelExportEntity> list3 = new ArrayList<ExcelExportEntity>();
            for (int i = 0; i < list.size(); ++i) {
                if ("1".equals(oConvertUtils.getString(list.get(i).get("is_show")))) {
                    final String string = list.get(i).get("field_name").toString();
                    final ExcelExportEntity excelExportEntity = new ExcelExportEntity(list.get(i).get("field_txt").toString(), (Object)string, 15);
                    final List<DictModel> queryColumnDict = this.onlCgreportHeadService.queryColumnDict(oConvertUtils.getString(list.get(i).get("dict_code")), JSONObject.parseArray(JSONObject.toJSONString(o)), string);
                    if (queryColumnDict != null && queryColumnDict.size() > 0) {
                        final ArrayList<String> list4 = new ArrayList<String>();
                        for (final DictModel dictModel : queryColumnDict) {
                            list4.add(dictModel.getText() + "_" + dictModel.getValue());
                        }
                        excelExportEntity.setReplace(list4.toArray(new String[list4.size()]));
                    }
                    final Object value = list.get(i).get("replace_val");
                    if (oConvertUtils.isNotEmpty(value)) {
                        excelExportEntity.setReplace(value.toString().split(","));
                    }
                    if (oConvertUtils.isNotEmpty(list.get(i).get("group_title"))) {
                        final String string2 = list.get(i).get("group_title").toString();
                        List<?> list5 = new ArrayList<Object>();
                        if (hashMap2.containsKey(string2)) {
                            list5 = hashMap2.get(string2);
                            list5.add(string);
                        }
                        else {
                            list3.add(new ExcelExportEntity(string2, (Object)string2, true));
                            list5.add(string);
                        }
                        hashMap2.put(string2, list5);
                        excelExportEntity.setColspan(true);
                    }
                    list3.add(excelExportEntity);
                }
                if ("1".equals(oConvertUtils.getString(list.get(i).get("is_total")))) {
                    list2.add(list.get(i).get("field_name").toString());
                }
            }
            for (final Map.Entry<String, List<?>> entry : hashMap2.entrySet()) {
                final String s3 = entry.getKey();
                final List<?> subColumnList = entry.getValue();
                for (final ExcelExportEntity excelExportEntity2 : list3) {
                    if (s3.equals(excelExportEntity2.getName()) && excelExportEntity2.isColspan()) {
                        excelExportEntity2.setSubColumnList((List)subColumnList);
                    }
                }
            }
            if (list2 != null && list2.size() > 0) {
                BigDecimal add = new BigDecimal(0.0);
                for (final String s4 : list2) {
                    final Iterator<Map<K, Object>> iterator5 = ((List<Map<K, Object>>)o).iterator();
                    while (iterator5.hasNext()) {
                        final String string3 = iterator5.next().get(s4).toString();
                        if (string3.matches("\\d+(.\\d+)?")) {
                            add = add.add(new BigDecimal(string3));
                        }
                    }
                    hashMap.put(s4, add);
                }
                ((List<Map<K, Object>>)o).add((Map<K, Object>)hashMap);
            }
            httpServletResponse.setContentType("application/vnd.ms-excel");
            Object outputStream = null;
            try {
                if ("MSIE".equalsIgnoreCase(BrowserUtils.checkBrowse(httpServletRequest).substring(0, 4))) {
                    httpServletResponse.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(s, "UTF-8") + ".xls");
                }
                else {
                    httpServletResponse.setHeader("content-disposition", "attachment;filename=" + new String(s.getBytes("UTF-8"), "ISO8859-1") + ".xls");
                }
                final Workbook exportExcel = ExcelExportUtil.exportExcel(new ExportParams((String)null, s2), (List)list3, (Collection)o);
                outputStream = httpServletResponse.getOutputStream();
                exportExcel.write((OutputStream)outputStream);
            }
            catch (Exception ex2) {}
            finally {
                try {
                    ((OutputStream)outputStream).flush();
                    ((OutputStream)outputStream).close();
                }
                catch (Exception ex3) {}
            }
            return;
        }
        throw new JeecgBootException("\u53c2\u6570\u9519\u8bef");
    }

    @GetMapping(value = {"/getRpColumns/{code}"})
    public Result<?> d(@PathVariable(value = "code") String string) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgreportHead::getCode, (Object) string);
        OnlCgreportHead onlCgreportHead = (OnlCgreportHead) this.onlCgreportHeadService.getOne((Wrapper) lambdaQueryWrapper);
        if (onlCgreportHead == null) {
            return Result.error((String) "\u5b9e\u4f53\u4e0d\u5b58\u5728");
        }
        Map<String, Object> map = this.onlCgreportHeadService.queryColumnInfo(onlCgreportHead.getId(), true);
        map.put("cgRpConfigId", onlCgreportHead.getId());
        map.put("cgRpConfigName", onlCgreportHead.getName());
        return Result.ok(map);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @PostMapping(value = {"/testConnection"})
    public Result a(@RequestBody DynamicDataSourceModel dynamicDataSourceModel) {
        Connection connection = null;
        try {
            Class.forName(dynamicDataSourceModel.getDbDriver());
            connection = DriverManager.getConnection(dynamicDataSourceModel.getDbUrl(), dynamicDataSourceModel.getDbUsername(), dynamicDataSourceModel.getDbPassword());
            if (connection != null) {
                Result result = Result.ok((String) "\u6570\u636e\u5e93\u8fde\u63a5\u6210\u529f");
                return result;
            }
            Result result = Result.ok((String) "\u6570\u636e\u5e93\u8fde\u63a5\u5931\u8d25\uff1a\u9519\u8bef\u672a\u77e5");
            return result;
        } catch (ClassNotFoundException classNotFoundException) {
            a.error(classNotFoundException.toString());
            Result result = Result.error((String) "\u6570\u636e\u5e93\u8fde\u63a5\u5931\u8d25\uff1a\u9a71\u52a8\u7c7b\u4e0d\u5b58\u5728");
            return result;
        } catch (Exception exception) {
            a.error(exception.toString());
            Result result = Result.error((String) ("\u6570\u636e\u5e93\u8fde\u63a5\u5931\u8d25\uff1a" + exception.getMessage()));
            return result;
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException sQLException) {
                a.error(sQLException.toString());
            }
        }
    }

    @GetMapping(value = {"/getReportDictList"})
    public Result<?> a(@RequestParam(value = "sql") String string, @RequestParam(name = "keyword", required = false) String string2) {
        List<DictModel> list = this.onlCgreportHeadService.queryDictSelectData(string, string2);
        return Result.ok(list);
    }

    private static /* synthetic */ Object a(SerializedLambda serializedLambda) {
        switch (serializedLambda.getImplMethodName()) {
            case "getCgrheadId": {
                if (serializedLambda.getImplMethodKind() != 5 || !serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") || !serializedLambda.getFunctionalInterfaceMethodName().equals("apply") || !serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") || !serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportParam") || !serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;"))
                    break;
                return OnlCgreportParam::getCgrheadId;
            }
            case "getCode": {
                if (serializedLambda.getImplMethodKind() != 5 || !serializedLambda.getFunctionalInterfaceClass().equals("com/baomidou/mybatisplus/core/toolkit/support/SFunction") || !serializedLambda.getFunctionalInterfaceMethodName().equals("apply") || !serializedLambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;)Ljava/lang/Object;") || !serializedLambda.getImplClass().equals("org/jeecg/modules/online/cgreport/entity/OnlCgreportHead") || !serializedLambda.getImplMethodSignature().equals("()Ljava/lang/String;"))
                    break;
                return OnlCgreportHead::getCode;
            }
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }
}

