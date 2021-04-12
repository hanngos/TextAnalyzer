<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="database.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Database</title>
    <style>
        table, th, td {
            border: 1px solid black;
            text-align: center;
        }
        table.center {
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<%
    HttpSession s = request.getSession(false);
    PrintWriter o = response.getWriter();
    if (s == null) {
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        o.println("<font color=red>Session timeout!</font>");
        rd.include(request, response);
    } else {
        Connection connection = (Connection) s.getAttribute("connection");
        o.println("<FORM ACTION=\"/TextAnalyzer_war_exploded/start.jsp\">");
        o.println("<label><INPUT NAME=\"BUTTON\" TYPE=\"SUBMIT\" VALUE=\"NEW\"></label></FORM>");
        o.println("<FORM ACTION=\"/TextAnalyzer_war_exploded/results\"><label><INPUT NAME=\"BUTTON\" TYPE=\"SUBMIT\" VALUE=\"SEE RESULTS\"></label></FORM>");
        o.println("<div style=\"text-align: center;\">\n" +
                "<h1><u>DATABASE</u></h1>\n" +
                "<HR><H2>TEXTS TABLE</H2>");
        try {
            TextsCRUD.select(connection, o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        o.println("<HR><H2>INFO TABLE</H2>");
        try {
            InfoCRUD.select(connection, o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        o.println("<HR><H2>LAST CHAR TABLE</H2>");
        try {
            LastCharCRUD.select(connection, o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        o.println("<HR><H2>WORDS TABLE</H2>");
        try {
            WordsCRUD.select(connection, o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        o.println("<HR><H2>PALINDROMES TABLE</H2>");
        try {
            PalindromesCRUD.select(connection, o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        o.println("<HR><H2>DIACRITICS TABLE</H2>");
        try {
            DiacriticsCRUD.select(connection, o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        o.println("</div>");
    }
        %>
</body>
</html>