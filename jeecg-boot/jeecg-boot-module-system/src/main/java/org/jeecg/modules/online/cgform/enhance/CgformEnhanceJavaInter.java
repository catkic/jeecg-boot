/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 */
package org.jeecg.modules.online.cgform.enhance;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;
import org.jeecg.modules.online.config.exception.BusinessException;

public interface CgformEnhanceJavaInter {
    @Deprecated
    public int execute(String var1, Map<String, Object> var2) throws BusinessException;

    public int execute(String var1, JSONObject var2) throws BusinessException;
}

