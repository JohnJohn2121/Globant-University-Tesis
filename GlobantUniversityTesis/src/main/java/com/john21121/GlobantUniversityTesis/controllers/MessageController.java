package com.john21121.GlobantUniversityTesis.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.john21121.GlobantUniversityTesis.dto.MessageDto;
import com.john21121.GlobantUniversityTesis.dto.RecipientDto;
import com.john21121.GlobantUniversityTesis.dto.UserDto;
import com.john21121.GlobantUniversityTesis.services.MessageServiceImpl;
import com.john21121.GlobantUniversityTesis.services.RecipientServiceImpl;
import com.john21121.GlobantUniversityTesis.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/login/{username}/messages")
public class MessageController {


    private final MessageServiceImpl messageService;
    private final RecipientServiceImpl recipientService;
    private final UserService userService;


    public MessageController( MessageServiceImpl messageService,
                             RecipientServiceImpl recipientService,
                             UserService userService){
        this.messageService = messageService;
        this.recipientService = recipientService;
        this.userService = userService;

    }

    @PostMapping("/message/")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipientDto sendMessageToRecipient(@RequestBody RecipientDto recipientDto){
        List<UserDto> userDtoList = new ArrayList<>();
        int i = 0;
        for (UserDto userDto : recipientDto.getUser()){
            userDto = userService.findUserByUsername(recipientDto.getUser().get(i).getUsername());
            userDtoList.add(userDto);
            i++;
        }
          MessageDto messageDto = recipientDto.getMessage();
          messageDto.setUser(userService.findUserByUsername(messageDto.getUser().getUsername()));
          recipientDto.setUser(userDtoList);
          recipientService.createNewRecipient(recipientDto);
    return recipientDto;
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

    @GetMapping()
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<MessageDto> getAllMessages(){
        return  messageService.getAllMessages();
    }

    @GetMapping("/sent")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @JsonIgnoreProperties("user")
    public List<MessageDto> getAllMessagesFromUser(@PathVariable("username")String username){
        List<MessageDto> messageDtos = messageService.getAllMessages();
        List<MessageDto> updatedMessageDto= new ArrayList<>();
        int i = 0;
        for (MessageDto messageDto : messageDtos){
            if (messageDto.getUser().getUsername().equals(username)) {
                updatedMessageDto.add(messageDto);
            }
            i++;
        }
        return  updatedMessageDto;
    }


    @GetMapping("/inbox")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<RecipientDto> getInboxFromUser(){
        return recipientService.getAllMessagesInRecipient();
    }

    @DeleteMapping("/receivedMessages/{recipientid}/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteMessageFromRecipient(@PathVariable("recipientid")Long messageId){
        recipientService.deleteById(messageId);
    }

}
