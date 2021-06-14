/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class m {
    static Map<String, Object> a = new HashMap<String, Object>();

    m() {
    }

    public static Map<String, Object> a() {
        return a;
    }

    public static boolean a(Object object) {
        if (object == null) {
            return true;
        }
        if (object.equals("")) {
            return true;
        }
        return object.equals("null");
    }

    public static boolean b(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof CharSequence) {
            return ((CharSequence)object).length() == 0;
        }
        if (object instanceof Collection) {
            return ((Collection)object).isEmpty();
        }
        if (object instanceof Map) {
            return ((Map)object).isEmpty();
        }
        if (object instanceof Object[]) {
            Object[] arrobject = (Object[])object;
            if (arrobject.length == 0) {
                return true;
            }
            boolean bl = true;
            for (int i2 = 0; i2 < arrobject.length; ++i2) {
                if (m.b(arrobject[i2])) continue;
                bl = false;
                break;
            }
            return bl;
        }
        return false;
    }

    public static boolean c(Object object) {
        return object != null && !object.equals("") && !object.equals("null");
    }

    public static String a(String string, String string2, String string3) {
        String string4 = m.c(string, string2, string3);
        return string4;
    }

    public static String b(String string, String string2, String string3) {
        string = "";
        try {
            string = new String(string.getBytes("ISO-8859-1"), "GBK");
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        return string;
    }

    private static String c(String string, String string2, String string3) {
        String string4 = null;
        if (string == null || string.trim().equals("")) {
            return string;
        }
        try {
            byte[] arrby = string.getBytes(string2);
            for (int i2 = 0; i2 < arrby.length; ++i2) {
                System.out.print(arrby[i2] + "  ");
            }
            string4 = new String(arrby, string3);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return string4;
    }

    public static int a(String string, int n2) {
        if (string == null || string == "") {
            return n2;
        }
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException numberFormatException) {
            return n2;
        }
    }

    public static int a(String string) {
        if (string == null || string == "") {
            return 0;
        }
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException numberFormatException) {
            return 0;
        }
    }

    public static int d(Object object) {
        if (object == null || object == "") {
            return 0;
        }
        try {
            return Integer.parseInt(object.toString());
        }
        catch (NumberFormatException numberFormatException) {
            return 0;
        }
    }

    public static int a(String string, Integer n2) {
        if (string == null || string == "") {
            return n2;
        }
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException numberFormatException) {
            return 0;
        }
    }

    public static Integer[] a(String[] arrstring) {
        Integer[] arrinteger = new Integer[arrstring.length];
        if (arrstring == null) {
            return null;
        }
        for (int i2 = 0; i2 < arrstring.length; ++i2) {
            arrinteger[i2] = Integer.parseInt(arrstring[i2]);
        }
        return arrinteger;
    }

    public static double a(String string, double d2) {
        if (string == null || string == "") {
            return d2;
        }
        try {
            return Double.parseDouble(string);
        }
        catch (NumberFormatException numberFormatException) {
            return d2;
        }
    }

    public static double a(Double d2, double d3) {
        if (d2 == null) {
            return d3;
        }
        return d2;
    }

    public static int a(Object object, int n2) {
        if (m.a(object)) {
            return n2;
        }
        try {
            return Integer.parseInt(object.toString());
        }
        catch (NumberFormatException numberFormatException) {
            return n2;
        }
    }

    public static int a(BigDecimal bigDecimal, int n2) {
        if (bigDecimal == null) {
            return n2;
        }
        return bigDecimal.intValue();
    }

    public static Integer[] b(String[] arrstring) {
        int n2 = arrstring.length;
        Integer[] arrinteger = new Integer[n2];
        try {
            for (int i2 = 0; i2 < n2; ++i2) {
                arrinteger[i2] = new Integer(arrstring[i2].trim());
            }
            return arrinteger;
        }
        catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    public static String b(String string) {
        return m.a(string, "");
    }

    public static String e(Object object) {
        if (m.a(object)) {
            return "";
        }
        return object.toString().trim();
    }

    public static String a(int n2) {
        return String.valueOf(n2);
    }

    public static String c(String[] arrstring) {
        if (arrstring.length == 0) {
            return "";
        }
        String string = "";
        for (String string2 : arrstring) {
            string = string + ",'" + string2 + "'";
        }
        return string.substring(1);
    }

    public static String a(float f2) {
        return String.valueOf(f2);
    }

    public static String a(String string, String string2) {
        if (m.a((Object)string)) {
            return string2;
        }
        return string.trim();
    }

    public static String a(Object object, String string) {
        if (m.a(object)) {
            return string;
        }
        return object.toString().trim();
    }

    public static long c(String string) {
        Long l2 = new Long(0L);
        try {
            l2 = Long.valueOf(string);
        }
        catch (Exception exception) {
            // empty catch block
        }
        return l2;
    }

    public static String b() {
        String string = null;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            string = inetAddress.getHostAddress();
        }
        catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        }
        return string;
    }

    private static boolean a(Class class_) throws Exception {
        return class_.equals(String.class) || class_.equals(Integer.class) || class_.equals(Byte.class) || class_.equals(Long.class) || class_.equals(Double.class) || class_.equals(Float.class) || class_.equals(Character.class) || class_.equals(Short.class) || class_.equals(BigDecimal.class) || class_.equals(BigInteger.class) || class_.equals(Boolean.class) || class_.equals(Date.class) || class_.isPrimitive();
    }

    public static String c() throws SocketException {
        String string = null;
        String string2 = null;
        Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
        InetAddress inetAddress = null;
        boolean bl = false;
        block0: while (enumeration.hasMoreElements() && !bl) {
            NetworkInterface networkInterface = enumeration.nextElement();
            Enumeration<InetAddress> enumeration2 = networkInterface.getInetAddresses();
            while (enumeration2.hasMoreElements()) {
                inetAddress = enumeration2.nextElement();
                if (!inetAddress.isSiteLocalAddress() && !inetAddress.isLoopbackAddress() && inetAddress.getHostAddress().indexOf(":") == -1) {
                    string2 = inetAddress.getHostAddress();
                    bl = true;
                    continue block0;
                }
                if (!inetAddress.isSiteLocalAddress() || inetAddress.isLoopbackAddress() || inetAddress.getHostAddress().indexOf(":") != -1) continue;
                string = inetAddress.getHostAddress();
            }
        }
        if (string2 != null && !"".equals(string2)) {
            return string2;
        }
        return string;
    }

    public static String d(String string) {
        String string2 = "";
        if (string != null) {
            Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
            Matcher matcher = pattern.matcher(string);
            string2 = matcher.replaceAll("");
        }
        return string2;
    }

    public static boolean a(String string, String[] arrstring) {
        if (arrstring == null || arrstring.length == 0) {
            return false;
        }
        for (int i2 = 0; i2 < arrstring.length; ++i2) {
            String string2 = arrstring[i2];
            if (!string2.equals(string)) continue;
            return true;
        }
        return false;
    }

    public static boolean b(String string, String string2) {
        String[] arrstring = null;
        if (string2 != null) {
            arrstring = string2.split(",");
        }
        return m.a(string, arrstring);
    }

    public static Map<Object, Object> d() {
        return new HashMap<Object, Object>();
    }

//    public static Map<Object, Object> a(Set<Object> set) {
//        Map<Object, Object> map = m.d();
//        for (Map.Entry<String, String> entry : set) {
//            map.put(entry.getKey().toString(), entry.getValue() == null ? "" : entry.getValue().toString().trim());
//        }
//        return map;
//    }
    public static Map<Object, Object> a(Set<Object> var0) {
        Map var1 = d();
        Iterator var2 = var0.iterator();

        while(var2.hasNext()) {
            Map.Entry var3 = (Map.Entry)var2.next();
            var1.put(var3.getKey().toString(), var3.getValue() == null ? "" : var3.getValue().toString().trim());
        }

        return var1;
    }

    public static boolean e(String string) {
        boolean bl = false;
        long l2 = m.f(string);
        long l3 = m.f("10.0.0.0");
        long l4 = m.f("10.255.255.255");
        long l5 = m.f("172.16.0.0");
        long l6 = m.f("172.31.255.255");
        long l7 = m.f("192.168.0.0");
        long l8 = m.f("192.168.255.255");
        bl = m.a(l2, l3, l4) || m.a(l2, l5, l6) || m.a(l2, l7, l8) || string.equals("127.0.0.1");
        return bl;
    }

    private static long f(String string) {
        String[] arrstring = string.split("\\.");
        long l2 = Integer.parseInt(arrstring[0]);
        long l3 = Integer.parseInt(arrstring[1]);
        long l4 = Integer.parseInt(arrstring[2]);
        long l5 = Integer.parseInt(arrstring[3]);
        long l6 = l2 * 256L * 256L * 256L + l3 * 256L * 256L + l4 * 256L + l5;
        return l6;
    }

    private static boolean a(long l2, long l3, long l4) {
        return l2 >= l3 && l2 <= l4;
    }
}

