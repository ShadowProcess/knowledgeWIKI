package com.github.hellxz.oauth2.service;

import com.github.hellxz.oauth2.config.OAuth2ClientProperties;
import com.github.hellxz.oauth2.config.OAuth2ServerProperties;
import com.github.hellxz.oauth2.consts.GrantType;
import com.github.hellxz.oauth2.domain.ClientUser;
import com.github.hellxz.oauth2.dto.TokenDTO;
import com.github.hellxz.oauth2.web.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.*;

@Component
public class TokenService {

    @Autowired
    private OAuth2ServerProperties auth2ServerProperties;

    @Autowired
    private OAuth2ClientProperties auth2ClientProperties;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 请求授权码url
     */
    public String getAuthorizeUrl() {
        HashMap<String, String> params = new HashMap<>();
        params.put("client_id", auth2ClientProperties.getClientId());
        params.put("redirect_uri", URLEncoder.encode(auth2ClientProperties.getRedirectUri()));
        params.put("response_type", auth2ClientProperties.getResponseType());
        params.put("scope", URLEncoder.encode(auth2ClientProperties.getScope()));
        return buildUrlStr(auth2ServerProperties.getAuthorizeUrl(), params);
    }

    /**
     * 请求授权token的url
     */
    public String getToken(String code) throws UnsupportedEncodingException {
        RequestEntity httpEntity = new RequestEntity<>(getHttpBody(code), getHttpHeaders(), HttpMethod.POST, URI.create(auth2ServerProperties.getTokenUrl()));
        ResponseEntity<TokenDTO> exchange = restTemplate.exchange(httpEntity, TokenDTO.class);
        if (exchange.getStatusCode().is2xxSuccessful()) {
            System.err.println(exchange.getBody());
            return Objects.requireNonNull(exchange.getBody()).getAccessToken();
        }
        throw new RuntimeException("请求令牌失败！");
    }

    public void tryGetUserInfo(ModelAndView mv, ClientUser currentUser) {
        //正常请求资源服务器，获取用户信息
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getAccessToken());
        RequestEntity<MultiValueMap<String, String>> requestEntity
                = new RequestEntity<>(headers, HttpMethod.GET, URI.create("http://localhost:8081/user/jack"));
        ResponseEntity<UserInfo> exchange = null;
        try{
            //尝试访问资源
            exchange = restTemplate.exchange(requestEntity, UserInfo.class);
        }catch (HttpClientErrorException exception){
            //未认证会报错，重定向到授权页面，获取新token
            mv.setViewName("redirect:" + getAuthorizeUrl());
            return;
        }
        assert exchange != null;
        if (exchange.getStatusCode().is2xxSuccessful()) {
            UserInfo body = exchange.getBody();
            mv.addObject("currentLoginUsername", currentUser.getUsername());
            mv.addObject("user", body);
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(auth2ClientProperties.getClientId(), auth2ClientProperties.getClientSecret());
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }

    private MultiValueMap<String, String> getHttpBody(String code) throws UnsupportedEncodingException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", GrantType.AUTHORIZATION_CODE.getCode());
        params.add("redirect_uri", auth2ClientProperties.getRedirectUri());
        params.add("scope", auth2ClientProperties.getScope());
        return params;
    }

    /**
     * 构建url
     *
     * @param endpoint 服务器端点url
     * @param params   参数列表
     * @return 带参数的url
     */
    private String buildUrlStr(String endpoint, Map<String, String> params) {
        List<String> keyValueParam = new ArrayList<>(params.size());
        params.forEach((key, value) -> keyValueParam.add(key + "=" + value));
        return endpoint + "?" + keyValueParam.stream().reduce((a, b) -> a + "&" + b).get();
    }
}
