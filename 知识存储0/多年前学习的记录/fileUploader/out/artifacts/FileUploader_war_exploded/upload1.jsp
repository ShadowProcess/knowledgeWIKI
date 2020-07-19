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

enctype 默认值为 application/x-www-form-urlencoded  这个传递的数据底层为 键值对形式，中间用&符号分隔 <br>

enctype 设置为 multipart/form-data  这个表示如果有文件，底层将使用  xxxxx------  中间为二进制流的数据  xxxxx----- <br>

<form action="${pageContext.request.contextPath}/upload1" enctype="multipart/form-data" method="post">
    <input type="file" name="f">
    <input type="submit" value="上传">
</form>

</body>
</html>
