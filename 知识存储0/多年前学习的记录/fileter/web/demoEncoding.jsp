<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/30
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
请输入中文

<form action="${pageContext.request.contextPath}/xx" method="post">
    <input type="text" name="msg1">
    <input type="text" name="msg2">

    <input type="submit" value="提交">
</form>


</body>
</html>
