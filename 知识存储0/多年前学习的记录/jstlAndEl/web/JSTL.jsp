<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    JSTL 标签库

    作用：和EL表达式一起  取代<% %>
    版本：1.0  1.1  1.2 (区别不大)


    标签库：
        c.tld   核心的标签库
        fn.tld  EL表达式标签库
        sql.tld 数据库标签库

<%
    int a = 10;
    if ( a==10 ) {

    }
%>

<%= a= 10 %>

<h4>使用jstl方式</h4>

<c:set var="i" value="10" scope="page"></c:set>   在page域中存入值

<c:if test="${i eq 10}">
    i = 10;
</c:if>

</body>
</html>
