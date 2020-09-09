package com.design.principle.ocp.improve;

public class Ocp {
    public static void main(String[] args) {
        //使用看看存在的问题
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
        graphicEditor.drawShape(new Other());
    }
}

//【使用方】
class GraphicEditor{
    //接收Shape对象；然后根据type，来绘制不同的图形
    public void drawShape(Shape s){
      s.draw();
    }
}

//基类
abstract class Shape{
    int m_type;
    public abstract void draw(); //抽象方法
}

class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("绘制矩形");
    }
}

class Circle extends Shape {

    @Override
    public void draw() {
        System.out.println("圆形");
    }
}

//新增画三角形
class Triangle extends Shape {

    @Override
    public void draw() {
        System.out.println("三角形");
    }
}

//新增
class Other extends Shape {

    @Override
    public void draw() {
        System.out.println("其它");
    }
}

