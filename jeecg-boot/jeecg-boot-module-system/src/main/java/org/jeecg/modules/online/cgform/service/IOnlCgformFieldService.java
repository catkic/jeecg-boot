/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.core.metadata.IPage
 *  com.baomidou.mybatisplus.extension.plugins.pagination.Page
 *  com.baomidou.mybatisplus.extension.service.IService
 *  org.apache.ibatis.annotations.Param
 */
package org.jeecg.modules.online.cgform.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.online.cgform.link.LinkDown;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.model.TreeModel;

public interface IOnlCgformFieldService
extends IService<OnlCgformField> {
    public Map<String, Object> queryAutolistPage(String var1, String var2, Map<String, Object> var3, List<String> var4);

    public Map<String, Object> queryAutoTreeNoPage(String var1, String var2, Map<String, Object> var3, List<String> var4, String var5);

    public void deleteAutoListMainAndSub(OnlCgformHead var1, String var2);

    public void deleteAutoListById(String var1, String var2);

    public void deleteAutoList(String var1, String var2, String var3);

    public void saveFormData(String var1, String var2, JSONObject var3, boolean var4);

    public void saveTreeFormData(String var1, String var2, JSONObject var3, String var4, String var5);

    public void saveFormData(List<OnlCgformField> var1, String var2, JSONObject var3);

    public List<OnlCgformField> queryFormFields(String var1, boolean var2);

    public List<OnlCgformField> queryFormFieldsByTableName(String var1);

    public OnlCgformField queryFormFieldByTableNameAndField(String var1, String var2);

    public void editTreeFormData(String var1, String var2, JSONObject var3, String var4, String var5);

    public void editFormData(String var1, String var2, JSONObject var3, boolean var4);

    public Map<String, Object> queryFormData(String var1, String var2, String var3);

    public Map<String, Object> queryFormData(List<OnlCgformField> var1, String var2, String var3);

    public List<Map<String, Object>> querySubFormData(List<OnlCgformField> var1, String var2, String var3, String var4);

    public List<Map<String, String>> getAutoListQueryInfo(String var1);

    public IPage<Map<String, Object>> selectPageBySql(Page<Map<String, Object>> var1, @Param(value="sqlStr") String var2);

    public List<String> selectOnlineHideColumns(String var1);

    public List<OnlCgformField> queryAvailableFields(String var1, String var2, String var3, boolean var4);

    public List<String> queryDisabledFields(String var1);

    public List<String> queryDisabledFields(String var1, String var2);

    public List<OnlCgformField> queryAvailableFields(String var1, boolean var2, List<OnlCgformField> var3, List<String> var4);

    public List<OnlCgformField> queryAvailableFields(String var1, String var2, boolean var3, List<OnlCgformField> var4, List<String> var5);

    public void executeInsertSQL(Map<String, Object> var1);

    public void executeUpdatetSQL(Map<String, Object> var1);

    public List<TreeModel> queryDataListByLinkDown(LinkDown var1);

    public void updateTreeNodeNoChild(String var1, String var2, String var3);

    public String queryTreeChildIds(OnlCgformHead var1, String var2);

    public String queryTreePids(OnlCgformHead var1, String var2);

    public String queryForeignKey(String var1, String var2);
}

