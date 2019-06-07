<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmtS" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:url value="/controller" var="controller"/>
<c:url value="/sair" var="sair"/>
<c:url value="/relatorio" var="relatorio"/>


<html>
	<head>
		<title>| Home</title>
		<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
        <link href="css/estilo.css" type="text/css" rel="stylesheet" />
		<link href="css/bootstrap.css" type="text/css" rel="stylesheet"/>
        <link href="css/index.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Merienda|Roboto" rel="stylesheet">
		
		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="js/jquery.mask.min.js"></script>	
		<script type="text/javascript" src="js/validate.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
        
	</head>
	<body id="fundohome">
		 <%@ include file="menu.jsp"%>
        <div class="container-fluid">
        <div id="homeinfo">
            <h2>Bem vindo(a) ${funcionario.getNome()} </h2>
        </div>
        <c:choose>
		 	<c:when test="${aberto eq 'false'}">
		 		<script>
			 		$(document).ready(function() {
						$('#abrirCaixaModal').modal('show');
					});
		 		</script>
		    	<button type="button" class="btn btn-orange" data-toggle="modal" data-target="#abrirCaixaModal">
					Abrir caixa
				</button>
		 	</c:when>
		 	<c:otherwise>
		 		<button type="button" class="btn btn-orange" data-toggle="modal" data-target="#fecharCaixaModal">
					Fechar caixa
				</button>
				<script>
					$(document).ready(function() {
						$('#abrirCaixaModal').modal('hide');
					});
				</script>
		 	</c:otherwise>
		</c:choose>
			<!-- Modal (abrir caixa) -->
			<div class="modal fade" id="abrirCaixaModal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered" role="document">
			    	<div class="modal-content">
			      		<div class="modal-header">
			        		<h5 class="modal-title" id="exampleModalLabel">Abrir caixa</h5>
			      		</div>
			      		<form action="${controller}" method="POST">
			      		<input type="hidden" value="IndexHome" name="tarefa" id="tarefa"> 
				      		<div class="modal-body">
				        		<label for="Valor"><p>Valor inicial do caixa:</p></label>
								<input type="text" name="valorinicial" id="salario" class="form-control" required>
				     		</div>
				      		<div class="modal-footer">
				        		<button type="submit" class="btn btn-orange">Abrir caixa</button>
				      		</div>
			      		</form>
			    	</div>
			  	</div>
			</div>
			<!-- Modal (fechar caixa) -->
			<div class="modal fade bd-example-modal-lg" id="fecharCaixaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog  modal-lg modal-dialog-centered" role="document">
			    	<div class="modal-content">
			      		<div class="modal-header">
			        		<h5 class="modal-title" id="exampleModalLabel">Fechar caixa</h5>
			        		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          			<span aria-hidden="true">&times;</span>
			        		</button>
			      		</div>
			      		<form action="${controller}" method="POST">
			      		<input type="hidden" value="IndexHome" name="tarefa" id="tarefa"> 
			      		<input type="hidden" value="fechar" name="fechar" id="fechar"> 
				      		<div class="modal-body">
				        		<div class="row">
				        			<div id="fecharcaixadiv" class="col">
                                        <br>
                                        <p><b>Saldo inicial: </b>${saldoabertura}</p>
                                        <br>
                                        <p><b>Data/Hora de abertura: </b>Aberto às ${horaabertura} de ${dataabertura}</p>
                                        <br>
                                        <p><b>Por: </b>${fabriu.getNomeCompleto()}</p>
				        				<br><br>
				        				<p><b>Saldo(apenas dinheiro): </b>${dinheiro}</p>
				        				<br>
				        				<p><b>Saldo(cartões): </b>${cartoes}</p>
				        				<br>
				        				<p><b>Saldo Final(cartões + dinheiro): </b>${cartoes + dinheiro}</p>
				        			</div>
				        		</div>
				     		</div>
				      		<div class="modal-footer">
				        		<button type="submit" class="btn btn-orange">Fechar caixa</button>
				      		</div>
			      		</form>
			    	</div>
			  	</div>
			</div>
        </div>
	</body>
</html>