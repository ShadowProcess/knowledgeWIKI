<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/9
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

结算成功！

<%
    //获取购物车
    Map<String, Integer> map = (Map<String, Integer>) session.getAttribute("cart");

    //把购物车商品信息和数量显示到页面
    if (map != null) {
        //循环遍历
        Set<String> set = map.keySet();

        for (String i : set) {
%>
<h3>您购买商品是<%= i%>,数量是<%=map.get(i)%>
</h3>
<%
        }
    } else {

%>
   您还未购物
<%

    }

%>

</body>
</html>
