package com.john21121.GlobantUniversityTesis.services;


import com.john21121.GlobantUniversityTesis.dto.RecipientDto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RecipientService {

    RecipientDto findById(Long id);

    RecipientDto createNewRecipient(RecipientDto recipientDto);

    List<RecipientDto> getAllMessagesInRecipient() ;

    RecipientDto saveRecipientByDto(Long id,RecipientDto recipientDto);

    void deleteById(Long deletionId);
}
