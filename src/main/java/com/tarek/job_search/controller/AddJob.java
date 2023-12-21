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
import java.sql.SQLException;

@WebServlet("/add_job")
public class AddJob extends HttpServlet {
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
