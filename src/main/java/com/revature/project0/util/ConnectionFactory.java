package com.revature.project0.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory factory = new ConnectionFactory();

    private Properties props = new Properties();

    private ConnectionFactory() {
        try {
            props.load(new FileReader("./src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // get the connection factory
    public static ConnectionFactory getInstance() {
        return factory;
    }

    // factory for connection objects
    public Connection getConnection() {
        //Singleton
        Connection conn = null;

        try {

            // Force the JVM to load the PostGreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password"));

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Could not connect!");
        }

        if (conn == null) {
            throw new RuntimeException("Failed to establish connection.");
        }

        return conn;

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
