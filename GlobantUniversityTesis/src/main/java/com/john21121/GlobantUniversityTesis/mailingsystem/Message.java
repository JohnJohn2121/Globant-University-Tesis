package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String body;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;


}
