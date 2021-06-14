package org.jeecg.modules.online.cgform.converter.convertb;

import java.util.ArrayList;
import java.util.List;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class c extends org.jeecg.modules.online.cgform.converter.converta.b {
   public c(OnlCgformField var1) {
      ISysBaseAPI var2 = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
      String var3 = var1.getDictTable();
      String var4 = var1.getDictText();
      String var5 = var1.getDictField();
      Object var6 = new ArrayList();
      if (oConvertUtils.isNotEmpty(var3)) {
         var6 = var2.queryTableDictItemsByCode(var3, var4, var5);
      } else if (oConvertUtils.isNotEmpty(var5)) {
         var6 = var2.queryDictItemsByCode(var5);
      }

      this.b = (List)var6;
      this.a = var1.getDbFieldName();
   }
}
