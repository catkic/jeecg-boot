/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
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

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
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

@RestController
@RequestMapping(value = {"/online/cgreport/param"})
@Slf4j
public class OnlCgreportParamController {
    @Autowired
    private IOnlCgreportParamService onlCgreportParamService;

    @GetMapping(value = {"/listByHeadId"})
    public Result<?> a(@RequestParam(value = "headId") String string) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cgrhead_id", string);
        queryWrapper.orderByAsc("order_num");
        List list = this.onlCgreportParamService.list(queryWrapper);
        Result result = new Result();
        result.setSuccess(true);
        result.setResult(list);
        return result;
    }

    @GetMapping(value = {"/list"})
    public Result<IPage<OnlCgreportParam>> a(OnlCgreportParam onlCgreportParam, @RequestParam(name = "pageNo", defaultValue = "1") Integer n2, @RequestParam(name = "pageSize", defaultValue = "10") Integer n3, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        QueryWrapper<OnlCgreportParam> queryWrapper = QueryGenerator.initQueryWrapper(onlCgreportParam, httpServletRequest.getParameterMap());
        Page<OnlCgreportParam> page = new Page(n2, n3);
        IPage<OnlCgreportParam> iPage = this.onlCgreportParamService.page( page, queryWrapper);
        result.setSuccess(true);
        result.setResult(iPage);
        return result;
    }

    @PostMapping(value = {"/add"})
    public Result<?> a(@RequestBody OnlCgreportParam onlCgreportParam) {
        this.onlCgreportParamService.save(onlCgreportParam);
        return Result.ok("添加成功!");
    }

    @PutMapping(value = {"/edit"})
    public Result<?> b(@RequestBody OnlCgreportParam onlCgreportParam) {
        this.onlCgreportParamService.updateById(onlCgreportParam);
        return Result.ok("编辑成功!");
    }

    @DeleteMapping(value = {"/delete"})
    public Result<?> b(@RequestParam(name = "id", required = true) String string) {
        this.onlCgreportParamService.removeById(string);
        return Result.ok("删除成功!");
    }

    @DeleteMapping(value = {"/deleteBatch"})
    public Result<?> c(@RequestParam(name = "ids", required = true) String string) {
        this.onlCgreportParamService.removeByIds(Arrays.asList(string.split(",")));
        return Result.ok("批量删除成功!");
    }

    @GetMapping(value = {"/queryById"})
    public Result<OnlCgreportParam> d(@RequestParam(name = "id", required = true) String string) {
        Result result = new Result();
        OnlCgreportParam onlCgreportParam = this.onlCgreportParamService.getById(string);
        result.setResult(onlCgreportParam);
        return result;
    }
}
