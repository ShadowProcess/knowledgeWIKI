<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/15
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>

    <script>
      function run() {
        // 校验表单数据
        // 返回true，表单才能提交
        var uname = document.form1.username.value; //拿到表单form1输入的用户名
      }
    </script>
  </head>
  <body>

    <form name="form1" action="/LoginRegister/mvc" method="post" onsubmit="return run()">
      <input type="text" name="username">


      验证码：<img href="#" onclick="show(); return false;"  id="imgId" src="${pageContext.request.contextPath}"/chengckcode>

      //1.加#防止页面跳转
      //2.show() 方法后边加return false，也可以防止页面跳转，因为click会先执行show()

    </form>

    ${pageContext.request.contextPath}
    //获取项目路径

  </body>

  <script>
    function show() {
      var image = document.getElementById("imgId");
      //防止，刷新页面时，图片读取了缓存
      image.src = "${pageContext.request.contextPath}/checkcode?"+new Date().getTime();
    }

  </script>

</html>
