package org.jeecg.modules.online.cgreport.service.a;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportAPIService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class b implements IOnlCgreportAPIService {
   private static final Logger a = LoggerFactory.getLogger(b.class);
   @Autowired
   private c onlCgreportHeadService;
   @Autowired
   private IOnlCgreportItemService onlCgreportItemService;
   @Autowired
   private ISysBaseAPI sysBaseAPI;
   @Autowired
   private IOnlCgreportParamService onlCgreportParamService;

   public b() {
   }

   public Map<String, Object> getDataById(String id, Map<String, Object> params) {
      return this.getData(id, (String)null, params);
   }

   public Map<String, Object> getDataByCode(String code, Map<String, Object> params) {
      return this.getData((String)null, code, params);
   }

   public Map<String, Object> getData(String id, String code, Map<String, Object> params) {
      OnlCgreportHead var4 = null;
      if (oConvertUtils.isNotEmpty(id)) {
         var4 = (OnlCgreportHead)this.onlCgreportHeadService.getById(id);
      } else if (oConvertUtils.isNotEmpty(code)) {
         LambdaQueryWrapper<OnlCgreportHead> var5 = new LambdaQueryWrapper<OnlCgreportHead>();
         var5.eq(OnlCgreportHead::getCode, code);
         var4 = (OnlCgreportHead)this.onlCgreportHeadService.getOne(var5);
      }

      if (var4 == null) {
         throw new JeecgBootException("实体不存在");
      } else {
         try {
            String var8 = var4.getCgrSql().trim();
            String var6 = var4.getDbSource();
            return this.executeSelectSqlRoute(var6, var8, params, var4.getId());
         } catch (Exception var7) {
            a.error(var7.getMessage(), var7);
            throw new JeecgBootException("SQL执行失败：" + var7.getMessage());
         }
      }
   }

   public Map<String, Object> executeSelectSqlRoute(String dbKey, String sql, Map<String, Object> params, String headId) throws Exception {
      if (StringUtils.isNotBlank(dbKey)) {
         a.debug("Online报表: 走了多数据源逻辑");
         return this.onlCgreportHeadService.executeSelectSqlDynamic(dbKey, sql, params, headId);
      } else {
         a.debug("Online报表: 走了稳定逻辑");
         return this.onlCgreportHeadService.executeSelectSql(sql, headId, params);
      }
   }
}
