package com.example.secret;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class SecretTest {
    public static void main(String[] args) {

        System.out.println(encodeTest("ssss"));
        decodeTest("c3Nzcw==");
    }


    //Base64编解码
    private static String encodeTest(String str) {
        Base64 base64 = new Base64();
        try {
            str = base64.encodeAsString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("base64编码后：" + str);
        return str;
    }


    //base64解码
    private static void decodeTest(String str) {
        //Base64 base64 = new Base64();
        //str = Arrays.toString(Base64.decodeBase64(str));
        str = new String(Base64.decodeBase64(str));
        System.out.println("Base64编码后：" + str);
    }
}
