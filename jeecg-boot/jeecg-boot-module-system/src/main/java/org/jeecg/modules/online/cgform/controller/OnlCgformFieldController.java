/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
 *  com.baomidou.mybatisplus.core.metadata.IPage
 *  com.baomidou.mybatisplus.extension.plugins.pagination.Page
 *  javax.servlet.http.HttpServletRequest
 *  org.jeecg.common.api.vo.Result
 *  org.jeecg.common.system.query.QueryGenerator
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.web.bind.annotation.DeleteMapping
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.PutMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package org.jeecg.modules.online.cgform.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="onlCgformFieldController")
@RequestMapping(value={"/online/cgform/field"})
public class OnlCgformFieldController {
    private static final Logger a = LoggerFactory.getLogger(OnlCgformFieldController.class);
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;

    @GetMapping(value={"/listByHeadCode"})
    public Result<?> a(@RequestParam(value="headCode") String string) {
        LambdaQueryWrapper<OnlCgformHead> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformHead>();
        lambdaQueryWrapper.eq(OnlCgformHead::getTableName, (Object)string);
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)lambdaQueryWrapper);
        if (onlCgformHead == null) {
            return Result.error((String)"不存在的code");
        }
        return this.b(onlCgformHead.getId());
    }

    @GetMapping(value={"/listByHeadId"})
    public Result<?> b(@RequestParam(value="headId") String string) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq((Object)"cgform_head_id", (Object)string);
        queryWrapper.orderByAsc((Object)"order_num");
        List list = this.onlCgformFieldService.list((Wrapper)queryWrapper);
        return Result.ok((Object)list);
    }

    @GetMapping(value={"/list"})
    public Result<IPage<OnlCgformField>> a(OnlCgformField onlCgformField, @RequestParam(name="pageNo", defaultValue="1") Integer n2, @RequestParam(name="pageSize", defaultValue="10") Integer n3, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper((Object)onlCgformField, (Map)httpServletRequest.getParameterMap());
        Page page = new Page((long)n2.intValue(), (long)n3.intValue());
        IPage iPage = this.onlCgformFieldService.page((IPage)page, (Wrapper)queryWrapper);
        result.setSuccess(true);
        result.setResult((Object)iPage);
        return result;
    }

    @PostMapping(value={"/add"})
    public Result<OnlCgformField> a(@RequestBody OnlCgformField onlCgformField) {
        Result result = new Result();
        try {
            this.onlCgformFieldService.save(onlCgformField);
            result.success("添加成功！");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            result.error500("操作失败");
        }
        return result;
    }

    @PutMapping(value={"/edit"})
    public Result<OnlCgformField> b(@RequestBody OnlCgformField onlCgformField) {
        Result result = new Result();
        OnlCgformField onlCgformField2 = (OnlCgformField)this.onlCgformFieldService.getById((Serializable)((Object)onlCgformField.getId()));
        if (onlCgformField2 == null) {
            result.error500("未找到对应实体");
        } else {
            boolean bl = this.onlCgformFieldService.updateById(onlCgformField);
            if (bl) {
                result.success("修改成功!");
            }
        }
        return result;
    }

    @DeleteMapping(value={"/delete"})
    public Result<OnlCgformField> c(@RequestParam(name="id", required=true) String string) {
        Result result = new Result();
        OnlCgformField onlCgformField = (OnlCgformField)this.onlCgformFieldService.getById((Serializable)((Object)string));
        if (onlCgformField == null) {
            result.error500("未找到对应实体");
        } else {
            boolean bl = this.onlCgformFieldService.removeById((Serializable)((Object)string));
            if (bl) {
                result.success("删除成功!");
            }
        }
        return result;
    }

    @DeleteMapping(value={"/deleteBatch"})
    public Result<OnlCgformField> d(@RequestParam(name="ids", required=true) String string) {
        Result result = new Result();
        if (string == null || "".equals(string.trim())) {
            result.error500("参数不识别！");
        } else {
            this.onlCgformFieldService.removeByIds(Arrays.asList(string.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    @GetMapping(value={"/queryById"})
    public Result<OnlCgformField> e(@RequestParam(name="id", required=true) String string) {
        Result result = new Result();
        OnlCgformField onlCgformField = (OnlCgformField)this.onlCgformFieldService.getById((Serializable)((Object)string));
        if (onlCgformField == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult((Object)onlCgformField);
            result.setSuccess(true);
        }
        return result;
    }
}

