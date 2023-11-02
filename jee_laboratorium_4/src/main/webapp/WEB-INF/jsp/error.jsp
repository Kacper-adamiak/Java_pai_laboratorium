<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
    <title>JSP Page</title>
</head>
<body>
<h1>Error Page</h1>
<p>Failed URL: ${url}
    Exception: ${exception.message}
    <c:forEach items="${exception.stackTrace}" var="ste">
        ${ste}
    </c:forEach>
</p>
</body>
</html>
