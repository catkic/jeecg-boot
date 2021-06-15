/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  org.springframework.stereotype.Service
 */
package org.jeecg.modules.online.auth.service.a;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;

import org.jeecg.modules.online.auth.entity.OnlAuthRelation;
import org.jeecg.modules.online.auth.mapper.OnlAuthRelationMapper;
import org.jeecg.modules.online.auth.service.IOnlAuthRelationService;
import org.springframework.stereotype.Service;

@Service(value = "onlAuthRelationServiceImpl")
public class OnlAuthRelationServiceImpl extends ServiceImpl<OnlAuthRelationMapper, OnlAuthRelation> implements IOnlAuthRelationService {
    @Override
    public void saveRoleAuth(String roleId, String cgformId, int type, String authMode, List<String> authIds) {
        LambdaQueryWrapper<OnlAuthRelation> lambdaQueryWrapper = new LambdaQueryWrapper<OnlAuthRelation>()
                .eq(OnlAuthRelation::getCgformId, (Object) cgformId)
                .eq(OnlAuthRelation::getType, (Object) type)
                .eq(OnlAuthRelation::getAuthMode, (Object) authMode)
                .eq(OnlAuthRelation::getRoleId, (Object) roleId);
        ((OnlAuthRelationMapper) this.baseMapper).delete((Wrapper) lambdaQueryWrapper);
        ArrayList<OnlAuthRelation> arrayList = new ArrayList<OnlAuthRelation>();
        for (String string : authIds) {
            OnlAuthRelation onlAuthRelation = new OnlAuthRelation();
            onlAuthRelation.setAuthId(string);
            onlAuthRelation.setCgformId(cgformId);
            onlAuthRelation.setRoleId(roleId);
            onlAuthRelation.setType(type);
            onlAuthRelation.setAuthMode(authMode);
            arrayList.add(onlAuthRelation);
        }
        this.saveBatch(arrayList);
    }
}

