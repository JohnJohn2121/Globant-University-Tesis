package com.john21121.GlobantUniversityTesis.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
@Component
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Long identificationNum;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private String country;


}
