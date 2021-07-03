/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.converter.factory;

import org.jeecg.modules.online.cgform.converter.field.StringModelConverter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class SelTreeCommentConverter extends StringModelConverter {
    public SelTreeCommentConverter(OnlCgformField onlCgformField) {
        String string = onlCgformField.getDictText();
        String[] arrstring = string.split(",");
        this.setTable(onlCgformField.getDictTable());
        this.setCode(arrstring[0]);
        this.setText(arrstring[2]);
    }
}

