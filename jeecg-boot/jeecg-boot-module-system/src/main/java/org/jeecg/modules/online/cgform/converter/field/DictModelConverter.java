/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jeecg.common.system.vo.DictModel
 *  org.jeecg.common.util.oConvertUtils
 */
package org.jeecg.modules.online.cgform.converter.field;

import java.util.List;
import java.util.Map;

import lombok.Data;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.FieldCommentConverter;

@Data
public class DictModelConverter implements FieldCommentConverter {
    protected String filed;
    protected List<DictModel> dictList;

    @Override
    public String converterToVal(String txt) {
        if (oConvertUtils.isNotEmpty((Object)txt)) {
            for (DictModel dictModel : this.dictList) {
                if (!dictModel.getText().equals(txt)) continue;
                return dictModel.getValue();
            }
        }
        return null;
    }

    @Override
    public String converterToTxt(String val) {
        if (oConvertUtils.isNotEmpty((Object)val)) {
            for (DictModel dictModel : this.dictList) {
                if (!dictModel.getValue().equals(val)) continue;
                return dictModel.getText();
            }
        }
        return null;
    }

    @Override
    public Map<String, String> getConfig() {
        return null;
    }
}

