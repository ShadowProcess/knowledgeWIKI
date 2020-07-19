package com.dom4j;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.util.Iterator;


public class Dom4jTest {

    public static void main(String[] args) throws Exception {
        run1();
    }


    //"com/dom4j/def.xml"
    public static Document getDocument(String url) throws Exception{
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(url);
        return document;
    }

    public static void run1() throws Exception {
        Document document = getDocument("src/com/dom4j/def.xml");

        Element root = document.getRootElement();

        for (Iterator i = root.elementIterator(); i.hasNext();) {
            Element element = (Element) i.next();
            System.out.println(element.getName());
        }
    }



    //递归的一种方式
    public void treeWalk(Document document) {
        treeWalk(document.getRootElement());
    }

    public void treeWalk(Element element) {
        for (int i=0; i < element.nodeCount() ;i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                treeWalk((Element) node);
            } else {
                // do something ...
            }
        }
    }



    //回写数据到XML文件

    public void write(Document document) throws Exception {
        XMLWriter writer = new XMLWriter(new FileWriter("output.xml"));
        writer.write(document);
        writer.close();

//        OutputFormat format = OutputFormat.createPrettyPrint();
//        writer = new XMLWriter(System.out,format);
//        writer.close();

//        OutputFormat format1 = OutputFormat.createCompactFormat();
//        writer = new XMLWriter(System.out,format);
//        writer.close();

    }


}
