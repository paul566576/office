<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style><%@include file='../css/style.css' %></style>
    <title>Update Department</title>
</head>
<body>

<c:set var="depName" scope="request" value="${dep}"/>

<form action="/FrontController" method="post">
    <h4>Update department</h4>

    <c:if test="${isNameExist}">
        <h4>${emailMessage}</h4>

        <input type="text" name="newName"  value="${newName}" required/><br>
        <input type="hidden" name="id" value="${id}"/>
    </c:if>

<c:if test="${!isNameExist}">
<p>name:</p>
    <input type="text" name="newName"  value="${depName.name}" required/><br>
    <input type="hidden" name="id" value="${depName.id}"/>
</c:if>
    <br>
    <input type="submit" name="command" value="update department"/>
</form>
<br>
<form action="/FrontController" method="get">
    <input type="submit" value="departments list" name="command" />
</form>
</body>
</html>
