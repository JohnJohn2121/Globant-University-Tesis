package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import com.john21121.GlobantUniversityTesis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()){
            throw new NotFoundException("User Not Found");
        }
        return userOptional.get();
    }
    //Todo complete
    @Override
    public User createNewUser(User user) {
        return null;
    }
    //Todo Complete
    @Override
    public User updateUserById(String userId, User user) {
        return null;
    }


    @Override
    public Set<User> getUsers() {
        Set<User> users= new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    @Override
    public void deleteById(String deletionId) {
        userRepository.deleteById(deletionId);
    }
}
