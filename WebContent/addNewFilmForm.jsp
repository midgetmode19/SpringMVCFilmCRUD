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
<title>Add New Film</title>
</head>
<body>
	<form action="AddFilm.do" method="POST">

		<br> Title<br>
		<input type="text" name="title" value="Joe"/> <br> Description<br>
		<input type="text" name="description" value="Dirt"/> <br> Release Year<br>
		<input type="text" name="releaseYear" value="1999"/> 
		 
		<select class="custom-select" name="languageID">
			<option selected>Language Id</option>
			<option value="1">English</option>
			<option value="2">Italian</option>
			<option value="3">Japanese</option>
			<option value="4">Mandarin</option>
			<option value="5">French</option>
			<option value="6">German</option>
		</select> <br> Rental Duration<br>
		 
		<select
			class="custom-select" name="rentalDuration">
			<option selected>Rental Duration</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
		</select> <br> Rental Rate<br>
		<select class="custom-select" name="rentalRate">
			<option selected>Rental Rate</option>
			<option value="0.99">$0.99</option>
			<option value="2.99">$2.99</option>
			<option value="4.99">$4.99</option>
		</select> <br>Film Length (in minutes)<br>
		<input type="text" name="length" value="120"/> <br>Replacement Cost<br>
		<input type="text" name="replacementCost" value="7.99"/> <br> Rating(G, PG,
		PG-13, R, NC-17)<br>
		<input type="text" name="rating" value="R"/><br>
		<br> Special Features<br>
		<select class="custom-select" name="specialFeatures">
			<option selected>Special Features</option>
			<option value="Deleted Scenes">Deleted Scenes</option>
			<option value="Behind the Scenes">Behind the Scenes</option>
			<option value="Commentaries">Commentaries</option>
			<option value="Trailers">Trailers</option>
		</select>
		
		<button type="submit" class="btn btn-primary btn-sm" value="Submit">Submit</button>
	</form>
</body>
</html>