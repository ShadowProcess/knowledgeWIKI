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
public class ServletCookieDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();

        Cookie cookie = MyCookieUtil.getCookieByName(cookies,"product");

        if (cookie != null) {
            cookie.setMaxAge(0);  //如果之前的Cookie设置过了有效路径，那么这个清除时，也要设置和之前一样的路径，[否则不行]
            response.addCookie(cookie);
        }

        //重定向到页面
        response.sendRedirect("/CookieAndSession/cookieShop.jsp");
    }


    /**
     * 检查指定ID在Cookie中存在
     * @param values
     * @param id
     * @return
     */
    public boolean checkId(String[] values, String id) {
        for (String s : values) {
            if (s.equals(id)) {
                return true;
            }
        }
        return false;
    }
}
