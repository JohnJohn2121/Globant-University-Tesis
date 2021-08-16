package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.mailingsystem.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    User findById(String id);

    User createNewUser(User user);

    Optional<User> updateUserById(String userId, User user);



    Set<User> getUsers () ;

    void deleteById(String deletionId);

}
