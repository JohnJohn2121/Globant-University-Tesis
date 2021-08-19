package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Component
public interface MessageService {

    Message findById(Long id);

    Message createNewMessage(Message message);

    Set<Message> getMessages() ;

    void deleteById(Long deletionId);
}
