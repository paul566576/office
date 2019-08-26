<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><%@include file='../css/style.css' %></style>
    <title>Creat Departments</title>
</head>
<body>

<form action="/FrontController" method="post">
    <h4>Enter name of department</h4>

<c:if test="${!isNameExist}">
    <h3>${emailMessage}</h3>
    <input type="text" name="name" value="${newName}" required/><br>
</c:if>

<c:if test="${isNameExist}">
     <input type="text" name="name" placeholder="department name" required/><br>
</c:if>


    <br>
    <input type="submit" name="command" value="create department"/>
</form>


<form action="/FrontController" method="get">
    <input type="submit" value="departments list" name="command" />
</form>


</body>
</html>
