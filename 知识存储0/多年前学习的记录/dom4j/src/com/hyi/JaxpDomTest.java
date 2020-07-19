package com.hyi;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class JaxpDomTest {

    public static void main(String[] args) throws Exception{
        add();
    }



    public static void run1() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse("web/Dom4j/test.xml");

        NodeList nodeList = document.getElementsByTagName("person");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.getTextContent());
        }
    }


    public static void add() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse("web/Dom4j/test.xml");

        NodeList nodeList = document.getElementsByTagName("person");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);


            Element element = document.createElement("团购");
            element.setTextContent("123元");

            node.appendChild(element);
        }


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.transform(new DOMSource(document),new StreamResult("web/Dom4j/test.xml"));
    }

}
