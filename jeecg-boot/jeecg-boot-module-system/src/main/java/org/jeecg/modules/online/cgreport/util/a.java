package org.jeecg.modules.online.cgreport.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.DateUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.util.b;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.config.b.d;
import org.jeecg.modules.online.config.exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class a {
   private static final Logger a = LoggerFactory.getLogger(a.class);

   public a() {
   }

   public static void a(HttpServletRequest var0, Map<String, Object> var1, Map<String, Object> var2, Map<String, Object> var3) {
      String var4 = (String)var1.get("field_name");
      String var5 = (String)var1.get("search_mode");
      String var6 = (String)var1.get("field_type");
      String var7;
      String var8;
      String var9;
      if ("single".equals(var5)) {
         var7 = var0.getParameter(var4.toLowerCase());

         try {
            if (oConvertUtils.isEmpty(var7)) {
               return;
            }

            var8 = var0.getQueryString();
            if (var8.contains(var4 + "=")) {
               var9 = new String(var7.getBytes("ISO-8859-1"), "UTF-8");
               var7 = var9;
            }
         } catch (UnsupportedEncodingException var10) {
            a.error(var10.getMessage(), var10);
            return;
         }

         if (oConvertUtils.isNotEmpty(var7)) {
            if (var7.contains("*")) {
               var7 = var7.replaceAll("\\*", "%");
               var2.put(var4, " LIKE :" + var4);
            } else {
               var2.put(var4, " = :" + var4);
            }
         }

         var3.put(var4, a(var6, var7, true));
      } else if ("group".equals(var5)) {
         var7 = var0.getParameter(var4.toLowerCase() + "_begin");
         var8 = var0.getParameter(var4.toLowerCase() + "_end");
         if (oConvertUtils.isNotEmpty(var7)) {
            var9 = " >= :" + var4 + "_begin";
            var2.put(var4, var9);
            var3.put(var4 + "_begin", a(var6, var7, true));
         }

         if (oConvertUtils.isNotEmpty(var8)) {
            var9 = " <= :" + var4 + "_end";
            var2.put(new String(var4), var9);
            var3.put(var4 + "_end", a(var6, var8, false));
         }
      }

   }

   private static Object a(String var0, String var1, boolean var2) {
      Object var3 = null;
      if (oConvertUtils.isNotEmpty(var1)) {
         if ("String".equalsIgnoreCase(var0)) {
            var3 = var1;
         } else if ("Date".equalsIgnoreCase(var0)) {
            if (var1.length() != 19 && var1.length() == 10) {
               if (var2) {
                  var1 = var1 + " 00:00:00";
               } else {
                  var1 = var1 + " 23:59:59";
               }
            }

            SimpleDateFormat var4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            var3 = DateUtils.str2Date(var1, var4);
         } else if ("Double".equalsIgnoreCase(var0)) {
            var3 = var1;
         } else if ("Integer".equalsIgnoreCase(var0)) {
            var3 = var1;
         } else {
            var3 = var1;
         }
      }

      return var3;
   }

   public static String a(List<Map<String, Object>> var0, Long var1) {
      JSONObject var2 = new JSONObject();
      JSONArray var3 = new JSONArray();
      var2.put("total", var1);
      if (var0 != null) {
         Iterator var4 = var0.iterator();

         while(var4.hasNext()) {
            Map var5 = (Map)var4.next();
            JSONObject var6 = new JSONObject();

            String var8;
            String var9;
            for(Iterator var7 = var5.keySet().iterator(); var7.hasNext(); var6.put(var8, var9)) {
               var8 = (String)var7.next();
               var9 = String.valueOf(var5.get(var8));
               var8 = var8.toLowerCase();
               if (var8.contains("time") || var8.contains("date")) {
                  var9 = a(var9);
               }
            }

            var3.add(var6);
         }
      }

      var2.put("rows", var3);
      return var2.toString();
   }

   public static String a(List<Map<String, Object>> var0) {
      JSONArray var1 = new JSONArray();
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         Map var3 = (Map)var2.next();
         JSONObject var4 = new JSONObject();

         String var6;
         String var7;
         for(Iterator var5 = var3.keySet().iterator(); var5.hasNext(); var4.put(var6, var7)) {
            var6 = (String)var5.next();
            var7 = String.valueOf(var3.get(var6));
            var6 = var6.toLowerCase();
            if (var6.contains("time") || var6.contains("date")) {
               var7 = a(var7);
            }
         }

         var1.add(var4);
      }

      return var1.toString();
   }

   public static String a(String var0) {
      SimpleDateFormat var1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
      SimpleDateFormat var2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date var3 = null;

      try {
         var3 = var1.parse(var0);
         return var2.format(var3);
      } catch (Exception var5) {
         return var0;
      }
   }

   public static String a(List<OnlCgreportItem> var0, Map<String, Object> var1, String var2, String var3) {
      StringBuffer var4 = new StringBuffer();
      String var5 = var3;
      if (var3 == null) {
         try {
            var5 = d.getDatabaseType();
         } catch (SQLException var14) {
            var14.printStackTrace();
         } catch (DBException var15) {
            var15.printStackTrace();
         }
      }

      HashSet var6 = new HashSet();
      Iterator var7 = var0.iterator();

      while(true) {
         while(true) {
            String var9;
            String var10;
            Object var18;
            do {
               while(true) {
                  OnlCgreportItem var8;
                  do {
                     if (!var7.hasNext()) {
                        var7 = var1.keySet().iterator();

                        while(var7.hasNext()) {
                           String var16 = (String)var7.next();
                           if (var16.startsWith("popup_param_pre__")) {
                              var9 = var1.get(var16).toString();
                              var10 = var16.substring("popup_param_pre__".length());
                              if (!var6.contains(var10)) {
                                 String var17 = QueryGenerator.getSingleQueryConditionSql(var10, var2, var9, false);
                                 var4.append(" and " + var17);
                              }
                           }
                        }

                        return var4.toString();
                     }

                     var8 = (OnlCgreportItem)var7.next();
                     var9 = var8.getFieldName();
                     var10 = var8.getFieldType();
                  } while(1 != var8.getIsSearch());

                  Object var11;
                  if ("group".equals(var8.getSearchMode())) {
                     var11 = var1.get(var9 + "_begin");
                     if (var11 != null) {
                        var4.append(" and " + var2 + var9 + " >= ");
                        if (!"Long".equals(var10) && !"Integer".equals(var10)) {
                           if ("ORACLE".equals(var5)) {
                              if (var10.toLowerCase().equals("datetime")) {
                                 var4.append(b.a(var11.toString()));
                              } else if (var10.toLowerCase().equals("date")) {
                                 var4.append(b.b(var11.toString()));
                              }
                           } else {
                              var4.append("'" + var11.toString() + "'");
                           }
                        } else {
                           var4.append(var11.toString());
                        }
                     }

                     var18 = var1.get(var9 + "_end");
                     break;
                  }

                  var11 = var1.get(var9);
                  if (var11 != null) {
                     boolean var12 = !"Long".equals(var10) && !"Integer".equals(var10);
                     if ("ORACLE".equals(var5)) {
                        if (var10.toLowerCase().equals("datetime")) {
                           var11 = b.a(var11.toString());
                           var12 = false;
                        } else if (var10.toLowerCase().equals("date")) {
                           var11 = b.b(var11.toString());
                           var12 = false;
                        }
                     }

                     String var13 = QueryGenerator.getSingleQueryConditionSql(var9, var2, var11, var12, var5);
                     var4.append(" and " + var13);
                     var6.add(var9);
                  }
               }
            } while(var18 == null);

            var4.append(" and " + var2 + var9 + " <= ");
            if (!"Long".equals(var10) && !"Integer".equals(var10)) {
               if ("ORACLE".equals(var5)) {
                  if (var10.toLowerCase().equals("datetime")) {
                     var4.append(b.a(var18.toString()));
                  } else if (var10.toLowerCase().equals("date")) {
                     var4.append(b.b(var18.toString()));
                  }
               } else {
                  var4.append("'" + var18.toString() + "'");
               }
            } else {
               var4.append(var18.toString());
            }
         }
      }
   }
}
