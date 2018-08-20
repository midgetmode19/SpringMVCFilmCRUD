<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Search by Film ID</title>
</head>
<body>
	<form action="findFilmByKeyword.do" method="GET">
		Enter Keyword <input type="text" name="keyWord" />
		<button type="submit" class="btn btn-primary btn-sm" value="Search">Go</button>
	</form>
</body>