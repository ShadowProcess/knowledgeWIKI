<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jsp" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>


  三个指令
    page   设置页面属性

    include  页面包含(静态包含)  先把页面全复制到一个文件，最后编译成一个class文件

    taglib 引入标签库(今天)


  JSP的动作标签
    <jsp:forward page="index.jsp"></jsp:forward>   转发
    <jsp:param name="" value=""/>   传递参数

    <jsp:include page="xxx.jsp"></jsp:include>  动态包含

    <jsp:include file="xxx.jsp"></jsp:include>  静态包含

======================================================================================


  </body>
</html>
