package com.tarek.job_search.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation for handling user logout.
 * Mapped to "/logout" URL pattern.
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {

     /**
     * Handles the HTTP GET request for user logout.
     * Terminates the user session and redirects to the login page.
     * 
     * @param req  HttpServletRequest object that contains the request the client has made of the servlet
     * @param resp HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.setAttribute("message","Logout successfully");
        resp.sendRedirect("login.jsp");
    }
}
