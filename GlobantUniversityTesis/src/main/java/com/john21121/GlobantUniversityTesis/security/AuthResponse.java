package com.john21121.GlobantUniversityTesis.security;

import lombok.Getter;

@Getter
public class AuthResponse {

    private final String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

}
