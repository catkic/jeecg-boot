/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.model;

import java.util.List;

import lombok.*;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.jeecg.modules.online.cgform.entity.OnlCgformHead;
import org.jeecg.modules.online.cgform.entity.OnlCgformIndex;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OnlCgformModel {
    private OnlCgformHead head;
    private List<OnlCgformField> fields;
    private List<String> deleteFieldIds;
    private List<OnlCgformIndex> indexs;
    private List<String> deleteIndexIds;


}

