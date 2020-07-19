package com.github.hellxz.oauth2.feign.fallback;

import com.github.hellxz.oauth2.dto.UserDto;
import com.github.hellxz.oauth2.feign.UserFeignClient;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public UserDto getUser(String username) {
        System.err.println("调用接口失败");
        return null;
    }
}
