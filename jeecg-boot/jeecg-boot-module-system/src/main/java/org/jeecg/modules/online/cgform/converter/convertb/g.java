package org.jeecg.modules.online.cgform.converter.convertb;

import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class g extends org.jeecg.modules.online.cgform.converter.converta.b {
   ProvinceCityArea c;

   public g(OnlCgformField var1) {
      this.a = var1.getDbFieldName();
      this.c = (ProvinceCityArea)SpringContextUtils.getBean(ProvinceCityArea.class);
   }

   public String converterToVal(String txt) {
      return oConvertUtils.isEmpty(txt) ? null : this.c.getCode(txt);
   }

   public String converterToTxt(String val) {
      return oConvertUtils.isEmpty(val) ? null : this.c.getText(val);
   }
}
