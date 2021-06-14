package org.jeecg.modules.online.cgform.converter.converta;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.FieldCommentConverter;

public class b implements FieldCommentConverter {
   protected String a;
   protected List<DictModel> b;

   public b() {
   }

   public String getFiled() {
      return this.a;
   }

   public void setFiled(String filed) {
      this.a = filed;
   }

   public List<DictModel> getDictList() {
      return this.b;
   }

   public void setDictList(List<DictModel> dictList) {
      this.b = dictList;
   }

   public String converterToVal(String txt) {
      if (oConvertUtils.isNotEmpty(txt)) {
         Iterator var2 = this.b.iterator();

         while(var2.hasNext()) {
            DictModel var3 = (DictModel)var2.next();
            if (var3.getText().equals(txt)) {
               return var3.getValue();
            }
         }
      }

      return null;
   }

   public String converterToTxt(String val) {
      if (oConvertUtils.isNotEmpty(val)) {
         Iterator var2 = this.b.iterator();

         while(var2.hasNext()) {
            DictModel var3 = (DictModel)var2.next();
            if (var3.getValue().equals(val)) {
               return var3.getText();
            }
         }
      }

      return null;
   }

   public Map<String, String> getConfig() {
      return null;
   }
}
