package com.github.hellxz.oauth2.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserDto {
    private String username;
    private String email;

    public UserDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
