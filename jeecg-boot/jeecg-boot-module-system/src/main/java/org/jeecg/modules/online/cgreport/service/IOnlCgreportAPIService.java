package org.jeecg.modules.online.cgreport.service;

import java.util.Map;

public interface IOnlCgreportAPIService {
   Map<String, Object> getDataById(String var1, Map<String, Object> var2);

   Map<String, Object> getDataByCode(String var1, Map<String, Object> var2);

   Map<String, Object> getData(String var1, String var2, Map<String, Object> var3);

   Map<String, Object> executeSelectSqlRoute(String var1, String var2, Map<String, Object> var3, String var4) throws Exception;
}
