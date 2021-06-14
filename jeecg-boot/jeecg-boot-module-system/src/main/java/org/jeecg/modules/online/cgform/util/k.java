/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jeecg.common.util.oConvertUtils
 */
package org.jeecg.modules.online.cgform.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import org.jeecg.common.util.oConvertUtils;

public class k {
    public static String a(byte[] arrby, int n2) {
        return new BigInteger(1, arrby).toString(n2);
    }

    public static String a(byte[] arrby) {
        return Base64.getEncoder().encodeToString(arrby);
    }

    public static byte[] a(String string) throws Exception {
        return oConvertUtils.isEmpty((Object)string) ? null : Base64.getDecoder().decode(string);
    }

    public static byte[] b(byte[] arrby) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(arrby);
        return messageDigest.digest();
    }

    public static byte[] b(String string) throws Exception {
        return oConvertUtils.isEmpty((Object)string) ? null : k.b(string.getBytes());
    }

    public static String c(String string) throws Exception {
        return oConvertUtils.isEmpty((Object)string) ? null : k.a(k.b(string));
    }

    public static byte[] a(String string, String string2) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(string2.getBytes()));
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(string.getBytes("utf-8"));
    }

    public static String b(String string, String string2) throws Exception {
        return k.a(k.a(string, string2));
    }

    public static String a(byte[] arrby, String string) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(string.getBytes()));
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES"));
        byte[] arrby2 = cipher.doFinal(arrby);
        return new String(arrby2);
    }

    public static String c(String string, String string2) throws Exception {
        return oConvertUtils.isEmpty((Object)string) ? null : k.a(k.a(string), string2);
    }
}

