package com;

import javax.servlet.http.Cookie;

public class Utils {


    /**
     * 获取指定名字的cookie
     *
     * @param cs
     * @param name
     * @return
     */
    public static Cookie findCookieByName(Cookie[] cs,String name) {
        if (cs == null || cs.length == 0) {
            return null;
        }

        for (Cookie c : cs) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }
}
