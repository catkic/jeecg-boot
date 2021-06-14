package org.jeecg.modules.online.cgform.converter.convertb;

import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class i extends org.jeecg.modules.online.cgform.converter.converta.a {
   public i(OnlCgformField var1) {
      String var2 = var1.getDictText();
      String[] var3 = var2.split(",");
      this.setTable(var1.getDictTable());
      this.setCode(var3[0]);
      this.setText(var3[2]);
   }
}
