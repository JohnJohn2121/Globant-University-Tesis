package com.john21121.GlobantUniversityTesis.mailingsystem;

import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Long identificationNum;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private String country;

    @OneToMany(cascade = CascadeType.ALL)
    private Message message;

    @ManyToMany
    @JoinTable(name = "user_recipient",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_id"))
    private Set<Recipient> recipients = new HashSet<>();


}
