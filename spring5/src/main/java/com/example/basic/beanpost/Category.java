package com.example.basic.beanpost;

public class Category {

    private Integer id;
    private String name;

    public Category(){
        System.out.println("构造方法执行");
    }

    public void ini(){
        System.out.println("自定义bean初始化方法");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        System.out.println("执行参数设置方法（set方法）");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
