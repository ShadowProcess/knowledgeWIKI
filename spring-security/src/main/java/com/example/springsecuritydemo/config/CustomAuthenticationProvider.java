package com.example.springsecuritydemo.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户输入认证信息，向后台请求时，这里处对用户进行认证
 */
@Service
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    //通过这个方法我们可以拿到form-data的数据，并且返回一个UserDetails如果登录成功的话，或者返回null如果登录失败。
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("开始执行授权认证");
        val token = (UsernamePasswordAuthenticationToken) authentication;
        val username = token.getName();
        // 从数据库查找该用户
        val userDetails = username != null
                ? userDetailsService.loadUserByUsername(username)
                : null;

        if (userDetails == null) throw new UsernameNotFoundException("用户名无效");
        if (!userDetails.isEnabled()) throw new DisabledException("用户已被禁用");
        if (!userDetails.isAccountNonExpired()) throw new AccountExpiredException("账号已过期");
        if (!userDetails.isAccountNonLocked()) throw new LockedException("账号已被锁定");
        if (!userDetails.isCredentialsNonExpired()) throw new LockedException("凭证已过期");
        // 数据库用户的密码，应为加密后的密码
        val password = userDetails.getPassword();
        // 与authentication里面的credentials相比较
        if (!passwordEncoder.matches(token.getCredentials().toString(), password))
            throw new BadCredentialsException("密码错误");

        // 给与授权
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    //这个方法是告诉spring security，我们这个《认证器》支持哪种验证。
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
