package com.example.util.secure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 此实用类提供报文摘要计算,如MD5,SHA
 *
 * @author bingyunxl
 */
public class MDUtil {

    /**
     * 按MD5报文摘要算法计算
     *
     * @param plaintext 明文
     * @return 密文
     */
    public static String encryptByMD5(String plaintext) {

        String ciphertext = "";

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest(plaintext.getBytes());
        ciphertext = Coding.byte2hex(digest);

        return ciphertext;
    }

    public static String encryptBy16MD5(String plaintext) {
        return encryptByMD5(plaintext).substring(8, 24);
    }

    /**
     * 按SHA报文摘要算法计算
     *
     * @param plaintext 明文
     * @return 密文
     */
    public static String encryptBySHA(String plaintext) {
        String ciphertext = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest(plaintext.getBytes());
        ciphertext = Coding.byte2hex(digest);
        return ciphertext;
    }

    public static String fileMD5(File file) {
        FileInputStream in = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            FileChannel ch = in.getChannel();// 1GB limit
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            md.update(byteBuffer);
            byte[] digest = md.digest();
            String ciphertext = Coding.byte2hex(digest);
            return ciphertext;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return "";
    }

    public static String md5(byte[]... data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            for (byte[] bs : data) {
                md.update(bs, 0, bs.length);
            }
            return Coding.byte2hex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] md5ToByte(byte[]... data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            for (byte[] bs : data) {
                md.update(bs, 0, bs.length);
            }
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private MDUtil() {
    }

    public static void main(String[] args) throws Exception {

        System.out.println(MDUtil.fileMD5(new File("c:/work/dbus-1.9.18.tar.gz")));

    }

}
