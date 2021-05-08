package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.bean.SecUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


/**
 * 自定义用户信息
 * SecUser是业务上的用户信息表
 */
public class CustomUser extends User {

    private static final long serialVersionUID = 3427061675128811549L;
    public static final int LOGIN_TYPE_PSSWD = 1; //登录类型是密码
    public static final int LOGIN_TYPE_TOKEN = 2; //登录类型是TOKEN

    private int loginType;
    private SecUser secUser;

    public CustomUser(SecUser secUser, Collection<? extends GrantedAuthority> authorities) {
        super(
                secUser.getUsername(),
                secUser.getPassword(),
                secUser.getEnabled(),
                true,
                true,
                true,
                authorities
        );
        this.secUser = secUser;
    }


    public SecUser getSecUser() {
        return secUser;
    }

    public void setSecUser(SecUser secUser) {
        this.secUser = secUser;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }
}
