package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String body;

    @ManyToOne
    private User user;

    @OneToMany
    @JoinTable(name = "message_recipient",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_id"))
    private Set<Recipient> recipient;


    public Message(String subject, String body, User user,Set< Recipient> recipient) {
        this.subject = subject;
        this.body = body;
        this.user = user;
        this.recipient = recipient;
    }
}
