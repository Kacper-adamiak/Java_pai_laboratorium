<%@ page import="com.jee_laboratorium_3.CountryBean" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: kacper
  Date: 26.10.2023
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<CountryBean> list =
        (ArrayList<CountryBean>)session.getAttribute("list");
%>
<html>
<head>
    <title>Country List</title>
</head>
<body>
    <h1>County List</h1>
    <table>
        <% for(CountryBean country: list){ %>
            <tr>
                <td>
                    <%=country.getName()%>
                </td>
                <td>
                    <%=country.getCode()%>
                </td>
                <td>
                    <%=country.getPopulation()%>
                </td>
                <td>
                    <a href="DetailsServlet?code=<%=country.getCode()%>">details</a>
                </td>
            </tr>
        <% } %>

    </table>
</body>
</html>
