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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
            return Result.error("数据源不存在");
        }
//        dynamicDataSourceModel = new HashMap();
        JSONObject hashMap = new JSONObject();
        List<OnlCgreportItem> arrayList = new ArrayList<OnlCgreportItem>();
        List<OnlCgreportParam> arrayList2 = new ArrayList<OnlCgreportParam>();
        List<String> list = null;
        List<String> list2 = null;
        try {
            Serializable serializable;
            log.info("Online报表，sql解析：" + string);
            this.baseCommonService.addLog("Online报表，sql解析：" + string, Integer.valueOf(2), Integer.valueOf(2));
            SqlInjectionUtil.specialFilterContentForOnlineReport(string);
            list = this.onlCgreportHeadService.getSqlFields(string, dbSource);
            list2 = this.onlCgreportHeadService.getSqlParams(string);
            int n2 = 1;
            for (String string3 : list) {
                OnlCgreportItem onlCgreportItem = new OnlCgreportItem();
                onlCgreportItem.setFieldName(string3.toLowerCase());
                onlCgreportItem.setFieldTxt(string3);
                onlCgreportItem.setIsShow(true);
                onlCgreportItem.setOrderNum(n2);
                onlCgreportItem.setId(org.jeecg.modules.online.cgform.util.DataBaseUtils.generateIdByIdWorker());
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
            log.error(exception.getMessage(), exception);
            String string4 = "解析失败，";
            int n3 = exception.getMessage().indexOf("Connection refused: connect");
            string4 = n3 != -1 ? string4 + "数据源连接失败." : (exception.getMessage().indexOf("值可能存在SQL注入风险") != -1 ? string4 + "SQL可能存在SQL注入风险." : (exception.getMessage().indexOf("该报表sql没有数据") != -1 ? string4 + "报表sql查询数据为空，无法解析字段." : (exception.getMessage().indexOf("SqlServer不支持SQL内排序") != -1 ? string4 + "SqlServer不支持SQL内排序." : string4 + "SQL语法错误.")));
            return Result.error(string4);
        }
        return Result.ok(dynamicDataSourceModel);
    }

    @GetMapping(value={"/list"})
    public Result<IPage<OnlCgreportHead>> list(OnlCgreportHead onlCgreportHead, @RequestParam(name="pageNo", defaultValue="1") Integer n2, @RequestParam(name="pageSize", defaultValue="10") Integer n3, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        QueryWrapper<OnlCgreportHead> queryWrapper = QueryGenerator.initQueryWrapper(onlCgreportHead, httpServletRequest.getParameterMap());
        Page<OnlCgreportHead> page = new Page<>(n2, n3);
        IPage<OnlCgreportHead> iPage = this.onlCgreportHeadService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(iPage);
        return result;
    }

    @PostMapping(value={"/add"})
    public Result<?> add(@RequestBody OnlCgreportModel onlCgreportModel) {
        Result result = new Result();
        try {
            String string = org.jeecg.modules.online.cgform.util.DataBaseUtils.generateIdByIdWorker();
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
            result.success("添加成功！");
        }
        catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            result.error500("操作失败");
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
            log.error(exception.getMessage(), exception);
            return Result.error("操作失败");
        }
    }

    @DeleteMapping(value={"/delete"})
    public Result<?> delete(@RequestParam(name="id") String string) {
        return this.onlCgreportHeadService.delete(string);
    }

    @DeleteMapping(value={"/deleteBatch"})
    public Result<?> deleteBatch(@RequestParam(name="ids") String string) {
        return this.onlCgreportHeadService.bathDelete(string.split(","));
    }

    @GetMapping(value={"/queryById"})
    public Result<OnlCgreportHead> queryById(@RequestParam(name="id", required=true) String string) {
        Result result = new Result();
        OnlCgreportHead onlCgreportHead = this.onlCgreportHeadService.getById(string);
        result.setResult(onlCgreportHead);
        return result;
    }
}
