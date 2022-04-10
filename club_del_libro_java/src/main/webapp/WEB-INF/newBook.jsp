<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<div class=container>

		<div class="row">
			<h1 class="col-10">Add a book to your shelf!</h1>
			<div class="col-2">
				<a href="/dashboard" class="btn btn-primary">Back to shelves</a>
			</div>
		</div>

		<form:form action="/books/create" method="POST"
			modelAttribute="newBook">

			<div class="form-group">
				<form:label path="name">Title:</form:label>
				<form:input path="name" class="form-control" />
				<form:errors path="name" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="author">Author:</form:label>
				<form:input path="author" class="form-control" />
				<form:errors path="author" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="thought">My thoughts:</form:label>
				<form:textarea path="thought" class="form-control" />
				<form:errors path="thought" class="text-danger" />
			</div>
			<form:input path="user" type="hidden" value="${user_session.getId()}" />
			<br>
			<button type="submit" class="btn btn-success">Submit</button>

		</form:form>

	</div>

</body>
</html>