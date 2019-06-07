<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:url value="/controller" var="controller" />
<c:url value="/excluir" var="excluir" />
<c:url value="/relatorio" var="relatorio" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>| Funcionarios</title>
		<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
		<link href="css/bootstrap.css" type="text/css" rel="stylesheet"/>
        <link href="css/index.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Merienda|Roboto" rel="stylesheet">
		
		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="js/jquery.mask.min.js"></script>	
		<script type="text/javascript" src="js/validate.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
        
	</head>
	<body>
	
	<%@ include file="menu.jsp"%>
	<script>
		function apagar() {
			$("#nome").val("");
			$("#cpf").val("");
			$("#datanascimento").val("");
			$("#cep").val("");//valor
			$("#logradouro").val("");
			$("#estado").val("");
			$("#cidade").val("");
			$("#bairro").val("");
			$("#complemento").val("");
			$("#senha").val("");
		}
	</script>

	<c:if test="${not empty feditar}">
		<script>
			$(document).ready(function() {
				$('#exampleModal').modal('show');
			});
		</script>
	</c:if>

	<h1>Funcionário</h1>
	<br>
	<br>
	<div class="col-md-5">

		<button type="button" class="btn btn-semfundo" data-toggle="modal" data-target="#exampleModal" onclick="apagar()">
             <img src="img/add-user.png"> Novo funcionário
        </button>
    

	</div>
	<br>
	<br>

	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Nome</th>
				<th scope="col">CPF</th>
				<th scope="col">Cargo</th>
				<th scope="col">Gerar</th>
				<th scope="col">Editar</th>
				<th scope="col">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty funcionarios}">
					<c:forEach var="funcionario" items="${funcionarios}">
						<tr>
							<th>${funcionario.getNomeCompleto()}</th>
							<th>${funcionario.getCpf()}</th>
							<th>${funcionario.getCargo()}</th>
							
							<form action="${relatorio}" method="POST" target="blank">
								<input type="hidden" value="funcionario" name="relatorio" id="relatorio"> 
								<input type="hidden" value="${funcionario.getId()}" name="relatorioid" id="relatorioid">
								<th><button type="submit" class="btn btn-semfundo">
                                    <img src="img/report.png">
                                </button></th>
							</form>
		
							<form action="${controller}" method="POST">
								<input type="hidden" value="IndexFuncionario" name="tarefa"
									id="tarefa"> <input type="hidden"
									value="${funcionario.getId()}" name="editar" id="editar">
								<th><button type="submit" class="btn btn-semfundo">
                                    <img src="img/edit.png">
                                </button></th> 
							</form>
							
							<form action="${controller}" method="POST">
								<input type="hidden" value="IndexFuncionario" name="tarefa"
									id="tarefa"> <input type="hidden"
									value="${funcionario.getId()}" name="excluir" id="excluir">
								<th><button type="submit" class="btn btn-semfundo">
                                    <img src="img/delete.png">
                                </button></th>
							</form>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<th>
							<p>Nenhum funcionário cadastrado</p>
						</th>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<div class="modal fade bd-example-modal-lg" id="exampleModal"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Cadastro de
						funcionário</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="col-12">
					<form action="${controller}" method="POST" id="form">
						<input type="hidden" value="IndexFuncionario" name="tarefa"
							id="tarefa"> <input type="hidden" name="alterar"
							id="alterar"
							<c:if test="${not empty feditar}">value="${feditar.getId()}"</c:if>>
						<div class="modal-body">

							<div class="form-group">
								<label for="Nome">*Nome:</label> 
								<input type="text" class="form-control" id="nome" name="nomeFuncionario" required
									<c:if test="${not empty feditar}">value="${feditar.getNomeCompleto()}"</c:if>>
							</div>
							<div class="row">
								<div class="col">
									<label for="CPF">*CPF:</label> <input type="text"
										class="form-control" id="cpf" name="cpf" required
										<c:if test="${not empty feditar}">value="${feditar.getCpf()}"</c:if>>
								</div>
								<div class="col">
									<label for="DataNascimento">Data de nascimento:</label> <input
										type="date" class="form-control" id="datanascimento"
										name="datanascimento" required
										<c:if test="${not empty feditar}">value="${feditar.getDataNascimento()}"</c:if>>
								</div>
							</div>
							<div class="row">
								<div class="col"></div>
							</div>
							<div class="row">
								<div class="col">
									<label for="Cep">*CEP:</label> <input type="text"
										class="form-control" id="cep" name="cep" required
										<c:if test="${not empty feditar}">value="${feditar.getCep()}"</c:if>>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<label for="Logradouro">*Logradouro:</label> <input type="text"
										class="form-control" id="logradouro" name="logradouro"
										required
										<c:if test="${not empty feditar}">value="${feditar.getLogradouro()}"</c:if>>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<label for="Estado">*Estado:</label> <input type="text"
										class="form-control" id="estado" name="estado" required
										<c:if test="${not empty feditar}">value="${feditar.getEstado()}"</c:if>>
								</div>
								<div class="col">
									<label for="Cidade">*Cidade:</label> <input type="text"
										class="form-control" id="cidade" name="cidade" required
										<c:if test="${not empty feditar}">value="${feditar.getCidade()}"</c:if>>
								</div>
							</div>
							<div class="row">
								<div class="col-8">
									<label for="Bairro">*Bairro:</label> <input type="text"
										class="form-control" id="bairro" name="bairro" required
										<c:if test="${not empty feditar}">value="${feditar.getBairro()}"</c:if>>
								</div>
								<div class="col">
									<label for="Complemento">Complemento:</label> <input
										type="text" class="form-control" id="complemento"
										name="complemento"
										<c:if test="${not empty feditar}">value="${feditar.getComplemento()}"</c:if>>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<label for="Senha">*Senha:</label> <input type="text"
										class="form-control" id="senha" name="senha" required
										<c:if test="${not empty feditar}">value="${feditar.getSenha()}"</c:if>>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<p>* campo obrigatório</p>
							<button type="submit" class="btn btn-orange">Salvar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="visuModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">${fvisualizar.getNome()}</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<img src="img/funcionario.jpg">
					<p>
					<h2>Nome:</h2>${fvisualizar.getNome()}</p>
					<br>
					<p>
					<h2>Cargo:</h2>${fvisualizar.getCargo()}</p>
				</div>
			</div>
		</div>
	</div>
	<form action="${relatorio}" method="GET" target="blank">
			<input type="hidden" value="funcionarios" name="relatorio" id="relatorio"> 
			<th><input type="submit" value="Relatório de funcionários" class="btn btn-orange" ></th>
		<form>
</body>
</html>