package org.jeecg.modules.online.cgform.converter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jeecg.common.util.MyClassLoader;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.convertb.d;
import org.jeecg.modules.online.cgform.converter.convertb.e;
import org.jeecg.modules.online.cgform.converter.convertb.f;
import org.jeecg.modules.online.cgform.converter.convertb.g;
import org.jeecg.modules.online.cgform.converter.convertb.h;
import org.jeecg.modules.online.cgform.converter.convertb.i;
import org.jeecg.modules.online.cgform.converter.convertb.j;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class a {
   private static final Logger a = LoggerFactory.getLogger(a.class);
   private static final String b = "list";
   private static final String c = "radio";
   private static final String d = "checkbox";
   private static final String e = "list_multi";
   private static final String f = "sel_search";
   private static final String g = "sel_tree";
   private static final String h = "cat_tree";
   private static final String i = "link_down";
   private static final String j = "sel_depart";
   private static final String k = "sel_user";
   private static final String l = "pca";
   private static final String m = "switch";

   public a() {
   }

   public static FieldCommentConverter a(OnlCgformField var0) {
      String var1 = var0.getFieldShowType();
      Object var2 = null;
      byte var4 = -1;
      switch(var1.hashCode()) {
      case -1624761913:
         if (var1.equals("link_down")) {
            var4 = 7;
         }
         break;
      case -889473228:
         if (var1.equals("switch")) {
            var4 = 11;
         }
         break;
      case 110798:
         if (var1.equals("pca")) {
            var4 = 10;
         }
         break;
      case 3322014:
         if (var1.equals("list")) {
            var4 = 0;
         }
         break;
      case 45359719:
         if (var1.equals("cat_tree")) {
            var4 = 6;
         }
         break;
      case 108270587:
         if (var1.equals("radio")) {
            var4 = 1;
         }
         break;
      case 702184024:
         if (var1.equals("list_multi")) {
            var4 = 2;
         }
         break;
      case 1186535523:
         if (var1.equals("sel_tree")) {
            var4 = 5;
         }
         break;
      case 1186566288:
         if (var1.equals("sel_user")) {
            var4 = 9;
         }
         break;
      case 1536891843:
         if (var1.equals("checkbox")) {
            var4 = 3;
         }
         break;
      case 1624559481:
         if (var1.equals("sel_depart")) {
            var4 = 8;
         }
         break;
      case 2053565741:
         if (var1.equals("sel_search")) {
            var4 = 4;
         }
      }

      switch(var4) {
      case 0:
      case 1:
         var2 = new org.jeecg.modules.online.cgform.converter.convertb.c(var0);
         break;
      case 2:
      case 3:
         var2 = new f(var0);
         break;
      case 4:
         var2 = new d(var0);
         break;
      case 5:
         var2 = new i(var0);
         break;
      case 6:
         var2 = new org.jeecg.modules.online.cgform.converter.convertb.a(var0);
         break;
      case 7:
         var2 = new e(var0);
         break;
      case 8:
         var2 = new org.jeecg.modules.online.cgform.converter.convertb.b(var0);
         break;
      case 9:
         var2 = new j(var0);
         break;
      case 10:
         var2 = new g(var0);
         break;
      case 11:
         var2 = new h(var0);
         break;
      default:
         var2 = null;
      }

      return (FieldCommentConverter)var2;
   }

   public static Map<String, FieldCommentConverter> a(List<OnlCgformField> var0) {
      HashMap var1 = new HashMap();
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         OnlCgformField var3 = (OnlCgformField)var2.next();
         FieldCommentConverter var4 = null;
         if (oConvertUtils.isNotEmpty(var3.getConverter())) {
            var4 = a(var3.getConverter().trim());
         } else {
            var4 = a(var3);
         }

         if (var4 != null) {
            var1.put(var3.getDbFieldName().toLowerCase(), var4);
         }
      }

      return var1;
   }

   private static FieldCommentConverter a(String var0) {
      Object var1 = null;
      if (var0.indexOf(".") > 0) {
         try {
            var1 = MyClassLoader.getClassByScn(var0).newInstance();
         } catch (InstantiationException var3) {
            a.error(var3.getMessage(), var3);
         } catch (IllegalAccessException var4) {
            a.error(var4.getMessage(), var4);
         }
      } else {
         var1 = SpringContextUtils.getBean(var0);
      }

      if (var1 != null && var1 instanceof FieldCommentConverter) {
         FieldCommentConverter var2 = (FieldCommentConverter)var1;
         return var2;
      } else {
         return null;
      }
   }
}
