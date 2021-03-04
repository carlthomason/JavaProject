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
<title>Show Fishing Location Information</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>
<body>
<div>
    <h1>Fishing site: <c:out value="${fish.fishSite}"/></h1>
    <table>
        <tr>
            <td>Location:</td>
            <td>${fish.city}, ${fish.state}</td>
        </tr>
        <tr>
            <td>Species Population:</td>
            <td>${fish.speciespopulation}</td>
        </tr>
        <tr>
            <td>Personal Best:</td>
            <td>${fish.personalbest}</td>
        </tr>


        <c:if test="${fish.user.getId()==userId}">
            <tr>
                <td><a class="button" href="/fish/${id}/edit">Edit a Fishing Site</a>
                <form action="/fish/${id}/delete" method="post" class="d-inline-block">
                                <input type="hidden" name="_method" value="delete">
                                <input type="submit" class="redbtn" value="Delete a Fishing Site">
                </form>
                </td>
            </tr>
        </c:if>
    </table>
    </div>
</body>
</html>