/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Component
 */
package org.jeecg.modules.online.cgform.enhance.export;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value="cgformEnhanceImportDemo")
public class c
implements CgformEnhanceJavaInter {
    private static final Logger a = LoggerFactory.getLogger(c.class);

    @Override
    public int execute(String tableName, JSONObject json) throws BusinessException {
        a.info("--------当前tableName=>" + tableName);
        a.info("===============================================================================================================");
        a.info("--------导入JSON数据=>" + json.toJSONString());
        if (oConvertUtils.isEmpty((Object)json.get((Object)"name"))) {
            a.info("-----变更导入数据，直接返回1----");
            json.put("name", (Object)"默认值");
            return 1;
        }
        if (json.getString("name").equals("error")) {
            json.put("name", (Object)"默认值");
            throw new BusinessException("测试抛出异常error");
        }
        if (json.getString("name").equals("hello")) {
            a.info("-----导入数据变更新，直接返回2----");
            json.put("id", (Object)"testid123");
            json.put("name", (Object)"JAVA导入增强 测试修改");
            return 2;
        }
        if (json.getString("name").equals("ok")) {
            a.info("-----丢弃导入数据，直接返回0----");
            return 0;
        }
        return 1;
    }

    @Override
    public int execute(String tableName, Map<String, Object> map) throws BusinessException {
        return 1;
    }
}

