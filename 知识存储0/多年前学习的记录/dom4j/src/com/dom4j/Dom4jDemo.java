package com.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.util.List;

public class Dom4jDemo {

    static String path = "src/com/dom4j/book2.xml";

    public static void main(String[] args) throws Exception {
        run5();
    }


    /**
     * 遍历节点
     * @throws Exception
     */
    public static void run1() throws Exception{
        //获取解析器对象
        SAXReader reader = new SAXReader();

        //解析XML，返回Document对象
        Document document = reader.read(path);

        //获取根节点
        Element root = document.getRootElement();

        //获取书的节点
        List<Element> books = root.elements("书");

        //获取第二本书
        Element book2 = books.get(1); //第二本

        //获取作者的标签
        Element author2 = book2.element("作者");

        //标签内的内容
        System.out.println(author2.getText());
    }


    /**
     * 添加节点
     */
    public static void run2() throws Exception{
        //获取解析器对象
        SAXReader reader = new SAXReader();

        //解析XML 得到Document对象
        Document document = reader.read(path);

        //获取根节点
        Element root = document.getRootElement();

        //获取第二本书
        Element book2 = (Element) root.elements("书").get(1);

        //添加元素
        book2.addElement("猫").setText("我是猫");


        //创建漂亮的格式 [格式化显示]
        OutputFormat formatPretty = OutputFormat.createPrettyPrint();
        //formatPretty.setEncoding("UTF-8"); 可设置编码，默认UTF-8

        //创建压缩的格式 [一行显示]
        OutputFormat formatCompact = OutputFormat.createCompactFormat();
        //formatPretty.setEncoding("UTF-8"); 可设置编码，默认UTF-8

        //回写类
        XMLWriter writer = new XMLWriter(new FileOutputStream(path),formatPretty);
        writer.write(document); //回写文档对象
        writer.close();
    }


    /**
     * 在指定位置添加节点
     */
    public static void run3() throws Exception{
        //获取解析器对象
        SAXReader reader = new SAXReader();

        //解析XML
        Document document = reader.read(path);

        //获取根节点
        Element root = document.getRootElement();

        Element book2 = (Element) root.elements("书").get(1);

        List<Element> list = book2.elements();

        Element dog = DocumentHelper.createElement("狗熊");
        dog.setText("大狗熊");

        list.add(1,dog);

        OutputFormat format = OutputFormat.createPrettyPrint();


        //回写
        XMLWriter writer = new XMLWriter(new FileOutputStream(path),format);
        writer.write(document);
        writer.close();
    }


    /**
     * 删除节点
     */
    public static void run4() throws Exception{
        //获取解析器对象
        SAXReader reader = new SAXReader();

        //解析XML
        Document document = reader.read(path);

        //获取根节点
        Element root = document.getRootElement();

        Element book2 = (Element) root.elements("书").get(1);

        Element cat = book2.element("猫");

        book2.remove(cat);


        OutputFormat format = OutputFormat.createPrettyPrint();


        //回写
        XMLWriter writer = new XMLWriter(new FileOutputStream(path),format);
        writer.write(document);
        writer.close();
    }


    /**
     * 修改内容
     */
    public static void run5() throws Exception{
        //获取解析器对象
        SAXReader reader = new SAXReader();

        //解析XML
        Document document = reader.read(path);

        //获取根节点
        Element root = document.getRootElement();

        Element book2 = (Element) root.elements("书").get(1);

        Element dog = book2.element("狗熊");
        dog.setText("小狗熊");


        OutputFormat format = OutputFormat.createPrettyPrint();

        //回写
        XMLWriter writer = new XMLWriter(new FileOutputStream(path),format);
        writer.write(document);
        writer.close();
    }


}
