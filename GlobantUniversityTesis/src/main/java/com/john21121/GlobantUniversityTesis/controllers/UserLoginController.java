package com.john21121.GlobantUniversityTesis.controllers;

import com.john21121.GlobantUniversityTesis.services.UserLoginService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

    private final UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }
}
