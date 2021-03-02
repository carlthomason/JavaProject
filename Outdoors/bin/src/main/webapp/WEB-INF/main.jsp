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
<title>Welcome</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
	<h1> Welcome, <c:out value="${user.firstName}" /></h1>
	<a href="/logout">Logout</a>
	
<div id = "hunt">
	<img src="<c:url value="${images/buck.img}"/>"/>
	<th><a href="/Hunt/home">Hunting</a></th>	
</div>
	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th class = "hunt"><a href="/Hunt/home">Hunting</a></th>
				<th><a href="/Fish/home">Fishing</a></th>
				<th><a href="/Camp/home">Camping</a></th>
			</tr>
		</thead>
	</table>

</body>
</html>