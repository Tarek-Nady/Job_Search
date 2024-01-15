package com.tarek.job_search.dao;

import com.tarek.job_search.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Data Access Object (DAO) class for handling operations related to the 'User' entity.
 * Provides methods to perform database operations like insert, update, and query users.
 */
public class UserDao {
    private Connection connection;

     /**
     * Constructor to initialize UserDao with a database connection.
     * @param connection The database connection to be used for data operations.
     */
    public UserDao(Connection connection) {
        this.connection = connection;
    }

     /**
     * Adds a new user to the database.
     * @param user The user object to be added.
     * @return true if the user is added successfully, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public boolean addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, qualification, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pr = connection.prepareStatement(sql)){
            pr.setString(1, user.getName());
            pr.setString(2, user.getEmail());
            pr.setString(3, user.getPassword());
            pr.setString(4, user.getQualification());
            pr.setString(5, user.getrole());
            int row_effected = pr.executeUpdate();
            if(row_effected==1)return true;
        }
        return false;
    }

     /**
     * Authenticates a user based on email and password.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The User object if authentication is successful, null otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public User login(String email,String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement pr = connection.prepareStatement(sql)){
            pr.setString(1, email);
            pr.setString(2, password);
            try (ResultSet resultSet = pr.executeQuery()){
                if(resultSet.next()){
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("qualification"),
                            resultSet.getString("role")
                    );
                }
            }

        }
        return null;
    }

    /**
     * Updates an existing user in the database.
     * @param user The user object with updated details.
     * @return true if the update is successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, qualification = ?, role = ? WHERE id = ?";
        try (PreparedStatement pr = connection.prepareStatement(sql)){
            pr.setString(1, user.getName());
            pr.setString(2, user.getEmail());
            pr.setString(3, user.getPassword());
            pr.setString(4, user.getQualification());
            pr.setString(5, user.getrole());
            pr.setInt(6, user.getId());
            int row_effected = pr.executeUpdate();
            if(row_effected==1)return true;
        }
        return false;
    }


}
