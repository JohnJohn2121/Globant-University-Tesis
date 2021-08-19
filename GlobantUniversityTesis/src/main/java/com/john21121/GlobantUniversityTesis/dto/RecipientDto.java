package com.john21121.GlobantUniversityTesis.dto;

import com.john21121.GlobantUniversityTesis.mailingsystem.RecipientType;
import lombok.Data;

@Data
public class RecipientDto {

    private Long id;
    private UserDto user;
    private MessageDto message;
    private RecipientType recipientType;


}
