<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.User" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jstl的set标签</title>
</head>
<body>
传统方式
<%
    int a= 10;
    pageContext.setAttribute("name","10",PageContext.REQUEST_SCOPE);
%>

JSTL方式
<c:set var="i" value="10" scope="request"></c:set>
${i}



<%
    User user = new User();
    user.setPassword("123");
    user.setUsername("alex");
    request.setAttribute("user",user);
%>
${user.username}


<!-- 修改对象指定属性的值 -->
<c:set target="${user}" property="username" value="小风"></c:set>
${user.username}


属性：
    var      定义属性
    value    存入的值
    scope    域范围
    target   目标
    property 修改的属性



</body>
</html>
