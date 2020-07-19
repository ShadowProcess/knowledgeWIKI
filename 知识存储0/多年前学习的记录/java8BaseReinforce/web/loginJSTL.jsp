<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            document.getElementById("selectCountry").submit()
        }
    </script>
    
</head>
<body>
<form id="selectCountry" action="${pageContext.request.contextPath}/login.jsp" method="post">
    <select name="country"  onchange="sendForm()">
        <option>---请选择国家---</option>
        <option value="china">中国</option>
        <option value="us">US</option>
    </select>
</form>

<br> 使用jstl标签库完成
<br>

<fmt:setLocale value="${param.local}" /> <!--相当于 获取一个Locale对象-->
<fmt:setBundle basename="message" var="bundle" scope="page" />


<fmt:message bundle="${bundle}" key="username" />
<fmt:message bundle="${bundle}" key="password"/>
<fmt:message bundle="${bundle}" key="submit"/>

</body>
</html>
