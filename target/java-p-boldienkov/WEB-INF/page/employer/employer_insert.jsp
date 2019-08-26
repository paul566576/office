<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="back" scope="request" value="${depId}"/>


<html>
<head>
    <style><%@include file='../css/style.css' %></style>
    <title>Create employer</title>
</head>
<body>
<form action="/FrontController" method="post">
    <h4>Create new employer</h4>

<c:if test="${!isEmaisExist}">
    <h3>${emailMessage}</h3>
        <p>name:</p>  <input type="text" name="name" value="${newName}" required/><br>
        <p>email:</p><input type="email" name="email" value="${newEmail}" required/><br>
        <p>employed date:</p><input type="date" name="date" value="${newDate}" required/><br>
</c:if>

<c:if test="${isEmaisExist}">
    <p>name:</p>  <input type="text" name="name" placeholder="name" required/><br>
    <p>email:</p> <input type="email" name="email" placeholder="email" required/><br>
    <p>employed date:</p><input type="date" name="date" placeholder="date" required/><br>
</c:if>


    <br>
    <input type="submit" name="command" value="Create employer"/>
</form>
<br>

<form action="/FrontController" method="get">
    <input type="submit" value="employers list" name="command" />
    <input type="hidden" name="id" value="${back}"/></td>

</form>

</body>
</html>
