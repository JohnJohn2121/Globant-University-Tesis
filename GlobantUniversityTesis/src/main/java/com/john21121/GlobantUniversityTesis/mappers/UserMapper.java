package com.john21121.GlobantUniversityTesis.mappers;

import com.john21121.GlobantUniversityTesis.dto.UserDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);


}
