package com.john21121.GlobantUniversityTesis.mailingsystem;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = "labels")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinTable(
            name="Recipient_User",
            joinColumns = @JoinColumn( name="recipient_id"),
            inverseJoinColumns = @JoinColumn( name="user_id")
    )
    private List<User> user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Message message;

    @ManyToMany(mappedBy = "recipients",cascade = CascadeType.MERGE)
    private List<Label> labels;

    @Enumerated(value = EnumType.STRING)
    private RecipientType recipientType;

}
