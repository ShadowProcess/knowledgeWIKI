package com;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码
 */

@WebServlet(name = "ServletVerificationCode")
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //解决post中文乱码问题
        request.setCharacterEncoding("UTF-8");


        //获取Session中的验证码
        String code1 = (String) request.getSession().getAttribute("code");

        //获取表单的验证码
        String code2 = request.getParameter("code");

        if (code1 == code2) {
            //验证通过
            response.getWriter().write("ok");

        } else {
            //验证不通过

            request.setAttribute("msg","不通过");
            //转发 [不需要写项目名]
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }


    }


}
