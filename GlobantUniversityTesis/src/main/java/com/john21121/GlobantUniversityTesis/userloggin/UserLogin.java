package com.john21121.GlobantUniversityTesis.userloggin;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<UserRegistration> userRegistrations = new HashSet<>();

}
