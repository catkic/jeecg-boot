package org.jeecg.modules.online.cgreport.service.a;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportItemMapper;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.util.SqlUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("onlCgreportItemServiceImpl")
public class d extends ServiceImpl<OnlCgreportItemMapper, OnlCgreportItem> implements IOnlCgreportItemService {
   public d() {
   }

   @Cacheable(
      value = {"sys:cache:online:rp"},
      key = "'search-'+#cgrheadId"
   )
   public List<Map<String, String>> getAutoListQueryInfo(String cgrheadId) {
      LambdaQueryWrapper var2 = new LambdaQueryWrapper();
      var2.eq(OnlCgreportItem::getCgrheadId, cgrheadId);
      var2.eq(OnlCgreportItem::getIsSearch, 1);
      List var3 = this.list(var2);
      ArrayList var4 = new ArrayList();
      int var5 = 0;

      HashMap var8;
      for(Iterator var6 = var3.iterator(); var6.hasNext(); var4.add(var8)) {
         OnlCgreportItem var7 = (OnlCgreportItem)var6.next();
         var8 = new HashMap();
         var8.put("label", var7.getFieldTxt());
         String var9 = var7.getDictCode();
         if (oConvertUtils.isNotEmpty(var9)) {
            if (SqlUtil.c(var9)) {
               var8.put("view", "search");
               var8.put("sql", var9);
            } else {
               var8.put("view", "list");
            }
         } else {
            var8.put("view", var7.getFieldType().toLowerCase());
         }

         var8.put("mode", oConvertUtils.isEmpty(var7.getSearchMode()) ? "single" : var7.getSearchMode());
         var8.put("field", var7.getFieldName());
         ++var5;
         if (var5 > 2) {
            var8.put("hidden", "1");
         }
      }

      return var4;
   }
}
