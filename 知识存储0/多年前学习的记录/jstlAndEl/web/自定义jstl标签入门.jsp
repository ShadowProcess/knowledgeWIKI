<%@ taglib prefix="myc" uri="http://www.itcast.cn/1110/myc" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/14
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

需求：自定义标签<myc:print/>在页面输出hello
     编写标签类
            编写一个类，继承SimpleTagSupport类
            重写两个方法，doTag()和setJspContext(JspContext pc)
            通过JspContext对象可以获取out对象向外输出内容

        提供tld文件进行配置
            通过tag标签配置自定义标签
            配置标签的名称<name>print<name>
            配置标签的类<tag-class>xxx.xxx.xxx</tag-class>
            配置标签体的内容<body-content>empty</body-content>

==============================================================================
自定义标签
实现SimpleTag标签

重写5个方法
void setJspContext(JspContext pc)
void setParent(JspTag parent)
void setJspBody(JspFragment jspBody)
void doTag()

JspTag getParent()

自定义标签的执行顺序
void setJspContext(JspContext pc)    默认把PageContext对象传过来，通过PageContext对象完成操作 * getOut
-> void setParent(JspTag parent)
-> void setJspBody(JspFragment jspBody)
-> void doTag()
==========================================================================

<myc:print/>   //执行到这个标签，就会去执行那个myc.tld文件配置的,对应的类
</body>
</html>
