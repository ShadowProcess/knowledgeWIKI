package com.fileupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Upload3Servlet",urlPatterns = "/upload3")
public class Upload3Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //this.getServletContext().getRealPath("xx") 获取web工程部署到tomcat后的工程路径
        File file = new File(this.getServletContext().getRealPath("/temp"));  // 获取temp目录部署到tomcat后的绝对磁盘路径
        DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 10, file);

        // 2.创建ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 用于解决上传[中文]文件名乱码问题
        upload.setHeaderEncoding("UTF-8");
        //如果使用request.setCharacterEncoding("UTF-8");//也可以但是不建议使用


        //设置单个文件上传大小，如果前台上传文件超出大小，如果超大，那么这个后台将抛出异常
        upload.setFileSizeMax(1024);
        //设置总文件总上传大小
        upload.setSizeMax(1024*10);

        //用于判断是否为上传操作，也就是判断表单中是否有multipart设置
        boolean flag = upload.isMultipartContent(request);

        try {
            //解析request，得到所有上传项，每一个item就相当于一个上传项
            List<FileItem> itemList = upload.parseRequest(request);


            //得到所有上传项
            for (FileItem item : itemList) {
                if (item.isFormField()) { //表单普通数据

                }
            }


        } catch (FileUploadException e) {
            e.printStackTrace();
        }


    }
}
