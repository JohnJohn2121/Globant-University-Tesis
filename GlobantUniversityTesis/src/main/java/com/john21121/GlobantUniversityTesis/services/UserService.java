package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public interface UserService {

    User findById(String id);

    User createNewUser(User user);

    Optional<User> updateUserById(String userId, User user);

    Set<User> getUsers () ;

    void deleteById(String deletionId);

}
