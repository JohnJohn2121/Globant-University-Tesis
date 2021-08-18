package com.john21121.GlobantUniversityTesis.controllers;

import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import com.john21121.GlobantUniversityTesis.mailingsystem.Recipient;
import com.john21121.GlobantUniversityTesis.mailingsystem.RecipientType;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import com.john21121.GlobantUniversityTesis.repository.MessageRepository;
import com.john21121.GlobantUniversityTesis.repository.RecipientRepository;
import com.john21121.GlobantUniversityTesis.services.MessageService;
import com.john21121.GlobantUniversityTesis.services.RecipientService;
import com.john21121.GlobantUniversityTesis.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/{userId}/messages")
public class MessageController {

    private final MessageRepository messageRepository;
    private final MessageService messageService;

    private final RecipientRepository recipientRepository;
    private final RecipientService recipientService;

    public MessageController(MessageRepository messageRepository, MessageService messageService,
                             RecipientService recipientService, RecipientRepository recipientRepository) {
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.recipientRepository = recipientRepository;
        this.recipientService = recipientService;
    }

    @PostMapping("/sendmessage/{recipientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessageToRecipient(@RequestBody Message message,@PathVariable("recipientId") Long recipientId){
        message = messageService.findById(message.getId());
        User user = message.getUser();
        Optional<Recipient> recipient = recipientRepository.findById(recipientId);
        if (recipient.isPresent()) {
            recipient.get().setRecipientType(RecipientType.TO);
            recipient.get().setMessage(message);
            recipient.get().setUser(user);
        }
        throw new NotFoundException("The user or Recipient, does not exist");
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

    @DeleteMapping("/receivedMessages/{recipientid}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteMessageFromRecipient(@PathVariable("recipientid")Long messageId){
        recipientService.deleteById(messageId);
        recipientRepository.deleteById(messageId);
    }

}
