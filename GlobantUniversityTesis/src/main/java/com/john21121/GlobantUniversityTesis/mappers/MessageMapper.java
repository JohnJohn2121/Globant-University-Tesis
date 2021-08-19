package com.john21121.GlobantUniversityTesis.mappers;

import com.john21121.GlobantUniversityTesis.dto.MessageDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDto messageToMessageDto(Message message);

    Message messageDtoToMessage(MessageDto messageDto);

}
