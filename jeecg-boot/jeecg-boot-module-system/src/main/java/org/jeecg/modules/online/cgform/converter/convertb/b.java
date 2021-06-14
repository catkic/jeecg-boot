package org.jeecg.modules.online.cgform.converter.convertb;

import java.util.ArrayList;
import java.util.List;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class b extends org.jeecg.modules.online.cgform.converter.converta.b {
   public b(OnlCgformField var1) {
      ISysBaseAPI var2 = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
      String var3 = "SYS_DEPART";
      String var4 = "DEPART_NAME";
      String var5 = "ID";
      List var6 = var2.queryTableDictItemsByCode(var3, var4, var5);
      this.b = var6;
      this.a = var1.getDbFieldName();
   }

   public String converterToVal(String txt) {
      if (oConvertUtils.isEmpty(txt)) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         String[] var3 = txt.split(",");
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            String var7 = super.converterToVal(var6);
            if (var7 != null) {
               var2.add(var7);
            }
         }

         return String.join(",", var2);
      }
   }

   public String converterToTxt(String val) {
      if (oConvertUtils.isEmpty(val)) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         String[] var3 = val.split(",");
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            String var7 = super.converterToTxt(var6);
            if (var7 != null) {
               var2.add(var7);
            }
         }

         return String.join(",", var2);
      }
   }
}
