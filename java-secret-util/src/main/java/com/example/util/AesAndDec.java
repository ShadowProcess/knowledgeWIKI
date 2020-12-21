package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Objects;

public class AesAndDec {

    private static final Logger log = LoggerFactory.getLogger(AesAndDec.class);

    private static final String PKCS = "AES/ECB/PKCS5Padding";
    private static final String DEFAULT_CODING = "utf-8";
    private static final String MD5 = "MD5";
    private static final String AES = "AES";
    private static final String BLANK = "";

    private AesAndDec() {

    }

    public static AesAndDec create() {
        final AesAndDec like = new AesAndDec();
        return like;
    }

    public String aesDecr(String encr, String seed) {
        if (Objects.isNull(encr) || Objects.isNull(seed)) {
            return BLANK;
        }
        byte[] keyb = getStrByte(seed);
        if (null == keyb) {
            return BLANK;
        }
        MessageDigest md = digest(MD5);
        if (null == md) {
            return BLANK;
        }
        byte[] digest = md.digest(keyb);
        if (null == digest) {
            return BLANK;
        }
        SecretKeySpec skey = new SecretKeySpec(digest, AES);
        Cipher dcipher = cipher(AES, Cipher.DECRYPT_MODE, skey);
        if (null == dcipher) {
            return BLANK;
        }
        byte[] clearbyte = decrDoFinal(encr, dcipher);
        if (null == clearbyte) {
            return BLANK;
        } else {
            return new String(clearbyte);
        }
    }

    private byte[] getStrByte(String str) {
        try {
            return str.getBytes(DEFAULT_CODING);
        } catch (Throwable t) {
            log.error(t.toString() + ",str=" + str, t);
        }
        return null;
    }

    private MessageDigest digest(String tp) {
        try {
            return MessageDigest.getInstance(tp);
        } catch (Throwable t) {
            log.error(t.toString() + ",tp=" + tp, t);
        }
        return null;
    }

    private Cipher cipher(String tp, int ctp, SecretKeySpec skey) {
        Cipher dcipher = cipher(tp);
        if (null == dcipher) {
            return null;
        } else {
            try {
                dcipher.init(ctp, skey);
            } catch (Throwable t) {
                log.error(t.toString() + ",tp=" + tp, t);
            }
            return dcipher;
        }
    }

    private Cipher cipher(String tp) {
        try {
            return Cipher.getInstance(tp);
        } catch (Throwable t) {
            log.error(t.toString() + ",tp=" + tp, t);
        }
        return null;
    }

    private byte[] decrDoFinal(String encrypted, Cipher dcipher) {
        try {
            return dcipher.doFinal(str2Byte(encrypted));
        } catch (Throwable t) {
            log.error(t.toString(), t);
        }
        return null;
    }

    public String encrypt(String content, String key) {
        if (null == content || null == key) {
            return BLANK;
        }
        byte[] input = getStrByte(content);
        if (null == input) {
            return BLANK;
        }
        MessageDigest md = digest(MD5);
        byte[] kbs = getStrByte(key);
        if (null == kbs) {
            return BLANK;
        }
        byte[] digest = md.digest(kbs);
        SecretKeySpec skc = new SecretKeySpec(digest, AES);
        Cipher ecipher = cipher(PKCS, Cipher.ENCRYPT_MODE, skc);
        if (null == ecipher) {
            return BLANK;
        }
        byte[] cipherByte = new byte[ecipher.getOutputSize(input.length)];
        int ctLength = update(input, cipherByte, ecipher);
        if (0 > ctLength) {
            return BLANK;
        }
        encrDoFinal(cipherByte, ctLength, ecipher);
        return byte2Str(cipherByte);
    }

    private int update(byte[] input, byte[] cipherByte, Cipher ecipher) {
        try {
            return ecipher.update(input, 0, input.length, cipherByte, 0);
        } catch (Throwable t) {
            log.error(t.toString(), t);
        }
        return -1;
    }

    private int encrDoFinal(byte[] cipherByte, int ctLength, Cipher ecipher) {
        try {
            return ecipher.doFinal(cipherByte, ctLength);
        } catch (Throwable t) {
            log.error(t.toString(), t);
        }
        return -1;
    }

    private byte[] str2Byte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return result;
    }

    private String byte2Str(byte buf[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    public static void main(String[] args) {


    }
}
