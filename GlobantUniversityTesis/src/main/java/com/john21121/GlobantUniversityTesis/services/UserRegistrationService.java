package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.userloggin.UserRegistration;

public interface UserRegistrationService {

    UserRegistration findById(Long l);


    void deleteById(Long deletionId);

}
