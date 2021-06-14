/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
 *  com.baomidou.mybatisplus.core.metadata.IPage
 *  com.baomidou.mybatisplus.extension.plugins.pagination.Page
 *  javax.servlet.http.HttpServletRequest
 *  org.jeecg.common.api.vo.Result
 *  org.jeecg.common.constant.enums.CgformEnum
 *  org.jeecg.common.system.query.QueryGenerator
 *  org.jeecg.common.system.util.JwtUtil
 *  org.jeecg.common.util.oConvertUtils
 *  org.jeecgframework.codegenerate.a.a
 *  org.jeecgframework.codegenerate.database.DbReadTableUtil
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.cache.annotation.CacheEvict
 *  org.springframework.core.io.Resource
 *  org.springframework.core.io.ResourceLoader
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PathVariable
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package org.jeecg.modules.online.cgform.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.enums.CgformEnum;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.util.JwtUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceSql;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.service.IOnlCgformEnhanceService;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.config.exception.DBException;
import org.jeecgframework.codegenerate.database.DbReadTableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="onlCgformHeadController")
@RequestMapping(value={"/online/cgform/head"})
public class OnlCgformHeadController {
    private static final Logger a = LoggerFactory.getLogger(OnlCgformHeadController.class);
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    private IOnlCgformEnhanceService onlCgformEnhanceService;
    private static List<String> b = null;
    @Autowired
    ResourceLoader resourceLoader;
    private static String c;

    @GetMapping(value={"/list"})
    public Result<IPage<OnlCgformHead>> a(OnlCgformHead onlCgformHead, @RequestParam(name="pageNo", defaultValue="1") Integer n2, @RequestParam(name="pageSize", defaultValue="10") Integer n3, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper((Object)onlCgformHead, (Map)httpServletRequest.getParameterMap());
        Page page = new Page((long)n2.intValue(), (long)n3.intValue());
        IPage iPage = this.onlCgformHeadService.page((IPage)page, (Wrapper)queryWrapper);
        if (onlCgformHead.getCopyType() != null && onlCgformHead.getCopyType() == 0) {
            this.onlCgformHeadService.initCopyState(iPage.getRecords());
        }
        result.setSuccess(true);
        result.setResult((Object)iPage);
        return result;
    }

    @PostMapping(value={"/add"})
    public Result<OnlCgformHead> a(@RequestBody OnlCgformHead onlCgformHead) {
        Result result = new Result();
        try {
            this.onlCgformHeadService.save(onlCgformHead);
            result.success("添加成功！");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            result.error500("操作失败");
        }
        return result;
    }

    @PutMapping(value={"/edit"})
    public Result<OnlCgformHead> b(@RequestBody OnlCgformHead onlCgformHead) {
        Result result = new Result();
        OnlCgformHead onlCgformHead2 = (OnlCgformHead)this.onlCgformHeadService.getById((Serializable)((Object)onlCgformHead.getId()));
        if (onlCgformHead2 == null) {
            result.error500("未找到对应实体");
        } else {
            boolean bl = this.onlCgformHeadService.updateById(onlCgformHead);
            if (bl) {
                result.success("修改成功!");
            }
        }
        return result;
    }

    @DeleteMapping(value={"/delete"})
    public Result<?> a(@RequestParam(name="id", required=true) String string) {
        try {
            this.onlCgformHeadService.deleteRecordAndTable(string);
        }
        catch (DBException dBException) {
            return Result.error((String)("删除失败" + dBException.getMessage()));
        }
        catch (SQLException sQLException) {
            return Result.error((String)("删除失败" + sQLException.getMessage()));
        }
        return Result.ok((String)"删除成功!");
    }

    @DeleteMapping(value={"/removeRecord"})
    public Result<?> b(@RequestParam(name="id", required=true) String string) {
        try {
            this.onlCgformHeadService.deleteRecord(string);
        }
        catch (DBException dBException) {
            return Result.error((String)("移除失败" + dBException.getMessage()));
        }
        catch (SQLException sQLException) {
            return Result.error((String)("移除失败" + sQLException.getMessage()));
        }
        return Result.ok((String)"移除成功!");
    }

    @DeleteMapping(value={"/deleteBatch"})
    public Result<OnlCgformHead> a(@RequestParam(name="ids", required=true) String string, @RequestParam(name="flag") String string2) {
        Result result = new Result();
        if (string == null || "".equals(string.trim())) {
            result.error500("参数不识别！");
        } else {
            this.onlCgformHeadService.deleteBatch(string, string2);
            result.success("删除成功!");
        }
        return result;
    }

    @GetMapping(value={"/queryById"})
    public Result<OnlCgformHead> c(@RequestParam(name="id", required=true) String string) {
        Result result = new Result();
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.onlCgformHeadService.getById((Serializable)((Object)string));
        if (onlCgformHead == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult((Object)onlCgformHead);
            result.setSuccess(true);
        }
        return result;
    }

    @GetMapping(value={"/queryByTableNames"})
    public Result<?> d(@RequestParam(name="tableNames", required=true) String string) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        String[] arrstring = string.split(",");
        lambdaQueryWrapper.in(OnlCgformHead::getTableName, Arrays.asList(arrstring));
        List list = this.onlCgformHeadService.list((Wrapper)lambdaQueryWrapper);
        if (list == null) {
            return Result.error((String)"未找到对应实体");
        }
        return Result.ok((Object)list);
    }

    @PostMapping(value={"/enhanceJs/{code}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> a(@PathVariable(value="code") String string, @RequestBody OnlCgformEnhanceJs onlCgformEnhanceJs) {
        try {
            onlCgformEnhanceJs.setCgformHeadId(string);
            this.onlCgformHeadService.saveEnhance(onlCgformEnhanceJs);
            return Result.ok((String)"保存成功!");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"保存失败!");
        }
    }

    @GetMapping(value={"/enhanceJs/{code}"})
    public Result<?> a(@PathVariable(value="code") String string, HttpServletRequest httpServletRequest) {
        try {
            String string2 = httpServletRequest.getParameter("type");
            OnlCgformEnhanceJs onlCgformEnhanceJs = this.onlCgformHeadService.queryEnhance(string, string2);
            if (onlCgformEnhanceJs == null) {
                return Result.error((String)"查询为空");
            }
            return Result.ok((Object)onlCgformEnhanceJs);
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"查询失败!");
        }
    }

    @PutMapping(value={"/enhanceJs/{code}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> b(@PathVariable(value="code") String string, @RequestBody OnlCgformEnhanceJs onlCgformEnhanceJs) {
        try {
            onlCgformEnhanceJs.setCgformHeadId(string);
            this.onlCgformHeadService.editEnhance(onlCgformEnhanceJs);
            return Result.ok((String)"保存成功!");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"保存失败!");
        }
    }

    @GetMapping(value={"/enhanceButton/{formId}"})
    public Result<?> b(@PathVariable(value="formId") String string, HttpServletRequest httpServletRequest) {
        try {
            List<OnlCgformButton> list = this.onlCgformHeadService.queryButtonList(string);
            if (list == null || list.size() == 0) {
                return Result.error((String)"查询为空");
            }
            return Result.ok(list);
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"查询失败!");
        }
    }

    @GetMapping(value={"/enhanceSql/{formId}"})
    public Result<?> c(@PathVariable(value="formId") String string, HttpServletRequest httpServletRequest) {
        List<OnlCgformEnhanceSql> list = this.onlCgformEnhanceService.queryEnhanceSqlList(string);
        return Result.OK(list);
    }

    @PostMapping(value={"/enhanceSql/{formId}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> a(@PathVariable(value="formId") String string, @RequestBody OnlCgformEnhanceSql onlCgformEnhanceSql) {
        try {
            onlCgformEnhanceSql.setCgformHeadId(string);
            if (this.onlCgformEnhanceService.checkOnlyEnhance(onlCgformEnhanceSql)) {
                this.onlCgformEnhanceService.saveEnhanceSql(onlCgformEnhanceSql);
                return Result.ok((String)"保存成功!");
            }
            return Result.error((String)"保存失败,该按钮已存在增强配置!");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"保存失败!");
        }
    }

    @PutMapping(value={"/enhanceSql/{formId}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> b(@PathVariable(value="formId") String string, @RequestBody OnlCgformEnhanceSql onlCgformEnhanceSql) {
        try {
            onlCgformEnhanceSql.setCgformHeadId(string);
            if (this.onlCgformEnhanceService.checkOnlyEnhance(onlCgformEnhanceSql)) {
                this.onlCgformEnhanceService.updateEnhanceSql(onlCgformEnhanceSql);
                return Result.ok((String)"保存成功!");
            }
            return Result.error((String)"保存失败,该按钮已存在增强配置!");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"保存失败!");
        }
    }

    @DeleteMapping(value={"/enhanceSql"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> e(@RequestParam(name="id", required=true) String string) {
        try {
            this.onlCgformEnhanceService.deleteEnhanceSql(string);
            return Result.ok((String)"删除成功");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"删除失败!");
        }
    }

    @DeleteMapping(value={"/deletebatchEnhanceSql"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> f(@RequestParam(name="ids", required=true) String string) {
        try {
            List<String> list = Arrays.asList(string.split(","));
            this.onlCgformEnhanceService.deleteBatchEnhanceSql(list);
            return Result.ok((String)"删除成功");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"删除失败!");
        }
    }

    @GetMapping(value={"/enhanceJava/{formId}"})
    public Result<?> a(@PathVariable(value="formId") String string, OnlCgformEnhanceJava onlCgformEnhanceJava) {
        List<OnlCgformEnhanceJava> list = this.onlCgformEnhanceService.queryEnhanceJavaList(string);
        return Result.OK(list);
    }

    @PostMapping(value={"/enhanceJava/{formId}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> b(@PathVariable(value="formId") String string, @RequestBody OnlCgformEnhanceJava onlCgformEnhanceJava) {
        try {
            if (!org.jeecg.modules.online.cgform.util.b.a(onlCgformEnhanceJava)) {
                return Result.error((String)"类实例化失败，请检查!");
            }
            onlCgformEnhanceJava.setCgformHeadId(string);
            if (this.onlCgformEnhanceService.checkOnlyEnhance(onlCgformEnhanceJava)) {
                this.onlCgformEnhanceService.saveEnhanceJava(onlCgformEnhanceJava);
                return Result.ok((String)"保存成功!");
            }
            return Result.error((String)"保存失败,配置重复了!");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"保存失败!");
        }
    }

    @PutMapping(value={"/enhanceJava/{formId}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> c(@PathVariable(value="formId") String string, @RequestBody OnlCgformEnhanceJava onlCgformEnhanceJava) {
        try {
            if (!org.jeecg.modules.online.cgform.util.b.a(onlCgformEnhanceJava)) {
                return Result.error((String)"类实例化失败，请检查!");
            }
            onlCgformEnhanceJava.setCgformHeadId(string);
            if (this.onlCgformEnhanceService.checkOnlyEnhance(onlCgformEnhanceJava)) {
                this.onlCgformEnhanceService.updateEnhanceJava(onlCgformEnhanceJava);
                return Result.ok((String)"保存成功!");
            }
            return Result.error((String)"保存失败,配置重复了!");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"保存失败!");
        }
    }

    @DeleteMapping(value={"/enhanceJava"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> g(@RequestParam(name="id", required=true) String string) {
        try {
            this.onlCgformEnhanceService.deleteEnhanceJava(string);
            return Result.ok((String)"删除成功");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"删除失败!");
        }
    }

    @DeleteMapping(value={"/deleteBatchEnhanceJava"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> h(@RequestParam(name="ids", required=true) String string) {
        try {
            List<String> list = Arrays.asList(string.split(","));
            this.onlCgformEnhanceService.deleteBatchEnhanceJava(list);
            return Result.ok((String)"删除成功");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"删除失败!");
        }
    }

    @GetMapping(value={"/queryTables"})
    public Result<?> a(@RequestParam(name="tableName", required=false) String string, @RequestParam(name="pageNo", defaultValue="1") Integer n2, @RequestParam(name="pageSize", defaultValue="10") Integer n3, HttpServletRequest httpServletRequest) {
        String string2 = JwtUtil.getUserNameByToken((HttpServletRequest)httpServletRequest);
        if (!"admin".equals(string2)) {
            return Result.error((String)"noadminauth");
        }
        List<Object> list = new ArrayList();
        try {
            list = DbReadTableUtil.a();
        }
        catch (SQLException sQLException) {
            a.error(sQLException.getMessage(), (Throwable)sQLException);
            return Result.error((String)"同步失败，未获取数据库表信息");
        }
        org.jeecg.modules.online.cgform.util.b.b(list);
        list = org.jeecg.modules.online.cgform.util.b.f(list);
        List<String> list2 = this.onlCgformHeadService.queryOnlinetables();
        this.b();
        list.removeAll(list2);
        ArrayList arrayList = new ArrayList();
        for (String string3 : list) {
            if (this.l(string3)) continue;
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("id", string3);
            arrayList.add(hashMap);
        }
        return Result.ok(arrayList);
    }

    @PostMapping(value={"/transTables/{tbnames}"})
    public Result<?> d(@PathVariable(value="tbnames") String string, HttpServletRequest httpServletRequest) {
        String string2 = JwtUtil.getUserNameByToken((HttpServletRequest)httpServletRequest);
        if (!"admin".equals(string2)) {
            return Result.error((String)"noadminauth");
        }
        if (oConvertUtils.isEmpty((Object)string)) {
            return Result.error((String)"未识别的表名信息");
        }
        if (c != null && c.equals(string)) {
            return Result.error((String)"不允许重复生成!");
        }
        c = string;
        String[] arrstring = string.split(",");
        for (int i2 = 0; i2 < arrstring.length; ++i2) {
            int n2;
            if (!oConvertUtils.isNotEmpty((Object)arrstring[i2]) || (n2 = this.onlCgformHeadService.count((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)arrstring[i2]))) > 0) continue;
            a.info("[IP] [online数据库导入表]   --表名：" + arrstring[i2]);
            this.onlCgformHeadService.saveDbTable2Online(arrstring[i2]);
        }
        c = null;
        return Result.ok((String)"同步完成!");
    }

    @GetMapping(value={"/rootFile"})
    public Result<?> a() {
        File[] arrfile;
        JSONArray jSONArray = new JSONArray();
        for (File file : arrfile = File.listRoots()) {
            JSONObject jSONObject = new JSONObject();
            if (file.isDirectory()) {
                jSONObject.put("key", (Object)file.getAbsolutePath());
                jSONObject.put("title", (Object)file.getPath());
                jSONObject.put("opened", (Object)false);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("icon", (Object)"custom");
                jSONObject.put("scopedSlots", (Object)jSONObject2);
                jSONObject.put("isLeaf", (Object)(file.listFiles() == null || file.listFiles().length == 0 ? 1 : 0));
            }
            jSONArray.add((Object)jSONObject);
        }
        return Result.ok((Object)jSONArray);
    }

    @GetMapping(value={"/fileTree"})
    public Result<?> i(@RequestParam(name="parentPath", required=true) String string) {
        File[] arrfile;
        JSONArray jSONArray = new JSONArray();
        File file = new File(string);
        for (File file2 : arrfile = file.listFiles()) {
            if (!file2.isDirectory() || !oConvertUtils.isNotEmpty((Object)file2.getPath())) continue;
            JSONObject jSONObject = new JSONObject();
            System.out.println(file2.getPath());
            jSONObject.put("key", (Object)file2.getAbsolutePath());
            jSONObject.put("title", (Object)file2.getPath().substring(file2.getPath().lastIndexOf(File.separator) + 1));
            jSONObject.put("isLeaf", (Object)(file2.listFiles() == null || file2.listFiles().length == 0 ? 1 : 0));
            jSONObject.put("opened", (Object)false);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("icon", (Object)"custom");
            jSONObject.put("scopedSlots", (Object)jSONObject2);
            jSONArray.add((Object)jSONObject);
        }
        return Result.ok((Object)jSONArray);
    }

    @GetMapping(value={"/tableInfo"})
    public Result<?> j(@RequestParam(name="code", required=true) String string) {
        List<OnlCgformHead> list;
        Object object;
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.onlCgformHeadService.getById((Serializable)((Object)string));
        if (onlCgformHead == null) {
            return Result.error((String)"未找到对应实体");
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("main", onlCgformHead);
        if (onlCgformHead.getTableType() == 2 && oConvertUtils.isNotEmpty((Object)(object = onlCgformHead.getSubTableStr()))) {
            String[] arrstring;
            list = new ArrayList();
            for (String string2 : arrstring = ((String)object).split(",")) {
                LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
                lambdaQueryWrapper.eq(OnlCgformHead::getTableName, (Object)string2);
                OnlCgformHead onlCgformHead2 = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)lambdaQueryWrapper);
                list.add(onlCgformHead2);
            }
            Collections.sort(list, new Comparator<OnlCgformHead>(){
                @Override

                public int compare(OnlCgformHead onlCgformHead, OnlCgformHead onlCgformHead2) {
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

            });
            hashMap.put("sub", list);
        }
        object = onlCgformHead.getTableType();
        if ("Y".equals(onlCgformHead.getIsTree())) {
            object = 3;
        }
        list = CgformEnum.getJspModelList((int)((Integer)object));
        hashMap.put("jspModeList", list);
        hashMap.put("projectPath", org.jeecgframework.codegenerate.a.a.m());
        return Result.ok(hashMap);
    }

    @PostMapping(value={"/copyOnline"})
    public Result<?> k(@RequestParam(name="code", required=true) String string) {
        try {
            OnlCgformHead onlCgformHead = (OnlCgformHead)this.onlCgformHeadService.getById((Serializable)((Object)string));
            if (onlCgformHead == null) {
                return Result.error((String)"未找到对应实体");
            }
            this.onlCgformHeadService.copyOnlineTableConfig(onlCgformHead);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return Result.ok();
    }

    private boolean l(String string) {
        for (String string2 : b) {
            if (!string.startsWith(string2) && !string.startsWith(string2.toUpperCase())) continue;
            return true;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void b() {
        if (b == null) {
            Resource resource = this.resourceLoader.getResource("classpath:jeecg" + File.separator + "jeecg_config.properties");
            InputStream inputStream = null;
            try {
                inputStream = resource.getInputStream();
                Properties properties = new Properties();
                properties.load(inputStream);
                String string = properties.getProperty("exclude_table");
                if (string != null) {
                    b = Arrays.asList(string.split(","));
                }
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
            finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (IOException iOException) {
                        iOException.printStackTrace();
                    }
                }
            }
        }
    }
}



