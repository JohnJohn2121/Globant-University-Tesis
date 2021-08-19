package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.dto.MessageDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MessageService {

    // Revision is for controller
    Optional<MessageDto> findById(Long id);

    MessageDto createNewMessage(MessageDto messageDto);

    MessageDto saveMessageByDto(Long id,MessageDto messageDto);

    List<MessageDto> getAllMessages() ;

    void deleteById(Long deletionId);
}
