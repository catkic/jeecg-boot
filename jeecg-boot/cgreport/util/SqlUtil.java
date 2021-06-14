/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletRequest
 *  org.jeecg.common.system.query.QueryGenerator
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package org.jeecg.modules.online.cgreport.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlUtil {
    private static final Logger p = LoggerFactory.getLogger(SqlUtil.class);
    public static final String a = " where ";
    public static final String b = " and ";
    public static final String c = " or ";
    public static final String d = "select * from ( {0}) sel_tab00 limit {1},{2}";
    public static final String e = "select * from ( {0}) sel_tab00 limit {2} offset {1}";
    public static final String f = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}";
    public static final String g = "select * from ( select row_number() over(order by tempColumn) tempRowNumber, * from (select top {1} tempColumn = 0, {0}) t ) tt where tempRowNumber > {2}";
    public static final String h = "select distinct table_name from information_schema.columns where table_schema = {0}";
    public static final String i = "SELECT distinct c.relname AS  table_name FROM pg_class c";
    public static final String j = "select distinct colstable.table_name as  table_name from user_tab_cols colstable";
    public static final String k = "select distinct c.name as  table_name from sys.objects c";
    public static final String l = "select column_name from information_schema.columns where table_name = {0} and table_schema = {1}";
    public static final String m = "select table_name from information_schema.columns where table_name = {0}";
    public static final String n = "select column_name from all_tab_columns where table_name ={0}";
    public static final String o = "select name from syscolumns where id={0}";

    public static String a(String string, Map map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT t.* FROM ( ");
        stringBuilder.append(string + " ");
        stringBuilder.append(") t ");
        if (map != null && map.size() >= 1) {
            stringBuilder.append("WHERE 1=1  ");
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String string2 = String.valueOf(iterator.next());
                String string3 = String.valueOf(map.get(string2));
                if (!oConvertUtils.isNotEmpty((Object)string3)) continue;
                stringBuilder.append(" AND ");
                stringBuilder.append(" " + string2 + string3);
            }
        }
        return stringBuilder.toString();
    }

    public static String b(String string, Map map) {
        String string2 = SqlUtil.a(string, map);
        string2 = "SELECT COUNT(*) COUNT FROM (" + string2 + ") t2";
        return string2;
    }

    public static String a(String string, Map map, int n2, int n3) {
        String string2 = "jdbc:mysql://127.0.0.1:3306/jeecg-boot?characterEncoding=UTF-8&useUnicode=true&useSSL=false";
        int n4 = (n2 - 1) * n3;
        Object[] arrobject = new String[]{string, n4 + "", n3 + ""};
        if (string2.indexOf("MYSQL") != -1 || string2.indexOf("MARIADB") != -1) {
            string = MessageFormat.format(d, arrobject);
        } else if (string2.indexOf("POSTGRESQL") != -1) {
            string = MessageFormat.format(e, arrobject);
        } else {
            int n5 = (n2 - 1) * n3;
            int n6 = n5 + n3;
            arrobject[2] = Integer.toString(n5);
            arrobject[1] = Integer.toString(n6);
            if (string2.indexOf("ORACLE") != -1) {
                string = MessageFormat.format(f, arrobject);
            } else if (string2.indexOf("SQLSERVER") != -1) {
                arrobject[0] = string.substring(SqlUtil.d(string));
                string = MessageFormat.format(g, arrobject);
            }
        }
        return string;
    }

    public static String a(String string, String string2, String string3, Map map, int n2, int n3) {
        string3 = SqlUtil.a(string3, map);
        int n4 = (n2 - 1) * n3;
        Object[] arrobject = new String[]{string3, n4 + "", n3 + ""};
        String string4 = "";
        if ("MYSQL".equalsIgnoreCase(string4) || "MARIADB".equalsIgnoreCase(string4)) {
            string3 = MessageFormat.format(d, arrobject);
        } else if ("POSTGRESQL".equalsIgnoreCase(string4)) {
            string3 = MessageFormat.format(e, arrobject);
        } else {
            int n5 = (n2 - 1) * n3;
            int n6 = n5 + n3;
            arrobject[2] = Integer.toString(n5);
            arrobject[1] = Integer.toString(n6);
            if ("ORACLE".equalsIgnoreCase(string4)) {
                string3 = MessageFormat.format(f, arrobject);
            } else if ("SQLSERVER".equalsIgnoreCase(string4)) {
                arrobject[0] = string3.substring(SqlUtil.d(string3));
                string3 = MessageFormat.format(g, arrobject);
            }
        }
        return string3;
    }

    private static int d(String string) {
        int n2 = string.toLowerCase().indexOf("select");
        int n3 = string.toLowerCase().indexOf("select distinct");
        return n2 + (n3 == n2 ? 15 : 6);
    }

    public static String a(String string, String ... arrstring) {
        if (oConvertUtils.isNotEmpty((Object)string)) {
            if ("MYSQL".equals(string) || "MARIADB".equals(string)) {
                return MessageFormat.format(h, arrstring);
            }
            if ("ORACLE".equals(string)) {
                return j;
            }
            if ("POSTGRESQL".equals(string)) {
                return i;
            }
            if ("SQLSERVER".equals(string)) {
                return k;
            }
        }
        return null;
    }

    public static String b(String string, String ... arrstring) {
        if (oConvertUtils.isNotEmpty((Object)string)) {
            if ("MYSQL".equals(string) || "MARIADB".equals(string)) {
                return MessageFormat.format(l, arrstring);
            }
            if ("ORACLE".equals(string)) {
                return MessageFormat.format(n, arrstring);
            }
            if ("POSTGRESQL".equals(string)) {
                return MessageFormat.format(m, arrstring);
            }
            if ("SQLSERVER".equals(string)) {
                return MessageFormat.format(o, arrstring);
            }
        }
        return null;
    }

    public static String a(String string) {
        string = string.replaceAll("(?i) where ", a);
        string = string.replaceAll("(?i) and ", b);
        string = string.replaceAll("(?i) or ", c);
        String string2 = "(,\\s*|\\s*(\\w|\\.)+\\s*[^, ]+ *\\S*)\\$\\{\\w+\\}\\S*";
        Pattern pattern = Pattern.compile(string2);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String string3 = matcher.group();
            p.debug("${}\u5339\u914d\u5e26\u53c2SQL\u7247\u6bb5 ==>" + string3);
            if (string3.indexOf(a) != -1) {
                String string4 = string3.substring(0, string3.indexOf(a));
                string = string.replace(string3, string4 + " where 1=1");
            } else if (string3.indexOf(b) != -1) {
                if ((string3 = string3.substring(string3.indexOf("and"))).indexOf("(") > 0) {
                    string3 = string3.substring(string3.indexOf("(") + 1);
                    string = string.replace(string3, " 1=1 ");
                } else {
                    string = string.replace(string3, "and 1=1");
                }
            } else if (string3.indexOf(c) != -1) {
                if ((string3 = string3.substring(string3.indexOf("or"))).indexOf("(") > 0) {
                    string3 = string3.substring(string3.indexOf("(") + 1);
                    string = string.replace(string3, " 1=1 ");
                } else {
                    string = string.replace(string3, "or 1=1");
                }
            } else {
                string = string3.startsWith(",") ? string.replace(string3, " ,1 ") : string.replace(string3, " 1=1 ");
            }
            p.debug("${}\u66ff\u6362\u540e\u7ed3\u679c ==>" + string);
        }
        string = string.replaceAll("(?i)\\(\\s*1=1\\s*(AND|OR)", "(");
        string = string.replaceAll("(?i)(AND|OR)\\s*1=1", "");
        return string;
    }

    public static void main(String[] args) {
        String string = "select * from sys_user where id   ='${id}' and del_flag=  ${flag}";
        System.out.println(SqlUtil.a(string));
    }

    public static Map<String, Object> a(HttpServletRequest httpServletRequest) {
        Map map = httpServletRequest.getParameterMap();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        Iterator iterator = map.entrySet().iterator();
        String string = "";
        String string2 = "";
        Object object = null;
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            string = (String)entry.getKey();
            object = entry.getValue();
            if ("_t".equals(string) || null == object) {
                string2 = "";
            } else if (object instanceof String[]) {
                String[] arrstring = (String[])object;
                for (int i2 = 0; i2 < arrstring.length; ++i2) {
                    string2 = arrstring[i2] + ",";
                }
                string2 = string2.substring(0, string2.length() - 1);
            } else {
                string2 = object.toString();
            }
            hashMap.put(string, string2);
        }
        return hashMap;
    }

    public static String b(String string) {
        String string2 = QueryGenerator.convertSystemVariables((String)string);
        String string3 = QueryGenerator.getAllConfigAuth();
        if (string.toLowerCase().indexOf("where") > 0) {
            return string2 + string3;
        }
        return string2 + " where 1=1  " + string3;
    }

    public static boolean c(String string) {
        String string2 = string.toLowerCase();
        return string2.indexOf("select") == 0;
    }
}

