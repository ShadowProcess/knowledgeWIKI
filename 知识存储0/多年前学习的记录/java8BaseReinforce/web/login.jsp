<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/9/6
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>国际化</title>

    <script>
        function sendForm() {
            
        }
    </script>
    
</head>
<body>
<form action="${pageContext.request.contextPath}/login.jsp" method="post">
    <select name="country"  onchange="sendForm()">
        <option>---请选择国家---</option>
        <option value="china">中国</option>
        <option value="us">US</option>
    </select>
</form>

<%
    // 自动识别浏览器设置的首选语言
    //String countryA = request.getHeader("accept-language");
    //if (countryA.startsWith("en-US")){
        //英语
    //}
    String country = request.getParameter("country");
    ResourceBundle bundle = null;

    if ("us".equals(country)) {
        bundle = ResourceBundle.getBundle("message", Locale.US);
    } else {
        bundle = ResourceBundle.getBundle("message",Locale.CHINA);
    }
%>

    <h1><%=bundle.getString("title")%></h1>
    <form action="">
        <%=bundle.getString("username")%> : <input type="text" name="username"> <br>
        <%=bundle.getString("password")%> : <input
            type="password" name="password"> <br> <input
            type="submit" value="<%bundle.getString("submit");%>">
    </form>

</body>
</html>
