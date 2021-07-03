/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.converter.factory;

import org.jeecg.modules.online.cgform.converter.field.StringModelConverter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class SelSearchCommentConverter
extends StringModelConverter {
    public SelSearchCommentConverter(OnlCgformField onlCgformField) {
        super(onlCgformField.getDictTable(), onlCgformField.getDictField(), onlCgformField.getDictText());
    }
}

