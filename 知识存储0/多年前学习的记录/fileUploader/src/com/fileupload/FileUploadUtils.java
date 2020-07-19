package com.fileupload;

import java.io.File;
import java.util.UUID;

public class FileUploadUtils {


    //目录分离算法
    public static String getRandomDirectory(String filename){
        int hashCode = filename.hashCode();
        System.out.println(hashCode); //32位的  int在内存中占32位 4个字节(4b)

        /**
         * int类型数据在内存占32位，转换为16进制数，就得到8个16进制数
         *
         * 16进制[4位（4b）]
         *
         *
         */

        String hex = Integer.toHexString(hashCode);
        System.out.println(hex); //不够8位，需要自己前补0

        // 把16进制[共8位]的每一位作为目录名

        /**
         * 91067235  10进制
         * 056d9363  16进制   看fileSeparation目录怎么分的
         */

        return "/"+hex.charAt(0)+"/"+hex.charAt(1);

    }


    public static void main(String[] args) {
        String path = getRandomDirectory("a.text");

        File file = new File("d:/upload");

        File directory = new File(file,path);

        if (!directory.exists()){
            directory.mkdirs();
        }

    }


    //目录分离算法2  该方法中涉及到的全部是移位运算,<<是带符号左移 >>>是无符号右移。
    public static String getRandomDirectory2(String filename){
        int hashCode = filename.hashCode();

        //a 返回的就是 [hashCode&0xf  得到是4位二进制]
        int a = hashCode&0xf;  //0xf  就是15  二进制位1111

        hashCode =hashCode>>>4;  //它的二进制数据右移动4位

        int b = hashCode & 0xf; //0xf  就是15  二进制1111
        return "/"+a+"/"+b; //共有256个目录
    }



    //得到文件随机名称
    public static String getUUIDFileName(String filename) {
        int index = filename.lastIndexOf(".");

        if (index != -1) {
            return UUID.randomUUID()+filename.substring(index);
        } else {
            return UUID.randomUUID().toString();
        }
    }

}
