package com.design.prototype.improve;

//原型模式

public class Client {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 1, "白色");
        sheep.friend = new Sheep("jack",2,"黑色");

        Sheep sheep1 = (Sheep) sheep.clone();
        Sheep sheep2 = (Sheep) sheep.clone();
        Sheep sheep3 = (Sheep) sheep.clone();
        System.out.println(sheep);
        System.out.println(sheep.friend.hashCode());
        System.out.println(sheep1);
        System.out.println(sheep1.friend.hashCode());
        System.out.println(sheep2);
        System.out.println(sheep2.friend.hashCode());
        System.out.println(sheep3);
        System.out.println(sheep3.friend.hashCode());
    }
}
