package com.github.hellxz.auth2;

import com.alibaba.fastjson.JSONObject;
import com.github.hellxz.oauth2.RedisAuthorizationServer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisAuthorizationServer.class})
public class RedisTokenSavedServerTest {
    private static final Logger log = LoggerFactory.getLogger(RedisTokenSavedServerTest.class);

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    private MockMvc mockMvc;

    @Before
    public void init(){
        //初始化web上下文，需要包含spring security filter chain
        mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void getAccessTokenTest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("http://localhost:8080/oauth/token")
                .header("Authorization", "Basic "
                        + Base64.getEncoder().encodeToString("client-a:client-a-secret".getBytes(StandardCharsets.UTF_8)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("username", "hellxz")
                .param("password", "test")
                .param("grant_type", "password")
                .param("scope", "all");
        String result = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        if(!StringUtils.isEmpty(result)){
            log.info(result);
        }
    }

    @Test
    public void checkTokenTest() throws Exception{
        MockHttpServletRequestBuilder getAccessTokenRequest = MockMvcRequestBuilders.post("http://localhost:8080/oauth/token")
                .header("Authorization", "Basic "
                        + Base64.getEncoder().encodeToString("client-a:client-a-secret".getBytes(StandardCharsets.UTF_8)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("username", "hellxz")
                .param("password", "test")
                .param("grant_type", "password")
                .param("scope", "all");
        String accessTokenJson = mockMvc.perform(getAccessTokenRequest)
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = JSONObject.parseObject(accessTokenJson);
        MockHttpServletRequestBuilder checkTokenRequest = MockMvcRequestBuilders.post("http://localhost:8080/oauth/check_token")
                .header("Authorization", "Basic "
                        + Base64.getEncoder().encodeToString("client-a:client-a-secret".getBytes(StandardCharsets.UTF_8)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("token", jsonObject.getString("access_token"));
        String responseBody = mockMvc.perform(checkTokenRequest)
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        if(!StringUtils.isEmpty(responseBody)){
            log.info(responseBody);
        }
    }

    @Test
    public void refreshTokenTest() throws Exception {
        MockHttpServletRequestBuilder getAccessTokenRequest = MockMvcRequestBuilders.post("http://localhost:8080/oauth/token")
                .header("Authorization", "Basic "
                        + Base64.getEncoder().encodeToString("client-a:client-a-secret".getBytes(StandardCharsets.UTF_8)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("username", "hellxz")
                .param("password", "test")
                .param("grant_type", "password")
                .param("scope", "all");
        String accessTokenJson = mockMvc.perform(getAccessTokenRequest)
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        JSONObject jsonObject = JSONObject.parseObject(accessTokenJson);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("http://localhost:8080/oauth/token")
                .header("Authorization", "Basic "
                        + Base64.getEncoder().encodeToString("client-a:client-a-secret".getBytes(StandardCharsets.UTF_8)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("refresh_token", jsonObject.getString("refresh_token"))
                .param("grant_type", "refresh_token")
                .param("scope", "all");
        String tokenJson = mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        if(!StringUtils.isEmpty(tokenJson)){
            log.info(tokenJson);
        }
    }

//    @Test
//    public void revokeTokenTest() throws Exception {
//        MockHttpServletRequestBuilder getAccessTokenRequest = MockMvcRequestBuilders.post("http://localhost:8080/oauth/token")
//                .header("Authorization", "Basic "
//                        + Base64.getEncoder().encodeToString("client-a:client-a-secret".getBytes(StandardCharsets.UTF_8)))
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//                .param("username", "hellxz")
//                .param("password", "test")
//                .param("grant_type", "password")
//                .param("scope", "all");
//        String accessTokenJson = mockMvc.perform(getAccessTokenRequest)
//                .andExpect(status().is2xxSuccessful())
//                .andReturn().getResponse().getContentAsString();
//
//        JSONObject jsonObject = JSONObject.parseObject(accessTokenJson);
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("http://localhost:8080/logout")
//                .header("Authorization", "Bearer " + jsonObject.getString("access_token"))
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED);
//        String revokeResult = mockMvc.perform(request)
//                .andExpect(status().is2xxSuccessful())
//                .andReturn().getResponse().getContentAsString();
//        if(!StringUtils.isEmpty(revokeResult)){
//            log.info(revokeResult);
//        }
//    }

}
