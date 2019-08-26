<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file='../css/style.css' %>
    </style>
    <title>Employers list</title>
</head>
<body>

<c:set var="depName" scope="request" value="${depName}"/>

<table border="1">
    <tr>
        <th colspan="6">Employers List from <b><i>${depName}</i></b> department</th>
    </tr>
    <tr>
        <th>name</th>
        <th>department</th>
        <th>email</th>
        <th>employed at</th>
        <th colspan="2">Actions</th>
    </tr>

    <c:forEach var="employer" items="${employers}">

    <tr>
        <td><c:out value="${employer.name}"/></td>
        <td><c:out value="${depName}"/></td>
        <td><c:out value="${employer.email}"/></td>
        <td><c:out value="${employer.employedAt}"/></td>

        <form action="/FrontController" method="post">
            <td><input type="submit" name="command" value="Update"/></td>
            <input type="hidden" name="idEmployer" value="${employer.id}"/></td>

        </form>
        <form action="/FrontController" method="post">
            <td><input type="submit" name="command" value="Delete"/>
                <input type="hidden" name="idEmployer" value="${employer.id}"/></td>
        </form>
        </c:forEach>
    </tr>
</table>
<br>

<form action="/FrontController" method="post">
    <input type="submit" name="command" value="Insert employer"/>
</form>
<br>
<form action="/FrontController" method="get">
    <input type="submit" value="departments list" name="command"/>
</form>
</body>
</html>
