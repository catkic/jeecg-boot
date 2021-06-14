/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.a;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class LinkDown {
    private String table;
    private String txt;
    private String key;
    private String linkField;
    private String idField;
    private String pidField;
    private String pidValue;
    private String condition;

    private String getQuerySql() {
        StringBuffer stringBuffer = new StringBuffer();
        String string = " ";
        stringBuffer.append("SELECT ");
        return null;
    }

}

