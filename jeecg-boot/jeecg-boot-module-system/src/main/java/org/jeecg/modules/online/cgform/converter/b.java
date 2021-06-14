package org.jeecg.modules.online.cgform.converter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class b {
   public static final int a = 2;
   public static final int b = 1;

   public b() {
   }

   public static void a(int var0, List<Map<String, Object>> var1, List<OnlCgformField> var2) {
      Map var3 = org.jeecg.modules.online.cgform.converter.a.a(var2);
      Iterator var4 = var1.iterator();

      while(var4.hasNext()) {
         Map var5 = (Map)var4.next();
         Iterator var6 = var5.entrySet().iterator();
         HashMap var7 = new HashMap();

         while(var6.hasNext()) {
            Entry var8 = (Entry)var6.next();
            Object var9 = var8.getValue();
            if (var9 != null) {
               String var10 = (String)var8.getKey();
               FieldCommentConverter var11 = (FieldCommentConverter)var3.get(var10.toLowerCase());
               if (var11 != null) {
                  String var12 = var9.toString();
                  String var13 = var0 == 1 ? var11.converterToTxt(var12) : var11.converterToVal(var12);
                  a(var11, var5, var0);
                  a(var11, var7, var12);
                  var5.put(var10, var13);
               }
            }
         }

         Iterator var14 = var7.keySet().iterator();

         while(var14.hasNext()) {
            String var15 = (String)var14.next();
            var5.put(var15, var7.get(var15));
         }
      }

   }

   private static void a(FieldCommentConverter var0, Map<String, Object> var1, int var2) {
      Map var3 = var0.getConfig();
      if (var3 != null) {
         String var4 = (String)var3.get("linkField");
         if (oConvertUtils.isNotEmpty(var4)) {
            String[] var5 = var4.split(",");
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               String var8 = var5[var7];
               Object var9 = var1.get(var8);
               if (var9 != null) {
                  String var10 = var9.toString();
                  String var11 = var2 == 1 ? var0.converterToTxt(var10) : var0.converterToVal(var10);
                  var1.put(var8, var11);
               }
            }
         }
      }

   }

   private static void a(FieldCommentConverter var0, Map<String, Object> var1, String var2) {
      Map var3 = var0.getConfig();
      if (var3 != null) {
         String var4 = (String)var3.get("treeText");
         if (oConvertUtils.isNotEmpty(var4)) {
            var1.put(var4, var2);
         }
      }

   }
}
