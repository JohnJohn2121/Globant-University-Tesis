package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

@Data
@Entity
public class Recipient {

    @ManyToMany(mappedBy = "recipients")
    private User user;

    @ManyToMany(mappedBy = "recipient")
    private Message message;

    @Enumerated(value = EnumType.STRING)
    private RecipientType recipientType;

}
