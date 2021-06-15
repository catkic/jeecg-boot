/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.model;

import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TableModel {
    private String field;
    private String table;
    private String key;


    public TableModel(String string, String string2) {
        this.key = string2;
        this.field = string;
    }
}

