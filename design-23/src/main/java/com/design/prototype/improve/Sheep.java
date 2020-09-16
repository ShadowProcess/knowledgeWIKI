package com.design.prototype.improve;

public class Sheep implements Cloneable {
    private String name;
    private int age;
    private String color;

    public Sheep friend; //对象成员在克隆的时候，怎么处理；浅克隆和深克隆
    //浅克隆；你克隆当前对象，并没有真正的复制一份friend，而是指向原来的那个

    public Sheep(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //克隆该实例，使用默认的clone方法来完成
    @Override
    protected Object clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheep;
    }
}
