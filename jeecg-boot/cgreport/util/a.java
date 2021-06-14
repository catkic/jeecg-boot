/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONObject
 *  javax.servlet.http.HttpServletRequest
 *  org.jeecg.common.system.query.QueryGenerator
 *  org.jeecg.common.util.DateUtils
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package org.jeecg.modules.online.cgreport.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.util.b;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.config.b.d;
import org.jeecg.modules.online.config.exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class a {

    public static void a(HttpServletRequest httpServletRequest, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        String string = (String)map.get("field_name");
        String string2 = (String)map.get("search_mode");
        String string3 = (String)map.get("field_type");
        if ("single".equals(string2)) {
            String string4 = httpServletRequest.getParameter(string.toLowerCase());
            try {
                if (oConvertUtils.isEmpty((Object)string4)) {
                    return;
                }
                String string5 = httpServletRequest.getQueryString();
                if (string5.contains(string + "=")) {
                    String string6;
                    string4 = string6 = new String(string4.getBytes("ISO-8859-1"), "UTF-8");
                }
            }
            catch (UnsupportedEncodingException unsupportedEncodingException) {
                log.error(unsupportedEncodingException.getMessage(), (Throwable)unsupportedEncodingException);
                return;
            }
            if (oConvertUtils.isNotEmpty((Object)string4)) {
                if (string4.contains("*")) {
                    string4 = string4.replaceAll("\\*", "%");
                    map2.put(string, " LIKE :" + string);
                } else {
                    map2.put(string, " = :" + string);
                }
            }
            map3.put(string, org.jeecg.modules.online.cgreport.util.a.a(string3, string4, true));
        } else if ("group".equals(string2)) {
            String string7;
            String string8 = httpServletRequest.getParameter(string.toLowerCase() + "_begin");
            String string9 = httpServletRequest.getParameter(string.toLowerCase() + "_end");
            if (oConvertUtils.isNotEmpty((Object)string8)) {
                string7 = " >= :" + string + "_begin";
                map2.put(string, string7);
                map3.put(string + "_begin", org.jeecg.modules.online.cgreport.util.a.a(string3, string8, true));
            }
            if (oConvertUtils.isNotEmpty((Object)string9)) {
                string7 = " <= :" + string + "_end";
                map2.put(new String(string), string7);
                map3.put(string + "_end", org.jeecg.modules.online.cgreport.util.a.a(string3, string9, false));
            }
        }
    }

    private static Object a(String string, String string2, boolean bl) {
        Object object = null;
        if (oConvertUtils.isNotEmpty((Object)string2)) {
            if ("String".equalsIgnoreCase(string)) {
                object = string2;
            } else if ("Date".equalsIgnoreCase(string)) {
                if (string2.length() != 19 && string2.length() == 10) {
                    string2 = bl ? string2 + " 00:00:00" : string2 + " 23:59:59";
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                object = DateUtils.str2Date((String)string2, (SimpleDateFormat)simpleDateFormat);
            } else {
                object = "Double".equalsIgnoreCase(string) ? string2 : ("Integer".equalsIgnoreCase(string) ? string2 : string2);
            }
        }
        return object;
    }

    public static String a(List<Map<String, Object>> list, Long l2) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("total", (Object)l2);
        if (list != null) {
            for (Map<String, Object> map : list) {
                JSONObject jSONObject2 = new JSONObject();
                for (String string : map.keySet()) {
                    String string2 = String.valueOf(map.get(string));
                    if ((string = string.toLowerCase()).contains("time") || string.contains("date")) {
                        string2 = org.jeecg.modules.online.cgreport.util.a.a(string2);
                    }
                    jSONObject2.put(string, (Object)string2);
                }
                jSONArray.add((Object)jSONObject2);
            }
        }
        jSONObject.put("rows", (Object)jSONArray);
        return jSONObject.toString();
    }

    public static String a(List<Map<String, Object>> list) {
        JSONArray jSONArray = new JSONArray();
        for (Map<String, Object> map : list) {
            JSONObject jSONObject = new JSONObject();
            for (String string : map.keySet()) {
                String string2 = String.valueOf(map.get(string));
                if ((string = string.toLowerCase()).contains("time") || string.contains("date")) {
                    string2 = org.jeecg.modules.online.cgreport.util.a.a(string2);
                }
                jSONObject.put(string, (Object)string2);
            }
            jSONArray.add((Object)jSONObject);
        }
        return jSONArray.toString();
    }

    public static String a(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(string);
            return simpleDateFormat2.format(date);
        }
        catch (Exception exception) {
            return string;
        }
    }

    public static String a(List<OnlCgreportItem> list, Map<String, Object> map, String string, String string2) {
        Object object;
        String string3;
        String string4;
        StringBuffer stringBuffer = new StringBuffer();
        String string5 = string2;
        if (string5 == null) {
            try {
                string5 = d.getDatabaseType();
            }
            catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
            catch (DBException dBException) {
                dBException.printStackTrace();
            }
        }
        HashSet<String> hashSet = new HashSet<String>();
        for (OnlCgreportItem object2 : list) {
            boolean bl;
            string4 = object2.getFieldName();
            string3 = object2.getFieldType();
            if (1 != object2.getIsSearch()) continue;
            if ("group".equals(object2.getSearchMode())) {
                Object object3;
                object = map.get(string4 + "_begin");
                if (object != null) {
                    stringBuffer.append(" and " + string + string4 + " >= ");
                    if ("Long".equals(string3) || "Integer".equals(string3)) {
                        stringBuffer.append(object.toString());
                    } else if ("ORACLE".equals(string5)) {
                        if (string3.toLowerCase().equals("datetime")) {
                            stringBuffer.append(b.a(object.toString()));
                        } else if (string3.toLowerCase().equals("date")) {
                            stringBuffer.append(b.b(object.toString()));
                        }
                    } else {
                        stringBuffer.append("'" + object.toString() + "'");
                    }
                }
                if ((object3 = map.get(string4 + "_end")) == null) continue;
                stringBuffer.append(" and " + string + string4 + " <= ");
                if ("Long".equals(string3) || "Integer".equals(string3)) {
                    stringBuffer.append(object3.toString());
                    continue;
                }
                if ("ORACLE".equals(string5)) {
                    if (string3.toLowerCase().equals("datetime")) {
                        stringBuffer.append(b.a(object3.toString()));
                        continue;
                    }
                    if (!string3.toLowerCase().equals("date")) continue;
                    stringBuffer.append(b.b(object3.toString()));
                    continue;
                }
                stringBuffer.append("'" + object3.toString() + "'");
                continue;
            }
            object = map.get(string4);
            if (object == null) continue;
            boolean bl2 = bl = !"Long".equals(string3) && !"Integer".equals(string3);
            if ("ORACLE".equals(string5)) {
                if (string3.toLowerCase().equals("datetime")) {
                    object = b.a(object.toString());
                    bl = false;
                } else if (string3.toLowerCase().equals("date")) {
                    object = b.b(object.toString());
                    bl = false;
                }
            }
            String string6 = QueryGenerator.getSingleQueryConditionSql((String)string4, (String)string, (Object)object, (boolean)bl, (String)string5);
            stringBuffer.append(" and " + string6);
            hashSet.add(string4);
        }
        for (String string7 : map.keySet()) {
            if (!string7.startsWith("popup_param_pre__")) continue;
            string4 = map.get(string7).toString();
            string3 = string7.substring("popup_param_pre__".length());
            if (hashSet.contains(string3)) continue;
            object = QueryGenerator.getSingleQueryConditionSql((String)string3, (String)string, (Object)string4, (boolean)false);
            stringBuffer.append(" and " + (String)object);
        }
        return stringBuffer.toString();
    }
}

