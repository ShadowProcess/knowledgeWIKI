package com.example.java.this_explain;

public class ThisCollege {

    public int i;
    public String s;

    public ThisCollege(int i, String s) {
        //TODO 构造方法中的this，指的是当前正在创建的对象
        this.i = i;
        this.s = s;
    }

    public void mm(){
        //TODO 普通方法中的this，指的是当前对象
        System.out.println(this.i);
    }


    public static void m(){
        //TODO 'com.example.java.this_explain.ThisCollege.this' cannot be referenced from a static context
        //用法错误：因为this变量不存在于当前静态方法的局部变量表中
        //System.out.println(this.i);
    }

    public static void main(String[] args) {
        //TODO 'com.example.java.this_explain.ThisCollege.this' cannot be referenced from a static context
        //用法错误：因为this变量不存在于当前静态方法的局部变量表中
        //System.out.println(this.i);
    }



}
