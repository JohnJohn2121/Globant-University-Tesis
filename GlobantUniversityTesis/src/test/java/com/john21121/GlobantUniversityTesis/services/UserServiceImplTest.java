package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.dto.UserDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import com.john21121.GlobantUniversityTesis.mappers.UserMapper;
import com.john21121.GlobantUniversityTesis.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    UserService userService;

    public static final String USERNAME = "JohnJohn";
    public static final String FIRSTNAME = "Juan";
    public static final String LASTNAME = "Abreu";
    public static final String PASSWORD = "Katana Zero";


    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
         userService = new UserServiceImpl(userRepository,UserMapper.INSTANCE);

        UserDto userDto = new UserDto();
        userDto.setUsername(USERNAME);
        userDto.setFirstName(FIRSTNAME);
        userDto.setPassword(PASSWORD);
        userDto.setLastName(LASTNAME);
        userDto.setId(4L);

    }


    @Test
    void findById() {

        User user = new User();
        user.setUsername(USERNAME);
        user.setFirstName(FIRSTNAME);
        user.setLastName(LASTNAME);
        user.setId(3L);

        //When
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        UserDto userDto = userService.findById(3L);

        //Then
        assertEquals(USERNAME,userDto.getUsername());
        assertEquals(FIRSTNAME,userDto.getFirstName());
        assertEquals(LASTNAME,userDto.getLastName());

    }

    @Test
    void createNewUser() {
        //Given
        UserDto userDto = new UserDto();
        userDto.setUsername(USERNAME);
        userDto.setFirstName(FIRSTNAME);
        userDto.setPassword(PASSWORD);
        userDto.setLastName(LASTNAME);
        userDto.setId(1L);
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setPassword(userDto.getPassword());
        user.setLastName(userDto.getLastName());

        //When
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserDto savedUser = userService.createNewUser(userDto);
        //Then
        assertEquals(USERNAME,savedUser.getUsername());
        assertEquals(FIRSTNAME,savedUser.getFirstName());
        assertEquals(LASTNAME,savedUser.getLastName());
        assertEquals(PASSWORD,savedUser.getPassword());

    }

    //TODO Verify Later
    @Test
    void updateUserById() {
        String newPassword = ("Kar en Tuk");
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setPassword(newPassword);
        updatedUserDto.setId(4L);
        User updatedUser = new User();
        updatedUser.setPassword(updatedUserDto.getPassword());

        when(userRepository.findById(4L)).thenReturn(Optional.of(updatedUser));

        UserDto updatedDto = userService.updateUserById(4L,updatedUserDto);

        assertEquals(newPassword,updatedDto.getPassword());
    }

    @Test
    void getUsers() {
        //Given
        List<User> users = Arrays.asList(new User(),new User(),new User(), new User());
        //when
        when(userRepository.findAll()).thenReturn(users);
        List<UserDto> userDtos = userService.getUsers();

        assertEquals(4,userDtos.size());
    }

    @Test
    void deleteById() {
        //Given
        Long id = 2L;
        //When
        userRepository.deleteById(id);
        //Then
        verify(userRepository,times(1)).deleteById(anyLong());
    }
}