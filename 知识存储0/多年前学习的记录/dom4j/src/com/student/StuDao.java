package com.student;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.util.List;

public class StuDao {

    static String path = "src/com/student/student.xml";


    public static void main(String[] args) throws Exception{
        Student student = new Student("007","邦德","能打");
        addStu(student);
    }

    /**
     * 向student.xml添加内容
     */
    public static void addStu(Student student) throws Exception{

        SAXReader reader = new SAXReader();
        Document document = reader.read(path);

        Element root = document.getRootElement();

        List<Element> element1 = root.elements("student");

        Element element = element1.get(0);


        element.element("num").setText(student.getNum());

        element.element("name").setText(student.getName());

        element.element("desc").setText(student.getDesc());


        XMLWriter writer = new XMLWriter(new FileOutputStream(path));
        writer.write(document);
        writer.close();


    }

    public static void delStu() {

    }
}
