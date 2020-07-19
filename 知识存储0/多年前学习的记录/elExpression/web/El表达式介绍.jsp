<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/13
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  El表达式：
    语法：
  <!--获取域对象的值-->
  <%
    String[] arrs = {"美美","到的","试试","外网"};

    pageContext.setAttribute("name","黄海波");
    request.setAttribute("name","美美");

  %>


  <!--获取集合的值-->
  <%
    List<String> list = new ArrayList<String>();
    list.add("美美");
    list.add("小风");
    request.setAttribute("list",list);
  %>


  、、如果没有指定域，那么从最小域取值
  ${requestScope.get("name")}

  ${arrs[2]}

  ${list[2]}

  <h3>Map集合的值</h3>
  <%
    Map<String,String> map = new HashMap<String,String>();
    map.put("aa","信息");
    map.put("bb","谢谢");
    request.setAttribute("map",map);
  %>

  ${map.get("aa")}


  </body>
</html>
