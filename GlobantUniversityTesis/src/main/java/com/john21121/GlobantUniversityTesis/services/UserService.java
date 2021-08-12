package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.mailingsystem.User;

public interface UserService {

    User findById(String l);

    void deleteById(String deletionId);

}
