/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  org.apache.commons.lang.StringUtils
 *  org.jeecg.common.exception.JeecgBootException
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package org.jeecg.modules.online.cgreport.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportAPIService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OnlCgreportAPIServiceImpl implements IOnlCgreportAPIService {
    @Autowired
    private OnlCgreportHeadServiceImpl onlCgreportHeadService;
    @Autowired
    private IOnlCgreportItemService onlCgreportItemService;
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IOnlCgreportParamService onlCgreportParamService;

    @Override
    public Map<String, Object> getDataById(String id, Map<String, Object> params) {
        return this.getData(id, null, params);
    }

    @Override
    public Map<String, Object> getDataByCode(String code, Map<String, Object> params) {
        return this.getData(null, code, params);
    }

    @Override
    public Map<String, Object> getData(String id, String code, Map<String, Object> params) {
        OnlCgreportHead onlCgreportHead = null;
        if (oConvertUtils.isNotEmpty(id)) {
            onlCgreportHead = this.onlCgreportHeadService.getById(id);
        } else if (oConvertUtils.isNotEmpty(code)) {
            LambdaQueryWrapper<OnlCgreportHead> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgreportHead>().eq(OnlCgreportHead::getCode, code);

            onlCgreportHead = this.onlCgreportHeadService.getOne(lambdaQueryWrapper);
        }
        if (onlCgreportHead == null) {
            throw new JeecgBootException("实体不存在");
        }
        try {
            String trim = onlCgreportHead.getCgrSql().trim();
            String string = onlCgreportHead.getDbSource();
            return this.executeSelectSqlRoute(string, trim, params, onlCgreportHead.getId());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new JeecgBootException("SQL执行失败：" + exception.getMessage());
        }
    }

    @Override
    public Map<String, Object> executeSelectSqlRoute(String dbKey, String sql, Map<String, Object> params, String headId) throws Exception {
        if (StringUtils.isNotBlank(dbKey)) {
            log.debug("Online报表: 走了多数据源逻辑");
            return this.onlCgreportHeadService.executeSelectSqlDynamic(dbKey, sql, params, headId);
        }
        log.debug("Online报表: 走了稳定逻辑");
        return this.onlCgreportHeadService.executeSelectSql(sql, headId, params);
    }

}
