<%@ page import="com.jee_laboratorium_3.CountryBean" %><%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 26.10.2023
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% CountryBean details =
        (CountryBean)session.getAttribute("details");
%>
<html>
<head>
    <title>Details</title>
</head>
<body>
    <h1>Details for <%= details.getName() %></h1>
    <p>Country code: <%= details.getCode()%></p>
    <p>Population: <%= details.getPopulation()%></p>
    <p>Surfface area: <%= details.getSurfaceArea()%></p>
    <br>
    <a href="ListServlet">Powrót</a>
</body>
</html>
