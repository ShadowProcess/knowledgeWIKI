package com;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "ServletRequest")
public class ServletRequest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("================================================");

        /**
         *  request获取中文乱码
         *      * post请求
         *      setCharacterEncoding(String env) 设置request的缓冲区编码
         *
         *      * get请求
         *      解决方案一：修改tomcat/conf/server.xml
         * 						<Connector port="80" protocol="HTTP/1.1"
         * 								   connectionTimeout="20000"
         * 								   redirectPort="8443" URIEncoding="utf-8"/>
         * 					* 必须有修改tomcat服务器配置文件权限
         *
         * 					解决方案二：逆向编解码
         * 					username = URLEncoder.encode(username, "ISO8859-1");
         * 					username = URLDecoder.decode(username, "utf-8");
         *
         * 					解决方案三：简写的方式（推荐使用）
         * 					username = new String(username.getBytes("ISO-8859-1"),"utf-8");
         *
         * 				* request获取中文数据乱码（总结：）
         * 					* post提交
         * 						* 设置request缓冲区的编码
         * 							request.setCharacterEncoding("utf-8");
         * 					* get提交
         * 						* String构造方法
         * 							username = new String(username.getBytes("ISO-8859-1"),"utf-8");
         *
         *
         */

        /** 设置request缓冲区的编码（一定要在request.getParameter()之前），否则没用*/
        request.setCharacterEncoding("UTF-8");



        System.out.println("================================================");
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        System.out.println(request.getParameter("sex"));
        System.out.println(request.getParameter("city"));

        String[] loves = request.getParameterValues("love");

        System.out.println(Arrays.toString(loves));

//        for (int i = 0; i < loves.length; i++) {
//            System.out.println(loves[i]);
//        }

        System.out.println("==============================================");

        // 获取Map集合
        Map<String,String[]> map = request.getParameterMap();
        Set<String> keys = map.keySet();

        for (String key : keys) {
            String[]  ss = map.get(key);
            System.out.println(Arrays.toString(ss));
        }

    }


    @Test
    public void run() throws UnsupportedEncodingException {
        String s =  URLEncoder.encode("了李四","ISO-8859-1");

        String y = URLDecoder.decode(s,"UTF-8");

        System.out.println(s);

        System.out.println(y);
    }

}
