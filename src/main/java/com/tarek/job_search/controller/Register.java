package com.tarek.job_search.controller;

import com.tarek.job_search.dao.UserDao;
import com.tarek.job_search.database.DbConnection;
import com.tarek.job_search.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation for handling new user registration.
 * Mapped to "/add_user" URL pattern.
 */
@WebServlet("/add_user")
    public class Register extends HttpServlet {

    /**
     * Handles the HTTP POST request for user registration.
     * Receives user details from the request, creates a new user, and attempts to add it to the database.
     * 
     * @param req  HttpServletRequest object that contains the request the client has made of the servlet
     * @param resp HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String qualification = req.getParameter("qualification");
            String Password = req.getParameter("password");
            String email = req.getParameter("email");
         //   String role = req.getParameter("role");
            UserDao user = new UserDao(DbConnection.getConnection());
            User u = new User(0, name, email, Password, qualification, "admin");
            boolean effected = user.addUser(u);
            HttpSession session = req.getSession();
            if (effected) {
                session.setAttribute("message", "successfully user added");
                resp.sendRedirect("signup.jsp");
            } else {
                session.setAttribute("message", "Something went wrong");
                resp.sendRedirect("signup.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
