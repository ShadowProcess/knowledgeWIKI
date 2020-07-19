package com;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统计网站的访问次数
 */

@WebServlet(name = "ServletCount")
public class ServletCount extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          ServletContext context = getServletContext();
          Integer count = (Integer) context.getAttribute("count");


          response.setCharacterEncoding("GBK");
          if (count != null) {
              response.getWriter().write(count.toString());
          } else {
              response.getWriter().write("你的网站还未被访问");
          }
    }
}
