package org.jeecg.modules.online.cgreport.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.model.OnlCgreportModel;

public interface IOnlCgreportHeadService extends IService<OnlCgreportHead> {
   Result<?> editAll(OnlCgreportModel var1);

   Result<?> delete(String var1);

   Result<?> bathDelete(String[] var1);

   Map<String, Object> executeSelectSql(String var1, String var2, Map<String, Object> var3) throws SQLException;

   Map<String, Object> executeSelectSqlDynamic(String var1, String var2, Map<String, Object> var3, String var4);

   List<String> getSqlFields(String var1, String var2) throws SQLException;

   List<String> getSqlParams(String var1);

   Map<String, Object> queryCgReportConfig(String var1);

   List<Map<?, ?>> queryByCgReportSql(String var1, Map var2, Map var3, int var4, int var5);

   List<DictModel> queryDictSelectData(String var1, String var2);

   Map<String, Object> queryColumnInfo(String var1, boolean var2);

   List<DictModel> queryColumnDict(String var1, JSONArray var2, String var3);
}
