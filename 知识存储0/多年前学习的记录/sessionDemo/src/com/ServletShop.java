package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车后台
 */

@WebServlet(name = "ServletShop")
public class ServletShop extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *
         * 1.购物车 Map<String,Integer> 把购物车存储在Session中
         *
         * 2.先获取购物车，判断是否是第一次访问
         *  第一次访问：创建购物车
         *  不是第一次访问：存入购物车
         *
         * 3.继续购物或者结算
         */

        String id = request.getParameter("id");
        String[] names = {"手电筒", "冰箱", "电视"};

        // 把id翻译为名称
        int idx = Integer.parseInt(id);

        // 商品的名称
        String name = names[idx];


        // 从session中获取购物车，先获取session
        HttpSession session = request.getSession();
        // 从session中获取购物车
        Map<String,Integer> cart = (Map<String, Integer>) session.getAttribute("cart");

        if (cart == null) {
            //首次访问
            cart = new HashMap<>();
            cart.put(name,idx);
            session.setAttribute("cart",cart);
        } else {
            //不是第一次访问,判断是否包含该商品
            if (cart.containsKey(name)) {

                //购物车存在该商品
                Integer count = cart.get(name);
                count++; // 数量加一

                //购物车存入商品
                cart.put(name,count);

                //把购物车放进Session
                session.setAttribute("cart",cart);
            } else {
                //购物车,不存在该商品
                cart.put(name,1); //存入商品名,数量1
                //把购物车放进Session
                session.setAttribute("cart",cart);
            }

        }

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<h3><a href='/Session/index.jsp'>继续购物</a>  <a href='/Session/pay.jsp'>去结算</a> </h3>");
        //response.sendRedirect("/Session/index.jsp");
    }
}
