package com.github.hellxz.oauth2.web.controller;

import com.github.hellxz.oauth2.web.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);

    @GetMapping("/user/{username}")
    public UserVO user(@PathVariable String username){
        log.info("{}", SecurityContextHolder.getContext().getAuthentication());
        return new UserVO(username, username + "@foxmail.com");
    }
}
