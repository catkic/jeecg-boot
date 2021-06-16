/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.mapper.BaseMapper
 *  com.baomidou.mybatisplus.core.metadata.IPage
 *  com.baomidou.mybatisplus.extension.plugins.pagination.Page
 *  org.apache.ibatis.annotations.Param
 */
package org.jeecg.modules.online.cgreport.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportHead;
import org.jeecg.modules.online.cgreport.entity.OnlCgreportParam;

public interface OnlCgreportHeadMapper extends BaseMapper<OnlCgreportHead> {
    public List<Map<String, Object>> executeSelect(@Param(value = "sql") String var1);

    public List<Map<String, Object>> executeSelete(@Param(value = "sql") String var1);

    public IPage<Map<String, Object>> selectPageBySql(Page<Map<String, Object>> var1, @Param(value = "sqlStr") String var2);

    public Long queryCountBySql(@Param(value = "sql") String var1);

    public Map<String, Object> queryCgReportMainConfig(@Param(value = "reportId") String var1);

    public List<Map<String, Object>> queryCgReportItems(@Param(value = "cgrheadId") String var1);

    public List<OnlCgreportParam> queryCgReportParams(@Param(value = "cgrheadId") String var1);
}

