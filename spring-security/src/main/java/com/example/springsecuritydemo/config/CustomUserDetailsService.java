package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.bean.SecAuthority;
import com.example.springsecuritydemo.bean.SecUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * 该类直接继承UserDetailsService，从数据库加载用户信息与用户权限信息。
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("验证用户输入的用户名和密码");

        //1.从数据库查出用户
        SecUser secUser = new SecUser();
        secUser.setUsername("ls");
        secUser.setPassword("$2a$10$BcbKLygM1o4DLVguKKqIWeg9KwYfkMPIMuU7YDFUKLABufDrqaZpa");
        secUser.setEnabled(Boolean.TRUE);

        //2.从数据库获取，该用户关联的权限信息
        Set<GrantedAuthority> authorities = getAuthorities(secUser);

        //3.构建UserDetails《用上面获取的用户和用户的权限信息构建》
        CustomUser customUser = new CustomUser(secUser,authorities);
        customUser.setLoginType(CustomUser.LOGIN_TYPE_PSSWD);

        return customUser;
    }


    //获取用户所有权限
    private Set<GrantedAuthority> getAuthorities(SecUser secUser){
        Set<GrantedAuthority> authorities = new HashSet<>();
        Set<SecAuthority> userAuthorities = secUser.getAuthorities();

        //mock data《创造虚假的用户权限信息数据，真实情况需要从数据库查》
        userAuthorities = new HashSet<>();
        SecAuthority sa = new SecAuthority();
        sa.setAuthority("admin");
        userAuthorities.add(sa);
        sa.setAuthority("r1");
        userAuthorities.add(sa);
        //mock data end

        log.info("UserAuthorities=" + userAuthorities);
        for (SecAuthority secAuthority : userAuthorities) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + secAuthority.getAuthority());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}
