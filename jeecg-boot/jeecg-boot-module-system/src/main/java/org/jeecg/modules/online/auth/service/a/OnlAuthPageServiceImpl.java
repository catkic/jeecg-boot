/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper
 *  com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  org.apache.shiro.SecurityUtils
 *  org.jeecg.common.system.vo.LoginUser
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package org.jeecg.modules.online.auth.service.a;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.online.auth.entity.OnlAuthPage;
import org.jeecg.modules.online.auth.entity.OnlAuthRelation;
import org.jeecg.modules.online.auth.mapper.OnlAuthPageMapper;
import org.jeecg.modules.online.auth.mapper.OnlAuthRelationMapper;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.auth.vo.AuthColumnVO;
import org.jeecg.modules.online.auth.vo.AuthPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="onlAuthPageServiceImpl")
public class OnlAuthPageServiceImpl
extends ServiceImpl<OnlAuthPageMapper, OnlAuthPage> implements IOnlAuthPageService {
    @Autowired
    private OnlAuthRelationMapper onlAuthRelationMapper;

    @Override
    public void disableAuthColumn(AuthColumnVO authColumnVO) {
        LambdaUpdateWrapper<OnlAuthPage> lambdaUpdateWrapper = new UpdateWrapper<OnlAuthPage>()
                .lambda()
                .eq(OnlAuthPage::getCgformId, authColumnVO.getCgformId())
                .eq(OnlAuthPage::getCode, authColumnVO.getCode())
                .eq(OnlAuthPage::getType, 1)
                .set(OnlAuthPage::getStatus, 0);
        this.update(lambdaUpdateWrapper);
    }

    @Override
    @Transactional
    public void enableAuthColumn(AuthColumnVO authColumnVO) {
        String string = authColumnVO.getCgformId();
        String string2 = authColumnVO.getCode();
        LambdaQueryWrapper<OnlAuthPage> lambdaQueryWrapper = new LambdaQueryWrapper<OnlAuthPage>()
                .eq(OnlAuthPage::getCgformId, (Object)string)
                .eq(OnlAuthPage::getCode, (Object)string2)
                .eq(OnlAuthPage::getType, (Object)1);
        List<OnlAuthPage> arrayList = this.list(lambdaQueryWrapper);
        if (arrayList != null && arrayList.size() > 0) {
            LambdaUpdateWrapper<OnlAuthPage> lambdaUpdateWrapper = new UpdateWrapper<OnlAuthPage>().lambda()
                    .eq(OnlAuthPage::getCgformId, (Object)string)
                    .eq(OnlAuthPage::getCode, (Object)string2)
                    .eq(OnlAuthPage::getType, (Object)1)
                    .set(OnlAuthPage::getStatus, (Object)1);
            this.update(lambdaUpdateWrapper);
        } else {
            arrayList = new ArrayList<OnlAuthPage>();
            arrayList.add(new OnlAuthPage(string, string2, 3, 5));
            arrayList.add(new OnlAuthPage(string, string2, 5, 5));
            arrayList.add(new OnlAuthPage(string, string2, 5, 3));
            this.saveBatch(arrayList);
        }
    }

    @Override
    public void switchAuthColumn(AuthColumnVO authColumnVO) {
        String string = authColumnVO.getCgformId();
        String string2 = authColumnVO.getCode();
        int n2 = authColumnVO.getSwitchFlag();
        if (n2 == 1) {
            this.switchListShow(string, string2, authColumnVO.isListShow());
        } else if (n2 == 2) {
            this.switchFormShow(string, string2, authColumnVO.isFormShow());
        } else if (n2 == 3) {
            this.switchFormEditable(string, string2, authColumnVO.isFormEditable());
        }
    }

    @Override
    @Transactional
    public void switchFormShow(String cgformId, String code, boolean flag) {
        this.a(cgformId, code, 5, 5, flag);
    }

    @Override
    @Transactional
    public void switchFormEditable(String cgformId, String code, boolean flag) {
        this.a(cgformId, code, 3, 5, flag);
    }

    @Override
    @Transactional
    public void switchListShow(String cgformId, String code, boolean flag) {
        this.a(cgformId, code, 5, 3, flag);
    }

    @Override
    public List<AuthPageVO> queryRoleAuthByFormId(String roleId, String cgformId, int type) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleAuthByFormId(roleId, cgformId, type);
    }

    @Override
    public List<AuthPageVO> queryRoleDataAuth(String roleId, String cgformId) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleDataAuth(roleId, cgformId);
    }

    @Override
    public List<AuthPageVO> queryAuthByFormId(String cgformId, int type) {
        if (type == 1) {
            return ((OnlAuthPageMapper)this.baseMapper).queryAuthColumnByFormId(cgformId);
        }
        return ((OnlAuthPageMapper)this.baseMapper).queryAuthButtonByFormId(cgformId);
    }

    @Override
    public List<String> queryRoleNoAuthCode(String cgformId, Integer control, Integer page) {
        LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String string = loginUser.getId();
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(string, cgformId, control, page, null);
    }

    @Override
    public List<String> queryFormDisabledCode(String cgformId) {
        return this.queryRoleNoAuthCode(cgformId, 3, 5);
    }

    @Override
    public List<String> queryHideCode(String userId, String cgformId, boolean isList) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, isList ? 3 : 5, null);
    }

    @Override
    public List<String> queryListHideColumn(String userId, String cgformId) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, 3, 1);
    }

    @Override
    public List<String> queryFormHideColumn(String userId, String cgformId) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, 5, 1);
    }

    @Override
    public List<String> queryFormHideButton(String userId, String cgformId) {
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, 5, 2);
    }

    @Override
    public List<String> queryHideCode(String cgformId, boolean isList) {
        LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
        String string = loginUser.getId();
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(string, cgformId, 5, isList ? 3 : 5, null);
    }

    @Override
    public List<String> queryListHideButton(String userId, String cgformId) {
        if (userId == null) {
            LoginUser loginUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
            userId = loginUser.getId();
        }
        return ((OnlAuthPageMapper)this.baseMapper).queryRoleNoAuthCode(userId, cgformId, 5, 3, 2);
    }

    private void a(String string, String string2, int n2, int n3, boolean bl) {
        LambdaQueryWrapper<OnlAuthPage> lambdaQueryWrapper = new LambdaQueryWrapper<OnlAuthPage>()
                .eq(OnlAuthPage::getCgformId, (Object)string)
                .eq(OnlAuthPage::getCode, (Object)string2)
                .eq(OnlAuthPage::getControl, (Object)n2)
                .eq(OnlAuthPage::getPage, (Object)n3)
                .eq(OnlAuthPage::getType, (Object)1);
        OnlAuthPage onlAuthPage = this.baseMapper.selectOne(lambdaQueryWrapper);
        if (bl) {
            if (onlAuthPage == null) {
                OnlAuthPage onlAuthPage2 = new OnlAuthPage();
                onlAuthPage2.setCgformId(string);
                onlAuthPage2.setCode(string2);
                onlAuthPage2.setControl(n2);
                onlAuthPage2.setPage(n3);
                onlAuthPage2.setType(1);
                onlAuthPage2.setStatus(1);
                ((OnlAuthPageMapper)this.baseMapper).insert(onlAuthPage2);
            } else if (onlAuthPage.getStatus() == 0) {
                onlAuthPage.setStatus(1);
                ((OnlAuthPageMapper)this.baseMapper).updateById(onlAuthPage);
            }
        } else if (!bl && onlAuthPage != null) {
            String string3 = onlAuthPage.getId();
            this.baseMapper.deleteById(onlAuthPage.getId());
            this.onlAuthRelationMapper.delete(new LambdaQueryWrapper<OnlAuthRelation>().eq(OnlAuthRelation::getAuthId, string3));
        }
    }
}

