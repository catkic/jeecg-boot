/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 *  org.apache.commons.lang.StringUtils
 *  org.apache.commons.lang.math.RandomUtils
 *  org.jeecg.common.handler.IFillRuleHandler
 */
package org.jeecg.modules.online.cgform.c;

import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.jeecg.common.handler.IFillRuleHandler;

public class a implements IFillRuleHandler {
    public Object execute(JSONObject params, JSONObject formData) {
        Object object;
        String string = "CN";
        if (params != null && (object = params.get("prefix")) != null) {
            string = object.toString();
        }
        object = new SimpleDateFormat("yyyyMMddHHmmss");
        int n2 = RandomUtils.nextInt((int) 90) + 10;
        String string2 = string + ((DateFormat) object).format(new Date()) + n2;
        String string3 = formData.getString("name");
        if (!StringUtils.isEmpty((String) string3)) {
            string2 = string2 + string3;
        }
        return string2;
    }
}

