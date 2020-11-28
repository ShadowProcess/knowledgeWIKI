package com.example.wipe_out_the_generic;


import java.util.ArrayList;
import java.util.List;

/**
 * 演示泛型擦出
 */
public class WipeOutTheGeneric {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        List listString = new ArrayList<String>();
        List listInteger = new ArrayList<Integer>();

        try {
            list.getClass().getMethod("add",Object.class).invoke(list,1);
            listString.getClass().getMethod("add",Object.class).invoke(listString,1);
            // 给不服气的读者们的测试之处，你可以改成字符串来尝试。
            listInteger.getClass().getMethod("add", Object.class).invoke(listInteger, 1);
        } catch (Exception ignored) {
        }
        System.out.println(list.size());        //1
        System.out.println(listString.size());  //1
        System.out.println(listInteger.size()); //1
    }
}
