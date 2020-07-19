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

<form action="${pageContext.request.contextPath}/login">
    username:<input type="text" name="username"/> <br>
    password:<input type="password" name="password"/> <br>
    <input type="submit" value="提交"/>
</form>

</body>
</html>
