/*
 * Decompiled with CFR 0.150.
 */
package org.jeecg.modules.online.cgform.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class n {
    private static final String a = "FECO#$N*CX";
    private static final Charset b = StandardCharsets.UTF_8;
    private static byte[] c = "FECO#$N*CX".getBytes(b);

    n() {
    }

    public static String a(String string) {
        byte[] arrby = string.getBytes(b);
        int n2 = arrby.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            for (byte by : c) {
                arrby[i2] = (byte)(arrby[i2] ^ by);
            }
        }
        return new String(arrby);
    }

    public static String b(String string) {
        byte[] arrby;
        byte[] arrby2 = arrby = string.getBytes(b);
        int n2 = arrby.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            for (byte by : c) {
                arrby[i2] = (byte)(arrby2[i2] ^ by);
            }
        }
        return new String(arrby);
    }

    public static String c(String string) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(string.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            // empty catch block
        }
        byte[] arrby = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < arrby.length; ++i2) {
            if (Integer.toHexString(0xFF & arrby[i2]).length() == 1) {
                stringBuffer.append("0").append(Integer.toHexString(0xFF & arrby[i2]));
                continue;
            }
            stringBuffer.append(Integer.toHexString(0xFF & arrby[i2]));
        }
        return stringBuffer.toString();
    }
}

