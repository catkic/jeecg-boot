/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  org.jeecg.common.util.oConvertUtils
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package org.jeecg.modules.online.cgform.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceSql;
import org.jeecg.modules.online.cgform.mapper.OnlCgformEnhanceJavaMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformEnhanceSqlMapper;
import org.jeecg.modules.online.cgform.service.IOnlCgformEnhanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="onlCgformEnhanceService")
public class OnlCgformEnhanceServiceImpl implements IOnlCgformEnhanceService {
    @Autowired
    private OnlCgformEnhanceJavaMapper onlCgformEnhanceJavaMapper;
    @Autowired
    private OnlCgformEnhanceSqlMapper onlCgformEnhanceSqlMapper;

    @Override
    public List<OnlCgformEnhanceJava> queryEnhanceJavaList(String cgformId) {
        LambdaQueryWrapper<OnlCgformEnhanceJava> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformEnhanceJava>()
                .eq(OnlCgformEnhanceJava::getCgformHeadId, cgformId);
        return this.onlCgformEnhanceJavaMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void saveEnhanceJava(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        this.onlCgformEnhanceJavaMapper.insert(onlCgformEnhanceJava);
    }

    @Override
    public void updateEnhanceJava(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        this.onlCgformEnhanceJavaMapper.updateById(onlCgformEnhanceJava);
    }

    @Override
    public void deleteEnhanceJava(String id) {
        this.onlCgformEnhanceJavaMapper.deleteById((Serializable)((Object)id));
    }

    @Override
    public void deleteBatchEnhanceJava(List<String> idList) {
        this.onlCgformEnhanceJavaMapper.deleteBatchIds(idList);
    }

    @Override
    public boolean checkOnlyEnhance(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        LambdaQueryWrapper<OnlCgformEnhanceJava> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformEnhanceJava>();
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getButtonCode, (Object)onlCgformEnhanceJava.getButtonCode());
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getCgformHeadId, (Object)onlCgformEnhanceJava.getCgformHeadId());
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getEvent, (Object)onlCgformEnhanceJava.getEvent());
        Integer n2 = this.onlCgformEnhanceJavaMapper.selectCount(lambdaQueryWrapper);
        if (n2 != null) {
            if (n2 == 1 && oConvertUtils.isEmpty((Object)onlCgformEnhanceJava.getId())) {
                return false;
            }
            if (n2 == 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkOnlyEnhance(OnlCgformEnhanceSql onlCgformEnhanceSql) {
        LambdaQueryWrapper<OnlCgformEnhanceSql> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformEnhanceSql>();
        lambdaQueryWrapper.eq(OnlCgformEnhanceSql::getButtonCode, (Object)onlCgformEnhanceSql.getButtonCode());
        lambdaQueryWrapper.eq(OnlCgformEnhanceSql::getCgformHeadId, (Object)onlCgformEnhanceSql.getCgformHeadId());
        Integer n2 = this.onlCgformEnhanceSqlMapper.selectCount((Wrapper)lambdaQueryWrapper);
        if (n2 != null) {
            if (n2 == 1 && oConvertUtils.isEmpty((Object)onlCgformEnhanceSql.getId())) {
                return false;
            }
            if (n2 > 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<OnlCgformEnhanceSql> queryEnhanceSqlList(String cgformId) {
        LambdaQueryWrapper<OnlCgformEnhanceSql> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformEnhanceSql>().eq(OnlCgformEnhanceSql::getCgformHeadId, cgformId);
        return this.onlCgformEnhanceSqlMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void saveEnhanceSql(OnlCgformEnhanceSql onlCgformEnhanceSql) {
        this.onlCgformEnhanceSqlMapper.insert(onlCgformEnhanceSql);
    }

    @Override
    public void updateEnhanceSql(OnlCgformEnhanceSql onlCgformEnhanceSql) {
        this.onlCgformEnhanceSqlMapper.updateById(onlCgformEnhanceSql);
    }

    @Override
    public void deleteEnhanceSql(String id) {
        this.onlCgformEnhanceSqlMapper.deleteById((Serializable)((Object)id));
    }

    @Override
    public void deleteBatchEnhanceSql(List<String> idList) {
        this.onlCgformEnhanceSqlMapper.deleteBatchIds(idList);
    }
}

