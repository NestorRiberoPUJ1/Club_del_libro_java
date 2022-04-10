<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h1 class="col-10">
				Bienvenido
				<c:out value="${user_session.getName()}" />
			</h1>
			<div class="col-2">
				<a href="/logout" class="btn btn-danger">Cerrar Sesión</a>
			</div>
		</div>

		<div class="row">
			<h4 class="col-10">Books from everyone´s shelves:</h4>
			<div class="col-2">
				<a href="/books/new" class="btn btn-warning">+ Add a book to my
					shelf</a>
			</div>
		</div>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author name</th>
					<th>Posted By</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}">
					<tr>
						<td><c:out value="${book.getId()}" /></td>
						<td><a href="/books/${book.getId()}"><c:out
									value="${book.getName()}" /></a></td>
						<td><c:out value="${book.getAuthor()}" /></td>
						<td><c:out value="${book.getUser().getName()}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>