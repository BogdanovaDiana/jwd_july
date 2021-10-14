package com.epam.jwd.repository;

import com.epam.jwd.repository.entity.AbstractEntity;

import java.util.List;

/**
 *
 * @param <T> - entity to be handled
 * @param <K> - the type of the id
 */
public interface Repository<T extends AbstractEntity<K>, K> {
    T save(T entity);
    T update(T entity);
    void delete(T entity);
    List<T> findAll();
    T findById(K id);
}
