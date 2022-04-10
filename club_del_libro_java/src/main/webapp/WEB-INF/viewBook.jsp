<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View book</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<div class="row">
			<h1 class="col-10">
				<c:out value="${book.getName()}" />
			</h1>
			<div class="col-2">
				<a href="/dashboard" class="btn btn-primary">Back to shelves</a>
			</div>
		</div>
		<h4>
			<c:out value="${book.getUser().getName()}" />
			read
			<c:out value="${book.getName()}" />
			by
			<c:out value="${book.getAuthor()}" />
			.
		</h4>

		<h4>
			Here are
			<c:out value="${book.getUser().getName()}" />
			's thoughts:
		</h4>


		<hr>
		<p>
			<c:out value="${book.getThought()}" />
		</p>
		<hr>
		<c:choose>
			<c:when test="${user_session.getId()==book.getUser().getId()}">
				<a href="edit/${book.getId()}" class="btn btn-warning">edit</a>
			</c:when>
		</c:choose>

	</div>

</body>
</html>