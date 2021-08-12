package com.john21121.GlobantUniversityTesis.userloggin;

import com.john21121.GlobantUniversityTesis.mailingsystem.Inbox;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"inboxes"})
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_userlogin",
        joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "userlogin_id"))
    private Set<UserLogin> userLogin;

    @ManyToMany
    @JoinTable(name = "user_inbox",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "inbox_id"))
    private Set<Inbox> inboxes;

}
