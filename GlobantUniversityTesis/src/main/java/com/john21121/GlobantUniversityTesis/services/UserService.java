package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    UserDto findById(Long id);

    UserDto createNewUser(UserDto userDto);

    UserDto updateUserById(Long userId, UserDto userDto);

    List<UserDto> getUsers ();

    UserDto saveUserByDto(Long id, UserDto userDto);

    void deleteById(Long deletionId);

}
