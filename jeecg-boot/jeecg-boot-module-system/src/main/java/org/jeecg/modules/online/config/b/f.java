/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.config.b;

import java.util.Comparator;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class f
implements Comparator<OnlCgformField> {
    public int a(OnlCgformField onlCgformField, OnlCgformField onlCgformField2) {
        if (onlCgformField == null || onlCgformField.getOrderNum() == null || onlCgformField2 == null || onlCgformField2.getOrderNum() == null) {
            return -1;
        }
        Integer n2 = onlCgformField.getOrderNum();
        Integer n3 = onlCgformField2.getOrderNum();
        return n2 < n3 ? -1 : (n2 == n3 ? 0 : 1);
    }

    @Override
    public /* synthetic */ int compare(Object object, Object object2) {
        return this.a((OnlCgformField)object, (OnlCgformField)object2);
    }
}

