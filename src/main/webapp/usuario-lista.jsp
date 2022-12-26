<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt_br">

<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Aplicativo 
				de Controle de Usuário </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/lista"
					class="nav-link">Usuários</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<div class="alert alert-success" *ngIf='message'>{{message}}</div> 

		<div class="container">
			<h3 class="text-center">Lista de Usuários</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/novo" class="btn btn-success">Adiconar
					Novo Usario</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Email</th>
						<th>Pais</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="usuario" items="${listaUsuarios}">

						<tr>
							<td><c:out value="${usuario.id}" /></td>
							<td><c:out value="${usuario.nome}" /></td>
							<td><c:out value="${usuario.email}" /></td>
							<td><c:out value="${usuario.pais}" /></td>
							<td><a href="edit?id=<c:out value='${usuario.id}' />">Editar</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${usuario.id}' />">Deletar</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>

</html>