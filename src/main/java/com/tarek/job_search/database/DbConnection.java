package com.tarek.job_search.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing the database connection.
 * Contains methods and fields to establish a connection with the MySQL database.
 */
public class DbConnection {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/Job_search?autoReconnect=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    // Static initializer block to load the JDBC driver
    static {
        try {
            // Load the MySQL JDBC driver to enable database connectivity
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            // Print the stack trace if the driver class is not found
            e.printStackTrace();
        }
    }

    /**
     * Provides a database connection using the DriverManager.
     * @return A Connection object to interact with the database.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        // Establish and return a connection to the database
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
