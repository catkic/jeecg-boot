/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  org.jeecg.common.system.vo.SysPermissionDataRuleModel
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package org.jeecg.modules.online.auth.service.a;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.jeecg.common.system.vo.SysPermissionDataRuleModel;
import org.jeecg.modules.online.auth.entity.OnlAuthData;
import org.jeecg.modules.online.auth.entity.OnlAuthRelation;
import org.jeecg.modules.online.auth.mapper.OnlAuthDataMapper;
import org.jeecg.modules.online.auth.mapper.OnlAuthRelationMapper;
import org.jeecg.modules.online.auth.service.IOnlAuthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "onlAuthDataServiceImpl")
public class OnlAuthDataServiceImpl extends ServiceImpl<OnlAuthDataMapper, OnlAuthData> implements IOnlAuthDataService {
    @Autowired
    private OnlAuthRelationMapper onlAuthRelationMapper;

    @Override
    public void deleteOne(String id) {
        this.removeById((Serializable) ((Object) id));
        LambdaQueryWrapper<OnlAuthRelation> lambdaQueryWrapper = new LambdaQueryWrapper<OnlAuthRelation>();
        this.onlAuthRelationMapper.delete(lambdaQueryWrapper.eq(OnlAuthRelation::getAuthId, (Object) id));
    }

    @Override
    public List<SysPermissionDataRuleModel> queryUserOnlineAuthData(String userId, String cgformId) {
        String string;
        List<SysPermissionDataRuleModel> list = ((OnlAuthDataMapper) this.baseMapper).queryRoleAuthData(userId, cgformId);
        List<SysPermissionDataRuleModel> list2 = ((OnlAuthDataMapper) this.baseMapper).queryDepartAuthData(userId, cgformId);
        HashMap<String, SysPermissionDataRuleModel> hashMap = new HashMap<String, SysPermissionDataRuleModel>();
        for (SysPermissionDataRuleModel sysPermissionDataRuleModel : list) {
            string = sysPermissionDataRuleModel.getId();
            if (hashMap.get(string) != null) continue;
            hashMap.put(string, sysPermissionDataRuleModel);
        }
        for (SysPermissionDataRuleModel sysPermissionDataRuleModel : list2) {
            string = sysPermissionDataRuleModel.getId();
            if (hashMap.get(string) != null) continue;
            hashMap.put(string, sysPermissionDataRuleModel);
        }
        Collection collection = hashMap.values();
        if (collection == null || collection.size() == 0) {
            return null;
        }
        return new ArrayList<SysPermissionDataRuleModel>(collection);
    }

}

