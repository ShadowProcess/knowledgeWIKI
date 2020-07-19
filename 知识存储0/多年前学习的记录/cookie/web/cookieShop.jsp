<%@ page import="util.MyCookieUtil" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/9
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cookie购物</title>

    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
</head>
<body>

<a href="/CookieAndSession/product?id=1">手电筒</a> <br/>

<a href="/CookieAndSession/product?id=2">电视</a> <br/>

<a href="/CookieAndSession/product?id=3">冰箱</a> <br/>

<a href="/CookieAndSession/product?id=4">电脑</a> <br/>

<h4>浏览记录...</h4>

<%
    //获取cookie的value
    Cookie[] cookies = request.getCookies();

    Cookie cookie = MyCookieUtil.getCookieByName(cookies, "product");

    if (cookie != null) {
        String value = cookie.getValue();
        String[] ids = value.split("-");

        for (String id : ids) {
            if (id != null) {

%>
<a href="/Cookie/product?id=<%=id%>"><%=id%>
</a> <br/>
<%
            }
        }
    }
%>

<a href="/Cookie/delete">删除历史记录</a>

</body>
</html>
