package com;

import java.lang.reflect.Method;

public class BankInfo {


    @Bank(maxMoney = 5000)
    public void account(String name1,String name2,int maxMoney) throws NoSuchMethodException {
        //1.获取当前方法的Method对象

        //1.1获取当前类的Class对象
        Class clazz = this.getClass();
        Method method = clazz.getDeclaredMethod("account",String.class,String.class,int.class);
        //1.2在Method类中有一个getAnnotation(Class annotationClass) 可以获取一个注解对象
        Bank bif = method.getAnnotation(Bank.class);
        //1.3通过注解对象来调整其属性
        int max = bif.maxMoney();

        //判断MaxMoney，就取代了配置文件


        //2.在Method类中有一个getAnnotation(Class annotationClass),可以获取注解对象
        //3.通过注解对象来调整其属性

    }
}
