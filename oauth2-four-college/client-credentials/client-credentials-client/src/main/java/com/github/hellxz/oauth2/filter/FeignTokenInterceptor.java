package com.github.hellxz.oauth2.filter;

import com.github.hellxz.oauth2.config.UaaProperties;
import com.github.hellxz.oauth2.utils.TokenUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Feign拦截器，为请求头上加上token
 */
@Configuration
public class FeignTokenInterceptor implements RequestInterceptor {

    @Resource
    private UaaProperties uaaProperties;

    @Resource
    private TokenUtils tokenUtils;

    /**
     * 请求头中没有Authorization的，调用资源服务器获取token置于请求头，使请求可被访问
     * 使用客户端模式
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Map<String, Collection<String>> headers = requestTemplate.headers();
        if (CollectionUtils.isEmpty(headers) || !headers.containsKey("Authorization")) {
            String clientId = uaaProperties.getClientId();
            String clientSecret = uaaProperties.getClientSecret();
            String tokenEndpoint = uaaProperties.getTokenEndpoint();
            String bearerToken = tokenUtils.getBearerTokenByClientCredentials(clientId, clientSecret, tokenEndpoint);
            if (StringUtils.isNotBlank(bearerToken)) {
                requestTemplate.header("Authorization", bearerToken);
            }
        }
    }
}
