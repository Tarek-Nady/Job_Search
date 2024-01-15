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

/**
 * Servlet implementation for deleting a job.
 * Mapped to "/delete" URL pattern.
 */
@WebServlet("/delete")
public class DeleteJob extends HttpServlet {
     /**
     * Handles the HTTP POST request.
     * Receives the job ID from the request and attempts to delete the corresponding job from the database.
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
//            System.out.println("hello  "+id);
            JobDao dao = new JobDao(DbConnection.getConnection());
            dao.deleteJob(id);
            resp.sendRedirect("confirmationPage.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
