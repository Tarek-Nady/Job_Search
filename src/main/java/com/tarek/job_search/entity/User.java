package com.tarek.job_search.entity;

/**
 * Entity class representing a User.
 * Contains user-related information and methods to access and modify it.
 */
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String qualification;
    private String role;

    public User() {
    }

      /**
     * Parameterized constructor to create a user object with specified details.
     * @param id The user's ID.
     * @param name The user's name.
     * @param email The user's email address.
     * @param password The user's password.
     * @param qualification The user's qualification.
     * @param role The user's role.
     */
    public User(int id, String name, String email, String password, String qualification, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.qualification = qualification;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getrole() {
        return role;
    }

    public void setrole(String role) {
        this.role = role;
    }
}
