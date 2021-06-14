package org.jeecg.modules.online.cgform.converter.convertb;

import java.util.HashMap;
import java.util.Map;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class a extends org.jeecg.modules.online.cgform.converter.converta.a {
   private String f;

   public String getTreeText() {
      return this.f;
   }

   public void setTreeText(String treeText) {
      this.f = treeText;
   }

   public a(OnlCgformField var1) {
      super("SYS_CATEGORY", "ID", "NAME");
      this.f = var1.getDictText();
      this.b = var1.getDbFieldName();
   }

   public Map<String, String> getConfig() {
      if (oConvertUtils.isEmpty(this.f)) {
         return null;
      } else {
         HashMap var1 = new HashMap();
         var1.put("treeText", this.f);
         var1.put("field", this.b);
         return var1;
      }
   }
}
