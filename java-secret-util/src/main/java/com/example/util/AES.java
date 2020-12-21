package com.example.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class AES {
    public static final String DEFAULT_CODING = "utf-8";
    /**
     * 解密
     *
     * @param encrypted
     * @param seed
     * @return
     * @throws Exception
     * @author iceinto
     * @date 2014-2-25
     */
    public static String decrypt(String encrypted, String seed) throws Exception {
        byte[] keyb = seed.getBytes(DEFAULT_CODING);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(keyb);
        SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
        Cipher dcipher = Cipher.getInstance("AES");
        dcipher.init(Cipher.DECRYPT_MODE, skey);
        byte[] clearbyte = dcipher.doFinal(toByte(encrypted));
        return new String(clearbyte);
    }

    /**
     * 加密
     *
     * @param content
     * @param key
     * @return
     * @throws Exception
     * @author iceinto
     * @date 2014-2-25
     */
    public static String encrypt(String content, String key) throws Exception {
        byte[] input = content.getBytes(DEFAULT_CODING);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(key.getBytes(DEFAULT_CODING));
        SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skc);
        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);
        return parseByte2HexStr(cipherText);
    }

    /**
     * 字符串转字节数组
     *
     * @param hexString
     * @return
     * @author iceinto
     * @date 2014-2-25
     */
    private static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
                    16).byteValue();
        }
        return result;
    }

    /**
     * 字节转16进制数组
     *
     * @param buf
     * @return
     * @author iceinto
     * @date 2014-2-25
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        //String req = "{\"mobileNum\": \"18018609133\",\"chn\": \"app\",\"reqTime\": \"1550132837329\",\"shorturi\": \"/index\"}";
        //System.out.println(AES.encrypt(req, "kkk"));
        //String jsonStr = decrypt("04b404720819e16043648b44e54d6839a94510e6611118ff592b3224507746e051c80d13f1ba87611cea5d815f6c32633d93d281074b16ea62908b750a0b5c2030d11445949ecf3a2f16294f3e3f8007628a68adb4d03ea4064c9f96cdd76235", "kkk");
        //System.out.println(jsonStr);

        String req = "bae37deedabcf70fc69d9a636f13050969dfbdc23ca2af5cd710280a1d5ad6dab28b545618974071ae8a05cae63e992c1c3d07ace0d3fa72ae8880a119ad7f025431a3ae3634693a86d400b6b1c94eb8";
        String illsecretandcoop = decrypt(req, "illsecretandcoop");
        System.out.println(illsecretandcoop);
    }
}

