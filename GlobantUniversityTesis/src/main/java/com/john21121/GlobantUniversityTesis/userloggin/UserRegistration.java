package com.john21121.GlobantUniversityTesis.userloggin;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


}
