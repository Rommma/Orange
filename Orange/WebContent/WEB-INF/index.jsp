<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:url value="/controller" var="controller"/>

<html>
	<head>
		<title>| Index</title>
		<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
		<link href="css/bootstrap.css" type="text/css" rel="stylesheet"/>
        <link href="css/estilo.css" type="text/css" rel="stylesheet">
        <link href="css/index.css" type="text/css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Merienda|Roboto" rel="stylesheet">
	</head>
	<body id="index">
        <div class="container-fluid">
            <div id="caixa" class="col-4 offset-4 shadow-lg bg-white rounded">
                <h1 id="orange">Orange</h1>
                <form action="${controller}" method="POST">
                    <input type="hidden" value="Login" name="tarefa" id="tarefa"> 
                    <div class="campo">
                        Login: <input type="text" name="nome" id="nome" class="form-control">
                    </div>
                    <div class="campo">
                        Senha: <input type="password" name="senha" id="senha" class="form-control">
                    </div>
                    <input type="submit" value="Logar" class="btn btn-orange" id="btnLogin">
                </form>
            </div>
            <div id="ass" class="col-md-4">Francielly Santos</div>
        </div>
	</body>
</html>