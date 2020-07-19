<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>jstl的remove标签</title>
</head>
<body>

传统方式
<%
    request.setAttribute("name","美美");
    request.removeAttribute("name");
%>


jstl方式
<c:set var="name" value="小风" scope="page"></c:set>
${name} //默认从最小的域取，如果不指定
<c:remove var="name" scope="page"></c:remove>


remove标签介绍
    var   删除的属性名
    scope 在域的范围




</body>
</html>
