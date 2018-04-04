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
    <title>$Base$</title>
  </head>
  <body>
  users:
  <table>
      <c:forEach items="${requestScope.list}" var="user">
          <tr>
              <td><c:out value="${user.id}"></c:out></td>
              <td><c:out value="${user.login}"></c:out></td>
              <td><c:out value="${user.password}"></c:out></td>
          </tr>
      </c:forEach>
  </table>
  </body>
</html>
