<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/10
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" session="true"

         import="java.util.Arrays"
         errorPage="error.jsp"

%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
设置jsp文件打开的方式：
window -- 选项 -- general -- editors -- 文件关联 -- 。jsp

在JSP页面
import属性可以出现多次，其它属性只能出现一次

language ： JSP文件允许嵌入的语言，只支持一种，只支持java 不需要改变

extends ： JSP翻译为Servlet文件，servlet文件继承的一个类

session ： 设置session是否可使用

autoFlush ：设置默认刷新缓冲区 （不要修改）

buffer ： 设置缓冲区的大小 默认8kb

errorPage ： 指定错误页面

isErrorPage ： 设置值，可以使用exception对象   true可以使用exception对象

contentType ：  服务器输出该页面内容采用的编码

pageEncoding : Jsp翻译成Servlet文件时，采用的编码

isElIgnore  :  是否忽略页面的el表达式


重点： session  import  contentType  pageEncoding  isElIgnored



配置全局的错误页面
在web.xml配置


<%
    List list = new ArrayList();
%>

<%
    session.setAttribute("username", "小风");
%>


&{username}

</body>
</html>
