/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.config.db;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformIndex;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class TableConfig {
    private String tableName;
    private String isDbSynch;
    private String content;
    private String jformVersion;
    private Integer jformType;
    private String jformPkType;
    private String jformPkSequence;
    private Integer relationType;
    private String subTableStr;
    private Integer tabOrder;
    private List<OnlCgformField> columns;
    private List<OnlCgformIndex> indexes;
    private String treeParentIdFieldName;
    private String treeIdFieldname;
    private String treeFieldname;
    private DataBaseConfig dbConfig;

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIsDbSynch() {
        return this.isDbSynch;
    }

    public void setIsDbSynch(String isDbSynch) {
        this.isDbSynch = isDbSynch;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJformVersion() {
        return this.jformVersion;
    }

    public void setJformVersion(String jformVersion) {
        this.jformVersion = jformVersion;
    }

    public Integer getJformType() {
        return this.jformType;
    }

    public void setJformType(Integer jformType) {
        this.jformType = jformType;
    }

    public String getJformPkType() {
        return this.jformPkType;
    }

    public void setJformPkType(String jformPkType) {
        this.jformPkType = jformPkType;
    }

    public String getJformPkSequence() {
        return this.jformPkSequence;
    }

    public void setJformPkSequence(String jformPkSequence) {
        this.jformPkSequence = jformPkSequence;
    }

    public Integer getRelationType() {
        return this.relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public String getSubTableStr() {
        return this.subTableStr;
    }

    public void setSubTableStr(String subTableStr) {
        this.subTableStr = subTableStr;
    }

    public Integer getTabOrder() {
        return this.tabOrder;
    }

    public void setTabOrder(Integer tabOrder) {
        this.tabOrder = tabOrder;
    }

    public List<OnlCgformField> getColumns() {
        return this.columns;
    }

    public void setColumns(List<OnlCgformField> columns) {
        this.columns = columns;
    }

    public List<OnlCgformIndex> getIndexes() {
        return this.indexes;
    }

    public void setIndexes(List<OnlCgformIndex> indexes) {
        this.indexes = indexes;
    }

    public String getTreeParentIdFieldName() {
        return this.treeParentIdFieldName;
    }

    public void setTreeParentIdFieldName(String treeParentIdFieldName) {
        this.treeParentIdFieldName = treeParentIdFieldName;
    }

    public String getTreeIdFieldname() {
        return this.treeIdFieldname;
    }

    public void setTreeIdFieldname(String treeIdFieldname) {
        this.treeIdFieldname = treeIdFieldname;
    }

    public String getTreeFieldname() {
        return this.treeFieldname;
    }

    public void setTreeFieldname(String treeFieldname) {
        this.treeFieldname = treeFieldname;
    }

    public DataBaseConfig getDbConfig() {
        return this.dbConfig;
    }

    public void setDbConfig(DataBaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }
}

