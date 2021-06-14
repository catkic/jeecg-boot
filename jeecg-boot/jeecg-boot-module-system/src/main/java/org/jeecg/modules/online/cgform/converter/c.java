package org.jeecg.modules.online.cgform.converter;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component("customDemoConverter")
public class c implements FieldCommentConverter {
   public c() {
   }

   public String converterToVal(String txt) {
      return txt != null && txt.equals("管理员1") ? "admin" : txt;
   }

   public String converterToTxt(String val) {
      if (val != null) {
         if (val.equals("admin")) {
            return "管理员1";
         }

         if (val.equals("scott")) {
            return "管理员2";
         }
      }

      return val;
   }

   public Map<String, String> getConfig() {
      return null;
   }
}
