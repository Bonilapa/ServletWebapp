<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Bonilapa
  Date: 11.03.2018
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <div>
    Hello, <c:out value = "${userName}"></c:out>
    <form method="post">
      <input type="hidden" name="dislogin" value="dislogin">
      <input type = "submit" value = "Dislogin"/>
    </form>
  </div>
  Tour agency start page.
  <p><a href="/tour" method="post" name="getDescription" value="tour">Tour</a></p>
  <p><a href="/order" method="post" name="getDescription" value="order">Orders</a></p>
  <p><a href="/login" method="post" name="getDescription" value="login">Login</a></p>
  <p><a href="/register" method="post" name="getDescription" value="register">Register</a></p>
  </body>
</html>
