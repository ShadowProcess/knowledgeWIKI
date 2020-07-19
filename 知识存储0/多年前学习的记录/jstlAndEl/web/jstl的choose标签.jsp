<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

传统方式
<%
    int a= 10;

    if (a ==10) {
       out.print("123");
    } else if (a>10) {
        out.print("333");
    } else {
        out.print("111");
    }
%>



JSTL方式
<c:set var="i" value="10" scope="page"></c:set>
<c:choose>
    <c:when test="${i ge 10}">
        i>10
    </c:when>
    <c:when test="${i lt 10}">
        i<10
    </c:when>
    <c:otherwise>
        i = 10
    </c:otherwise>
</c:choose>


</body>
</html>
