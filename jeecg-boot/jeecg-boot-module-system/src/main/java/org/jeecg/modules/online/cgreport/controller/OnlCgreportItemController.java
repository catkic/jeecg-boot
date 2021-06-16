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
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
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
@RequestMapping(value={"/online/cgreport/item"})
@Slf4j
public class OnlCgreportItemController {
    @Autowired
    private IOnlCgreportItemService onlCgreportItemService;

    @GetMapping(value={"/listByHeadId"})
    public Result<?> a(@RequestParam(value="headId") String string) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("cgrhead_id", string);
        queryWrapper.orderByAsc("order_num");
        List list = this.onlCgreportItemService.list(queryWrapper);
        Result result = new Result();
        result.setSuccess(true);
        result.setResult(list);
        return result;
    }

    @GetMapping(value={"/list"})
    public Result<IPage<OnlCgreportItem>> a(OnlCgreportItem onlCgreportItem, @RequestParam(name="pageNo", defaultValue="1") Integer n2, @RequestParam(name="pageSize", defaultValue="10") Integer n3, HttpServletRequest httpServletRequest) {
        Result result = new Result();
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(onlCgreportItem, (Map)httpServletRequest.getParameterMap());
        Page page = new Page(n2.intValue(), n3.intValue());
        IPage iPage = this.onlCgreportItemService.page((IPage)page, queryWrapper);
        result.setSuccess(true);
        result.setResult(iPage);
        return result;
    }

    @PostMapping(value={"/add"})
    public Result<?> a(@RequestBody OnlCgreportItem onlCgreportItem) {
        this.onlCgreportItemService.save(onlCgreportItem);
        return Result.ok("添加成功!");
    }

    @PutMapping(value={"/edit"})
    public Result<?> b(@RequestBody OnlCgreportItem onlCgreportItem) {
        this.onlCgreportItemService.updateById(onlCgreportItem);
        return Result.ok("编辑成功!");
    }

    @DeleteMapping(value={"/delete"})
    public Result<?> b(@RequestParam(name="id", required=true) String string) {
        this.onlCgreportItemService.removeById(string);
        return Result.ok("删除成功!");
    }

    @DeleteMapping(value={"/deleteBatch"})
    public Result<?> c(@RequestParam(name="ids", required=true) String string) {
        this.onlCgreportItemService.removeByIds(Arrays.asList(string.split(",")));
        return Result.ok("批量删除成功!");
    }

    @GetMapping(value={"/queryById"})
    public Result<OnlCgreportItem> d(@RequestParam(name="id", required=true) String string) {
        Result result = new Result();
        OnlCgreportItem onlCgreportItem = this.onlCgreportItemService.getById(string);
        result.setResult(onlCgreportItem);
        result.setSuccess(true);
        return result;
    }
}
