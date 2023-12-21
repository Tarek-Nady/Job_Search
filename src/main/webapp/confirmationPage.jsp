<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Job Deletion Confirmation</title>
    <!-- Add any required CSS or JS files -->
</head>
<body>

<%-- Retrieve message from session --%>
<%
    String message = (String) session.getAttribute("message");
    session.removeAttribute("message"); // Remove message after displaying
%>

<div>
    <% if (message != null) { %>
    <p><%= message %></p>
    <% } else { %>
    <p>No message available.</p>
    <% } %>
</div>

<!-- Add navigation links or buttons as needed -->
<a href="index.jsp">Back to Home</a> <!-- Replace 'index.jsp' with the actual home page -->

</body>
</html>
