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

public class Base64Util {
    public static String a(byte[] arrby, int n2) {
        return new BigInteger(1, arrby).toString(n2);
    }

    public static String encode(byte[] arrby) {
        return Base64.getEncoder().encodeToString(arrby);
    }

    public static byte[] base64Decode(String string) throws Exception {
        return oConvertUtils.isEmpty(string) ? null : Base64.getDecoder().decode(string);
    }

    public static byte[] MD5(byte[] arrby) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(arrby);
        return messageDigest.digest();
    }

    public static byte[] MD5(String string) throws Exception {
        return oConvertUtils.isEmpty(string) ? null : Base64Util.MD5(string.getBytes());
    }

    public static String MD5Encrypt2String(String string) throws Exception {
        return oConvertUtils.isEmpty(string) ? null : Base64Util.encode(Base64Util.MD5(string));
    }

    public static byte[] AESEncrypt(String str, String key) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(key.getBytes()));
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(str.getBytes("utf-8"));
    }

    public static String AESEncryptBase64(String str, String key) throws Exception {
        return Base64Util.encode(Base64Util.AESEncrypt(str, key));
    }

    public static String AESEncrypt2String(byte[] encrypt, String key) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(key.getBytes()));
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES"));
        byte[] arrby2 = cipher.doFinal(encrypt);
        return new String(arrby2);
    }

    public static String AESEncrypt2String(String str, String key) throws Exception {
        return oConvertUtils.isEmpty(str) ? null : Base64Util.AESEncrypt2String(Base64Util.base64Decode(str), key);
    }
}

