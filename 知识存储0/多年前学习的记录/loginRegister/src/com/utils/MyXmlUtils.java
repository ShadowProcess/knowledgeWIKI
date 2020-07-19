package com.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class MyXmlUtils {


    public static final String PATH = "com/user.xml";

    //获取Document对象
    public static Document getDocument(String path) throws DocumentException {

        SAXReader reader = new SAXReader();

        Document document = reader.read(PATH);

        return document;
    }


    //回写
    public static void writeXml(Document document,String path) throws IOException {

        OutputFormat f = OutputFormat.createPrettyPrint();

        f.setEncoding("UTF-8");

        XMLWriter writer = new XMLWriter(new FileOutputStream(path),f);

        writer.write(document);
        writer.close();
    }




}
