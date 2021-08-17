package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import com.john21121.GlobantUniversityTesis.repository.MessageRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message findById(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        if (!message.isPresent()){
            throw new NotFoundException("This Message Does not exist");
        }

        return message.get();
    }

    @Override
    public Message createNewMessage(Message message) {
        messageRepository.save(message);
        return message;
    }

    @Override
    public Set<Message> getMessages() {
        Set<Message> messages= new HashSet<>();
        messageRepository.findAll().iterator().forEachRemaining(messages::add);
        return messages;
    }

    @Override
    public void deleteById(Long deletionId) {
        messageRepository.deleteById(deletionId);
    }
}
