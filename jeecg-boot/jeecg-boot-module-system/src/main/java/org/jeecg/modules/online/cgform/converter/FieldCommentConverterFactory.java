/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.jeecg.common.util.MyClassLoader
 *  org.jeecg.common.util.SpringContextUtils
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package org.jeecg.modules.online.cgform.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.MyClassLoader;
import org.jeecg.common.util.SpringContextUtils;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.converter.factory.b;
import org.jeecg.modules.online.cgform.converter.factory.a;
import org.jeecg.modules.online.cgform.converter.factory.c;
import org.jeecg.modules.online.cgform.converter.factory.d;
import org.jeecg.modules.online.cgform.converter.factory.e;
import org.jeecg.modules.online.cgform.converter.factory.f;
import org.jeecg.modules.online.cgform.converter.factory.g;
import org.jeecg.modules.online.cgform.converter.factory.h;
import org.jeecg.modules.online.cgform.converter.factory.i;
import org.jeecg.modules.online.cgform.converter.factory.j;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

@Slf4j
public class FieldCommentConverterFactory {
    private static final String b = "list";
    private static final String c = "radio";
    private static final String d = "checkbox";
    private static final String e = "list_multi";
    private static final String f = "sel_search";
    private static final String g = "sel_tree";
    private static final String h = "cat_tree";
    private static final String i = "link_down";
    private static final String j = "sel_depart";
    private static final String k = "sel_user";
    private static final String l = "pca";
    private static final String m = "switch";

    public static FieldCommentConverter FieldCommentConverterFactory(OnlCgformField onlCgformField) {
        String string = onlCgformField.getFieldShowType();
        FieldCommentConverter fieldCommentConverter = null;
        switch (string) {
            case "list":
            case "radio": {
                fieldCommentConverter = new c(onlCgformField);
                break;
            }
            case "list_multi":
            case "checkbox": {
                fieldCommentConverter = new f(onlCgformField);
                break;
            }
            case "sel_search": {
                fieldCommentConverter = new d(onlCgformField);
                break;
            }
            case "sel_tree": {
                fieldCommentConverter = new i(onlCgformField);
                break;
            }
            case "cat_tree": {
                fieldCommentConverter = new a(onlCgformField);
                break;
            }
            case "link_down": {
                fieldCommentConverter = new e(onlCgformField);
                break;
            }
            case "sel_depart": {
                fieldCommentConverter = new b(onlCgformField);
                break;
            }
            case "sel_user": {
                fieldCommentConverter = new j(onlCgformField);
                break;
            }
            case "pca": {
                fieldCommentConverter = new g(onlCgformField);
                break;
            }
            case "switch": {
                fieldCommentConverter = new h(onlCgformField);
                break;
            }
            default: {
                fieldCommentConverter = null;
            }
        }
        return fieldCommentConverter;
    }

    public static Map<String, FieldCommentConverter> FieldCommentConverterFactory(List<OnlCgformField> list) {
        HashMap<String, FieldCommentConverter> hashMap = new HashMap<String, FieldCommentConverter>();
        for (OnlCgformField onlCgformField : list) {
            FieldCommentConverter fieldCommentConverter = null;
            fieldCommentConverter = oConvertUtils.isNotEmpty(onlCgformField.getConverter()) ? FieldCommentConverterFactory.FieldCommentConverterFactory(onlCgformField.getConverter().trim()) : FieldCommentConverterFactory.FieldCommentConverterFactory(onlCgformField);
            if (fieldCommentConverter == null) continue;
            hashMap.put(onlCgformField.getDbFieldName().toLowerCase(), fieldCommentConverter);
        }
        return hashMap;
    }

    private static FieldCommentConverter FieldCommentConverterFactory(String string) {
        Object object = null;
        if (string.indexOf(".") > 0) {
            try {
                object = MyClassLoader.getClassByScn(string).newInstance();
            } catch (InstantiationException | IllegalAccessException instantiationException) {
                log.error(instantiationException.getMessage(), instantiationException);
            }
        } else {
            object = SpringContextUtils.getBean(string);
        }
        if (object != null && object instanceof FieldCommentConverter) {
            return (FieldCommentConverter) object;
        }
        return null;
    }
}

