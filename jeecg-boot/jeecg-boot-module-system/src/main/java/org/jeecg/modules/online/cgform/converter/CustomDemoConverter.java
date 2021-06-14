/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.springframework.stereotype.Component
 */
package org.jeecg.modules.online.cgform.converter;

import java.util.Map;

import org.jeecg.modules.online.cgform.converter.FieldCommentConverter;
import org.springframework.stereotype.Component;

@Component(value = "customDemoConverter")
public class CustomDemoConverter implements FieldCommentConverter {
    @Override
    public String converterToVal(String txt) {
        if (txt != null && txt.equals("管理员1")) {
            return "admin";
        }
        return txt;
    }

    @Override
    public String converterToTxt(String val) {
        if (val != null) {
            if (val.equals("admin")) {
                return "管理员1";
            }
            if (val.equals("scott")) {
                return "管理员2";
            }
        }
        return val;
    }

    @Override
    public Map<String, String> getConfig() {
        return null;
    }
}

