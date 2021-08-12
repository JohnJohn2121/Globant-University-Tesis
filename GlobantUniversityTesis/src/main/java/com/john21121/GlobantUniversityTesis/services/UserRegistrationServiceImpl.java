package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.repository.UserRegistrationRepository;
import com.john21121.GlobantUniversityTesis.userloggin.UserRegistration;

import java.util.Optional;

public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRegistrationRepository userRegisRepository;

    public UserRegistrationServiceImpl(UserRegistrationRepository userRegisRepository) {
        this.userRegisRepository = userRegisRepository;
    }

    @Override
    public UserRegistration findById(Long l) {

        Optional<UserRegistration> userRegistration = userRegisRepository.findById(l);

        if (!userRegistration.isPresent()){
            throw new NotFoundException("This Registration was never made");
        }


        return userRegistration.get();
    }

    @Override
    public void deleteById(Long deletionId) {
        userRegisRepository.deleteById(deletionId);
    }


}