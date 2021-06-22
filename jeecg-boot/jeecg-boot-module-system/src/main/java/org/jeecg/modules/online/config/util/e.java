/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.config.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class e {
    protected static Map<String, String> a = new HashMap<>();

    private static String a(String string, int n2) {
        String string2 = string;
        for (String s : a.keySet()) {
            String string3 = String.valueOf(s);
            String string4 = String.valueOf(a.get(string3));
            if (n2 == 1) {
                string2 = string.replaceAll(string3, string4);
                continue;
            }
            if (n2 != 2) continue;
            string2 = string.replaceAll(string4, string3);
        }
        return string2;
    }

    static {
        a.put("class", "clazz");
    }
}

