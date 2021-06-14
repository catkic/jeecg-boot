/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jeecg.common.system.api.ISysBaseAPI
 *  org.jeecg.common.system.vo.DictModel
 *  org.jeecg.common.util.SpringContextUtils
 *  org.jeecg.common.util.oConvertUtils
 */
package org.jeecg.modules.online.cgform.converter.field;

import java.util.List;
import java.util.Map;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.FieldCommentConverter;

public class StringModelConverter implements FieldCommentConverter {
    protected ISysBaseAPI a = (ISysBaseAPI)SpringContextUtils.getBean(ISysBaseAPI.class);
    protected String field;
    protected String table;
    protected String code;
    protected String text;

    public StringModelConverter() {
    }

    public StringModelConverter(String string, String string2, String string3) {
        this();
        this.table = string;
        this.code = string2;
        this.text = string3;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTable() {
        return this.table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String converterToVal(String txt) {
        if (oConvertUtils.isNotEmpty((Object)txt)) {
            String string = this.text + "= '" + txt + "'";
            String string2 = null;
            int n2 = this.table.indexOf("where");
            if (n2 > 0) {
                string2 = this.table.substring(0, n2).trim();
                string = string + " and " + this.table.substring(n2 + 5);
            } else {
                string2 = this.table;
            }
            List list = this.a.queryFilterTableDictInfo(string2, this.text, this.code, string);
            if (list != null && list.size() > 0) {
                return ((DictModel)list.get(0)).getValue();
            }
        }
        return null;
    }

    @Override
    public String converterToTxt(String val) {
        if (oConvertUtils.isNotEmpty((Object)val)) {
            String string = this.code + "= '" + val + "'";
            String string2 = null;
            int n2 = this.table.indexOf("where");
            if (n2 > 0) {
                string2 = this.table.substring(0, n2).trim();
                string = string + " and " + this.table.substring(n2 + 5);
            } else {
                string2 = this.table;
            }
            List list = this.a.queryFilterTableDictInfo(string2, this.text, this.code, string);
            if (list != null && list.size() > 0) {
                return ((DictModel)list.get(0)).getText();
            }
        }
        return null;
    }

    @Override
    public Map<String, String> getConfig() {
        return null;
    }
}

