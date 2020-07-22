package com.example.fileutil;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;

public class FileTest {

    @Test
    public void _0() throws IOException {
        File fileA = new File("E:\\test\\test.txt");
        File fileB = new File("E:\\test1\\test.txt");
        //拷贝文件
        FileUtils.copyFile(fileA, fileB);
        long checkSum = FileUtils.checksum(fileA, new CRC32()).getValue();
    }

    /**
     * 将某个目录下的文件,拷贝到另外的目录下
     */
    @Test
    public void _1(){
        //原文件夹
        File srcDir = new File("G:\\original");
        //目标文件夹
        File destDir = new File("G:\\destination");
        try {
            FileUtils.copyDirectory(srcDir,destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 本方法是将目录original，整体拷贝到目录objective中，即在目录objective下面有个子目录original
     */
    @Test
    public void _2(){
        //本方法是将目录original，整体拷贝到目录objective中，即在目录objective下面有个子目录original
        File srcDir = new File("G:\\original");
        File destDir = new File("G:\\objective");
        try {
            FileUtils.copyDirectoryToDirectory(srcDir,destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }



        //将text.txt文件拷贝到objective目录下，文件名保持原来的
        File srcFile = new File("G:\\original\\src\\test.txt");
        File dir = new File("G:\\objective");
        try {
            FileUtils.copyFileToDirectory(srcFile,dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //删除文件或者目录
    @Test
    public void _4(){
        //清空目录objective下的所有内容，但是不删除objective目录
        File file1 = new File("G:\\objective");
        try {
            FileUtils.cleanDirectory(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //删除目录objective以及子目录子文件,即G盘下没有了objective目录
        File file2 = new File("G:\\objective");
        try {
            FileUtils.deleteDirectory(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //读写文件
    @Test
    public void _9() throws Exception{
        File file = new File("D:\\lol");

        //读取文件返回读取的内容字符串，另一个重载的方法可以设置编码
        String context1 = FileUtils.readFileToString(file);
        String context2 = FileUtils.readFileToString(file,"UTF-8");

        //读取文件返回字节数组
        byte[] bytes = FileUtils.readFileToByteArray(file);

        //读取文件返回流
        FileInputStream fileInputStream = FileUtils.openInputStream(file);
        FileOutputStream fileOutputStream = FileUtils.openOutputStream(file);

        //既然有读取文件内容的方法那么必然也有写的方法
        FileUtils.writeStringToFile(file,"data");
        FileUtils.writeStringToFile(file,"data","UTF-8");
    }


}
