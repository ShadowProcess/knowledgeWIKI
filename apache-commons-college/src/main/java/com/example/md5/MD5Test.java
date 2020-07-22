package com.example.md5;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * byte(字节)
 *
 * 一个字节,8位，可以组成2^8=256中不同数字。byte存值范围: -128 - 127;
 *  -128 -> -1 在十六进制表示范围: 0x80 -> 0xff ，在二进制表示范围：10000000 -> 11111111
 *  0 -> 127   在十六进制表示范围: 0x00 -> 0x7f , 在二进制表示范围是：00000000 -> 01111111
 *
 * byte中可以存：一个ascii字符(ascii范围：0-127)、十进制数值(-128-127)、十六进制数值(0x00 - 0x79，十进值范围：0-127)
 */

public class MD5Test {
    public static void main(String[] args) throws IOException {
        //MD5加密字符串
        String s = DigestUtils.md5Hex("data");
        System.out.println(s);


        //MD5加密字节数组
        byte[] bytes = DigestUtils.md5(new byte[]{});



        //MD5加密流的
        byte[] bytes1 = DigestUtils.md5(new FileInputStream(""));
    }
}
