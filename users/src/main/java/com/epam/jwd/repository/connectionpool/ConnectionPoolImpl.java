package com.epam.jwd.repository.connectionpool;

import com.epam.jwd.repository.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

public final class ConnectionPoolImpl implements ConnectionPool {
    private static final ConnectionPool INSTANCE = new ConnectionPoolImpl();
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/jwd";
    private static final String USER = "postgres";
    private static final String PASS = "1111";
    private static final String DRIVER = "org.postgresql.Driver";
    private static int INITIAL_POOL_SIZE = 5;

    private final Queue<ProxyConnection> availableConnections = new ArrayDeque<>();
    private final List<ProxyConnection> givenAwayConnections = new ArrayList<>();

    private boolean initialized = false;

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    private ConnectionPoolImpl() {
    }

    @Override
    public synchronized boolean init() {
        try {
            if(!initialized) {
                initializeConnections(INITIAL_POOL_SIZE);
                initialized = true;
                return true;
            }
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public synchronized boolean shutDown() {
        if(initialized) {
            closeConnections();
            initialized = false;
            return true;
        }
        return false;
    }

    @Override
    public synchronized Connection takeConnection() throws InterruptedException {
        while(availableConnections.isEmpty()) {
            this.wait();
        }

        final ProxyConnection connection = availableConnections.poll();
        givenAwayConnections.add(connection);
        return connection;
    }

    @Override
    public synchronized void returnConnection(Connection connection) {
        if(givenAwayConnections.remove(connection)) {
            availableConnections.add((ProxyConnection) connection);
            this.notifyAll();
        } else {
            //log here
        }
    }

    private boolean initializeConnections(int amount) throws ConnectionPoolException {
        try {
            for (int i = 0; i < amount; i++) {
                final Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                final ProxyConnection proxyConnection = new ProxyConnection(connection, this);
                availableConnections.add(proxyConnection);
            }
            return true;
        }catch (SQLException e) {
            throw new ConnectionPoolException();
        }
    }

    private void closeConnections() {
       closeConnections(this.availableConnections);
       closeConnections(this.givenAwayConnections);
    }

    private void closeConnections(Collection<ProxyConnection> connections) {
        for (ProxyConnection connection : connections) {
            closeConnection(connection);
        }
    }

    private void closeConnection(ProxyConnection connection) {
        try {
            connection.realClose();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
