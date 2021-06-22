/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

class IpUtils {
    IpUtils() {
    }

    private static String b() {
        String string = "";
        byte[] arrby = null;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            arrby = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
        }
        catch (Exception exception) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer("");
        if (arrby == null) {
            return null;
        }
        for (int i2 = 0; i2 < arrby.length; ++i2) {
            int n2;
            String string2;
            if (i2 != 0) {
                stringBuffer.append("-");
            }
            if ((string2 = Integer.toHexString(n2 = arrby[i2] & 0xFF)).length() == 1) {
                stringBuffer.append("0" + string2);
                continue;
            }
            stringBuffer.append(string2);
        }
        string = stringBuffer.toString().toUpperCase();
        if ("".equals(string)) {
            return null;
        }
        return string;
    }

    private static String c() {
        String string = "";
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                byte[] arrby = enumeration.nextElement().getHardwareAddress();
                if (arrby == null) continue;
                StringBuilder stringBuilder = new StringBuilder();
                for (byte by : arrby) {
                    stringBuilder.append(IpUtils.a(by));
                    stringBuilder.append("-");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                string = stringBuilder.toString().toUpperCase();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return string;
    }

    private static String a(byte by) {
        String string = "000000" + Integer.toHexString(by);
        return string.substring(string.length() - 2);
    }

    private static String d() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static String a() {
        String string = IpUtils.d();
        String string2 = null;
        string2 = string.startsWith("windows") ? IpUtils.b() : IpUtils.c();
        return string2;
    }
}

