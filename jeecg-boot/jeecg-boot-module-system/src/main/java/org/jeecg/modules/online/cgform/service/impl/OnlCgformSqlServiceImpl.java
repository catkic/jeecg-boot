/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONObject
 *  org.apache.ibatis.session.ExecutorType
 *  org.apache.ibatis.session.SqlSession
 *  org.jeecg.common.util.SpringContextUtils
 *  org.mybatis.spring.SqlSessionTemplate
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package org.jeecg.modules.online.cgform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.modules.online.cgform.converter.MapDictConverter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.mapper.OnlCgformFieldMapper;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgform.service.IOnlCgformSqlService;
import org.jeecg.modules.online.cgform.util.DataBaseUtils;
import org.jeecg.modules.online.cgform.util.j;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OnlCgformSqlServiceImpl implements IOnlCgformSqlService {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private IOnlCgformHeadService onlCgformHeadService;

    @Override
    public void saveBatchOnlineTable(OnlCgformHead head, List<OnlCgformField> fieldList, List<Map<String, Object>> dataList) throws BusinessException {
        try {
            MapDictConverter.a(2, dataList, fieldList);
            SqlSession sqlSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
            OnlCgformFieldMapper onlCgformFieldMapper = sqlSession.getMapper(OnlCgformFieldMapper.class);
            int n2 = 1000;
            if (n2 >= dataList.size()) {
                for (int i2 = 0; i2 < dataList.size(); ++i2) {
                    String string = JSON.toJSONString(dataList.get(i2));
                    this.a(string, head, fieldList, onlCgformFieldMapper);
                }
            } else {
                for (int i3 = 0; i3 < dataList.size(); ++i3) {
                    String string = JSON.toJSONString(dataList.get(i3));
                    this.a(string, head, fieldList, onlCgformFieldMapper);
                    if (i3 % n2 != 0) continue;
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
            sqlSession.commit();
        } catch (Exception e) {
            log.error("error ??? 我自己农的", e);
        }
    }

    @Override
    public void saveOrUpdateSubData(String subDataJsonStr, OnlCgformHead head, List<OnlCgformField> subFiledList) throws BusinessException {
        OnlCgformFieldMapper onlCgformFieldMapper = (OnlCgformFieldMapper) SpringContextUtils.getBean(OnlCgformFieldMapper.class);
        this.a(subDataJsonStr, head, subFiledList, onlCgformFieldMapper);
    }

    @Override
    public Map<String, String> saveOnlineImportDataWithValidate(OnlCgformHead head, List<OnlCgformField> fieldList, List<Map<String, Object>> dataList) {
        StringBuffer stringBuffer = new StringBuffer();
        j j2 = new j(fieldList);
        OnlCgformFieldMapper onlCgformFieldMapper = (OnlCgformFieldMapper) SpringContextUtils.getBean(OnlCgformFieldMapper.class);
        int n2 = 0;
        int n3 = 0;
        int n4 = dataList.size();
        for (int i2 = 0; i2 < n4; ++i2) {
            String string = JSON.toJSONString(dataList.get(i2));
            String string2 = j2.a(string, ++n2);
            if (string2 == null) {
                try {
                    this.a(string, head, fieldList, onlCgformFieldMapper);
                } catch (Exception exception) {
                    ++n3;
                    String string3 = this.a(exception.getCause().getMessage());
                    String string4 = j.b(string3, n2);
                    stringBuffer.append(string4);
                }
                continue;
            }
            ++n3;
            stringBuffer.append(string2);
        }
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("error", stringBuffer.toString());
        hashMap.put("tip", j.a(n4, n3));
        return hashMap;
    }

    private void a(String string, OnlCgformHead onlCgformHead, List<OnlCgformField> list, OnlCgformFieldMapper onlCgformFieldMapper) throws BusinessException {
        JSONObject jSONObject = JSONObject.parseObject((String) string);
        int n2 = this.onlCgformHeadService.executeEnhanceJava("import", "start", onlCgformHead, jSONObject);
        String string2 = onlCgformHead.getTableName();
        if (1 == n2) {
            Map<String, Object> map = DataBaseUtils.a(string2, list, jSONObject);
            onlCgformFieldMapper.executeInsertSQL(map);
        } else if (2 == n2) {
            Map<String, Object> map = DataBaseUtils.b(string2, list, jSONObject);
            onlCgformFieldMapper.executeUpdatetSQL(map);
        } else if (0 == n2) {
            // empty if block
        }
    }

    private String a(String string) {
        String string2 = "^Duplicate entry \'(.*)\' for key .*$";
        Pattern pattern = Pattern.compile(string2);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return "重复数据" + matcher.group(1);
        }
        return string;
    }
}

