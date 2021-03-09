package com.example.xml;

import java.util.TreeMap;

public class XmlUtil {

    public static void main(String[] args) {
        StringBuilder xml = new StringBuilder("<xml>");

        buildXml(xml, "1", "1");
        buildXml(xml, "2", "2");
        buildXml(xml, "3", "3");
        buildXml(xml, "4", "4");

        xml.append("</xml>");
        System.out.println(xml.toString());
    }

    /**
     * <![CDATA[]]用法：
     * <![CDATA[文本内容]]>
     *
     * @param xml
     * @param key
     * @param value
     */
    private static void buildXml(StringBuilder xml, String key, String value) {
        if ("特殊的文本内容".equals(key)) {
            xml.append("<").append(key).append("><![CDATA[")
                    .append(value)
                    .append("]]></").append(key).append(">");
        } else {
            xml.append("<").append(key).append(">")
                    .append(value)
                    .append("</").append(key).append(">");
        }
    }
}
