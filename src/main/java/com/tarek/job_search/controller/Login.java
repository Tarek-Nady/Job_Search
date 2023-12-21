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

@WebServlet("/login")
public class Login extends HttpServlet {
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
