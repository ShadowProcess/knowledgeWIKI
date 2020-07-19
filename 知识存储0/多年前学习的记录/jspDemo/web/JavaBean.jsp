<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/13
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  String username= request.getParameter("username");
  String password = request.getParameter("password");
%>

<jsp:useBean id="com.Person"></jsp:useBean>

<jsp:setProperty property="username" name="u" ></jsp:setProperty>
<jsp:setProperty property="password" name="u" ></jsp:setProperty>

<jsp:getProperty property="username" name = "u"/>
<jsp:getProperty property="password" name = "u"/>


//内省 (Introspector) JDK的类




</body>
</html>
