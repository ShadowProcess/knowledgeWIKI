<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/18
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body onload="run()">

//拿到指定名字Cookie的值,非中文
${cookie.username.value}


<form action="${pageContext.request.contextPath}/login"></form>

<input type="text" name="username">
<input type="text" name="password">
<input type="checkbox" name="remember" value="remember">记住用户名 <br/>

<input type="submit" value="提交">

登录记住用户名【使用cookie】，看LoginServlet怎么写的

=================================

Cookie不支持中文

如何记住中文用户名？
   先把中文编码，等取出时在解码；

//拿到指定名字Cookie的值,中文【后台以UTF8编码】

方式1：
    <%
        //先获取cookie
        //URLDecoder.decode("username","UTF-8");
    %>

方式2：
    自定义EL函数，解码


方式3：
    写js程序处理



</body>
<script>
    function run() {
        //接收cookie
        var str = "${cookie.username.value}";

        //解码
        var newStr = decodeURI(str);

        //赋值
        document.getElementById("xx").value = newStr;
    }
</script>

</html>
