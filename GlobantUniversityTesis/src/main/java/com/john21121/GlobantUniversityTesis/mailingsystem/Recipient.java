package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Recipient {

    @OneToMany
    private User user;

    @OneToMany
    private Message message;

    @Enumerated(value = EnumType.STRING)
    private RecipientType recipientType;

}
