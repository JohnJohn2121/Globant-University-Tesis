package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.repository.UserLogginRepository;
import com.john21121.GlobantUniversityTesis.userloggin.UserLogin;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    private final UserLogginRepository userLogginRepository;

    public UserLoginServiceImpl(UserLogginRepository userLogginRepository) {
        this.userLogginRepository = userLogginRepository;
    }

    @Override
    public UserLogin findById(Long l) {

        Optional<UserLogin> userLogin = userLogginRepository.findById(l);

        if (!userLogin.isPresent()){
            throw new NotFoundException("This User Do not exist");
        }

        return userLogin.get();
    }

    @Override
    public void deleteById(Long deletionId) {
        userLogginRepository.deleteById(deletionId);

    }
}
