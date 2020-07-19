package util;

import javax.servlet.http.Cookie;

public class MyCookieUtil {

    /**
     * 通过指定名称查找指定的cookie
     * @return
     */
    public static Cookie getCookieByName(Cookie[] cookies,String name){

        // 如果数组为空
        if (cookies == null) {
            return null;
        } else {
            //循环遍历 目的: 和name进行匹配，如果匹配成功，返回当前cookie
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

}
