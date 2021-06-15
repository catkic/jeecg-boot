/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  freemarker.template.Configuration
 *  freemarker.template.Template
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package org.jeecg.modules.online.config.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateUtil {
    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);

    /**
     * 猜测应该是传map进来渲染这个模板的
     * catkic
     * @param path
     * @param code
     * @param map
     * @return
     */
    public static String renderTemplate(String path, String code, Map<String, Object> map) {
        try {
            StringWriter stringWriter = new StringWriter();
            Template template = configuration.getTemplate(path, code);
            template.process(map, stringWriter);
            return stringWriter.toString();
        }
        catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return exception.toString();
        }
    }

    public static String renderTemplate(String path, Map<String, Object> map) {
        return TemplateUtil.renderTemplate(path, "utf-8", map);
    }

    static {
        configuration.setNumberFormat("0.#####################");
        configuration.setClassForTemplateLoading(TemplateUtil.class, "/");
    }
}

