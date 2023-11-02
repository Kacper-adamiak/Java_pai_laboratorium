<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.http.HttpRequest" %>
<%@ page import="java.text.DecimalFormat" %><%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 21.10.2023
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%!
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date now = new Date();
    String date = dateFormat.format(now);
    Double loanValue;
    Double loanPercentage;
    Double loanMonthlyPercentage;
    Integer loanInstallments;
    DecimalFormat df = new DecimalFormat("#.00");
    DecimalFormat df2 = new DecimalFormat("#.");
    String getLoanResult(HttpServletRequest request){
        System.out.println(request.getParameter("loanValue"));
        System.out.println(request.getParameter(String.valueOf(loanValue)));
        if (request.getParameter("wyslij")!=null){

            String result="";

            try {
                loanValue = Double.parseDouble(request.getParameter("loanValue"));
                loanPercentage = Double.parseDouble(request.getParameter("loanPercentage"));
                loanInstallments = (int) Double.parseDouble(request.getParameter("loanInstallments"));
                loanMonthlyPercentage = loanPercentage/12/100;
                if(loanValue == 0 || loanPercentage == 0 || loanInstallments == 0){
                    return "Blendne dane";
                }
                result = "Rata miesieczna: " +
                        df.format((loanValue * loanMonthlyPercentage)/
                                (1 - (1/Math.pow((1+loanMonthlyPercentage), loanInstallments))));
                return result;
            }
            catch (Exception ex) {
                if(ex instanceof NumberFormatException){
                    return "Blendne dane";
                }
                return ex.toString();
            }
        }
        return "";
    }
%>
<%
    String loanResult = getLoanResult(request);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Laboratorium 2</title>
</head>
<body>
    <p><%= date %></p>
    <form>
        <p>Kwota pożyczki</p>
        <input type="number" value="<%= loanValue != null ? loanValue : "" %>" name="loanValue" id="loanValue">
        <p>Procent roczny</p>
        <input type="number" value="<%= loanPercentage != null ? loanPercentage : "" %>" name="loanPercentage" id="loanPercentage">
        <p>Liczba rat</p>
        <input type="number" value="<%= loanInstallments != null ? loanInstallments : "" %>" name="loanInstallments" id="loanInstallments">
        <button name="wyslij" id="wyslij" value="wyslij">Wyślij</button>
    </form>
    <p><%= loanResult %></p>
</body>
</html>
