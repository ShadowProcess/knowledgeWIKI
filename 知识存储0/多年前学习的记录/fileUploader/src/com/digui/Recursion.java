package com.digui;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class Recursion {


    /**
     * 递归遍历目录,目录层次太深，将导致内存溢出
     *
     * @param file
     */
    public void getFile(File file) {
        if (file.isDirectory()) {
            //是目录
            File[] fs = file.listFiles();

            for (int i = 0; i < fs.length; i++) {
                getFile(fs[i]); //递归调用
            }
        } else if (file.isFile()) {
            //是文件
            System.out.println("文件名:" + file.getName());
        }
    }


    /**
     * 使用队列解决目录层次深的问题
     * 队列特点：先进先出
     * 在JDK中有一个接口Queue,有一个实现类LinkedList
     * 它其实就是一个队列
     * <p>
     * 如果使用队列，插入使用offer  获取使用poll
     */

    public void recursion(File file) {
        String path = "D:\\DevelopmentSoftWare\\ideaWebWorkSpace\\FileUploader\\web\\uploadFile";

        File uploadDirectory = new File(path);

        Queue<File> queue = new LinkedList<File>();

        queue.offer(uploadDirectory);

        while (!queue.isEmpty()) { //队列不为空
            File f = queue.poll(); //从队列获取一个file

            if (f.isDirectory()) { //是目录，将目录下所有文件遍历出来，存储到队列
                File[] fs = f.listFiles();
                for (int i = 0; i < fs.length; i++) {
                    queue.offer(fs[i]);
                }
            } else {
                System.out.println("文件名："+f.getName());
            }
        }
    }


}
