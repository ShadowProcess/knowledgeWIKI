<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>  1.0版本
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>      1.1版本
<html>
<head>
    <title>jstl的out标签</title>
</head>
<body>

<h4>传统方式</h4>
<%= "hello" %>
<%
    int a= 10;
    request.setAttribute("name","xy");
%>


<h4>JSTL的方式</h4>
<c:out value="hello"></c:out>
<c:out value="${name}"></c:out>


<%-- "" --%>
<c:out value="${city}" default="北京"></c:out>
<c:out value="<a href='#'>超链接</a>" escapeXml="false"></c:out>


jstl的out标签总结
    c:out  输出内容
            value  输出的内容(常量或者变量)
            default: 默认值
            escapeXml" 默认是true  进行转义  设置为false  不转义

</body>
</html>
