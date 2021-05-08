package com.example.springsecuritydemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 自定义授权失败处理器
 * req：相当与HttpServletRequest
 * res：相当与HttpServletResponse
 * e：这里保存了我们登录失败的原因
 */

@Slf4j
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info("自定义授权失败处理器.,登录失败");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value()); //500 服务器异常
        response.setContentType("application/json;charset=UTF-8");
        String s = objectMapper.writeValueAsString(e.getMessage());
        response.getWriter().write(s);
    }
}
