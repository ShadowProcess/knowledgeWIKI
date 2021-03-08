package com.github.hellxz.oauth2.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.hellxz.oauth2.demo.utils.Response;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

/**
 * 授权Api适配controller
 */
@RestController
@SuppressWarnings("deprecation")
public class UaaAdapterController {
    private static final Logger log = LoggerFactory.getLogger(UaaAdapterController.class);

    @Value("${custom.uaa-uri}")
    String uaaUri;

    @GetMapping("/api/user/login")
    public Response userLogin(@RequestHeader("Authorization") String authorization,
                              @RequestParam String username, @RequestParam String password) {
        ResourceOwnerPasswordAccessTokenProvider provider = new ResourceOwnerPasswordAccessTokenProvider();
        ResourceOwnerPasswordResourceDetails passwordResourceDetails =
                obtainPasswordResourceDetails(authorization, username, password);
        OAuth2AccessToken oAuth2AccessToken = null;
        try {
            oAuth2AccessToken = provider.obtainAccessToken(passwordResourceDetails,
                    new DefaultAccessTokenRequest());
        } catch (OAuth2AccessDeniedException accessDeniedException) {
            log.error("获取访问token失败", accessDeniedException);
            return new Response<String>().err("获取访问token失败", accessDeniedException.getStackTrace().toString());
        }
        return new Response<OAuth2AccessToken>().ok(oAuth2AccessToken);
    }

    @GetMapping("/api/client/login")
    public Response clientLogin(@RequestHeader("Authorization") String authorization){
        ClientCredentialsAccessTokenProvider clientCredentialsAccessTokenProvider = new ClientCredentialsAccessTokenProvider();
        ClientCredentialsResourceDetails clientCredentialsResourceDetails = obtainClientCredentialResourceDetails(authorization);

        OAuth2AccessToken oAuth2AccessToken = null;
        try{
            oAuth2AccessToken = clientCredentialsAccessTokenProvider.obtainAccessToken(
                    clientCredentialsResourceDetails, new DefaultAccessTokenRequest());
        }catch(OAuth2AccessDeniedException accessDeniedException){
            log.error("获取访问token失败", accessDeniedException);
            return new Response<String>().err("获取访问token失败", accessDeniedException.getStackTrace().toString());
        }
        return new Response<OAuth2AccessToken>().ok(oAuth2AccessToken);
    }

    private ResourceOwnerPasswordResourceDetails obtainPasswordResourceDetails(
            String authorization, String username, String password) {
        ResourceOwnerPasswordResourceDetails passwordResourceDetails = new ResourceOwnerPasswordResourceDetails();
        passwordResourceDetails.setUsername(username);
        passwordResourceDetails.setPassword(password);
        JSONObject clientInfo = getClientInfoFromAuthorization(authorization);
        passwordResourceDetails.setClientId(clientInfo.getString("clientId"));
        passwordResourceDetails.setClientSecret(clientInfo.getString("clientSecret"));
        String[] scopes = {"users"};
        passwordResourceDetails.setScope(Arrays.asList(scopes));
        passwordResourceDetails.setAccessTokenUri(uaaUri);
        return passwordResourceDetails;
    }

    private ClientCredentialsResourceDetails obtainClientCredentialResourceDetails(String authorization){
        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        JSONObject clientInfo = getClientInfoFromAuthorization(authorization);
        clientCredentialsResourceDetails.setClientId(clientInfo.getString("clientId"));
        clientCredentialsResourceDetails.setClientSecret(clientInfo.getString("clientSecret"));
        clientCredentialsResourceDetails.setScope(Collections.singletonList("service"));
        clientCredentialsResourceDetails.setAccessTokenUri(uaaUri);
        return clientCredentialsResourceDetails;
    }

    private JSONObject getClientInfoFromAuthorization(String authorization) {
        if(!authorization.toLowerCase().startsWith("basic")){
            throw new RuntimeException("解析请求头失败");
        }
        String basic = authorization.replace("Basic", "").trim();
        byte[] decode = Base64.decodeBase64(basic.getBytes(StandardCharsets.UTF_8));
        String contractStr = new String(decode);
        if (!contractStr.contains(":")){
            throw new RuntimeException("解析请求头失败");
        }
        String[] clientInfoArray = contractStr.split(":", 2);
        JSONObject json = new JSONObject();
        json.put("clientId", clientInfoArray[0]);
        json.put("clientSecret", clientInfoArray[1]);
        return json;
    }
}
