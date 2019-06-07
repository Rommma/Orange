<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:url value="/controller" var="controller" />
<c:url value="/excluir" var="excluir" />
<c:url value="/relatorio" var="relatorio" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>| Produtos</title>
		<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
		<link href="css/bootstrap.css" type="text/css" rel="stylesheet"/>
        <link href="css/index.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Merienda|Roboto" rel="stylesheet">
		
		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="js/jquery.mask.min.js"></script>	
		<script type="text/javascript" src="js/validate.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
        
	</head>
	<body id="produtos">
	<script>
		function apagar() {
			$("#nome").val("");
			$("#tipo").val("");
			$("#sabor").val("");
			$("#salario").val("");//valor
			$("#quantidade").val("");
			$("#peso").val("");
			$("#peso").val("");
			$("#tipopeso").val("");
			$("#datav").val("");
			$("#dataf").val("");
			$("#obs").val("");
		}
	</script>
	<c:if test="${not empty peditar}">
		<script>
			$(document).ready(function() {
				$('#exampleModal').modal('show');
			});
		</script>
	</c:if>

	<c:if test="${not empty existe}">
		<script>
			$(document).ready(function() {
				$('#existeModal').modal('show');
			});
		</script>
		<c:set var="peditar" value="${existe}" />
	</c:if>


	<%@ include file="menu.jsp"%>

	<h1>Produtos</h1>
	<br>
	<br>
	<button type="button" class="btn btn-semfundo" data-toggle="modal" data-target="#exampleModal" onclick="apagar()">
        <img src="img/cake-slice.png"> Novo produto
    </button>

	<button type="button" class="btn btn-semfundo" data-toggle="modal" data-target="#saborModal">
        <img src="img/chocolate-bar.png">Novo sabor
    </button>

	<button type="button" class="btn btn-semfundo" data-toggle="modal" data-target="#tipoModal">
        <img src="img/cupcake.png">Novo tipo de produto
    </button>
	
	
	<br>
	<br>
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Nome</th>
				<th scope="col">Tipo</th>
				<th scope="col">Sabor</th>
				<th scope="col">Quantidade</th>
				<th scope="col">Preço</th>
				<th scope="col">Relatório</th>
				<th scope="col">Editar</th>
				<th scope="col">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty produtos}">
					<c:forEach var="produto" items="${produtos}">
						<tr>
							<th>${produto.getNome()}</th>
							<th>${produto.getTipo()}</th>
							<th>${produto.getSabor()}</th>
							<th>${produto.getQuantidade()}</th>
							<th><fmt:formatNumber value="${produto.getValor()}" minFractionDigits="2" type="currency"></fmt:formatNumber></th>
		
							
							<form action="${relatorio}" method="POST" target="blank">
								<input type="hidden" value="produto" name="relatorio" id="relatorio"> 
								<input type="hidden" value="${produto.getId()}" name="relatorioid" id="relatorioid">
								<th><button type="submit" class="btn btn-semfundo">
                                    <img src="img/report.png">
                                </button></th>
							</form>
							
							<form action="${controller}" method="POST">
								<input type="hidden" value="IndexProduto" name="tarefa"
									id="tarefa"> <input type="hidden"
									value="${produto.getId()}" name="editar" id="editar">
								<th><button type="submit" class="btn btn-semfundo">
                                    <img src="img/edit.png">
                                </button></th>      
							</form>
							
							<form action="${controller}" method="POST">
								<input type="hidden" value="IndexProduto" name="tarefa"
									id="tarefa"> <input type="hidden"
									value="${produto.getId()}" name="excluir" id="excluir">
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
							<p>Nenhum produto cadastrado</p>
						</th>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<!-- Modal -->
	<div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered" role="document" style="width:90%">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Cadastro de produto</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="col-12">
					<form action="${controller}" method="POST" id="form">
						<div class="modal-body">
							<input type="hidden" value="IndexProduto" name="tarefa" id="tarefa"> 
							<input type="hidden" name="alterar" id="alterar" <c:if test="${not empty peditar}">value="${peditar.getId()}"</c:if>>

							<div class="form-group">
								<label for="Nome">Nome:</label> <input type="text"
									class="form-control" id="nome" name="nomeProduto"
									<c:if test="${not empty peditar}">value="${peditar.getNome()}"</c:if>>
							</div>
							<div class="row">
								<div class="col">
									<div class="opcoes">
										<label for="tipo">*Tipo de produto:</label> <select
											class="form-control" id="tipo" name="tipo" required>
											<option><c:if test="${not empty peditar}">${peditar.getTipo()}</c:if></option>
											<c:forEach var="tipo" items="${tipos}">
												<option>${tipo}</option>
											</c:forEach>
										</select> 
									</div>
								</div>
								<div class="col">
									<div class="opcoes">
										<label for="tipo">*Sabor:</label> <select class="form-control"
											id="sabor" name="sabor" required>
											<option><c:if test="${not empty peditar}">${peditar.getSabor()}</c:if></option>
											<c:forEach var="sabor" items="${sabores}">
												<option>${sabor}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col-4">
									<label for="Preco">*Preço:</label> 
									<input type="text"class="form-control" id="salario" name="valor" required
									<c:if test="${not empty peditar}">value="${peditar.getValorString()}"</c:if>>
								</div>
								<div class="col-4">
									<div class="row">
										<div class="col">
											<label for="Peso">Peso:</label> 
											<input type="text"class="form-control" id="peso" name="peso"
											<c:if test="${not empty peditar}">value="${peditar.getPeso()}"</c:if>>
										</div>
										<div class="col">
											<label for="Tipopeso"><br></label> 
											<select class="form-control" id="tipopeso" name="tipopeso">
												<option><c:if test="${not empty peditar}">${peditar.getTipopeso()}</c:if></option>
												<option>Kg</option>
												<option>g</option>
												<option>mg</option>
												<option>L</option>
												<option>Uni</option>
											</select>
										</div>
									</div>
								</div>
								<div class="col-4">
									<label for="Quantidade">*Quantidade:</label> 
									<input type="number" class="form-control" id="quantidade" name="quantidade" required
									<c:if test="${not empty peditar}">value="${peditar.getQuantidade()}"</c:if>>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col">
									<label for="DataFabricacao">Data de fabricação:</label> 
									<input type="date" id="datav" class="form-control datav" name="datafabricacao" placeholder="dd/mm/aaaa"
										<c:if test="${not empty peditar}">value="${peditar.getDataFabricacao()}"</c:if>>
								</div>
								<div class="col">
									<label for="DataValidade">*Data de validade:</label> <input
										type="date" id="dataf" class="form-control dataf"
										name="datavalidade" placeholder="dd/mm/aaaa" required
										<c:if test="${not empty peditar}">value="${peditar.getDataValidade()}"</c:if>>
								</div>
							</div>
							<br>
							<div class="row">
								<div class="col">
									<label for="Obs">Observação:</label>
									<textarea class="form-control" id="obs" aria-label="With textarea" name="obs"><c:if
									test="${not empty peditar}">${peditar.getObs()}</c:if></textarea>
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
	<!-- Modal (sabor) -->
	<div class="modal fade" id="saborModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Novo sabor</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="${controller}" method="POST">
					<input type="hidden" value="IndexProduto" name="tarefa" id="tarefa">
					<div class="modal-body">
						<label for="Sabor">Nome:</label> <input type="text"
							name="novosabor" id="sabor" class="form-control">
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-orange">Salvar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal (tipo) -->
	<div class="modal fade" id="tipoModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Novo tipo de
						produto</h5>
                    <button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="${controller}" method="POST">
					<input type="hidden" value="IndexProduto" name="tarefa" id="tarefa">
					<div class="modal-body">
						<label for="Tipo">Nome:</label> <input type="text"name="novotipo" id="tipo" class="form-control">
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-orange">Salvar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal (produto ja existe) -->
	<div class="modal fade" id="existeModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Já existe um
						produto com esse nome!</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Deseja edita-lo?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-orange" data-dismiss="modal">Não</button>
					<button type="button" class="btn btn-orange" data-dismiss="modal" data-toggle="modal" data-target="#exampleModal">Sim</button>
				</div>
			</div>
		</div>
	</div>
	<form action="${relatorio}" method="POST" target="blank">
			<input type="hidden" value="produtos" name="relatorio" id="relatorio"> 
			<th><input type="submit" value="Relatório de produtos" class="btn btn-orange"></th>
	<form>
</body>
</html>
