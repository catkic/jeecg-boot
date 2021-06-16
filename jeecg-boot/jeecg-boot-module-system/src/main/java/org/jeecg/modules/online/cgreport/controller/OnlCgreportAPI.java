//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.jeecg.modules.online.cgreport.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
import org.jeecg.modules.online.cgreport.service.IOnlCgreportAPIService;
import org.jeecg.modules.online.cgreport.service.IOnlCgreportHeadService;
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
public class OnlCgreportAPI {
    private static final Logger a = LoggerFactory.getLogger(OnlCgreportAPI.class);
    @Autowired
    private IOnlCgreportAPIService cgreportAPIService;
    @Autowired
    private IOnlCgreportHeadService onlCgreportHeadService;
    @Autowired
    private IOnlCgreportItemService onlCgreportItemService;
    @Autowired
    private ISysBaseAPI sysBaseAPI;
    @Autowired
    private IOnlCgreportParamService onlCgreportParamService;

    public OnlCgreportAPI() {
    }

    @GetMapping({"/getColumnsAndData/{code}"})
    @PermissionData
    public Result<?> a(@PathVariable("code") String var1, HttpServletRequest var2) {
        OnlCgreportHead var3 = this.onlCgreportHeadService.getById(var1);
        if (var3 == null) {
            return Result.error("实体不存在");
        } else {
            Result var4 = this.getDataByCode(var1, var2);
            if (!var4.getCode().equals(200)) {
                return var4;
            } else {
                JSONObject var5 = JSON.parseObject(JSONObject.toJSONString(var4.getResult()));
                JSONArray var6 = var5.getJSONArray("records");
                Map var7 = this.onlCgreportHeadService.queryColumnInfo(var1, false);
                JSONArray var8 = (JSONArray) var7.get("columns");
                HashMap var9 = new HashMap();
                if (var8 != null) {
                    for (int var10 = 0; var10 < var8.size(); ++var10) {
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

    /**
     * @deprecated
     */
    @Deprecated
    @GetMapping({"/getColumns/{code}"})
    public Result<?> a(@PathVariable("code") String var1) {
        OnlCgreportHead var2 = this.onlCgreportHeadService.getById(var1);
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

            while (var7.hasNext()) {
                OnlCgreportItem var8 = (OnlCgreportItem) var7.next();
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
                        List var12 = ((OnlCgreportHeadMapper) this.onlCgreportHeadService.getBaseMapper()).executeSelete(var10);
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
    public Result<?> getDataByCode(@PathVariable("code") String code, HttpServletRequest request) {
        Map<String, Object> var3 = SqlUtil.formatParasMap(request);
        var3.put("getAll", request.getAttribute("getAll"));

        try {
            return Result.OK(this.cgreportAPIService.getDataById(code, var3));
        } catch (JeecgBootException var5) {
            return Result.error(var5.getMessage());
        }
    }

    @GetMapping({"/getDataOrderByValue/{code}"})
    @PermissionData
    public Result<?> c(@PathVariable("code") String var1, HttpServletRequest var2) {
        OnlCgreportHead var3 = this.onlCgreportHeadService.getById(var1);
        if (var3 == null) {
            return Result.error("实体不存在");
        } else {
            String var4 = var3.getCgrSql().trim();
            String var5 = var3.getDbSource();

            try {
                Map var6 = SqlUtil.formatParasMap(var2);
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
        for (int var3 = 0; var3 < var1.size(); ++var3) {
            JSONObject var4 = var1.getJSONObject(var3);
            String var5 = var4.getString("id");
            int var6 = (int) var2.stream().filter((var1x) -> {
                return var5.equals(((JSONObject) var1x).getString("id"));
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
            LambdaQueryWrapper<OnlCgreportParam> var2 = new LambdaQueryWrapper();
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
    public void exportXls(@PathVariable("reportId") String reportId, HttpServletRequest request, HttpServletResponse response) {
        // 韩金说过上传下载文件只能在controller里面实现？为什么
        String fileName = "报表";
        String workBookTitle = "导出信息";
        if (!oConvertUtils.isNotEmpty(reportId)) {
            throw new JeecgBootException("参数错误");
        } else {
            Map<String, Object> cgReportConfig;
            try {
                cgReportConfig = this.onlCgreportHeadService.queryCgReportConfig(reportId);
            } catch (Exception e) {
                throw new JeecgBootException("动态报表配置不存在!");
            }

            List<Map<String, Object>> items = (List<Map<String, Object>>) cgReportConfig.get("items");
            request.setAttribute("getAll", true);
            Result data = this.getDataByCode(reportId, request);
            List<Map<String, Object>> records;
            if (data.getCode().equals(200)) {
                Map<String, Object> result = (Map<String, Object>) data.getResult();
                records = (List) result.get("records");
            } else {
                records = null;
            }

            List<String> totalCol = new ArrayList<>();
            // 独立的一行，用来计total
            Map<String, Object> totalRow = new HashMap<>();
            // 我猜是合并表头
            Map<String, List<String>> group = new HashMap<>();
            List<ExcelExportEntity> exportList = new ArrayList<>();
            JSONArray jsons = JSONObject.parseArray(JSONObject.toJSONString(records));

            for (int i = 0; i < items.size(); ++i) {
                if ("1".equals(oConvertUtils.getString(items.get(i).get("is_show")))) {
                    String fieldName = items.get(i).get("field_name").toString();
                    Object dictCode = items.get(i).get("dict_code");
                    String fieldTxt = items.get(i).get("field_txt").toString();
                    Object replaceVal = items.get(i).get("replace_val");

                    ExcelExportEntity export = new ExcelExportEntity(fieldTxt, fieldName, 15);

                    List<DictModel> dictModels = this.onlCgreportHeadService.queryColumnDict(oConvertUtils.getString(dictCode), jsons, fieldName);
                    if (dictModels != null && dictModels.size() > 0) {
                        List<String> dicts = new ArrayList<>();
                        for (DictModel dictModel : dictModels) {
                            dicts.add(dictModel.getText() + "_" + dictModel.getValue());

                        }
                        export.setReplace(dicts.toArray(new String[dicts.size()]));
                    }

                    if (oConvertUtils.isNotEmpty(replaceVal)) {
                        export.setReplace(replaceVal.toString().split(","));
                    }

                    if (oConvertUtils.isNotEmpty(items.get(i).get("group_title"))) {
                        String groupTitle = items.get(i).get("group_title").toString();
                        List<String> subTitle = new ArrayList<>();
                        // 这一步魔改了

                        if (group.containsKey(groupTitle)) {
                            group.get(groupTitle).add(fieldName);
                        } else {
                            ExcelExportEntity groupExport = new ExcelExportEntity(groupTitle, groupTitle, true);
                            exportList.add(groupExport);
                            subTitle.add(fieldName);
                            group.put(groupTitle, subTitle);

                        }

                        export.setColspan(true);
                    }

                    exportList.add(export);
                }

                if ("1".equals(oConvertUtils.getString(items.get(i).get("is_total")))) {
                    totalCol.add(items.get(i).get("field_name").toString());
                }
            }

            for (Entry<String, List<String>> entry : group.entrySet()) {
                for (ExcelExportEntity eee : exportList) {
                    if (entry.getKey().equals(eee.getName()) && eee.isColspan()) {
                        eee.setSubColumnList(entry.getValue());
                    }
                }
            }

            // 合计行
            if (totalCol.size() > 0) {
                BigDecimal total = new BigDecimal(0);
                for (String key : totalCol) {
                    if (records != null) {
                        for (Map<String, Object> record : records) {
                            String num = record.get(key).toString();
                            // 用正则转成数字计数
                            // 正则不匹配的就不会在里面
                            if (num.matches("\\d+(.\\d+)?")) {
                                total = total.add(new BigDecimal(num));
                            }
                        }
                    }
                    totalRow.put(key, total);
                }
                records.add(totalRow);
            }

            response.setContentType("application/vnd.ms-excel");
            ServletOutputStream os = null;

            try {
                String browse = BrowserUtils.checkBrowse(request);
                if ("MSIE".equalsIgnoreCase(browse.substring(0, 4))) {
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
                } else {
                    String filenameUtf8 = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
                    response.setHeader("Content-Disposition", "attachment; filename=" + filenameUtf8 + ".xls");
                }
                // 下面的子标题
                // chrome里面ajax 设置标题不生效
                Workbook wb = ExcelExportUtil.exportExcel(new ExportParams(fileName, workBookTitle), exportList, records);
                os = response.getOutputStream();
                wb.write(os);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null) {
                        os.flush();
                        os.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

    @GetMapping({"/getRpColumns/{code}"})
    public Result<?> d(@PathVariable("code") String var1) {
        LambdaQueryWrapper<OnlCgreportHead> var2 = new LambdaQueryWrapper<>();
        var2.eq(OnlCgreportHead::getCode, var1);
        OnlCgreportHead var3 = this.onlCgreportHeadService.getOne(var2);
        if (var3 == null) {
            return Result.error("实体不存在");
        } else {
            Map var4 = this.onlCgreportHeadService.queryColumnInfo(var3.getId(), true);
            var4.put("cgRpConfigId", var3.getId());
            var4.put("cgRpConfigName", var3.getName());
            return Result.OK(var4);
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
                if (var2 != null) {
                    var3 = Result.ok("数据库连接成功");
                    return var3;
                }

                var3 = Result.ok("数据库连接失败：错误未知");
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
    public Result<?> a(@RequestParam("sql") String var1, @RequestParam(name = "keyword", required = false) String var2) {
        List var3 = this.onlCgreportHeadService.queryDictSelectData(var1, var2);
        return Result.ok(var3);
    }
}
