<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film successfully added</title>
</head>
<body>
  <c:choose>
    <c:when test="${! empty film}">
      <p>Film successfully added</p>
    </c:when>
    <c:otherwise>
      <p>Failed to add film</p>
    </c:otherwise>
  </c:choose>
  <a href="index.html">Return to Main Menu</a>
</body>
</html>