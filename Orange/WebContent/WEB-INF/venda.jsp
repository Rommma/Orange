<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:url value="/controller" var="controller" />
<c:url value="/sair" var="sair" />

<c:set var="valor" value="1000000"></c:set>
<fmt:setLocale value="pt-BR"></fmt:setLocale>

<html>
<head>
<title>| Venda</title>
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
		<c:if test="${ativo eq '0'}">
			<script>
			window.onload = function(){
				window.open('page.html','page','toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=100,height=100');
			}
			</script>
		</c:if>
		<c:if test="${not empty totalpagamento}">
		<script>
			$(document).ready(function() {
				$('#pagamentoModal').modal('show');
			});
			var totalpagamento = ${totalpagamento};
			var faltapagar = ${totalpagamento};
			
			setInterval(function(){
				var totalpago = $('#totalpago').val() * 1;
				var faltapagar = $('#faltapagar').val() * 1;
				var troco = $('#troco').val() * 1;
				
				var dinheiro = ($('#dinheiro').val().replace(",", ".")) * 1;
				var debito = ($('#debito').val().replace(",", ".")) * 1;
				var credito = ($('#credito').val().replace(",", ".")) * 1;
				var vr = ($('#vr').val().replace(",", ".")) * 1;
				var va = ($('#va').val().replace(",", ".")) * 1;
				
				var pagamento = dinheiro + debito + credito + vr + va;
				
				if(pagamento == totalpagamento){
					$('#totalpago').val(totalpagamento.toFixed(2));
					$('#faltapagar').val(0);
					$('#troco').val(0);
					
					$('#btnpagamento').disabled = false;
				}else if(pagamento > totalpagamento){
					var sobra = pagamento - totalpagamento;
					$('#totalpago').val(pagamento.toFixed(2));
					$('#faltapagar').val(0);
					$('#troco').val(sobra.toFixed(2));
					
					$('#btnpagamento').disabled = false;
				}else{
					var sobra = totalpagamento - pagamento;
					$('#totalpago').val(pagamento.toFixed(2));
					$('#faltapagar').val(sobra.toFixed(2));
					$('#troco').val(0);
					
					$('#btnpagamento').disabled = true;
				}
			}, 1000);
		</script>
	</c:if>
		
	
	<script>
		oninput="this.setCustomValidity('')";
		var atual = 0;
		var da = ${desconto};
		var vetor = new Array();
		var valordesconto;
		
		function add(id){
			var i;
			var achou = false;
			for(i=0; i<vetor; i++){
				if(vetor[i] == id){
					achou = true;
				}
			}
			if(achou == false){
				vetor.push(id);	
			}
		}
		setInterval(function(){
		//	if($("#listaModal").is(":visible") == true){
				for(i=0; i<vetor.length; i++){
					var quant = $('#quantidade'+vetor[i]).val();
					$('.quant'+vetor[i]).val(quant);
				}
				$('.valordesconto').val(da);
				$('#totalpagamento').val(totalpagamento);
		//	}
			
		}, 1000);
		function atualiza(id){
			
			var va = $('#subtotal'+id).val();
			var q = $('#quantidade'+id).val();
			var p = $('#valor2'+id).val();
			
			//$('#quant'+id).val(q);
			add(id);
			
			var subtotal = q * p;
			$('#subtotal'+id).val(subtotal.toFixed(2));
			
			var total = $('#total').val()-va;
			total = total+subtotal;
			$('.total').val(total.toFixed(2));
			atual = total;
			
		}
		function desconto(){
			
			var total = $('#total').val() * 1;
			
			var des1 = $('#salario').val().replace(",", ".");
			var des = des1 * 1;
			if(des > atual){
				alert("Desconto maior que o valor total");
				$("#salario").val(da);
			}else{
				var r = atual - des;
				$('.total').val(r.toFixed(2));
				
				des1 = des1.replace(".", ",");
				da = des1;
				$('.valordesconto').val(da);
			}
		}
		function cliente(nome){
			$('#clientenome').val(nome);
		}
	</script>
	<style>
				.table-wrapper-2 {
				    display: block;
				    max-height: 400px;
				    overflow-y: auto;
				    -ms-overflow-style: -ms-autohiding-scrollbar;
				}
				.table-wrapper-3 {
				    display: block;
				    max-height: 500px;
				    overflow-y: auto;
				    -ms-overflow-style: -ms-autohiding-scrollbar;
				}
				.campo{
					width:100px;
				}
				.modal.show .modal-dialog{
					max-width: 90% !important;
				}
	</style>
	<h1>Venda</h1>
	<div class="content-fluid">
		<div class="row">
			<div class="col-2 offset-9">
				<button type="button" class="btn btn-semfundo" data-toggle="modal" data-target="#listaModal">
                    <img src="img/cupcake.png"> Adicionar produto
                </button>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-3">
				<div class="col">
					<label for="Atendente">Atendente:</label> <input type="text"
						class="form-control" id="Atendente" name="Atendente"
						value="${funcionario.getNome()}" disabled>
				</div>
				<br>
				<div class="col">
					<label for="Atendente">Cliente:</label> 
					<input type="text" class="form-control" id="cliente" name="cliente" onblur="cliente(this.value)">
				</div>
				<br>
				<div class="col">
					<label for="Desconto">Desconto:</label> 
					<input type="text" name="desconto" value="${desconto}" id="salario" class="form-control" onblur="desconto(this.value)" >
				</div>
				<div class="col">
					<label for="Subtotal">Subtotal:</label> 
					<input type="text" name="subtotal" class="total form-control" id="total" disabled>
				</div>
				<br>
			</div>
			<div class="col">
				<div class="table-wrapper-2">
					<table class="table table-fixed" id="tabelacompras">
						<thead class="thead-dark">
							<tr>
								<th scope="col">Nome</th>
								<th scope="col">Preço unitário</th>
								<th scope="col">Quantidade</th>
								<th scope="col">Subtotal</th>
								<th scope="col">Excluir</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty compras}">
									<c:forEach var="compra" items="${compras}">
										<tr>
											<form action="${controller}" method="POST" id="formcompra${compra.getId()}">
												<input type="hidden" value="IndexVenda" name="tarefa" id="tarefa">
												<input type="hidden" value="${compra.getId()}" name="qproduto" id="qproduto">
												
												<th scope="row">${compra.getNome()}</th>
												<input type="hidden" value="${compra.getValor()}" name="valor2" id="valor2${compra.getId()}">
												<th scope="row" id="valor${compra.getId()}">
													<fmt:formatNumber value="${compra.getValor()}" minFractionDigits="2" type="currency"></fmt:formatNumber>
												</th>	
												<th scope="row"><input type="number" id="quantidade${compra.getId()}" name="quantidade${compra.getId()}" class="campo quantidade form-control" onblur="atualiza(${compra.getId()})" onload="add(${compra.getId()})" min="1" max="${compra.getQuantidade()}" 
												oninvalid="this.setCustomValidity('Estoque possui ${compra.getQuantidade()} produto(s)')" oninput="this.setCustomValidity('')"
												value="${compra.getQuantidadecompra()}">
												</th>
												<th scope="row"><input type="number" id="subtotal${compra.getId()}" name="subtotal" class="campo form-control" disabled></th>
											</form>
											<form action="${controller}" method="POST">
												<input type="hidden" name="valordesconto" id="valordesconto" class="valordesconto">
												<input type="hidden" value="IndexVenda" name="tarefa" id="tarefa"> 
												<input type="hidden" value="${compra.getId()}" name="excluir" id="excluir">
												<th><button type="submit" class="btn btn-semfundo naoredonda">
                                             <img src="img/bagDelete.png">
                                        </button></th>
											</form>
										</tr>
										<script>
											var idcompra = ${compra.getId()};
											atualiza(idcompra);
										</script>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<th>
											<p>Nenhum produto selecionado</p>
										</th>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-4 offset-8">
						<form action="${controller}" method="POST">
							<input type="hidden" value="IndexVenda" name="tarefa" id="tarefa"> 
							
							<input type="hidden" name="total" class="total">
							<input type="hidden" name="cliente" class="cliente" id="clientenome">
							<input type="hidden" name="valordesconto" id="valordesconto" class="valordesconto">
							<c:forEach var="compra" items="${compras}">
								<input type="hidden" name="quant${compra.getId()}" id="quant${compra.getId()}" class="quant${compra.getId()}">
							</c:forEach>
							
							<input type="hidden" value="finalizar" name="finalizar" id="finalizar"> 
							
							<input type="submit" class="btn btn-orange" value="Finalizar compra">
						</form>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<!-- Modal (produtos)-->
	<div class="modal fade bd-example-modal-lg" id="listaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered" role="document" style="width:90%">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Lista de produtos</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="col table-wrapper-3">
						<table class="table">
							<thead class="thead-dark">
								<tr>
									<th scope="col">Nome</th>
									<th scope="col">Tipo</th>
									<th scope="col">Sabor</th>
									<th scope="col">Quantidade</th>
									<th scope="col">Preço</th>
									<th scope="col">Adicionar</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="produto" items="${produtos}">
									<tr>
										<th>${produto.getNome()}</th>
										<th>${produto.getTipo()}</th>
										<th>${produto.getSabor()}</th>
										<th>${produto.getQuantidade()}</th>
										<th><fmt:formatNumber value="${produto.getValor()}" minFractionDigits="2" type="currency"></fmt:formatNumber></th>
										<form action="${controller}" method="POST">
										
										<input type="hidden" name="valordesconto" id="valordesconto" class="valordesconto">
										<c:forEach var="compra" items="${compras}">
											<input type="hidden" name="quant${compra.getId()}" id="quant${compra.getId()}" class="quant${compra.getId()}">
										</c:forEach>
											
											<input type="hidden" value="IndexVenda" name="tarefa" id="tarefa"> 
											<input type="hidden" value="${produto.getId()}" name="add" id="add">
											<th><button type="submit" class="btn btn-semfundo naoredonda">
                                             <img src="img/bag.png">
                                        </button></th>
										</form>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal (pagamento)-->
	<div class="modal fade bd-example-modal-lg" id="pagamentoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pagamento</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="${controller}" method="POST">
					<input type="hidden" value="IndexVenda" name="tarefa" id="tarefa">
					<input type="hidden" value="pagamento" name="pagamento" id="pagamento">
					<input type="hidden" value="${vendaobj.getId()}" name="vendaobj" id="vendaobj">
					
					<div class="col">
						<label>Cliente:${clientepagamento}</label>
					</div>
					<div class="row">
						<div class="col">
							Total a pagar:
							<input type="text" name="totalpagar" id="totalpagar" class="form-control" disabled value="${totalpagamento}">
						</div>
						<div class="col">
							Total pago:
							<input type="text" name="totalpago" id="totalpago"  class="form-control" disabled>
						</div>
						<div class="col">
							Falta:
							<input type="text" name="faltapagar" id="faltapagar" value="${totalpagamento}" class="valor form-control" disabled>
						</div>
						<div class="col">
							Troco:
							<input type="text" name="troco" id="troco" class="valor form-control" disabled>
						</div>
					</div>
					<div class="row">
						<div class="col" id="pagamento">
                            <div class="col">
                                <label>Dinheiro:</label><input type="text" id="dinheiro" name="dinheiro" class="form-control">
                            </div>
                            <div class="col">
							     <label>Débito:</label><input type="text" id="debito" name="debito" class="form-control">
                            </div>
                            <div class="col">
				                <label>Crédito:</label><input type="text" id="credito" name="credito" class="form-control">
                            </div>
                            <div class="col">
							     <label>VR:</label><input type="text" id="vr" name="vr" class="form-control">
							</div>
                            <div class="col">
							     <label>VA:</label><input type="text" id="va" name="va" class="form-control">
							</div>
						</div>
					</div>
					<input type="submit" id="btnpagamento" class="btn btn-orange" value="Finalizar compra"> 
				</div>
			</div>
		</div>
	</div>
	<script>
		desconto();
	</script>
</body>
</html>