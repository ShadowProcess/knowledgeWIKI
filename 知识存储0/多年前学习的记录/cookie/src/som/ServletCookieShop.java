package som;

import util.MyCookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletCookieShop")
public class ServletCookieShop extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * 1.获取请求参数
         * 2.获取cookie数组，通过指定的名称查找cookie
         * 3.如果cookie为空，第一次访问
         *              --- 如果是第一次访问，创建cookie，回写到浏览器
         *
         * 4.重定向到商品页面
         */

        String productId = request.getParameter("id");

        Cookie[] cookies = request.getCookies();
        Cookie cookie = MyCookieUtil.getCookieByName(cookies, "product");

        if (cookie == null) {
            // 创建cookie
            Cookie cookie1 = new Cookie("product", productId);

            //设置有效时间 7天
            cookie1.setMaxAge(60*60*24*7);

            // 回写
            response.addCookie(cookie1);
        } else {

            //获取cookie的value
            String value = cookie.getValue();
            String[] values = value.split("-");

            if (!checkId(values, productId)) {
                //不包含
                cookie.setValue(value + "-" + productId);

                //设置有效时间 7天
                cookie.setMaxAge(60*60*24*7);

                //回写
                response.addCookie(cookie);
            }
        }

        //重定向到页面
        response.sendRedirect("/CookieAndSession/cookieShop.jsp");
    }


    public boolean checkId(String[] values, String id) {
        for (String s : values) {
            if (s.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
