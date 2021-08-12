package com.john21121.GlobantUniversityTesis.controllers;

import com.john21121.GlobantUniversityTesis.services.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


}
