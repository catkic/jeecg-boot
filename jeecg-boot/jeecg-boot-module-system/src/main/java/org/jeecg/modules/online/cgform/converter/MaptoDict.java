/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jeecg.common.util.oConvertUtils
 */
package org.jeecg.modules.online.cgform.converter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

public class MaptoDict {
    public static final int a = 2;
    public static final int b = 1;

    public static void a(int n2, List<Map<String, Object>> list, List<OnlCgformField> list2) {
        Map<String, FieldCommentConverter> map = FieldCommentConverterFactory.FieldCommentConverterFactory(list2);
        for (Map<String, Object> map2 : list) {
            Iterator<Map.Entry<String, Object>> iterator = map2.entrySet().iterator();
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            while (iterator.hasNext()) {
                String string;
                FieldCommentConverter fieldCommentConverter;
                Map.Entry<String, Object> entry = iterator.next();
                Object object = entry.getValue();
                if (object == null || (fieldCommentConverter = map.get((string = (String)entry.getKey()).toLowerCase())) == null) continue;
                String string2 = object.toString();
                String string3 = n2 == 1 ? fieldCommentConverter.converterToTxt(string2) : fieldCommentConverter.converterToVal(string2);
                MaptoDict.a(fieldCommentConverter, map2, n2);
                MaptoDict.a(fieldCommentConverter, hashMap, string2);
                map2.put(string, string3);
            }
            for (Object object : hashMap.keySet()) {
                map2.put((String)object, hashMap.get(object));
            }
        }
    }

    private static void a(FieldCommentConverter fieldCommentConverter, Map<String, Object> map, int n2) {
        String string;
        Map<String, String> map2 = fieldCommentConverter.getConfig();
        if (map2 != null && oConvertUtils.isNotEmpty((Object)(string = map2.get("linkField")))) {
            for (String string2 : string.split(",")) {
                Object object = map.get(string2);
                if (object == null) continue;
                String string3 = object.toString();
                String string4 = n2 == 1 ? fieldCommentConverter.converterToTxt(string3) : fieldCommentConverter.converterToVal(string3);
                map.put(string2, string4);
            }
        }
    }

    private static void a(FieldCommentConverter fieldCommentConverter, Map<String, Object> map, String string) {
        String string2;
        Map<String, String> map2 = fieldCommentConverter.getConfig();
        if (map2 != null && oConvertUtils.isNotEmpty((Object)(string2 = map2.get("treeText")))) {
            map.put(string2, string);
        }
    }
}

