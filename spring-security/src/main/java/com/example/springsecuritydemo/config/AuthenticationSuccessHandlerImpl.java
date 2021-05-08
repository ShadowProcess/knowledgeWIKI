package com.example.springsecuritydemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


/**
 * 自定义授权成功之后，处理器
 * <p>
 * req：相当与HttpServletRequest
 * res：相当与HttpServletResponse
 * authentication：这里保存了我们登录后的用户信息
 */
@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info(String.format("IP %s，用户 %s， 于 %s 成功登录系统。", request.getRemoteHost(), authentication.getName(), LocalDateTime.now()));
        try {
            // 发邮件
            log.info("发邮件");
            // 发短信
            log.info("发短信");
            // 发微信
            log.info("发微信");

            response.sendRedirect("/hello"); //重定向到指定页面
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
