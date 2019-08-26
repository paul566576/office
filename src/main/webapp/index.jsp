<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Departments</title>
</head>
<body>
<form action="/FrontController" method="get">
    <input type="submit" value="departments list" name="command"/>
    <input type="hidden" value="DepList" name="command">
</form>


</body>
</html>
