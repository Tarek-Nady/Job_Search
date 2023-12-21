package com.tarek.job_search.controller;

import com.tarek.job_search.dao.JobDao;
import com.tarek.job_search.database.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static com.tarek.job_search.database.DbConnection.getConnection;

@WebServlet("/deleteJob")
public class DeleteJob extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = req.getParameter("id") != null ? Integer.parseInt(req.getParameter("id")) : 0;
        try {
            JobDao dao = new JobDao(DbConnection.getConnection());
            boolean effected =  dao.deleteJob(id);
            HttpSession session = req.getSession();
            if(effected){
                session.setAttribute("message", "Job deleted successfully");
                resp.sendRedirect("view_job.jsp");
            }else {
                session.setAttribute("message", "Something went wrong");
                resp.sendRedirect("view_job.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
