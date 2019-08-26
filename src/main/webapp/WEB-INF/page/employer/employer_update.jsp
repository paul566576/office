<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <style><%@include file='../css/style.css' %></style>
    <title>Update employer</title>
</head>
<body>

<c:set var="name" scope="request" value="${empl.name}"/>
<c:set var="id" scope="request" value="${empl.id}"/>
<c:set var="depName" scope="request" value="${depName}"/>
<c:set var="email" scope="request" value="${empl.email}"/>
<c:set var="date" scope="request" value="${empl.employedAt}"/>

<c:set var="newname" scope="request" value="${newName}"/>
<c:set var="newDepname" scope="request" value="${newDepName}"/>
<c:set var="newemail" scope="request" value="${newEmail}"/>
<c:set var="newdate" scope="request" value="${newDate}"/>

<c:set var="back" scope="request" value="${depId}"/>


<form action="/FrontController" method="post">
    <h4>Update employer</h4>

<c:if test="${!isNameExist}">
    <h3>${emailMessage}</h3>

    <p>name:</p> <input type="text" name="name"  value="${newname}"  required/><br>
    <p>department name:</p> <input type="text" name="DepName" value="${newDepname}"  required/><br>
    <p>email:</p> <input type="email" name="email" value="${newemail}" required/><br>
    <p>employed date:</p> <input type="date" name="date" value="${newdate}"  required/><br>

    <input type="hidden" name="id" value="${id}"/>
    <input type="hidden" name="idDepartment" value="${depId}"/>


</c:if>
<c:if test="${isNameExist}">
        <p>name:</p> <input type="text" name="name"  value="${name}"  required/><br>
        <p>department name:</p> <input type="text" name="DepName" value="${depName}"  required/><br>
        <p>email:</p> <input type="email" name="email" value="${email}" required/><br>
        <p>employed date:</p> <input type="date" name="date" value="${date}"  required/><br>

        <input type="hidden" name="id" value="${id}"/>
        <input type="hidden" name="idDepartment" value="${depId}"/>
        <br>

</c:if>
<br>
<input type="submit" name="command" value="Update employer"/>
    <input type="hidden" name="id" value="${DEP_ID}"/></td>
</form>

<form action="/FrontController" method="get">
    <input type="submit" value="employers list" name="command" />
    <input type="hidden" name="id" value="${back}"/></td>

</form>
</body>
</html>
