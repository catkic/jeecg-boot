/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  org.springframework.stereotype.Service
 */
package org.jeecg.modules.online.cgform.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.lang.invoke.SerializedLambda;

import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.mapper.OnlCgformButtonMapper;
import org.jeecg.modules.online.cgform.service.IOnlCgformButtonService;
import org.springframework.stereotype.Service;

@Service
public class OnlCgformButtonServiceImpl extends ServiceImpl<OnlCgformButtonMapper, OnlCgformButton> implements IOnlCgformButtonService {
    @Override
    public void saveButton(OnlCgformButton onlCgformButton) {
        LambdaQueryWrapper<OnlCgformButton> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformButton>()
                .eq(OnlCgformButton::getButtonCode, (Object) onlCgformButton.getButtonCode())
                .eq(OnlCgformButton::getCgformHeadId, (Object) onlCgformButton.getCgformHeadId());
        Integer n2 = this.baseMapper.selectCount(lambdaQueryWrapper);
        if (n2 == null || n2 == 0) {
            this.save(onlCgformButton);
        }
    }
}

