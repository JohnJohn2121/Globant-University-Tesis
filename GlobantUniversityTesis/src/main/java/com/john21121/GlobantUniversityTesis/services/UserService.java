package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.userloggin.User;

public interface UserService {

    User findById(Long l);

    void deleteById(Long deletionId);
}
