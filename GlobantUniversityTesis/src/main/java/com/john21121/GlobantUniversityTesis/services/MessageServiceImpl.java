package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.dto.MessageDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import com.john21121.GlobantUniversityTesis.mappers.MessageMapper;
import com.john21121.GlobantUniversityTesis.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }


    private MessageDto saveAndReturn(Message message){
        Message savedMessage = messageRepository.save(message);
        return messageMapper.messageToMessageDto(savedMessage);
    }


    @Override
    public Optional<MessageDto> findById(Long id) {
        return messageRepository.findById(id).map(messageMapper::messageToMessageDto);
    }

    @Override
    public MessageDto createNewMessage(MessageDto messageDto) {


        return saveAndReturn(messageMapper.messageDtoToMessage(messageDto));
    }

    @Override
    public MessageDto saveMessageByDto(Long id, MessageDto messageDto) {
        Message message = messageMapper.messageDtoToMessage(messageDto);
        message.setId(id);

        return saveAndReturn(message);
    }

    @Override
    public List<MessageDto> getAllMessages() {
        return messageRepository.findAll().stream()
                .map(message -> {MessageDto messageDto= messageMapper.messageToMessageDto(message);
                return messageDto;})
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long deletionId) {
        messageRepository.deleteById(deletionId);
    }
}
