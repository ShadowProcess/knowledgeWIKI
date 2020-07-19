<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/10
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isErrorPage="true"
%>
<html>
<head>
    <title>Title</title>
</head>
<body>


<h3>亲，服务器正在维护！</h3>
<%= exception.getMessage()%>

        错误页面，如果其他页面设置了这个页面为错误页面
，那么其他页面发生错误，将跳转到这里
</body>
</html>
