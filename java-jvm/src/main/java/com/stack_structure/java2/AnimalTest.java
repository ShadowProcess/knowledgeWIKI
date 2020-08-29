package com.stack_structure.java2;

/**
 * 说明早期绑定和晚期绑定的例子
 *
 */
class Animal{
    public void eat(){
        System.out.println("动物进食");
    }
}

interface Huntable{
    void hunt();
}

class Dog extends Animal implements Huntable{
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    @Override
    public void hunt() {
        System.out.println("捕食耗子，多管闲事");
    }
}

class Cat extends Animal implements Huntable{

    public Cat(){
        super();//表现为：早期绑定
    }

    public Cat(String name){
        this();//表现为：早期绑定
    }

    @Override
    public void eat() {
        super.eat();//表现为：早期绑定
        System.out.println("猫吃鱼");
    }

    @Override
    public void hunt() {
        System.out.println("捕食耗子，天经地义");
    }
}
public class AnimalTest {

    public void showAnimal(Animal animal){
        animal.eat();//表现为：晚期绑定（编译时期确定不了）
    }

    //因为不确定是要调用哪个实例化对象
    public void showHunt(Huntable h){
        h.hunt();//表现为：晚期绑定
    }
}
