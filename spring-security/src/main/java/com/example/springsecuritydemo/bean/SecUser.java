package com.example.springsecuritydemo.bean;

import lombok.Data;

import java.util.Set;

/**
 * 用户信息表
 */
@Data
public class SecUser {

    private String username; //用户名
    private String password; //用户密码
    private Boolean enabled; //账号是否禁用

    //该用户具有的所有权限
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
//    @JoinTable(name = "t_flow_sec_user_authority", // @JoinTable 注解中的配置是站在中间表的角度,该中间表并不存在于数据库中,相当于一张临时表,对被关联的两个表的描述
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<SecAuthority> authorities;

}
