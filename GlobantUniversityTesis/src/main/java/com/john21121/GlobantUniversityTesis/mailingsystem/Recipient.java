package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ValueGenerationType;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "labels")
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Message message;

    @ManyToMany(mappedBy = "recipients")
    private Set<Label> labels;

    @Enumerated(value = EnumType.STRING)
    private RecipientType recipientType;

}
