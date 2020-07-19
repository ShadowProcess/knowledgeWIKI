package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * 文件下载
 */

@WebServlet(name = "ServletFileDownload")
public class ServletFileDownload extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            //判断用户的浏览器
            String agent = request.getHeader("User-Agent");
            System.out.println("用户浏览器信息:" + agent);
            if (agent.contains("MSIE")) {
                //filename = URLEncoder.encode(filename,"UTF-8");
                System.out.println("IE浏览器");
            }

            System.out.println("远端访问地址:" + request.getRemoteAddr());

            run(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //文件下载
    public void run(HttpServletResponse response) throws Exception {

        /**设置浏览器打开改文件以附件的形式打开**/
        response.setHeader("Content-Disposition", "attachment;filename=123.png");

        // 获取到文件，读入输入流中
        String path = getServletContext().getRealPath("/images/123.png");
        System.out.println(path);
        InputStream in = new FileInputStream(path); //获取文件的绝对磁盘路径


        //包含文件的名称  c:\tomcat\webapps\day10\img\girl.jpg
        //截取文件的名称  girl.jpg
        //获取最后一个\的位置
        //index = path.lastIndexof("\\")  if(index != -1) { ...}


        // 通过Response读取到客户端
        OutputStream out = response.getOutputStream();

        byte[] b = new byte[1024];
        int len = 0;
        while ((len = in.read(b)) != -1) {
            out.write(b, 0, len);
        }

        in.close();
        out.close(); //服务器管理这个流，可以写，可以不写


    }
}
