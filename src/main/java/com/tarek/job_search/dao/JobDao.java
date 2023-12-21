package com.tarek.job_search.dao;

import com.tarek.job_search.entity.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JobDao {
    private Connection connection;
    private PreparedStatement ps;

    public JobDao(Connection connection) {
        this.connection = connection;
    }

    public boolean addJob(Job job) throws SQLException {
        String sql = "INSERT INTO jobs (title, description, category, location, status, pdate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, job.getTitle());
            statement.setString(2, job.getDescription());
            statement.setString(3, job.getCategory());
            statement.setString(4, job.getLocation());
            statement.setString(5, job.getStatus());
            statement.setDate(6, new java.sql.Date(job.getPdate().getTime()));
            int row_effected = statement.executeUpdate();
            if(row_effected > 0) {
                return true;
            }
        }
        return false;
    }

    public Job getJobById(int id) throws SQLException {
        String sql = "SELECT * FROM jobs WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Job(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("category"),
                            resultSet.getString("location"),
                            resultSet.getString("status"),
                            resultSet.getDate("pdate")
                    );
                }
            }
        }
        return null;
    }

    public List<Job> getAllJobs() throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM jobs";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                jobs.add(new Job(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("category"),
                        resultSet.getString("location"),
                        resultSet.getString("status"),
                        resultSet.getDate("pdate")
                ));
            }
        }
        return jobs;
    }

    public boolean updateJob(Job job) throws SQLException {
        String sql = "UPDATE jobs SET title = ?, description = ?, category = ?, location = ?, status = ?, pdate = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, job.getTitle());
            statement.setString(2, job.getDescription());
            statement.setString(3, job.getCategory());
            statement.setString(4, job.getLocation());
            statement.setString(5, job.getStatus());
            statement.setDate(6, new java.sql.Date(job.getPdate().getTime()));
            statement.setInt(7, job.getId());
            int row_effected = statement.executeUpdate();
            if(row_effected==1){
                return true;
            }
        }
        return false;
    }

    public boolean deleteJob(int id) throws SQLException {
        String sql = "DELETE FROM jobs WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int row_effcted = statement.executeUpdate();
            if(row_effcted==1)return true;
        }
        return false;
    }

    public List<Job> getJobsByCategoryOrLocation(String category, String location) throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM jobs WHERE category = ? OR location = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category);
            statement.setString(2, location);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    jobs.add(new Job(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("category"),
                            resultSet.getString("location"),
                            resultSet.getString("status"),
                            resultSet.getDate("pdate")
                    ));
                }
            }
        }
        return jobs;
    }

    public List<Job> getJobsByCategoryAndLocation(String category, String location) throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM jobs WHERE category = ? AND location = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category);
            statement.setString(2, location);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    jobs.add(new Job(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            resultSet.getString("category"),
                            resultSet.getString("location"),
                            resultSet.getString("status"),
                            resultSet.getDate("pdate")
                    ));
                }
            }
        }
        return jobs;
    }

}
