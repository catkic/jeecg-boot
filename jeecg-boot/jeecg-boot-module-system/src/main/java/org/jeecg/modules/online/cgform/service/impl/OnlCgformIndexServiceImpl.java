/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  org.jeecg.common.constant.CommonConstant
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package org.jeecg.modules.online.cgform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.modules.online.cgform.entity.OnlCgformIndex;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformIndexMapper;
import org.jeecg.modules.online.cgform.service.IOnlCgformIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "onlCgformIndexServiceImpl")
@Slf4j
public class OnlCgformIndexServiceImpl extends ServiceImpl<OnlCgformIndexMapper, OnlCgformIndex> implements IOnlCgformIndexService {
    @Autowired
    private OnlCgformHeadMapper onlCgformHeadMapper;

    @Override
    public void createIndex(String code, String databaseType, String tbname) {
        LambdaQueryWrapper<OnlCgformIndex> lambdaQueryWrapper = new LambdaQueryWrapper<OnlCgformIndex>()
                .eq(OnlCgformIndex::getCgformHeadId, code);
        List<OnlCgformIndex> list = this.list(lambdaQueryWrapper);
        if (list != null && list.size() > 0) {
            for (OnlCgformIndex onlCgformIndex : list) {
                if (CommonConstant.DEL_FLAG_1.equals(onlCgformIndex.getDelFlag()) || !"N".equals(onlCgformIndex.getIsDbSynch()))
                    continue;
                String string;
                String indexName = onlCgformIndex.getIndexName();
                String indexField = onlCgformIndex.getIndexField();
                String indexType = "normal".equals(onlCgformIndex.getIndexType()) ? " index " : onlCgformIndex.getIndexType() + " index ";
                switch (databaseType) {
                    case "MYSQL":
                        string = "create " + indexType + indexName + " on " + tbname + "(" + indexField + ")";
                        break;

                    case "ORACLE":
                        string = "create " + indexType + indexName + " on " + tbname + "(" + indexField + ")";
                        break;

                    case "SQLSERVER":
                        string = "create " + indexType + indexName + " on " + tbname + "(" + indexField + ")";
                        break;

                    case "POSTGRESQL":
                        string = "create " + indexType + indexName + " on " + tbname + "(" + indexField + ")";
                        break;

                    default:
                        string = "create " + indexType + indexName + " on " + tbname + "(" + indexField + ")";

                }
                log.info(" 创建索引 executeDDL ：" + string);
                this.onlCgformHeadMapper.executeDDL(string);
                onlCgformIndex.setIsDbSynch("Y");
                this.updateById(onlCgformIndex);
            }
        }
    }

    @Override
    public boolean isExistIndex(String countSql) {
        if (countSql == null) {
            return true;
        }
        return this.baseMapper.queryIndexCount(countSql) > 0;
    }

    @Override
    public List<OnlCgformIndex> getCgformIndexsByCgformId(String cgformId) {
        return this.baseMapper.selectList(new LambdaQueryWrapper<OnlCgformIndex>().in(OnlCgformIndex::getCgformHeadId, cgformId));
    }
}

