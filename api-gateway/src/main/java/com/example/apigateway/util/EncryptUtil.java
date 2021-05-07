package com.example.apigateway.util;


import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public class EncryptUtil {
    private final static String DES = "DES";
    private final static String ENCODE = "UTF-8";
    private static final Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

    /**
     * 加密
     *
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        try {
            byte[] bt = encrypt(data.getBytes(ENCODE), (System.currentTimeMillis() + "").getBytes(ENCODE));
            String strs = encodeBase64(bt);
            return strs.replaceAll("\r\n", "");
        } catch (Exception e) {
            logger.error("加密,{}", e);
        }
        return null;
    }

    /**
     * base64
     *
     * @param str
     * @return
     * @throws Exception
     */
    private static String encodeBase64(byte[] str) throws Exception {
        byte[] b = str;
        String s = null;
        try {
            if (b != null) {
                byte[] tmp = Base64.encode(b);
                s = new String(tmp, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("base64数据错误,{}", e);
            throw e;
        }
        return s;
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
}
