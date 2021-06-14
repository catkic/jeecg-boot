package org.jeecg.modules.online.cgreport.service.a;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.DynamicDataSourceModel;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.common.util.dynamic.db.DataSourceCachePool;
import org.jeecg.common.util.dynamic.db.DynamicDBUtil;
import org.jeecg.common.util.dynamic.db.SqlUtils;
import org.jeecg.modules.online.cgform.enums.DataBaseEnum;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportHeadMapper;
import org.jeecg.modules.online.cgreport.model.OnlCgreportModel;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportHeadService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.jeecg.modules.online.cgreport.util.SqlUtil;
import org.jeecg.modules.online.config.exception.DBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("onlCgreportHeadServiceImpl")
public class c extends ServiceImpl<OnlCgreportHeadMapper, OnlCgreportHead> implements IOnlCgreportHeadService {
   private static final Logger a = LoggerFactory.getLogger(c.class);
   @Autowired
   private IOnlCgreportParamService onlCgreportParamService;
   @Autowired
   private IOnlCgreportItemService onlCgreportItemService;
   @Autowired
   private OnlCgreportHeadMapper mapper;
   @Autowired
   private ISysBaseAPI sysBaseAPI;

   public c() {
   }

   public Map<String, Object> executeSelectSql(String sql, String onlCgreportHeadId, Map<String, Object> params) throws SQLException {
      String var4 = null;

      try {
         var4 = org.jeecg.modules.online.config.b.d.getDatabaseType();
      } catch (DBException var20) {
         var20.printStackTrace();
      }

      LambdaQueryWrapper<OnlCgreportParam> var5 = new LambdaQueryWrapper<OnlCgreportParam>();
      var5.eq(OnlCgreportParam::getCgrheadId, onlCgreportHeadId);
      List var6 = this.onlCgreportParamService.list(var5);
      if (var6 != null && var6.size() > 0) {
         Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            OnlCgreportParam var8 = (OnlCgreportParam)var7.next();
            Object var9 = params.get("self_" + var8.getParamName());
            String var10 = "";
            if (var9 != null) {
               var10 = var9.toString();
            } else if (var9 == null && oConvertUtils.isNotEmpty(var8.getParamValue())) {
               var10 = var8.getParamValue();
            }

            String var11 = "${" + var8.getParamName() + "}";
            if (sql.indexOf(var11) > 0) {
               if (var10 != null && var10.startsWith("'") && var10.endsWith("'")) {
                  var10 = var10.substring(1, var10.length() - 1);
               }

               sql = sql.replace(var11, var10);
            } else if (oConvertUtils.isNotEmpty(var10)) {
               params.put("popup_param_pre__" + var8.getParamName(), var10);
            }
         }
      }

      HashMap var21 = new HashMap();
      Integer var22 = oConvertUtils.getInt(params.get("pageSize"), 10);
      Integer var23 = oConvertUtils.getInt(params.get("pageNo"), 1);
      Page var25 = new Page((long)var23, (long)var22);
      LambdaQueryWrapper<OnlCgreportItem> var24 = new LambdaQueryWrapper<OnlCgreportItem>();
      var24.eq(OnlCgreportItem::getCgrheadId, onlCgreportHeadId);
      ArrayList var12 = new ArrayList();
      String[] var13 = (String[])params.keySet().toArray(new String[0]);
      int var14 = var13.length;

      String var16;
      for(int var15 = 0; var15 < var14; ++var15) {
         var16 = var13[var15];
         if (var16.startsWith("force_")) {
            String var17 = var16.substring("force_".length());
            var12.add(var17);
            params.put(var17, params.get(var16));
         }
      }

      List<OnlCgreportItem> list = this.onlCgreportItemService.list(var24);
      if (var12.size() > 0) {
         var24.in(OnlCgreportItem::getFieldName, var12);
         list.forEach((var0) -> {
            var0.setIsSearch(1);
         });
      } else {
         var24.eq(OnlCgreportItem::getIsSearch, 1);
      }

      String var27 = "jeecg_rp_temp.";
      String var28 = org.jeecg.modules.online.cgreport.util.a.a(list, params, (String)var27, (String)null);
      if (ReUtil.contains(" order\\s+by ", sql.toLowerCase()) && "SQLSERVER".equalsIgnoreCase(var4)) {
         throw new JeecgBootException("SqlServer不支持SQL内排序!");
      } else {
         var16 = "select * from (" + sql + ") jeecg_rp_temp  where 1=1 " + var28;
         var16 = SqlUtil.b(var16);
         Object var29 = params.get("column");
         if (var29 != null) {
            var16 = var16 + " order by jeecg_rp_temp." + var29.toString() + " " + params.get("order").toString();
         }

         a.info("报表查询sql=>\r\n" + var16);
         Object var18;
         if (Boolean.valueOf(String.valueOf(params.get("getAll")))) {
            List var19 = this.mapper.executeSelect(var16);
            var18 = new Page();
            ((IPage)var18).setRecords(var19);
            ((IPage)var18).setTotal((long)var19.size());
         } else {
            var18 = this.mapper.selectPageBySql(var25, var16);
         }

         var21.put("total", ((IPage)var18).getTotal());
         var21.put("records", org.jeecg.modules.online.cgform.util.b.d(((IPage)var18).getRecords()));
         return var21;
      }
   }

   public Map<String, Object> executeSelectSqlDynamic(String dbKey, String sql, Map<String, Object> params, String onlCgreportHeadId) {
      DynamicDataSourceModel var5 = DataSourceCachePool.getCacheDynamicDataSourceModel(dbKey);
      String var6 = (String)params.get("order");
      String var7 = (String)params.get("column");
      int var8 = oConvertUtils.getInt(params.get("pageNo"), 1);
      int var9 = oConvertUtils.getInt(params.get("pageSize"), 10);
      a.info("【Online多数据源逻辑】报表查询参数params: " + JSON.toJSONString(params));
      LambdaQueryWrapper<OnlCgreportParam> var10 = new LambdaQueryWrapper<OnlCgreportParam>();
      var10.eq(OnlCgreportParam::getCgrheadId, onlCgreportHeadId);
      List var11 = this.onlCgreportParamService.list(var10);
      OnlCgreportParam var13;
      String var15;
      if (var11 != null && var11.size() > 0) {
         for(Iterator var12 = var11.iterator(); var12.hasNext(); sql = sql.replace("${" + var13.getParamName() + "}", var15)) {
            var13 = (OnlCgreportParam)var12.next();
            Object var14 = params.get("self_" + var13.getParamName());
            var15 = "";
            if (var14 != null) {
               var15 = var14.toString();
            } else if (var14 == null && oConvertUtils.isNotEmpty(var13.getParamValue())) {
               var15 = var13.getParamValue();
            }
         }
      }

      LambdaQueryWrapper<OnlCgreportItem> var24 = new LambdaQueryWrapper<OnlCgreportItem>();
      var24.eq(OnlCgreportItem::getCgrheadId, onlCgreportHeadId);
      var24.eq(OnlCgreportItem::getIsSearch, 1);
      List var25 = this.onlCgreportItemService.list(var24);
      if (ReUtil.contains(" order\\s+by ", sql.toLowerCase()) && "3".equalsIgnoreCase(var5.getDbType())) {
         throw new JeecgBootException("SqlServer不支持SQL内排序!");
      } else {
         String var26 = "jeecg_rp_temp.";
         var15 = DataBaseEnum.getDataBaseNameByValue(var5.getDbType());
         String var16 = org.jeecg.modules.online.cgreport.util.a.a(var25, params, var26, var15);
         String var17 = "select * from (" + sql + ") jeecg_rp_temp  where 1=1 " + var16;
         var17 = SqlUtil.b(var17);
         String var18 = SqlUtils.getCountSql(var17);
         Object var19 = params.get("column");
         if (var19 != null) {
            var17 = var17 + " order by jeecg_rp_temp." + var19.toString() + " " + params.get("order").toString();
         }

         String var20 = var17;
         if (!Boolean.valueOf(String.valueOf(params.get("getAll")))) {
            var20 = SqlUtils.createPageSqlByDBType(var5.getDbType(), var17, var8, var9);
         }

         a.info("多数据源 报表查询sql=>querySql: " + var17);
         a.info("多数据源 报表查询sql=>pageSQL: " + var20);
         a.info("多数据源 报表查询sql=>countSql: " + var18);
         HashMap var21 = new HashMap();
         Map var22 = (Map)DynamicDBUtil.findOne(dbKey, var18, new Object[0]);
         var21.put("total", var22.get("total"));
         List var23 = DynamicDBUtil.findList(dbKey, var20, new Object[0]);
         var21.put("records", org.jeecg.modules.online.cgform.util.b.d(var23));
         return var21;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public Result<?> editAll(OnlCgreportModel values) {
      OnlCgreportHead var2 = values.getHead();
      OnlCgreportHead var3 = (OnlCgreportHead)super.getById(var2.getId());
      if (var3 == null) {
         return Result.error("未找到对应实体");
      } else {
         super.updateById(var2);
         LambdaQueryWrapper<OnlCgreportItem> var4 = new LambdaQueryWrapper<OnlCgreportItem>();
         var4.eq(OnlCgreportItem::getCgrheadId, var2.getId());
         this.onlCgreportItemService.remove(var4);
         LambdaQueryWrapper<OnlCgreportParam> var5 = new LambdaQueryWrapper<OnlCgreportParam>();
         var5.eq(OnlCgreportParam::getCgrheadId, var2.getId());
         this.onlCgreportParamService.remove(var5);
         Iterator var6 = values.getParams().iterator();

         while(var6.hasNext()) {
            OnlCgreportParam var7 = (OnlCgreportParam)var6.next();
            var7.setCgrheadId(var2.getId());
         }

         var6 = values.getItems().iterator();

         while(var6.hasNext()) {
            OnlCgreportItem var8 = (OnlCgreportItem)var6.next();
            var8.setFieldName(var8.getFieldName().trim().toLowerCase());
            var8.setCgrheadId(var2.getId());
         }

         this.onlCgreportItemService.saveBatch(values.getItems());
         this.onlCgreportParamService.saveBatch(values.getParams());
         return Result.ok("全部修改成功");
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public Result<?> delete(String id) {
      boolean var2 = super.removeById(id);
      if (var2) {
         LambdaQueryWrapper<OnlCgreportItem> var3 = new LambdaQueryWrapper<OnlCgreportItem>();
         var3.eq(OnlCgreportItem::getCgrheadId, id);
         this.onlCgreportItemService.remove(var3);
         LambdaQueryWrapper<OnlCgreportParam> var4 = new LambdaQueryWrapper<OnlCgreportParam>();
         var4.eq(OnlCgreportParam::getCgrheadId, id);
         this.onlCgreportParamService.remove(var4);
      }

      return Result.ok("删除成功");
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public Result<?> bathDelete(String[] ids) {
      String[] var2 = ids;
      int var3 = ids.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var2[var4];
         boolean var6 = super.removeById(var5);
         if (var6) {
            LambdaQueryWrapper<OnlCgreportItem> var7 = new LambdaQueryWrapper<OnlCgreportItem>();
            var7.eq(OnlCgreportItem::getCgrheadId, var5);
            this.onlCgreportItemService.remove(var7);
            LambdaQueryWrapper<OnlCgreportParam> var8 = new LambdaQueryWrapper<OnlCgreportParam>();
            var8.eq(OnlCgreportParam::getCgrheadId, var5);
            this.onlCgreportParamService.remove(var8);
         }
      }

      return Result.ok("删除成功");
   }

   public List<String> getSqlFields(String sql, String dbKey) throws SQLException {
      List var3 = null;
      if (StringUtils.isNotBlank(dbKey)) {
         var3 = this.a(sql, dbKey);
      } else {
         var3 = this.a(sql, (String)null);
      }

      return var3;
   }

   public List<String> getSqlParams(String sql) {
      if (oConvertUtils.isEmpty(sql)) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         String var3 = "\\$\\{\\w+\\}";
         Pattern var4 = Pattern.compile(var3);
         Matcher var5 = var4.matcher(sql);

         while(var5.find()) {
            String var6 = var5.group();
            var2.add(var6.substring(var6.indexOf("{") + 1, var6.indexOf("}")));
         }

         return var2;
      }
   }

   private List<String> a(String var1, String var2) throws SQLException {
      if (oConvertUtils.isEmpty(var1)) {
         return null;
      } else {
         var1 = var1.replace("=", " = ");
         var1 = var1.trim();
         if (var1.endsWith(";")) {
            var1 = var1.substring(0, var1.length() - 1);
         }

         var1 = QueryGenerator.convertSystemVariables(var1);
         var1 = SqlUtil.a(var1);
         Set var3;
         if (StringUtils.isNotBlank(var2)) {
            a.info("parse sql : " + var1);
            DynamicDataSourceModel var4 = DataSourceCachePool.getCacheDynamicDataSourceModel(var2);
            if (ReUtil.contains(" order\\s+by ", var1.toLowerCase()) && "3".equalsIgnoreCase(var4.getDbType())) {
               throw new JeecgBootException("SqlServer不支持SQL内排序!");
            }

            if ("1".equals(var4.getDbType())) {
               var1 = "SELECT * FROM (" + var1 + ") temp LIMIT 1";
            } else if ("2".equals(var4.getDbType())) {
               var1 = "SELECT * FROM (" + var1 + ") temp WHERE ROWNUM <= 1";
            } else if ("3".equals(var4.getDbType())) {
               var1 = "SELECT TOP 1 * FROM (" + var1 + ") temp";
            }

            a.info("parse sql with page : " + var1);
            Map var5 = (Map)DynamicDBUtil.findOne(var2, var1, new Object[0]);
            if (var5 == null) {
               throw new JeecgBootException("该报表sql没有数据");
            }

            var3 = var5.keySet();
         } else {
            a.info("parse sql: " + var1);
            String var8 = null;

            try {
               var8 = org.jeecg.modules.online.config.b.d.getDatabaseType();
            } catch (DBException var7) {
               var7.printStackTrace();
            }

            if (ReUtil.contains(" order\\s+by ", var1.toLowerCase()) && "SQLSERVER".equalsIgnoreCase(var8)) {
               throw new JeecgBootException("SqlServer不支持SQL内排序!");
            }

            IPage var9 = this.mapper.selectPageBySql(new Page(1L, 1L), var1);
            List var6 = var9.getRecords();
            if (var6.size() < 1) {
               throw new JeecgBootException("该报表sql没有数据");
            }

            var3 = ((Map)var6.get(0)).keySet();
         }

         if (var3 != null) {
            var3.remove("ROW_ID");
         }

         return new ArrayList(var3);
      }
   }

   public Map<String, Object> queryCgReportConfig(String reportId) {
      HashMap var2 = new HashMap(0);
      Map var3 = this.mapper.queryCgReportMainConfig(reportId);
      List var4 = this.mapper.queryCgReportItems(reportId);
      List var5 = this.mapper.queryCgReportParams(reportId);
      if (org.jeecg.modules.online.config.b.d.a()) {
         var2.put("main", org.jeecg.modules.online.cgform.util.b.b(var3));
         var2.put("items", org.jeecg.modules.online.cgform.util.b.d(var4));
      } else {
         var2.put("main", var3);
         var2.put("items", var4);
      }

      var2.put("params", var5);
      return var2;
   }

   public List<Map<?, ?>> queryByCgReportSql(String sql, Map params, Map paramData, int pageNo, int pageSize) {
      String var6 = SqlUtil.a(sql, params);
      List var7 = null;
      if (paramData != null && paramData.size() == 0) {
         paramData = null;
      }

      if (pageNo == -1 && pageSize == -1) {
         var7 = this.mapper.executeSelete(var6);
      } else {
         Page var8 = new Page((long)pageNo, (long)pageSize);
         IPage var9 = this.mapper.selectPageBySql(var8, var6);
         if (var9.getRecords() != null && var9.getRecords().size() > 0) {
            var7.addAll(var9.getRecords());
         }
      }

      return var7;
   }

   public List<DictModel> queryDictSelectData(String sql, String keyword) {
      Object var3 = new ArrayList();
      Page var4 = new Page();
      var4.setSearchCount(false);
      var4.setCurrent(1L);
      var4.setSize(10L);
      sql = sql.trim();
      int var5 = sql.lastIndexOf(";");
      if (var5 == sql.length() - 1) {
         sql = sql.substring(0, var5);
      }

      if (keyword != null && !"".equals(keyword)) {
         String var6 = " like '%" + keyword + "%'";
         sql = "select * from (" + sql + ") t where t.value " + var6 + " or " + "t.text" + var6;
      }

      IPage var9 = ((OnlCgreportHeadMapper)this.baseMapper).selectPageBySql(var4, sql);
      List var7 = var9.getRecords();
      if (var7 != null && var7.size() != 0) {
         String var8 = JSON.toJSONString(var7);
         var3 = JSON.parseArray(var8, DictModel.class);
      }

      return (List)var3;
   }

   @Cacheable(
      value = {"sys:cache:online:rp"},
      key = "'column-'+#code+'-'+#queryDict"
   )
   public Map<String, Object> queryColumnInfo(String code, boolean queryDict) {
      HashMap var3 = new HashMap();
      QueryWrapper var4 = new QueryWrapper();
      ((QueryWrapper)((QueryWrapper)var4.eq("cgrhead_id", code)).eq("is_show", 1)).orderByAsc("order_num");
      List var5 = this.onlCgreportItemService.list(var4);
      JSONArray var6 = new JSONArray();
      JSONArray var7 = new JSONArray();
      HashMap var8 = new HashMap();
      boolean var9 = false;

      JSONObject var12;
      for(Iterator var10 = var5.iterator(); var10.hasNext(); var7.add(var12)) {
         OnlCgreportItem var11 = (OnlCgreportItem)var10.next();
         var12 = new JSONObject(4);
         var12.put("title", var11.getFieldTxt());
         var12.put("dataIndex", var11.getFieldName());
         var12.put("fieldType", var11.getFieldType());
         var12.put("align", "center");
         var12.put("sorter", "true");
         var12.put("isTotal", var11.getIsTotal());
         var12.put("groupTitle", var11.getGroupTitle());
         if (oConvertUtils.isNotEmpty(var11.getGroupTitle())) {
            var9 = true;
         }

         String var13 = var11.getFieldType();
         if ("Integer".equals(var13) || "Date".equals(var13) || "Long".equals(var13)) {
            var12.put("sorter", "true");
         }

         String var14;
         if (StringUtils.isNotBlank(var11.getFieldHref())) {
            var14 = "fieldHref_" + var11.getFieldName();
            JSONObject var15 = new JSONObject();
            var15.put("customRender", var14);
            var12.put("scopedSlots", var15);
            JSONObject var16 = new JSONObject();
            var16.put("slotName", var14);
            var16.put("href", var11.getFieldHref());
            var6.add(var16);
         }

         var14 = var11.getDictCode();
         if (var14 != null && !"".equals(var14)) {
            if (queryDict) {
               List var17 = this.queryColumnDict(var11.getDictCode(), (JSONArray)null, (String)null);
               var8.put(var11.getFieldName(), var17);
               var12.put("customRender", var11.getFieldName());
            } else {
               var12.put("dictCode", var14);
            }
         }
      }

      if (queryDict) {
         var3.put("dictOptions", var8);
      }

      var3.put("columns", var7);
      var3.put("fieldHrefSlots", var6);
      var3.put("isGroupTitle", var9);
      return var3;
   }

   public List<DictModel> queryColumnDict(String dictCode, JSONArray records, String fieldName) {
      List var4 = null;
      if (oConvertUtils.isNotEmpty(dictCode)) {
         if (dictCode.trim().toLowerCase().indexOf("select ") == 0 && (fieldName == null || records.size() > 0)) {
            dictCode = dictCode.trim();
            int var5 = dictCode.lastIndexOf(";");
            if (var5 == dictCode.length() - 1) {
               dictCode = dictCode.substring(0, var5);
            }

            String var6 = "SELECT * FROM (" + dictCode + ") temp ";
            String var12;
            if (records != null) {
               ArrayList var7 = new ArrayList();

               for(int var8 = 0; var8 < records.size(); ++var8) {
                  JSONObject var9 = records.getJSONObject(var8);
                  String var10 = var9.getString(fieldName);
                  if (StringUtils.isNotBlank(var10)) {
                     var7.add(var10);
                  }
               }

               var12 = "'" + StringUtils.join(var7, "','") + "'";
               var6 = var6 + "WHERE temp.value IN (" + var12 + ")";
            }

            List var11 = ((OnlCgreportHeadMapper)this.getBaseMapper()).executeSelete(var6);
            if (var11 != null && var11.size() != 0) {
               var12 = JSON.toJSONString(var11);
               var4 = JSON.parseArray(var12, DictModel.class);
            }
         } else {
            var4 = this.sysBaseAPI.queryDictItemsByCode(dictCode);
         }
      }

      return var4;
   }
}
