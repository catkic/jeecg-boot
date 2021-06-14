/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
 *  com.baomidou.mybatisplus.core.metadata.IPage
 *  com.baomidou.mybatisplus.extension.plugins.pagination.Page
 *  javax.servlet.http.HttpServletRequest
 *  org.apache.commons.lang.StringUtils
 *  org.jeecg.common.api.vo.Result
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.system.query.QueryGenerator
 *  org.jeecg.common.system.vo.DynamicDataSourceModel
 *  org.jeecg.common.util.SqlInjectionUtil
 *  org.jeecg.modules.base.service.BaseCommonService
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.cache.annotation.CacheEvict
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package org.jeecg.modules.online.cgreport.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.DynamicDataSourceModel;
import org.jeecg.common.util.SqlInjectionUtil;
import org.jeecg.modules.base.service.BaseCommonService;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.model.OnlCgreportModel;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportHeadService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/online/cgreport/head"})
@Slf4j
public class OnlCgreportHeadController {
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IOnlCgreportHeadService onlCgreportHeadService;
    @Autowired
    private IOnlCgreportParamService onlCgreportParamService;
    @Autowired
    private IOnlCgreportItemService onlCgreportItemService;
    @Autowired
    private BaseCommonService baseCommonService;

    @GetMapping(value={"/parseSql"})
    public Result<?> parseSql(@RequestParam(name="sql") String string, @RequestParam(name="dbKey", required=false) String dbSource) {
        DynamicDataSourceModel dynamicDataSourceModel = this.sysBaseAPI.getDynamicDbSourceByCode(dbSource);
        if (StringUtils.isNotBlank(dbSource) && dynamicDataSourceModel == null) {
            return Result.error((String)"\u6570\u636e\u6e90\u4e0d\u5b58\u5728");
        }
//        dynamicDataSourceModel = new HashMap();
        JSONObject hashMap = new JSONObject();
        List<OnlCgreportItem> arrayList = new ArrayList<OnlCgreportItem>();
        List<OnlCgreportParam> arrayList2 = new ArrayList<OnlCgreportParam>();
        List<String> list = null;
        List<String> list2 = null;
        try {
            Serializable serializable;
            log.info("Online\u62a5\u8868\uff0csql\u89e3\u6790\uff1a" + string);
            this.baseCommonService.addLog("Online\u62a5\u8868\uff0csql\u89e3\u6790\uff1a" + string, Integer.valueOf(2), Integer.valueOf(2));
            SqlInjectionUtil.specialFilterContentForOnlineReport((String)string);
            list = this.onlCgreportHeadService.getSqlFields(string, dbSource);
            list2 = this.onlCgreportHeadService.getSqlParams(string);
            int n2 = 1;
            for (String string3 : list) {
                OnlCgreportItem onlCgreportItem = new OnlCgreportItem();
                onlCgreportItem.setFieldName(string3.toLowerCase());
                onlCgreportItem.setFieldTxt(string3);
                onlCgreportItem.setShow(true);
                onlCgreportItem.setOrderNum(n2);
                onlCgreportItem.setId(org.jeecg.modules.online.cgform.util.b.a());
                onlCgreportItem.setFieldType("String");
                arrayList.add(onlCgreportItem);
                ++n2;
            }
            for (String string3 : list2) {
                OnlCgreportParam onlCgreportParam = new OnlCgreportParam();
                onlCgreportParam.setParamName(string3);
                onlCgreportParam.setParamTxt(string3);
                arrayList2.add(onlCgreportParam);
            }
            hashMap.put("fields", arrayList);
            hashMap.put("params", arrayList2);
        }
        catch (Exception exception) {
            log.error(exception.getMessage(), (Throwable)exception);
            String string4 = "\u89e3\u6790\u5931\u8d25\uff0c";
            int n3 = exception.getMessage().indexOf("Connection refused: connect");
            string4 = n3 != -1 ? string4 + "\u6570\u636e\u6e90\u8fde\u63a5\u5931\u8d25." : (exception.getMessage().indexOf("\u503c\u53ef\u80fd\u5b58\u5728SQL\u6ce8\u5165\u98ce\u9669") != -1 ? string4 + "SQL\u53ef\u80fd\u5b58\u5728SQL\u6ce8\u5165\u98ce\u9669." : (exception.getMessage().indexOf("\u8be5\u62a5\u8868sql\u6ca1\u6709\u6570\u636e") != -1 ? string4 + "\u62a5\u8868sql\u67e5\u8be2\u6570\u636e\u4e3a\u7a7a\uff0c\u65e0\u6cd5\u89e3\u6790\u5b57\u6bb5." : (exception.getMessage().indexOf("SqlServer\u4e0d\u652f\u6301SQL\u5185\u6392\u5e8f") != -1 ? string4 + "SqlServer\u4e0d\u652f\u6301SQL\u5185\u6392\u5e8f." : string4 + "SQL\u8bed\u6cd5\u9519\u8bef.")));
            return Result.error(string4);
        }
        return Result.ok(dynamicDataSourceModel);
    }

    @GetMapping(value={"/list"})
    public Result<IPage<OnlCgreportHead>> list(OnlCgreportHead onlCgreportHead, @RequestParam(name="pageNo", defaultValue="1") Integer n2, @RequestParam(name="pageSize", defaultValue="10") Integer n3, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper((Object)onlCgreportHead, httpServletRequest.getParameterMap());
        Page page = new Page((long)n2.intValue(), (long)n3.intValue());
        IPage iPage = this.onlCgreportHeadService.page((IPage)page, (Wrapper)queryWrapper);
        result.setSuccess(true);
        result.setResult((Object)iPage);
        return result;
    }

    @PostMapping(value={"/add"})
    public Result<?> add(@RequestBody OnlCgreportModel onlCgreportModel) {
        Result result = new Result();
        try {
            String string = org.jeecg.modules.online.cgform.util.b.a();
            OnlCgreportHead onlCgreportHead = onlCgreportModel.getHead();
            List<OnlCgreportParam> list = onlCgreportModel.getParams();
            List<OnlCgreportItem> list2 = onlCgreportModel.getItems();
            onlCgreportHead.setId(string);
            for (OnlCgreportParam serializable : list) {
                serializable.setId(null);
                serializable.setCgrheadId(string);
            }
            for (OnlCgreportItem onlCgreportItem : list2) {
                onlCgreportItem.setId(null);
                onlCgreportItem.setFieldName(onlCgreportItem.getFieldName().trim().toLowerCase());
                onlCgreportItem.setCgrheadId(string);
            }
            this.onlCgreportHeadService.save(onlCgreportHead);
            this.onlCgreportParamService.saveBatch(list);
            this.onlCgreportItemService.saveBatch(list2);
            result.success("\u6dfb\u52a0\u6210\u529f\uff01");
        }
        catch (Exception exception) {
            log.error(exception.getMessage(), (Throwable)exception);
            result.error500("\u64cd\u4f5c\u5931\u8d25");
        }
        return result;
    }

    @PutMapping(value={"/editAll"})
    @CacheEvict(value={"sys:cache:online:rp"}, allEntries=true, beforeInvocation=true)
    public Result<?> editAll(@RequestBody OnlCgreportModel onlCgreportModel) {
        try {
            return this.onlCgreportHeadService.editAll(onlCgreportModel);
        }
        catch (Exception exception) {
            log.error(exception.getMessage(), (Throwable)exception);
            return Result.error((String)"\u64cd\u4f5c\u5931\u8d25");
        }
    }

    @DeleteMapping(value={"/delete"})
    public Result<?> delete(@RequestParam(name="id", required=true) String string) {
        return this.onlCgreportHeadService.delete(string);
    }

    @DeleteMapping(value={"/deleteBatch"})
    public Result<?> deleteBatch(@RequestParam(name="ids", required=true) String string) {
        return this.onlCgreportHeadService.bathDelete(string.split(","));
    }

    @GetMapping(value={"/queryById"})
    public Result<OnlCgreportHead> queryById(@RequestParam(name="id", required=true) String string) {
        Result result = new Result();
        OnlCgreportHead onlCgreportHead = (OnlCgreportHead)this.onlCgreportHeadService.getById((Serializable)((Object)string));
        result.setResult((Object)onlCgreportHead);
        return result;
    }
}

