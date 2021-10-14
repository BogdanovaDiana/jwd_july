package com.epam.jwd.repository.entity;

public abstract class AbstractEntity<T> {
    public AbstractEntity() {
    }

    public AbstractEntity(T id) {
        this.id = id;
    }

    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
