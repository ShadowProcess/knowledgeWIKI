<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/7
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$

  <form action="/VerifyAndLogin/checkCode" method="post">
    <table border="1" width="70%">
      <tr>
        <td>输入姓名</td>
        <td><input type="text" name="username"></td>
      </tr>

      <tr>
        <td>输入密码</td>
        <td><input type="password" name="password"></td>
      </tr>

      <tr>
        <td>验证码</td>
        <td>
          <input type="text" name="code">
          <image id="imgId" src="/VerifyAndLogin/checkCode"></image>
          <a href="#" onclick="changeCode()">看不清,换一张</a>
        </td>
      </tr>

      <tr>
        <td colspan="2">
          <input type="submit" value="登陆">
        </td>
      </tr>
    </table>
  </form>


  <hr/>


  <form action="/VerifyAndLogin/ServletRequest" method="post">
      <table>

        <tr>
          <td>名字</td>
          <td>
            <input type="text" name="username">
          </td>
        </tr>

        <tr>
          <td>密码</td>
          <td>
            <input type="password" name="password">
          </td>
        </tr>

        <tr>
          <td><input type="radio" name="sex" value="nan">男</td>
          <td><input type="radio" name="sex" value="nan">女</td>
        </tr>

        <tr>
          <td>选择爱好</td>
          <td>
            <input type="checkbox" name="love" value="lq">篮球
            <input type="checkbox" name="love" value="zq">足球
            <input type="checkbox" name="love" value="pq">拍球
          </td>
        </tr>

        <tr>
          <td>选择城市</td>

          <td>
            <select name="city" id="">
              <option value="none">---请选择---</option>
              <option value="bj">北京</option>
              <option value="sh">上海</option>
              <option value="gz">广州</option>
            </select>

          </td>
        </tr>

        <tr>
          <td colspan="2">
            <input type="submit" value="提交">
          </td>
        </tr>
      </table>
  </form>



  </body>

  <script>

    //看不清，换一张
    function changeCode() {
        //获取图片
        var image = document.getElementById("imgId")
        image.src = "/VerifyAndLogin/checkCode?"+new Date().getTime();
        //在请求后面加时间戳，不让浏览器读取缓存，保证每一次都是新的请求
    }

  </script>

</html>
