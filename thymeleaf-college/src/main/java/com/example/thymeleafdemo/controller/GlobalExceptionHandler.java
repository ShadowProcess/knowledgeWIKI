package com.example.thymeleafdemo.controller;

import com.example.thymeleafdemo.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 这是一种异常处理器，但是没有自适应效果
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //浏览器和客户端都返回的是json
/*    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object> handleException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.not exist");
        map.put("message",e.getMessage());
        return map;
    }*/

    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        //传入我们自己的错误状态码 4xx 5xx
        /**
         * Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
         */
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE,400);
        map.put("code", "user.not exist");
        map.put("message", e.getMessage());
        request.setAttribute("ext",map);
        //转发到/err进行自适应处理
        return "forward:/error";
    }
}
