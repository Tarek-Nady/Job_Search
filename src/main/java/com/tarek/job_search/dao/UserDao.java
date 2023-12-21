package com.tarek.job_search.dao;

import com.tarek.job_search.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }
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
