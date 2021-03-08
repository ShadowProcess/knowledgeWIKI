package com.github.hellxz.oauth2.web.controller;

import com.github.hellxz.oauth2.web.vo.UserVO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/user/{username}")
    public UserVO user(@PathVariable String username){
        System.err.println(SecurityContextHolder.getContext().getAuthentication());
        return new UserVO(username, username + "@foxmail.com");
    }
}
