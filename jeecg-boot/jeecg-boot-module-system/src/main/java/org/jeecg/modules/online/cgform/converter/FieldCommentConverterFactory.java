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
import org.jeecg.modules.online.cgform.converter.factory.DepartCommentConverter;
import org.jeecg.modules.online.cgform.converter.factory.CatTreeCommentConverter;
import org.jeecg.modules.online.cgform.converter.factory.SingleSelCommentConverter;
import org.jeecg.modules.online.cgform.converter.factory.SelSearchCommentConverter;
import org.jeecg.modules.online.cgform.converter.factory.LinkDownCommentConverter;
import org.jeecg.modules.online.cgform.converter.factory.MultiSelCommentConverter;
import org.jeecg.modules.online.cgform.converter.factory.PCACommentConverter;
import org.jeecg.modules.online.cgform.converter.factory.SwitchCommentConverter;
import org.jeecg.modules.online.cgform.converter.factory.SelTreeCommentConverter;
import org.jeecg.modules.online.cgform.converter.factory.UserCommentConverter;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;

@Slf4j
public class FieldCommentConverterFactory {
    private static final String FIELD_SHOW_TYPE_LIST = "list";
    private static final String FIELD_SHOW_TYPE_RADIO = "radio";
    private static final String FIELD_SHOW_TYPE_CHECKBOX = "checkbox";
    private static final String FIELD_SHOW_TYPE_LIST_MULTI = "list_multi";
    private static final String FIELD_SHOW_TYPE_SEL_SEARCH = "sel_search";
    private static final String FIELD_SHOW_TYPE_SEL_TREE = "sel_tree";
    private static final String FIELD_SHOW_TYPE_CAT_TREE = "cat_tree";
    private static final String FIELD_SHOW_TYPE_LINK_DOWN = "link_down";
    private static final String FIELD_SHOW_TYPE_SEL_DEPART = "sel_depart";
    private static final String FIELD_SHOW_TYPE_SEL_USER = "sel_user";
    private static final String FIELD_SHOW_TYPE_PCA = "pca";
    private static final String FIELD_SHOW_TYPE_SWITCH = "switch";

    public static FieldCommentConverter FieldCommentConverterFactory(OnlCgformField onlCgformField) {
        String string = onlCgformField.getFieldShowType();
        FieldCommentConverter fieldCommentConverter;
        switch (string) {
            case FIELD_SHOW_TYPE_LIST:
            case FIELD_SHOW_TYPE_RADIO: {
                fieldCommentConverter = new SingleSelCommentConverter(onlCgformField);
                break;
            }
            case FIELD_SHOW_TYPE_LIST_MULTI:
            case FIELD_SHOW_TYPE_CHECKBOX: {
                fieldCommentConverter = new MultiSelCommentConverter(onlCgformField);
                break;
            }
            case FIELD_SHOW_TYPE_SEL_SEARCH: {
                fieldCommentConverter = new SelSearchCommentConverter(onlCgformField);
                break;
            }
            case FIELD_SHOW_TYPE_SEL_TREE: {
                fieldCommentConverter = new SelTreeCommentConverter(onlCgformField);
                break;
            }
            case FIELD_SHOW_TYPE_CAT_TREE: {
                fieldCommentConverter = new CatTreeCommentConverter(onlCgformField);
                break;
            }
            case FIELD_SHOW_TYPE_LINK_DOWN: {
                fieldCommentConverter = new LinkDownCommentConverter(onlCgformField);
                break;
            }
            case FIELD_SHOW_TYPE_SEL_DEPART: {
                fieldCommentConverter = new DepartCommentConverter(onlCgformField);
                break;
            }
            case FIELD_SHOW_TYPE_SEL_USER: {
                fieldCommentConverter = new UserCommentConverter(onlCgformField);
                break;
            }
            case FIELD_SHOW_TYPE_PCA: {
                fieldCommentConverter = new PCACommentConverter(onlCgformField);
                break;
            }
            case FIELD_SHOW_TYPE_SWITCH: {
                fieldCommentConverter = new SwitchCommentConverter(onlCgformField);
                break;
            }
            default: {
                fieldCommentConverter = null;
            }
        }
        return fieldCommentConverter;
    }

    public static Map<String, FieldCommentConverter> FieldCommentConverterFactory(List<OnlCgformField> list) {
        Map<String, FieldCommentConverter> hashMap = new HashMap<>();
        for (OnlCgformField onlCgformField : list) {
            FieldCommentConverter fieldCommentConverter = oConvertUtils.isNotEmpty(onlCgformField.getConverter()) ? FieldCommentConverterFactory.FieldCommentConverterFactory(onlCgformField.getConverter().trim()) : FieldCommentConverterFactory.FieldCommentConverterFactory(onlCgformField);
            if (fieldCommentConverter != null) {
                hashMap.put(onlCgformField.getDbFieldName().toLowerCase(), fieldCommentConverter);
            }
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

