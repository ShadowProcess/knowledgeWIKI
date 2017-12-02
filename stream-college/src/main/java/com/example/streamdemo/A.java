package com.example.streamdemo;

//如果一个类中有多个属性需要使用不同的泛型声明，则可以在声明类时指定多个泛型类型。
public class A<T, Self, B, S> {

    private T name;
    private Self age;
    private B gender;
    private S id;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public Self getAge() {
        return age;
    }

    public void setAge(Self age) {
        this.age = age;
    }

    public B getGender() {
        return gender;
    }

    public void setGender(B gender) {
        this.gender = gender;
    }

    public S getId() {
        return id;
    }

    public void setId(S id) {
        this.id = id;
    }

    public static void main(String[] args) {
        A<String,String,String,String> a = new A();
        a.setAge("1");
        a.setGender("2");
        a.setName("3");
        a.setId("4");
        System.out.println(a);
    }
}
