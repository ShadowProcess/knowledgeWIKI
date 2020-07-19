package com.filedownload;

import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet(name = "FileDownloadServlet",urlPatterns = "/download")
public class FileDownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到下载的文件名称【注意乱码问题】
        String filename = request.getParameter("filename");

        //解决中文文件名乱码【下载文件显示时中文乱码问题】
        String realFilename = new String(filename.getBytes("iso8859-8"),"utf-8");




        //在文件中查找
        File file = new File("d:/upload/"+filename);
        FileInputStream in = new FileInputStream(file);


        if (file.exists()) {
            //要下载的文件存在

            //todo 要设置浏览器以附件打开  参数值取决于要下载的文件是什么
            //得到文件类型
            String mimeType = this.getServletContext().getMimeType(filename);
            System.out.printf("==="+mimeType);
            // 如果设置mimetype，浏览器可以解析的就直接展示了，不能解析的，直接下载
            response.setContentType(mimeType);  //例如：response.setContentType("image/bmp"); 告诉浏览器以什么方式打开文件

            //2.设置一个响应头，设置后的效果，就是无论返回是否可以解析，就是下载
            /**
             * 下载文件名显示的中文乱码问题
             * IE  要求filename必须是utf-8码
             * fireFox 要求filename必须是base64编码
             * Chrome 要求filename必须是utf-8码
             */
            String userAgent = request.getHeader("user-agent");
            System.out.printf(userAgent);

            if (userAgent.contains("MSIE")) {
               String 文件名 =  URLEncoder.encode(filename,"UTF-8");
            } else if (userAgent.contains("Firefox")) {
                BASE64Encoder base64Encoder = new BASE64Encoder();
                String 文件名 = base64Encoder.encode(filename.getBytes());
            } else if (userAgent.contains("Chrome")) {

            } else {
                // 其它浏览器
            }
            response.setHeader("content-disposition","attachment;filename=下载文件名称");


            //3.下载文件名中文乱码



            //通过流，将文件写会到浏览器端
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024*100];
            int len = -1;
            while ((len = in.read(b)) != -1) {
                os.write(b,0,len);
                os.flush();
            }

            os.close();
            in.close();

        } else {

            response.getWriter().write("file not exist");
        }

    }
}
