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
 * Servlet implementation for handling user profile updates.
 * Mapped to "/edit_profile" URL pattern.
 */
@WebServlet("/edit_profile")
public class UpdateUser extends HttpServlet {

     /**
     * Handles the HTTP POST request for updating a user's profile.
     * Receives user profile details from the request, updates the user information, and saves the changes to the database.
     * 
     * @param req  HttpServletRequest object that contains the request the client has made of the servlet
     * @param resp HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String qualification = req.getParameter("qualification");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            System.out.println("password "+password);
            //String role = req.getParameter("role");
            UserDao dao = new UserDao(DbConnection.getConnection());
            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setQualification(qualification);
            user.setEmail(email);
            user.setPassword(password);
            user.setrole("user");
            HttpSession session = req.getSession();
            boolean effected = dao.updateUser(user);
            if(effected){
                session.setAttribute("message","Profile Updated Successfully");
                resp.sendRedirect("home.jsp");
            }else {
                session.setAttribute("message","Something went wrong on server");
                resp.sendRedirect("home.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
