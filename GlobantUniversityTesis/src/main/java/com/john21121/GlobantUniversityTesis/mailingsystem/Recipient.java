package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;

@Data
@Entity
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Message message;

    @Enumerated(value = EnumType.STRING)
    private RecipientType recipientType;

}
