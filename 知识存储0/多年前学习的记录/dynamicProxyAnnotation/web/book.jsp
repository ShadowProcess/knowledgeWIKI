<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/9/9
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    当前用户:${user.username} <br>
    <hr>
    <a href="${pageContext.request.contextPath}/book">book add</a>
    <br>
    <a href="${pageContext.request.contextPath}/book">book update</a>
    <br>
    <a href="${pageContext.request.contextPath}/book">book delete</a>
    <br>
    <a href="${pageContext.request.contextPath}/book">book search</a>

</body>
</html>
