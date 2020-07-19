<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 11:49
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
    String[] arrs = {"美美","小风","小苍"};
    request.setAttribute("arrs",arrs);
%>

<!-- for(String s : arrs) {} -->
<c:forEach var="s" items="${arrs}">
    ${ s }
</c:forEach>


遍历集合
<%
    List<String> list = new ArrayList<String>();
    list.add("小风");
    list.add("发育");
    request.setAttribute("list",list);
%>


<c:forEach var="s" items="${list}">
    ${s}
</c:forEach>


<%
    Map<String,String> map = new HashMap<String,String>();
    map.put("aa","美美");
    map.put("bb","小风");
    map.put("cc","芙蓉");
    request.setAttribute("map",map);
%>

<c:forEach var="entry" items="${map}">
    ${entry.key} -- ${entry.value}
</c:forEach>

遍历对象的集合
<%
    List<User> users = new ArrayList<User>();
    users.add(new User());
    users.add(new User());
    request.setAttribute("users",users);
%>
<c:forEach var="user" items="${users}">
    ${user.username} -- ${user.password}
</c:forEach>


foreach讲解
    循环遍历数据(数组，集合，Map集合)

    属性
        var 遍历数据的类型
        items 需要遍历的内容

</body>
</html>
