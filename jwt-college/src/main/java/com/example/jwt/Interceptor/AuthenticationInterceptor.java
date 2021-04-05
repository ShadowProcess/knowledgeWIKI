package com.example.jwt.Interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwt.util.JWTUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从http请求头中取出token
        String token = httpServletRequest.getHeader("token");
        System.out.println("拦截器获取Token:" + token);

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查方法名是否是“login”如果是则跳过，也可以加注解，用注解过滤不需要权限的方法
        if ("login".equals(method.getName())) {
            return true;
        }
        // 执行认证
        if (token == null) {
            throw new RuntimeException("无token，请重新登录");
        }

        DecodedJWT decodedJWT = JWTUtil.verifyToken(token);
        if (decodedJWT == null) {
            throw new RuntimeException("token无法解析，或者已过期");
        } else {
            //TODO 获取用户id，判断用户在数据库是否存在，如果存在，则放行，不存在则拦截跳转登录
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
