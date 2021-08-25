package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.dto.UserDto;
import com.john21121.GlobantUniversityTesis.exceptions.ResourceNotFoundException;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import com.john21121.GlobantUniversityTesis.mappers.UserMapper;
import com.john21121.GlobantUniversityTesis.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper =userMapper;
    }

    private UserDto saveAndReturn(User user){
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public UserDto findById(Long id) {
        return userRepository.findById(id).map(userMapper::userToUserDto)
                .map(userDto -> {userDto.setId(id);
                return userDto;}).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public UserDto createNewUser(UserDto userDto) {
        return saveAndReturn(userMapper.userDtoToUser(userDto));
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return userMapper.userToUserDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto updateUserById(Long userId, UserDto userDto) {
        return userRepository.findById(userId).map(user -> {

            if (userDto.getPassword() != null){
                user.setPassword(userDto.getPassword());
            }
            if (userDto.getAddress() != null){
                user.setAddress(userDto.getAddress());
            }
            if (userDto.getCity() != null){
               user.setCity(userDto.getCity());
            }
            if (userDto.getZipCode() != null){
                user.setZipCode(userDto.getZipCode());
            }
            if (userDto.getCountry() != null){
                user.setCountry(userDto.getCountry());
            }

            return userMapper.userToUserDto(userRepository.save(user));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto saveUserByDto(Long id, UserDto userDto) {

        User user = userMapper.userDtoToUser(userDto);
        user.setId(id);

        return saveAndReturn(user);
    }

    @Override
    public void deleteById(Long deletionId) {
        userRepository.deleteById(deletionId);
    }
}
