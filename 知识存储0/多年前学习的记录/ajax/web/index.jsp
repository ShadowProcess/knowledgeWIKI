<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/9/7
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  Ajax是什么,它有什么用?

  AJAX即 “Asynchronous Javascript And XML”
  (异步JavaScript和XML)

  是指一种创建交互式网页应用的网页开发技术

  Ajax = 异步  JavaScript和XML（标准通用标记语言的子集）

  问题：异步是什么？
  异步操作的核心 XMLHttpRequest对象

  <hr>


  </body>
  <script>
    var xmlHttp;
    function loadXMLDoc() {
      xmlHttp = null;
      if (window.XMLHttpRequest) {
        //code for all new browsers
        xmlHttp = new XMLHttpRequest();
      } else if (window.ActiveXObject) {
        // code for IE5 and IE6
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
      }

      if (xmlHttp!=null) {
        xmlHttp.onreadystatechange = state_Change;
        xmlHttp.open("GET",url,true);
        xmlHttp.send(null);
      } else {
        alert("your browser does not support XMLHTTP.");
      }

      function state_Change() {
        if (xmlHttp.readyState == 4) {
            // 4 = "loaded"
          if (xmlHttp.status == 200) {
            // 200 = ok
          }
        }
      }
    }
  </script>


</html>
