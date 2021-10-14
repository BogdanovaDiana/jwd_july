package com.epam.jwd.repository.impl;

import com.epam.jwd.repository.Repository;
import com.epam.jwd.repository.connectionpool.ConnectionPool;
import com.epam.jwd.repository.connectionpool.ConnectionPoolImpl;
import com.epam.jwd.repository.entity.AbstractEntity;
import com.epam.jwd.repository.entity.User;
import com.epam.jwd.repository.entity.UserRole;
import com.epam.jwd.repository.exception.RepositoryException;
import com.epam.jwd.repository.exception.RepositoryExceptionStrings;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl implements Repository<User, Integer> {
    private static final String SQL_SAVE_USER = "INSERT INTO users(name, age, role_id) values (?, ?, ?)";

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    @Override
    public User save(User user) {
        Connection connection;
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            connection = pool.takeConnection();
            statement = connection.prepareStatement(SQL_SAVE_USER);
            statement.setString(1, user.getName());
            statement.setInt(2, user.getAge());
            statement.setInt(3, user.getRole().getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (InterruptedException | SQLException e) {
            //log here
            //InterruptedException in separate catch block
            throw new RepositoryException(RepositoryExceptionStrings.SAVE_OPERATION_FAILED_EXCEPTION, e);
        }

        return user;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Integer id) {
        return null;
    }
}
