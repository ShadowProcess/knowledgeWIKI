package com.hyi;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class JaxpSaxTest {

    public static void main(String[] args) throws Exception {
        run1();
    }


    /**
     * 获取所有解析内容
     */
    public static void run1() throws Exception{
        //获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();

        //获取解析器
        SAXParser p = factory.newSAXParser();

        //解析
        p.parse("src/com/hyi/abc.xml",new MyHandler2());
    }
}


/**
 * 自定义解析器
 */
class MyHandler2 extends DefaultHandler{

    //如果解析到作者时，设置为true
    private boolean flag = false;

    //只要已解析开始标签时，默认调用该方法，把解析内容赋值给参数
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //super.startElement(uri, localName, qName, attributes);

        //System.out.println("开始标签"+qName);

        if (qName == "title") {
            flag = true;
        }

    }

    //控制characters的输出，我只在解析作者的时候，才打印
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //super.characters(ch, start, length);

        if (flag) {
            String str = new String(ch,start,length);
            System.out.println(str);
            flag = false;
        }

    }

    //只要已解析结束标签时，默认调用该方法，把解析内容赋值给参数
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //super.endElement(uri, localName, qName);
        //System.out.println("结束标签:"+qName);
    }

}



/**
 * 自定义解析器
 */
class MyHandler extends DefaultHandler{

    //只要已解析开始标签时，默认调用该方法，把解析内容赋值给参数
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //super.startElement(uri, localName, qName, attributes);

        System.out.println("开始标签"+qName);

    }


    //只要已解析文本时，默认调用该方法，把解析内容赋值给参数
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //super.characters(ch, start, length);
        String str = new String(ch,start,length);
        System.out.println(str);
    }

    //只要已解析结束标签时，默认调用该方法，把解析内容赋值给参数
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //super.endElement(uri, localName, qName);
        System.out.println("结束标签:"+qName);
    }

}

