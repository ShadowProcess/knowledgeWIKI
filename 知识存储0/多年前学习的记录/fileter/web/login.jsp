<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/30
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/login" method="post">
        username:<input type="text" name="username"> <br>
        password:<input type="password" name="password"> <br>
        <input type="checkbox" name="autoLogin" value="ok">自动登录 <br>
        <input type="submit" value="提交">
    </form>



</body>
</html>
