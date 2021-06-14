package org.jeecg.modules.online.cgform.converter.convertb;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class e extends org.jeecg.modules.online.cgform.converter.converta.a {
   private String f;

   public String getLinkField() {
      return this.f;
   }

   public void setLinkField(String linkField) {
      this.f = linkField;
   }

   public e(OnlCgformField var1) {
      String var2 = var1.getDictTable();
      org.jeecg.modules.online.cgform.a.a var3 = (org.jeecg.modules.online.cgform.a.a)JSONObject.parseObject(var2, org.jeecg.modules.online.cgform.a.a.class);
      this.setTable(var3.getTable());
      this.setCode(var3.getKey());
      this.setText(var3.getTxt());
      this.f = var3.getLinkField();
   }

   public Map<String, String> getConfig() {
      HashMap var1 = new HashMap();
      var1.put("linkField", this.f);
      return var1;
   }
}
