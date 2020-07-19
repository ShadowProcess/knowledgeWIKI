import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;

/**
 * JAXP的DOM解析XML
 */
public class XmlParseDiy {


    public static void main(String[] args) throws Exception {

        deleteNode();
    }


    /**
     * 遍历父节点
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void run1() throws Exception {
        Document document = getDocument();

        NodeList nodeList = document.getElementsByTagName("person");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            System.out.println(node.getTextContent());
        }
    }


    /**
     * 添加子节点
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     */
    public static void add() throws Exception, IOException, SAXException, TransformerException {

        Document document = getDocument();

        NodeList nodeList = document.getElementsByTagName("person");

        Element element = document.createElement("person1");
        Text text = document.createTextNode("自创建");
        element.appendChild(text);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            node.getParentNode().appendChild(element);
        }

        /**
         * 回写到XML
         */
        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.transform(new DOMSource(document),new StreamResult("web/XML/demo.xml"));
    }


    public static Document getDocument() throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xp = factory.newDocumentBuilder();

        Document document = xp.parse("web/XML/demo.xml");
        return document;
    }


    public static void deleteNode () throws Exception {
        Document document = getDocument();

        NodeList nodeList = document.getElementsByTagName("person1");

        Node node = nodeList.item(0);

        node.getParentNode().removeChild(node);

        /**
         * 回写到XML
         */
        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.transform(new DOMSource(document),new StreamResult("web/XML/demo.xml"));

    }
}
