<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/9/2
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script>
        //div会自动换行

        function addFile() {
            var div = document.getElementById("content");
            div.innerHTML += "<div><input type='file' name='f'/><input type='button' value='remove file' onclick='removeFile(this)'/> </div>";
        }

        function removeFile(btn) {
            document.getElementById("content").removeChild(btn.parentNode)
        }
    </script>
</head>
<body>

<input type="button" value="add File" onclick="addFile()">

<form action="${pageContext.request.contextPath}/multiFileUpload" enctype="multipart/form-data" method="post">

    <input type="file" name="f"> <br>

    <%--//支持客户端多选--%>
    <%--解决io流java.io.FileNotFoundException: 【D:\\***\\***】 (拒绝访问。)
    异常原因是因为在输入过着输出流中没有定义文件名,而只有目录。--%>
    <%--<input type="file" name="multiFile" multiple="multiple" size="200">--%>

    <div id="content"></div>
    <input type="submit" value="上传">

</form>

</body>
</html>
