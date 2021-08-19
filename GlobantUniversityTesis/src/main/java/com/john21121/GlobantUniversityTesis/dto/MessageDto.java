package com.john21121.GlobantUniversityTesis.dto;


import lombok.Data;

import java.util.Set;

@Data
public class MessageDto {

    private Long id;
    private String subject;
    private String body;
    private UserDto user;
    private Set<RecipientDto> recipient;

}
