package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletCache")
public class ServletCache extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Cache-Control : no-cache
        //Expires : -1               //Thu, 01 Dec 1994 16:00:00 GMT (非常特殊，转换特定日期格式才可以)
        //Pragma : no-cache

        /**禁用浏览器缓存*/
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",-1);
        response.setHeader("Pragma","no-cache");
    }
}
