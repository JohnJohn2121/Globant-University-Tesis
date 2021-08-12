package com.john21121.GlobantUniversityTesis.controllers;


import com.john21121.GlobantUniversityTesis.services.UserService;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/{id}")
public class UserController {

    @Autowired
    User user;

    private final UserService userService;

    public UserController(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }



}
