package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.dto.RecipientDto;
import com.john21121.GlobantUniversityTesis.exceptions.ResourceNotFoundException;
import com.john21121.GlobantUniversityTesis.mailingsystem.Recipient;
import com.john21121.GlobantUniversityTesis.mappers.RecipientMapper;
import com.john21121.GlobantUniversityTesis.repository.RecipientRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipientServiceImpl implements RecipientService {

    private final RecipientRepository recipientRepository;
    private final RecipientMapper recipientMapper;

    public RecipientServiceImpl(RecipientRepository recipientRepository, RecipientMapper recipientMapper) {
        this.recipientRepository = recipientRepository;
        this.recipientMapper = recipientMapper;
    }

    private RecipientDto saveAndReturn(Recipient recipient){
        Recipient savedRecipient = recipientRepository.save(recipient);
        return recipientMapper.recipientToRecipientDto(savedRecipient);
    }

    @Override
    public RecipientDto findById(Long id) {
        return recipientRepository.findById(id).map(recipientMapper ::recipientToRecipientDto)
                .map(recipientDto -> {recipientDto.setId(id);
                return recipientDto;}).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public RecipientDto createNewRecipient(RecipientDto recipientDto) {
        return saveAndReturn(recipientMapper.recipientDtoToRecipient(recipientDto));
    }

    @Override
    public List<RecipientDto> getAllMessagesInRecipient() {
        return recipientRepository.findAll().stream()
                .map(recipientMapper::recipientToRecipientDto)
                .collect(Collectors.toList());
    }

    @Override
    public RecipientDto saveRecipientByDto(Long id,RecipientDto recipientDto) {
        Recipient recipient = recipientMapper.recipientDtoToRecipient(recipientDto);
        return saveAndReturn(recipient);
    }

    @Override
    public void deleteById(Long deletionId) {
        recipientRepository.deleteById(deletionId);
    }
}
