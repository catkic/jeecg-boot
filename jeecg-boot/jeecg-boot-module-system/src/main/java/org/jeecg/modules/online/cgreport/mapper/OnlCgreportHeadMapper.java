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
   List<Map<String, Object>> executeSelect(@Param("sql") String var1);

   List<Map<?, ?>> executeSelete(@Param("sql") String var1);

   IPage<Map<String, Object>> selectPageBySql(Page<Map<String, Object>> var1, @Param("sqlStr") String var2);

   Long queryCountBySql(@Param("sql") String var1);

   Map<String, Object> queryCgReportMainConfig(@Param("reportId") String var1);

   List<Map<String, Object>> queryCgReportItems(@Param("cgrheadId") String var1);

   List<OnlCgreportParam> queryCgReportParams(@Param("cgrheadId") String var1);
}
