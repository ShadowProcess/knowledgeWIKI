package com.github.hellxz.oauth2.config;

import com.github.hellxz.oauth2.dao.ClientUserRepositories;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class ClientAuthenticationManager implements AuthenticationManager {

    @Autowired
    private ClientUserDetailsService clientUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        String password = principal.getPassword();
        if (StringUtils.isAnyBlank(username, password)) {
            throw new RuntimeException("用户名与密码均不能为空");
        }
        UserDetails userDetails = clientUserDetailsService.loadUserByUsername(username);
        if (!StringUtils.equals(password, userDetails.getPassword())) {
            throw new RuntimeException("密码输入不正确！");
        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
    }
}
