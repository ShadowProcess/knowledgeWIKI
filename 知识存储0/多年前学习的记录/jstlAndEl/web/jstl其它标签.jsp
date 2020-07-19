<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
============================================================
《import标签》

包含页面,context虚拟路径,不写也ok
<c:import url="index.jsp" context="/JstlEl" var="i" scope="page">
    <c:param name="username" value="meimei"></c:param>  <%--向包含的页面传递参数--%>
</c:import>
${i}

============================================================
《url标签》

c:url
标签用于在JSP页面中构造一个URL地址，其主要目的是实现URL重写
URL重写就是将会话标识号以参数的形式附加在URL地址后面
http://localhost/day12/demo?jessionid=xxxxxxxxxxxxxxx

<c:url var="i" value="/index.jsp" scope="request" context="/JstlAndEl">
    <c:param name="username" value="张三"></c:param>
</c:url>
<a href="${i}">choose</a>

============================================================
《redirect标签》

重定向

<c:redirect url="/index.jsp" context="/JstlAndEl">
    <c:param name="username" value="xiaofeng"></c:param>
</c:redirect>

============================================================
《forToken标签》

分割字符串

<s:set var="i" value="aa,bb,cc" scope="page"></s:set>
<c:forTokens items="${i}" delims="," var="x">
    ${x}
</c:forTokens>

</body>
</html>
