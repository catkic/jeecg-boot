package org.jeecg.modules.online.cgform.converter.convertb;

import com.alibaba.fastjson.JSONArray;
import java.util.ArrayList;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class h extends org.jeecg.modules.online.cgform.converter.converta.b {
   public h(OnlCgformField var1) {
      String var2 = var1.getFieldExtendJson();
      String var3 = "Y";
      String var4 = "N";
      if (var2 != null && !"".equals(var2)) {
         JSONArray var5 = JSONArray.parseArray(var2);
         if (var5 != null && var5.size() == 2) {
            var3 = var5.get(0).toString();
            var4 = var5.get(1).toString();
         }
      }

      ArrayList var8 = new ArrayList();
      DictModel var6 = new DictModel(var3, "是");
      DictModel var7 = new DictModel(var4, "否");
      var8.add(var6);
      var8.add(var7);
      this.b = var8;
      this.a = var1.getDbFieldName();
   }
}
