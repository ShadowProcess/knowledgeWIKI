package com.github.hellxz.oauth2.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Objects;

@Component
public class TokenUtils {

    @Resource(name = "tokenTemplate")
    private RestTemplate tokenTemplate;

    public String getBearerTokenByClientCredentials(String clientId, String clientSecret, String tokenEndpoint){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(clientId, clientSecret);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        RequestEntity<MultiValueMap<String, String>> requestEntity
                = new RequestEntity<>(params, httpHeaders, HttpMethod.POST, URI.create(tokenEndpoint));
        ResponseEntity<TokenDTO> exchange = tokenTemplate.exchange(requestEntity, TokenDTO.class);
        if (exchange.getStatusCode().is2xxSuccessful()) {
            return "Bearer " + Objects.requireNonNull(exchange.getBody()).getAccessToken();
        }else{
            return "";
        }
    }

    static class TokenDTO {
        @JsonProperty("access_token")
        private String accessToken;
        @JsonProperty("refresh_token")
        private String refreshToken;
        @JsonProperty("token_type")
        private String tokenType;
        @JsonProperty("expires_in")
        private String expiresIn;
        @JsonProperty("scope")
        private String scope;

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public String getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(String expiresIn) {
            this.expiresIn = expiresIn;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }
    }

    @Bean("tokenTemplate")
    public RestTemplate tokenTemplate(){
        return new RestTemplate();
    }
}
