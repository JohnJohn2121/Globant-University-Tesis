package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_username")
    private Long id;
    private String username;
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
    private Set<Message> message;

    public User() {
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_recipient",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipient_id"))
    private Set<Recipient> recipients = new HashSet<>();

    public User(String username, String password, String firstName, String lastName, Long identificationNum,
                String address, String zipCode, String city, String state, String country) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identificationNum = identificationNum;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public User(Set<Message> message) {
        this.message = message;
    }
}
