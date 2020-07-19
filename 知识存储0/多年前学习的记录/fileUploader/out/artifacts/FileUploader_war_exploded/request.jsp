<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/9/1
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

//测试 get方法  后台request获取的流

<form action="${pageContext.request.contextPath}/request"  method="get">
    <input type="text" name="demo" value="123">
    <input type="submit" value="上传">
</form>

</body>
</html>
