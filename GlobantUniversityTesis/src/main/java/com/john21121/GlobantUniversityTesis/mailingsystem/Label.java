package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"messages","user"})
@Entity
@NoArgsConstructor
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String labelName;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "labels")
    private Set<Message> messages;

    public Label( String labelName, User user) {
        this.labelName = labelName;
        this.user = user;
    }

}
