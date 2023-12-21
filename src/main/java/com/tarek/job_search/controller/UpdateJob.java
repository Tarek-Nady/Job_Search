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
import java.util.Date;

@WebServlet("/edit_job")
public class UpdateJob extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String location = req.getParameter("location");
            String category = req.getParameter("category");
            String  pdata = req.getParameter("pdate");
            String status = req.getParameter("status");
            JobDao dao = new JobDao(DbConnection.getConnection());
            Job job = new Job();
            job.setId(id);
            job.setCategory(category);
            job.setDescription(description);
            job.setLocation(location);
            job.setStatus(status);
            job.setTitle(title);

            HttpSession session = req.getSession();
            boolean effected = dao.updateJob(job);
            if(effected){
                session.setAttribute("message","Job Updated Successfully");
                resp.sendRedirect("view_job.jsp");
            }else {
                session.setAttribute("message","Something went wrong on server");
                resp.sendRedirect("view_job.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
