package com.fileupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 总结：关于文件上传时的乱码
 *  1.上传文件名称乱码
 *      servletFileUpload.setHeaderEncoding("utf-8");
 *  2.非上传组件内容乱码
 *      FileItem.getString("utf-8")
 *
 *  3.上传文件内容乱码
 *      不需要解决，因为我们在上传时，使用字节流进行复制
 *      字节流，不存在乱码
 */


@WebServlet(name = "Upload2Servlet",urlPatterns = "/upload2")
public class Upload2Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.创建DiskFileItemFactory
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 2.创建ServletFileUpload类
        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setHeaderEncoding("utf-8");

        // 3.底层通过request获取数据，进行解析，将解析的数据封装到 List<FileItem>
        try {
            // xxxx----- ???  xxxx-----  每一个这样的数据就是一个fileitem
            List<FileItem> files = upload.parseRequest(request);

            // 4.遍历集合，集合中的每一项，就是一个上传数据
            for (FileItem item : files) {

                if (item.isFormField()) {
                    //表单普通数据  例如： <input type="text" name="content">
                    String fieldName = item.getFieldName();
                    System.out.println("普通表单数据名："+fieldName);

                    String name = item.getName();
                    System.out.println("组件名[就是filename，普通数据没有这个名字，就返回null]："+name);

                    // 相当于request.getParameter(String name)
                    String content = item.getString();


                } else {
                    //就是上传组件  例如： <input type="file">
                    String fieldName = item.getFieldName();
                    System.out.println("文件组件名:"+fieldName);

                    //上传文件名称
                    String name = item.getName();
                    System.out.println("组件名[就是filename，上传文件的名称]："+name);
                    name = name.substring(name.lastIndexOf("\\")+1);

                    //上传文件的流
                    InputStream io = item.getInputStream(); // 获取上传文件的流

                    byte[] bytes = new byte[1024];
                    int len=-1;

                    FileOutputStream fos = new FileOutputStream("D:\\DevelopmentSoftWare\\ideaWebWorkSpace\\FileUploader\\web\\uploadFile\\"+name);
                    while ((len = io.read(bytes))!=-1) {
                            fos.write(bytes,0,len);
                    }
                    item.delete(); //删除temp中的临时文件
                    fos.close();
                    io.close();
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }


    //使用工具类，流的写法
    void copyIo(InputStream in, OutputStream out) throws IOException {
        IOUtils.copy(in,out);
    }
}
