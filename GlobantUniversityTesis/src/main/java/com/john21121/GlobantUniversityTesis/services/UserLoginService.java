package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.userloggin.UserLogin;

public interface UserLoginService {

    UserLogin findById(Long l);

    void deleteById(Long deletionId);
}
