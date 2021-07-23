/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 */
package org.jeecg.modules.online.cgform.util;

import com.alibaba.fastjson.JSONObject;
import java.math.BigDecimal;
import java.util.Map;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class TypeUtils {
    public static final String a = "int";
    public static final String b = "Integer";
    public static final String c = "double";
    public static final String d = "BigDecimal";
    public static final String e = "Blob";
    public static final String f = "Date";
    public static final String g = "datetime";
    public static final String h = "Timestamp";

    public static boolean isDigit(String string) {
        return a.equals(string) || c.equals(string) || d.equals(string) || b.equals(string);
    }

    public static boolean b(String string) {
        return f.equalsIgnoreCase(string) || g.equalsIgnoreCase(string) || h.equalsIgnoreCase(string);
    }

    public static String a(String string, OnlCgformField onlCgformField, JSONObject jSONObject, Map<String, Object> map) {
        String string2 = onlCgformField.getDbType();
        String string3 = onlCgformField.getDbFieldName();
        String string4 = onlCgformField.getFieldShowType();
        if (jSONObject.get((Object)string3) == null) {
            return "null";
        }
        if (a.equals(string2)) {
            map.put(string3, jSONObject.getIntValue(string3));
            return "#{" + string3 + ",jdbcType=INTEGER}";
        }
        if (c.equals(string2)) {
            map.put(string3, jSONObject.getDoubleValue(string3));
            return "#{" + string3 + ",jdbcType=DOUBLE}";
        }
        if (d.equals(string2)) {
            map.put(string3, new BigDecimal(jSONObject.getString(string3)));
            return "#{" + string3 + ",jdbcType=DECIMAL}";
        }
        if (e.equals(string2)) {
            map.put(string3, jSONObject.getString(string3) != null ? jSONObject.getString(string3).getBytes() : null);
            return "#{" + string3 + ",jdbcType=BLOB}";
        }
        if (f.equals(string2)) {
            String string5 = jSONObject.getString(string3);
            if ("ORACLE".equals(string)) {
                if ("date".equals(string4)) {
                    map.put(string3, string5.length() > 10 ? string5.substring(0, 10) : string5);
                    return "to_date(#{" + string3 + "},'yyyy-MM-dd')";
                }
                map.put(string3, string5.length() == 10 ? jSONObject.getString(string3) + " 00:00:00" : string5);
                return "to_date(#{" + string3 + "},'yyyy-MM-dd HH24:mi:ss')";
            }
            if ("POSTGRESQL".equals(string)) {
                if ("date".equals(string4)) {
                    map.put(string3, string5.length() > 10 ? string5.substring(0, 10) : string5);
                    return "CAST(#{" + string3 + "} as TIMESTAMP)";
                }
                map.put(string3, string5.length() == 10 ? jSONObject.getString(string3) + " 00:00:00" : string5);
                return "CAST(#{" + string3 + "} as TIMESTAMP)";
            }
            map.put(string3, jSONObject.getString(string3));
            return "#{" + string3 + "}";
        }
        map.put(string3, jSONObject.getString(string3));
        return "#{" + string3 + ",jdbcType=VARCHAR}";
    }
}

