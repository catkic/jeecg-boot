/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.core.toolkit.IdWorker
 *  javax.servlet.http.HttpServletRequest
 *  org.apache.commons.lang.StringUtils
 *  org.apache.shiro.SecurityUtils
 *  org.jeecg.common.exception.JeecgBootException
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.system.query.MatchTypeEnum
 *  org.jeecg.common.system.query.QueryGenerator
 *  org.jeecg.common.system.query.QueryRuleEnum
 *  org.jeecg.common.system.vo.DictModel
 *  org.jeecg.common.system.vo.LoginUser
 *  org.jeecg.common.system.vo.SysPermissionDataRuleModel
 *  org.jeecg.common.util.CommonUtils
 *  org.jeecg.common.util.DateUtils
 *  org.jeecg.common.util.SpringContextUtils
 *  org.jeecg.common.util.UUIDGenerator
 *  org.jeecg.common.util.oConvertUtils
 *  org.jeecgframework.poi.excel.entity.params.ExcelExportEntity
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package org.jeecg.modules.online.cgform.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.MatchTypeEnum;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.system.vo.SysPermissionDataRuleModel;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.common.util.jsonschema.CommonProperty;
import org.jeecg.common.util.jsonschema.JsonSchemaDescrip;
import org.jeecg.common.util.jsonschema.a;
import org.jeecg.common.util.jsonschema.validate.DictProperty;
import org.jeecg.common.util.jsonschema.validate.HiddenProperty;
import org.jeecg.common.util.jsonschema.validate.LinkDownProperty;
import org.jeecg.common.util.jsonschema.validate.NumberProperty;
import org.jeecg.common.util.jsonschema.validate.PopupProperty;
import org.jeecg.common.util.jsonschema.validate.StringProperty;
import org.jeecg.common.util.jsonschema.validate.SwitchProperty;
import org.jeecg.common.util.jsonschema.validate.TreeSelectProperty;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.entity.OnlCgformIndex;
import org.jeecg.modules.online.cgform.enums.CgformValidPatternEnum;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.model.d;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.config.exception.DBException;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;

@Slf4j
public class b {
    public static final String SELECT = "SELECT ";
    public static final String FROM = " FROM ";
    public static final String AND = " AND ";
    public static final String LIKE = " like ";
    public static final String COUNT = " COUNT(*) ";
    public static final String WHERE_1 = " where 1=1  ";
    public static final String WHERE = " where  ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String ASC = "asc";
    public static final String DESC = "desc";
    public static final String EQUAL = "=";
    public static final String l = "!=";
    public static final String m = ">=";
    public static final String n = ">";
    public static final String o = "<=";
    public static final String p = "<";
    public static final String q = " or ";
    public static final String r = "Y";
    public static final String s = "$";
    public static final String t = "CREATE_TIME";
    public static final String u = "CREATE_BY";
    public static final String v = "UPDATE_TIME";
    public static final String w = "UPDATE_BY";
    public static final String x = "SYS_ORG_CODE";
    public static final int y = 2;
    public static final String z = "'";
    public static final String A = "N";
    public static final String COMMA = ",";
    public static final String C = "single";
    public static final String D = "id";
    public static final String E = "bpm_status";
    public static final String F = "1";
    public static final String G = "force";
    public static final String H = "normal";
    public static final String SWITCH = "switch";
    public static final String POPUP = "popup";
    public static final String SEL_SEARCH = "sel_search";
    public static final String IMAGE = "image";
    public static final String FILE = "file";
    public static final String SEL_TREE = "sel_tree";
    public static final String CAT_TREE = "cat_tree";
    public static final String LINK_DOWN = "link_down";
    public static final String SYS_USER = "SYS_USER";
    public static final String REALNAME = "REALNAME";
    public static final String USERNAME = "USERNAME";
    public static final String SYS_DEPART = "SYS_DEPART";
    public static final String DEPART_NAME = "DEPART_NAME";
    public static final String ID = "ID";
    public static final String SYS_CATEGORY = "SYS_CATEGORY";
    public static final String NAME = "NAME";
    public static final String CODE = "CODE";
    public static final String ID1 = "ID";
    public static final String PID = "PID";
    public static final String HAS_CHILD = "HAS_CHILD";
    public static final String POPUP_MULTI = "popupMulti";
    public static final String SEL_SEARCH1 = "sel_search";
    public static final String ae = "sub-table-design_";
    public static final String af = "import";
    public static final String ag = "export";
    public static final String ah = "query";
    public static final String FORM = "form";
    public static final String aj = "list";
    public static final String ak = "1";
    public static final String al = "start";
    public static final String am = "erp";
    public static final String an = "exportSingleOnly";
    public static final String ao = "isSingleTableImport";
    public static final String ap = "validateStatus";
    public static final String aq = "1";
    public static final String ar = "foreignKeys";
    public static final int as = 1;
    public static final int at = 2;
    public static final int au = 0;
    public static final int av = 1;
    public static final String aw = "1";
    public static final String ax = "id";
    public static final String ay = "center";
    public static final String az = "showLength";
    private static final String aB = "beforeAdd,beforeEdit,afterAdd,afterEdit,beforeDelete,afterDelete,mounted,created";
    private static String aC;

    public static void a(String string, List<OnlCgformField> list, StringBuffer stringBuffer) {
        if (list == null || list.size() == 0) {
            stringBuffer.append("SELECT id");
        } else {
            stringBuffer.append(SELECT);
            int n2 = list.size();
            boolean bl = false;
            for (int i2 = 0; i2 < n2; ++i2) {
                OnlCgformField onlCgformField = list.get(i2);
                if ("id".equals(onlCgformField.getDbFieldName())) {
                    bl = true;
                }
                if (CAT_TREE.equals(onlCgformField.getFieldShowType()) && oConvertUtils.isNotEmpty((Object) onlCgformField.getDictText())) {
                    stringBuffer.append(onlCgformField.getDictText() + COMMA);
                }
                if (i2 == n2 - 1) {
                    stringBuffer.append(onlCgformField.getDbFieldName() + " ");
                    continue;
                }
                stringBuffer.append(onlCgformField.getDbFieldName() + COMMA);
            }
            if (!bl) {
                stringBuffer.append(",id");
            }
        }
        stringBuffer.append(FROM + org.jeecg.modules.online.cgform.util.b.f(string));
    }

    public static String a(String string) {
        return " to_date('" + string + "','yyyy-MM-dd HH24:mi:ss')";
    }

    public static String b(String string) {
        return " to_date('" + string + "','yyyy-MM-dd')";
    }

    public static boolean c(String string) {
        if (aj.equals(string)) {
            return true;
        }
        if ("radio".equals(string)) {
            return true;
        }
        if ("checkbox".equals(string)) {
            return true;
        }
        return "list_multi".equals(string);
    }

    public static String a(List<OnlCgformField> list, Map<String, Object> map, List<String> list2) {
        return org.jeecg.modules.online.cgform.util.b.a(list, map, list2, null);
    }

    public static String a(List<OnlCgformField> list, Map<String, Object> map, List<String> list2, List<SysPermissionDataRuleModel> list3) {
        StringBuffer stringBuffer = new StringBuffer();
        String string = "";
        try {
            string = org.jeecg.modules.online.config.b.d.getDatabaseType();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } catch (DBException dBException) {
            dBException.printStackTrace();
        }
        Map<String, SysPermissionDataRuleModel> ruleMap = QueryGenerator.getRuleMap(list3);
        for (String object : ruleMap.keySet()) {
            if (!oConvertUtils.isNotEmpty(object) || !object.startsWith("SQL_RULES_COLUMN")) continue;
            stringBuffer.append(" AND (" + QueryGenerator.getSqlRuleValue(ruleMap.get(object).getRuleValue()) + ")");
        }
        for (OnlCgformField onlCgformField : list) {
            Object object2;
            String string2 = onlCgformField.getDbFieldName();
            String string3 = onlCgformField.getDbType();
            if (ruleMap.containsKey(string2)) {
                org.jeecg.modules.online.cgform.util.b.a(string, ruleMap.get(string2), string2, string3, stringBuffer);
            } else if (ruleMap.containsKey(oConvertUtils.camelNames(string2))) {
                org.jeecg.modules.online.cgform.util.b.a(string, ruleMap.get(string2), string2, string3, stringBuffer);
            }
            if (list2 != null && list2.contains(string2)) {
                onlCgformField.setIsQuery(1);
                onlCgformField.setQueryMode(C);
            }
            if (oConvertUtils.isNotEmpty((Object) onlCgformField.getMainField()) && oConvertUtils.isNotEmpty((Object) onlCgformField.getMainTable())) {
                onlCgformField.setIsQuery(1);
                onlCgformField.setQueryMode(C);
            }
            if (1 != onlCgformField.getIsQuery()) continue;
            if (C.equals(onlCgformField.getQueryMode())) {
                String string4;
                object2 = map.get(string2);
                if (object2 == null) continue;
                if ("list_multi".equals(onlCgformField.getFieldShowType())) {
                    String[] object = object2.toString().split(COMMA);
                    string4 = "";
                    for (int i2 = 0; i2 < ((String[]) object).length; ++i2) {
                        string4 = oConvertUtils.isNotEmpty(string4) ? string4 + q + string2 + LIKE + "'%" + object[i2] + ",%'" + q + string2 + LIKE + "'%," + (String) object[i2] + "%'" : string2 + LIKE + "'%" + object[i2] + ",%'" + q + string2 + LIKE + "'%," + (String) object[i2] + "%'";
                    }
                    stringBuffer.append(" AND (" + string4 + ")");
                }
                if (POPUP.equals(onlCgformField.getFieldShowType())) {
                    stringBuffer.append(" AND (" + org.jeecg.modules.online.cgform.util.b.b(string2, object2.toString()) + ")");
                    continue;
                }
                if ("ORACLE".equals(string) && string3.toLowerCase().indexOf("date") >= 0) {
                    stringBuffer.append(AND + string2 + EQUAL + org.jeecg.modules.online.cgform.util.b.a(object2.toString()));
                    continue;
                }
                boolean bl = !org.jeecg.modules.online.cgform.util.i.a(string3);
                string4 = QueryGenerator.getSingleQueryConditionSql((String) string2, (String) "", (Object) object2, (boolean) bl);
                stringBuffer.append(AND + string4);
                continue;
            }
            object2 = map.get(string2 + "_begin");
            if (object2 != null) {
                stringBuffer.append(AND + string2 + m);
                if (org.jeecg.modules.online.cgform.util.i.a(string3)) {
                    stringBuffer.append(object2.toString());
                } else if ("ORACLE".equals(string) && string3.toLowerCase().indexOf("date") >= 0) {
                    stringBuffer.append(org.jeecg.modules.online.cgform.util.b.a(object2.toString()));
                } else {
                    stringBuffer.append(z + object2.toString() + z);
                }
            }
            Object object = map.get(string2 + "_end");
            if (object == null) continue;
            stringBuffer.append(AND + string2 + o);
            if (org.jeecg.modules.online.cgform.util.i.a(string3)) {
                stringBuffer.append(object.toString());
                continue;
            }
            if ("ORACLE".equals(string) && string3.toLowerCase().indexOf("date") >= 0) {
                stringBuffer.append(org.jeecg.modules.online.cgform.util.b.a(object.toString()));
                continue;
            }
            stringBuffer.append(z + object.toString() + z);
        }
        return stringBuffer.toString();
    }

    public static String a(Map<String, Object> map) {
        Object object = map.get("superQueryParams");
        if (object == null || StringUtils.isBlank((String) object.toString())) {
            return "";
        }
        IOnlCgformFieldService iOnlCgformFieldService = (IOnlCgformFieldService) SpringContextUtils.getBean(IOnlCgformFieldService.class);
        String string = null;
        try {
            string = URLDecoder.decode(object.toString(), "UTF-8");
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
            return "";
        }
        JSONArray jSONArray = JSONArray.parseArray((String) string);
        Object object2 = map.get("superQueryMatchType");
        MatchTypeEnum matchTypeEnum = MatchTypeEnum.getByValue((Object) object2);
        if (matchTypeEnum == null) {
            matchTypeEnum = MatchTypeEnum.AND;
        }
        HashMap<String, JSONObject> hashMap = new HashMap<String, JSONObject>();
        StringBuilder stringBuilder = new StringBuilder(AND).append("(");
        for (int i2 = 0; i2 < jSONArray.size(); ++i2) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            String string2 = jSONObject.getString("field");
            String[] arrstring = string2.split(COMMA);
            if (arrstring.length == 1) {
                org.jeecg.modules.online.cgform.util.b.a(stringBuilder, string2, jSONObject, matchTypeEnum, null, i2 == 0);
                continue;
            }
            if (arrstring.length != 2) continue;
            String string3 = arrstring[0];
            String string4 = arrstring[1];
            JSONObject jSONObject2 = (JSONObject) hashMap.get(string3);
            if (jSONObject2 == null) {
                List<OnlCgformField> list = iOnlCgformFieldService.queryFormFieldsByTableName(string3);
                jSONObject2 = new JSONObject(3);
                for (OnlCgformField onlCgformField : list) {
                    if (!StringUtils.isNotBlank((String) onlCgformField.getMainTable())) continue;
                    jSONObject2.put("subTableName", (Object) string3);
                    jSONObject2.put("subField", (Object) onlCgformField.getDbFieldName());
                    jSONObject2.put("mainTable", (Object) onlCgformField.getMainTable());
                    jSONObject2.put("mainField", (Object) onlCgformField.getMainField());
                }
                hashMap.put(string3, jSONObject2);
            }
            org.jeecg.modules.online.cgform.util.b.a(stringBuilder, string4, jSONObject, matchTypeEnum, jSONObject2, i2 == 0);
        }
        return stringBuilder.append(")").toString();
    }

    private static void a(StringBuilder stringBuilder, String string, JSONObject jSONObject, MatchTypeEnum matchTypeEnum, JSONObject jSONObject2, boolean bl) {
        if (!bl) {
            stringBuilder.append(" ").append(matchTypeEnum.getValue()).append(" ");
        }
        String string2 = jSONObject.getString("type");
        String string3 = jSONObject.getString("val");
        String string4 = org.jeecg.modules.online.cgform.util.b.a(string2, string3);
        QueryRuleEnum queryRuleEnum = QueryRuleEnum.getByValue((String) jSONObject.getString("rule"));
        if (queryRuleEnum == null) {
            queryRuleEnum = QueryRuleEnum.EQ;
        }
        if (jSONObject2 != null) {
            String string5 = jSONObject2.getString("subTableName");
            String string6 = jSONObject2.getString("subField");
            String string7 = jSONObject2.getString("mainTable");
            String string8 = jSONObject2.getString("mainField");
            stringBuilder.append("(").append(string8).append(" IN (SELECT ").append(string6).append(FROM).append(string5).append(" WHERE ");
            if (POPUP.equals(string2)) {
                stringBuilder.append(org.jeecg.modules.online.cgform.util.b.b(string, string3));
            } else {
                stringBuilder.append(string);
                org.jeecg.modules.online.cgform.util.b.a(stringBuilder, queryRuleEnum, string3, string4, string2);
            }
            stringBuilder.append("))");
        } else if (POPUP.equals(string2)) {
            stringBuilder.append(org.jeecg.modules.online.cgform.util.b.b(string, string3));
        } else {
            stringBuilder.append(string);
            org.jeecg.modules.online.cgform.util.b.a(stringBuilder, queryRuleEnum, string3, string4, string2);
        }
    }

    private static void a(StringBuilder stringBuilder, QueryRuleEnum queryRuleEnum, String string, String string2, String string3) {
        if ("date".equals(string3) && "ORACLE".equalsIgnoreCase(org.jeecg.modules.online.cgform.util.b.getDatabseType())) {
            string2 = (string2 = string2.replace(z, "")).length() == 10 ? org.jeecg.modules.online.cgform.util.b.b(string2) : org.jeecg.modules.online.cgform.util.b.a(string2);
        }
        switch (queryRuleEnum) {
            case GT: {
                stringBuilder.append(n).append(string2);
                break;
            }
            case GE: {
                stringBuilder.append(m).append(string2);
                break;
            }
            case LT: {
                stringBuilder.append(p).append(string2);
                break;
            }
            case LE: {
                stringBuilder.append(o).append(string2);
                break;
            }
            case NE: {
                stringBuilder.append(l).append(string2);
                break;
            }
            case IN: {
                stringBuilder.append(" IN (");
                String[] arrstring = string.split(COMMA);
                for (int i2 = 0; i2 < arrstring.length; ++i2) {
                    String string4 = arrstring[i2];
                    if (!StringUtils.isNotBlank((String) string4)) continue;
                    String string5 = org.jeecg.modules.online.cgform.util.b.a(string3, string4);
                    stringBuilder.append(string5);
                    if (i2 >= arrstring.length - 1) continue;
                    stringBuilder.append(COMMA);
                }
                stringBuilder.append(")");
                break;
            }
            case LIKE: {
                stringBuilder.append(LIKE).append(A).append(z).append("%").append(string).append("%").append(z);
                break;
            }
            case LEFT_LIKE: {
                stringBuilder.append(LIKE).append(A).append(z).append("%").append(string).append(z);
                break;
            }
            case RIGHT_LIKE: {
                stringBuilder.append(LIKE).append(A).append(z).append(string).append("%").append(z);
                break;
            }
            default: {
                stringBuilder.append(EQUAL).append(string2);
            }
        }
    }

    private static String a(String string, String string2) {
        if ("int".equals(string) || "number".equals(string)) {
            return string2;
        }
        if ("date".equals(string)) {
            return z + string2 + z;
        }
        if ("SQLSERVER".equals(org.jeecg.modules.online.cgform.util.b.getDatabseType())) {
            return "N'" + string2 + z;
        }
        return z + string2 + z;
    }

    public static Map<String, Object> a(HttpServletRequest httpServletRequest) {
        Map<String, String[]> map = httpServletRequest.getParameterMap();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        Iterator<Map.Entry<String, String[]>> iterator = map.entrySet().iterator();
        String string = "";
        String string2 = "";
        Object object = null;
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            string = (String) entry.getKey();
            object = entry.getValue();
            if ("_t".equals(string) || null == object) {
                string2 = "";
            } else if (object instanceof String[]) {
                String[] arrstring = (String[]) object;
                for (int i2 = 0; i2 < arrstring.length; ++i2) {
                    string2 = arrstring[i2] + COMMA;
                }
                string2 = string2.substring(0, string2.length() - 1);
            } else {
                string2 = object.toString();
            }
            hashMap.put(string, string2);
        }
        return hashMap;
    }

    public static boolean a(String string, List<OnlCgformField> list) {
        for (OnlCgformField onlCgformField : list) {
            if (!string.equals(onlCgformField.getDbFieldName())) continue;
            return true;
        }
        return false;
    }

    public static JSONObject a(List<OnlCgformField> list, List<String> list2, d d2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<CommonProperty> arrayList2 = new ArrayList<CommonProperty>();
        ISysBaseAPI iSysBaseAPI = (ISysBaseAPI) SpringContextUtils.getBean(ISysBaseAPI.class);
        OnlCgformHeadMapper onlCgformHeadMapper = (OnlCgformHeadMapper) SpringContextUtils.getBean(OnlCgformHeadMapper.class);
        ArrayList<String> arrayList3 = new ArrayList<String>();

        for (OnlCgformField onlCgformField : list) {
            String dbFieldName = onlCgformField.getDbFieldName();
            String dictText = onlCgformField.getDictText();
            String fieldShowType = onlCgformField.getFieldShowType();
            String dbFieldTxt = onlCgformField.getDbFieldTxt();
            String dictField = onlCgformField.getDictField();
            CommonProperty property;


            if ("id".equals(dbFieldName) || arrayList3.contains(dbFieldName)) continue;
            if ("1".equals(onlCgformField.getFieldMustInput())) {
                arrayList.add(dbFieldName);
            }
            if (SWITCH.equals(fieldShowType)) {
                property = new SwitchProperty(dbFieldName, dbFieldTxt, onlCgformField.getFieldExtendJson());
            } else if (org.jeecg.modules.online.cgform.util.b.c(fieldShowType)) {
                property = new DictProperty(dbFieldName, fieldShowType, dbFieldTxt, onlCgformField.getDictTable(), onlCgformField.getDictField(), onlCgformField.getDictText());
                if (org.jeecg.modules.online.cgform.util.i.a(onlCgformField.getDbType())) {
                    property.setType("number");
                }
            } else if ("sel_search".equals(fieldShowType)) {
                property = new DictProperty(dbFieldName, dbFieldTxt, onlCgformField.getDictTable(), onlCgformField.getDictField(), onlCgformField.getDictText());
            } else if (org.jeecg.modules.online.cgform.util.i.a(onlCgformField.getDbType())) {
                NumberProperty numberProperty = new NumberProperty(dbFieldName, dbFieldTxt, "number");
                if (CgformValidPatternEnum.INTEGER.getType().equals(onlCgformField.getFieldValidType())) {
                    numberProperty.setPattern(CgformValidPatternEnum.INTEGER.getPattern());
                }
                property = numberProperty;
            } else if (POPUP.equals(fieldShowType)) {
                PopupProperty popupProperty = new PopupProperty(dbFieldName, dbFieldTxt, onlCgformField.getDictTable(), dictText, onlCgformField.getDictField());
                if (dictText != null && !dictText.equals("")) {
                    String[] object2 = dictText.split(COMMA);
                    int n2 = object2.length;
                    for (int i2 = 0; i2 < n2; ++i2) {
                        String text = object2[i2];
                        if (org.jeecg.modules.online.cgform.util.b.a(text, list)) continue;
                        HiddenProperty hiddenProperty = new HiddenProperty(text, text);
                        hiddenProperty.setOrder(onlCgformField.getOrderNum());
                        arrayList2.add(hiddenProperty);
                    }
                }
                String fieldExtendJson = onlCgformField.getFieldExtendJson();
                if (StringUtils.isNotEmpty(fieldExtendJson)) {
                    JSONObject jsonObject = JSONObject.parseObject(fieldExtendJson);
                    if (jsonObject.containsKey(POPUP_MULTI)) {
                        popupProperty.setPopupMulti(jsonObject.getBoolean(POPUP_MULTI));
                    }
                }
                property = popupProperty;
            } else if (LINK_DOWN.equals(fieldShowType)) {
                LinkDownProperty object5 = new LinkDownProperty(dbFieldName, dbFieldTxt, onlCgformField.getDictTable());
                org.jeecg.modules.online.cgform.util.b.a(object5, list, arrayList3);
                property = object5;
            } else if (SEL_TREE.equals(fieldShowType)) {
                String[] dictTexts = dictText.split(COMMA);
                String dict = onlCgformField.getDictTable() + COMMA + dictTexts[2] + COMMA + dictTexts[0];
                TreeSelectProperty treeSelectProperty = new TreeSelectProperty(dbFieldName, dbFieldTxt, dict, dictTexts[1], onlCgformField.getDictField());
                if (dictTexts.length > 3) {
                    treeSelectProperty.setHasChildField(dictTexts[3]);
                }
                property = treeSelectProperty;
            } else if (CAT_TREE.equals(fieldShowType)) {
                String category = "0";
                if (oConvertUtils.isNotEmpty(dictField) && !"0".equals(dictField)) {
                    category = onlCgformHeadMapper.queryCategoryIdByCode(dictField);
                }
                if (oConvertUtils.isEmpty(dictText)) {
                    property = new TreeSelectProperty(dbFieldName, dbFieldTxt, category);
                } else {
                    property = new TreeSelectProperty(dbFieldName, dbFieldTxt, category, dictText);
                    HiddenProperty hiddenProperty = new HiddenProperty(dictText, dictText);
                    arrayList2.add(hiddenProperty);
                }
            } else if (d2 != null && dbFieldName.equals(d2.getFieldName())) {
                String object5 = d2.getTableName() + COMMA + d2.getTextField() + COMMA + d2.getCodeField();
                TreeSelectProperty treeSelectProperty = new TreeSelectProperty(dbFieldName, dbFieldTxt, object5, d2.getPidField(), d2.getPidValue());
                treeSelectProperty.setHasChildField(d2.getHsaChildField());
                treeSelectProperty.setPidComponent(1);
                property = treeSelectProperty;
            } else {
                StringProperty stringProperty = new StringProperty(dbFieldName, dbFieldTxt, fieldShowType, onlCgformField.getDbLength());
                if (oConvertUtils.isNotEmpty((Object) onlCgformField.getFieldValidType())) {
                    CgformValidPatternEnum patternInfoByType = CgformValidPatternEnum.getPatternInfoByType(onlCgformField.getFieldValidType());
                    if (patternInfoByType != null) {
                        if (CgformValidPatternEnum.NOTNULL == patternInfoByType) {
                            arrayList.add(dbFieldName);
                        } else {
                            stringProperty.setPattern(patternInfoByType.getPattern());
                            stringProperty.setErrorInfo(patternInfoByType.getMsg());
                        }
                    } else {
                        stringProperty.setPattern(onlCgformField.getFieldValidType());
                        stringProperty.setErrorInfo("输入的值不合法");
                    }
                }
                property = stringProperty;
            }
            if (onlCgformField.getIsReadOnly() == 1 || list2.contains(dbFieldName)) {
                property.setDisabled(true);
            }
            property.setOrder(onlCgformField.getOrderNum());
            property.setDefVal(onlCgformField.getFieldDefaultValue());
            property.setFieldExtendJson(onlCgformField.getFieldExtendJson());
            property.setDbPointLength(onlCgformField.getDbPointLength());
            arrayList2.add(property);
        }
        JSONObject jsonObject;
        JsonSchemaDescrip jsonSchemaDescrip;
        if (arrayList.size() > 0) {
            jsonSchemaDescrip = new JsonSchemaDescrip(arrayList);
        } else {
            jsonSchemaDescrip = new JsonSchemaDescrip();
        }
        jsonObject = org.jeecg.common.util.jsonschema.b.a(jsonSchemaDescrip, arrayList2);
        return jsonObject;
    }

    public static JSONObject b(String string, List<OnlCgformField> list) {
        JSONObject jSONObject = new JSONObject();
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<CommonProperty> arrayList2 = new ArrayList<CommonProperty>();
        ISysBaseAPI iSysBaseAPI = (ISysBaseAPI) SpringContextUtils.getBean(ISysBaseAPI.class);
        for (OnlCgformField onlCgformField : list) {
            String string2 = onlCgformField.getDbFieldName();
            if ("id".equals(string2)) continue;
            String string3 = onlCgformField.getDbFieldTxt();
            if ("1".equals(onlCgformField.getFieldMustInput())) {
                arrayList.add(string2);
            }
            String string4 = onlCgformField.getFieldShowType();
            String string5 = onlCgformField.getDictField();
            CommonProperty commonProperty = null;
            if (org.jeecg.modules.online.cgform.util.i.a(onlCgformField.getDbType())) {
                commonProperty = new NumberProperty(string2, string3, "number");
            } else if (org.jeecg.modules.online.cgform.util.b.c(string4)) {
                List list2 = iSysBaseAPI.queryDictItemsByCode(string5);
                commonProperty = new StringProperty(string2, string3, string4, onlCgformField.getDbLength(), list2);
            } else {
                commonProperty = new StringProperty(string2, string3, string4, onlCgformField.getDbLength());
            }
            commonProperty.setOrder(onlCgformField.getOrderNum());
            arrayList2.add(commonProperty);
        }
        jSONObject = org.jeecg.common.util.jsonschema.b.a(string, arrayList, arrayList2);
        return jSONObject;
    }

    public static Set<String> a(List<OnlCgformField> list) {
        String string;
        HashSet<String> hashSet = new HashSet<String>();
        for (OnlCgformField onlCgformField : list) {
            if (POPUP.equals(onlCgformField.getFieldShowType()) && (string = onlCgformField.getDictText()) != null && !string.equals("")) {
                hashSet.addAll(Arrays.stream(string.split(COMMA)).collect(Collectors.toSet()));
            }
            if (!CAT_TREE.equals(onlCgformField.getFieldShowType()) || !oConvertUtils.isNotEmpty((Object) (string = onlCgformField.getDictText())))
                continue;
            hashSet.add(string);
        }
        for (OnlCgformField onlCgformField : list) {
            string = onlCgformField.getDbFieldName();
            if (onlCgformField.getIsShowForm() != 1 || !hashSet.contains(string)) continue;
            hashSet.remove(string);
        }
        return hashSet;
    }

    public static Map<String, Object> a(String string, List<OnlCgformField> list, JSONObject jSONObject) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        String string2 = "";
        try {
            string2 = org.jeecg.modules.online.config.b.d.getDatabaseType();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } catch (DBException dBException) {
            dBException.printStackTrace();
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        boolean bl = false;
        String string3 = null;
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (loginUser == null) {
            throw new JeecgBootException("online保存表单数据异常:系统未找到当前登陆用户信息");
        }
        Set<String> set = org.jeecg.modules.online.cgform.util.b.a(list);
        for (OnlCgformField onlCgformField : list) {
            String string4;
            String string5 = onlCgformField.getDbFieldName();
            if (null == string5) {
                log.info("--------online保存表单数据遇见空名称的字段------->>" + onlCgformField.getId());
                continue;
            }
            if ("id".equals(string5.toLowerCase())) {
                bl = true;
                string3 = jSONObject.getString(string5);
                continue;
            }
            org.jeecg.modules.online.cgform.util.b.a(onlCgformField, loginUser, jSONObject, u, t, x);
            if (E.equals(string5.toLowerCase())) {
                stringBuffer.append(COMMA + string5);
                stringBuffer2.append(",'1'");
                continue;
            }
            if (set.contains(string5)) {
                stringBuffer.append(COMMA + string5);
                string4 = org.jeecg.modules.online.cgform.util.i.a(string2, onlCgformField, jSONObject, hashMap);
                stringBuffer2.append(COMMA + string4);
                continue;
            }
            if (onlCgformField.getIsShowForm() != 1 && oConvertUtils.isEmpty((Object) onlCgformField.getMainField()) && oConvertUtils.isEmpty((Object) onlCgformField.getDbDefaultVal()))
                continue;
            if (jSONObject.get((Object) string5) == null) {
                if (oConvertUtils.isEmpty((Object) onlCgformField.getDbDefaultVal())) continue;
                jSONObject.put(string5, (Object) onlCgformField.getDbDefaultVal());
            }
            if ("".equals(jSONObject.get((Object) string5)) && (org.jeecg.modules.online.cgform.util.i.a(string4 = onlCgformField.getDbType()) || org.jeecg.modules.online.cgform.util.i.b(string4)))
                continue;
            stringBuffer.append(COMMA + string5);
            string4 = org.jeecg.modules.online.cgform.util.i.a(string2, onlCgformField, jSONObject, hashMap);
            stringBuffer2.append(COMMA + string4);
        }
        if (bl) {
            if (oConvertUtils.isEmpty(string3)) {
                string3 = org.jeecg.modules.online.cgform.util.b.a();
            }
        } else {
            string3 = org.jeecg.modules.online.cgform.util.b.a();
        }
        String string6 = "insert into " + org.jeecg.modules.online.cgform.util.b.f(string) + "(" + "id" + stringBuffer.toString() + ") values(#{id,jdbcType=VARCHAR}" + stringBuffer2.toString() + ")";
        hashMap.put("execute_sql_string", string6);
        hashMap.put("id", string3);
        log.info("--动态表单保存sql-->" + (String) string6);
        return hashMap;
    }

    public static Map<String, Object> b(String string, List<OnlCgformField> list, JSONObject jSONObject) {
        StringBuffer stringBuffer = new StringBuffer();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        String string2 = "";
        try {
            string2 = org.jeecg.modules.online.config.b.d.getDatabaseType();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } catch (DBException dBException) {
            dBException.printStackTrace();
        }
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (loginUser == null) {
            throw new JeecgBootException("online修改表单数据异常:系统未找到当前登陆用户信息");
        }
        Set<String> set = org.jeecg.modules.online.cgform.util.b.a(list);
        for (OnlCgformField object2 : list) {
            String string3;
            String string4 = object2.getDbFieldName();
            if (null == string4) {
                log.info("--------online修改表单数据遇见空名称的字段------->>" + object2.getId());
                continue;
            }
            org.jeecg.modules.online.cgform.util.b.a(object2, loginUser, jSONObject, w, v);
            if (set.contains(string4) && jSONObject.get((Object) string4) != null && !"".equals(jSONObject.getString(string4))) {
                string3 = org.jeecg.modules.online.cgform.util.i.a(string2, object2, jSONObject, hashMap);
                stringBuffer.append(string4 + EQUAL + string3 + COMMA);
                continue;
            }
            if (object2.getIsShowForm() != 1 || "id".equals(string4) || "".equals(jSONObject.get((Object) string4)) && (org.jeecg.modules.online.cgform.util.i.a(string3 = object2.getDbType()) || org.jeecg.modules.online.cgform.util.i.b(string3)) || oConvertUtils.isNotEmpty((Object) object2.getMainTable()) && oConvertUtils.isNotEmpty((Object) object2.getMainField()))
                continue;
            string3 = org.jeecg.modules.online.cgform.util.i.a(string2, object2, jSONObject, hashMap);
            stringBuffer.append(string4 + EQUAL + string3 + COMMA);
        }
        Object object3 = stringBuffer.toString();
        if (((String) object3).endsWith(COMMA)) {
            object3 = ((String) object3).substring(0, ((String) object3).length() - 1);
        }
        String string5 = "update " + org.jeecg.modules.online.cgform.util.b.f(string) + " set " + (String) object3 + WHERE + "id" + EQUAL + z + jSONObject.getString("id") + z;
        log.info("--动态表单编辑sql-->" + string5);
        hashMap.put("execute_sql_string", string5);
        return hashMap;
    }

    public static String a(String string, List<OnlCgformField> list, String string2) {
        return org.jeecg.modules.online.cgform.util.b.a(string, list, "id", string2);
    }

    public static String a(String string, List<OnlCgformField> list, String string2, String string3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(SELECT);
        int n2 = list.size();
        boolean bl = false;
        for (int i2 = 0; i2 < n2; ++i2) {
            String string4 = list.get(i2).getDbFieldName();
            if ("id".equals(string4)) {
                bl = true;
            }
            stringBuffer.append(string4);
            if (n2 <= i2 + 1) continue;
            stringBuffer.append(COMMA);
        }
        if (!bl) {
            stringBuffer.append(",id");
        }
        stringBuffer.append(FROM + org.jeecg.modules.online.cgform.util.b.f(string) + WHERE_1 + AND + string2 + EQUAL + z + string3 + z);
        return stringBuffer.toString();
    }

    public static void a(OnlCgformField onlCgformField, LoginUser loginUser, JSONObject jSONObject, String... arrstring) {
        String string = onlCgformField.getDbFieldName();
        boolean bl = false;
        for (String string2 : arrstring) {
            if (!string.toUpperCase().equals(string2)) continue;
            if (onlCgformField.getIsShowForm() == 1) {
                if (jSONObject.get((Object) string) == null) {
                    bl = true;
                }
            } else {
                onlCgformField.setIsShowForm(1);
                bl = true;
            }
            if (!bl) break;
            switch (string2) {
                case "CREATE_BY": {
                    jSONObject.put(string, (Object) loginUser.getUsername());
                    break;
                }
                case "CREATE_TIME": {
                    onlCgformField.setFieldShowType("datetime");
                    jSONObject.put(string, (Object) DateUtils.formatDateTime());
                    break;
                }
                case "UPDATE_BY": {
                    jSONObject.put(string, (Object) loginUser.getUsername());
                    break;
                }
                case "UPDATE_TIME": {
                    onlCgformField.setFieldShowType("datetime");
                    jSONObject.put(string, (Object) DateUtils.formatDateTime());
                    break;
                }
                case "SYS_ORG_CODE": {
                    jSONObject.put(string, (Object) loginUser.getOrgCode());
                    break;
                }
            }
            break;
        }
    }

    public static boolean a(Object object, Object object2) {
        if (oConvertUtils.isEmpty((Object) object) && oConvertUtils.isEmpty((Object) object2)) {
            return true;
        }
        return oConvertUtils.isNotEmpty((Object) object) && object.equals(object2);
    }

    public static boolean a(OnlCgformField onlCgformField, OnlCgformField onlCgformField2) {
        return !org.jeecg.modules.online.cgform.util.b.a((Object) onlCgformField.getDbFieldName(), (Object) onlCgformField2.getDbFieldName()) || !org.jeecg.modules.online.cgform.util.b.a((Object) onlCgformField.getDbFieldTxt(), (Object) onlCgformField2.getDbFieldTxt()) || !org.jeecg.modules.online.cgform.util.b.a(onlCgformField.getDbLength(), onlCgformField2.getDbLength()) || !org.jeecg.modules.online.cgform.util.b.a(onlCgformField.getDbPointLength(), onlCgformField2.getDbPointLength()) || !org.jeecg.modules.online.cgform.util.b.a((Object) onlCgformField.getDbType(), (Object) onlCgformField2.getDbType()) || !org.jeecg.modules.online.cgform.util.b.a(onlCgformField.getDbIsNull(), onlCgformField2.getDbIsNull()) || !org.jeecg.modules.online.cgform.util.b.a(onlCgformField.getDbIsKey(), onlCgformField2.getDbIsKey()) || !org.jeecg.modules.online.cgform.util.b.a((Object) onlCgformField.getDbDefaultVal(), (Object) onlCgformField2.getDbDefaultVal());
    }

    public static boolean a(OnlCgformIndex onlCgformIndex, OnlCgformIndex onlCgformIndex2) {
        return !org.jeecg.modules.online.cgform.util.b.a((Object) onlCgformIndex.getIndexName(), (Object) onlCgformIndex2.getIndexName()) || !org.jeecg.modules.online.cgform.util.b.a((Object) onlCgformIndex.getIndexField(), (Object) onlCgformIndex2.getIndexField()) || !org.jeecg.modules.online.cgform.util.b.a((Object) onlCgformIndex.getIndexType(), (Object) onlCgformIndex2.getIndexType());
    }

    public static boolean a(OnlCgformHead onlCgformHead, OnlCgformHead onlCgformHead2) {
        return !org.jeecg.modules.online.cgform.util.b.a((Object) onlCgformHead.getTableName(), (Object) onlCgformHead2.getTableName()) || !org.jeecg.modules.online.cgform.util.b.a((Object) onlCgformHead.getTableTxt(), (Object) onlCgformHead2.getTableTxt());
    }

    public static String a(String string, List<OnlCgformField> list, Map<String, Object> map) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        for (OnlCgformField onlCgformField : list) {
            Object object;
            boolean bl;
            String string2 = onlCgformField.getDbFieldName();
            String string3 = onlCgformField.getDbType();
            if (onlCgformField.getIsShowList() == 1) {
                stringBuffer2.append(COMMA + string2);
            }
            if (oConvertUtils.isNotEmpty((Object) onlCgformField.getMainField())) {
                bl = !org.jeecg.modules.online.cgform.util.i.a(string3);
                object = QueryGenerator.getSingleQueryConditionSql((String) string2, (String) "", (Object) map.get(string2), (boolean) bl);
                if (!"".equals(object)) {
                    stringBuffer.append(AND + (String) object);
                }
            }
            if (onlCgformField.getIsQuery() != 1) continue;
            if (C.equals(onlCgformField.getQueryMode())) {
                if (map.get(string2) == null) continue;
                bl = !org.jeecg.modules.online.cgform.util.i.a(string3);
                object = QueryGenerator.getSingleQueryConditionSql((String) string2, (String) "", (Object) map.get(string2), (boolean) bl);
                if ("".equals(object)) continue;
                stringBuffer.append(AND + (String) object);
                continue;
            }
            Object object2 = map.get(string2 + "_begin");
            if (object2 != null) {
                stringBuffer.append(AND + string2 + m);
                if (org.jeecg.modules.online.cgform.util.i.a(string3)) {
                    stringBuffer.append(object2.toString());
                } else {
                    stringBuffer.append(z + object2.toString() + z);
                }
            }
            if ((object = map.get(string2 + "_end")) == null) continue;
            stringBuffer.append(AND + string2 + o);
            if (org.jeecg.modules.online.cgform.util.i.a(string3)) {
                stringBuffer.append(object.toString());
                continue;
            }
            stringBuffer.append(z + object.toString() + z);
        }
        return "SELECT id" + stringBuffer2.toString() + FROM + org.jeecg.modules.online.cgform.util.b.f(string) + WHERE_1 + stringBuffer.toString();
    }

    public static List<ExcelExportEntity> a(List<OnlCgformField> list, String string) {
        List<ExcelExportEntity> arrayList = new ArrayList<ExcelExportEntity>();
        for (int i2 = 0; i2 < list.size(); ++i2) {
            int n2 = 0;
            if (null != string && string.equals(list.get(i2).getDbFieldName()) || list.get(i2).getIsShowList() != 1)
                continue;
            ExcelExportEntity excelExportEntity = new ExcelExportEntity(list.get(i2).getDbFieldTxt(), (Object) list.get(i2).getDbFieldName());
            int n3 = list.get(i2).getDbLength() == 0 ? 12 : (n2 = list.get(i2).getDbLength() > 30 ? 30 : list.get(i2).getDbLength());
            if (list.get(i2).getFieldShowType().equals("date")) {
                excelExportEntity.setFormat("yyyy-MM-dd");
            } else if (list.get(i2).getFieldShowType().equals("datetime")) {
                excelExportEntity.setFormat("yyyy-MM-dd HH:mm:ss");
            }
            if (n2 < 10) {
                n2 = 10;
            }
            excelExportEntity.setWidth((double) n2);
            arrayList.add(excelExportEntity);
        }
        return arrayList;
    }

    public static boolean a(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        String string = onlCgformEnhanceJava.getCgJavaType();
        String string2 = onlCgformEnhanceJava.getCgJavaValue();
        if (oConvertUtils.isNotEmpty((Object) string2)) {
            try {
                Object object;
                if ("class".equals(string) && ((object = Class.forName(string2)) == null || ((Class) object).newInstance() == null)) {
                    return false;
                }
                if ("spring".equals(string) && (object = SpringContextUtils.getBean((String) string2)) == null) {
                    return false;
                }
            } catch (Exception exception) {
                log.error(exception.getMessage(), (Throwable) exception);
                return false;
            }
        }
        return true;
    }

    public static void b(List<String> list) {
        Collections.sort(list, (string, string2) -> {
            if (string == null || string2 == null) {
                return -1;
            }
            if (string.compareTo(string2) > 0) {
                return 1;
            }
            if (string.compareTo(string2) < 0) {
                return -1;
            }
            if (string.compareTo(string2) == 0) {
                return 0;
            }
            return 0;
        });
    }

    public static void c(List<String> list) {
        Collections.sort(list, new Comparator<String>() {

            @Override
            public int compare(String string, String string2) {
                if (string == null || string2 == null) {
                    return -1;
                }
                if (string.length() > string2.length()) {
                    return 1;
                }
                if (string.length() < string2.length()) {
                    return -1;
                }
                if (string.compareTo(string2) > 0) {
                    return 1;
                }
                if (string.compareTo(string2) < 0) {
                    return -1;
                }
                if (string.compareTo(string2) == 0) {
                    return 0;
                }
                return 0;
            }
        });
    }

    private static String a(String string, boolean bl, QueryRuleEnum queryRuleEnum) {
        if (queryRuleEnum == QueryRuleEnum.IN) {
            return org.jeecg.modules.online.cgform.util.b.a(string, bl);
        }
        if (bl) {
            return z + QueryGenerator.converRuleValue((String) string) + z;
        }
        return QueryGenerator.converRuleValue((String) string);
    }

    private static String a(String string, boolean bl) {
        if (string == null || string.length() == 0) {
            return "()";
        }
        string = QueryGenerator.converRuleValue((String) string);
        String[] arrstring = string.split(COMMA);
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string2 : arrstring) {
            if (string2 == null || string2.length() == 0) continue;
            if (bl) {
                arrayList.add(z + string2 + z);
                continue;
            }
            arrayList.add(string2);
        }
        return "(" + StringUtils.join(arrayList, (String) COMMA) + ")";
    }

    private static void a(String string, SysPermissionDataRuleModel sysPermissionDataRuleModel, String string2, String string3, StringBuffer stringBuffer) {
        QueryRuleEnum queryRuleEnum = QueryRuleEnum.getByValue((String) sysPermissionDataRuleModel.getRuleConditions());
        boolean bl = !org.jeecg.modules.online.cgform.util.i.a(string3);
        String string4 = org.jeecg.modules.online.cgform.util.b.a(sysPermissionDataRuleModel.getRuleValue(), bl, queryRuleEnum);
        if (string4 == null || queryRuleEnum == null) {
            return;
        }
        if ("ORACLE".equalsIgnoreCase(string) && "Date".equals(string3)) {
            string4 = (string4 = string4.replace(z, "")).length() == 10 ? org.jeecg.modules.online.cgform.util.b.b(string4) : org.jeecg.modules.online.cgform.util.b.a(string4);
        }
        switch (queryRuleEnum) {
            case GT: {
                stringBuffer.append(AND + string2 + n + string4);
                break;
            }
            case GE: {
                stringBuffer.append(AND + string2 + m + string4);
                break;
            }
            case LT: {
                stringBuffer.append(AND + string2 + p + string4);
                break;
            }
            case LE: {
                stringBuffer.append(AND + string2 + o + string4);
                break;
            }
            case EQ: {
                stringBuffer.append(AND + string2 + EQUAL + string4);
                break;
            }
            case NE: {
                stringBuffer.append(AND + string2 + " <> " + string4);
                break;
            }
            case IN: {
                stringBuffer.append(AND + string2 + " IN " + string4);
                break;
            }
            case LIKE: {
                stringBuffer.append(AND + string2 + " LIKE '%" + QueryGenerator.trimSingleQuote((String) string4) + "%'");
                break;
            }
            case LEFT_LIKE: {
                stringBuffer.append(AND + string2 + " LIKE '%" + QueryGenerator.trimSingleQuote((String) string4) + z);
                break;
            }
            case RIGHT_LIKE: {
                stringBuffer.append(AND + string2 + " LIKE '" + QueryGenerator.trimSingleQuote((String) string4) + "%'");
                break;
            }
            default: {
                log.info("--查询规则未匹配到---");
            }
        }
    }

    public static String a(String string, JSONObject jSONObject) {
        if (jSONObject == null) {
            return string;
        }
        string = string.replace("#{UUID}", UUIDGenerator.generate());
        for (String string2 : QueryGenerator.getSqlRuleParams(string)) {
            String string3;
            if (jSONObject.get((Object) string2.toUpperCase()) == null && jSONObject.get((Object) string2.toLowerCase()) == null) {
                string3 = QueryGenerator.converRuleValue((String) string2);
                string = string.replace("#{" + string2 + "}", string3);
                continue;
            }
            string3 = null;
            if (jSONObject.containsKey((Object) string2.toLowerCase())) {
                string3 = jSONObject.getString(string2.toLowerCase());
            } else if (jSONObject.containsKey((Object) string2.toUpperCase())) {
                string3 = jSONObject.getString(string2.toUpperCase());
            }
            string = string.replace("#{" + string2 + "}", string3);
        }
        return string;
    }

    public static String c(String string, List<OnlCgformButton> list) {
        string = org.jeecg.modules.online.cgform.util.b.d(string, list);
        for (String string2 : aB.split(COMMA)) {
            Matcher matcher;
            Pattern pattern;
            if ("beforeAdd,afterAdd,mounted,created".indexOf(string2) >= 0) {
                pattern = Pattern.compile("(" + string2 + "\\s*\\(\\)\\s*\\{)");
                matcher = pattern.matcher(string);
                if (!matcher.find()) continue;
                string = string.replace(matcher.group(0), string2 + "(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
                continue;
            }
            pattern = Pattern.compile("(" + string2 + "\\s*\\(row\\)\\s*\\{)");
            matcher = pattern.matcher(string);
            if (matcher.find()) {
                string = string.replace(matcher.group(0), string2 + "(that,row){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
                continue;
            }
            Pattern pattern2 = Pattern.compile("(" + string2 + "\\s*\\(\\)\\s*\\{)");
            Matcher matcher2 = pattern2.matcher(string);
            if (!matcher2.find()) continue;
            string = string.replace(matcher2.group(0), string2 + "(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
        }
        return org.jeecg.modules.online.cgform.util.b.d(string);
    }

    public static void a(OnlCgformEnhanceJs onlCgformEnhanceJs, String string, List<OnlCgformField> list) {
        if (onlCgformEnhanceJs == null || oConvertUtils.isEmpty((Object) onlCgformEnhanceJs.getCgJs())) {
            return;
        }
        String string2 = onlCgformEnhanceJs.getCgJs();
        String string3 = "onlChange";
        Pattern pattern = Pattern.compile("(" + string + "_" + string3 + "\\s*\\(\\)\\s*\\{)");
        Matcher matcher = pattern.matcher(string2);
        if (matcher.find()) {
            string2 = string2.replace(matcher.group(0), string + "_" + string3 + "(){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
            for (OnlCgformField onlCgformField : list) {
                Pattern pattern2 = Pattern.compile("(" + onlCgformField.getDbFieldName() + "\\s*\\(\\))");
                Matcher matcher2 = pattern2.matcher(string2);
                if (!matcher2.find()) continue;
                string2 = string2.replace(matcher2.group(0), onlCgformField.getDbFieldName() + "(that,event)");
            }
        }
        onlCgformEnhanceJs.setCgJs(string2);
    }

    public static void a(OnlCgformEnhanceJs onlCgformEnhanceJs, String string, List<OnlCgformField> list, boolean bl) {
        if (onlCgformEnhanceJs == null || oConvertUtils.isEmpty((Object) onlCgformEnhanceJs.getCgJs())) {
            return;
        }
        String string2 = onlCgformEnhanceJs.getCgJs();
        String string3 = "onlChange";
        Pattern pattern = Pattern.compile("([^_]" + string3 + "\\s*\\(\\)\\s*\\{)");
        Matcher matcher = pattern.matcher(string2);
        if (matcher.find()) {
            string2 = string2.replace(matcher.group(0), string3 + "(){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
            for (OnlCgformField onlCgformField : list) {
                Pattern pattern2 = Pattern.compile("(" + onlCgformField.getDbFieldName() + "\\s*\\(\\))");
                Matcher matcher2 = pattern2.matcher(string2);
                if (!matcher2.find()) continue;
                string2 = string2.replace(matcher2.group(0), onlCgformField.getDbFieldName() + "(that,event)");
            }
        }
        onlCgformEnhanceJs.setCgJs(string2);
        org.jeecg.modules.online.cgform.util.b.a(onlCgformEnhanceJs);
        org.jeecg.modules.online.cgform.util.b.a(onlCgformEnhanceJs, string, list);
    }

    public static void a(OnlCgformEnhanceJs onlCgformEnhanceJs) {
        String string = onlCgformEnhanceJs.getCgJs();
        String string2 = "show";
        Pattern pattern = Pattern.compile("(" + string2 + "\\s*\\(\\)\\s*\\{)");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            string = string.replace(matcher.group(0), string2 + "(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
        }
        onlCgformEnhanceJs.setCgJs(string);
    }

    public static String d(String string) {
        log.info("最终的增强JS", (Object) string);
        return "class OnlineEnhanceJs{constructor(getAction,postAction,deleteAction){this._getAction=getAction;this._postAction=postAction;this._deleteAction=deleteAction;}" + string + "}";
    }

    public static String d(String string, List<OnlCgformButton> list) {
        if (list != null) {
            for (OnlCgformButton onlCgformButton : list) {
                Matcher matcher;
                Pattern pattern;
                String string2 = onlCgformButton.getButtonCode();
                if ("link".equals(onlCgformButton.getButtonStyle())) {
                    pattern = Pattern.compile("(" + string2 + "\\s*\\(row\\)\\s*\\{)");
                    matcher = pattern.matcher(string);
                    if (matcher.find()) {
                        string = string.replace(matcher.group(0), string2 + "(that,row){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
                        continue;
                    }
                    Pattern pattern2 = Pattern.compile("(" + string2 + "\\s*\\(\\)\\s*\\{)");
                    Matcher matcher2 = pattern2.matcher(string);
                    if (!matcher2.find()) continue;
                    string = string.replace(matcher2.group(0), string2 + "(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
                    continue;
                }
                if (!"button".equals(onlCgformButton.getButtonStyle()) && !FORM.equals(onlCgformButton.getButtonStyle())
                        || !(matcher = (pattern = Pattern.compile("(" + string2 + "\\s*\\(\\)\\s*\\{)")).matcher(string)).find())
                    continue;
                string = string.replace(matcher.group(0), string2 + "(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;");
            }
        }
        return string;
    }

    public static JSONArray a(List<OnlCgformField> list, List<String> list2) {
        JSONArray jSONArray = new JSONArray();
        ISysBaseAPI iSysBaseAPI = (ISysBaseAPI) SpringContextUtils.getBean(ISysBaseAPI.class);
        for (OnlCgformField onlCgformField : list) {
            String string = onlCgformField.getDbFieldName();
            if ("id".equals(string)) continue;
            JSONObject jSONObject = new JSONObject();
            if (list2.indexOf(string) >= 0) {
                jSONObject.put("disabled", (Object) true);
            }
            if (onlCgformField.getIsReadOnly() != null && 1 == onlCgformField.getIsReadOnly()) {
                jSONObject.put("disabled", (Object) true);
            }
            jSONObject.put("title", (Object) onlCgformField.getDbFieldTxt());
            jSONObject.put("key", (Object) string);
            String string2 = org.jeecg.modules.online.cgform.util.b.c(onlCgformField);
            jSONObject.put("type", (Object) string2);
            if (onlCgformField.getFieldLength() == null) {
                jSONObject.put("width", (Object) "186px");
            } else if ("sel_depart".equals(string2) || "sel_user".equals(string2)) {
                jSONObject.put("width", (Object) "");
            } else {
                jSONObject.put("width", (Object) (onlCgformField.getFieldLength() + "px"));
            }
            if (string2.equals(FILE) || string2.equals(IMAGE)) {
                jSONObject.put("responseName", (Object) "message");
                jSONObject.put("token", (Object) true);
            }
            if (string2.equals(SWITCH)) {
                jSONObject.put("type", (Object) "checkbox");
                JSONArray object = new JSONArray();
                if (oConvertUtils.isEmpty((Object) onlCgformField.getFieldExtendJson())) {
                    object.add(r);
                    object.add(A);
                } else {
                    object = JSONArray.parseArray(onlCgformField.getFieldExtendJson());
                }
                jSONObject.put("customValue", object);
            }
            if (string2.equals(POPUP)) {
                jSONObject.put("popupCode", (Object) onlCgformField.getDictTable());
                jSONObject.put("orgFields", (Object) onlCgformField.getDictField());
                jSONObject.put("destFields", (Object) onlCgformField.getDictText());
                String object = onlCgformField.getDictText();
                if (object != null && !((String) object).equals("")) {
                    String[] arrstring;
                    ArrayList<String> arrayList = new ArrayList<String>();
                    for (String string3 : arrstring = ((String) object).split(COMMA)) {
                        if (org.jeecg.modules.online.cgform.util.b.a(string3, list)) continue;
                        arrayList.add(string3);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("title", (Object) string3);
                        jSONObject2.put("key", (Object) string3);
                        jSONObject2.put("type", (Object) "hidden");
                        jSONArray.add((Object) jSONObject2);
                    }
                }
            }
            jSONObject.put("defaultValue", (Object) onlCgformField.getDbDefaultVal());
            jSONObject.put("fieldDefaultValue", (Object) onlCgformField.getFieldDefaultValue());
            jSONObject.put("placeholder", (Object) ("请输入" + onlCgformField.getDbFieldTxt()));
            jSONObject.put("validateRules", (Object) org.jeecg.modules.online.cgform.util.b.b(onlCgformField));
            if (aj.equals(onlCgformField.getFieldShowType()) || "radio".equals(onlCgformField.getFieldShowType()) || "checkbox_meta".equals(onlCgformField.getFieldShowType()) || "list_multi".equals(onlCgformField.getFieldShowType()) || "sel_search".equals(onlCgformField.getFieldShowType())) {
                jSONObject.put("view", (Object) onlCgformField.getFieldShowType());
                jSONObject.put("dictTable", (Object) onlCgformField.getDictTable());
                jSONObject.put("dictText", (Object) onlCgformField.getDictText());
                jSONObject.put("dictCode", (Object) onlCgformField.getDictField());
                if ("list_multi".equals(onlCgformField.getFieldShowType())) {
                    jSONObject.put("width", (Object) "230px");
                }
            }
            jSONObject.put("fieldExtendJson", (Object) onlCgformField.getFieldExtendJson());
            jSONArray.add((Object) jSONObject);
        }
        return jSONArray;
    }

    private static JSONArray b(OnlCgformField onlCgformField) {
        JSONObject jSONObject;
        JSONArray jSONArray = new JSONArray();
        if (onlCgformField.getDbIsNull() == 0 || "1".equals(onlCgformField.getFieldMustInput())) {
            jSONObject = new JSONObject();
            jSONObject.put("required", (Object) true);
            jSONObject.put("message", (Object) (onlCgformField.getDbFieldTxt() + "不能为空!"));
            jSONArray.add((Object) jSONObject);
        }
        if (oConvertUtils.isNotEmpty((Object) onlCgformField.getFieldValidType())) {
            jSONObject = new JSONObject();
            if ("only".equals(onlCgformField.getFieldValidType())) {
                jSONObject.put("unique", (Object) true);
                jSONObject.put("message", (Object) (onlCgformField.getDbFieldTxt() + "不能重复"));
            } else {
                jSONObject.put("pattern", (Object) onlCgformField.getFieldValidType());
                jSONObject.put("message", (Object) (onlCgformField.getDbFieldTxt() + "格式不正确"));
            }
            jSONArray.add((Object) jSONObject);
        }
        return jSONArray;
    }

    public static Map<String, Object> b(Map<String, Object> map) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        if (map == null || map.isEmpty()) {
            return hashMap;
        }
        Set<String> set = map.keySet();
        for (String string : set) {
            String string2;
            Object object2 = map.get(string);
            if (object2 instanceof Clob) {
                object2 = org.jeecg.modules.online.cgform.util.b.a((Clob) object2);
            } else if (object2 instanceof byte[]) {
                object2 = new String((byte[]) object2);
            } else if (object2 instanceof Blob) {
                try {
                    if (object2 != null) {
                        Blob object = (Blob) object2;
                        object2 = new String(object.getBytes(1L, (int) object.length()), "UTF-8");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            String object = string.toLowerCase();
            if (object2 != null && object2 instanceof String && (string2 = object2.toString()).startsWith("[") && string2.endsWith("]")) {
                object2 = JSONArray.parseArray((String) string2);
            }
            hashMap.put((String) object, object2 == null ? "" : object2);
        }
        return hashMap;
    }

    public static JSONObject a(JSONObject jSONObject) {
        if (org.jeecg.modules.online.config.b.d.a()) {
            JSONObject jSONObject2 = new JSONObject();
            if (jSONObject == null || jSONObject.isEmpty()) {
                return jSONObject2;
            }
            for (String string : jSONObject.keySet()) {
                String string2 = string.toLowerCase();
                jSONObject2.put(string2, jSONObject.get((Object) string));
            }
            return jSONObject2;
        }
        return jSONObject;
    }

    public static List<Map<String, Object>> d(List<Map<String, Object>> list) {
        ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : list) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            Set<String> set = map.keySet();
            for (String string : set) {
                Object object2 = map.get(string);
                if (object2 instanceof Clob) {
                    object2 = org.jeecg.modules.online.cgform.util.b.a((Clob) object2);
                } else if (object2 instanceof byte[]) {
                    object2 = new String((byte[]) object2);
                } else if (object2 instanceof Blob) {
                    try {
                        if (object2 != null) {
                            Blob object = (Blob) object2;
                            object2 = new String(object.getBytes(1L, (int) object.length()), "UTF-8");
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                String object = string.toLowerCase();
                hashMap.put((String) object, object2 == null ? "" : object2);
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public static String a(Clob clob) {
        String string = "";
        try {
            Reader reader = clob.getCharacterStream();
            char[] arrc = new char[(int) clob.length()];
            reader.read(arrc);
            string = new String(arrc);
            reader.close();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return string;
    }

    public static Map<String, Object> c(String string, List<OnlCgformField> list, JSONObject jSONObject) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        String string2 = "";
        try {
            string2 = org.jeecg.modules.online.config.b.d.getDatabaseType();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } catch (DBException dBException) {
            dBException.printStackTrace();
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        boolean bl = false;
        String string3 = null;
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (loginUser == null) {
            throw new JeecgBootException("online保存表单数据异常:系统未找到当前登陆用户信息");
        }
        for (OnlCgformField onlCgformField : list) {
            String string4;
            String string5 = onlCgformField.getDbFieldName();
            if (null == string5) {
                log.info("--------online保存表单数据遇见空名称的字段------->>" + onlCgformField.getId());
                continue;
            }
            if (jSONObject.get((Object) string5) == null && !u.equalsIgnoreCase(string5) && !t.equalsIgnoreCase(string5) && !x.equalsIgnoreCase(string5))
                continue;
            org.jeecg.modules.online.cgform.util.b.a(onlCgformField, loginUser, jSONObject, u, t, x);
            if ("".equals(jSONObject.get((Object) string5)) && (org.jeecg.modules.online.cgform.util.i.a(string4 = onlCgformField.getDbType()) || org.jeecg.modules.online.cgform.util.i.b(string4)))
                continue;
            if ("id".equals(string5.toLowerCase())) {
                bl = true;
                string3 = jSONObject.getString(string5);
                continue;
            }
            stringBuffer.append(COMMA + string5);
            string4 = org.jeecg.modules.online.cgform.util.i.a(string2, onlCgformField, jSONObject, hashMap);
            stringBuffer2.append(COMMA + string4);
        }
        if (!bl || oConvertUtils.isEmpty(string3)) {
            string3 = org.jeecg.modules.online.cgform.util.b.a();
        }
        String string6 = "insert into " + org.jeecg.modules.online.cgform.util.b.f(string) + "(" + "id" + stringBuffer.toString() + ") values(" + z + string3 + z + stringBuffer2.toString() + ")";
        hashMap.put("execute_sql_string", string6);
        log.info("--表单设计器表单保存sql-->" + (String) string6);
        return hashMap;
    }

    public static Map<String, Object> d(String string, List<OnlCgformField> list, JSONObject jSONObject) {
        StringBuffer stringBuffer = new StringBuffer();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        String string2 = "";
        try {
            string2 = org.jeecg.modules.online.config.b.d.getDatabaseType();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        } catch (DBException dBException) {
            dBException.printStackTrace();
        }
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (loginUser == null) {
            throw new JeecgBootException("online保存表单数据异常:系统未找到当前登陆用户信息");
        }
        for (OnlCgformField object2 : list) {
            String string3;
            String string4 = object2.getDbFieldName();
            if (null == string4) {
                log.info("--------online修改表单数据遇见空名称的字段------->>" + object2.getId());
                continue;
            }
            if ("id".equals(string4) || jSONObject.get((Object) string4) == null && !w.equalsIgnoreCase(string4) && !v.equalsIgnoreCase(string4) && !x.equalsIgnoreCase(string4))
                continue;
            org.jeecg.modules.online.cgform.util.b.a(object2, loginUser, jSONObject, w, v, x);
            if ("".equals(jSONObject.get((Object) string4)) && (org.jeecg.modules.online.cgform.util.i.a(string3 = object2.getDbType()) || org.jeecg.modules.online.cgform.util.i.b(string3)))
                continue;
            string3 = org.jeecg.modules.online.cgform.util.i.a(string2, object2, jSONObject, hashMap);
            stringBuffer.append(string4 + EQUAL + string3 + COMMA);
        }
        Object object3 = stringBuffer.toString();
        if (((String) object3).endsWith(COMMA)) {
            object3 = ((String) object3).substring(0, ((String) object3).length() - 1);
        }
        String string5 = "update " + org.jeecg.modules.online.cgform.util.b.f(string) + " set " + (String) object3 + WHERE + "id" + EQUAL + z + jSONObject.getString("id") + z;
        log.info("--表单设计器表单编辑sql-->" + string5);
        hashMap.put("execute_sql_string", string5);
        return hashMap;
    }

    public static Map<String, Object> a(String string, String string2, String string3) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        String string4 = "update " + org.jeecg.modules.online.cgform.util.b.f(string) + " set " + string2 + EQUAL + z + 0 + z + WHERE + "id" + EQUAL + z + string3 + z;
        log.info("--修改树节点状态：为无子节点sql-->" + string4);
        hashMap.put("execute_sql_string", string4);
        return hashMap;
    }

    public static String e(String string) {
        if (string == null || "".equals(string) || "0".equals(string)) {
            return "";
        }
        return "CODE like '" + string + "%" + z;
    }

    public static String f(String string) {
        if (Pattern.matches("^[a-zA-z].*\\$\\d+$", string)) {
            return string.substring(0, string.lastIndexOf(s));
        }
        return string;
    }

    public static void a(LinkDownProperty linkDownProperty, List<OnlCgformField> list, List<String> list2) {
        String string = linkDownProperty.getDictTable();
        JSONObject jSONObject = JSONObject.parseObject((String) string);
        String string2 = jSONObject.getString("linkField");
        ArrayList<a> arrayList = new ArrayList<a>();
        if (oConvertUtils.isNotEmpty((Object) string2)) {
            String[] arrstring = string2.split(COMMA);
            block0:
            for (OnlCgformField onlCgformField : list) {
                String string3 = onlCgformField.getDbFieldName();
                for (String string4 : arrstring) {
                    if (!string4.equals(string3)) continue;
                    list2.add(string3);
                    arrayList.add(new a(onlCgformField.getDbFieldTxt(), string3));
                    continue block0;
                }
            }
        }
        linkDownProperty.setOtherColumns(arrayList);
    }

    public static String a(byte[] arrby, String string, String string2, String string3) {
        return CommonUtils.uploadOnlineImage((byte[]) arrby, (String) string, (String) string2, (String) string3);
    }

    public static List<String> e(List<OnlCgformField> list) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (OnlCgformField onlCgformField : list) {
            if (!IMAGE.equals(onlCgformField.getFieldShowType())) continue;
            arrayList.add(onlCgformField.getDbFieldTxt());
        }
        return arrayList;
    }

    public static List<String> b(List<OnlCgformField> list, String string) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (OnlCgformField onlCgformField : list) {
            if (!IMAGE.equals(onlCgformField.getFieldShowType())) continue;
            arrayList.add(string + "_" + onlCgformField.getDbFieldTxt());
        }
        return arrayList;
    }

    public static String a() {
        long l2 = IdWorker.getId();
        return String.valueOf(l2);
    }

    public static String a(Exception exception) {
        String string;
        String string2 = string = exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage();
        if (string.indexOf("ORA-01452") != -1) {
            string = "ORA-01452: 无法 CREATE UNIQUE INDEX; 找到重复的关键字";
        } else if (string.indexOf("duplicate key") != -1) {
            string = "无法 CREATE UNIQUE INDEX; 找到重复的关键字";
        }
        return string;
    }

    public static List<DictModel> a(OnlCgformField onlCgformField) {
        ArrayList<DictModel> arrayList = new ArrayList<DictModel>();
        String string = onlCgformField.getFieldExtendJson();
        String string2 = "是";
        String string3 = "否";
        JSONArray jSONArray = JSONArray.parseArray("[\"Y\",\"N\"]");

        if (oConvertUtils.isNotEmpty((Object) string)) {
            jSONArray = JSONArray.parseArray((String) string);
        }
        DictModel dictModel = new DictModel(jSONArray.getString(0), string2);
        DictModel dictModel2 = new DictModel(jSONArray.getString(1), string3);
        arrayList.add(dictModel);
        arrayList.add(dictModel2);
        return arrayList;
    }

    private static String c(OnlCgformField onlCgformField) {
        if ("checkbox".equals(onlCgformField.getFieldShowType())) {
            return "checkbox";
        }
        if (aj.equals(onlCgformField.getFieldShowType())) {
            return "select";
        }
        if (SWITCH.equals(onlCgformField.getFieldShowType())) {
            return SWITCH;
        }
        if ("sel_user".equals(onlCgformField.getFieldShowType())) {
            return "sel_user";
        }
        if ("sel_depart".equals(onlCgformField.getFieldShowType())) {
            return "sel_depart";
        }
        if (IMAGE.equals(onlCgformField.getFieldShowType()) || FILE.equals(onlCgformField.getFieldShowType()) || "radio".equals(onlCgformField.getFieldShowType()) || POPUP.equals(onlCgformField.getFieldShowType()) || "list_multi".equals(onlCgformField.getFieldShowType()) || "sel_search".equals(onlCgformField.getFieldShowType())) {
            return onlCgformField.getFieldShowType();
        }
        if ("datetime".equals(onlCgformField.getFieldShowType())) {
            return "datetime";
        }
        if ("date".equals(onlCgformField.getFieldShowType())) {
            return "date";
        }
        if ("int".equals(onlCgformField.getDbType())) {
            return "inputNumber";
        }
        if ("double".equals(onlCgformField.getDbType()) || "BigDecimal".equals(onlCgformField.getDbType())) {
            return "inputNumber";
        }
        return "input";
    }

    private static String getDatabseType() {
        if (oConvertUtils.isNotEmpty((Object) aC)) {
            return aC;
        }
        try {
            aC = org.jeecg.modules.online.config.b.d.getDatabaseType();
            return aC;
        } catch (Exception exception) {
            exception.printStackTrace();
            return aC;
        }
    }

    public static List<String> f(List<String> list) {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String string : list) {
            arrayList.add(string.toLowerCase());
        }
        return arrayList;
    }

    private static String b(String string, String string2) {
        String string3 = "";
        if (string2 == null || "".equals(string2)) {
            return string3;
        }
        String[] arrstring = string2.split(COMMA);
        for (int i2 = 0; i2 < arrstring.length; ++i2) {
            if (i2 > 0) {
                string3 = string3 + AND;
            }
            string3 = string3 + string + LIKE;
            if ("SQLSERVER".equals(org.jeecg.modules.online.cgform.util.b.getDatabseType())) {
                string3 = string3 + A;
            }
            string3 = string3 + "'%" + arrstring[i2] + "%" + z;
        }
        log.info(" POPUP fieldSql: " + string3);
        return string3;
    }

    public static String a(String string, String string2, StringBuffer stringBuffer) {
        String string3 = "logs" + File.separator + ((SimpleDateFormat) DateUtils.yyyyMMdd.get()).format(new Date()) + File.separator;
        String string4 = string + File.separator + string3;
        File file = new File(string4);
        if (!file.exists()) {
            file.mkdirs();
        }
        String string5 = string2 + Math.round(Math.random() * 10000.0);
        String string6 = string4 + string5 + ".txt";
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(string6));
            bufferedWriter.write(stringBuffer.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception exception) {
            log.info("excel导入生成错误日志文件异常:" + exception.getMessage());
        }
        return "/sys/common/static/" + string3 + string5 + ".txt";
    }

    public static JSONObject b(JSONObject var0) {
        JSONObject var1;
        if (var0.containsKey("properties")) {
            var1 = var0.getJSONObject("properties");
        } else {
            JSONObject var2 = var0.getJSONObject("schema");
            var1 = var2.getJSONObject("properties");
        }

        ISysBaseAPI var15 = SpringContextUtils.getBean(ISysBaseAPI.class);

        for (String key : var1.keySet()) {
            JSONObject var5 = var1.getJSONObject(key);
            String var6 = var5.getString("view");
            String var7;
            if (c(var6)) {
                var7 = var5.getString("dictCode");
                String var16 = var5.getString("dictText");
                String var17 = var5.getString("dictTable");
                Object var18 = new ArrayList();
                if (oConvertUtils.isNotEmpty(var17)) {
                    var18 = var15.queryTableDictItemsByCode(var17, var16, var7);
                } else if (oConvertUtils.isNotEmpty(var7)) {
                    var18 = var15.queryDictItemsByCode(var7);
                }

                if (var18 != null && ((List) var18).size() > 0) {
                    var5.put("enum", var18);
                }
            } else if ("tab".equals(var6)) {
                var7 = var5.getString("relationType");
                if ("1".equals(var7)) {
                    b(var5);
                } else {
                    JSONArray var8 = var5.getJSONArray("columns");

                    for (int var9 = 0; var9 < var8.size(); ++var9) {
                        JSONObject var10 = var8.getJSONObject(var9);
                        if (c(var10)) {
                            String var11 = var10.getString("dictCode");
                            String var12 = var10.getString("dictText");
                            String var13 = var10.getString("dictTable");
                            Object var14 = new ArrayList();
                            if (oConvertUtils.isNotEmpty(var13)) {
                                var14 = var15.queryTableDictItemsByCode(var13, var12, var11);
                            } else if (oConvertUtils.isNotEmpty(var11)) {
                                var14 = var15.queryDictItemsByCode(var11);
                            }

                            if (var14 != null && ((List) var14).size() > 0) {
                                var10.put("options", var14);
                            }
                        }
                    }
                }
            }
        }

        return var0;
    }

    private static boolean c(JSONObject jSONObject) {
        String string;
        Object object = jSONObject.get((Object) "view");
        return object != null && (aj.equals(string = object.toString()) || "radio".equals(string) || "checkbox_meta".equals(string) || "list_multi".equals(string) || "sel_search".equals(string));
    }
}

