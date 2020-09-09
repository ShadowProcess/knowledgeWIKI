package com.design.principle.ocp;

//开闭原则
public class Ocp {
    public static void main(String[] args) {
        //使用看看存在的问题
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawRectangle(new Rectangle());
        graphicEditor.drawCircle(new Circle());
    }
}

//【使用方】
class GraphicEditor{
    //接收Shape对象；然后根据type，来绘制不同的图形
    public void drawShape(Shape s){
        if (s.m_type == 1) {
            drawRectangle(s);
        } else if (s.m_type == 2) {
            drawCircle(s);
        }else if (s.m_type == 3) {
            drawTriangle(s);
        }
    }

    public void drawRectangle(Shape r){
        System.out.println("矩形");
    }

    public void drawCircle(Shape r){
        System.out.println("圆形");
    }

    public void drawTriangle(Shape r){
        System.out.println("三角形");
    }
}

//基类
class Shape{
    int m_type;
}

class Rectangle extends Shape {
    Rectangle(){
        super.m_type = 1;
    }
}

class Circle extends Shape {
    Circle(){
        super.m_type = 2;
    }
}

//新增画三角形
class Triangle extends Shape {
    Triangle(){
        super.m_type = 3;
    }
}

