package com.john21121.GlobantUniversityTesis.controllers;

import com.john21121.GlobantUniversityTesis.dto.MessageDto;
import com.john21121.GlobantUniversityTesis.dto.RecipientDto;
import com.john21121.GlobantUniversityTesis.dto.UserDto;
import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.mailingsystem.RecipientType;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import com.john21121.GlobantUniversityTesis.mappers.UserMapper;
import com.john21121.GlobantUniversityTesis.repository.MessageRepository;
import com.john21121.GlobantUniversityTesis.repository.RecipientRepository;
import com.john21121.GlobantUniversityTesis.repository.UserRepository;
import com.john21121.GlobantUniversityTesis.services.MessageServiceImpl;
import com.john21121.GlobantUniversityTesis.services.RecipientService;
import com.john21121.GlobantUniversityTesis.services.UserService;
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
    private final UserService userService;


    public MessageController(MessageRepository messageRepository, MessageServiceImpl messageService,
                             RecipientService recipientService, RecipientRepository recipientRepository,
                             UserService userService ){
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.recipientRepository = recipientRepository;
        this.recipientService = recipientService;
        this.userService = userService;

    }
    private UserDto findUserById( Long userId){
        return userService.findById(userId);
    }


    //TODO fix recipient name, validate userID here
    @PostMapping("/message/")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendMessageToRecipient(@RequestBody MessageDto message,@PathVariable("{userId}")Long userId ){
//        if (userRepository.findByUsername(message.getUser().getUsername()) == null){
//            throw new NotFoundException("This user does not exist");
//        }else

        UserDto userDto = findUserById(userId);
        message.setUser(userDto);
        messageService.createNewMessage(message);
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
