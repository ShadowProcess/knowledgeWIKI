<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  
  <body>

	<a href='${pageContext.request.contextPath}/upload.jsp'>上传</a><br>
	<a href="${pageContext.request.contextPath}/showDownload">下载</a>
  </body>
</html>
