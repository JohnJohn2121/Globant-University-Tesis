package com.john21121.GlobantUniversityTesis.mappers;

import com.john21121.GlobantUniversityTesis.dto.MessageDto;
import com.john21121.GlobantUniversityTesis.dto.UserDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageMapperTest {

    MessageMapper messageMapper = MessageMapper.INSTANCE;

    public static final String SUBJECT = "Testing subject";
    public static final String BODY = "I guess this should be a longer message, but this is it";

    @BeforeEach
    void setUp() {
    }

    @Test
    void messageToMessageDto() {
        //Given
        Message message = new Message();

        message.setSubject(SUBJECT);
        message.setBody(BODY);

        //When
        MessageDto messageDto = messageMapper.messageToMessageDto(message);

        //Then
        assertEquals(SUBJECT, messageDto.getSubject());
        assertEquals(BODY, messageDto.getBody());
        
    }

}