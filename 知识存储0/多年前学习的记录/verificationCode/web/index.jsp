<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 2019/8/7
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>验证码[JDK里边的东西]</title>
  </head>
  <body>
    1.先在内存中生成一张图片【画布，纸】
      BufferImage(int width,int height,int imageType)

    2.获取画笔对象
      Graphics getGraphics()

    3.随机生成的，但是数据是准备好的
      随机生成4个字符， Random类  Math.random()

    4.通过画笔对象把数字画到图片上
      drawString(String str,int x,int y)

    5.画干扰线
      drawLine(int x1,int y1,int x2,int y2)

    6.需要把内存中的图片输出到客户端
    ImageIo.write(RenderedImage im,String formatName,OutputStream output)

    BufferedImage
    "jpg"
    response.getOutputStream()

    <hr/>

    <a href="./code">访问验证码</a>


  </body>
</html>
