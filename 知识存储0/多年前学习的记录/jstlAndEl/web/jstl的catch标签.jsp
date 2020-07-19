<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
传统方式
<%
    try{

    }catch (Exception e){
        e.printStackTrace();
    }
%>



JSTL的方式 [将异常信息保存在e中]
<c:catch var="e">
    <%
        int i = 10/0;
    %>
</c:catch>
${e.message}


c:catch
    属性： var 将异常信息保存在var中
    代码：


</body>
</html>
