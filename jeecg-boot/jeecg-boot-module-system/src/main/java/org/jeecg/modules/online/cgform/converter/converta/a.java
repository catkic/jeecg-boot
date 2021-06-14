package org.jeecg.modules.online.cgform.converter.converta;

import java.util.List;
import java.util.Map;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.FieldCommentConverter;

public class a implements FieldCommentConverter {
   protected ISysBaseAPI a;
   protected String b;
   protected String c;
   protected String d;
   protected String e;

   public a() {
      this.a = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
   }

   public a(String var1, String var2, String var3) {
      this();
      this.c = var1;
      this.d = var2;
      this.e = var3;
   }

   public String getField() {
      return this.b;
   }

   public void setField(String field) {
      this.b = field;
   }

   public String getTable() {
      return this.c;
   }

   public void setTable(String table) {
      this.c = table;
   }

   public String getCode() {
      return this.d;
   }

   public void setCode(String code) {
      this.d = code;
   }

   public String getText() {
      return this.e;
   }

   public void setText(String text) {
      this.e = text;
   }

   public String converterToVal(String txt) {
      if (oConvertUtils.isNotEmpty(txt)) {
         String var2 = this.e + "= '" + txt + "'";
         String var3 = null;
         int var4 = this.c.indexOf("where");
         if (var4 > 0) {
            var3 = this.c.substring(0, var4).trim();
            var2 = var2 + " and " + this.c.substring(var4 + 5);
         } else {
            var3 = this.c;
         }

         List var5 = this.a.queryFilterTableDictInfo(var3, this.e, this.d, var2);
         if (var5 != null && var5.size() > 0) {
            return ((DictModel)var5.get(0)).getValue();
         }
      }

      return null;
   }

   public String converterToTxt(String val) {
      if (oConvertUtils.isNotEmpty(val)) {
         String var2 = this.d + "= '" + val + "'";
         String var3 = null;
         int var4 = this.c.indexOf("where");
         if (var4 > 0) {
            var3 = this.c.substring(0, var4).trim();
            var2 = var2 + " and " + this.c.substring(var4 + 5);
         } else {
            var3 = this.c;
         }

         List var5 = this.a.queryFilterTableDictInfo(var3, this.e, this.d, var2);
         if (var5 != null && var5.size() > 0) {
            return ((DictModel)var5.get(0)).getText();
         }
      }

      return null;
   }

   public Map<String, String> getConfig() {
      return null;
   }
}
