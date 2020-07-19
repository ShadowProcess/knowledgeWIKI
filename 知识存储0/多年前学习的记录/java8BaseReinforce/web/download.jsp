<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'index.jsp' starting page</title>
</head>

<body>
	<table border="1" align="center" width="65%">
		<tr>
			<td>文件名称</td>
			<td>文件描述</td>
			<td>下载操作</td>
		</tr>

		<c:forEach items="${rs}" var="r">
			<tr>
				<td>${r.realname}</td>
				<td>${r.description }</td>
				<td><a href='${pageContext.request.contextPath}/download?id=${r.id}'>下载</a>
				</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>
