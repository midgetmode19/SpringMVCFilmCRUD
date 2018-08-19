<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>The result of your query:</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty film}">
			<p><strong>Film Id:</strong> ${film.id }</p>
			<p><strong>Title:</strong> ${film.title}</p>
			<p><strong>Description:</strong> ${film.description}</p>
			<p><strong>Release Year:</strong> ${film.releaseYear}</p>
			<p><strong>Language:</strong> ${film.language}</p>
			<p><strong>Rental Duration:</strong> ${film.rentalDuration}</p>
			<p><strong>Rental Rate:</strong> ${film.rentalRate}</p>
			<p><strong>Length:</strong> ${film.length}</p>
			<p><strong>Replacement Cost:</strong> ${film.replacementCost}</p>
			<p><strong>Rating:</strong> ${film.rating}</p>
			<p><strong>Special Features:</strong> ${film.specialFeatures}</p>
			

 
    </form>
   <%-- <p>
    <form action="editFilm.jsp" method="POST" value="${film.id}"> 
				
	<input type="hidden" name="filmId" value="${film.id}">
	<button type="submit" class="btn btn-primary btn-sm" value="Submit">Update this film?</button>
	</form>
	</p>  --%>
    
    
			<p>
				<form action="editFilm.do" method="POST" value="${film.id}"> 
				
				<input type="hidden" name="filmId" value="${film.id}">
				<h4>Update Fields:</h4>
		<br>New Title<br> <input type="text" name="title" /> <br>
		Description<br> <input type="text" name="description" /> <br>
		Release Year<br> <input type="text" name="releaseYear" /> <select
			class="custom-select" name="languageID">
			<option selected>Language Id</option>
			<option value="1">English</option>
			<option value="2">Italian</option>
			<option value="3">Japanese</option>
			<option value="4">Mandarin</option>
			<option value="5">French</option>
			<option value="6">German</option>
		</select> <br> Rental Duration<br> <select class="custom-select"
			name="rentalDuration">
			<option selected>Rental Duration</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
		</select> <br> Rental Rate<br> <select class="custom-select"
			name="rentalRate">
			<option selected>Rental Rate</option>
			<option value="0.99">$0.99</option>
			<option value="2.99">$2.99</option>
			<option value="4.99">$4.99</option>
		</select> <br> Film Length (in minutes)<br> <input type="text"
			name="length" /> <br> Replacement Cost<br> <input
			type="text" name="replacementCost" /> <br> Rating(G, PG, PG-13,
		R, NC-17)<br> <input type="text" name="rating" /><br> <br>
		Special Features<br> <select class="custom-select"
			name="specialFeatures">
			<option selected>Special Features</option>
			<option value="Deleted Scenes">Deleted Scenes</option>
			<option value="Behind the Scenes">Behind the Scenes</option>
			<option value="Commentaries">Commentaries</option>
			<option value="Trailers">Trailers</option>
		</select> <br>
		<button type="submit" class="btn btn-primary btn-sm" value="Submit">Submit</button>
	</form>
	
			</p>
			<form action="removeFilm.do" method="POST">
				Delete the film:<input type="text"
					name="filmId" value="${film.id}">
				<button type="submit" class="btn btn-primary btn-sm" value="Submit">Delete
					This Film</button>
			</form>
		</c:when>
		<c:otherwise>
			<p>No films found</p>
		</c:otherwise>
	</c:choose>
	<a href="index.html">Return to Main Menu</a>


</body>
</html>