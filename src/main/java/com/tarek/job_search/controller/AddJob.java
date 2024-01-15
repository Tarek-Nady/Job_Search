package com.tarek.job_search.controller;

import com.tarek.job_search.dao.JobDao;
import com.tarek.job_search.database.DbConnection;
import com.tarek.job_search.entity.Job;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
/**
 * Servlet implementation for adding a new job.
 * Mapped to "/add_job" URL pattern.
 */
@WebServlet("/add_job")
public class AddJob extends HttpServlet {
     /**
     * Handles the HTTP POST request.
     * Receives job details from the request, creates a new job, and attempts to save it to the database.
     * 
     * @param req  HttpServletRequest object that contains the request the client has made of the servlet
     * @param resp HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String  title = req.getParameter("title");
            String  description = req.getParameter("description");
            String  location = req.getParameter("location");
            String  category = req.getParameter("category");
            String  status = req.getParameter("status");
            Job job = new Job();
            job.setCategory(category);
            job.setDescription(description);
            job.setLocation(location);
            job.setStatus(status);
            job.setTitle(title);
            HttpSession session = req.getSession();
            JobDao dao = new JobDao(DbConnection.getConnection());
            if(dao.addJob(job)) {
                session.setAttribute("message", "Job added successfully");
                resp.sendRedirect("add_job.jsp");
            }
            else{
                session.setAttribute("message", "Something went wrong");
                resp.sendRedirect("add_job.jsp");
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
