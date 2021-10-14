package com.epam.jwd.service;

import com.epam.jwd.service.dto.AbstractDto;

import java.rmi.ServerException;

public interface Service<T extends AbstractDto<K>, K> {
    T create(T entity) throws ServerException;
    T update(T entity);
    void delete(T entity);
    T getById(K id);
    T getAll();
}
