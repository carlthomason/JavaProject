<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
<body>
	<h1>Create a new task</h1>
	
	<form:form method="POST" action="/tasks/new" modelAttribute="task">
		<table>
			<tr>
				<td><form:label path="taskName">Task:</form:label></td>
				<td><form:input path="taskName" class="input" /></td>				
			</tr>
								
			<tr>
				<td><form:label path="assignee">Assignee:</form:label></td>
				<td><form:select path="assignee" class="select is-one-third">
					<form:option value=""></form:option>
						<c:forEach items="${users}" var="user">
							<c:if test="${user.name != currentUser.name}">
								<form:option value="${user}">
									<c:out value="${user.name}" />
								</form:option>
							</c:if>
						</c:forEach>
					</form:select></td>
			</tr>
			
			<tr>
				<td><form:label path="priority">Priority:</form:label></td>
				<td><form:select path="priority" class="select is-one-third">
						<form:option value=""></form:option>
						<form:option value="1">Low</form:option>
						<form:option value="2">Medium</form:option>
						<form:option value="3">High</form:option>
					</form:select></td>
			</tr>
		</table>
						
		<input type="submit" value="Create" class="button" />
		
	</form:form>
	
	<p>
		<form:errors path="task.*"></form:errors>
	</p>
	
	<p><c:out value="${errors}"/></p>
	
</body>
</body>
</html>