package com.john21121.GlobantUniversityTesis.mailingsystem;

import com.sun.istack.NotNull;
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

    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private List<User> user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Message message;

    @ManyToMany(mappedBy = "recipients",cascade = CascadeType.MERGE)
    private List<Label> labels;

    @Enumerated(value = EnumType.STRING)
    private RecipientType recipientType;

}
