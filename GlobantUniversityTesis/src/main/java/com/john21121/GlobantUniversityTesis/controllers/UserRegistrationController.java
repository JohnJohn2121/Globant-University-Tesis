package com.john21121.GlobantUniversityTesis.controllers;

import com.john21121.GlobantUniversityTesis.services.UserRegistrationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

}
