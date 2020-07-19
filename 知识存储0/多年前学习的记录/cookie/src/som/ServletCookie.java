package som;

import util.MyCookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ServletCookie")
public class ServletCookie extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.获取浏览器的所有cookie，判断是否是第一次访问
         * 2.如果是第一次访问
         *      输出欢迎，记录当前的时间，回写到浏览器
         * 3.如果不是第一次访问
         *      获取时间，输出到浏览器，记录当前的时间，回写到浏览器
         *
         * 记录当前的时间，回写到浏览器
         *
         */
        response.setContentType("text/html;charset=UTF-8");


        //获取所有cookie
        Cookie[] cookies = request.getCookies();
        //通过指定名称来查找cookie  Cookie c = new Cookie("last","当前时间");
        Cookie cookie = MyCookieUtil.getCookieByName(cookies,"last");

        if (cookie == null) {
            //第一次访问
            response.getWriter().write("<h1>欢迎访问</h1>");
        } else {
            //不是第一次访问
            String value = cookie.getValue();
            response.getWriter().write("<h1>您又来了,"+value+"</h1>");
        }

        //记录当前时间
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        String s = sd.format(date);

        //回写到浏览器
        Cookie c = new Cookie("last",s);

        //设置cookie的有效时间
        //cookie.setMaxAge(60*60*24); //秒
            //结束cookie setMaxAge(0)  cookie就失效了


        //设置有效路径
        //c.setPath("/");
            //默认的路径()
            // web.xml配置  /last            默认路径  /day11
            // web.xml配置  /servlet/last    默认路径  /day11/servlet

        //设置有效域名
        //cookie.setDomain("localhost");


//        当在本地测试时，浏览器的域名为localhost，在代码中cookie.setPath("/");cookie.setDomain("localhost")，这时候在火狐浏览器中能够添加cookie，而在chrome和IE下添加失败，后来在hosts文件中添加了域名
//
//        127.0.0.1     my.test.com
//
//        这个问题就解决了，应该是IE和chrome对域名为localhost的cookie不接受而火狐的可以，但这只是我的猜测，所以在设置cookie的时候尽量不要用localhost。

        //回写  //2、把Cookie值发送到浏览器中（通过响应头发送：set-cookie）
        response.addCookie(c);
    }


    /**
     *
     * 今天在练习 cookie时意外的报了这个错。
     *
     * java.lang.IllegalArgumentException: An invalid character [32] was present in the Cookie value
     * 1
     * 这句话的意思是
     * 一个不识别的字符[32]出现在了cookie当中
     * 由于tomcat的版本比较高，所以在addCookie时是不能使用空格的 而在ASCII码中32对应的就是空格。只要把后台代码中的空格删掉就可以了。
     * 我的代码：是在cookie中添加了时间，而时间的格式化中我是用了format。上代码：
     *
     *         Date date = new Date();
     *         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
     *         String currentTime = format.format(date);
     *         Cookie cookie = new Cookie("accessTime",currentTime);
     *         cookie.setMaxAge(60*60*10);
     *         response.addCookie(cookie);
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 这里的天和小时之间用的是空格分隔，所以会报错。
     *
     * 只要将这个空格修改掉就可以了。比如改成 -
     * 问题解决
     * ---------------------
     * 版权声明：本文为CSDN博主「caoPengFlying」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/caopengflying/article/details/78965733
     *
     *
     *
     */

}
