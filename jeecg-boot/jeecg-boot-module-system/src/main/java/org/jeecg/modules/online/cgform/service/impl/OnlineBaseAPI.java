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
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.mapper.OnlCgformFieldMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.service.IOnlineBaseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineBaseAPI implements IOnlineBaseAPI {
    @Autowired
    private OnlCgformHeadMapper onlCgformHeadMapper;
    @Autowired
    private OnlCgformFieldMapper onlCgformFieldMapper;

    @Override
    public String getOnlineErpCode(String code, String tableType) {
        List<OnlCgformField> list;
        String string;
        OnlCgformHead onlCgformHead;
        if ("3".equals(tableType) && (onlCgformHead = this.onlCgformHeadMapper.selectById((Serializable) ((Object) (string = code.substring(1))))) != null && onlCgformHead.getTableType() == 3 && (list = this.onlCgformFieldMapper.selectList(new LambdaQueryWrapper<OnlCgformField>().eq(OnlCgformField::getCgformHeadId, (Object) string))) != null && list.size() > 0) {
            String string2 = null;
            for (OnlCgformField onlCgformField : list) {
                if (!oConvertUtils.isNotEmpty(onlCgformField.getMainTable())) continue;
                string2 = onlCgformField.getMainTable();
                break;
            }
            LambdaQueryWrapper lambdaQueryWrapper2 = new LambdaQueryWrapper<OnlCgformHead>().eq(OnlCgformHead::getTableName, string2);
            onlCgformHead = this.onlCgformHeadMapper.selectOne(lambdaQueryWrapper2);
            String themeTemplate = onlCgformHead.getThemeTemplate();
            if ("innerTable".equals(themeTemplate) || "erp".equals(themeTemplate)) {
                code = "/" + onlCgformHead.getId();
            }
        }
        return code;
    }

}

