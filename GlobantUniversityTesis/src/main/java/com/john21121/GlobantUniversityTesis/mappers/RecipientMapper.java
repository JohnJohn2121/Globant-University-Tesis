package com.john21121.GlobantUniversityTesis.mappers;

import com.john21121.GlobantUniversityTesis.dto.RecipientDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import com.john21121.GlobantUniversityTesis.mailingsystem.Recipient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipientMapper {


    RecipientMapper INSTANCE = Mappers.getMapper(RecipientMapper.class);

    RecipientDto recipientToRecipientDto(Message message);

    Recipient recipientDtoToRecipient(RecipientDto recipientDto);


}
