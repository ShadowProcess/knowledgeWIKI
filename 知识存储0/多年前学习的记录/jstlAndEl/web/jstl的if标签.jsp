<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 11:29
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
    int a = 10;
    if (a >= 10) {
        out.print("a>=10");
    } else {
        out.print("a<10");
    }
%>


JSTL方式
<c:set var="i" value="10" scope="page"></c:set>
<c:if test="${i ge 10}" var="x" scope="page">
    i>=10 //这是输出,将结果保存在x中
</c:if>

<c:if test="${i lt 10}">
    i<10  //这是输出
</c:if>

</body>
</html>
