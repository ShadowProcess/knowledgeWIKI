package com.example.springsecuritydemo.bean;

import lombok.Data;


/**
 * 用户权限表（业务表）
 */
@Data
public class SecAuthority  {

    private Long id;
    private String authority; //角色
    private String urlPattern;//角色可访问的URL
}
