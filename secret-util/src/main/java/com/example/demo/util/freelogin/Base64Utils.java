package com.example.demo.util.freelogin;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** */

/**
 * <p>
 * BASE64编码解码工具包
 * </p>
 * <p>
 * 依赖javabase64-1.3.1.jar
 * </p>
 *
 * @author IceWee
 * @date 2012-5-19
 * @version 1.0
 */
public class Base64Utils {

/** *//**
     * 文件读取缓冲区大小
     */
    private static final int CACHE_SIZE = 1024;

/**
     * <p>
     * BASE64字符串解码为二进制数据
     * </p>
     *
     * @param base64
     * @return
     * @throws Exception
     */
    public static byte[] decode(String base64) throws Exception {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] buffer = base64Decoder.decodeBuffer(base64);
        return buffer;
    }

/**
     * <p>
     * 二进制数据编码为BASE64字符串
     * </p>
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(byte[] bytes) throws Exception {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encodeBuffer(bytes);
    }

    public static void main(String[] args) {
        //原字符串
        String str = "";
        //Base64格式字符串
        String base64Str = "";

        try {
            //Base64编码
            System.out.println("原字符串："+str);
            System.out.println("Base64编码后字符串"+encode(str.getBytes("UTF-8")));

            //Base64解码
            System.out.println("待解码字符串"+base64Str);
            System.out.println("解码后字符串："+decode(base64Str));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}