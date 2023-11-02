<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 21.10.2023
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DecimalFormat df = new DecimalFormat("#.00");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
    <jsp:useBean id="loan" class="com.example.jee_laboratorium_2.LoanBean" scope="session">
    </jsp:useBean>
    <jsp:setProperty name="loan" property="*" />
    <p>Kwota pożyczki</p>
    <input type="text" value="<%= loan.getLoanValue()%>" name="loanValue" id="loanValue">
    <p>Procent roczny</p>
    <input type="number" value="<%= loan.getLoanPercentage() %>" name="loanPercentage" id="loanPercentage">
    <p>Liczba rat</p>
    <input type="number" value="<%= df.format(loan.getLoanInstallments()) %>" name="loanInstallments" id="loanInstallments">
    <button name="wyslij" id="wyslij" value="wyslij">Wyślij</button>
</form>
    <p><%= loan.getInstallment() %></p>
</body>
</html>
