/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONException
 *  com.alibaba.fastjson.JSONObject
 *  com.baomidou.mybatisplus.core.conditions.Wrapper
 *  com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
 *  com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 *  freemarker.template.TemplateException
 *  org.apache.commons.lang.StringUtils
 *  org.hibernate.HibernateException
 *  org.jeecg.common.api.vo.Result
 *  org.jeecg.common.constant.CommonConstant
 *  org.jeecg.common.constant.enums.CgformEnum
 *  org.jeecg.common.util.MyClassLoader
 *  org.jeecg.common.util.RestUtil
 *  org.jeecg.common.util.SpringContextUtils
 *  org.jeecg.common.util.UUIDGenerator
 *  org.jeecg.common.util.oConvertUtils
 *  org.jeecgframework.codegenerate.database.DbReadTableUtil
 *  org.jeecgframework.codegenerate.generate.impl.CodeGenerateOne
 *  org.jeecgframework.codegenerate.generate.impl.CodeGenerateOneToMany
 *  org.jeecgframework.codegenerate.generate.pojo.ColumnVo
 *  org.jeecgframework.codegenerate.generate.pojo.TableVo
 *  org.jeecgframework.codegenerate.generate.pojo.onetomany.MainTableVo
 *  org.jeecgframework.codegenerate.generate.pojo.onetomany.SubTableVo
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.http.HttpHeaders
 *  org.springframework.http.HttpMethod
 *  org.springframework.http.MediaType
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package org.jeecg.modules.online.cgform.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.SerializedLambda;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import org.jeecg.common.constant.enums.CgformEnum;
import org.jeecg.common.util.MyClassLoader;
import org.jeecg.common.util.RestUtil;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.UUIDGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.auth.service.IOnlAuthPageService;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecg.modules.online.cgform.enhance.CgformEnhanceJavaListInter;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJava;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceSql;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.entity.OnlCgformIndex;
import org.jeecg.modules.online.cgform.mapper.OnlCgformButtonMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformEnhanceJavaMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformEnhanceJsMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformEnhanceSqlMapper;
import org.jeecg.modules.online.cgform.mapper.OnlCgformHeadMapper;
import org.jeecg.modules.online.cgform.model.OnlGenerateModel;
import org.jeecg.modules.online.cgform.service.IOnlCgformFieldService;
import org.jeecg.modules.online.cgform.service.IOnlCgformHeadService;
import org.jeecg.modules.online.cgform.service.IOnlCgformIndexService;
import org.jeecg.modules.online.config.a.a;
import org.jeecg.modules.online.config.a.b;
import org.jeecg.modules.online.config.b.c;
import org.jeecg.modules.online.config.b.d;
import org.jeecg.modules.online.config.exception.BusinessException;
import org.jeecg.modules.online.config.exception.DBException;
import org.jeecg.modules.online.config.service.DbTableHandleI;
import org.jeecgframework.codegenerate.database.DbReadTableUtil;
import org.jeecgframework.codegenerate.generate.impl.CodeGenerateOne;
import org.jeecgframework.codegenerate.generate.impl.CodeGenerateOneToMany;
import org.jeecgframework.codegenerate.generate.pojo.ColumnVo;
import org.jeecgframework.codegenerate.generate.pojo.TableVo;
import org.jeecgframework.codegenerate.generate.pojo.onetomany.MainTableVo;
import org.jeecgframework.codegenerate.generate.pojo.onetomany.SubTableVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="onlCgformHeadServiceImpl")
public class e
extends ServiceImpl<OnlCgformHeadMapper, OnlCgformHead>
implements IOnlCgformHeadService {
    private static final Logger a = LoggerFactory.getLogger(e.class);
    @Autowired
    private IOnlCgformFieldService fieldService;
    @Autowired
    private IOnlCgformIndexService indexService;
    @Autowired
    private OnlCgformEnhanceJsMapper onlCgformEnhanceJsMapper;
    @Autowired
    private OnlCgformButtonMapper onlCgformButtonMapper;
    @Autowired
    private OnlCgformEnhanceJavaMapper onlCgformEnhanceJavaMapper;
    @Autowired
    private OnlCgformEnhanceSqlMapper onlCgformEnhanceSqlMapper;
    @Autowired
    private b dataBaseConfig;
    @Autowired
    private IOnlAuthPageService onlAuthPageService;

    @Override
    @Transactional(rollbackFor={Exception.class})
    public Result<?> addAll(org.jeecg.modules.online.cgform.model.a model) {
        String string = UUID.randomUUID().toString().replace("-", "");
        OnlCgformHead onlCgformHead = model.getHead();
        List<OnlCgformField> list = model.getFields();
        List<OnlCgformIndex> list2 = model.getIndexs();
        onlCgformHead.setId(string);
        for (int i2 = 0; i2 < list.size(); ++i2) {
            OnlCgformField serializable = list.get(i2);
            serializable.setId(null);
            serializable.setCgformHeadId(string);
            if (serializable.getOrderNum() == null) {
                serializable.setOrderNum(i2);
            }
            this.a(serializable);
        }
        for (OnlCgformIndex onlCgformIndex : list2) {
            onlCgformIndex.setId(null);
            onlCgformIndex.setCgformHeadId(string);
        }
        onlCgformHead.setIsDbSynch("N");
        onlCgformHead.setQueryMode("single");
        onlCgformHead.setTableVersion(1);
        onlCgformHead.setCopyType(0);
        if (onlCgformHead.getTableType() == 3 && onlCgformHead.getTabOrderNum() == null) {
            onlCgformHead.setTabOrderNum(1);
        }
        super.save((Object)onlCgformHead);
        this.fieldService.saveBatch(list);
        this.indexService.saveBatch(list2);
        this.a(onlCgformHead, list);
        return Result.ok((String)"添加成功");
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public Result<?> editAll(org.jeecg.modules.online.cgform.model.a model) {
        Object object;
        Integer n2;
        OnlCgformHead onlCgformHead = model.getHead();
        OnlCgformHead onlCgformHead2 = (OnlCgformHead)super.getById((Serializable)((Object)onlCgformHead.getId()));
        if (onlCgformHead2 == null) {
            return Result.error((String)"未找到对应实体");
        }
        String string = onlCgformHead2.getIsDbSynch();
        if (org.jeecg.modules.online.cgform.util.b.a(onlCgformHead2, onlCgformHead)) {
            string = "N";
        }
        if ((n2 = onlCgformHead2.getTableVersion()) == null) {
            n2 = 1;
        }
        n2 = n2 + 1;
        onlCgformHead.setTableVersion(n2);
        List<OnlCgformField> list = model.getFields();
        List<OnlCgformIndex> list2 = model.getIndexs();
        ArrayList<OnlCgformField> arrayList = new ArrayList<OnlCgformField>();
        ArrayList<OnlCgformField> arrayList2 = new ArrayList<OnlCgformField>();
        for (OnlCgformField object32 : list) {
            String string2 = String.valueOf(object32.getId());
            this.a(object32);
            if (string2.length() == 32) {
                arrayList2.add(object32);
                continue;
            }
            object = "_pk";
            if (((String)object).equals(string2)) continue;
            object32.setId(null);
            object32.setCgformHeadId(onlCgformHead.getId());
            arrayList.add(object32);
        }
        if (arrayList.size() > 0) {
            string = "N";
        }
        int n3 = 0;
        for (OnlCgformField onlCgformField : arrayList2) {
            object = (OnlCgformField)this.fieldService.getById((Serializable)((Object)onlCgformField.getId()));
            this.a(((OnlCgformField)object).getMainTable(), onlCgformHead.getTableName());
            boolean bl = org.jeecg.modules.online.cgform.util.b.a((OnlCgformField)object, onlCgformField);
            if (bl) {
                string = "N";
            }
            if ((((OnlCgformField)object).getOrderNum() == null ? 0 : ((OnlCgformField)object).getOrderNum()) > n3) {
                n3 = ((OnlCgformField)object).getOrderNum();
            }
            this.fieldService.updateById(onlCgformField);
        }
        for (OnlCgformField onlCgformField : arrayList) {
            if (onlCgformField.getOrderNum() == null) {
                onlCgformField.setOrderNum(++n3);
            }
            this.fieldService.save(onlCgformField);
        }
        List<OnlCgformIndex> list3 = this.indexService.getCgformIndexsByCgformId(onlCgformHead.getId());
        ArrayList<OnlCgformIndex> arrayList3 = new ArrayList<OnlCgformIndex>();
        object = new ArrayList<OnlCgformIndex>();
        for (OnlCgformIndex onlCgformIndex : list2) {
            String string3 = String.valueOf(onlCgformIndex.getId());
            if (string3.length() == 32) {
                object.add(onlCgformIndex);
                continue;
            }
            onlCgformIndex.setId(null);
            onlCgformIndex.setIsDbSynch("N");
            onlCgformIndex.setDelFlag(CommonConstant.DEL_FLAG_0);
            onlCgformIndex.setCgformHeadId(onlCgformHead.getId());
            arrayList3.add(onlCgformIndex);
        }
        for (OnlCgformIndex onlCgformIndex : list3) {
            boolean bl = list2.stream().anyMatch(onlCgformIndex2 -> onlCgformIndex.getId().equals(onlCgformIndex2.getId()));
            if (bl) continue;
            onlCgformIndex.setDelFlag(CommonConstant.DEL_FLAG_1);
            object.add(onlCgformIndex);
            string = "N";
        }
        if (arrayList3.size() > 0) {
            string = "N";
            this.indexService.saveBatch(arrayList3);
        }
        Object object2 = object.iterator();
        while (object2.hasNext()) {
            OnlCgformIndex onlCgformIndex = object2.next();
            OnlCgformIndex onlCgformIndex3 = (OnlCgformIndex)this.indexService.getById((Serializable)((Object)onlCgformIndex.getId()));
            boolean bl = org.jeecg.modules.online.cgform.util.b.a(onlCgformIndex3, onlCgformIndex);
            if (bl) {
                string = "N";
                onlCgformIndex.setIsDbSynch("N");
            }
            this.indexService.updateById(onlCgformIndex);
        }
        if (model.getDeleteFieldIds().size() > 0) {
            string = "N";
            object2 = model.getDeleteFieldIds();
            Iterator iterator = object2.iterator();
            while (iterator.hasNext()) {
                String string4 = (String)iterator.next();
                OnlCgformField onlCgformField = (OnlCgformField)this.fieldService.getById((Serializable)((Object)string4));
                if (onlCgformField == null) continue;
                this.a(onlCgformField.getMainTable(), onlCgformHead.getTableName());
                this.fieldService.removeById((Serializable)((Object)string4));
            }
        }
        onlCgformHead.setIsDbSynch(string);
        super.updateById((Object)onlCgformHead);
        this.a(onlCgformHead, list);
        this.b(onlCgformHead, list);
        return Result.ok((String)"全部修改成功");
    }

    private void a(String string, String string2) {
        LambdaQueryWrapper lambdaQueryWrapper;
        OnlCgformHead onlCgformHead;
        if (oConvertUtils.isNotEmpty((Object)string) && (onlCgformHead = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)(lambdaQueryWrapper = (LambdaQueryWrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string)))) != null) {
            String string3 = onlCgformHead.getSubTableStr();
            String[] arrstring = string3.split(",");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (String string4 : arrstring) {
                if (string4.equals(string2)) continue;
                arrayList.add(string4);
            }
            onlCgformHead.setSubTableStr(String.join((CharSequence)",", arrayList));
            ((OnlCgformHeadMapper)this.baseMapper).updateById(onlCgformHead);
        }
    }

    @Override
    public void doDbSynch(String code, String synMethod) throws HibernateException, IOException, TemplateException, SQLException, DBException {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById((Serializable)((Object)code));
        if (onlCgformHead == null) {
            throw new DBException("实体配置不存在");
        }
        String string = onlCgformHead.getTableName();
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object)code);
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        List list = this.fieldService.list((Wrapper)lambdaQueryWrapper);
        a a2 = new a();
        a2.setTableName(string);
        a2.setJformPkType(onlCgformHead.getIdType());
        a2.setJformPkSequence(onlCgformHead.getIdSequence());
        a2.setContent(onlCgformHead.getTableTxt());
        a2.setColumns(list);
        a2.setDbConfig(this.dataBaseConfig);
        if ("normal".equals(synMethod)) {
            long l2 = System.currentTimeMillis();
            boolean bl = d.a(string);
            a.info("==判断表是否存在消耗时间" + (System.currentTimeMillis() - l2) + "毫秒");
            if (bl) {
                c c2 = new c();
                List<String> list2 = c2.b(a2);
                for (String object2 : list2) {
                    if (oConvertUtils.isEmpty((Object)object2) || oConvertUtils.isEmpty((Object)object2.trim())) continue;
                    ((OnlCgformHeadMapper)this.baseMapper).executeDDL(object2);
                }
                List list3 = this.indexService.list((Wrapper)new LambdaQueryWrapper().eq(OnlCgformIndex::getCgformHeadId, (Object)code));
                Iterator iterator = list3.iterator();
                while (iterator.hasNext()) {
                    OnlCgformIndex onlCgformIndex = (OnlCgformIndex)iterator.next();
                    if (!"N".equals(onlCgformIndex.getIsDbSynch()) && !CommonConstant.DEL_FLAG_1.equals(onlCgformIndex.getDelFlag())) continue;
                    String string2 = c2.b(onlCgformIndex.getIndexName(), string);
                    if (this.indexService.isExistIndex(string2)) {
                        String string3 = c2.a(onlCgformIndex.getIndexName(), string);
                        try {
                            a.info("删除索引 executeDDL:" + string3);
                            ((OnlCgformHeadMapper)this.baseMapper).executeDDL(string3);
                            if (!CommonConstant.DEL_FLAG_1.equals(onlCgformIndex.getDelFlag())) continue;
                            this.indexService.removeById((Serializable)((Object)onlCgformIndex.getId()));
                        }
                        catch (Exception exception) {
                            a.error("删除表【" + string + "】索引(" + onlCgformIndex.getIndexName() + ")失败!", (Throwable)exception);
                        }
                        continue;
                    }
                    if (!CommonConstant.DEL_FLAG_1.equals(onlCgformIndex.getDelFlag())) continue;
                    this.indexService.removeById((Serializable)((Object)onlCgformIndex.getId()));
                }
            } else {
                c.a(a2);
            }
        } else if ("force".equals(synMethod)) {
            DbTableHandleI dbTableHandleI = d.getTableHandle();
            String string4 = dbTableHandleI.dropTableSQL(string);
            ((OnlCgformHeadMapper)this.baseMapper).executeDDL(string4);
            c.a(a2);
        }
        this.indexService.createIndex(code, d.getDatabaseType(), string);
        onlCgformHead.setIsDbSynch("Y");
        if (onlCgformHead.getTableVersion() == 1) {
            onlCgformHead.setTableVersion(2);
        }
        this.updateById(onlCgformHead);
    }

    @Override
    public void deleteRecordAndTable(String id) throws DBException, SQLException {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById((Serializable)((Object)id));
        if (onlCgformHead == null) {
            throw new DBException("实体配置不存在");
        }
        long l2 = System.currentTimeMillis();
        boolean bl = d.a(onlCgformHead.getTableName());
        a.info("==判断表是否存在消耗时间 " + (System.currentTimeMillis() - l2) + "毫秒");
        if (bl) {
            String string = d.getTableHandle().dropTableSQL(onlCgformHead.getTableName());
            a.info(" 删除表  executeDDL： " + string);
            ((OnlCgformHeadMapper)this.baseMapper).executeDDL(string);
        }
        ((OnlCgformHeadMapper)this.baseMapper).deleteById((Serializable)((Object)id));
        this.a(onlCgformHead);
    }

    @Override
    public void deleteRecord(String id) throws DBException, SQLException {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById((Serializable)((Object)id));
        if (onlCgformHead == null) {
            throw new DBException("实体配置不存在");
        }
        ((OnlCgformHeadMapper)this.baseMapper).deleteById((Serializable)((Object)id));
        this.a(onlCgformHead);
    }

    private void a(OnlCgformHead onlCgformHead) {
        if (onlCgformHead.getTableType() == 3) {
            Object object;
            LambdaQueryWrapper lambdaQueryWrapper = (LambdaQueryWrapper)new LambdaQueryWrapper().eq(OnlCgformField::getCgformHeadId, (Object)onlCgformHead.getId());
            List list = this.fieldService.list((Wrapper)lambdaQueryWrapper);
            String string = null;
            Object object2 = list.iterator();
            while (object2.hasNext() && !oConvertUtils.isNotEmpty((Object)(string = ((OnlCgformField)(object = (OnlCgformField)object2.next())).getMainTable()))) {
            }
            if (oConvertUtils.isNotEmpty(string) && (object2 = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string))) != null && oConvertUtils.isNotEmpty((Object)(object = ((OnlCgformHead)object2).getSubTableStr()))) {
                List list2 = Arrays.asList(((String)object).split(",")).stream().collect(Collectors.toList());
                list2.remove(onlCgformHead.getTableName());
                ((OnlCgformHead)object2).setSubTableStr(String.join((CharSequence)",", list2));
                ((OnlCgformHeadMapper)this.baseMapper).updateById(object2);
            }
        }
    }

    @Override
    public List<Map<String, Object>> queryListData(String sql) {
        return ((OnlCgformHeadMapper)this.baseMapper).queryList(sql);
    }

    @Override
    public void saveEnhance(OnlCgformEnhanceJs onlCgformEnhanceJs) {
        this.onlCgformEnhanceJsMapper.insert(onlCgformEnhanceJs);
    }

    @Override
    public OnlCgformEnhanceJs queryEnhance(String code, String type) {
        return (OnlCgformEnhanceJs)this.onlCgformEnhanceJsMapper.selectOne((Wrapper)((LambdaQueryWrapper)new LambdaQueryWrapper().eq(OnlCgformEnhanceJs::getCgJsType, (Object)type)).eq(OnlCgformEnhanceJs::getCgformHeadId, (Object)code));
    }

    @Override
    public void editEnhance(OnlCgformEnhanceJs onlCgformEnhanceJs) {
        this.onlCgformEnhanceJsMapper.updateById(onlCgformEnhanceJs);
    }

    @Override
    public OnlCgformEnhanceSql queryEnhanceSql(String formId, String buttonCode) {
        return (OnlCgformEnhanceSql)this.onlCgformEnhanceSqlMapper.selectOne((Wrapper)((LambdaQueryWrapper)new LambdaQueryWrapper().eq(OnlCgformEnhanceSql::getCgformHeadId, (Object)formId)).eq(OnlCgformEnhanceSql::getButtonCode, (Object)buttonCode));
    }

    @Override
    public OnlCgformEnhanceJava queryEnhanceJava(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getButtonCode, (Object)onlCgformEnhanceJava.getButtonCode());
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getCgformHeadId, (Object)onlCgformEnhanceJava.getCgformHeadId());
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getCgJavaType, (Object)onlCgformEnhanceJava.getCgJavaType());
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getEvent, (Object)onlCgformEnhanceJava.getEvent());
        return (OnlCgformEnhanceJava)this.onlCgformEnhanceJavaMapper.selectOne((Wrapper)lambdaQueryWrapper);
    }

    @Override
    public List<OnlCgformButton> queryButtonList(String code, boolean isListButton) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformButton::getButtonStatus, (Object)"1");
        lambdaQueryWrapper.eq(OnlCgformButton::getCgformHeadId, (Object)code);
        if (isListButton) {
            lambdaQueryWrapper.in(OnlCgformButton::getButtonStyle, new Object[]{"link", "button"});
        } else {
            lambdaQueryWrapper.eq(OnlCgformButton::getButtonStyle, (Object)"form");
        }
        lambdaQueryWrapper.orderByAsc(OnlCgformButton::getOrderNum);
        return this.onlCgformButtonMapper.selectList((Wrapper)lambdaQueryWrapper);
    }

    @Override
    public List<OnlCgformButton> queryButtonList(String code) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformButton::getButtonStatus, (Object)"1");
        lambdaQueryWrapper.eq(OnlCgformButton::getCgformHeadId, (Object)code);
        lambdaQueryWrapper.orderByAsc(OnlCgformButton::getOrderNum);
        return this.onlCgformButtonMapper.selectList((Wrapper)lambdaQueryWrapper);
    }

    @Override
    public List<String> queryOnlinetables() {
        return ((OnlCgformHeadMapper)this.baseMapper).queryOnlinetables();
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void saveDbTable2Online(String tbname) {
        OnlCgformHead onlCgformHead = new OnlCgformHead();
        onlCgformHead.setTableType(1);
        onlCgformHead.setIsCheckbox("Y");
        onlCgformHead.setIsDbSynch("Y");
        onlCgformHead.setIsTree("N");
        onlCgformHead.setIsPage("Y");
        onlCgformHead.setQueryMode("group");
        onlCgformHead.setTableName(tbname.toLowerCase());
        onlCgformHead.setTableTxt(tbname);
        onlCgformHead.setTableVersion(1);
        onlCgformHead.setFormTemplate("1");
        onlCgformHead.setCopyType(0);
        onlCgformHead.setIsDesForm("N");
        onlCgformHead.setScroll(1);
        onlCgformHead.setThemeTemplate("normal");
        String string = UUIDGenerator.generate();
        onlCgformHead.setId(string);
        ArrayList<OnlCgformField> arrayList = new ArrayList<OnlCgformField>();
        try {
            List list = DbReadTableUtil.b((String)tbname);
            for (int i2 = 0; i2 < list.size(); ++i2) {
                ColumnVo columnVo = (ColumnVo)list.get(i2);
                a.info("  columnt : " + columnVo.toString());
                String string2 = columnVo.getFieldDbName();
                OnlCgformField onlCgformField = new OnlCgformField();
                onlCgformField.setCgformHeadId(string);
                onlCgformField.setDbFieldNameOld(columnVo.getFieldDbName().toLowerCase());
                onlCgformField.setDbFieldName(columnVo.getFieldDbName().toLowerCase());
                if (oConvertUtils.isNotEmpty((Object)columnVo.getFiledComment())) {
                    onlCgformField.setDbFieldTxt(columnVo.getFiledComment());
                } else {
                    onlCgformField.setDbFieldTxt(columnVo.getFieldName());
                }
                onlCgformField.setDbIsKey(0);
                onlCgformField.setIsShowForm(1);
                onlCgformField.setIsQuery(0);
                onlCgformField.setFieldMustInput("0");
                onlCgformField.setIsShowList(1);
                onlCgformField.setOrderNum(i2 + 1);
                onlCgformField.setQueryMode("single");
                onlCgformField.setDbLength(oConvertUtils.getInt((String)columnVo.getPrecision()));
                onlCgformField.setFieldLength(120);
                onlCgformField.setDbPointLength(oConvertUtils.getInt((String)columnVo.getScale()));
                onlCgformField.setFieldShowType("text");
                onlCgformField.setDbIsNull("Y".equals(columnVo.getNullable()) ? 1 : 0);
                onlCgformField.setIsReadOnly(0);
                if ("id".equalsIgnoreCase(string2)) {
                    String[] arrstring = new String[]{"java.lang.Integer", "java.lang.Long"};
                    String string3 = columnVo.getFieldType();
                    if (Arrays.asList(arrstring).contains(string3)) {
                        onlCgformHead.setIdType("NATIVE");
                    } else {
                        onlCgformHead.setIdType("UUID");
                    }
                    onlCgformField.setDbIsKey(1);
                    onlCgformField.setIsShowForm(0);
                    onlCgformField.setIsShowList(0);
                    onlCgformField.setIsReadOnly(1);
                }
                if ("create_by".equalsIgnoreCase(string2) || "create_time".equalsIgnoreCase(string2) || "update_by".equalsIgnoreCase(string2) || "update_time".equalsIgnoreCase(string2) || "sys_org_code".equalsIgnoreCase(string2)) {
                    onlCgformField.setIsShowForm(0);
                    onlCgformField.setIsShowList(0);
                }
                if ("java.lang.Integer".equalsIgnoreCase(columnVo.getFieldType())) {
                    onlCgformField.setDbType("int");
                } else if ("java.lang.Long".equalsIgnoreCase(columnVo.getFieldType())) {
                    onlCgformField.setDbType("int");
                } else if ("java.util.Date".equalsIgnoreCase(columnVo.getFieldType())) {
                    onlCgformField.setDbType("Date");
                    onlCgformField.setFieldShowType("date");
                } else if ("java.lang.Double".equalsIgnoreCase(columnVo.getFieldType()) || "java.lang.Float".equalsIgnoreCase(columnVo.getFieldType())) {
                    onlCgformField.setDbType("double");
                } else if ("java.math.BigDecimal".equalsIgnoreCase(columnVo.getFieldType()) || "BigDecimal".equalsIgnoreCase(columnVo.getFieldType())) {
                    onlCgformField.setDbType("BigDecimal");
                } else if ("byte[]".equalsIgnoreCase(columnVo.getFieldType()) || columnVo.getFieldType().contains("blob")) {
                    onlCgformField.setDbType("Blob");
                    columnVo.setCharmaxLength(null);
                } else if ("java.lang.Object".equals(columnVo.getFieldType()) && ("text".equalsIgnoreCase(columnVo.getFieldDbType()) || "ntext".equalsIgnoreCase(columnVo.getFieldDbType()))) {
                    onlCgformField.setDbType("Text");
                    onlCgformField.setFieldShowType("textarea");
                } else if ("java.lang.Object".equals(columnVo.getFieldType()) && "image".equalsIgnoreCase(columnVo.getFieldDbType())) {
                    onlCgformField.setDbType("Blob");
                } else {
                    onlCgformField.setDbType("string");
                }
                if (oConvertUtils.isEmpty((Object)columnVo.getPrecision()) && oConvertUtils.isNotEmpty((Object)columnVo.getCharmaxLength())) {
                    if (Long.valueOf(columnVo.getCharmaxLength()) >= 3000L) {
                        onlCgformField.setDbType("Text");
                        onlCgformField.setFieldShowType("textarea");
                        try {
                            onlCgformField.setDbLength(Integer.valueOf(columnVo.getCharmaxLength()));
                        }
                        catch (Exception exception) {
                            a.error(exception.getMessage(), (Throwable)exception);
                        }
                    } else {
                        onlCgformField.setDbLength(Integer.valueOf(columnVo.getCharmaxLength()));
                    }
                } else {
                    if (oConvertUtils.isNotEmpty((Object)columnVo.getPrecision())) {
                        onlCgformField.setDbLength(Integer.valueOf(columnVo.getPrecision()));
                    } else if (onlCgformField.getDbType().equals("int")) {
                        onlCgformField.setDbLength(10);
                    }
                    if (oConvertUtils.isNotEmpty((Object)columnVo.getScale())) {
                        onlCgformField.setDbPointLength(Integer.valueOf(columnVo.getScale()));
                    }
                }
                if (oConvertUtils.getInt((String)columnVo.getPrecision()) == -1 && oConvertUtils.getInt((String)columnVo.getScale()) == 0) {
                    onlCgformField.setDbType("Text");
                }
                if ("Blob".equals(onlCgformField.getDbType()) || "Text".equals(onlCgformField.getDbType()) || "Date".equals(onlCgformField.getDbType())) {
                    onlCgformField.setDbLength(0);
                    onlCgformField.setDbPointLength(0);
                }
                arrayList.add(onlCgformField);
            }
        }
        catch (Exception exception) {
            a.error(exception.getMessage(), (Throwable)exception);
        }
        if (oConvertUtils.isEmpty((Object)onlCgformHead.getFormCategory())) {
            onlCgformHead.setFormCategory("bdfl_include");
        }
        this.save(onlCgformHead);
        this.fieldService.saveBatch(arrayList);
    }

    private void a(OnlCgformHead onlCgformHead, List<OnlCgformField> list) {
        block14: {
            block15: {
                if (onlCgformHead.getTableType() != 3) break block15;
                onlCgformHead = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectById((Serializable)((Object)onlCgformHead.getId()));
                for (int i2 = 0; i2 < list.size(); ++i2) {
                    OnlCgformHead onlCgformHead2;
                    OnlCgformField onlCgformField = list.get(i2);
                    String string = onlCgformField.getMainTable();
                    if (oConvertUtils.isEmpty((Object)string) || (onlCgformHead2 = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string))) == null) continue;
                    String string2 = onlCgformHead2.getSubTableStr();
                    if (oConvertUtils.isEmpty((Object)string2)) {
                        string2 = onlCgformHead.getTableName();
                    } else if (string2.indexOf(onlCgformHead.getTableName()) < 0) {
                        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(string2.split(",")));
                        for (int i3 = 0; i3 < arrayList.size(); ++i3) {
                            String string3 = (String)arrayList.get(i3);
                            OnlCgformHead onlCgformHead3 = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string3));
                            if (onlCgformHead3 == null || onlCgformHead.getTabOrderNum() >= oConvertUtils.getInt((Object)onlCgformHead3.getTabOrderNum(), (int)0)) continue;
                            arrayList.add(i3, onlCgformHead.getTableName());
                            break;
                        }
                        if (arrayList.indexOf(onlCgformHead.getTableName()) < 0) {
                            arrayList.add(onlCgformHead.getTableName());
                        }
                        string2 = String.join((CharSequence)",", arrayList);
                    }
                    onlCgformHead2.setSubTableStr(string2);
                    ((OnlCgformHeadMapper)this.baseMapper).updateById(onlCgformHead2);
                    break block14;
                }
                break block14;
            }
            List list2 = ((OnlCgformHeadMapper)this.baseMapper).selectList((Wrapper)new LambdaQueryWrapper().like(OnlCgformHead::getSubTableStr, (Object)onlCgformHead.getTableName()));
            if (list2 == null || list2.size() <= 0) break block14;
            for (OnlCgformHead onlCgformHead4 : list2) {
                String string = onlCgformHead4.getSubTableStr();
                if (onlCgformHead4.getSubTableStr().equals(onlCgformHead.getTableName())) {
                    string = "";
                } else if (onlCgformHead4.getSubTableStr().startsWith(onlCgformHead.getTableName() + ",")) {
                    string = string.replace(onlCgformHead.getTableName() + ",", "");
                } else if (onlCgformHead4.getSubTableStr().endsWith("," + onlCgformHead.getTableName())) {
                    string = string.replace("," + onlCgformHead.getTableName(), "");
                } else if (onlCgformHead4.getSubTableStr().indexOf("," + onlCgformHead.getTableName() + ",") != -1) {
                    string = string.replace("," + onlCgformHead.getTableName() + ",", ",");
                }
                onlCgformHead4.setSubTableStr(string);
                ((OnlCgformHeadMapper)this.baseMapper).updateById(onlCgformHead4);
            }
        }
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public String saveManyFormData(String code, JSONObject json, String xAccessToken) throws DBException, BusinessException {
        JSONObject jSONObject;
        String string;
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById((Serializable)((Object)code));
        if (onlCgformHead == null) {
            throw new DBException("数据库主表ID[" + code + "]不存在");
        }
        String string2 = "add";
        this.executeEnhanceJava(string2, "start", onlCgformHead, json);
        String string3 = org.jeecg.modules.online.cgform.util.b.f(onlCgformHead.getTableName());
        if (onlCgformHead.getTableType() == 2 && oConvertUtils.isNotEmpty((Object)(string = onlCgformHead.getSubTableStr()))) {
            for (String string4 : jSONObject = string.split(",")) {
                OnlCgformHead onlCgformHead2;
                JSONArray jSONArray = json.getJSONArray(string4);
                if (jSONArray == null || jSONArray.size() == 0 || (onlCgformHead2 = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string4))) == null) continue;
                List list = this.fieldService.list((Wrapper)new LambdaQueryWrapper().eq(OnlCgformField::getCgformHeadId, (Object)onlCgformHead2.getId()));
                String string5 = "";
                String string6 = null;
                for (OnlCgformField onlCgformField : list) {
                    if (oConvertUtils.isEmpty((Object)onlCgformField.getMainField())) continue;
                    string5 = onlCgformField.getDbFieldName();
                    String string7 = onlCgformField.getMainField();
                    if (json.get((Object)string7.toLowerCase()) != null) {
                        string6 = json.getString(string7.toLowerCase());
                    }
                    if (json.get((Object)string7.toUpperCase()) == null) continue;
                    string6 = json.getString(string7.toUpperCase());
                }
                for (int i2 = 0; i2 < jSONArray.size(); ++i2) {
                    OnlCgformField onlCgformField;
                    onlCgformField = jSONArray.getJSONObject(i2);
                    if (string6 != null) {
                        onlCgformField.put(string5, string6);
                    }
                    this.fieldService.saveFormData(list, string4, (JSONObject)onlCgformField);
                }
            }
        }
        if ("Y".equals(onlCgformHead.getIsTree())) {
            this.fieldService.saveTreeFormData(code, string3, json, onlCgformHead.getTreeIdField(), onlCgformHead.getTreeParentIdField());
        } else {
            this.fieldService.saveFormData(code, string3, json, false);
        }
        this.executeEnhanceSql(string2, code, json);
        this.executeEnhanceJava(string2, "end", onlCgformHead, json);
        if (oConvertUtils.isNotEmpty((Object)json.get((Object)"bpm_status")) || oConvertUtils.isNotEmpty((Object)json.get((Object)"bpm_status".toUpperCase()))) {
            try {
                string = new HttpHeaders();
                string.setContentType(MediaType.parseMediaType((String)"application/json;charset=UTF-8"));
                string.set("Accept", "application/json;charset=UTF-8");
                string.set("X-Access-Token", xAccessToken);
                jSONObject = new JSONObject();
                jSONObject.put("flowCode", (Object)("onl_" + onlCgformHead.getTableName()));
                jSONObject.put("id", json.get((Object)"id"));
                jSONObject.put("formUrl", (Object)"modules/bpm/task/form/OnlineFormDetail");
                jSONObject.put("formUrlMobile", (Object)"online/OnlineDetailForm");
                String string8 = RestUtil.getBaseUrl() + "/act/process/extActProcess/saveMutilProcessDraft";
                JSONObject jSONObject2 = (JSONObject)RestUtil.request((String)string8, (HttpMethod)HttpMethod.POST, (HttpHeaders)string, null, (Object)jSONObject, JSONObject.class).getBody();
                if (jSONObject2 != null) {
                    String string9 = jSONObject2.getString("result");
                    a.info("保存流程草稿 dataId : " + string9);
                }
            }
            catch (Exception exception) {
                a.error("保存流程草稿异常, " + exception.getMessage(), (Throwable)exception);
            }
        }
        return onlCgformHead.getTableName();
    }

    @Override
    public Map<String, Object> querySubFormData(String table, String mainId) throws DBException {
        HashMap<String, Object> hashMap = new HashMap();
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)table));
        if (onlCgformHead == null) {
            throw new DBException("数据库子表[" + table + "]不存在");
        }
        List<OnlCgformField> list = this.fieldService.queryFormFields(onlCgformHead.getId(), false);
        String string = null;
        Object object = list.iterator();
        while (object.hasNext()) {
            OnlCgformField onlCgformField = object.next();
            if (!oConvertUtils.isNotEmpty((Object)onlCgformField.getMainField())) continue;
            string = onlCgformField.getDbFieldName();
            break;
        }
        if ((object = this.fieldService.querySubFormData(list, table, string, mainId)) != null && object.size() == 0) {
            throw new DBException("数据库子表[" + table + "]未找到相关信息,主表ID为" + mainId);
        }
        if (object.size() > 1) {
            throw new DBException("数据库子表[" + table + "]存在多条记录,主表ID为" + mainId);
        }
        hashMap = (Map)object.get(0);
        return hashMap;
    }

    @Override
    public List<Map<String, Object>> queryManySubFormData(String table, String mainId) throws DBException {
        OnlCgformField onlCgformField2;
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)table));
        if (onlCgformHead == null) {
            throw new DBException("数据库子表[" + table + "]不存在");
        }
        List<OnlCgformField> list = this.fieldService.queryFormFields(onlCgformHead.getId(), false);
        if (list == null || list.size() == 0) {
            throw new DBException("找不到子表字段，请确认配置是否正确!");
        }
        String string = null;
        String string2 = null;
        String string3 = null;
        for (OnlCgformField onlCgformField2 : list) {
            if (!oConvertUtils.isNotEmpty((Object)onlCgformField2.getMainField())) continue;
            string = onlCgformField2.getDbFieldName();
            string2 = onlCgformField2.getMainTable();
            string3 = onlCgformField2.getMainField();
            break;
        }
        ArrayList arrayList = new ArrayList();
        onlCgformField2 = new OnlCgformField();
        onlCgformField2.setDbFieldName(string3);
        arrayList.add(onlCgformField2);
        Map<String, Object> map = this.fieldService.queryFormData(arrayList, string2, mainId);
        String string4 = oConvertUtils.getString((String)oConvertUtils.getString((Object)map.get(string3)), (String)oConvertUtils.getString((Object)map.get(string3.toUpperCase())));
        List<Map<String, Object>> list2 = this.fieldService.querySubFormData(list, table, string, string4);
        if (list2 != null && list2.size() == 0) {
            throw new DBException("数据库子表[" + table + "]未找到相关信息,主表ID为" + mainId);
        }
        ArrayList<Map<String, Object>> arrayList2 = new ArrayList<Map<String, Object>>(list2.size());
        for (Map<String, Object> map2 : list2) {
            arrayList2.add(org.jeecg.modules.online.cgform.util.b.b(map2));
        }
        return arrayList2;
    }

    @Override
    public Map<String, Object> queryManyFormData(String code, String id) throws DBException {
        String string;
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById((Serializable)((Object)code));
        if (onlCgformHead == null) {
            throw new DBException("数据库主表ID[" + code + "]不存在");
        }
        List<OnlCgformField> list = this.fieldService.queryFormFields(code, true);
        if (list == null || list.size() == 0) {
            throw new DBException("找不到字段，请确认配置是否正确!");
        }
        Map<String, Object> map = this.fieldService.queryFormData(list, onlCgformHead.getTableName(), id);
        if (onlCgformHead.getTableType() == 2 && oConvertUtils.isNotEmpty((Object)(string = onlCgformHead.getSubTableStr()))) {
            String[] arrstring;
            for (String string2 : arrstring = string.split(",")) {
                OnlCgformHead onlCgformHead2 = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string2));
                if (onlCgformHead2 == null) continue;
                List<OnlCgformField> list2 = this.fieldService.queryFormFields(onlCgformHead2.getId(), false);
                String string3 = "";
                String string4 = null;
                for (OnlCgformField onlCgformField : list2) {
                    if (oConvertUtils.isEmpty((Object)onlCgformField.getMainField())) continue;
                    string3 = onlCgformField.getDbFieldName();
                    String string5 = onlCgformField.getMainField();
                    if (null == map.get(string5)) {
                        string4 = map.get(string5.toUpperCase()).toString();
                        continue;
                    }
                    string4 = map.get(string5).toString();
                }
                List<Map<String, Object>> list3 = this.fieldService.querySubFormData(list2, string2, string3, string4);
                if (list3 == null || list3.size() == 0) {
                    map.put(string2, new String[0]);
                    continue;
                }
                map.put(string2, org.jeecg.modules.online.cgform.util.b.d(list3));
            }
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public String editManyFormData(String code, JSONObject json) throws DBException, BusinessException {
        String string;
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById((Serializable)((Object)code));
        if (onlCgformHead == null) {
            throw new DBException("数据库主表ID[" + code + "]不存在");
        }
        String string2 = "edit";
        this.executeEnhanceJava(string2, "start", onlCgformHead, json);
        String string3 = onlCgformHead.getTableName();
        if ("Y".equals(onlCgformHead.getIsTree())) {
            this.fieldService.editTreeFormData(code, string3, json, onlCgformHead.getTreeIdField(), onlCgformHead.getTreeParentIdField());
        } else {
            this.fieldService.editFormData(code, string3, json, false);
        }
        if (onlCgformHead.getTableType() == 2 && oConvertUtils.isNotEmpty((Object)(string = onlCgformHead.getSubTableStr()))) {
            String[] arrstring;
            for (String string4 : arrstring = string.split(",")) {
                String string5;
                OnlCgformHead onlCgformHead2 = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string4));
                if (onlCgformHead2 == null) continue;
                List list = this.fieldService.list((Wrapper)new LambdaQueryWrapper().eq(OnlCgformField::getCgformHeadId, (Object)onlCgformHead2.getId()));
                String string6 = "";
                String string7 = null;
                for (OnlCgformField onlCgformField : list) {
                    if (oConvertUtils.isEmpty((Object)onlCgformField.getMainField())) continue;
                    string6 = onlCgformField.getDbFieldName();
                    string5 = onlCgformField.getMainField();
                    if (json.get((Object)string5.toLowerCase()) != null) {
                        string7 = json.getString(string5.toLowerCase());
                    }
                    if (json.get((Object)string5.toUpperCase()) == null) continue;
                    string7 = json.getString(string5.toUpperCase());
                }
                if (oConvertUtils.isEmpty(string7)) continue;
                this.fieldService.deleteAutoList(string4, string6, string7);
                JSONArray jSONArray = json.getJSONArray(string4);
                if (jSONArray == null || jSONArray.size() == 0) continue;
                for (int i2 = 0; i2 < jSONArray.size(); ++i2) {
                    string5 = jSONArray.getJSONObject(i2);
                    if (string7 != null) {
                        string5.put(string6, (Object)string7);
                    }
                    this.fieldService.saveFormData(list, string4, (JSONObject)string5);
                }
            }
        }
        this.executeEnhanceJava(string2, "end", onlCgformHead, json);
        this.executeEnhanceSql(string2, code, json);
        return string3;
    }

    @Override
    public int executeEnhanceJava(String buttonCode, String eventType, OnlCgformHead head, JSONObject json) throws BusinessException {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getActiveStatus, (Object)"1");
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getButtonCode, (Object)buttonCode);
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getCgformHeadId, (Object)head.getId());
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getEvent, (Object)eventType);
        OnlCgformEnhanceJava onlCgformEnhanceJava = (OnlCgformEnhanceJava)this.onlCgformEnhanceJavaMapper.selectOne((Wrapper)lambdaQueryWrapper);
        Object object = this.a(onlCgformEnhanceJava);
        if (object != null && object instanceof CgformEnhanceJavaInter) {
            CgformEnhanceJavaInter cgformEnhanceJavaInter = (CgformEnhanceJavaInter)object;
            return cgformEnhanceJavaInter.execute(head.getTableName(), json);
        }
        return 1;
    }

    @Override
    public void executeEnhanceExport(OnlCgformHead head, List<Map<String, Object>> dataList) throws BusinessException {
        this.executeEnhanceList(head, "export", dataList);
    }

    @Override
    public void executeEnhanceList(OnlCgformHead head, String buttonCode, List<Map<String, Object>> dataList) throws BusinessException {
        Object object;
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getActiveStatus, (Object)"1");
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getButtonCode, (Object)buttonCode);
        lambdaQueryWrapper.eq(OnlCgformEnhanceJava::getCgformHeadId, (Object)head.getId());
        List list = this.onlCgformEnhanceJavaMapper.selectList((Wrapper)lambdaQueryWrapper);
        if (list != null && list.size() > 0 && (object = this.a((OnlCgformEnhanceJava)list.get(0))) != null && object instanceof CgformEnhanceJavaListInter) {
            CgformEnhanceJavaListInter cgformEnhanceJavaListInter = (CgformEnhanceJavaListInter)object;
            cgformEnhanceJavaListInter.execute(head.getTableName(), dataList);
        }
    }

    private Object a(OnlCgformEnhanceJava onlCgformEnhanceJava) {
        if (onlCgformEnhanceJava != null) {
            String string = onlCgformEnhanceJava.getCgJavaType();
            String string2 = onlCgformEnhanceJava.getCgJavaValue();
            if (oConvertUtils.isNotEmpty((Object)string2)) {
                Object object = null;
                if ("class".equals(string)) {
                    try {
                        object = MyClassLoader.getClassByScn((String)string2).newInstance();
                    }
                    catch (InstantiationException instantiationException) {
                        a.error(instantiationException.getMessage(), (Throwable)instantiationException);
                    }
                    catch (IllegalAccessException illegalAccessException) {
                        a.error(illegalAccessException.getMessage(), (Throwable)illegalAccessException);
                    }
                } else if ("spring".equals(string)) {
                    object = SpringContextUtils.getBean((String)string2);
                }
                return object;
            }
        }
        return null;
    }

    @Override
    public void executeEnhanceSql(String buttonCode, String formId, JSONObject json) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformEnhanceSql::getButtonCode, (Object)buttonCode);
        lambdaQueryWrapper.eq(OnlCgformEnhanceSql::getCgformHeadId, (Object)formId);
        OnlCgformEnhanceSql onlCgformEnhanceSql = (OnlCgformEnhanceSql)this.onlCgformEnhanceSqlMapper.selectOne((Wrapper)lambdaQueryWrapper);
        if (onlCgformEnhanceSql != null && oConvertUtils.isNotEmpty((Object)onlCgformEnhanceSql.getCgbSql())) {
            String[] arrstring;
            String string = org.jeecg.modules.online.cgform.util.b.a(onlCgformEnhanceSql.getCgbSql(), json);
            for (String string2 : arrstring = string.split(";")) {
                if (string2 == null || string2.toLowerCase().trim().equals("")) continue;
                a.info(" online sql 增强： " + string2);
                ((OnlCgformHeadMapper)this.baseMapper).executeDDL(string2);
            }
        }
    }

    @Override
    public void executeCustomerButton(String buttonCode, String formId, String dataId) throws BusinessException {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById((Serializable)((Object)formId));
        if (onlCgformHead == null) {
            throw new BusinessException("未找到表配置信息");
        }
        Map<String, Object> map = ((OnlCgformHeadMapper)this.baseMapper).queryOneByTableNameAndId(org.jeecg.modules.online.cgform.util.b.f(onlCgformHead.getTableName()), dataId);
        JSONObject jSONObject = JSONObject.parseObject((String)JSON.toJSONString(map));
        this.executeEnhanceJava(buttonCode, "start", onlCgformHead, jSONObject);
        this.executeEnhanceSql(buttonCode, formId, jSONObject);
        this.executeEnhanceJava(buttonCode, "end", onlCgformHead, jSONObject);
    }

    @Override
    public List<OnlCgformButton> queryValidButtonList(String headId) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformButton::getCgformHeadId, (Object)headId);
        lambdaQueryWrapper.eq(OnlCgformButton::getButtonStatus, (Object)"1");
        lambdaQueryWrapper.orderByAsc(OnlCgformButton::getOrderNum);
        return this.onlCgformButtonMapper.selectList((Wrapper)lambdaQueryWrapper);
    }

    @Override
    public OnlCgformEnhanceJs queryEnhanceJs(String formId, String cgJsType) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformEnhanceJs::getCgformHeadId, (Object)formId);
        lambdaQueryWrapper.eq(OnlCgformEnhanceJs::getCgJsType, (Object)cgJsType);
        return (OnlCgformEnhanceJs)this.onlCgformEnhanceJsMapper.selectOne((Wrapper)lambdaQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void deleteOneTableInfo(String formId, String dataId) throws BusinessException {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getById((Serializable)((Object)formId));
        if (onlCgformHead == null) {
            throw new BusinessException("未找到表配置信息");
        }
        String string = org.jeecg.modules.online.cgform.util.b.f(onlCgformHead.getTableName());
        Map<String, Object> map = ((OnlCgformHeadMapper)this.baseMapper).queryOneByTableNameAndId(string, dataId);
        if (map == null) {
            throw new BusinessException("未找到数据信息");
        }
        Map<String, Object> map2 = org.jeecg.modules.online.cgform.util.b.b(map);
        String string2 = "delete";
        JSONObject jSONObject = JSONObject.parseObject((String)JSON.toJSONString(map2));
        this.executeEnhanceJava(string2, "start", onlCgformHead, jSONObject);
        this.updateParentNode(onlCgformHead, dataId);
        if (onlCgformHead.getTableType() == 2) {
            this.fieldService.deleteAutoListMainAndSub(onlCgformHead, dataId);
        } else {
            String string3 = "delete from " + string + " where id = '" + dataId + "'";
            ((OnlCgformHeadMapper)this.baseMapper).deleteOne(string3);
        }
        this.executeEnhanceSql(string2, formId, jSONObject);
        this.executeEnhanceJava(string2, "end", onlCgformHead, jSONObject);
    }

    @Override
    @Deprecated
    public JSONObject queryFormItem(OnlCgformHead head, String username) {
        String string;
        Object object;
        List<OnlCgformField> list = this.fieldService.queryAvailableFields(head.getId(), head.getTableName(), head.getTaskId(), false);
        ArrayList<String> arrayList = new ArrayList<String>();
        if (oConvertUtils.isEmpty((Object)head.getTaskId())) {
            object = this.onlAuthPageService.queryFormDisabledCode(head.getId());
            if (object != null && object.size() > 0 && object.get(0) != null) {
                arrayList.addAll((Collection<String>)object);
            }
        } else {
            object = this.fieldService.queryDisabledFields(head.getTableName(), head.getTaskId());
            if (object != null && object.size() > 0 && object.get(0) != null) {
                arrayList.addAll((Collection<String>)object);
            }
        }
        object = org.jeecg.modules.online.cgform.util.b.a(list, arrayList, null);
        if (head.getTableType() == 2 && oConvertUtils.isNotEmpty((Object)(string = head.getSubTableStr()))) {
            for (String string2 : string.split(",")) {
                OnlCgformHead onlCgformHead = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string2));
                if (onlCgformHead == null) continue;
                List<OnlCgformField> list2 = this.fieldService.queryAvailableFields(onlCgformHead.getId(), onlCgformHead.getTableName(), head.getTaskId(), false);
                List<Object> list3 = new ArrayList();
                list3 = oConvertUtils.isNotEmpty((Object)head.getTaskId()) ? this.fieldService.queryDisabledFields(onlCgformHead.getTableName(), head.getTaskId()) : this.onlAuthPageService.queryFormDisabledCode(onlCgformHead.getId());
                JSONObject jSONObject = new JSONObject();
                if (1 == onlCgformHead.getRelationType()) {
                    jSONObject = org.jeecg.modules.online.cgform.util.b.a(list2, list3, null);
                } else {
                    jSONObject.put("columns", (Object)org.jeecg.modules.online.cgform.util.b.a(list2, list3));
                }
                jSONObject.put("relationType", (Object)onlCgformHead.getRelationType());
                jSONObject.put("view", (Object)"tab");
                jSONObject.put("order", (Object)onlCgformHead.getTabOrderNum());
                jSONObject.put("formTemplate", (Object)onlCgformHead.getFormTemplate());
                jSONObject.put("describe", (Object)onlCgformHead.getTableTxt());
                jSONObject.put("key", (Object)onlCgformHead.getTableName());
                object.getJSONObject("properties").put(onlCgformHead.getTableName(), (Object)jSONObject);
            }
        }
        return object;
    }

    @Override
    public List<String> generateCode(OnlGenerateModel model) throws Exception {
        TableVo tableVo = new TableVo();
        tableVo.setEntityName(model.getEntityName());
        tableVo.setEntityPackage(model.getEntityPackage());
        tableVo.setFtlDescription(model.getFtlDescription());
        tableVo.setTableName(model.getTableName());
        tableVo.setSearchFieldNum(Integer.valueOf(-1));
        ArrayList<ColumnVo> arrayList = new ArrayList<ColumnVo>();
        ArrayList<ColumnVo> arrayList2 = new ArrayList<ColumnVo>();
        this.a(model.getCode(), arrayList, arrayList2);
        OnlCgformHead onlCgformHead = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getId, (Object)model.getCode()));
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("scroll", onlCgformHead.getScroll() == null ? "0" : onlCgformHead.getScroll().toString());
        String string = onlCgformHead.getFormTemplate();
        if (oConvertUtils.isEmpty((Object)string)) {
            tableVo.setFieldRowNum(Integer.valueOf(1));
        } else {
            tableVo.setFieldRowNum(Integer.valueOf(Integer.parseInt(string)));
        }
        if ("Y".equals(onlCgformHead.getIsTree())) {
            hashMap.put("pidField", onlCgformHead.getTreeParentIdField());
            hashMap.put("hasChildren", onlCgformHead.getTreeIdField());
            hashMap.put("textField", onlCgformHead.getTreeFieldname());
        }
        tableVo.setExtendParams(hashMap);
        CgformEnum cgformEnum = CgformEnum.getCgformEnumByConfig((String)model.getJspMode());
        ArrayList<String> arrayList3 = new CodeGenerateOne(tableVo, arrayList, arrayList2).generateCodeFile(model.getProjectPath(), cgformEnum.getTemplatePath(), cgformEnum.getStylePath());
        if (arrayList3 == null || arrayList3.size() == 0) {
            arrayList3 = new ArrayList<String>();
            arrayList3.add(" :::::: 生成失败ERROR提醒 :::::: ");
            arrayList3.add("1.未找到代码生成器模板，请确认路径是否含有中文或特殊字符！");
            arrayList3.add("2.如果是JAR包运行，请参考此文档 http://doc.jeecg.com/2043922");
        }
        return arrayList3;
    }

    @Override
    public List<String> generateOneToMany(OnlGenerateModel model) throws Exception {
        MainTableVo mainTableVo = new MainTableVo();
        mainTableVo.setEntityName(model.getEntityName());
        mainTableVo.setEntityPackage(model.getEntityPackage());
        mainTableVo.setFtlDescription(model.getFtlDescription());
        mainTableVo.setTableName(model.getTableName());
        OnlCgformHead onlCgformHead = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getId, (Object)model.getCode()));
        String string = onlCgformHead.getFormTemplate();
        if (oConvertUtils.isEmpty((Object)string)) {
            mainTableVo.setFieldRowNum(Integer.valueOf(1));
        } else {
            mainTableVo.setFieldRowNum(Integer.valueOf(Integer.parseInt(string)));
        }
        ArrayList<ColumnVo> arrayList = new ArrayList<ColumnVo>();
        ArrayList<ColumnVo> arrayList2 = new ArrayList<ColumnVo>();
        this.a(model.getCode(), arrayList, arrayList2);
        List<OnlGenerateModel> list = model.getSubList();
        ArrayList<SubTableVo> arrayList3 = new ArrayList<SubTableVo>();
        for (OnlGenerateModel object2 : list) {
            OnlCgformHead onlCgformHead2 = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)object2.getTableName()));
            if (onlCgformHead2 == null) continue;
            SubTableVo subTableVo = new SubTableVo();
            subTableVo.setEntityName(object2.getEntityName());
            subTableVo.setEntityPackage(model.getEntityPackage());
            subTableVo.setTableName(object2.getTableName());
            subTableVo.setFtlDescription(object2.getFtlDescription());
            Integer n2 = onlCgformHead2.getRelationType();
            subTableVo.setForeignRelationType(n2 == 1 ? "1" : "0");
            ArrayList<ColumnVo> arrayList4 = new ArrayList<ColumnVo>();
            ArrayList<ColumnVo> arrayList5 = new ArrayList<ColumnVo>();
            OnlCgformField onlCgformField = this.a(onlCgformHead2.getId(), arrayList4, arrayList5);
            if (onlCgformField == null) continue;
            subTableVo.setOriginalForeignKeys(new String[]{onlCgformField.getDbFieldName()});
            subTableVo.setForeignKeys(new String[]{onlCgformField.getDbFieldName()});
            subTableVo.setColums(arrayList4);
            subTableVo.setOriginalColumns(arrayList5);
            arrayList3.add(subTableVo);
        }
        CgformEnum cgformEnum = CgformEnum.getCgformEnumByConfig((String)model.getJspMode());
        List list2 = new CodeGenerateOneToMany(mainTableVo, arrayList, arrayList2, arrayList3).generateCodeFile(model.getProjectPath(), cgformEnum.getTemplatePath(), cgformEnum.getStylePath());
        return list2;
    }

    private OnlCgformField a(String string, List<ColumnVo> list, List<ColumnVo> list2) {
        LambdaQueryWrapper lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(OnlCgformField::getCgformHeadId, (Object)string);
        lambdaQueryWrapper.orderByAsc(OnlCgformField::getOrderNum);
        List list3 = this.fieldService.list((Wrapper)lambdaQueryWrapper);
        OnlCgformField onlCgformField = null;
        for (OnlCgformField onlCgformField2 : list3) {
            JSONObject jSONObject;
            if (oConvertUtils.isNotEmpty((Object)onlCgformField2.getMainTable())) {
                onlCgformField = onlCgformField2;
            }
            ColumnVo columnVo = new ColumnVo();
            columnVo.setFieldLength(onlCgformField2.getFieldLength());
            columnVo.setFieldHref(onlCgformField2.getFieldHref());
            columnVo.setFieldValidType(onlCgformField2.getFieldValidType());
            columnVo.setFieldDefault(onlCgformField2.getDbDefaultVal());
            columnVo.setFieldShowType(onlCgformField2.getFieldShowType());
            columnVo.setFieldOrderNum(onlCgformField2.getOrderNum());
            columnVo.setIsKey(onlCgformField2.getDbIsKey() == 1 ? "Y" : "N");
            columnVo.setIsShow(onlCgformField2.getIsShowForm() == 1 ? "Y" : "N");
            columnVo.setIsShowList(onlCgformField2.getIsShowList() == 1 ? "Y" : "N");
            columnVo.setIsQuery(onlCgformField2.getIsQuery() == 1 ? "Y" : "N");
            columnVo.setQueryMode(onlCgformField2.getQueryMode());
            columnVo.setDictField(onlCgformField2.getDictField());
            if (oConvertUtils.isNotEmpty((Object)onlCgformField2.getDictTable()) && onlCgformField2.getDictTable().indexOf("where") > 0) {
                columnVo.setDictTable(onlCgformField2.getDictTable().split("where")[0].trim());
            } else {
                columnVo.setDictTable(onlCgformField2.getDictTable());
            }
            columnVo.setDictText(onlCgformField2.getDictText());
            columnVo.setFieldDbName(onlCgformField2.getDbFieldName());
            columnVo.setFieldName(oConvertUtils.camelName((String)onlCgformField2.getDbFieldName()));
            columnVo.setFiledComment(onlCgformField2.getDbFieldTxt());
            columnVo.setFieldDbType(onlCgformField2.getDbType());
            columnVo.setFieldType(this.a(onlCgformField2.getDbType()));
            columnVo.setClassType(onlCgformField2.getFieldShowType());
            columnVo.setClassType_row(onlCgformField2.getFieldShowType());
            if (onlCgformField2.getDbIsNull() == 0 || "*".equals(onlCgformField2.getFieldValidType()) || "1".equals(onlCgformField2.getFieldMustInput())) {
                columnVo.setNullable("N");
            } else {
                columnVo.setNullable("Y");
            }
            if ("switch".equals(onlCgformField2.getFieldShowType())) {
                if (oConvertUtils.isNotEmpty((Object)onlCgformField2.getFieldExtendJson())) {
                    columnVo.setDictField(onlCgformField2.getFieldExtendJson());
                } else {
                    columnVo.setDictField("is_open");
                }
            }
            HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
            if (StringUtils.isNotBlank((String)onlCgformField2.getFieldExtendJson())) {
                try {
                    JSONObject jSONObject2 = JSONObject.parseObject((String)onlCgformField2.getFieldExtendJson());
                    if (jSONObject2 != null) {
                        hashMap.putAll(jSONObject2.getInnerMap());
                    }
                }
                catch (JSONException jSONException) {
                    // empty catch block
                }
            }
            columnVo.setExtendParams(hashMap);
            if ("popup".equals(onlCgformField2.getFieldShowType())) {
                boolean bl = true;
                Object v = hashMap.get("popupMulti");
                if (v != null) {
                    bl = (Boolean)v;
                }
                hashMap.put("popupMulti", bl);
            }
            columnVo.setSort("1".equals(onlCgformField2.getSortFlag()) ? "Y" : "N");
            columnVo.setReadonly(Integer.valueOf(1).equals(onlCgformField2.getIsReadOnly()) ? "Y" : "N");
            if (oConvertUtils.isNotEmpty((Object)onlCgformField2.getFieldDefaultValue()) && !onlCgformField2.getFieldDefaultValue().trim().startsWith("${") && !onlCgformField2.getFieldDefaultValue().trim().startsWith("#{") && !onlCgformField2.getFieldDefaultValue().trim().startsWith("{{")) {
                columnVo.setDefaultVal(onlCgformField2.getFieldDefaultValue());
            }
            if (("file".equals(onlCgformField2.getFieldShowType()) || "image".equals(onlCgformField2.getFieldShowType())) && oConvertUtils.isNotEmpty((Object)onlCgformField2.getFieldExtendJson()) && oConvertUtils.isNotEmpty((Object)(jSONObject = JSONObject.parseObject((String)onlCgformField2.getFieldExtendJson())).getString("uploadnum"))) {
                columnVo.setUploadnum(jSONObject.getString("uploadnum"));
            }
            list2.add(columnVo);
            if (onlCgformField2.getIsShowForm() != 1 && onlCgformField2.getIsShowList() != 1 && onlCgformField2.getIsQuery() != 1) continue;
            list.add(columnVo);
        }
        return onlCgformField;
    }

    private String a(String string) {
        if ((string = string.toLowerCase()).indexOf("int") >= 0) {
            return "java.lang.Integer";
        }
        if (string.indexOf("double") >= 0) {
            return "java.lang.Double";
        }
        if (string.indexOf("decimal") >= 0) {
            return "java.math.BigDecimal";
        }
        if (string.indexOf("date") >= 0) {
            return "java.util.Date";
        }
        return "java.lang.String";
    }

    @Override
    public void addCrazyFormData(String tbname, JSONObject json) throws DBException, UnsupportedEncodingException {
        String string;
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)tbname));
        if (onlCgformHead == null) {
            throw new DBException("数据库主表[" + tbname + "]不存在");
        }
        if (onlCgformHead.getTableType() == 2 && (string = onlCgformHead.getSubTableStr()) != null) {
            String[] arrstring;
            for (String string2 : arrstring = string.split(",")) {
                OnlCgformHead onlCgformHead2;
                JSONArray jSONArray;
                String string3 = json.getString("sub-table-design_" + string2);
                if (oConvertUtils.isEmpty((Object)string3) || (jSONArray = JSONArray.parseArray((String)URLDecoder.decode(string3, "UTF-8"))) == null || jSONArray.size() == 0 || (onlCgformHead2 = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string2))) == null) continue;
                List list = this.fieldService.list((Wrapper)new LambdaQueryWrapper().eq(OnlCgformField::getCgformHeadId, (Object)onlCgformHead2.getId()));
                String string4 = "";
                String string5 = null;
                for (OnlCgformField onlCgformField : list) {
                    if (oConvertUtils.isEmpty((Object)onlCgformField.getMainField())) continue;
                    string4 = onlCgformField.getDbFieldName();
                    String string6 = onlCgformField.getMainField();
                    string5 = json.getString(string6);
                }
                for (int i2 = 0; i2 < jSONArray.size(); ++i2) {
                    OnlCgformField onlCgformField;
                    onlCgformField = jSONArray.getJSONObject(i2);
                    if (string5 != null) {
                        onlCgformField.put(string4, string5);
                    }
                    this.fieldService.executeInsertSQL(org.jeecg.modules.online.cgform.util.b.c(string2, list, (JSONObject)onlCgformField));
                }
            }
        }
        this.fieldService.saveFormData(onlCgformHead.getId(), tbname, json, true);
    }

    @Override
    public void editCrazyFormData(String tbname, JSONObject json) throws DBException, UnsupportedEncodingException {
        OnlCgformHead onlCgformHead = (OnlCgformHead)this.getOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)tbname));
        if (onlCgformHead == null) {
            throw new DBException("数据库主表[" + tbname + "]不存在");
        }
        if (onlCgformHead.getTableType() == 2) {
            String[] arrstring;
            String string = onlCgformHead.getSubTableStr();
            for (String string2 : arrstring = string.split(",")) {
                OnlCgformField onlCgformField2;
                OnlCgformHead onlCgformHead2 = (OnlCgformHead)((OnlCgformHeadMapper)this.baseMapper).selectOne((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getTableName, (Object)string2));
                if (onlCgformHead2 == null) continue;
                List list = this.fieldService.list((Wrapper)new LambdaQueryWrapper().eq(OnlCgformField::getCgformHeadId, (Object)onlCgformHead2.getId()));
                String string3 = "";
                String string4 = null;
                for (OnlCgformField onlCgformField2 : list) {
                    if (oConvertUtils.isEmpty((Object)onlCgformField2.getMainField())) continue;
                    string3 = onlCgformField2.getDbFieldName();
                    String string5 = onlCgformField2.getMainField();
                    string4 = json.getString(string5);
                }
                if (oConvertUtils.isEmpty(string4)) continue;
                this.fieldService.deleteAutoList(string2, string3, string4);
                String string6 = json.getString("sub-table-design_" + string2);
                if (oConvertUtils.isEmpty((Object)string6) || (onlCgformField2 = JSONArray.parseArray((String)URLDecoder.decode(string6, "UTF-8"))) == null || onlCgformField2.size() == 0) continue;
                for (int i2 = 0; i2 < onlCgformField2.size(); ++i2) {
                    JSONObject jSONObject = onlCgformField2.getJSONObject(i2);
                    if (string4 != null) {
                        jSONObject.put(string3, (Object)string4);
                    }
                    this.fieldService.executeInsertSQL(org.jeecg.modules.online.cgform.util.b.c(string2, list, jSONObject));
                }
            }
        }
        this.fieldService.editFormData(onlCgformHead.getId(), tbname, json, true);
    }

    @Override
    public Integer getMaxCopyVersion(String physicId) {
        Integer n2 = ((OnlCgformHeadMapper)this.baseMapper).getMaxCopyVersion(physicId);
        return n2 == null ? 0 : n2;
    }

    @Override
    public void copyOnlineTableConfig(OnlCgformHead physicTable) throws Exception {
        String string = physicTable.getId();
        OnlCgformHead onlCgformHead = new OnlCgformHead();
        String string2 = UUIDGenerator.generate();
        onlCgformHead.setId(string2);
        onlCgformHead.setPhysicId(string);
        onlCgformHead.setCopyType(1);
        onlCgformHead.setCopyVersion(physicTable.getTableVersion());
        onlCgformHead.setTableVersion(1);
        onlCgformHead.setTableName(this.b(string, physicTable.getTableName()));
        onlCgformHead.setTableTxt(physicTable.getTableTxt());
        onlCgformHead.setFormCategory(physicTable.getFormCategory());
        onlCgformHead.setFormTemplate(physicTable.getFormTemplate());
        onlCgformHead.setFormTemplateMobile(physicTable.getFormTemplateMobile());
        onlCgformHead.setIdSequence(physicTable.getIdSequence());
        onlCgformHead.setIdType(physicTable.getIdType());
        onlCgformHead.setIsCheckbox(physicTable.getIsCheckbox());
        onlCgformHead.setIsPage(physicTable.getIsPage());
        onlCgformHead.setIsTree(physicTable.getIsTree());
        onlCgformHead.setQueryMode(physicTable.getQueryMode());
        onlCgformHead.setTableType(1);
        onlCgformHead.setIsDbSynch("N");
        onlCgformHead.setIsDesForm(physicTable.getIsDesForm());
        onlCgformHead.setDesFormCode(physicTable.getDesFormCode());
        onlCgformHead.setTreeParentIdField(physicTable.getTreeParentIdField());
        onlCgformHead.setTreeFieldname(physicTable.getTreeFieldname());
        onlCgformHead.setTreeIdField(physicTable.getTreeIdField());
        onlCgformHead.setRelationType(null);
        onlCgformHead.setTabOrderNum(null);
        onlCgformHead.setSubTableStr(null);
        onlCgformHead.setThemeTemplate(physicTable.getThemeTemplate());
        onlCgformHead.setScroll(physicTable.getScroll());
        List list = this.fieldService.list((Wrapper)new LambdaQueryWrapper().eq(OnlCgformField::getCgformHeadId, (Object)string));
        for (OnlCgformField onlCgformField : list) {
            OnlCgformField onlCgformField2 = new OnlCgformField();
            onlCgformField2.setCgformHeadId(string2);
            this.a(onlCgformField, onlCgformField2);
            this.fieldService.save(onlCgformField2);
        }
        ((OnlCgformHeadMapper)this.baseMapper).insert(onlCgformHead);
    }

    @Override
    public void initCopyState(List<OnlCgformHead> headList) {
        List<String> list = ((OnlCgformHeadMapper)this.baseMapper).queryCopyPhysicId();
        for (OnlCgformHead onlCgformHead : headList) {
            if (list.contains(onlCgformHead.getId())) {
                onlCgformHead.setHascopy(1);
                continue;
            }
            onlCgformHead.setHascopy(0);
        }
    }

    @Override
    public void deleteBatch(String ids, String flag) {
        String[] arrstring = ids.split(",");
        if ("1".equals(flag)) {
            for (String string : arrstring) {
                try {
                    this.deleteRecordAndTable(string);
                }
                catch (DBException dBException) {
                    dBException.printStackTrace();
                }
                catch (SQLException sQLException) {
                    sQLException.printStackTrace();
                }
            }
        } else {
            this.removeByIds(Arrays.asList(arrstring));
        }
    }

    @Override
    public void updateParentNode(OnlCgformHead head, String dataId) {
        if ("Y".equals(head.getIsTree())) {
            Integer n2;
            String string = org.jeecg.modules.online.cgform.util.b.f(head.getTableName());
            String string2 = head.getTreeParentIdField();
            Map<String, Object> map = ((OnlCgformHeadMapper)this.baseMapper).queryOneByTableNameAndId(string, dataId);
            String string3 = null;
            if (map.get(string2) != null && !"0".equals(map.get(string2))) {
                string3 = map.get(string2).toString();
            } else if (map.get(string2.toUpperCase()) != null && !"0".equals(map.get(string2.toUpperCase()))) {
                string3 = map.get(string2.toUpperCase()).toString();
            }
            if (string3 != null && (n2 = ((OnlCgformHeadMapper)this.baseMapper).queryChildNode(string, string2, string3)) == 1) {
                String string4 = head.getTreeIdField();
                this.fieldService.updateTreeNodeNoChild(string, string4, string3);
            }
        }
    }

    private void b(OnlCgformHead onlCgformHead, List<OnlCgformField> list) {
        List list2 = this.list((Wrapper)new LambdaQueryWrapper().eq(OnlCgformHead::getPhysicId, (Object)onlCgformHead.getId()));
        if (list2 != null && list2.size() > 0) {
            for (OnlCgformHead onlCgformHead2 : list2) {
                Object object;
                Object object22;
                ArrayList arrayList2;
                List list3 = this.fieldService.list((Wrapper)new LambdaQueryWrapper().eq(OnlCgformField::getCgformHeadId, (Object)onlCgformHead2.getId()));
                if (list3 == null || list3.size() == 0) {
                    for (OnlCgformField onlCgformField : list) {
                        arrayList2 = new OnlCgformField();
                        ((OnlCgformField)((Object)arrayList2)).setCgformHeadId(onlCgformHead2.getId());
                        this.a(onlCgformField, (OnlCgformField)((Object)arrayList2));
                        this.fieldService.save(arrayList2);
                    }
                    continue;
                }
                HashMap hashMap3 = new HashMap();
                for (ArrayList arrayList2 : list3) {
                    hashMap3.put(((OnlCgformField)((Object)arrayList2)).getDbFieldName(), 1);
                }
                HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
                for (OnlCgformField onlCgformField : list) {
                    hashMap.put(onlCgformField.getDbFieldName(), 1);
                }
                arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                for (Object object22 : hashMap.keySet()) {
                    if (hashMap3.get(object22) == null) {
                        arrayList3.add(object22);
                        continue;
                    }
                    arrayList2.add(object22);
                }
                ArrayList arrayList4 = new ArrayList();
                object22 = hashMap3.keySet().iterator();
                while (object22.hasNext()) {
                    object = (String)object22.next();
                    if (hashMap.get(object) != null) continue;
                    arrayList4.add(object);
                }
                if (arrayList4.size() > 0) {
                    object22 = list3.iterator();
                    while (object22.hasNext()) {
                        object = (OnlCgformField)object22.next();
                        if (!arrayList4.contains(((OnlCgformField)object).getDbFieldName())) continue;
                        this.fieldService.removeById((Serializable)((Object)((OnlCgformField)object).getId()));
                    }
                }
                if (arrayList3.size() > 0) {
                    object22 = list.iterator();
                    while (object22.hasNext()) {
                        object = (OnlCgformField)object22.next();
                        if (!arrayList3.contains(((OnlCgformField)object).getDbFieldName())) continue;
                        OnlCgformField onlCgformField = new OnlCgformField();
                        onlCgformField.setCgformHeadId(onlCgformHead2.getId());
                        this.a((OnlCgformField)object, onlCgformField);
                        this.fieldService.save(onlCgformField);
                    }
                }
                if (arrayList2.size() <= 0) continue;
                object22 = arrayList2.iterator();
                while (object22.hasNext()) {
                    object = (String)object22.next();
                    this.b((String)object, list, list3);
                }
            }
        }
    }

    private void b(String string, List<OnlCgformField> list, List<OnlCgformField> list2) {
        OnlCgformField object = null;
        for (OnlCgformField object2 : list) {
            if (!string.equals(object2.getDbFieldName())) continue;
            object = object2;
        }
        Object object3 = null;
        for (OnlCgformField onlCgformField : list2) {
            if (!string.equals(onlCgformField.getDbFieldName())) continue;
            object3 = onlCgformField;
        }
        if (object != null && object3 != null) {
            boolean bl;
            boolean bl2 = false;
            if (!object.getDbType().equals(((OnlCgformField)object3).getDbType())) {
                ((OnlCgformField)object3).setDbType(object.getDbType());
                boolean bl3 = true;
            }
            if (!object.getDbDefaultVal().equals(((OnlCgformField)object3).getDbDefaultVal())) {
                ((OnlCgformField)object3).setDbDefaultVal(object.getDbDefaultVal());
                boolean bl4 = true;
            }
            if (object.getDbLength() != ((OnlCgformField)object3).getDbLength()) {
                ((OnlCgformField)object3).setDbLength(object.getDbLength());
                boolean bl5 = true;
            }
            if (object.getDbIsNull() != ((OnlCgformField)object3).getDbIsNull()) {
                ((OnlCgformField)object3).setDbIsNull(object.getDbIsNull());
                bl = true;
            }
            if (bl) {
                this.fieldService.updateById(object3);
            }
        }
    }

    private void a(OnlCgformField onlCgformField, OnlCgformField onlCgformField2) {
        onlCgformField2.setDbDefaultVal(onlCgformField.getDbDefaultVal());
        onlCgformField2.setDbFieldName(onlCgformField.getDbFieldName());
        onlCgformField2.setDbFieldNameOld(onlCgformField.getDbFieldNameOld());
        onlCgformField2.setDbFieldTxt(onlCgformField.getDbFieldTxt());
        onlCgformField2.setDbIsKey(onlCgformField.getDbIsKey());
        onlCgformField2.setDbIsNull(onlCgformField.getDbIsNull());
        onlCgformField2.setDbLength(onlCgformField.getDbLength());
        onlCgformField2.setDbPointLength(onlCgformField.getDbPointLength());
        onlCgformField2.setDbType(onlCgformField.getDbType());
        onlCgformField2.setDictField(onlCgformField.getDictField());
        onlCgformField2.setDictTable(onlCgformField.getDictTable());
        onlCgformField2.setDictText(onlCgformField.getDictText());
        onlCgformField2.setFieldExtendJson(onlCgformField.getFieldExtendJson());
        onlCgformField2.setFieldHref(onlCgformField.getFieldHref());
        onlCgformField2.setFieldLength(onlCgformField.getFieldLength());
        onlCgformField2.setFieldMustInput(onlCgformField.getFieldMustInput());
        onlCgformField2.setFieldShowType(onlCgformField.getFieldShowType());
        onlCgformField2.setFieldValidType(onlCgformField.getFieldValidType());
        onlCgformField2.setFieldDefaultValue(onlCgformField.getFieldDefaultValue());
        onlCgformField2.setIsQuery(onlCgformField.getIsQuery());
        onlCgformField2.setIsShowForm(onlCgformField.getIsShowForm());
        onlCgformField2.setIsShowList(onlCgformField.getIsShowList());
        onlCgformField2.setMainField(null);
        onlCgformField2.setMainTable(null);
        onlCgformField2.setOrderNum(onlCgformField.getOrderNum());
        onlCgformField2.setQueryMode(onlCgformField.getQueryMode());
        onlCgformField2.setIsReadOnly(onlCgformField.getIsReadOnly());
        onlCgformField2.setSortFlag(onlCgformField.getSortFlag());
        onlCgformField2.setQueryDefVal(onlCgformField.getQueryDefVal());
        onlCgformField2.setQueryConfigFlag(onlCgformField.getQueryConfigFlag());
        onlCgformField2.setQueryDictField(onlCgformField.getQueryDictField());
        onlCgformField2.setQueryDictTable(onlCgformField.getQueryDictTable());
        onlCgformField2.setQueryDictText(onlCgformField.getQueryDictText());
        onlCgformField2.setQueryMustInput(onlCgformField.getQueryMustInput());
        onlCgformField2.setQueryShowType(onlCgformField.getQueryShowType());
        onlCgformField2.setQueryValidType(onlCgformField.getQueryValidType());
        onlCgformField2.setConverter(onlCgformField.getConverter());
    }

    private void a(OnlCgformField onlCgformField) {
        if ("Text".equals(onlCgformField.getDbType()) || "Blob".equals(onlCgformField.getDbType())) {
            onlCgformField.setDbLength(0);
            onlCgformField.setDbPointLength(0);
        }
    }

    private String b(String string, String string2) {
        List<String> list = ((OnlCgformHeadMapper)this.baseMapper).queryAllCopyTableName(string);
        int n2 = 0;
        if (list != null || list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); ++i2) {
                String string3 = list.get(i2);
                int n3 = Integer.parseInt(string3.split("\\$")[1]);
                if (n3 <= n2) continue;
                n2 = n3;
            }
        }
        return string2 + "$" + ++n2;
    }


}

