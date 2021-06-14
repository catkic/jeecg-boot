/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.converter;

import java.util.Map;

public interface FieldCommentConverter {
    public String converterToVal(String var1);

    public String converterToTxt(String var1);

    public Map<String, String> getConfig();
}

