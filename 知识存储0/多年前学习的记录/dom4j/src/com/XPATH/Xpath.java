package com.XPATH;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;

public class Xpath {

    static String path = "src/com/dom4j/book2.xml";

    public static void main(String[] args) throws Exception{
        run6();
    }


    //Xpath使用
    public static void run6() throws Exception{
        SAXReader reader = new SAXReader();
        Document document = reader.read(path);

        List<Node> list = document.selectNodes("//书架//书//作者");


        Node author2 = list.get(1);

        System.out.println(author2.getText());

    }

}
