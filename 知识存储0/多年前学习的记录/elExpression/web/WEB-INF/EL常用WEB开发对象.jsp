<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/13
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    获取WEB开发中的对象

    pageScope
    requestScope
    sessionScope
    applicationScope

    param   获取请求参数    getParameter()

    paramValues  获取请求的参数  getParameterValues()

    header        获取请求头
    headerValues  获取所有请求头

    initParam     获取全局初始化参数

    cookie        获取cookie
                ${cookie.last}  //last为键名
    pageContext
                ${pageContext.request.remoteAddr}
                ${pageContext.request.contextPath}  //获取项目名



    ${header.refer}

    ${initParam.username}



</body>
</html>
