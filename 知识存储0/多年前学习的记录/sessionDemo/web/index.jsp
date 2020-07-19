<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/9
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>

    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
  </head>
  <body>

  <a href="/Session/session">访问Session</a>
  <hr/>

  ${sessionScope.get("uniqueId")}


  <hr/>

  <a href="/Session/shop?id=0">手电筒</a>
  <a href="/Session/shop?id=1">冰箱</a>
  <a href="/Session/shop?id=2">电视</a>

  </body>
</html>
