package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.mailingsystem.Message;

import java.util.Set;

public interface MessageService {

    Message findById(Long id);

    Message createNewMessage(Message message);

    Set<Message> getMessages() ;

    void deleteById(Long deletionId);
}
