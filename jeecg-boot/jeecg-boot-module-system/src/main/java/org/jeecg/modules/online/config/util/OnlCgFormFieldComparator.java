/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.config.util;

import java.util.Comparator;

import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class OnlCgFormFieldComparator implements Comparator<OnlCgformField> {

    @Override
    public int compare(OnlCgformField onlCgformField, OnlCgformField onlCgformField2) {
        if (onlCgformField == null || onlCgformField.getOrderNum() == null || onlCgformField2 == null || onlCgformField2.getOrderNum() == null) {
            return -1;
        }
        Integer n2 = onlCgformField.getOrderNum();
        Integer n3 = onlCgformField2.getOrderNum();
        return n2.compareTo(n3);
    }

}

