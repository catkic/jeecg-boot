/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONObject
 */
package org.jeecg.modules.online.cgform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.enums.CgformValidPatternEnum;

public class FieldValidateUtils {
    private Map<String, OnlCgformField> d;
    private static final String e = ",";
    private static final String f = "第%s行校验信息:";
    private static final String g = "总上传行数:%s,已导入行数:%s,错误行数:%s";
    public static final String a = "error";
    public static final String b = "tip";
    public static final String c = "filePath";

    public FieldValidateUtils() {
    }

    public FieldValidateUtils(List<OnlCgformField> list) {
        this.d = new HashMap<String, OnlCgformField>();
        for (OnlCgformField onlCgformField : list) {
            String string = onlCgformField.getFieldValidType();
            if (string != null && !"".equals(string) && !CgformValidPatternEnum.ONLY.getType().equals(string)) {
                this.d.put(onlCgformField.getDbFieldName(), onlCgformField);
            }
            if (onlCgformField.getDbIsNull() != 0 && !"1".equals(onlCgformField.getFieldMustInput())) continue;
            this.d.put(onlCgformField.getDbFieldName(), onlCgformField);
        }
    }

    public String a(String string, int n2) {
        StringBuffer stringBuffer = new StringBuffer();
        JSONObject jSONObject = JSON.parseObject(string);
        for (String string2 : this.d.keySet()) {
            String string3 = jSONObject.getString(string2);
            OnlCgformField onlCgformField = this.d.get(string2);
            String string4 = onlCgformField.getFieldValidType();
            if (CgformValidPatternEnum.NOTNULL.getType().equals(string4) || onlCgformField.getDbIsNull() == 0 || "1".equals(onlCgformField.getFieldMustInput())) {
                if (string3 != null && !"".equals(string3)) continue;
                stringBuffer.append(onlCgformField.getDbFieldTxt() + CgformValidPatternEnum.NOTNULL.getMsg() + e);
                continue;
            }
            if (string3 == null || "".equals(string3)) continue;
            String string5 = null;
            String string6 = null;
            if (CgformValidPatternEnum.INTEGER.getType().equals(string4)) {
                string5 = "^-?[1-9]\\d*$";
                string6 = "请输入整数";
            } else {
                CgformValidPatternEnum patternInfoByType = CgformValidPatternEnum.getPatternInfoByType(string4);
                if (patternInfoByType == null) {
                    string5 = string4;
                    string6 = "校验【" + string5 + "】未通过";
                } else {
                    string5 = patternInfoByType.getPattern();
                    string6 = patternInfoByType.getMsg();
                }
            }
            Pattern reg = Pattern.compile(string5);
            Matcher matcher = reg.matcher(string3);
            if (matcher.find()) continue;
            stringBuffer.append(onlCgformField.getDbFieldTxt() + string6 + e);
        }
        if (stringBuffer.length() > 0) {
            return FieldValidateUtils.b(stringBuffer.toString(), n2);
        }
        return null;
    }

    public static String b(String string, int n2) {
        return String.format(f, n2) + string + "\r\n";
    }

    public static String a(int n2, int n3) {
        int n4 = n2 - n3;
        return String.format(g, n2, n4, n3);
    }
}

