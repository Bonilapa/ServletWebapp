<%--
  Created by IntelliJ IDEA.
  User: Bonilapa
  Date: 04.04.2018
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<div>
    Hello, <c:out value = "${userName}"></c:out>
    <form method="post">
        <input type="hidden" name="dislogin" value="dislogin">
        <input type = "submit" value = "Dislogin"/>
    </form>
</div>
<p>Map:</p>
<p><a href="/tour" method="post" name="getDescription" value="tour">Tours</a></p>
<p><a href="/hello" method="post" name="getDescription" value="hello">Index</a></p>
<p><a href="/login" method="post" name="getDescription" value="login">Login</a></p>
<p><a href="/register" method="post" name="getDescription" value="register">Register</a></p>
Ordered tour:
<table>
    <tr><td>Tour name:</td><td><c:out value="${name}"></c:out></td> </tr>
    <tr><td>Tour price:</td><td><br><c:out value="${price}"></c:out></td></tr>
    <tr><td>Tour Date:</td><td><br><c:out value="${date}"></c:out></td></tr>
    <tr><td>Tour Description:</td><td><br><c:out value="${description}"></c:out></td></tr>

</table>
</body>
</html>