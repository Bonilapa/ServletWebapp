<%--
  Created by IntelliJ IDEA.
  User: Bonilapa
  Date: 04.04.2018
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>$Tours$</title>
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
<p><a href="/hello" method="post" name="getDescription" value="hello">Index</a></p>
<p><a href="/order" method="post" name="getDescription" value="order">Orders</a></p>
<p><a href="/login" method="post" name="getDescription" value="login">Login</a></p>
<p><a href="/register" method="post" name="getDescription" value="register">Register</a></p>
Tours:
<table>
    <c:forEach items="${requestScope.list}" var="tour">
        <tr>
            <td><c:out value="${tour.name}"></c:out></td>
            <td><c:out value="${tour.price}"></c:out></td>
            <td><c:out value="${tour.date}"></c:out></td>
            <td>
                <form method="post">
                    <input type="hidden" name="getDescription" value="${tour.id}"/>
                    <input type="submit" value="Look at"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
