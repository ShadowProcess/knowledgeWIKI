package com.example.demo.util.freelogin;


import com.google.common.hash.Hashing;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class HMACSHA1 {

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    /**
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
     *
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);

        byte[] text = encryptText.getBytes(ENCODING);
        //完成 Mac 操作
        return mac.doFinal(text);
    }


    public static String hmacSha1Encrypt(String clearText, String appSecret) {
        return Hashing.hmacSha1(appSecret.getBytes())
                .newHasher()
                .putString(clearText, StandardCharsets.UTF_8)
                .hash()
                .toString()
                .toUpperCase();
    }

    public static void main(String[] args) throws Exception {
        //明文
        String clearText = "zhpt_inner_test1jsonA07F8458AC429D517E13DA47E180E2A57495B89B34E3A48B697C72FBEE864E43135C121877B2D873A5B74ABAEF5693B7842BA5D474810D3A99EADEA0EFBD0FED5F63E3DC0811C3FE114F4876ABFE38C3414" +
                "653E6206E22A2ECFD1E60BF8C2698EF7A91F542126B173C9601BDB37EF10A" +
                "DE3876AFC0313F38CEDC0CA3E5A666EEv1.5";
        //秘钥
        String secretKey = "sAecMFcAlIXes93VaWXgr3jgMup4Y0a6";

        System.out.println(hmacSha1Encrypt(clearText,secretKey));
    }
}
