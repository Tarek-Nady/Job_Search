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

@WebServlet("/delete")
public class DeleteJob extends HttpServlet {
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
