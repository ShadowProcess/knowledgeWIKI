package com.annotation.circulardependency;

import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.ArrayList;
import java.util.List;

public class Test {

    //Spring初始化Bean的过程 [Spring扫描类之后，并没有立即创建类]

    //遍历所有扫描到的类
    public void load(){
        for(int i=0;i<10000;i++){
            //1.先创建一个这个存储类信息
            GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
            genericBeanDefinition.setBeanClass(IndexService.class);
            genericBeanDefinition.setBeanClassName("xxxx");
            genericBeanDefinition.setScope("prototype");
            //...
            //...

            //2.在创建一个集合存储所有 bean的名字
            List<String> s = new ArrayList<>();
            s.add("xxxx1");
            s.add("xxxx2");
            //...

        }
    }


}
