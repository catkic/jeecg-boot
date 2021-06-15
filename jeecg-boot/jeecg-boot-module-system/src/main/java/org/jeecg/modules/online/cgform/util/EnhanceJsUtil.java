/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  org.jeecg.common.util.oConvertUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package org.jeecg.modules.online.cgform.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.online.cgform.entity.OnlCgformButton;
import org.jeecg.modules.online.cgform.entity.OnlCgformEnhanceJs;
import org.jeecg.modules.online.cgform.entity.OnlCgformField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnhanceJsUtil {
    private static final Logger a = LoggerFactory.getLogger(EnhanceJsUtil.class);
    private static final String b = "beforeSubmit,beforeAdd,beforeEdit,afterAdd,afterEdit,beforeDelete,afterDelete,mounted,created,show,loaded";
    private static final String c = "\\}\\s*\r*\n*\\s*";
    private static final String d = ",";

    public static void main(String[] args) {
        System.out.println("123123" + null + "23232");

        String string = "aa(row){console.log(112);}\nbb(row){console.log(row);}";
        String string2 = "bb";
        System.out.println(EnhanceJsUtil.a(string, string2));
        ArrayList<OnlCgformButton> arrayList = new ArrayList<OnlCgformButton>();
        OnlCgformButton onlCgformButton = new OnlCgformButton();
        onlCgformButton.setButtonCode("bb");
        onlCgformButton.setButtonStyle("link");
        arrayList.add(onlCgformButton);
        OnlCgformButton onlCgformButton2 = new OnlCgformButton();
        onlCgformButton2.setButtonCode("aa");
        onlCgformButton2.setButtonStyle("link");
        arrayList.add(onlCgformButton2);
        System.out.println(EnhanceJsUtil.b(string, arrayList));
    }

    public static String a(String string, String string2) {
        String string3 = "(" + string2 + "\\s*\\(row\\)\\s*\\{)";
        String string4 = string2 + ":function(that,row){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;";
        String string5 = EnhanceJsUtil.b(string, c + string3, "}," + string4);
        string = string5 == null ? EnhanceJsUtil.c(string, string3, string4) : string5;
        string = EnhanceJsUtil.a(string, string2, null);
        return string;
    }

    public static String a(String string, String string2, String string3) {
        String string4 = "(" + oConvertUtils.getString((String) string3) + string2 + "\\s*\\(\\)\\s*\\{)";
        String string5 = string2 + ":function(that){const getAction=this._getAction,postAction=this._postAction,deleteAction=this._deleteAction;";
        String string6 = EnhanceJsUtil.b(string, c + string4, "}," + string5);
        string = string6 == null ? EnhanceJsUtil.c(string, string4, string5) : string6;
        return string;
    }

    public static String b(String string, String string2, String string3) {
        Pattern pattern = Pattern.compile(string2);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            string = string.replace(matcher.group(0), string3);
            return string;
        }
        return null;
    }

    public static String c(String string, String string2, String string3) {
        String string4 = EnhanceJsUtil.b(string, string2, string3);
        if (string4 != null) {
            return string4;
        }
        return string;
    }

    public static String a(String string, List<OnlCgformButton> list) {
        a.info("最终的增强JS", (Object) string);
        return "class OnlineEnhanceJs{constructor(getAction,postAction,deleteAction){this._getAction=getAction;this._postAction=postAction;this._deleteAction=deleteAction;}" + string + "}";
    }

    public static String b(String string, String string2) {
        String string3 = "(\\s+" + string2 + "\\s*\\(\\)\\s*\\{)";
        String string4 = string2 + ":function(that,event){";
        String string5 = EnhanceJsUtil.b(string, c + string3, "}," + string4);
        string = string5 == null ? EnhanceJsUtil.c(string, string3, string4) : string5;
        return string;
    }

    public static String a(String string) {
        String string2 = "function OnlineEnhanceJs(getAction,postAction,deleteAction){return {_getAction:getAction,_postAction:postAction,_deleteAction:deleteAction," + string + "}}";
        a.info("最终的增强JS", (Object) string2);
        return string2;
    }

    public static String b(String string, List<OnlCgformButton> list) {
        string = EnhanceJsUtil.c(string, list);
        String string2 = "function OnlineEnhanceJs(getAction,postAction,deleteAction){return {_getAction:getAction,_postAction:postAction,_deleteAction:deleteAction," + string + "}}";
        a.info("最终的增强JS", (Object) string2);
        return string2;
    }

    public static String c(String string, List<OnlCgformButton> list) {
        if (list != null) {
            for (OnlCgformButton onlCgformButton : list) {
                String string2 = onlCgformButton.getButtonCode();
                if ("link".equals(onlCgformButton.getButtonStyle())) {
                    string = EnhanceJsUtil.a(string, string2);
                    continue;
                }
                if (!"button".equals(onlCgformButton.getButtonStyle()) && !"form".equals(onlCgformButton.getButtonStyle()))
                    continue;
                string = EnhanceJsUtil.a(string, string2, null);
            }
        }
        for (String string3 : b.split(d)) {
            string = "beforeAdd,afterAdd,mounted,created,show,loaded".indexOf(string3) >= 0 ? EnhanceJsUtil.a(string, string3, null) : EnhanceJsUtil.a(string, string3);
        }
        return string;
    }

    public static void a(OnlCgformEnhanceJs onlCgformEnhanceJs, String string, List<OnlCgformField> list) {
        if (onlCgformEnhanceJs == null || oConvertUtils.isEmpty((Object) onlCgformEnhanceJs.getCgJs())) {
            return;
        }
        String string2 = " " + onlCgformEnhanceJs.getCgJs();
        a.debug("one enhanceJs begin==> " + string2);
        Pattern pattern = Pattern.compile("(\\s{1}onlChange\\s*\\(\\)\\s*\\{)");
        Matcher matcher = pattern.matcher(string2);
        if (matcher.find()) {
            a.debug("---JS 增强转换-main--enhanceJsFunctionName----onlChange");
            string2 = EnhanceJsUtil.a(string2, "onlChange", "\\s{1}");
            for (OnlCgformField onlCgformField : list) {
                string2 = EnhanceJsUtil.b(string2, onlCgformField.getDbFieldName());
            }
        }
        a.debug("one enhanceJs end==> " + string2);
        onlCgformEnhanceJs.setCgJs(string2);
    }

    public static void b(OnlCgformEnhanceJs onlCgformEnhanceJs, String string, List<OnlCgformField> list) {
        if (onlCgformEnhanceJs == null || oConvertUtils.isEmpty((Object) onlCgformEnhanceJs.getCgJs())) {
            return;
        }
        a.info(" sub enhanceJs begin==> " + onlCgformEnhanceJs);
        String string2 = onlCgformEnhanceJs.getCgJs();
        String string3 = string + "_" + "onlChange";
        Pattern pattern = Pattern.compile("(" + string3 + "\\s*\\(\\)\\s*\\{)");
        Matcher matcher = pattern.matcher(string2);
        if (matcher.find()) {
            a.info("---JS 增强转换-sub-- enhanceJsFunctionName----" + string3);
            string2 = EnhanceJsUtil.a(string2, string3, null);
            for (OnlCgformField onlCgformField : list) {
                string2 = EnhanceJsUtil.b(string2, onlCgformField.getDbFieldName());
            }
        }
        a.info(" sub enhanceJs end==> " + string2);
        onlCgformEnhanceJs.setCgJs(string2);
    }
}

