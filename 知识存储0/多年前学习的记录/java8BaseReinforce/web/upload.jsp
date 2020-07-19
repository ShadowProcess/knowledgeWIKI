<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>上传页面</title>
  </head>
  
  <body>

	<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
		<input type="file" name="f"><br>
		描述:<input type="text" name="description"><br>
		<input type="submit" value="提交">
	</form>
  </body>
</html>
