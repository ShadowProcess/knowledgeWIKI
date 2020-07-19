<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/9/5
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    如何你下载的文件能被浏览器解析，那么浏览器会直接打开，不能被浏览器
    解析，就是下载操作。

    客户端访问服务器静态资源文件时，静态资源文件是通过缺省Servlet返回
    在tomcat配置文件conf/web.xml 找到  ---org.apache.catalina.servlets.DefaultServlet

    1.超链接下载[其实底层还是defaultServlet来处理，从服务器用流写会浏览器]
        <a href="${pageContext.request.contextPath}/upload/a.bmp">a.bmp</a>

    2.编写服务器程序，读取服务器文件，完成下载
        必须设置两个头信息，来自MIME协议 Content-Type Content-Disposition

    <hr>
    <br><br>

    <a href="${pageContext.request.contextPath}/download?filename=a.bmp">a.bmp下载</a>


</body>
</html>
