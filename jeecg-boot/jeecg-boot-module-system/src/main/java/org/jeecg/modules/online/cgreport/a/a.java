package org.jeecg.modules.online.cgreport.a;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.system.vo.DynamicDataSourceModel;
import org.jeecg.common.util.BrowserUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportItem;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;
import org.jeecg.modules.online.cgreport.mapper.OnlCgreportHeadMapper;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportItemService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportParamService;
import org.jeecg.modules.online.cgreport.util.SqlUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelExportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("onlCgreportAPI")
@RequestMapping({"/online/cgreport/api"})
public class a {
   private static final Logger a = LoggerFactory.getLogger(a.class);
   @Autowired
   private org.jeecg.modules.online.cgreport.service.a.b cgreportAPIService;
   @Autowired
   private org.jeecg.modules.online.cgreport.service.a.c onlCgreportHeadService;
   @Autowired
   private IOnlCgreportItemService onlCgreportItemService;
   @Autowired
   private ISysBaseAPI sysBaseAPI;
   @Autowired
   private IOnlCgreportParamService onlCgreportParamService;

   public a() {
   }

   @GetMapping({"/getColumnsAndData/{code}"})
   @PermissionData
   public Result<?> a(@PathVariable("code") String var1, HttpServletRequest var2) {
      OnlCgreportHead var3 = (OnlCgreportHead)this.onlCgreportHeadService.getById(var1);
      if (var3 == null) {
         return Result.error("实体不存在");
      } else {
         Result var4 = this.b(var1, var2);
         if (!var4.getCode().equals(200)) {
            return var4;
         } else {
            JSONObject var5 = JSON.parseObject(JSONObject.toJSONString(var4.getResult()));
            JSONArray var6 = var5.getJSONArray("records");
            Map var7 = this.onlCgreportHeadService.queryColumnInfo(var1, false);
            JSONArray var8 = (JSONArray)var7.get("columns");
            HashMap var9 = new HashMap();
            if (var8 != null) {
               for(int var10 = 0; var10 < var8.size(); ++var10) {
                  JSONObject var11 = var8.getJSONObject(var10);
                  Object var12 = var11.get("dictCode");
                  if (var12 != null) {
                     String var13 = var12.toString();
                     String var14 = var8.getJSONObject(var10).getString("dataIndex");
                     List var15 = this.onlCgreportHeadService.queryColumnDict(var13, var6, var14);
                     if (var15 != null) {
                        var9.put(var14, var15);
                        var11.put("customRender", var14);
                     }
                  }
               }
            }

            var7.put("cgreportHeadName", var3.getName());
            var7.put("data", var4.getResult());
            var7.put("dictOptions", var9);
            return Result.ok(var7);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   @GetMapping({"/getColumns/{code}"})
   public Result<?> a(@PathVariable("code") String var1) {
      OnlCgreportHead var2 = (OnlCgreportHead)this.onlCgreportHeadService.getById(var1);
      if (var2 == null) {
         return Result.error("实体不存在");
      } else {
         QueryWrapper var3 = new QueryWrapper();
         var3.eq("cgrhead_id", var1);
         var3.eq("is_show", 1);
         var3.orderByAsc("order_num");
         List var4 = this.onlCgreportItemService.list(var3);
         ArrayList var5 = new ArrayList();
         HashMap var6 = new HashMap();
         Iterator var7 = var4.iterator();

         while(var7.hasNext()) {
            OnlCgreportItem var8 = (OnlCgreportItem)var7.next();
            HashMap var9 = new HashMap(3);
            var9.put("title", var8.getFieldTxt());
            var9.put("dataIndex", var8.getFieldName());
            var9.put("align", "center");
            var9.put("sorter", "true");
            var5.add(var9);
            String var10 = var8.getDictCode();
            if (oConvertUtils.isNotEmpty(var10)) {
               List var11 = null;
               if (var10.toLowerCase().indexOf("select ") == 0) {
                  List var12 = ((OnlCgreportHeadMapper)this.onlCgreportHeadService.getBaseMapper()).executeSelete(var10);
                  if (var12 != null && var12.size() != 0) {
                     String var13 = JSON.toJSONString(var12);
                     var11 = JSON.parseArray(var13, DictModel.class);
                  }
               } else {
                  var11 = this.sysBaseAPI.queryDictItemsByCode(var10);
               }

               if (var11 != null) {
                  var6.put(var8.getFieldName(), var11);
                  var9.put("customRender", var8.getFieldName());
               }
            }
         }

         HashMap var14 = new HashMap(1);
         var14.put("columns", var5);
         var14.put("dictOptions", var6);
         var14.put("cgreportHeadName", var2.getName());
         return Result.ok(var14);
      }
   }

   @GetMapping({"/getData/{code}"})
   @PermissionData
   public Result<?> b(@PathVariable("code") String var1, HttpServletRequest var2) {
      Map var3 = SqlUtil.a(var2);
      var3.put("getAll", var2.getAttribute("getAll"));

      try {
         return Result.OK(this.cgreportAPIService.getDataById(var1, var3));
      } catch (JeecgBootException var5) {
         return Result.error(var5.getMessage());
      }
   }

   @GetMapping({"/getDataOrderByValue/{code}"})
   @PermissionData
   public Result<?> c(@PathVariable("code") String var1, HttpServletRequest var2) {
      OnlCgreportHead var3 = (OnlCgreportHead)this.onlCgreportHeadService.getById(var1);
      if (var3 == null) {
         return Result.error("实体不存在");
      } else {
         String var4 = var3.getCgrSql().trim();
         String var5 = var3.getDbSource();

         try {
            Map var6 = SqlUtil.a(var2);
            Object var7 = var6.get("order_field");
            Object var8 = var6.get("order_value");
            if (!oConvertUtils.isEmpty(var7) && !oConvertUtils.isEmpty(var8)) {
               String var9 = "force_" + var7;
               var6.put(var9, var8);
               var6.put("getAll", true);
               Map var10 = this.cgreportAPIService.executeSelectSqlRoute(var5, var4, var6, var3.getId());
               JSONArray var11 = JSON.parseArray(JSON.toJSONString(var10.get("records")));
               var6.remove(var7.toString());
               var6.remove(var9);
               var6.remove("order_field");
               var6.remove("order_value");
               var6.put("getAll", var2.getAttribute("getAll"));
               Map var12 = this.cgreportAPIService.executeSelectSqlRoute(var5, var4, var6, var3.getId());
               JSONArray var13 = JSON.parseArray(JSON.toJSONString(var12.get("records")));
               this.a(var11, var13);
               var12.put("records", var13);
               return Result.ok(var12);
            } else {
               return Result.error("order_field 和 order_value 参数不能为空！");
            }
         } catch (Exception var14) {
            a.error(var14.getMessage(), var14);
            return Result.error("SQL执行失败：" + var14.getMessage());
         }
      }
   }

   private void a(JSONArray var1, JSONArray var2) {
      for(int var3 = 0; var3 < var1.size(); ++var3) {
         JSONObject var4 = var1.getJSONObject(var3);
         String var5 = var4.getString("id");
         int var6 = (int)var2.stream().filter((var1x) -> {
            return var5.equals(((JSONObject)var1x).getString("id"));
         }).count();
         if (var6 == 0) {
            var2.add(0, var4);
         }
      }

   }

   @GetMapping({"/getQueryInfo/{code}"})
   public Result<?> b(@PathVariable("code") String var1) {
      try {
         List var2 = this.onlCgreportItemService.getAutoListQueryInfo(var1);
         return Result.ok(var2);
      } catch (Exception var3) {
         a.info(var3.getMessage(), var3);
         return Result.error("查询失败");
      }
   }

   @GetMapping({"/getParamsInfo/{code}"})
   public Result<?> c(@PathVariable("code") String var1) {
      try {
         LambdaQueryWrapper var2 = new LambdaQueryWrapper();
         var2.eq(OnlCgreportParam::getCgrheadId, var1);
         List var3 = this.onlCgreportParamService.list(var2);
         return Result.ok(var3);
      } catch (Exception var4) {
         a.info(var4.getMessage(), var4);
         return Result.error("查询失败");
      }
   }

   @PermissionData
   @RequestMapping({"/exportXls/{reportId}"})
   public void a(@PathVariable("reportId") String var1, HttpServletRequest var2, HttpServletResponse var3) {
      String var4 = "报表";
      String var5 = "导出信息";
      if (!oConvertUtils.isNotEmpty(var1)) {
         throw new JeecgBootException("参数错误");
      } else {
         Map var6 = null;

         try {
            var6 = this.onlCgreportHeadService.queryCgReportConfig(var1);
         } catch (Exception var35) {
            throw new JeecgBootException("动态报表配置不存在!");
         }

         List var7 = (List)var6.get("items");
         var2.setAttribute("getAll", true);
         Result var8 = this.b(var1, var2);
         List var9 = null;
         if (var8.getCode().equals(200)) {
            Map var10 = (Map)var8.getResult();
            var9 = (List)var10.get("records");
         }

         ArrayList var36 = new ArrayList();
         HashMap var11 = new HashMap();
         HashMap var12 = new HashMap();
         ArrayList var13 = new ArrayList();

         String var15;
         for(int var14 = 0; var14 < var7.size(); ++var14) {
            if ("1".equals(oConvertUtils.getString(((Map)var7.get(var14)).get("is_show")))) {
               var15 = ((Map)var7.get(var14)).get("field_name").toString();
               ExcelExportEntity var16 = new ExcelExportEntity(((Map)var7.get(var14)).get("field_txt").toString(), var15, 15);
               Object var17 = ((Map)var7.get(var14)).get("dict_code");
               JSONArray var18 = JSONObject.parseArray(JSONObject.toJSONString(var9));
               List var19 = this.onlCgreportHeadService.queryColumnDict(oConvertUtils.getString(var17), var18, var15);
               if (var19 != null && var19.size() > 0) {
                  ArrayList var20 = new ArrayList();
                  Iterator var21 = var19.iterator();

                  while(var21.hasNext()) {
                     DictModel var22 = (DictModel)var21.next();
                     var20.add(var22.getText() + "_" + var22.getValue());
                  }

                  var16.setReplace((String[])var20.toArray(new String[var20.size()]));
               }

               Object var50 = ((Map)var7.get(var14)).get("replace_val");
               if (oConvertUtils.isNotEmpty(var50)) {
                  var16.setReplace(var50.toString().split(","));
               }

               if (oConvertUtils.isNotEmpty(((Map)var7.get(var14)).get("group_title"))) {
                  String var52 = ((Map)var7.get(var14)).get("group_title").toString();
                  Object var53 = new ArrayList();
                  if (var12.containsKey(var52)) {
                     var53 = (List)var12.get(var52);
                     ((List)var53).add(var15);
                  } else {
                     ExcelExportEntity var23 = new ExcelExportEntity(var52, var52, true);
                     var13.add(var23);
                     ((List)var53).add(var15);
                  }

                  var12.put(var52, var53);
                  var16.setColspan(true);
               }

               var13.add(var16);
            }

            if ("1".equals(oConvertUtils.getString(((Map)var7.get(var14)).get("is_total")))) {
               var36.add(((Map)var7.get(var14)).get("field_name").toString());
            }
         }

         Iterator var37 = var12.entrySet().iterator();

         String var42;
         while(var37.hasNext()) {
            Entry var39 = (Entry)var37.next();
            var42 = (String)var39.getKey();
            List var43 = (List)var39.getValue();
            Iterator var46 = var13.iterator();

            while(var46.hasNext()) {
               ExcelExportEntity var48 = (ExcelExportEntity)var46.next();
               if (var42.equals(var48.getName()) && var48.isColspan()) {
                  var48.setSubColumnList(var43);
               }
            }
         }

         if (var36 != null && var36.size() > 0) {
            BigDecimal var38 = new BigDecimal(0.0D);
            Iterator var40 = var36.iterator();

            while(var40.hasNext()) {
               var42 = (String)var40.next();
               Iterator var45 = var9.iterator();

               while(var45.hasNext()) {
                  Map var47 = (Map)var45.next();
                  String var49 = var47.get(var42).toString();
                  if (var49.matches("\\d+(.\\d+)?")) {
                     BigDecimal var51 = new BigDecimal(var49);
                     var38 = var38.add(var51);
                  }
               }

               var11.put(var42, var38);
            }

            var9.add(var11);
         }

         var3.setContentType("application/vnd.ms-excel");
         ServletOutputStream var41 = null;

         try {
            var15 = BrowserUtils.checkBrowse(var2);
            if ("MSIE".equalsIgnoreCase(var15.substring(0, 4))) {
               var3.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(var4, "UTF-8") + ".xls");
            } else {
               var42 = new String(var4.getBytes("UTF-8"), "ISO8859-1");
               var3.setHeader("content-disposition", "attachment;filename=" + var42 + ".xls");
            }

            Workbook var44 = ExcelExportUtil.exportExcel(new ExportParams((String)null, var5), var13, var9);
            var41 = var3.getOutputStream();
            var44.write(var41);
         } catch (Exception var33) {
         } finally {
            try {
               var41.flush();
               var41.close();
            } catch (Exception var32) {
            }

         }

      }
   }

   @GetMapping({"/getRpColumns/{code}"})
   public Result<?> d(@PathVariable("code") String var1) {
      LambdaQueryWrapper var2 = new LambdaQueryWrapper();
      var2.eq(OnlCgreportHead::getCode, var1);
      OnlCgreportHead var3 = (OnlCgreportHead)this.onlCgreportHeadService.getOne(var2);
      if (var3 == null) {
         return Result.error("实体不存在");
      } else {
         Map var4 = this.onlCgreportHeadService.queryColumnInfo(var3.getId(), true);
         var4.put("cgRpConfigId", var3.getId());
         var4.put("cgRpConfigName", var3.getName());
         return Result.ok(var4);
      }
   }

   @PostMapping({"/testConnection"})
   public Result a(@RequestBody DynamicDataSourceModel var1) {
      Connection var2 = null;

      Result var3;
      try {
         Result var4;
         try {
            Class.forName(var1.getDbDriver());
            var2 = DriverManager.getConnection(var1.getDbUrl(), var1.getDbUsername(), var1.getDbPassword());
            if (var2 == null) {
               var3 = Result.ok("数据库连接失败：错误未知");
               return var3;
            }

            var3 = Result.ok("数据库连接成功");
         } catch (ClassNotFoundException var17) {
            a.error(var17.toString());
            var4 = Result.error("数据库连接失败：驱动类不存在");
            return var4;
         } catch (Exception var18) {
            a.error(var18.toString());
            var4 = Result.error("数据库连接失败：" + var18.getMessage());
            return var4;
         }
      } finally {
         try {
            if (var2 != null && !var2.isClosed()) {
               var2.close();
            }
         } catch (SQLException var16) {
            a.error(var16.toString());
         }

      }

      return var3;
   }

   @GetMapping({"/getReportDictList"})
   public Result<?> a(@RequestParam("sql") String var1, @RequestParam(name = "keyword",required = false) String var2) {
      List var3 = this.onlCgreportHeadService.queryDictSelectData(var1, var2);
      return Result.ok(var3);
   }
}
