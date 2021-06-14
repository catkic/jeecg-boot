/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper
 *  com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper
 *  com.baomidou.mybatisplus.core.toolkit.support.SFunction
 *  org.jeecg.common.api.vo.Result
 *  org.jeecg.common.util.oConvertUtils
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
package org.jeecg.modules.online.auth.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.auth.entity.OnlAuthData;
import org.jeecg.modules.online.auth.entity.OnlAuthPage;
import org.jeecg.modules.online.auth.entity.OnlAuthRelation;
import org.jeecg.modules.online.auth.service.IOnlAuthDataService;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.auth.service.IOnlAuthRelationService;
import org.jeecg.modules.online.auth.vo.AuthColumnVO;
import org.jeecg.modules.online.auth.vo.AuthPageVO;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.service.IOnlCgformButtonService;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
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

@RestController(value="onlCgformAuthController")
@RequestMapping(value={"/online/cgform/api"})
public class OnlCgformAuthController {
    private static final Logger a = LoggerFactory.getLogger(OnlCgformAuthController.class);
    @Autowired
    private IOnlCgformFieldService onlCgformFieldService;
    @Autowired
    private IOnlAuthDataService onlAuthDataService;
    @Autowired
    private IOnlAuthPageService onlAuthPageService;
    @Autowired
    private IOnlCgformButtonService onlCgformButtonService;
    @Autowired
    private IOnlAuthRelationService onlAuthRelationService;
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;

    @GetMapping(value={"/authData/{cgformId}"})
    public Result<List<OnlAuthData>> a(@PathVariable(value="cgformId") String string) {
        Result result = new Result();
        LambdaQueryWrapper<OnlAuthData> lambdaQueryWrapper = new LambdaQueryWrapper<OnlAuthData>();
        lambdaQueryWrapper.eq(OnlAuthData::getCgformId, (Object)string);
        List list = this.onlAuthDataService.list((Wrapper)lambdaQueryWrapper);
        result.setResult((Object)list);
        result.setSuccess(true);
        return result;
    }

    @PostMapping(value={"/authData"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<OnlAuthData> a(@RequestBody OnlAuthData onlAuthData) {
        Result result = new Result();
        try {
            this.onlAuthDataService.save(onlAuthData);
            result.success("添加成功！");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            result.error500("操作失败");
        }
        return result;
    }

    @PutMapping(value={"/authData"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<OnlAuthData> b(@RequestBody OnlAuthData onlAuthData) {
        Result result = new Result();
        this.onlAuthDataService.updateById(onlAuthData);
        return result;
    }

    @DeleteMapping(value={"/authData/{id}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> b(@PathVariable(value="id") String string) {
        this.onlAuthDataService.deleteOne(string);
        return Result.ok((String)"删除成功!");
    }

    @GetMapping(value={"/authButton/{cgformId}"})
    public Result<Map<String, Object>> c(@PathVariable(value="cgformId") String string) {
        Result result = new Result();
        LambdaQueryWrapper<OnlCgformButton> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformButton>()
                .eq(OnlCgformButton::getCgformHeadId, string)
                .eq(OnlCgformButton::getButtonStatus, 1)
                .select("buttonCode", "buttonName", "buttonStyle");
        List<OnlCgformButton> list = this.onlCgformButtonService.list(lambdaQueryWrapper);
        LambdaQueryWrapper lambdaQueryWrapper2 = (LambdaQueryWrapper)((LambdaQueryWrapper)new LambdaQueryWrapper().eq(OnlAuthPage::getCgformId, (Object)string)).eq(OnlAuthPage::getType, (Object)2);
        List list2 = this.onlAuthPageService.list((Wrapper)lambdaQueryWrapper2);
        HashMap<String, List> hashMap = new HashMap<String, List>();
        hashMap.put("buttonList", list);
        hashMap.put("authList", list2);
        result.setResult(hashMap);
        result.setSuccess(true);
        return result;
    }

    @PostMapping(value={"/authButton"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<OnlAuthPage> a(@RequestBody OnlAuthPage onlAuthPage) {
        Result result = new Result();
        try {
            OnlAuthPage onlAuthPage2;
            String string = onlAuthPage.getId();
            boolean bl = false;
            if (oConvertUtils.isNotEmpty((Object)string) && (onlAuthPage2 = (OnlAuthPage)this.onlAuthPageService.getById((Serializable)((Object)string))) != null) {
                bl = true;
                onlAuthPage2.setStatus(1);
                this.onlAuthPageService.updateById(onlAuthPage2);
            }
            if (!bl) {
                onlAuthPage.setStatus(1);
                this.onlAuthPageService.save(onlAuthPage);
            }
            result.setResult((Object)onlAuthPage);
            result.success("操作成功！");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            result.error500("操作失败");
        }
        return result;
    }

    @PutMapping(value={"/authButton/{id}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> d(@PathVariable(value="id") String string) {
        LambdaUpdateWrapper<OnlAuthPage> lambdaUpdateWrapper = (new UpdateWrapper<OnlAuthPage>().lambda()
                .eq(OnlAuthPage::getId, (Object)string))
                .set(OnlAuthPage::getStatus, (Object)0);
        this.onlAuthPageService.update((Wrapper)lambdaUpdateWrapper);
        return Result.ok((String)"操作成功");
    }

    @GetMapping(value={"/authColumn/{cgformId}"})
    public Result<List<AuthColumnVO>> e(@PathVariable(value="cgformId") String string) {
        Result result = new Result();
        LambdaQueryWrapper<OnlCgformField> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformField>();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object)string);
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        List<OnlCgformField> list = this.onlCgformFieldService.list(lambdaQueryWrapper);
        if (list == null || list.size() == 0) {
            Result.error((String)"未找到对应字段信息!");
        }
        LambdaQueryWrapper<OnlAuthPage> lambdaQueryWrapper2 = (new LambdaQueryWrapper<OnlAuthPage>()
                .eq(OnlAuthPage::getCgformId, (Object)string))
                .eq(OnlAuthPage::getType, (Object)1);
        List<OnlAuthPage> list2 = this.onlAuthPageService.list((Wrapper)lambdaQueryWrapper2);
        ArrayList<AuthColumnVO> arrayList = new ArrayList<AuthColumnVO>();
        for (OnlCgformField onlCgformField : list) {
            AuthColumnVO authColumnVO = new AuthColumnVO(onlCgformField);
            Integer n2 = 0;
            boolean bl = false;
            boolean bl2 = false;
            boolean bl3 = false;
            for (int i2 = 0; i2 < list2.size(); ++i2) {
                OnlAuthPage onlAuthPage = (OnlAuthPage)list2.get(i2);
                if (!onlCgformField.getDbFieldName().equals(onlAuthPage.getCode())) continue;
                n2 = onlAuthPage.getStatus();
                if (onlAuthPage.getPage() == 3 && onlAuthPage.getControl() == 5) {
                    bl = true;
                }
                if (onlAuthPage.getPage() != 5) continue;
                if (onlAuthPage.getControl() == 5) {
                    bl2 = true;
                    continue;
                }
                if (onlAuthPage.getControl() != 3) continue;
                bl3 = true;
            }
            authColumnVO.setStatus(n2);
            authColumnVO.setListShow(bl);
            authColumnVO.setFormShow(bl2);
            authColumnVO.setFormEditable(bl3);
            arrayList.add(authColumnVO);
        }
        result.setResult(arrayList);
        Result.ok((String)"加载字段权限数据完成");
        return result;
    }

    @PutMapping(value={"/authColumn"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> a(@RequestBody AuthColumnVO authColumnVO) {
        Result result = new Result();
        try {
            if (authColumnVO.getStatus() == 1) {
                this.onlAuthPageService.enableAuthColumn(authColumnVO);
            } else {
                this.onlAuthPageService.disableAuthColumn(authColumnVO);
            }
            result.success("操作成功！");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            result.error500("操作失败");
        }
        return result;
    }

    @PostMapping(value={"/authColumn"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> b(@RequestBody AuthColumnVO authColumnVO) {
        Result result = new Result();
        try {
            this.onlAuthPageService.switchAuthColumn(authColumnVO);
            result.success("操作成功！");
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
            result.error500("操作失败");
        }
        return result;
    }

    @GetMapping(value={"/authPage/{cgformId}/{type}"})
    public Result<List<AuthPageVO>> a(@PathVariable(value="cgformId") String string, @PathVariable(value="type") Integer n2) {
        Result result = new Result();
        List<AuthPageVO> list = this.onlAuthPageService.queryAuthByFormId(string, n2);
        result.setResult(list);
        result.setSuccess(true);
        return result;
    }

    @GetMapping(value={"/validAuthData/{cgformId}"})
    public Result<List<OnlAuthData>> f(@PathVariable(value="cgformId") String string) {
        Result result = new Result();
        LambdaQueryWrapper<OnlAuthData> lambdaQueryWrapper = ((new LambdaQueryWrapper<OnlAuthData>()
                .eq(OnlAuthData::getCgformId, (Object)string))
                .eq(OnlAuthData::getStatus, (Object)1))
                .select(new SFunction<OnlAuthData, String>[]{OnlAuthData::getId, OnlAuthData::getRuleName});
        List list = this.onlAuthDataService.list((Wrapper)lambdaQueryWrapper);
        result.setResult((Object)list);
        result.setSuccess(true);
        return result;
    }

    @GetMapping(value={"/roleAuth"})
    public Result<List<OnlAuthRelation>> a(@RequestParam(value="roleId") String string, @RequestParam(value="cgformId") String string2, @RequestParam(value="type") Integer n2, @RequestParam(value="authMode") String string3) {
        Result result = new Result();
        LambdaQueryWrapper lambdaQueryWrapper = ((LambdaQueryWrapper)((LambdaQueryWrapper)((LambdaQueryWrapper)((LambdaQueryWrapper)new LambdaQueryWrapper().eq(OnlAuthRelation::getRoleId, (Object)string)).eq(OnlAuthRelation::getCgformId, (Object)string2)).eq(OnlAuthRelation::getType, (Object)n2)).eq(OnlAuthRelation::getAuthMode, (Object)string3)).select(new SFunction[]{OnlAuthRelation::getAuthId});
        List list = this.onlAuthRelationService.list((Wrapper)lambdaQueryWrapper);
        result.setResult((Object)list);
        result.setSuccess(true);
        return result;
    }

    @PostMapping(value={"/roleColumnAuth/{roleId}/{cgformId}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> a(@PathVariable(value="roleId") String string, @PathVariable(value="cgformId") String string2, @RequestBody JSONObject jSONObject) {
        Result result = new Result();
        JSONArray jSONArray = jSONObject.getJSONArray("authId");
        String string3 = jSONObject.getString("authMode");
        List list = jSONArray.toJavaList(String.class);
        this.onlAuthRelationService.saveRoleAuth(string, string2, 1, string3, list);
        result.setSuccess(true);
        return result;
    }

    @PostMapping(value={"/roleButtonAuth/{roleId}/{cgformId}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> b(@PathVariable(value="roleId") String string, @PathVariable(value="cgformId") String string2, @RequestBody JSONObject jSONObject) {
        Result result = new Result();
        JSONArray jSONArray = jSONObject.getJSONArray("authId");
        String string3 = jSONObject.getString("authMode");
        List list = jSONArray.toJavaList(String.class);
        this.onlAuthRelationService.saveRoleAuth(string, string2, 2, string3, list);
        result.setSuccess(true);
        return result;
    }

    @PostMapping(value={"/roleDataAuth/{roleId}/{cgformId}"})
    @CacheEvict(value={"sys:cache:online:list", "sys:cache:online:form"}, allEntries=true, beforeInvocation=true)
    public Result<?> c(@PathVariable(value="roleId") String string, @PathVariable(value="cgformId") String string2, @RequestBody JSONObject jSONObject) {
        Result result = new Result();
        JSONArray jSONArray = jSONObject.getJSONArray("authId");
        String string3 = jSONObject.getString("authMode");
        List list = jSONArray.toJavaList(String.class);
        this.onlAuthRelationService.saveRoleAuth(string, string2, 3, string3, list);
        result.setSuccess(true);
        return result;
    }

    @GetMapping(value={"/getAuthColumn/{desformCode}"})
    public Result<List<AuthColumnVO>> g(@PathVariable(value="desformCode") String string) {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string));
        if (onlCgformHead == null) {
            Result.error((String)"未找到对应字段信息!");
        }
        Result result = new Result();
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object)onlCgformHead.getId());
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        List list = this.onlCgformFieldService.list((Wrapper)lambdaQueryWrapper);
        if (list == null || list.size() == 0) {
            Result.error((String)"未找到对应字段信息!");
        }
        ArrayList<AuthColumnVO> arrayList = new ArrayList<AuthColumnVO>();
        for (OnlCgformField onlCgformField : list) {
            AuthColumnVO authColumnVO = new AuthColumnVO(onlCgformField);
            authColumnVO.setTableName(onlCgformHead.getTableName());
            authColumnVO.setTableNameTxt(onlCgformHead.getTableTxt());
            arrayList.add(authColumnVO);
        }
        if (oConvertUtils.isNotEmpty((Object)onlCgformHead.getSubTableStr())) {
            for (String string2 : onlCgformHead.getSubTableStr().split(",")) {
                LambdaQueryWrapper lambdaQueryWrapper2;
                List list2;
                OnlCgformHead onlCgformHead2 = (OnlCgformHead)this.onlCgformHeadService.getOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string2));
                if (onlCgformHead2 == null || (list2 = this.onlCgformFieldService.list((Wrapper)(lambdaQueryWrapper2 = (LambdaQueryWrapper)new LambdaQueryWrapper().eq(OnlCgformField::getCgformHeadId, (Object)onlCgformHead2.getId())))) == null) continue;
                for (OnlCgformField onlCgformField : list2) {
                    AuthColumnVO authColumnVO = new AuthColumnVO(onlCgformField);
                    authColumnVO.setTableName(onlCgformHead2.getTableName());
                    authColumnVO.setTableNameTxt(onlCgformHead2.getTableTxt());
                    arrayList.add(authColumnVO);
                }
            }
        }
        result.setResult(arrayList);
        Result.ok((String)"加载字段权限数据完成");
        return result;
    }
}

