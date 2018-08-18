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
      <ul>
        <li>${film.id }</li>
        <li>${film.title}</li>
        <li>${film.description}</li>
        <li>${film.releaseYear}</li>
        <li>${film.rating}</li>
      </ul>
    </c:when>
    <c:otherwise>
      <p>No films found</p>
    </c:otherwise>
  </c:choose>
  <a href="index.html">Return to Main Menu</a>
  <form action="removeFilm.do" method="POST">
 To delete this film type the film ID:<input type="text" name="filmId">
 <button type="submit" class="btn btn-primary btn-sm" value="Submit">Delete This Film</button>
 </form>
</body>
</html>