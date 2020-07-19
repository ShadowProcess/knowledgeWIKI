<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/30
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

      当前用户:${user.username} <br>   如果不指定域，默认从最小的域中去取pageContext->Request->Session->ServletContext

  </body>
</html>
