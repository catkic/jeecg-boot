/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
 *  com.baomidou.mybatisplus.core.metadata.IPage
 *  com.baomidou.mybatisplus.extension.plugins.pagination.Page
 *  javax.servlet.http.HttpServletRequest
 *  org.jeecg.common.api.vo.Result
 *  org.jeecg.common.system.query.QueryGenerator
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.cache.annotation.CacheEvict
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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.service.IOnlCgformButtonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="onlCgformButtonController")
@RequestMapping(value={"/online/cgform/button"})
public class OnlCgformButtonController {
    private static final Logger a = LoggerFactory.getLogger(OnlCgformButtonController.class);
    @Autowired
    private IOnlCgformButtonService onlCgformButtonService;

    @GetMapping(value={"/list/{code}"})
    public Result<IPage<OnlCgformButton>> a(OnlCgformButton onlCgformButton, @RequestParam(name="pageNo", defaultValue="1") Integer n2, @RequestParam(name="pageSize", defaultValue="10") Integer n3, HttpServletRequest httpServletRequest, @PathVariable(value="code") String string) {
        Result result = new Result();
        onlCgformButton.setCgformHeadId(string);
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper((Object)onlCgformButton, (Map)httpServletRequest.getParameterMap());
        Page page = new Page((long)n2.intValue(), (long)n3.intValue());
        IPage iPage = this.onlCgformButtonService.page((IPage)page, (Wrapper)queryWrapper);
        result.setSuccess(true);
        result.setResult((Object)iPage);
        return result;
    }

    @PostMapping(value={"/add"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<OnlCgformButton> a(@RequestBody OnlCgformButton onlCgformButton) {
        Result result = new Result();
        try {
            this.onlCgformButtonService.save(onlCgformButton);
            result.success("添加成功！");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            result.error500("操作失败");
        }
        return result;
    }

    @PostMapping(value={"/aitest"})
    public Result<OnlCgformButton> a(@RequestBody JSONArray jSONArray) {
        Result result = new Result();
        try {
            for (int i2 = 0; i2 < jSONArray.size(); ++i2) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                OnlCgformButton onlCgformButton = (OnlCgformButton)JSONObject.parseObject((String)jSONObject.toJSONString(), OnlCgformButton.class);
                this.onlCgformButtonService.saveButton(onlCgformButton);
            }
            result.success("添加成功！");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            result.error500("操作失败");
        }
        return result;
    }

    @PutMapping(value={"/edit"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<OnlCgformButton> b(@RequestBody OnlCgformButton onlCgformButton) {
        Result result = new Result();
        OnlCgformButton onlCgformButton2 = (OnlCgformButton)this.onlCgformButtonService.getById((Serializable)((Object)onlCgformButton.getId()));
        if (onlCgformButton2 == null) {
            result.error500("未找到对应实体");
        } else {
            boolean bl = this.onlCgformButtonService.updateById(onlCgformButton);
            if (bl) {
                result.success("修改成功!");
            }
        }
        return result;
    }

    @DeleteMapping(value={"/delete"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<OnlCgformButton> a(@RequestParam(name="id", required=true) String string) {
        Result result = new Result();
        OnlCgformButton onlCgformButton = (OnlCgformButton)this.onlCgformButtonService.getById((Serializable)((Object)string));
        if (onlCgformButton == null) {
            result.error500("未找到对应实体");
        } else {
            boolean bl = this.onlCgformButtonService.removeById((Serializable)((Object)string));
            if (bl) {
                result.success("删除成功!");
            }
        }
        return result;
    }

    @DeleteMapping(value={"/deleteBatch"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<OnlCgformButton> b(@RequestParam(name="ids", required=true) String string) {
        Result result = new Result();
        if (string == null || "".equals(string.trim())) {
            result.error500("参数不识别！");
        } else {
            this.onlCgformButtonService.removeByIds(Arrays.asList(string.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    @GetMapping(value={"/queryById"})
    public Result<OnlCgformButton> c(@RequestParam(name="id", required=true) String string) {
        Result result = new Result();
        OnlCgformButton onlCgformButton = (OnlCgformButton)this.onlCgformButtonService.getById((Serializable)((Object)string));
        if (onlCgformButton == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult((Object)onlCgformButton);
            result.setSuccess(true);
        }
        return result;
    }
}


