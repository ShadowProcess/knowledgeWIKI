package com.github.hellxz.oauth2.feign;

import com.github.hellxz.oauth2.dto.UserDto;
import com.github.hellxz.oauth2.feign.fallback.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-feign",url = "http://localhost:8081", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/user/{username}")
    UserDto getUser(@PathVariable("username") String username);
}
