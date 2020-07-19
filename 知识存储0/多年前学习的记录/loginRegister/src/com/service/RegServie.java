package com.service;

import com.utils.MyXmlUtils;
import com.vo.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.IOException;
import java.util.List;

public class RegServie {


    /**
     * 操作XML完成注册
     * @param user
     * @return
     */
    public int regUser(User user) throws DocumentException, IOException {

        //使用工具类,操作XML
        Document document = MyXmlUtils.getDocument(MyXmlUtils.PATH);
        //解析XML
        Element root = document.getRootElement();


        /**
         * 判断重复问题
         */
        List<Element> elementList = root.elements("username");
       if (elementList != null || elementList.size()>0) {
           for (Element u : elementList) {
               String xmlName = u.elementText("username");
               //
               if (user.getUsername() == xmlName) {
                   System.out.println("哟用户名已经存在");
               } else {
                   System.out.println("该用户可以注册");
               }
           }
       }



        Element uEle = root.addElement("user");
        uEle.addElement("username").setText(user.getUsername());


        //回写
        MyXmlUtils.writeXml(document,MyXmlUtils.PATH);


        //返回1用户名重写了，返回2邮箱重名，返回0注册成功

        return 0;
    }
}
