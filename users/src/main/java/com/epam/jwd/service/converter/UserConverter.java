package com.epam.jwd.service.converter;

import com.epam.jwd.repository.entity.User;
import com.epam.jwd.service.dto.UserDto;

public class UserConverter {
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        return userDto;
    }

    public User convert(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        return user;
    }
}
