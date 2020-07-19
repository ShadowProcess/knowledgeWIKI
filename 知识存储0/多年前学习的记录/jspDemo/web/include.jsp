<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/12
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>网站的logo</h3>

include : 包含页面(页面布局)
语法
<%@ include file="要包含的文件地址(静态包含)" %>

被包含页面的头都删掉

<%@ include file="index.jsp" %>
<%@ include file="404.jsp" %>
<%@ include file="error.jsp" %>
<%@ include file="500.jsp" %>

<%
    int a = 10;
%>
</body>
</html>
