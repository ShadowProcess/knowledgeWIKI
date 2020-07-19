<%@ taglib prefix="myc" uri="http://www.itcast.cn/1110/myc" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/15
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<myc:print/>

<myc:out>要输出的内容!</myc:out>

//带有标签主体的标签
//    * 编写类，继承SimpleTagSupport
//    * 重写doTag()方法
//    * 获取标签主体
            JspFragment jf = getJspBody();
            jf.invoke(null);  //虽然传null，但是默认是out
    配置
    <!--配置自定义带主体的标签-->
    <tag>
        <!--配置自定义标签的名称-->
         <name>out</name>
        <tag-class>com.TagDemo2</tag-class>
        <!--配置标签主体-->
        <body-content>scriptless</body-content>
    </tag>


    <body-content></body-content>元素的可选值有；
    empty：不能有标签体的内容
    scriptless:可以有标签体


</body>
</html>
