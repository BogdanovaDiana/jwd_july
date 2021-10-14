package com.epam.jwd.repository.connectionpool;

import java.sql.Connection;

public interface ConnectionPool {
    boolean init();

    boolean shutDown();

    Connection takeConnection() throws InterruptedException;

    void returnConnection(Connection connection);

}
