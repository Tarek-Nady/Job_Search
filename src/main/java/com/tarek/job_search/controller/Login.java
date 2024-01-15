package com.tarek.job_search.controller;

import com.tarek.job_search.dao.JobDao;
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
 * Servlet implementation for handling user login.
 * Mapped to "/login" URL pattern.
 */

@WebServlet("/login")
public class Login extends HttpServlet {

    
    /**
     * Handles the HTTP POST request for user login.
     * Authenticates the user based on the provided credentials and redirects to appropriate page based on the role.
     * 
     * @param req  HttpServletRequest object that contains the request the client has made of the servlet
     * @param resp HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            User user = new User();
            HttpSession session = req.getSession();
            if("tarek_98admin@gmail.com".equals(email) && "admin@7889".equals(password)){
                session.setAttribute("user",user);
                user.setrole("admin");
                resp.sendRedirect("admin.jsp");
            }
            else {
                UserDao dao = new UserDao(DbConnection.getConnection());
                User user1 = dao.login(email, password);
                if(user1!=null){
                    session.setAttribute("user",user1);
                    resp.sendRedirect("home.jsp");
                }else{
                    session.setAttribute("message","Invalid Email or Password");
                    resp.sendRedirect("login.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
