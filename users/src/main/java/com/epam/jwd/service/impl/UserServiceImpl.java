package com.epam.jwd.service.impl;

import com.epam.jwd.repository.Repository;
import com.epam.jwd.repository.entity.User;
import com.epam.jwd.repository.impl.UserRepositoryImpl;
import com.epam.jwd.service.Service;
import com.epam.jwd.service.converter.UserConverter;
import com.epam.jwd.service.dto.UserDto;
import com.epam.jwd.service.validator.UserValidator;
import com.epam.jwd.service.validator.Validator;

import java.rmi.ServerException;

public class UserServiceImpl implements Service<UserDto, Integer> {
    private Repository<User, Integer> userRepository = new UserRepositoryImpl();
    private Validator<UserDto> validator = new UserValidator();

    private UserConverter converter = new UserConverter();

    @Override
    public UserDto create(UserDto user) throws ServerException {
        validator.validate(user);
        return converter.convert(userRepository.save(converter.convert(user)));
    }

    @Override
    public UserDto update(UserDto user) {
        return null;
    }

    @Override
    public void delete(UserDto user) {

    }

    @Override
    public UserDto getById(Integer id) {
        return null;
    }

    @Override
    public UserDto getAll() {
        return null;
    }
}
