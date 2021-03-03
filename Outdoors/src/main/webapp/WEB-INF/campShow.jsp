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
	
	<h1>Task: <c:out value="${task.taskName}" /></h1>
	
	<table>
		<tr>
			<td>Creator:</td>
			<td>${task.creator.getName()}</td>
		</tr>
		<tr>
			<td class="subtitle">Assignee:</td>
			<td class="subtitle">${task.assignee.getName()}</td>
		</tr>
		<tr>
			<td>Priority:</td>
				<c:if test="${task.priority==1}">
					<td class="subtitle">Low</td>
				</c:if>
				<c:if test="${task.priority==2}">
					<td class="subtitle">Medium</td>
				</c:if>
				<c:if test="${task.priority==3}">
					<td class="subtitle">High</td>
				</c:if>
		</tr>	
						
		<c:if test="${task.creator.getId()==currentUserId}">
			<tr>
				<td><a class="button" href="/tasks/${task.id}/edit">Edit</a></td>
				<td><a class="button" href="/tasks/${task.id}/delete">Delete</a></td>
			</tr>
		</c:if>
	</table>
					
	<c:if test="${task.assignee.getId()==currentUserId}">
		<a href="/tasks/${task.id}/delete" class="button">Completed</a>
	</c:if>
				 
</body>
</body>
</html>