package com.john21121.GlobantUniversityTesis.controllers;

import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import com.john21121.GlobantUniversityTesis.repository.MessageRepository;
import com.john21121.GlobantUniversityTesis.services.MessageService;
import com.john21121.GlobantUniversityTesis.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class MessageController {

    private final MessageRepository messageRepository;
    private final MessageService messageService;
    private final User user;
    private final UserService userService;

    public MessageController(MessageRepository messageRepository, MessageService messageService,
                             User user, UserService userService) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.user = user;
        this.userService = userService;
    }



}
