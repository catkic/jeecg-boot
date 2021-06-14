/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Component
 */
package org.jeecg.modules.online.cgform.enhance.a;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value="cgformEnhanceBeanDemo")
public class a
implements CgformEnhanceJavaInter {
    private static final Logger a = LoggerFactory.getLogger(a.class);

    @Override
    public int execute(String tableName, JSONObject json) throws BusinessException {
        a.info("--------我是自定义java增强测试bean-----------");
        a.info("--------当前表单 tableName=> " + tableName);
        a.info("--------当前表单 JSON数据=> " + json.toJSONString());
        if (json.containsKey((Object)"phone")) {
            json.put("phone", (Object)"18611100000");
        }
        return 1;
    }

    @Override
    public int execute(String tableName, Map<String, Object> map) throws BusinessException {
        return 1;
    }
}

