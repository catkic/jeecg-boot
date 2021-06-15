/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class TablePidModel {
    private String fieldName;
    private String tableName;
    private String codeField;
    private String textField;
    private String pidField;
    private String pidValue;
    private String hsaChildField;
}

