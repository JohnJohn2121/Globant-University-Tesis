package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Inbox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Recipient recipient;

    @ManyToMany
    @JoinTable(name = "inbox_messages",
            joinColumns = @JoinColumn(name = "inbox_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    private Set<Message> messages = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "inbox_labels",
    joinColumns = @JoinColumn(name = "inbox_id"),
        inverseJoinColumns = @JoinColumn(name = "label_id"))
    private Set<Label> labels = new HashSet<>();

}
