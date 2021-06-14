/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  org.jeecg.common.util.oConvertUtils
 *  org.springframework.cache.annotation.Cacheable
 *  org.springframework.stereotype.Service
 */
package org.jeecg.modules.online.cgreport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportItemMapper;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.util.SqlUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service(value="onlCgreportItemServiceImpl")
public class OnlCgreportItemServiceImpl
extends ServiceImpl<OnlCgreportItemMapper, OnlCgreportItem>
implements IOnlCgreportItemService {
    @Override
    @Cacheable(value={"sys:cache:online:rp"}, key="'search-'+#cgrheadId")
    public List<Map<String, String>> getAutoListQueryInfo(String cgrheadId) {
        LambdaQueryWrapper<OnlCgreportItem> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgreportItem>()
                .eq(OnlCgreportItem::getCgrheadId, cgrheadId)
                .eq(OnlCgreportItem::getIsSearch, 1);
        List<OnlCgreportItem> list = this.list(lambdaQueryWrapper);
        List<Map<String, String>> arrayList = new ArrayList<>();
        int n2 = 0;
        for (OnlCgreportItem onlCgreportItem : list) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("label", onlCgreportItem.getFieldTxt());
            String string = onlCgreportItem.getDictCode();
            if (oConvertUtils.isNotEmpty((Object)string)) {
                if (SqlUtil.c(string)) {
                    hashMap.put("view", "search");
                    hashMap.put("sql", string);
                } else {
                    hashMap.put("view", "list");
                }
            } else {
                hashMap.put("view", onlCgreportItem.getFieldType().toLowerCase());
            }
            hashMap.put("mode", oConvertUtils.isEmpty((Object)onlCgreportItem.getSearchMode()) ? "single" : onlCgreportItem.getSearchMode());
            hashMap.put("field", onlCgreportItem.getFieldName());
            if (++n2 > 2) {
                hashMap.put("hidden", "1");
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }
}

