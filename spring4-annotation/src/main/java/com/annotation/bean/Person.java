package com.annotation.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {

    //使用@Value赋值
    //1.基本数值
    //2.可以写 SpEL； #{}
    //3.可以写 ${};取出配置文件【properties】中的值（在运行的环境变量中的值）

    @Value("张三")
    private String name;
    @Value("#{20-2}")
    private Integer age;

    @Value("${person.nickName}")  //这个属性没有set方法
    private String nickName;

    public String getName() {
        return name;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
