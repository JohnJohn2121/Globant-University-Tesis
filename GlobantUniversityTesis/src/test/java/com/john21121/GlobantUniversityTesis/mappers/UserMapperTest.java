package com.john21121.GlobantUniversityTesis.mappers;

import com.john21121.GlobantUniversityTesis.dto.UserDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {


    public static final  String USERNAME = "johnjohn";
    public static final  String PASSWORD = "SpaceCowboy";
    public static final  String FIRSTNAME = "Juan";
    public static final  String LASTNAME = "Abreu";
    public static final  Long IDNUM = 12345L;
    public static final  String ADDRESS = "Humberto 1° 820";
    public static final  String ZIPCODE = "12345";
    public static final  String CITY = "Capital Federal";
    public static final  String STATE = "Buenos Aires";
    public static final  String COUNTRY = "ARGENTINA";

    UserMapper userMapper = UserMapper.INSTANCE;


    @BeforeEach
    void setUp() {
    }

    @Test
    void userToUserDto() {
        //Given
        User user = new User();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);
        user.setFirstName(FIRSTNAME);
        user.setLastName(LASTNAME);
        user.setIdentificationNum(IDNUM);
        user.setAddress(ADDRESS);
        user.setZipCode(ZIPCODE);
        user.setCity(CITY);
        user.setState(STATE);
        user.setCountry(COUNTRY);

        //When
        UserDto userDto = userMapper.userToUserDto(user);

        //Then
        assertEquals(USERNAME, userDto.getUsername());
        assertEquals(PASSWORD, userDto.getPassword());
        assertEquals(FIRSTNAME, userDto.getFirstName());
        assertEquals(LASTNAME, userDto.getLastName());
        assertEquals(IDNUM, userDto.getIdentificationNum());
        assertEquals(ADDRESS, userDto.getAddress());
        assertEquals(ZIPCODE, userDto.getZipCode());
        assertEquals(CITY, userDto.getCity());
        assertEquals(STATE, userDto.getState());
        assertEquals(COUNTRY, userDto.getCountry());

    }


}