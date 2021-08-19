package com.john21121.GlobantUniversityTesis.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Long identificationNum;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private String country;
    private Set<MessageDto> message;
    private Set<RecipientDto> recipients = new HashSet<>();

}
