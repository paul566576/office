<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>

<html>

<c:set var="title" value="Error" scope="page" />

<body>

	<table id="main-container">

		<tr >
			<td class="content">

				<h2 class="error">
					The following error occurred
				</h2>
			
				<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
				<c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
				<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

				<c:if test="${not empty code}">
					<h3>Error code: ${code}</h3>
				</c:if>

				<c:if test="${not empty message}">
					<h3>${message}</h3>
				</c:if>
				
				<c:if test="${not empty exception}">
					<% exception.printStackTrace(new PrintWriter(out)); %>
				</c:if>
				
				<c:if test="${not empty requestScope.errorMessage}">
					<h3>${requestScope.errorMessage}</h3>
				</c:if>

			</td>
		</tr>

	</table>
</body>
</html>