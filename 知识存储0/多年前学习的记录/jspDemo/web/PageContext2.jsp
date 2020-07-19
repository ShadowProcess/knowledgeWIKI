<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/13
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
【PageContext对象】

域对象
* 自己存取值
* 向其它域存取值
setAttribute(String name,Object obj,int scope)
getAttribute(String name ,int scope)
findAttribute(String name)


可以获取其它8个对象
* 编写框架或者通用性高的代码

* 在JSP的时候（4个域对象）
ServletContext 整个web应用
Session 一次会话
Request 一次请求
PageContext 当前页面


<h4>向其他域中存入值</h4>
<%
    pageContext.setAttribute("name", "美美");
    // 下面这句等于上面
    pageContext.setAttribute("name", "美美", pageContext.PAGE_SCOPE);

    //向request域存入值
    request.setAttribute("name", "小风");
    pageContext.setAttribute("name", "小风", pageContext.REQUEST_SCOPE);

    //向Session域中存入值
    pageContext.setAttribute("name", "小风", pageContext.SESSION_SCOPE);


    //向ServletContext域存入数据
    pageContext.setAttribute("name", "小班长", pageContext.APPLICATION_SCOPE);

%>

<h4>取出其他域值</h4>

<%= pageContext.getAttribute("name", pageContext.SESSION_SCOPE)%>
<%= session.getAttribute("name")%>


${pageScope.name}
${requestScope.name}
${sessionScope.name}
${applicationScope.name}


<h1>JSP的标签(jsp的动作标签)</h1>

<jsp:forward page="500.jsp"></jsp:forward> 转发
<jsp:include page="404.jsp"></jsp:include> 页面的包含(动态包含)
<jsp:param name="username" value="meimei"/>  传递参数




把数据封装到javaBean中：（jsp页面完成的）
<jsp:useBean id=""></jsp:useBean>
<jsp:setProperty name="" property=""></jsp:setProperty>
<jsp:getProperty name="" property="class"/>


</body>
</html>
