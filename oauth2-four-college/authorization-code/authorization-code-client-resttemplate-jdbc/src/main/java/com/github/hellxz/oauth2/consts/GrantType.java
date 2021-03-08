package com.github.hellxz.oauth2.consts;

public enum GrantType {
    /**
     * 授权码
     */
    AUTHORIZATION_CODE("authorization_code"),
    /**
     * 简化模式
     */
    IMPLICIT("implicit"),
    /**
     * 客户端模式
     */
    CLIENT_CREDENTIALS("client_credentials"),
    /**
     * 用户密码模式
     */
    PASSWORD("password");

    private String code;
    GrantType(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
