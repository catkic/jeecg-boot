/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.ArrayUtils
 *  org.apache.commons.lang.CharSetUtils
 *  org.apache.commons.lang.CharUtils
 *  org.apache.commons.lang.ObjectUtils
 *  org.apache.commons.lang.QstrEncodeUtil
 *  org.apache.commons.lang.StringEscapeUtils
 *  org.apache.commons.lang.StringUtils
 *  org.apache.commons.lang.WordUtils
 *  org.apache.commons.lang.text.StrBuilder
 *  org.apache.commons.net.SignatureUtil
 */
package org.jeecg.modules.online.cgform.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.lang.*;
//import org.apache.commons.lang.QstrEncodeUtil;
import org.apache.commons.lang.text.StrBuilder;
//import org.apache.commons.net.SignatureUtil;


public class StrUtils {
    public static final String a = "";
    public static final int b = -1;
    public static boolean c = Boolean.FALSE;
    public static boolean d = Boolean.TRUE;
    private static final int g = 8192;
    public static String e;
//    static String[] f;

    public static boolean isBlank(String string) {
        return string == null || string.length() == 0;
    }

    public static boolean isNotBlank(String string) {
        return !org.apache.commons.lang.StringUtils.isEmpty(string);
    }

    public static boolean notNull(Object object) {
        return object != null;
    }

    public static boolean contains(String string, List<String> list) {
        if (string != null && list != null) {
            for (String string2 : list) {
                if (string2 != null && string.contains(string2)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean c(String string) {
        if (string == null) {
            return true;
        }
        return isBlank(string.trim());
    }

    public static boolean d(String string) {
        return !org.apache.commons.lang.StringUtils.isBlank(string);
    }

    public static String e(String string) {
        return string == null ? a : string.trim();
    }

    public static String f(String string) {
        return string == null ? null : string.trim();
    }

    public static String g(String string) {
        String string2 = StrUtils.f(string);
        return StrUtils.isBlank(string2) ? null : string2;
    }

    public static String h(String string) {
        return string == null ? a : string.trim();
    }

    public static String i(String var0) {
        return a(var0, (String)null);
    }

    public static String j(String var0) {
        if (var0 == null) {
            return null;
        } else {
            var0 = a((String)var0, (String)null);
            return var0.length() == 0 ? null : var0;
        }
    }

    public static String k(String string) {
        return string == null ? a : a(string, (String) null);
    }

    public static String a(String string, String string2) {
        if (StrUtils.isBlank(string)) {
            return string;
        }
        string = StrUtils.b(string, string2);
        return StrUtils.c(string, string2);
    }

    public static String b(String var0, String var1) {
        int var2;
        if (var0 != null && (var2 = var0.length()) != 0) {
            int var3 = 0;
            if (var1 == null) {
                while(var3 != var2 && Character.isWhitespace(var0.charAt(var3))) {
                    ++var3;
                }
            } else {
                if (var1.length() == 0) {
                    return var0;
                }

                while(var3 != var2 && var1.indexOf(var0.charAt(var3)) != -1) {
                    ++var3;
                }
            }

            return var0.substring(var3);
        } else {
            return var0;
        }
    }

    public static String c(String string, String string2) {
        int n2;
        if (string == null || (n2 = string.length()) == 0) {
            return string;
        }
        if (string2 == null) {
            while (n2 != 0 && Character.isWhitespace(string.charAt(n2 - 1))) {
                --n2;
            }
        } else {
            if (string2.length() == 0) {
                return string;
            }
            while (n2 != 0 && string2.indexOf(string.charAt(n2 - 1)) != -1) {
                --n2;
            }
        }
        return string.substring(0, n2);
    }

    public static String[] a(String[] arrstring) {
        return StrUtils.a(arrstring, null);
    }

    public static String[] a(String[] arrstring, String string) {
        int n2;
        if (arrstring == null || (n2 = arrstring.length) == 0) {
            return arrstring;
        }
        String[] arrstring2 = new String[n2];
        for (int i2 = 0; i2 < n2; ++i2) {
            arrstring2[i2] = StrUtils.a(arrstring[i2], string);
        }
        return arrstring2;
    }

    public static boolean d(String string, String string2) {
        return string == null ? string2 == null : string.equals(string2);
    }

    public static boolean e(String string, String string2) {
        return string == null ? string2 == null : string.equalsIgnoreCase(string2);
    }

    public static int a(String string, char c2) {
        if (StrUtils.isBlank(string)) {
            return -1;
        }
        return string.indexOf(c2);
    }

    public static int a(String string, char c2, int n2) {
        if (StrUtils.isBlank(string)) {
            return -1;
        }
        return string.indexOf(c2, n2);
    }

    public static int f(String string, String string2) {
        if (string == null || string2 == null) {
            return -1;
        }
        return string.indexOf(string2);
    }

    public static int a(String string, String string2, int n2) {
        return StrUtils.a(string, string2, n2, false);
    }

    private static int a(String string, String string2, int n2, boolean bl) {
        if (string == null || string2 == null || n2 <= 0) {
            return -1;
        }
        if (string2.length() == 0) {
            return bl ? string.length() : 0;
        }
        int n3 = 0;
        int n4 = bl ? string.length() : -1;
        do {
            if ((n4 = bl ? string.lastIndexOf(string2, n4 - 1) : string.indexOf(string2, n4 + 1)) >= 0) continue;
            return n4;
        } while (++n3 < n2);
        return n4;
    }

    public static int b(String string, String string2, int n2) {
        if (string == null || string2 == null) {
            return -1;
        }
        if (string2.length() == 0 && n2 >= string.length()) {
            return string.length();
        }
        return string.indexOf(string2, n2);
    }

    public static int g(String string, String string2) {
        return StrUtils.c(string, string2, 0);
    }

    public static int c(String string, String string2, int n2) {
        int n3;
        if (string == null || string2 == null) {
            return -1;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > (n3 = string.length() - string2.length() + 1)) {
            return -1;
        }
        if (string2.length() == 0) {
            return n2;
        }
        for (int i2 = n2; i2 < n3; ++i2) {
            if (!string.regionMatches(true, i2, string2, 0, string2.length())) continue;
            return i2;
        }
        return -1;
    }

    public static int b(String string, char c2) {
        if (StrUtils.isBlank(string)) {
            return -1;
        }
        return string.lastIndexOf(c2);
    }

    public static int b(String string, char c2, int n2) {
        if (StrUtils.isBlank(string)) {
            return -1;
        }
        return string.lastIndexOf(c2, n2);
    }

    public static int h(String string, String string2) {
        if (string == null || string2 == null) {
            return -1;
        }
        return string.lastIndexOf(string2);
    }

    public static int d(String string, String string2, int n2) {
        return StrUtils.a(string, string2, n2, true);
    }

    public static int e(String string, String string2, int n2) {
        if (string == null || string2 == null) {
            return -1;
        }
        return string.lastIndexOf(string2, n2);
    }

    public static int i(String string, String string2) {
        if (string == null || string2 == null) {
            return -1;
        }
        return StrUtils.f(string, string2, string.length());
    }

    public static int f(String string, String string2, int n2) {
        if (string == null || string2 == null) {
            return -1;
        }
        if (n2 > string.length() - string2.length()) {
            n2 = string.length() - string2.length();
        }
        if (n2 < 0) {
            return -1;
        }
        if (string2.length() == 0) {
            return n2;
        }
        for (int i2 = n2; i2 >= 0; --i2) {
            if (!string.regionMatches(true, i2, string2, 0, string2.length())) continue;
            return i2;
        }
        return -1;
    }

    public static boolean c(String string, char c2) {
        if (StrUtils.isBlank(string)) {
            return false;
        }
        return string.indexOf(c2) >= 0;
    }

    public static boolean j(String string, String string2) {
        if (string == null || string2 == null) {
            return false;
        }
        return string.indexOf(string2) >= 0;
    }

    public static boolean k(String string, String string2) {
        if (string == null || string2 == null) {
            return false;
        }
        int n2 = string2.length();
        int n3 = string.length() - n2;
        for (int i2 = 0; i2 <= n3; ++i2) {
            if (!string.regionMatches(true, i2, string2, 0, n2)) continue;
            return true;
        }
        return false;
    }

    public static int a(String string, char[] arrc) {
        if (StrUtils.isBlank(string) || ArrayUtils.isEmpty((char[])arrc)) {
            return -1;
        }
        int n2 = string.length();
        int n3 = n2 - 1;
        int n4 = arrc.length;
        int n5 = n4 - 1;
        for (int i2 = 0; i2 < n2; ++i2) {
            char c2 = string.charAt(i2);
            for (int i3 = 0; i3 < n4; ++i3) {
                if (arrc[i3] != c2) continue;
                if (i2 < n3 && i3 < n5) {
                    if (arrc[i3 + 1] != string.charAt(i2 + 1)) continue;
                    return i2;
                }
                return i2;
            }
        }
        return -1;
    }

    public static int l(String string, String string2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return -1;
        }
        return StrUtils.a(string, string2.toCharArray());
    }

    public static boolean b(String string, char[] arrc) {
        if (StrUtils.isBlank(string) || ArrayUtils.isEmpty((char[])arrc)) {
            return false;
        }
        int n2 = string.length();
        int n3 = arrc.length;
        int n4 = n2 - 1;
        int n5 = n3 - 1;
        for (int i2 = 0; i2 < n2; ++i2) {
            char c2 = string.charAt(i2);
            for (int i3 = 0; i3 < n3; ++i3) {
                if (arrc[i3] != c2) continue;
                if (i3 == n5) {
                    return true;
                }
                if (i2 >= n4 || arrc[i3 + 1] != string.charAt(i2 + 1)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean m(String string, String string2) {
        if (string2 == null) {
            return false;
        }
        return StrUtils.b(string, string2.toCharArray());
    }

    public static int c(String string, char[] arrc) {
        if (StrUtils.isBlank(string) || ArrayUtils.isEmpty((char[])arrc)) {
            return -1;
        }
        int n2 = string.length();
        int n3 = n2 - 1;
        int n4 = arrc.length;
        int n5 = n4 - 1;
        block0: for (int i2 = 0; i2 < n2; ++i2) {
            char c2 = string.charAt(i2);
            for (int i3 = 0; i3 < n4; ++i3) {
                if (arrc[i3] == c2 && (i2 >= n3 || i3 >= n5 || arrc[i3 + 1] == string.charAt(i2 + 1))) continue block0;
            }
            return i2;
        }
        return -1;
    }

    public static int n(String string, String string2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return -1;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            boolean bl;
            char c2 = string.charAt(i2);
            boolean bl2 = bl = string2.indexOf(c2) >= 0;
            if (i2 + 1 < n2) {
                char c3 = string.charAt(i2 + 1);
                if (!bl || string2.indexOf(c3) >= 0) continue;
                return i2;
            }
            if (bl) continue;
            return i2;
        }
        return -1;
    }

    public static boolean d(String string, char[] arrc) {
        if (arrc == null || string == null) {
            return false;
        }
        if (string.length() == 0) {
            return true;
        }
        if (arrc.length == 0) {
            return false;
        }
        return StrUtils.c(string, arrc) == -1;
    }

    public static boolean o(String string, String string2) {
        if (string == null || string2 == null) {
            return false;
        }
        return StrUtils.d(string, string2.toCharArray());
    }

    public static boolean e(String string, char[] arrc) {
        if (string == null || arrc == null) {
            return true;
        }
        int n2 = string.length();
        int n3 = n2 - 1;
        int n4 = arrc.length;
        int n5 = n4 - 1;
        for (int i2 = 0; i2 < n2; ++i2) {
            char c2 = string.charAt(i2);
            for (int i3 = 0; i3 < n4; ++i3) {
                if (arrc[i3] != c2) continue;
                if (i3 == n5) {
                    return false;
                }
                if (i2 >= n3 || arrc[i3 + 1] != string.charAt(i2 + 1)) continue;
                return false;
            }
        }
        return true;
    }

    public static boolean p(String string, String string2) {
        if (string == null || string2 == null) {
            return true;
        }
        return StrUtils.e(string, string2.toCharArray());
    }

    public static int a(String string, String[] arrstring) {
        if (string == null || arrstring == null) {
            return -1;
        }
        int n2 = arrstring.length;
        int n3 = Integer.MAX_VALUE;
        int n4 = 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            String string2 = arrstring[i2];
            if (string2 == null || (n4 = string.indexOf(string2)) == -1 || n4 >= n3) continue;
            n3 = n4;
        }
        return n3 == Integer.MAX_VALUE ? -1 : n3;
    }

    public static int b(String string, String[] arrstring) {
        if (string == null || arrstring == null) {
            return -1;
        }
        int n2 = arrstring.length;
        int n3 = -1;
        int n4 = 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            String string2 = arrstring[i2];
            if (string2 == null || (n4 = string.lastIndexOf(string2)) <= n3) continue;
            n3 = n4;
        }
        return n3;
    }

    public static String a(String string, int n2) {
        if (string == null) {
            return null;
        }
        if (n2 < 0) {
            n2 = string.length() + n2;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > string.length()) {
            return a;
        }
        return string.substring(n2);
    }

    public static String a(String string, int n2, int n3) {
        if (string == null) {
            return null;
        }
        if (n3 < 0) {
            n3 = string.length() + n3;
        }
        if (n2 < 0) {
            n2 = string.length() + n2;
        }
        if (n3 > string.length()) {
            n3 = string.length();
        }
        if (n2 > n3) {
            return a;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        return string.substring(n2, n3);
    }

    public static String b(String string, int n2) {
        if (string == null) {
            return null;
        }
        if (n2 < 0) {
            return a;
        }
        if (string.length() <= n2) {
            return string;
        }
        return string.substring(0, n2);
    }

    public static String c(String string, int n2) {
        if (string == null) {
            return null;
        }
        if (n2 < 0) {
            return a;
        }
        if (string.length() <= n2) {
            return string;
        }
        return string.substring(string.length() - n2);
    }

    public static String b(String string, int n2, int n3) {
        if (string == null) {
            return null;
        }
        if (n3 < 0 || n2 > string.length()) {
            return a;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (string.length() <= n2 + n3) {
            return string.substring(n2);
        }
        return string.substring(n2, n2 + n3);
    }

    public static String q(String string, String string2) {
        if (StrUtils.isBlank(string) || string2 == null) {
            return string;
        }
        if (string2.length() == 0) {
            return a;
        }
        int n2 = string.indexOf(string2);
        if (n2 == -1) {
            return string;
        }
        return string.substring(0, n2);
    }

    public static String r(String string, String string2) {
        if (StrUtils.isBlank(string)) {
            return string;
        }
        if (string2 == null) {
            return a;
        }
        int n2 = string.indexOf(string2);
        if (n2 == -1) {
            return a;
        }
        return string.substring(n2 + string2.length());
    }

    public static String s(String string, String string2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return string;
        }
        int n2 = string.lastIndexOf(string2);
        if (n2 == -1) {
            return string;
        }
        return string.substring(0, n2);
    }

    public static String t(String string, String string2) {
        if (StrUtils.isBlank(string)) {
            return string;
        }
        if (StrUtils.isBlank(string2)) {
            return a;
        }
        int n2 = string.lastIndexOf(string2);
        if (n2 == -1 || n2 == string.length() - string2.length()) {
            return a;
        }
        return string.substring(n2 + string2.length());
    }

    public static String u(String string, String string2) {
        return StrUtils.a(string, string2, string2);
    }

    public static String a(String string, String string2, String string3) {
        int n2;
        if (string == null || string2 == null || string3 == null) {
            return null;
        }
        int n3 = string.indexOf(string2);
        if (n3 != -1 && (n2 = string.indexOf(string3, n3 + string2.length())) != -1) {
            return string.substring(n3 + string2.length(), n2);
        }
        return null;
    }

    public static String[] b(String string, String string2, String string3) {
        int n2;
        int n3;
        if (string == null || StrUtils.isBlank(string2) || StrUtils.isBlank(string3)) {
            return null;
        }
        int n4 = string.length();
        if (n4 == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        int n5 = string3.length();
        int n6 = string2.length();
        ArrayList<String> arrayList = new ArrayList<String>();
        int n7 = 0;
        while (n7 < n4 - n5 && (n3 = string.indexOf(string2, n7)) >= 0 && (n2 = string.indexOf(string3, n3 += n6)) >= 0) {
            arrayList.add(string.substring(n3, n2));
            n7 = n2 + n5;
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public static String v(String string, String string2) {
        return StrUtils.a(string, string2, string2);
    }

    public static String c(String string, String string2, String string3) {
        return StrUtils.a(string, string2, string3);
    }

    public static String[] l(String string) {
        return StrUtils.g(string, null, -1);
    }

    public static String[] d(String string, char c2) {
        return StrUtils.a(string, c2, false);
    }

    public static String[] w(String string, String string2) {
        return StrUtils.c(string, string2, -1, false);
    }

    public static String[] g(String string, String string2, int n2) {
        return StrUtils.c(string, string2, n2, false);
    }

    public static String[] x(String string, String string2) {
        return StrUtils.b(string, string2, -1, false);
    }

    public static String[] h(String string, String string2, int n2) {
        return StrUtils.b(string, string2, n2, false);
    }

    public static String[] y(String string, String string2) {
        return StrUtils.b(string, string2, -1, true);
    }

    public static String[] i(String string, String string2, int n2) {
        return StrUtils.b(string, string2, n2, true);
    }

    private static String[] b(String string, String string2, int n2, boolean bl) {
        if (string == null) {
            return null;
        }
        int n3 = string.length();
        if (n3 == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        if (string2 == null || a.equals(string2)) {
            return StrUtils.c(string, null, n2, bl);
        }
        int n4 = string2.length();
        ArrayList<String> arrayList = new ArrayList<String>();
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        while (n7 < n3) {
            n7 = string.indexOf(string2, n6);
            if (n7 > -1) {
                if (n7 > n6) {
                    if (++n5 == n2) {
                        n7 = n3;
                        arrayList.add(string.substring(n6));
                        continue;
                    }
                    arrayList.add(string.substring(n6, n7));
                    n6 = n7 + n4;
                    continue;
                }
                if (bl) {
                    if (++n5 == n2) {
                        n7 = n3;
                        arrayList.add(string.substring(n6));
                    } else {
                        arrayList.add(a);
                    }
                }
                n6 = n7 + n4;
                continue;
            }
            arrayList.add(string.substring(n6));
            n7 = n3;
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] m(String string) {
        return StrUtils.c(string, null, -1, true);
    }

    public static String[] e(String string, char c2) {
        return StrUtils.a(string, c2, true);
    }

    private static String[] a(String string, char c2, boolean bl) {
        if (string == null) {
            return null;
        }
        int n2 = string.length();
        if (n2 == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        int n3 = 0;
        int n4 = 0;
        boolean bl2 = false;
        boolean bl3 = false;
        while (n3 < n2) {
            if (string.charAt(n3) == c2) {
                if (bl2 || bl) {
                    arrayList.add(string.substring(n4, n3));
                    bl2 = false;
                    bl3 = true;
                }
                n4 = ++n3;
                continue;
            }
            bl3 = false;
            bl2 = true;
            ++n3;
        }
        if (bl2 || bl && bl3) {
            arrayList.add(string.substring(n4, n3));
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] z(String string, String string2) {
        return StrUtils.c(string, string2, -1, true);
    }

    public static String[] j(String string, String string2, int n2) {
        return StrUtils.c(string, string2, n2, true);
    }

    private static String[] c(String string, String string2, int n2, boolean bl) {
        if (string == null) {
            return null;
        }
        int n3 = string.length();
        if (n3 == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        ArrayList<String> arrayList = new ArrayList<String>();
        int n4 = 1;
        int n5 = 0;
        int n6 = 0;
        boolean bl2 = false;
        boolean bl3 = false;
        if (string2 == null) {
            while (n5 < n3) {
                if (Character.isWhitespace(string.charAt(n5))) {
                    if (bl2 || bl) {
                        bl3 = true;
                        if (n4++ == n2) {
                            n5 = n3;
                            bl3 = false;
                        }
                        arrayList.add(string.substring(n6, n5));
                        bl2 = false;
                    }
                    n6 = ++n5;
                    continue;
                }
                bl3 = false;
                bl2 = true;
                ++n5;
            }
        } else if (string2.length() == 1) {
            char c2 = string2.charAt(0);
            while (n5 < n3) {
                if (string.charAt(n5) == c2) {
                    if (bl2 || bl) {
                        bl3 = true;
                        if (n4++ == n2) {
                            n5 = n3;
                            bl3 = false;
                        }
                        arrayList.add(string.substring(n6, n5));
                        bl2 = false;
                    }
                    n6 = ++n5;
                    continue;
                }
                bl3 = false;
                bl2 = true;
                ++n5;
            }
        } else {
            while (n5 < n3) {
                if (string2.indexOf(string.charAt(n5)) >= 0) {
                    if (bl2 || bl) {
                        bl3 = true;
                        if (n4++ == n2) {
                            n5 = n3;
                            bl3 = false;
                        }
                        arrayList.add(string.substring(n6, n5));
                        bl2 = false;
                    }
                    n6 = ++n5;
                    continue;
                }
                bl3 = false;
                bl2 = true;
                ++n5;
            }
        }
        if (bl2 || bl && bl3) {
            arrayList.add(string.substring(n6, n5));
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] n(String string) {
        return StrUtils.a(string, false);
    }

    public static String[] o(String string) {
        return StrUtils.a(string, true);
    }

    private static String[] a(String string, boolean bl) {
        if (string == null) {
            return null;
        }
        if (string.length() == 0) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        char[] arrc = string.toCharArray();
        ArrayList<String> arrayList = new ArrayList<String>();
        int n2 = 0;
        int n3 = Character.getType(arrc[n2]);
        for (int i2 = n2 + 1; i2 < arrc.length; ++i2) {
            int n4 = Character.getType(arrc[i2]);
            if (n4 == n3) continue;
            if (bl && n4 == 2 && n3 == 1) {
                int n5 = i2 - 1;
                if (n5 != n2) {
                    arrayList.add(new String(arrc, n2, n5 - n2));
                    n2 = n5;
                }
            } else {
                arrayList.add(new String(arrc, n2, i2 - n2));
                n2 = i2;
            }
            n3 = n4;
        }
        arrayList.add(new String(arrc, n2, arrc.length - n2));
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public static String a(Object[] arrobject) {
        return StrUtils.a(arrobject, null);
    }

    public static String b(Object[] arrobject) {
        return StrUtils.a(arrobject, null);
    }

    public static String a(Object[] arrobject, char c2) {
        if (arrobject == null) {
            return null;
        }
        return StrUtils.a(arrobject, c2, 0, arrobject.length);
    }

    public static String a(Object[] arrobject, char c2, int n2, int n3) {
        if (arrobject == null) {
            return null;
        }
        int n4 = n3 - n2;
        if (n4 <= 0) {
            return a;
        }
        StrBuilder strBuilder = new StrBuilder(n4 *= (arrobject[n2] == null ? 16 : arrobject[n2].toString().length()) + 1);
        for (int i2 = n2; i2 < n3; ++i2) {
            if (i2 > n2) {
                strBuilder.append(c2);
            }
            if (arrobject[i2] == null) continue;
            strBuilder.append(arrobject[i2]);
        }
        return strBuilder.toString();
    }

    public static String a(Object[] arrobject, String string) {
        if (arrobject == null) {
            return null;
        }
        return StrUtils.a(arrobject, string, 0, arrobject.length);
    }

    public static String a(Object[] arrobject, String string, int n2, int n3) {
        int n4;
        if (arrobject == null) {
            return null;
        }
        if (string == null) {
            string = a;
        }
        if ((n4 = n3 - n2) <= 0) {
            return a;
        }
        StrBuilder strBuilder = new StrBuilder(n4 *= (arrobject[n2] == null ? 16 : arrobject[n2].toString().length()) + string.length());
        for (int i2 = n2; i2 < n3; ++i2) {
            if (i2 > n2) {
                strBuilder.append(string);
            }
            if (arrobject[i2] == null) continue;
            strBuilder.append(arrobject[i2]);
        }
        return strBuilder.toString();
    }

    public static String a(Iterator iterator, char c2) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return a;
        }
        Object e2 = iterator.next();
        if (!iterator.hasNext()) {
            return ObjectUtils.toString(e2);
        }
        StrBuilder strBuilder = new StrBuilder(256);
        if (e2 != null) {
            strBuilder.append(e2);
        }
        while (iterator.hasNext()) {
            strBuilder.append(c2);
            Object e3 = iterator.next();
            if (e3 == null) continue;
            strBuilder.append(e3);
        }
        return strBuilder.toString();
    }

    public static String a(Iterator iterator, String string) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return a;
        }
        Object e2 = iterator.next();
        if (!iterator.hasNext()) {
            return ObjectUtils.toString(e2);
        }
        StrBuilder strBuilder = new StrBuilder(256);
        if (e2 != null) {
            strBuilder.append(e2);
        }
        while (iterator.hasNext()) {
            Object e3;
            if (string != null) {
                strBuilder.append(string);
            }
            if ((e3 = iterator.next()) == null) continue;
            strBuilder.append(e3);
        }
        return strBuilder.toString();
    }

//    private static void b() {
//        String string = "kOzlPKX8Es8/FwQdXX60NZDs5Tyl/BLPPxcEHV1+tDWQ7OU8pfwSzz8XBB1dfrQ1iN5zqKZaPYep6qn8X+n2QLKRXOKp2vFQjVEFS7T75CGCnTDPggguLlLiSRDR+DN00a2paweobc4zkqYP/0gU+d4jODOhQvZ9DFAsE4kQZpsXOzNfRrTeD+fWUnMFtAcuP5wfzEsz9Gm/ENqSW/sf0JQaTCWAxHmqaMpauPUZXNWEiuzRhMaXSDRmD4nV1DOcTqvDj5BtUWWdnJQV+by4VjVceI6gYuC5E3R+m4p6QG4wiASRPe+mpGacCousLwjL6a6Zx+iA2MXhQiPM6WjQTWIWA3TKwu105/ojzopukCuQ7OU8pfwSzz8XBB1dfrQ1kOzlPKX8Es8/FwQdXX60Nb+YHwc5536J89tvlGzFHGI=";
//        try {
//            Object object;
//            if (f == null || f.length == 0) {
//                object = org.jeecg.modules.online.cgform.util.c.a();
//                if (object == null) {
//                    object = ResourceBundle.getBundle("jeecglic");
//                }
//                f = QstrEncodeUtil.isDocker() ? new String[]{SignatureUtil.getDockerLicense()} : ((ResourceBundle)object).getString("licence").split(",");
//            }
//            if (!org.jeecg.modules.online.cgform.util.c.b(f, l.getssssserver()) && !org.jeecg.modules.online.cgform.util.c.b(f, l.getssss())) {
//                System.out.println(" Computer SN ：  " + l.getmachine_code());
//                object = k.c(string, "123456");
//                System.err.println((String)object);
//                System.exit(0);
//            }
//        }
//        catch (Exception exception) {
//            try {
//                System.out.println(" Computer SN ：  " + l.getmachine_code());
//                String string2 = k.c(string, "123456");
//                System.err.println(string2);
//                System.exit(0);
//            }
//            catch (Exception exception2) {
//                // empty catch block
//            }
//        }
//    }

    public static String a(Collection collection, char c2) {
        if (collection == null) {
            return null;
        }
        return StrUtils.a(collection.iterator(), c2);
    }

    public static String a(Collection collection, String string) {
        if (collection == null) {
            return null;
        }
        return StrUtils.a(collection.iterator(), string);
    }

    public static String p(String string) {
        if (string == null) {
            return null;
        }
        return CharSetUtils.delete((String)string, (String)" \t\r\n\b");
    }

    public static String q(String string) {
        if (StrUtils.isBlank(string)) {
            return string;
        }
        int n2 = string.length();
        char[] arrc = new char[n2];
        int n3 = 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            if (Character.isWhitespace(string.charAt(i2))) continue;
            arrc[n3++] = string.charAt(i2);
        }
        if (n3 == n2) {
            return string;
        }
        return new String(arrc, 0, n3);
    }

    public static String A(String string, String string2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return string;
        }
        if (string.startsWith(string2)) {
            return string.substring(string2.length());
        }
        return string;
    }

    public static String B(String string, String string2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return string;
        }
        if (StrUtils.T(string, string2)) {
            return string.substring(string2.length());
        }
        return string;
    }

    public static String C(String string, String string2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return string;
        }
        if (string.endsWith(string2)) {
            return string.substring(0, string.length() - string2.length());
        }
        return string;
    }

    public static String D(String string, String string2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return string;
        }
        if (StrUtils.V(string, string2)) {
            return string.substring(0, string.length() - string2.length());
        }
        return string;
    }

    public static String E(String string, String string2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return string;
        }
        return StrUtils.a(string, string2, a, -1);
    }

    public static String f(String string, char c2) {
        if (StrUtils.isBlank(string) || string.indexOf(c2) == -1) {
            return string;
        }
        char[] arrc = string.toCharArray();
        int n2 = 0;
        for (int i2 = 0; i2 < arrc.length; ++i2) {
            if (arrc[i2] == c2) continue;
            arrc[n2++] = arrc[i2];
        }
        return new String(arrc, 0, n2);
    }

    public static String d(String string, String string2, String string3) {
        return StrUtils.a(string, string2, string3, 1);
    }

    public static String e(String string, String string2, String string3) {
        return StrUtils.a(string, string2, string3, -1);
    }

    public static String a(String string, String string2, String string3, int n2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2) || string3 == null || n2 == 0) {
            return string;
        }
        int n3 = 0;
        int n4 = string.indexOf(string2, n3);
        if (n4 == -1) {
            return string;
        }
        int n5 = string2.length();
        int n6 = string3.length() - n5;
        int n7 = n6 = n6 < 0 ? 0 : n6;
        StrBuilder strBuilder = new StrBuilder(string.length() + (n6 *= n2 < 0 ? 16 : (n2 > 64 ? 64 : n2)));
        while (n4 != -1) {
            strBuilder.append(string.substring(n3, n4)).append(string3);
            n3 = n4 + n5;
            if (--n2 == 0) break;
            n4 = string.indexOf(string2, n3);
        }
        strBuilder.append(string.substring(n3));
        return strBuilder.toString();
    }

    public static String a(String string, String[] arrstring, String[] arrstring2) {
        return StrUtils.a(string, arrstring, arrstring2, false, 0);
    }

//    private static void c() {
//        String string = "kOzlPKX8Es8/FwQdXX60NZDs5Tyl/BLPPxcEHV1+tDWQ7OU8pfwSzz8XBB1dfrQ1iN5zqKZaPYep6qn8X+n2QLKRXOKp2vFQjVEFS7T75CGCnTDPggguLlLiSRDR+DN00a2paweobc4zkqYP/0gU+d4jODOhQvZ9DFAsE4kQZpsXOzNfRrTeD+fWUnMFtAcuP5wfzEsz9Gm/ENqSW/sf0JQaTCWAxHmqaMpauPUZXNWEiuzRhMaXSDRmD4nV1DOcTqvDj5BtUWWdnJQV+by4VjVceI6gYuC5E3R+m4p6QG4wiASRPe+mpGacCousLwjL6a6Zx+iA2MXhQiPM6WjQTWIWA3TKwu105/ojzopukCuQ7OU8pfwSzz8XBB1dfrQ1kOzlPKX8Es8/FwQdXX60Nb+YHwc5536J89tvlGzFHGI=";
//        try {
//            Object object;
//            if (f == null || f.length == 0) {
//                object = org.jeecg.modules.online.cgform.util.c.a();
//                if (object == null) {
//                    object = ResourceBundle.getBundle("jeecglic");
//                }
//                f = QstrEncodeUtil.isDocker() ? new String[]{SignatureUtil.getDockerLicense()} : ((ResourceBundle)object).getString("licence").split(",");
//            }
//            if (!org.jeecg.modules.online.cgform.util.c.b(f, l.getssssserver()) && !org.jeecg.modules.online.cgform.util.c.b(f, l.getssss())) {
//                System.out.println(" Computer SN ：  " + l.getmachine_code());
//                object = k.c(string, "123456");
//                System.err.println((String)object);
//                System.exit(0);
//            }
//        }
//        catch (Exception exception) {
//            try {
//                System.out.println(" Computer SN ：  " + l.getmachine_code());
//                String string2 = k.c(string, "123456");
//                System.err.println(string2);
//                System.exit(0);
//            }
//            catch (Exception exception2) {
//                System.exit(0);
//            }
//        }
//    }

    public static String b(String string, String[] arrstring, String[] arrstring2) {
        int n2 = arrstring == null ? 0 : arrstring.length;
        return StrUtils.a(string, arrstring, arrstring2, true, n2);
    }

    private static String a(String string, String[] arrstring, String[] arrstring2, boolean bl, int n2) {
        int n3;
        int n4;
        if (string == null || string.length() == 0 || arrstring == null || arrstring.length == 0 || arrstring2 == null || arrstring2.length == 0) {
            return string;
        }
        if (n2 < 0) {
            throw new IllegalStateException("TimeToLive of " + n2 + " is less than 0: " + string);
        }
        int n5 = arrstring.length;
        int n6 = arrstring2.length;
        if (n5 != n6) {
            throw new IllegalArgumentException("Search and Replace array lengths don't match: " + n5 + " vs " + n6);
        }
        boolean[] arrbl = new boolean[n5];
        int n7 = -1;
        int n8 = -1;
        int n9 = -1;
        for (n4 = 0; n4 < n5; ++n4) {
            if (arrbl[n4] || arrstring[n4] == null || arrstring[n4].length() == 0 || arrstring2[n4] == null) continue;
            n9 = string.indexOf(arrstring[n4]);
            if (n9 == -1) {
                arrbl[n4] = true;
                continue;
            }
            if (n7 != -1 && n9 >= n7) continue;
            n7 = n9;
            n8 = n4;
        }
        if (n7 == -1) {
            return string;
        }
        n4 = 0;
        int n10 = 0;
        for (int i2 = 0; i2 < arrstring.length; ++i2) {
            if (arrstring[i2] == null || arrstring2[i2] == null || (n3 = arrstring2[i2].length() - arrstring[i2].length()) <= 0) continue;
            n10 += 3 * n3;
        }
        n10 = Math.min(n10, string.length() / 5);
        StrBuilder strBuilder = new StrBuilder(string.length() + n10);
        while (n7 != -1) {
            for (n3 = n4; n3 < n7; ++n3) {
                strBuilder.append(string.charAt(n3));
            }
            strBuilder.append(arrstring2[n8]);
            n4 = n7 + arrstring[n8].length();
            n7 = -1;
            n8 = -1;
            n9 = -1;
            for (n3 = 0; n3 < n5; ++n3) {
                if (arrbl[n3] || arrstring[n3] == null || arrstring[n3].length() == 0 || arrstring2[n3] == null) continue;
                n9 = string.indexOf(arrstring[n3], n4);
                if (n9 == -1) {
                    arrbl[n3] = true;
                    continue;
                }
                if (n7 != -1 && n9 >= n7) continue;
                n7 = n9;
                n8 = n3;
            }
        }
        n3 = string.length();
        for (int i3 = n4; i3 < n3; ++i3) {
            strBuilder.append(string.charAt(i3));
        }
        String string2 = strBuilder.toString();
        if (!bl) {
            return string2;
        }
        return StrUtils.a(string2, arrstring, arrstring2, bl, n2 - 1);
    }

    public static String a(String string, char c2, char c3) {
        if (string == null) {
            return null;
        }
        return string.replace(c2, c3);
    }

    public static String f(String string, String string2, String string3) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return string;
        }
        if (string3 == null) {
            string3 = a;
        }
        boolean bl = false;
        int n2 = string3.length();
        int n3 = string.length();
        StrBuilder strBuilder = new StrBuilder(n3);
        for (int i2 = 0; i2 < n3; ++i2) {
            char c2 = string.charAt(i2);
            int n4 = string2.indexOf(c2);
            if (n4 >= 0) {
                bl = true;
                if (n4 >= n2) continue;
                strBuilder.append(string3.charAt(n4));
                continue;
            }
            strBuilder.append(c2);
        }
        if (bl) {
            return strBuilder.toString();
        }
        return string;
    }

    public static String a(String string, String string2, int n2, int n3) {
        return new StrBuilder(n2 + string2.length() + string.length() - n3 + 1).append(string.substring(0, n2)).append(string2).append(string.substring(n3)).toString();
    }

    public static String b(String string, String string2, int n2, int n3) {
        if (string == null) {
            return null;
        }
        if (string2 == null) {
            string2 = a;
        }
        int n4 = string.length();
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > n4) {
            n2 = n4;
        }
        if (n3 < 0) {
            n3 = 0;
        }
        if (n3 > n4) {
            n3 = n4;
        }
        if (n2 > n3) {
            int n5 = n2;
            n2 = n3;
            n3 = n5;
        }
        return new StrBuilder(n4 + n2 - n3 + string2.length() + 1).append(string.substring(0, n2)).append(string2).append(string.substring(n3)).toString();
    }

    public static String r(String string) {
        if (StrUtils.isBlank(string)) {
            return string;
        }
        if (string.length() == 1) {
            char c2 = string.charAt(0);
            if (c2 == '\n' || c2 == '\n') {
                return a;
            }
            return string;
        }
        int n2 = string.length() - 1;
        char c3 = string.charAt(n2);
        if (c3 == '\n') {
            if (string.charAt(n2 - 1) == '\n') {
                --n2;
            }
        } else if (c3 != '\n') {
            ++n2;
        }
        return string.substring(0, n2);
    }

    public static String F(String string, String string2) {
        if (StrUtils.isBlank(string) || string2 == null) {
            return string;
        }
        if (string.endsWith(string2)) {
            return string.substring(0, string.length() - string2.length());
        }
        return string;
    }

    public static String s(String string) {
        return StrUtils.G(string, "\n");
    }

    public static String G(String string, String string2) {
        if (string.length() == 0) {
            return string;
        }
        String string3 = string.substring(string.length() - string2.length());
        if (string2.equals(string3)) {
            return string.substring(0, string.length() - string2.length());
        }
        return string;
    }

    public static String H(String string, String string2) {
        int n2 = string.lastIndexOf(string2);
        if (n2 == string.length() - string2.length()) {
            return string2;
        }
        if (n2 != -1) {
            return string.substring(n2);
        }
        return a;
    }

    public static String I(String string, String string2) {
        int n2 = string.indexOf(string2);
        if (n2 == -1) {
            return string;
        }
        return string.substring(n2 + string2.length());
    }

    public static String J(String string, String string2) {
        int n2 = string.indexOf(string2);
        if (n2 == -1) {
            return a;
        }
        return string.substring(0, n2 + string2.length());
    }

    public static String t(String string) {
        if (string == null) {
            return null;
        }
        int n2 = string.length();
        if (n2 < 2) {
            return a;
        }
        int n3 = n2 - 1;
        String string2 = string.substring(0, n3);
        char c2 = string.charAt(n3);
        if (c2 == '\n' && string2.charAt(n3 - 1) == '\n') {
            return string2.substring(0, n3 - 1);
        }
        return string2;
    }

    public static String u(String string) {
        int n2 = string.length() - 1;
        if (n2 <= 0) {
            return a;
        }
        char c2 = string.charAt(n2);
        if (c2 == '\n') {
            if (string.charAt(n2 - 1) == '\n') {
                --n2;
            }
        } else {
            ++n2;
        }
        return string.substring(0, n2);
    }

    public static String v(String string) {
        return StringEscapeUtils.escapeJava((String)string);
    }

    public static String d(String string, int n2) {
        if (string == null) {
            return null;
        }
        if (n2 <= 0) {
            return a;
        }
        int n3 = string.length();
        if (n2 == 1 || n3 == 0) {
            return string;
        }
        if (n3 == 1 && n2 <= 8192) {
            return StrUtils.a(n2, string.charAt(0));
        }
        int n4 = n3 * n2;
        switch (n3) {
            case 1: {
                char c2 = string.charAt(0);
                char[] arrc = new char[n4];
                for (int i2 = n2 - 1; i2 >= 0; --i2) {
                    arrc[i2] = c2;
                }
                return new String(arrc);
            }
            case 2: {
                char c3 = string.charAt(0);
                char c4 = string.charAt(1);
                char[] arrc = new char[n4];
                for (int i3 = n2 * 2 - 2; i3 >= 0; --i3) {
                    arrc[i3] = c3;
                    arrc[i3 + 1] = c4;
                    --i3;
                }
                return new String(arrc);
            }
        }
        StrBuilder strBuilder = new StrBuilder(n4);
        for (int i4 = 0; i4 < n2; ++i4) {
            strBuilder.append(string);
        }
        return strBuilder.toString();
    }

    public static String k(String string, String string2, int n2) {
        if (string == null || string2 == null) {
            return StrUtils.d(string, n2);
        }
        String string3 = StrUtils.d(string + string2, n2);
        return StrUtils.C(string3, string2);
    }

    private static String a(int n2, char c2) throws IndexOutOfBoundsException {
        if (n2 < 0) {
            throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + n2);
        }
        char[] arrc = new char[n2];
        for (int i2 = 0; i2 < arrc.length; ++i2) {
            arrc[i2] = c2;
        }
        return new String(arrc);
    }

    public static String e(String string, int n2) {
        return StrUtils.a(string, n2, ' ');
    }

    public static String a(String string, int n2, char c2) {
        if (string == null) {
            return null;
        }
        int n3 = n2 - string.length();
        if (n3 <= 0) {
            return string;
        }
        if (n3 > 8192) {
            return StrUtils.a(string, n2, String.valueOf(c2));
        }
        return string.concat(StrUtils.a(n3, c2));
    }

    public static String a(String string, int n2, String string2) {
        if (string == null) {
            return null;
        }
        if (StrUtils.isBlank(string2)) {
            string2 = " ";
        }
        int n3 = string2.length();
        int n4 = string.length();
        int n5 = n2 - n4;
        if (n5 <= 0) {
            return string;
        }
        if (n3 == 1 && n5 <= 8192) {
            return StrUtils.a(string, n2, string2.charAt(0));
        }
        if (n5 == n3) {
            return string.concat(string2);
        }
        if (n5 < n3) {
            return string.concat(string2.substring(0, n5));
        }
        char[] arrc = new char[n5];
        char[] arrc2 = string2.toCharArray();
        for (int i2 = 0; i2 < n5; ++i2) {
            arrc[i2] = arrc2[i2 % n3];
        }
        return string.concat(new String(arrc));
    }

    public static String f(String string, int n2) {
        return StrUtils.b(string, n2, ' ');
    }

    public static String b(String string, int n2, char c2) {
        if (string == null) {
            return null;
        }
        int n3 = n2 - string.length();
        if (n3 <= 0) {
            return string;
        }
        if (n3 > 8192) {
            return StrUtils.b(string, n2, String.valueOf(c2));
        }
        return StrUtils.a(n3, c2).concat(string);
    }

    public static String b(String string, int n2, String string2) {
        if (string == null) {
            return null;
        }
        if (StrUtils.isBlank(string2)) {
            string2 = " ";
        }
        int n3 = string2.length();
        int n4 = string.length();
        int n5 = n2 - n4;
        if (n5 <= 0) {
            return string;
        }
        if (n3 == 1 && n5 <= 8192) {
            return StrUtils.b(string, n2, string2.charAt(0));
        }
        if (n5 == n3) {
            return string2.concat(string);
        }
        if (n5 < n3) {
            return string2.substring(0, n5).concat(string);
        }
        char[] arrc = new char[n5];
        char[] arrc2 = string2.toCharArray();
        for (int i2 = 0; i2 < n5; ++i2) {
            arrc[i2] = arrc2[i2 % n3];
        }
        return new String(arrc).concat(string);
    }

    public static int w(String string) {
        return string == null ? 0 : string.length();
    }

    public static String g(String string, int n2) {
        return StrUtils.c(string, n2, ' ');
    }

    public static String c(String string, int n2, char c2) {
        if (string == null || n2 <= 0) {
            return string;
        }
        int n3 = string.length();
        int n4 = n2 - n3;
        if (n4 <= 0) {
            return string;
        }
        string = StrUtils.b(string, n3 + n4 / 2, c2);
        string = StrUtils.a(string, n2, c2);
        return string;
    }

    public static String c(String string, int n2, String string2) {
        int n3;
        int n4;
        if (string == null || n2 <= 0) {
            return string;
        }
        if (StrUtils.isBlank(string2)) {
            string2 = " ";
        }
        if ((n4 = n2 - (n3 = string.length())) <= 0) {
            return string;
        }
        string = StrUtils.b(string, n3 + n4 / 2, string2);
        string = StrUtils.a(string, n2, string2);
        return string;
    }

    public static String x(String string) {
        if (string == null) {
            return null;
        }
        return string.toUpperCase();
    }

    public static String a(String string, Locale locale) {
        if (string == null) {
            return null;
        }
        return string.toUpperCase(locale);
    }

    public static String y(String string) {
        if (string == null) {
            return null;
        }
        return string.toLowerCase();
    }

    public static String b(String string, Locale locale) {
        if (string == null) {
            return null;
        }
        return string.toLowerCase(locale);
    }

    public static String z(String string) {
        int n2;
        if (string == null || (n2 = string.length()) == 0) {
            return string;
        }
        return new StrBuilder(n2).append(Character.toTitleCase(string.charAt(0))).append(string.substring(1)).toString();
    }

    public static String A(String string) {
        return StrUtils.z(string);
    }

    public static String B(String string) {
        int n2;
        if (string == null || (n2 = string.length()) == 0) {
            return string;
        }
        return new StrBuilder(n2).append(Character.toLowerCase(string.charAt(0))).append(string.substring(1)).toString();
    }

    public static String C(String string) {
        return StrUtils.B(string);
    }

    public static String D(String string) {
        int n2;
        if (string == null || (n2 = string.length()) == 0) {
            return string;
        }
        StrBuilder strBuilder = new StrBuilder(n2);
        char c2 = ' ';
        for (int i2 = 0; i2 < n2; ++i2) {
            c2 = string.charAt(i2);
            if (Character.isUpperCase(c2)) {
                c2 = Character.toLowerCase(c2);
            } else if (Character.isTitleCase(c2)) {
                c2 = Character.toLowerCase(c2);
            } else if (Character.isLowerCase(c2)) {
                c2 = Character.toUpperCase(c2);
            }
            strBuilder.append(c2);
        }
        return strBuilder.toString();
    }

    public static String E(String string) {
        return WordUtils.capitalize((String)string);
    }

    public static int K(String string, String string2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return 0;
        }
        int n2 = 0;
        int n3 = 0;
        while ((n3 = string.indexOf(string2, n3)) != -1) {
            ++n2;
            n3 += string2.length();
        }
        return n2;
    }

    public static boolean F(String string) {
        if (string == null) {
            return false;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (Character.isLetter(string.charAt(i2))) continue;
            return false;
        }
        return true;
    }

    public static boolean G(String string) {
        if (string == null) {
            return false;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (Character.isLetter(string.charAt(i2)) || string.charAt(i2) == ' ') continue;
            return false;
        }
        return true;
    }

    public static boolean H(String string) {
        if (string == null) {
            return false;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (Character.isLetterOrDigit(string.charAt(i2))) continue;
            return false;
        }
        return true;
    }

    public static boolean I(String string) {
        if (string == null) {
            return false;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (Character.isLetterOrDigit(string.charAt(i2)) || string.charAt(i2) == ' ') continue;
            return false;
        }
        return true;
    }

    public static boolean J(String string) {
        if (string == null) {
            return false;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (CharUtils.isAsciiPrintable((char)string.charAt(i2))) continue;
            return false;
        }
        return true;
    }

    public static boolean K(String string) {
        if (string == null) {
            return false;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (Character.isDigit(string.charAt(i2))) continue;
            return false;
        }
        return true;
    }

    public static boolean L(String string) {
        if (string == null) {
            return false;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (Character.isDigit(string.charAt(i2)) || string.charAt(i2) == ' ') continue;
            return false;
        }
        return true;
    }

    public static boolean M(String string) {
        if (string == null) {
            return false;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (Character.isWhitespace(string.charAt(i2))) continue;
            return false;
        }
        return true;
    }

    public static boolean N(String string) {
        if (string == null || StrUtils.isBlank(string)) {
            return false;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (Character.isLowerCase(string.charAt(i2))) continue;
            return false;
        }
        return true;
    }

    public static boolean O(String string) {
        if (string == null || StrUtils.isBlank(string)) {
            return false;
        }
        int n2 = string.length();
        for (int i2 = 0; i2 < n2; ++i2) {
            if (Character.isUpperCase(string.charAt(i2))) continue;
            return false;
        }
        return true;
    }

    public static String P(String string) {
        return string == null ? a : string;
    }

    public static ResourceBundle a() {
        PropertyResourceBundle propertyResourceBundle = null;
        String string = System.getProperty("user.dir") + File.separator + "config" + File.separator + "jeecglic.properties";
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(string));
            propertyResourceBundle = new PropertyResourceBundle(bufferedInputStream);
            bufferedInputStream.close();
        }
        catch (FileNotFoundException fileNotFoundException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return propertyResourceBundle;
    }

    public static String L(String string, String string2) {
        return string == null ? string2 : string;
    }

    public static String M(String string, String string2) {
        return org.apache.commons.lang.StringUtils.isBlank((String)string) ? string2 : string;
    }

    public static String N(String string, String string2) {
        return org.apache.commons.lang.StringUtils.isEmpty((String)string) ? string2 : string;
    }

    public static String Q(String string) {
        if (string == null) {
            return null;
        }
        return new StrBuilder(string).reverse().toString();
    }

    public static String g(String string, char c2) {
        if (string == null) {
            return null;
        }
        Object[] arrobject = StrUtils.d(string, c2);
        ArrayUtils.reverse((Object[])arrobject);
        return StrUtils.a(arrobject, c2);
    }

    public static String O(String string, String string2) {
        if (string == null) {
            return null;
        }
        Object[] arrobject = StrUtils.w(string, string2);
        ArrayUtils.reverse((Object[])arrobject);
        if (string2 == null) {
            return StrUtils.a(arrobject, ' ');
        }
        return StrUtils.a(arrobject, string2);
    }

    public static String h(String string, int n2) {
        return StrUtils.c(string, 0, n2);
    }

    public static String c(String string, int n2, int n3) {
        if (string == null) {
            return null;
        }
        if (n3 < 4) {
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        }
        if (string.length() <= n3) {
            return string;
        }
        if (n2 > string.length()) {
            n2 = string.length();
        }
        if (string.length() - n2 < n3 - 3) {
            n2 = string.length() - (n3 - 3);
        }
        if (n2 <= 4) {
            return string.substring(0, n3 - 3) + "...";
        }
        if (n3 < 7) {
            throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
        }
        if (n2 + (n3 - 3) < string.length()) {
            return "..." + StrUtils.h(string.substring(n2), n3 - 3);
        }
        return "..." + string.substring(string.length() - (n3 - 3));
    }

    public static String l(String string, String string2, int n2) {
        if (StrUtils.isBlank(string) || StrUtils.isBlank(string2)) {
            return string;
        }
        if (n2 >= string.length() || n2 < string2.length() + 2) {
            return string;
        }
        int n3 = n2 - string2.length();
        int n4 = n3 / 2 + n3 % 2;
        int n5 = string.length() - n3 / 2;
        StrBuilder strBuilder = new StrBuilder(n2);
        strBuilder.append(string.substring(0, n4));
        strBuilder.append(string2);
        strBuilder.append(string.substring(n5));
        return strBuilder.toString();
    }

    public static String P(String string, String string2) {
        if (string == null) {
            return string2;
        }
        if (string2 == null) {
            return string;
        }
        int n2 = StrUtils.Q(string, string2);
        if (n2 == -1) {
            return a;
        }
        return string2.substring(n2);
    }

    public static int Q(String string, String string2) {
        int n2;
        if (string == string2) {
            return -1;
        }
        if (string == null || string2 == null) {
            return 0;
        }
        for (n2 = 0; n2 < string.length() && n2 < string2.length() && string.charAt(n2) == string2.charAt(n2); ++n2) {
        }
        if (n2 < string2.length() || n2 < string.length()) {
            return n2;
        }
        return -1;
    }

    public static int b(String[] arrstring) {
        int n2;
        if (arrstring == null || arrstring.length <= 1) {
            return -1;
        }
        boolean bl = false;
        boolean bl2 = true;
        int n3 = arrstring.length;
        int n4 = Integer.MAX_VALUE;
        int n5 = 0;
        for (n2 = 0; n2 < n3; ++n2) {
            if (arrstring[n2] == null) {
                bl = true;
                n4 = 0;
                continue;
            }
            bl2 = false;
            n4 = Math.min(arrstring[n2].length(), n4);
            n5 = Math.max(arrstring[n2].length(), n5);
        }
        if (bl2 || n5 == 0 && !bl) {
            return -1;
        }
        if (n4 == 0) {
            return 0;
        }
        n2 = -1;
        for (int i2 = 0; i2 < n4; ++i2) {
            char c2 = arrstring[0].charAt(i2);
            for (int i3 = 1; i3 < n3; ++i3) {
                if (arrstring[i3].charAt(i2) == c2) continue;
                n2 = i2;
                break;
            }
            if (n2 != -1) break;
        }
        if (n2 == -1 && n4 != n5) {
            return n4;
        }
        return n2;
    }

    public static boolean b(String[] arrstring, String string) {
        List<String> list = Arrays.asList(arrstring);
        return list.contains(string);
    }

    public static String c(String[] arrstring) {
        if (arrstring == null || arrstring.length == 0) {
            return a;
        }
        int n2 = StrUtils.b(arrstring);
        if (n2 == -1) {
            if (arrstring[0] == null) {
                return a;
            }
            return arrstring[0];
        }
        if (n2 == 0) {
            return a;
        }
        return arrstring[0].substring(0, n2);
    }

    public static int R(String string, String string2) {
        int n2;
        if (string == null || string2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        int n3 = string.length();
        int n4 = string2.length();
        if (n3 == 0) {
            return n4;
        }
        if (n4 == 0) {
            return n3;
        }
        if (n3 > n4) {
            String object = string;
            string = string2;
            string2 = object;
            n3 = n4;
            n4 = string2.length();
        }
        int[] object = new int[n3 + 1];
        int[] object2 = new int[n3 + 1];
        for (n2 = 0; n2 <= n3; ++n2) {
            object[n2] = n2;
        }
        for (int i2 = 1; i2 <= n4; ++i2) {
            char c2 = string2.charAt(i2 - 1);
            object2[0] = i2;
            for (n2 = 1; n2 <= n3; ++n2) {
                int bl = string.charAt(n2 - 1) != c2 ? 1:0;
                object2[n2] = Math.min(Math.min(object2[n2 - 1] + 1, (int)(object[n2] + 1)), (int)(object[n2 - 1] + bl));
            }
            int[] object3 = object;
            object = object2;
            object2 = object3;
        }
        return (int)object[n3];
    }

    public static boolean S(String string, String string2) {
        return StrUtils.a(string, string2, false);
    }

    public static boolean T(String string, String string2) {
        return StrUtils.a(string, string2, true);
    }

    private static boolean a(String string, String string2, boolean bl) {
        if (string == null || string2 == null) {
            return string == null && string2 == null;
        }
        if (string2.length() > string.length()) {
            return false;
        }
        return string.regionMatches(bl, 0, string2, 0, string2.length());
    }

    public static boolean U(String string, String string2) {
        return StrUtils.b(string, string2, false);
    }

    public static boolean V(String string, String string2) {
        return StrUtils.b(string, string2, true);
    }

    private static boolean b(String string, String string2, boolean bl) {
        if (string == null || string2 == null) {
            return string == null && string2 == null;
        }
        if (string2.length() > string.length()) {
            return false;
        }
        int n2 = string.length() - string2.length();
        return string.regionMatches(bl, n2, string2, 0, string2.length());
    }

    public static String R(String string) {
        if ((string = StrUtils.i(string)) == null || string.length() <= 2) {
            return string;
        }
        StrBuilder strBuilder = new StrBuilder(string.length());
        for (int i2 = 0; i2 < string.length(); ++i2) {
            char c2 = string.charAt(i2);
            if (Character.isWhitespace(c2)) {
                if (i2 <= 0 || Character.isWhitespace(string.charAt(i2 - 1))) continue;
                strBuilder.append(' ');
                continue;
            }
            strBuilder.append(c2);
        }
        return strBuilder.toString();
    }

//    static {
//        org.jeecg.modules.online.cgform.util.c.c();
//        org.jeecg.modules.online.cgform.util.c.b();
//        f = null;
//    }
}

