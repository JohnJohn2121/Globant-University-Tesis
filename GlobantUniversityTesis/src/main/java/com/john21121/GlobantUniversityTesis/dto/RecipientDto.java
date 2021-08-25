package com.john21121.GlobantUniversityTesis.dto;

import com.john21121.GlobantUniversityTesis.mailingsystem.RecipientType;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class RecipientDto {

    private Long id;
    private List<UserDto> user;
    private MessageDto message;
    private RecipientType recipientType;
    private List<LabelDto> labelDtoSet;


}
