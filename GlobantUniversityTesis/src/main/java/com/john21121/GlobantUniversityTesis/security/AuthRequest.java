package com.john21121.GlobantUniversityTesis.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequest {

    private String username;
    private String password;

}
