<%--
  Created by IntelliJ IDEA.
  User: Bonilapa
  Date: 04.04.2018
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Tours$</title>
</head>
<body>
Chosen tour:
<table>
    <tr><td>Tour name:</td><td><c:out value="${name}"></c:out></td> </tr>

            <tr><td>Tour price:</td><td><br><c:out value="${price}"></c:out></td></tr>
            <tr><td>Tour Date:</td><td><br><c:out value="${date}"></c:out></td></tr>
            <tr><td>Tour Description:</td><td><br><c:out value="${description}"></c:out></td></tr>

</table>
</body>
</html>

