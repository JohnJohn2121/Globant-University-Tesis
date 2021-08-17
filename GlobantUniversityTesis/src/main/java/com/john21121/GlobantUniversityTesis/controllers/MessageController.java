package com.john21121.GlobantUniversityTesis.controllers;

import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import com.john21121.GlobantUniversityTesis.mailingsystem.Recipient;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import com.john21121.GlobantUniversityTesis.repository.MessageRepository;
import com.john21121.GlobantUniversityTesis.repository.RecipientRepository;
import com.john21121.GlobantUniversityTesis.services.MessageService;
import com.john21121.GlobantUniversityTesis.services.RecipientService;
import com.john21121.GlobantUniversityTesis.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final MessageService messageService;
    private final User user;
    private final UserService userService;
    private final RecipientRepository recipientRepository;
    private final RecipientService recipientService;

    public MessageController(MessageRepository messageRepository, MessageService messageService,
                             User user, UserService userService, RecipientService recipientService,
                             RecipientRepository recipientRepository) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.user = user;
        this.userService = userService;
        this.recipientRepository = recipientRepository;
        this.recipientService = recipientService;
    }

    @PostMapping("/sendmessage/")
    @ResponseStatus(HttpStatus.CREATED)
    public Message sendMessage(@RequestBody Message message){
        //User user1 = (User) userService.findById(); ask Pablo or Natalia if this is proper
        message.setUser(user);
        messageRepository.save(message);
        messageService.createNewMessage(message);
        return message;
    }

    @GetMapping("/sentMessages/{messageid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Message getMessageSentById(@PathVariable("messageid")Long messageId){
        return messageService.findById(messageId);
    }

    @GetMapping("/receivedMessages/{recipientid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Recipient getRecipientMessageById(@PathVariable("recipientid")Long messageId){
        return recipientService.findById(messageId);
    }

    @GetMapping("/sentMessages")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getAllMessages(){
        return (List<Message>) messageRepository.findAll();
    }



}
