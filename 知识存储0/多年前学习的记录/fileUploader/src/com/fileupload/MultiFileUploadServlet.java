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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MultiFileUploadServlet", urlPatterns = "/multiFileUpload")
public class MultiFileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取temp目录部署到tomcat后的绝对磁盘路径
        File temp = new File(this.getServletContext().getRealPath("/temp"));

        DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 100, temp);

        //创建ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);

        //判断是否为文件上传
        boolean flag = upload.isMultipartContent(request);
        if (flag) {
            //解决上传文件名中文乱码
            upload.setHeaderEncoding("UTF-8");
            //设置上传文件大小
            //upload.setSizeMax(1024 * 10 * 10); //总大小10M


            try {
                List<FileItem> items = upload.parseRequest(request);

                for (FileItem item : items) {
                    if (item.isFormField()) {
                        //非上传组件
                        System.out.println("组件名:" + item.getFieldName());
                        System.out.println("内容:" + item.getString("UTF-8"));
                    } else {
                        //上传文件名称
                        String name = item.getName();
                        System.out.println("组件名[就是filename，上传文件的名称]："+name);
                        name = name.substring(name.lastIndexOf("\\")+1);

                        //得到随机文件名
                        String randomFilename = FileUploadUtils.getUUIDFileName(name);

                        //得到随机目录
                        String randomDirectory = FileUploadUtils.getRandomDirectory(randomFilename);

                        //随机目录可能不存在需要创建
                        File rd = new File("D:\\DevelopmentSoftWare\\ideaWebWorkSpace\\FileUploader\\web\\uploadFile\\",randomDirectory);

                        if (!rd.exists()) {
                            rd.mkdirs();
                        }

                        IOUtils.copy(item.getInputStream(),new FileOutputStream(new File(rd,randomFilename)));
                        item.delete();

                        //上传文件的流
//                        InputStream io = item.getInputStream(); // 获取上传文件的流
//
//                        byte[] bytes = new byte[1024];
//                        int len=-1;
//
//                        FileOutputStream fos = new FileOutputStream(new File(rd,randomFilename));
//                        while ((len = io.read(bytes))!=-1) {
//                            fos.write(bytes,0,len);
//                        }
//                        item.delete(); //删除temp中的临时文件
//                        fos.close();
//                        io.close();
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }
}
