<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="database.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Program</title>
</head>
<body>
<div style="text-align: center;">
    <%
        Connection connection = null;
        try {
            connection = DatabaseInit.initDatabase();
            DatabaseInit.createTables(connection);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getSession(true).setAttribute("connection", connection);
        RequestDispatcher rd = request.getRequestDispatcher("/start.jsp");
        rd.include(request, response);
    %>

</div>
</body>
</html>