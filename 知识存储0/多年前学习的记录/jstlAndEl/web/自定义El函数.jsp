<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="myfn" uri="http://www.itcast.cn/1110/myfn" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

本页第一行引入

${fn:length("abcdefg")}

${fn:toUpperCase("abc")}

自定义El函数(入门)
1.编写一个类，方法必须是静态方法
2.在WEB-INF目录下创建myfn.tld的文件，配置
<%--<?xml version="1.0" encoding="UTF-8"?>
<taglib xmlns="http://java.sun.com/xml/ns/j2ee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd"
        version="2.0">

    <tlib-version>1.0</tlib-version>
    <short-name>myfn</short-name>
    <uri>http://www.itcast.cn/1110/myfn</uri>

    <!--配置自定义EL的函数-->
    <function>
        <!--配置方法名称-->
        <name>sayHi</name>
        <!--方法所在的类-->
        <function-class>com.ElDemo</function-class>
        <!--配置方法的签名-->
        <function-signature>java.lang.String sayHello(java.lang.String)</function-signature>
    </function>
</taglib>--%>

3.在该页面引入[自定义EL函数]
${myfn:sayHi("ni hao")}








</body>
</html>
