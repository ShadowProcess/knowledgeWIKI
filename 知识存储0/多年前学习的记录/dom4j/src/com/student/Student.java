package com.student;

public class Student {

    String num;

    String name;

    String desc;

    Student() {

    }

    public Student(String num, String name, String desc) {
        this.num = num;
        this.name = name;
        this.desc = desc;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
