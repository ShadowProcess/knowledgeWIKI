<%@page import="com.atguigu.spring.struts2.beans.Person"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<% 
		//1. 从 appication 域对象中得到 IOC 容器的实例
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		
		//2. 从 IOC 容器中得到 bean
		Person person = ctx.getBean(Person.class);
		
		//3. 使用 bean
		person.hello();
	%>
	
</body>
</html>