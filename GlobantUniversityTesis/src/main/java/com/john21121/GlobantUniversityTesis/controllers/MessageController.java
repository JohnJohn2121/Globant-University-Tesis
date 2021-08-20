package com.john21121.GlobantUniversityTesis.controllers;

import com.john21121.GlobantUniversityTesis.dto.MessageDto;
import com.john21121.GlobantUniversityTesis.dto.RecipientDto;
import com.john21121.GlobantUniversityTesis.dto.UserDto;
import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.mailingsystem.RecipientType;
import com.john21121.GlobantUniversityTesis.repository.MessageRepository;
import com.john21121.GlobantUniversityTesis.repository.RecipientRepository;
import com.john21121.GlobantUniversityTesis.services.MessageService;
import com.john21121.GlobantUniversityTesis.services.MessageServiceImpl;
import com.john21121.GlobantUniversityTesis.services.RecipientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/login/{userId}/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final MessageServiceImpl messageService;
    private final RecipientRepository recipientRepository;
    private final RecipientService recipientService;

    public MessageController(MessageRepository messageRepository, MessageServiceImpl messageService,
                             RecipientService recipientService, RecipientRepository recipientRepository) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.recipientRepository = recipientRepository;
        this.recipientService = recipientService;
    }

    //TODO fix recipient name, validate userID here
    @PostMapping("/messages/{recipientid}")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessageToRecipient(@RequestBody MessageDto message, @PathVariable("recipientId") Long recipientId){
        message = messageService.findById(message.getId());
        UserDto user = message.getUser();
        RecipientDto recipient = recipientService.findById(recipientId);
        if (recipient != null) {
            recipient.setRecipientType(RecipientType.TO);
            recipient.setMessage(message);
            recipient.setUser(user);
        }
        throw new NotFoundException("The user or Recipient, does not exist");
    }

    @GetMapping("/sentMessages/{messageid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public MessageDto getMessageSentById(@PathVariable("messageid")Long messageId){
        return messageService.findById(messageId);
    }

    @GetMapping("/receivedMessages/{recipientid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RecipientDto getRecipientMessageById(@PathVariable("recipientid")Long messageId){
        return recipientService.findById(messageId);
    }

    @GetMapping("/inbox")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> getAllMessages(){
        return  messageService.getAllMessages();
    }

    @DeleteMapping("/receivedMessages/{recipientid}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteMessageFromRecipient(@PathVariable("recipientid")Long messageId){
        recipientService.deleteById(messageId);
        recipientRepository.deleteById(messageId);
    }

}
