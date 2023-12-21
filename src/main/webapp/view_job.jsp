<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.tarek.job_search.entity.Job"%>
<%@page import="com.tarek.job_search.database.DbConnection"%>
<%@page import="com.tarek.job_search.dao.JobDao"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Jobs</title>
    <%@include file="component/all_css.jsp"%>
</head>

<body style="background-color: #f0f1f2;">
<c:if test="${user.role ne 'admin' }">
    <c:redirect url="login.jsp"></c:redirect>
</c:if>
<%@include file="component/navbar.jsp"%>

<div class="container mb-5">
    <div class="row">
        <div class="col-md-12">

            <h5 class="text-center text-primary">All Jobs</h5>

            <c:if test="${not empty message }">
                <div class="alert alert-success" role="alert">${message }</div>
                <c:remove var="succMsg" />
            </c:if>

            <%
                JobDao dao = new JobDao(DbConnection.getConnection());
                List<Job> list = dao.getAllJobs();
                for (Job j : list) {
            %>
            <div class="card mt-2"
                 style="box-shadow: 8px 9px 19px -10px rgba(33, 37, 41, 1);">
                <div class="card-body">
                    <div class="text-center text-primary">
                        <i class="far fa-clipboard fa-2x"></i>
                    </div>



                    <h6><%=j.getTitle()%></h6>
                    <p><%=j.getDescription()%></p>

                    <br>
                    <div class="row">
                        <div class="col col-md-3 d-inline">
                            <input type="text" class="form-control form-control-sm"
                                   value="Location: <%=j.getLocation()%>" readonly>
                        </div>

                        <div class="col col-md-3 d-inline">
                            <input type="text" class="form-control form-control-sm"
                                   value="Category: <%=j.getCategory()%>" readonly>
                        </div>

                        <div class="col col-md-3 d-inline">
                            <input type="text" class="form-control form-control-sm"
                                   value="Status: <%=j.getStatus()%>" readonly>
                        </div>
                    </div>

                    <h6 class="mt-3">
                        Publish Date:<%=j.getPdate()%></h6>
                    <div class=" text-center mt-4">
                        <a href="edit_job.jsp?id=<%=j.getId()%>"
                           class="btn btn-sm bg-success text-white"><i
                                class="fa-solid fa-pen-to-square"></i> Edit</a> <a
                            href="delete?id=<%=j.getId()%>"
                            class="btn btn-sm bg-danger text-white"><i
                            class="fa-solid fa-trash"></i> Delete</a>
                    </div>
                </div>

            </div>
            <%
                }
            %>


        </div>
    </div>
</div>

<%@include file="component/footer.jsp"%>
</body>
</html>