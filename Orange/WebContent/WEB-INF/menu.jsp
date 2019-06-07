<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#" id="orange">Orange</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<form action="${controller}" method="POST">
					<input type="hidden" value="IndexHome" name="tarefa" id="tarefa">
					<input type="submit" class="nav-link itemmenu" value="Home">
				</form>
			</li>
		<c:if test="${funcionario.getNomeCompleto() eq 'adm'}">
			<li class="nav-item">
				<form action="${controller}" method="POST">
					<input type="hidden" value="IndexProduto" name="tarefa" id="tarefa">
					<input type="submit" class="nav-link itemmenu" value="Produtos">
				</form>
			</li>
			<li class="nav-item">
				<form action="${controller}" method="POST">
					<input type="hidden" value="IndexFuncionario" name="tarefa" id="tarefa"> 
                    <input type="submit" class="nav-link itemmenu" value="Funcionarios">
				</form>
			</li>
		</c:if> 
			<li class="nav-item">
				<form action="${controller}" method="POST">
					<input type="hidden" value="IndexVenda" name="tarefa" id="tarefa">
					<input type="submit" class="nav-link itemmenu" value="Venda">
				</form>
			</li>
		</ul>
		<a href="${sair}" id="btnsair">Sair</a>
	</div>
</nav>