<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The result of your query:</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty films}">
			<ul>
				<c:forEach var="film" items="${films }">
					<li>${film }</li>
					
					<form action="removeFilm.do" method="POST">
				<strong>Delete Film:</strong><input type="text"
					name="filmId" value="${film.id }">
					
				<button type="submit" class="btn btn-primary btn-sm" value="Submit">Delete
					</button>
					<br>
					<br>
					</form>
			<form action="findFilm.do" method="GET">
			<input type="hidden" name="filmId" value="${film.id}">
			<strong>Edit Film:</strong><input type="text"
					name="filmId" value="${film.id }">
				<button type="submit" class="btn btn-primary btn-sm" value="Submit">Go</button>
				<br>
			</form>
			<br>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No films found</p>
		</c:otherwise>
	</c:choose>
	<a href="index.html">Return to Main Menu</a>


</body>
</html>