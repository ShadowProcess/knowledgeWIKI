package com.servlet;

import com.domain.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.得到请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //2.
        UserService service = new UserService();
        User user = new User(1,username,password);
        try {
            //user = service.login(username,password);

            if ( user != null) {
                req.getSession().setAttribute("user",user);
                resp.sendRedirect(req.getContextPath()+"/book.jsp");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
