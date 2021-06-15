/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.extension.service.IService
 *  freemarker.template.TemplateException
 *  org.hibernate.HibernateException
 *  org.jeecg.common.api.vo.Result
 */
package org.jeecg.modules.online.cgform.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceSql;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.model.OnlGenerateModel;
import org.jeecg.modules.online.cgform.model.OnlCgformModel;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.jeecg.modules.online.config.exception.DBException;

public interface IOnlCgformHeadService
extends IService<OnlCgformHead> {
    public Result<?> addAll(OnlCgformModel var1);

    public Result<?> editAll(OnlCgformModel var1);

    public void doDbSynch(String var1, String var2) throws HibernateException, IOException, TemplateException, SQLException, DBException;

    public void deleteRecordAndTable(String var1) throws DBException, SQLException;

    public void deleteRecord(String var1) throws DBException, SQLException;

    public List<Map<String, Object>> queryListData(String var1);

    public OnlCgformEnhanceJs queryEnhance(String var1, String var2);

    public void saveEnhance(OnlCgformEnhanceJs var1);

    public void editEnhance(OnlCgformEnhanceJs var1);

    public OnlCgformEnhanceSql queryEnhanceSql(String var1, String var2);

    public OnlCgformEnhanceJava queryEnhanceJava(OnlCgformEnhanceJava var1);

    public List<OnlCgformButton> queryButtonList(String var1, boolean var2);

    public List<OnlCgformButton> queryButtonList(String var1);

    public List<String> queryOnlinetables();

    public void saveDbTable2Online(String var1);

    public JSONObject queryFormItem(OnlCgformHead var1, String var2);

    public String saveManyFormData(String var1, JSONObject var2, String var3) throws DBException, BusinessException;

    public Map<String, Object> queryManyFormData(String var1, String var2) throws DBException;

    public List<Map<String, Object>> queryManySubFormData(String var1, String var2) throws DBException;

    public Map<String, Object> querySubFormData(String var1, String var2) throws DBException;

    public String editManyFormData(String var1, JSONObject var2) throws DBException, BusinessException;

    public int executeEnhanceJava(String var1, String var2, OnlCgformHead var3, JSONObject var4) throws BusinessException;

    public void executeEnhanceExport(OnlCgformHead var1, List<Map<String, Object>> var2) throws BusinessException;

    public void executeEnhanceList(OnlCgformHead var1, String var2, List<Map<String, Object>> var3) throws BusinessException;

    public void executeEnhanceSql(String var1, String var2, JSONObject var3);

    public void executeCustomerButton(String var1, String var2, String var3) throws BusinessException;

    public List<OnlCgformButton> queryValidButtonList(String var1);

    public OnlCgformEnhanceJs queryEnhanceJs(String var1, String var2);

    public void deleteOneTableInfo(String var1, String var2) throws BusinessException;

    public List<String> generateCode(OnlGenerateModel var1) throws Exception;

    public List<String> generateOneToMany(OnlGenerateModel var1) throws Exception;

    public void addCrazyFormData(String var1, JSONObject var2) throws DBException, UnsupportedEncodingException;

    public void editCrazyFormData(String var1, JSONObject var2) throws DBException, UnsupportedEncodingException;

    public Integer getMaxCopyVersion(String var1);

    public void copyOnlineTableConfig(OnlCgformHead var1) throws Exception;

    public void initCopyState(List<OnlCgformHead> var1);

    public void deleteBatch(String var1, String var2);

    public void updateParentNode(OnlCgformHead var1, String var2);
}

