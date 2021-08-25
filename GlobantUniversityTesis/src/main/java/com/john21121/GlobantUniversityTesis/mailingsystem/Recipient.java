package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "labels")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<User> user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Message message;

    @ManyToMany(mappedBy = "recipients")
    private Set<Label> labels;

    @Enumerated(value = EnumType.STRING)
    private RecipientType recipientType;

}
