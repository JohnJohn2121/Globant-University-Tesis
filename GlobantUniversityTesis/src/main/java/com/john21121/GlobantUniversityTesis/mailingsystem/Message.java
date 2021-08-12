package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String body;

    @Lob
    private Byte[] attachment;

    @ManyToMany(mappedBy = "messages")
    private Set<Inbox> inbox = new HashSet<>();

}
