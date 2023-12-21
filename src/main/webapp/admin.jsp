<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <%@include file="component/all_css.jsp"%>
    <style type="text/css">
        .back-img {
            background: url("img/admin_page.jpg");
            height: 80vh;
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>
</head>
<body>
<c:if test="${userobj.role ne 'admin' }">
    <c:redirect url="login.jsp"></c:redirect>
</c:if>
<%@include file="component/navbar.jsp"%>

<div class="container-fluid back-img">
    <div class="text-center">
        <h1 class="text-black p-4">Welcome Admin</h1>
    </div>
</div>
<%@include file="component/footer.jsp"%>
</body>
</html>