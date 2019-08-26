<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file='../css/style.css' %>
    </style>
    <title>Department list</title>
</head>
<body>

<table border="1">
    <tr>
        <th colspan="4">List of department</th>
    </tr>
    <tr>
        <th>Department name</th>
        <th colspan="3">Actions</th>
    </tr>
    <c:forEach var="department" items="${departments}">
    <tr>
        <td><c:out value="${department.name}"/></td>

        <form action="/FrontController" method="post">
            <td><input type="submit" name="command" value="update"/></td>
            <input type="hidden" name="id" value="${department.id}"/></td>

        </form>
        <form action="/FrontController" method="post">
            <td><input type="submit" name="command" value="delete"/>
                <input type="hidden" name="id" value="${department.id}"/></td>
        </form>

        <form action="/FrontController" method="get">
            <td><input type="submit" name="command" value="employers list"/>
                <input type="hidden" name="id" value="${department.id}"/></td>
        </form>
        </c:forEach>
    </tr>
</table>
<br>
<form action="/FrontController" method="post">
    <input type="submit" name="command" value="insert department"/>
</form>

</body>
</html>
