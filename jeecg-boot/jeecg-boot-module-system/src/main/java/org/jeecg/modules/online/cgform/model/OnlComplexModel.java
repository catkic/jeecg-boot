/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jeecg.common.system.vo.DictModel
 */
package org.jeecg.modules.online.cgform.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.model.HrefSlots;
import org.jeecg.modules.online.cgform.model.OnlColumn;
import org.jeecg.modules.online.cgform.model.b;

public class OnlComplexModel
implements Serializable {
    private static final long b = 1L;
    private String c;
    private String d;
    private String e;
    private String f;
    private Integer g;
    private String h;
    private String i;
    private Integer j;
    private List<OnlColumn> k;
    private List<String> l;
    private Map<String, List<DictModel>> m = new HashMap<String, List<DictModel>>();
    private List<OnlCgformButton> n;
    List<HrefSlots> a;
    private String o;
    private List<b> p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private Integer v;

    public String getCode() {
        return this.c;
    }

    public String getFormTemplate() {
        return this.d;
    }

    public String getDescription() {
        return this.e;
    }

    public String getCurrentTableName() {
        return this.f;
    }

    public Integer getTableType() {
        return this.g;
    }

    public String getPaginationFlag() {
        return this.h;
    }

    public String getCheckboxFlag() {
        return this.i;
    }

    public Integer getScrollFlag() {
        return this.j;
    }

    public List<OnlColumn> getColumns() {
        return this.k;
    }

    public List<String> getHideColumns() {
        return this.l;
    }

    public Map<String, List<DictModel>> getDictOptions() {
        return this.m;
    }

    public List<OnlCgformButton> getCgButtonList() {
        return this.n;
    }

    public List<HrefSlots> getFieldHrefSlots() {
        return this.a;
    }

    public String getEnhanceJs() {
        return this.o;
    }

    public List<b> getForeignKeys() {
        return this.p;
    }

    public String getPidField() {
        return this.q;
    }

    public String getHasChildrenField() {
        return this.r;
    }

    public String getTextField() {
        return this.s;
    }

    public String getIsDesForm() {
        return this.t;
    }

    public String getDesFormCode() {
        return this.u;
    }

    public Integer getRelationType() {
        return this.v;
    }

    public void setCode(String code) {
        this.c = code;
    }

    public void setFormTemplate(String formTemplate) {
        this.d = formTemplate;
    }

    public void setDescription(String description) {
        this.e = description;
    }

    public void setCurrentTableName(String currentTableName) {
        this.f = currentTableName;
    }

    public void setTableType(Integer tableType) {
        this.g = tableType;
    }

    public void setPaginationFlag(String paginationFlag) {
        this.h = paginationFlag;
    }

    public void setCheckboxFlag(String checkboxFlag) {
        this.i = checkboxFlag;
    }

    public void setScrollFlag(Integer scrollFlag) {
        this.j = scrollFlag;
    }

    public void setColumns(List<OnlColumn> columns) {
        this.k = columns;
    }

    public void setHideColumns(List<String> hideColumns) {
        this.l = hideColumns;
    }

    public void setDictOptions(Map<String, List<DictModel>> dictOptions) {
        this.m = dictOptions;
    }

    public void setCgButtonList(List<OnlCgformButton> cgButtonList) {
        this.n = cgButtonList;
    }

    public void setFieldHrefSlots(List<HrefSlots> fieldHrefSlots) {
        this.a = fieldHrefSlots;
    }

    public void setEnhanceJs(String enhanceJs) {
        this.o = enhanceJs;
    }

    public void setForeignKeys(List<b> foreignKeys) {
        this.p = foreignKeys;
    }

    public void setPidField(String pidField) {
        this.q = pidField;
    }

    public void setHasChildrenField(String hasChildrenField) {
        this.r = hasChildrenField;
    }

    public void setTextField(String textField) {
        this.s = textField;
    }

    public void setIsDesForm(String isDesForm) {
        this.t = isDesForm;
    }

    public void setDesFormCode(String desFormCode) {
        this.u = desFormCode;
    }

    public void setRelationType(Integer relationType) {
        this.v = relationType;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OnlComplexModel)) {
            return false;
        }
        OnlComplexModel onlComplexModel = (OnlComplexModel)o;
        if (!onlComplexModel.a(this)) {
            return false;
        }
        Integer n2 = this.getTableType();
        Integer n3 = onlComplexModel.getTableType();
        if (n2 == null ? n3 != null : !((Object)n2).equals(n3)) {
            return false;
        }
        Integer n4 = this.getScrollFlag();
        Integer n5 = onlComplexModel.getScrollFlag();
        if (n4 == null ? n5 != null : !((Object)n4).equals(n5)) {
            return false;
        }
        Integer n6 = this.getRelationType();
        Integer n7 = onlComplexModel.getRelationType();
        if (n6 == null ? n7 != null : !((Object)n6).equals(n7)) {
            return false;
        }
        String string = this.getCode();
        String string2 = onlComplexModel.getCode();
        if (string == null ? string2 != null : !string.equals(string2)) {
            return false;
        }
        String string3 = this.getFormTemplate();
        String string4 = onlComplexModel.getFormTemplate();
        if (string3 == null ? string4 != null : !string3.equals(string4)) {
            return false;
        }
        String string5 = this.getDescription();
        String string6 = onlComplexModel.getDescription();
        if (string5 == null ? string6 != null : !string5.equals(string6)) {
            return false;
        }
        String string7 = this.getCurrentTableName();
        String string8 = onlComplexModel.getCurrentTableName();
        if (string7 == null ? string8 != null : !string7.equals(string8)) {
            return false;
        }
        String string9 = this.getPaginationFlag();
        String string10 = onlComplexModel.getPaginationFlag();
        if (string9 == null ? string10 != null : !string9.equals(string10)) {
            return false;
        }
        String string11 = this.getCheckboxFlag();
        String string12 = onlComplexModel.getCheckboxFlag();
        if (string11 == null ? string12 != null : !string11.equals(string12)) {
            return false;
        }
        List<OnlColumn> list = this.getColumns();
        List<OnlColumn> list2 = onlComplexModel.getColumns();
        if (list == null ? list2 != null : !((Object)list).equals(list2)) {
            return false;
        }
        List<String> list3 = this.getHideColumns();
        List<String> list4 = onlComplexModel.getHideColumns();
        if (list3 == null ? list4 != null : !((Object)list3).equals(list4)) {
            return false;
        }
        Map<String, List<DictModel>> map = this.getDictOptions();
        Map<String, List<DictModel>> map2 = onlComplexModel.getDictOptions();
        if (map == null ? map2 != null : !((Object)map).equals(map2)) {
            return false;
        }
        List<OnlCgformButton> list5 = this.getCgButtonList();
        List<OnlCgformButton> list6 = onlComplexModel.getCgButtonList();
        if (list5 == null ? list6 != null : !((Object)list5).equals(list6)) {
            return false;
        }
        List<HrefSlots> list7 = this.getFieldHrefSlots();
        List<HrefSlots> list8 = onlComplexModel.getFieldHrefSlots();
        if (list7 == null ? list8 != null : !((Object)list7).equals(list8)) {
            return false;
        }
        String string13 = this.getEnhanceJs();
        String string14 = onlComplexModel.getEnhanceJs();
        if (string13 == null ? string14 != null : !string13.equals(string14)) {
            return false;
        }
        List<b> list9 = this.getForeignKeys();
        List<b> list10 = onlComplexModel.getForeignKeys();
        if (list9 == null ? list10 != null : !((Object)list9).equals(list10)) {
            return false;
        }
        String string15 = this.getPidField();
        String string16 = onlComplexModel.getPidField();
        if (string15 == null ? string16 != null : !string15.equals(string16)) {
            return false;
        }
        String string17 = this.getHasChildrenField();
        String string18 = onlComplexModel.getHasChildrenField();
        if (string17 == null ? string18 != null : !string17.equals(string18)) {
            return false;
        }
        String string19 = this.getTextField();
        String string20 = onlComplexModel.getTextField();
        if (string19 == null ? string20 != null : !string19.equals(string20)) {
            return false;
        }
        String string21 = this.getIsDesForm();
        String string22 = onlComplexModel.getIsDesForm();
        if (string21 == null ? string22 != null : !string21.equals(string22)) {
            return false;
        }
        String string23 = this.getDesFormCode();
        String string24 = onlComplexModel.getDesFormCode();
        return !(string23 == null ? string24 != null : !string23.equals(string24));
    }

    protected boolean a(Object object) {
        return object instanceof OnlComplexModel;
    }

    public int hashCode() {
        int n2 = 59;
        int n3 = 1;
        Integer n4 = this.getTableType();
        n3 = n3 * 59 + (n4 == null ? 43 : ((Object)n4).hashCode());
        Integer n5 = this.getScrollFlag();
        n3 = n3 * 59 + (n5 == null ? 43 : ((Object)n5).hashCode());
        Integer n6 = this.getRelationType();
        n3 = n3 * 59 + (n6 == null ? 43 : ((Object)n6).hashCode());
        String string = this.getCode();
        n3 = n3 * 59 + (string == null ? 43 : string.hashCode());
        String string2 = this.getFormTemplate();
        n3 = n3 * 59 + (string2 == null ? 43 : string2.hashCode());
        String string3 = this.getDescription();
        n3 = n3 * 59 + (string3 == null ? 43 : string3.hashCode());
        String string4 = this.getCurrentTableName();
        n3 = n3 * 59 + (string4 == null ? 43 : string4.hashCode());
        String string5 = this.getPaginationFlag();
        n3 = n3 * 59 + (string5 == null ? 43 : string5.hashCode());
        String string6 = this.getCheckboxFlag();
        n3 = n3 * 59 + (string6 == null ? 43 : string6.hashCode());
        List<OnlColumn> list = this.getColumns();
        n3 = n3 * 59 + (list == null ? 43 : ((Object)list).hashCode());
        List<String> list2 = this.getHideColumns();
        n3 = n3 * 59 + (list2 == null ? 43 : ((Object)list2).hashCode());
        Map<String, List<DictModel>> map = this.getDictOptions();
        n3 = n3 * 59 + (map == null ? 43 : ((Object)map).hashCode());
        List<OnlCgformButton> list3 = this.getCgButtonList();
        n3 = n3 * 59 + (list3 == null ? 43 : ((Object)list3).hashCode());
        List<HrefSlots> list4 = this.getFieldHrefSlots();
        n3 = n3 * 59 + (list4 == null ? 43 : ((Object)list4).hashCode());
        String string7 = this.getEnhanceJs();
        n3 = n3 * 59 + (string7 == null ? 43 : string7.hashCode());
        List<b> list5 = this.getForeignKeys();
        n3 = n3 * 59 + (list5 == null ? 43 : ((Object)list5).hashCode());
        String string8 = this.getPidField();
        n3 = n3 * 59 + (string8 == null ? 43 : string8.hashCode());
        String string9 = this.getHasChildrenField();
        n3 = n3 * 59 + (string9 == null ? 43 : string9.hashCode());
        String string10 = this.getTextField();
        n3 = n3 * 59 + (string10 == null ? 43 : string10.hashCode());
        String string11 = this.getIsDesForm();
        n3 = n3 * 59 + (string11 == null ? 43 : string11.hashCode());
        String string12 = this.getDesFormCode();
        n3 = n3 * 59 + (string12 == null ? 43 : string12.hashCode());
        return n3;
    }

    public String toString() {
        return "OnlComplexModel(code=" + this.getCode() + ", formTemplate=" + this.getFormTemplate() + ", description=" + this.getDescription() + ", currentTableName=" + this.getCurrentTableName() + ", tableType=" + this.getTableType() + ", paginationFlag=" + this.getPaginationFlag() + ", checkboxFlag=" + this.getCheckboxFlag() + ", scrollFlag=" + this.getScrollFlag() + ", columns=" + this.getColumns() + ", hideColumns=" + this.getHideColumns() + ", dictOptions=" + this.getDictOptions() + ", cgButtonList=" + this.getCgButtonList() + ", fieldHrefSlots=" + this.getFieldHrefSlots() + ", enhanceJs=" + this.getEnhanceJs() + ", foreignKeys=" + this.getForeignKeys() + ", pidField=" + this.getPidField() + ", hasChildrenField=" + this.getHasChildrenField() + ", textField=" + this.getTextField() + ", isDesForm=" + this.getIsDesForm() + ", desFormCode=" + this.getDesFormCode() + ", relationType=" + this.getRelationType() + ")";
    }
}

