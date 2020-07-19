<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/6
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <%--<meta http-equiv="refresh" content="5;url=http://www.baidu.com">   这句可实现本页面5秒跳转       --%>
  </head>
  <body>

  <form action="./login" method="post">
    <table border="1">
      <tr>
        <td>名字</td>
        <td><input type="text" name="username"></td>
      </tr>
      <tr>
        <td>密码</td>
        <td><input type="text" name="password"></td>
      </tr>

      <tr>
        <td><input type="submit" value="登录"></td>
        <td></td>
      </tr>
    </table>

  </form>

  <hr/>

  <a href="./refresh">5庙</a> <br/>

  <hr/>

  <a href="./encode">中文乱码问题</a>

  </body>
</html>
