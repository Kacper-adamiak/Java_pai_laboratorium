<%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 21.10.2023
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h2>Wprowadzono błędne dane!</h2>
    <p>Pojawił się następujący błąd:
        <%= exception.getMessage() %>. <br />
    </p>
    <a href="calcwithbean.jsp">Powrót</a>
</body>
</html>
