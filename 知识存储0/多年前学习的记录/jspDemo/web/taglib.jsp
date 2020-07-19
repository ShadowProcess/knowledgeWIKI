<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/13
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>taglib引入标签库的文件</title>
</head>
<body>

    taglib : 引入标签库文件

        uri:引入标签库文件的名称
        prefix: 标签库的名称



    Jsp内置对象--out

        内值对象            真实的对象
        request           HttpRequest       setAttribute  getAttribute
        response          HttpResponse      addCookie     getWriter
        session           HttpSession       setAttribute   getAttribute
        application       ServletContext    setAttribute  getAttribute

        config            ServletConfig     getInitParameter     getInitParameterNames
        exception         Throwable         getMessage

        out               JspWriter         writer()   print()

        page              Object            (不使用对象，jsp翻译之后当前页面对象)

        pageContext       PageContext        可以获取其它另外8个对象， setAttribute  getAttribute



        Out对象
            JspWriter     PrintWriter     response.getWriter()


    <!-- 浏览器得到结果：BBBB HELLO AAAA CCCC  为什么这样，看左边图-->
        <%= "HELLO" %>
        <% out.print("AAAA"); %>
        <% response.getWriter().print("BBBB"); %>
        <% out.print("CCCC");%>

</body>
</html>
