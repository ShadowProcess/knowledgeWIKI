package com.fenye;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FenyeServlet")
public class FenyeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.默认访问第一页
        int pageNumber = 1;

        //2.每页默认条数 人为定义
        int currentPageCount = 5;

        //3.调用Service，完成当前查询操作
        //List customers = service.findByPage(pageNumber,currentPage);
    }
}
