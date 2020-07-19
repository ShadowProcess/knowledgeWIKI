<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/5
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <h3>HTML的相对路径</h3>
  <a href="./demo5">当前目录访问</a> <br/>
  <a href="demo5">访问Servlet1</a> <br/>
  <a href="../demo5">上级目录访问[../demo5 找不到文件 404]</a> <br/>

  <hr/>

  <h3>HTML的绝对路径</h3>
  <a href="http://localhost:8080/ServletDemo/demo5">绝对路径1</a> <br>
  <a href="/ServletDemo/demo5">绝对路径2</a> <br>


  <hr/>

  客户端的绝对路径
    /day09/demo5  需要写项目名

  服务器的绝对路径
    /demo5    不能写项目名


  <hr/>

  <a href="./demo6">测试参数设置</a>

  </body>
</html>
