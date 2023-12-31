<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.tarek.job_search.entity.Job"%>
<%@page import="com.tarek.job_search.database.DbConnection"%>
<%@page import="com.tarek.job_search.dao.JobDao"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.Instant" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User : Home</title>
    <%@include file="component/all_css.jsp"%>
</head>


<body style="background-color: #d5d5d6;">

<c:if test="${empty user }">
    <c:redirect url="login.jsp"></c:redirect>
</c:if>
<%@include file="component/navbar.jsp"%>

<div class="container mb-5">
    <div class="row">
        <div class="col-md-12">

            <h5 class="text-center text-dark">All Jobs</h5>

            <c:if test="${not empty message }">
                <h4 class="text-center text-success">${message }</h4>
                <c:remove var="succMsg" />
            </c:if>


            <div class="card bg-dark text-white">
                <div class="card-body">

                    <form class="form-inline" action="more_view.jsp" method="get">
                        <div class="from-group col-md-5 mt-1 d-inline ">
                            <h4 style="display: inline;" class="text-white">Location</h4>
                        </div>

                        <div class="form-group col-md-5 mr-5 d-inline">
                            <select name="loc" class="custom-select bg-light text-dark border border-dark"
                                    id="inlineFormCustomSelectPref1">
                                <option selected>Choose...</option>
                                <option value="cairo">cairo</option>
                                <option value="alex">alex</option>
                                <option value="giza">giza</option>
                                <option value="menofia">menofia</option>
                                <option value="suez">suze</option>
                                <option value="gharbia">gharbia</option>
                                <option value="aswa">aswan</option>
                                <option value="assuit">assuit</option>
                                <option value="ismaillia">ismaillia</option>
                            </select>
                        </div>

                        <div class="from-group col-md-5 mt-1 ml-5 d-inline">
                            <div style="width:10px; display: inline-block;"> </div>
                            <h4 style="display: inline;" class="ml-5 text-white">Category</h4>
                        </div>

                        <div class="form-group col-md-5 d-inline">
                            <select class="custom-select bg-light text-dark border border-dark" id="inlineFormCustomSelectPref"
                                    name="cat">
                                <option value="ca" selected>Choose...</option>
                                <option value="IT">Information Technology</option>
                                <option value="Devloper">Devlopement</option>
                                <option value="Banking">Banking</option>
                                <option value="Engineer">Engineering</option>
                                <option value="Teacher">Teaching</option>
                            </select>
                        </div>
                        <br>
                        <br>
                        <button class="btn btn-outline-light  btn-block"><strong>Search</strong></button>
                    </form>

                </div>
            </div>

            <%
                JobDao dao = new JobDao(DbConnection.getConnection());
                List<Job> list = dao.getAllJobs();
                for (Job j : list) {
            %>
            <div class="card mt-2 opacity-80 bg-light text-dark"
                 style="box-shadow: 8px 9px 19px -10px rgba(33, 37, 41, 1);">
                <div class="card-body">
                    <div class="text-center text-secondary">
                        <i class="fa-regular fa-clipboard fa-2x"></i>
                    </div>



                    <h6><%=j.getTitle()%></h6>

                    <%
                        if (j.getDescription().length() > 0 && j.getDescription().length() < 120) {
                    %>
                    <p><%=j.getDescription()%></p>
                    <%
                    } else {
                    %>
                    <p><%=j.getDescription().substring(0, 120)%></p>...<%
                    }
                %>


                    <br>
                    <div class="form-row">
                        <div class="form-group col col-md-3 d-inline-block">
                            <input type="text" class="form-control form-control-sm"
                                   value="Location: <%=j.getLocation()%>" readonly>
                        </div>

                        <div class="form-group col col-md-3 d-inline-block">
                            <input type="text" class="form-control form-control-sm"
                                   value="Category: <%=j.getCategory()%>" readonly>
                        </div>
                    </div>

                    <h6 class="mt-3">
                        Publish Date:<%long d = j.getPdate();
                        Instant instant = Instant.ofEpochMilli(d);
                        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        String formattedDate = localDateTime.format(formatter);
                    %><%=formattedDate%></h6>
                    <div class=" text-center mt-4">
                        <a href="one_view.jsp?id=<%=j.getId()%>"
                           class="btn btn-outline-secondary  btn-block btn-sm">View More</a>
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