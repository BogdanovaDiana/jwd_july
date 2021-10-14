package com.epam.jwd.service.validator;

import com.epam.jwd.service.dto.UserDto;

import java.rmi.ServerException;

public class UserValidator implements Validator<UserDto> {
    private static final String MIN_NAME_EX = "";
    private final static Integer MIN_NAME_LENGTH = 2;
    private final static Integer MAX_NAME_LENGTH = 15;
    @Override
    public void validate(UserDto dto) throws ServerException {
        validateName(dto.getName());

    }

    private void validateName(String name) throws ServerException {
        if (name.length() < MIN_NAME_LENGTH) {
            throw new ServerException(MIN_NAME_EX);
        }
    }
}
