package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.repository.UserRepository;
import com.john21121.GlobantUniversityTesis.userloggin.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long l) {

        Optional<User> userOptional = userRepository.findById(l);

        if (!userOptional.isPresent()){
            throw new NotFoundException("This User Do not exist");
        }

        return userOptional.get();
    }

    @Override
    public void deleteById(Long deletionId) {

        userRepository.deleteById(deletionId);

    }
}
