<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
迭代数据 1-9
<c:forEach var="i" begin="1" end="10" step="1">
    ${i}
</c:forEach>

计算1到100的和
<c:set var="sum" value="0" scope="page">
    <c:forEach var="i" begin="1" end="100" step="1">
        <c:set var="sum" value="${sum+1}" scope="page"></c:set>
    </c:forEach>
</c:set>
${sum}

begin 从哪里开始
end   到哪里结合
step  步长


遍历10到100的偶数，每到第三个数显示红色
<c:forEach var="i" begin="10" end="100" step="2" varStatus="status">

    <c:choose>
        <c:when test="${status.count % 3 eq 0}">
            <font color="red">${i}</font>
        </c:when>
        <c:otherwise>
            ${i}
        </c:otherwise>
    </c:choose>

</c:forEach>

varStatus属性介绍： 【记录循环遍历的信息】
    index  现在指到成员的索引
    count  总共指到成员的总数
    first  现在是否是第一个成员
    last   现在是否为最后一个成员

</body>
</html>
