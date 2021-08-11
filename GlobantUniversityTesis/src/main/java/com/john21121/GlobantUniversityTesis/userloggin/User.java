package com.john21121.GlobantUniversityTesis.userloggin;

import com.john21121.GlobantUniversityTesis.mailingsystem.Inbox;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UserRegistration userRegistration;
    private UserLogin userLogin;
    private Inbox inbox;

}
