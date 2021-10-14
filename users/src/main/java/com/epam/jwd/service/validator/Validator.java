package com.epam.jwd.service.validator;

import java.rmi.ServerException;

public interface Validator<T> {
    void validate(T dto) throws ServerException;
}
