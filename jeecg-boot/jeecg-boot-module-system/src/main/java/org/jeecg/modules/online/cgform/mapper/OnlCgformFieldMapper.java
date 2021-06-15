/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.mapper.BaseMapper
 *  com.baomidou.mybatisplus.core.metadata.IPage
 *  com.baomidou.mybatisplus.extension.plugins.pagination.Page
 *  org.apache.ibatis.annotations.Param
 */
package org.jeecg.modules.online.cgform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.online.cgform.link.LinkDown;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.model.TreeModel;

public interface OnlCgformFieldMapper
extends BaseMapper<OnlCgformField> {
    public List<Map<String, Object>> queryListBySql(@Param(value="sqlStr") String var1);

    public Integer queryCountBySql(@Param(value="sqlStr") String var1);

    public void deleteAutoList(@Param(value="sqlStr") String var1);

    public void saveFormData(@Param(value="sqlStr") String var1);

    public void editFormData(@Param(value="sqlStr") String var1);

    public Map<String, Object> queryFormData(@Param(value="sqlStr") String var1);

    public List<Map<String, Object>> queryListData(@Param(value="sqlStr") String var1);

    public IPage<Map<String, Object>> selectPageBySql(Page<Map<String, Object>> var1, @Param(value="sqlStr") String var2);

    public void executeInsertSQL(Map<String, Object> var1);

    public void executeUpdatetSQL(Map<String, Object> var1);

    public List<String> selectOnlineHideColumns(@Param(value="user_id") String var1, @Param(value="online_tbname") String var2);

    public List<String> selectOnlineDisabledColumns(@Param(value="user_id") String var1, @Param(value="online_tbname") String var2);

    public List<String> selectFlowAuthColumns(@Param(value="table_name") String var1, @Param(value="task_id") String var2, @Param(value="rule_type") String var3);

    @Deprecated
    public List<TreeModel> queryDataListByLinkDown(@Param(value="query") LinkDown var1);
}

