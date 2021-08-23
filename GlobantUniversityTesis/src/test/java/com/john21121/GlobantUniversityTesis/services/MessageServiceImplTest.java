package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.dto.MessageDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import com.john21121.GlobantUniversityTesis.mappers.MessageMapper;
import com.john21121.GlobantUniversityTesis.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class MessageServiceImplTest {

    MessageService messageService;

    @Mock
    MessageRepository messageRepository;

    public static final String SUBJECT = "Invitation to party";
    public static final String BODY = "You are invited to a generic pizza party made just to give " +
            "context to this test";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        messageService = new MessageServiceImpl(messageRepository, MessageMapper.INSTANCE);
    }

    @Test
    void findById() {
        //Given
        Message message = new Message();
        message.setBody(BODY);
        message.setSubject(SUBJECT);
        message.setId(6L);

        //When
        when(messageRepository.findById(anyLong())).thenReturn(Optional.of(message));
        MessageDto messageDto = messageService.findById(6L);

        //Then
        assertEquals(BODY,messageDto.getBody());
        assertEquals(SUBJECT,messageDto.getSubject());

    }

    @Test
    void createNewMessage() {
        //Given
        MessageDto messageDto = new MessageDto();
        messageDto.setBody(BODY);
        messageDto.setSubject(SUBJECT);
        messageDto.setId(1L);

        Message message= new Message();
        message.setBody(messageDto.getBody());
        message.setSubject(messageDto.getSubject());

        //When
        when(messageRepository.save(any(Message.class))).thenReturn(message);
        MessageDto savedMessage = messageService.createNewMessage(messageDto);
        //Then
        assertEquals(BODY,messageDto.getBody());
        assertEquals(SUBJECT,messageDto.getSubject());

    }

    @Test
    void getAllMessages() {
        //Given
        List<Message> messages = Arrays.asList(new Message(),new Message());
        //When
        when(messageRepository.findAll()).thenReturn(messages);
        List<MessageDto> messageDtos = messageService.getAllMessages();
        //Then
        assertEquals(2,messageDtos.size());
    }

    @Test
    void deleteById() {
        //Given
        Long id = 1L;
        //When
        messageRepository.deleteById(id);
        //Then
        verify(messageRepository,times(1)).deleteById(anyLong());
    }
}