<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/9/7
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>

<script>
    //1.得到XMLHttpRequest
    var xmlHttp = null;
    if (window.XMLHttpRequest) {
        //针对于现在浏览器包括IE7以上版本
        xmlHttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        ////针对于IE5,IE6
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //2.注册回调函数
    xmlHttp.onreadystatechange=function () {
        //5.处理响应数据，当数据全部返回，并且成功
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            //就是服务器response对象写到浏览器的数据
            alert(xmlHttp.responseText);
            alert(xmlHttp.responseXML);
        }
    };

    //3.Open
    //只是用于设置请求方式,以及URL路径,设置为异步
    xmlHttp.open("GET","http://localhost/day23/ajax1",true);

    //4.发送请求send
    xmlHttp.send(null);
    // null代表没有参数，如果有参数可以写成
    // "username=tom&password=123"

    //5.在回调函数中处理数据
        //a. 在XMLHttpRequest对象有一个属性 readyState
        //b. 0，1，2，3，4
    /**
     * 0    Uninitialized   初始化状态,XMLHttpRequest对象已创建或已被abort()方法重置
     * 1    Open            open()方法已调用，但是send()方法来调用，请求还没有被发送；
     * 2    Send            send()方法已调用，HTTP请求已发送到web服务器，未接收到响应
     * 3    Receiving       所有响应都已经接收到，响应体开始接收，但未完成
     * 4    Loaded          HTTP响应已经完全接收
     *
     */

    /**
     * Ajax操作中请求参数的设置
     *  1.对于get方式，参数设置
     *      直接在url后面拼接
     *      例如："${pageContext.request.contextPath}/ajax2?name=tom";
     *
     *  2.对于post请求方式，参数设置
     *      xmlHttp.open("POST","${pageContext.request.contextPath}/ajax2")
     *      xmlHttp.send("name=tom");
     *
     *      注意：如果使用post请求方式，还需要设置一个Http请求头
     *      【下面这句设置必须放在open方法下边】
     *      xmlHttp.setRequestHeader("content-type","application/x-www-form-urlencoded");
     */

</script>
</html>
