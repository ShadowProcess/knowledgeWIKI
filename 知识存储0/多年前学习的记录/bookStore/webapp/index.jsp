<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2018/12/8
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookStore</title>
    <!--引入bootstrap-->
    <link rel="stylesheet" type="text/css" href="UI/bootstrap-3.3.7-dist/css/bootstrap.css"/>
    <!--先引入Jquery-->
    <script src="UI/jquery/jquery-3.3.1.min.js"></script>
    <!--再引入bootstrap的js-->
    <script src="UI/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

    <!-- 引入toastr插件-->
    <link rel="stylesheet" type="text/css" href="UI/toastr/build/toastr.min.css"/>
    <script src="UI/toastr/build/toastr.min.js"></script>

    <link href="favicon.ico" rel="shortcut icon">

    <style>
        body{
            background-image: url(UI/images/book.jpg);
            background-size: cover;
        }
    </style>

</head>

<body>
<div style="height: 150px"></div>
<div>
    <div class="row" align="center">
        <div class="col-sm-12">
            <form class="m-t" role="form" method="post">
                <h4 class="no-margins" style="font-family: 'Arial Black';font-size: 50px">BookStore</h4>
                <div class="form-group" style="width: 300px">
                    <input id="userName" name="userName" type="email" class="form-control" placeholder="用户名"
                           required="">
                </div>
                <div class="form-group" style="width: 300px">
                    <input id="passWord" name="passWord" type="password" class="form-control" placeholder="密码"
                           required="">
                </div>
                <button id="loginSubmit"  class="btn btn-primary block full-width m-b">登 录</button>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(function () {
        $('#loginSubmit').click(function () {
            $.ajax({
                url: "${pageContext.request.contextPath }/user/ajaxLogin",
                type: "post",
                data: {
                    userName: $("#userName").val(),
                    passWord: $("#passWord").val()
                },
               //contentType:"application/json;charset=utf-8", //注意：这里不能加下面这行，否则数据会传不到后台
                dataType: "json", //后台返回的数据格式：json/xml/html/script/jsonp/text
                async:true,

                //为了防止重复点击
                beforeSend: function() {
                    enableButton(true);
                },

                //成功
                success : function(data) {
                    console.log("Success:-->>" + data);
                    if (data == "passWordFail"){ //密码错误
                        toastr.error("密码错误!")
                    } else if (data == "nonentity"){     //用户不存在
                        toastr.info("用户不存在!")
                    } else {  //登录成功
                        //toastr.success("登录成功")
                        window.location.href="user/loginSuccess"
                    }
                },

                //错误情况
                error : function(error) {
                    console.log("Error:-->>" + error); //"Error:Can't get message"
                },

                //请求完成后回调函数 (请求成功或失败之后均调用)。
                complete: function(message) {
                    console.log("Complete:-->>" + message);
                    enableButton(false);
                }
            });
        });
    })


    function enableButton(flag) {
        $("#loginSubmit").prop("disabled",flag); // prop() 方法设置或返回被选元素的属性和值。
    }


    $(function () {
        toastr.options = {

            "closeButton": false, //是否显示关闭按钮

            "debug": false, //是否使用debug模式

            "positionClass": "toast-top-full-width",//弹出窗的位置

            "showDuration": "300",//显示的动画时间

            "hideDuration": "1000",//消失的动画时间

            "timeOut": "2000", //展现时间

            "extendedTimeOut": "1000",//加长展示时间

            "showEasing": "swing",//显示时的动画缓冲方式

            "hideEasing": "linear",//消失时的动画缓冲方式

            "showMethod": "fadeIn",//显示时的动画方式

            "hideMethod": "fadeOut" //消失时的动画方式
        };
    })
</script>
</body>
</html>
