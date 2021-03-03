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
	
	<form:form method="POST" action="/tasks/${id}/edit" modelAttribute="edittask">
		<input type = "hidden" name="_method" value = "put">
		<form:input type="hidden" path="id"></form:input> 
		
			<table>
				<tr>
					<td><form:label path="taskName">Task:</form:label></td>
					<td><form:input path="taskName" class="input"/></td>
					<td><form:errors path="taskName"/></td>
				</tr>
				
				<tr>
					<td><form:label path="assignee">Assignee:</form:label></td>
					<td><form:select path="assignee" class="select">
							<form:option value="${edittask.assignee}">${edittask.assignee.getName()}</form:option>
								<c:forEach items="${users}" var="user"> 
									<c:if test="${user.name != edittask.assignee.getName()}"> 
										<c:if test="${user.name!=creator.name}">
											<form:option value="${user}">
												<c:out value="${user.name}"/>
											</form:option>
										</c:if>
									</c:if> 
								</c:forEach> 
						</form:select></td>
					<td><form:errors path="assignee" /></td>
				</tr>
				
				<tr>
					<td><form:label path="priority">Priority:</form:label></td>
					<td><form:select path="priority" class="select">
							<form:option value="1">Low</form:option>
							<form:option value="2">Medium</form:option>
							<form:option value="3">High</form:option>
						</form:select></td>
					<td><form:errors path="priority" /></td>
		</table>
						
		<form:input type="hidden" path="creator" value="${creator.id}"/>
		
		<input type="submit" value="Edit" class="button" />
	
	</form:form>
	
	<p>
		<form:errors path="task.*"></form:errors>
	</p>
	
	<p><c:out value="${error}"/></p>			
</body>
</body>
</html>