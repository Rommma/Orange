package br.com.univercode.pdv.web;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.univercode.pdv.dao.FuncionarioDAO;
import br.com.univercode.pdv.dao.ProdutoDAO;
import br.com.univercode.pdv.dao.SaborDAO;
import br.com.univercode.pdv.dao.TipoDAO;
import br.com.univercode.pdv.model.Funcionario;
import br.com.univercode.pdv.model.ITarefa;
import br.com.univercode.pdv.model.Produto;
import br.com.univercode.pdv.repositorio.RepositorioF;
import br.com.univercode.pdv.repositorio.RepositorioP;

public class IndexFuncionario implements ITarefa{

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse resp) {
		
		FuncionarioDAO fdao = new FuncionarioDAO(); 
		Funcionario f;
		int id;
		
		//Excluir funcionario
		String excluir= req.getParameter("excluir");
		if(excluir != "" && excluir!=null) {
			RepositorioF.getInstance().delete(Integer.parseInt(excluir));
			//excluir = null;
		}
		
		
		/*String visualizar = req.getParameter("visualizar");
		
		if(visualizar !=null && visualizar!="") {
			id = (Integer.parseInt(visualizar));
			Funcionario fvisualizar = fdao.listarPeloId(id);
			req.setAttribute("visualizar", fvisualizar);
		}*/
		
		//Editar funcionario
		String editar = req.getParameter("editar");
		if(editar != "" && editar!=null) {
			
			id = Integer.parseInt(editar);
			
			Funcionario feditar = fdao.listarPeloId(id);
			req.setAttribute("feditar", feditar);

		}
		
		String nome = req.getParameter("nomeFuncionario");
		
		if(nome != null && nome!="") {
			
			String cep = req.getParameter("cep");
			String cpf = req.getParameter("cpf");
			String senha = req.getParameter("senha");
			String estado = req.getParameter("estado");
			String cidade = req.getParameter("cidade");
			String bairro = req.getParameter("bairro");
			String logradouro = req.getParameter("logradouro");
			String complemento;
			String datanascimento;
			
			if(req.getParameter("complemento") == null || req.getParameter("complemento") == "") {
				complemento = "--";
			}else {
				complemento = req.getParameter("complemento");
			}
			if(req.getParameter("datanascimento") == null || req.getParameter("datanascimento") == "") {
				datanascimento = "--";
			}else {
				datanascimento = req.getParameter("datanascimento");
			}
			
			f = new Funcionario(nome, cep, cpf, senha, estado, cidade, bairro, logradouro, complemento, datanascimento, "");
			
			String alterar = req.getParameter("alterar");
			
			if(alterar !=null && alterar!="") {
				f.setId(Integer.parseInt(alterar));
				RepositorioF.getInstance().alterar(f);
				alterar = null;
			}else {
				RepositorioF.getInstance().add(f);
			}
			nome = null;
		}
		
		ArrayList<Funcionario> funcionarios = fdao.listarTudo();
		
		req.setAttribute("funcionarios", funcionarios);
	
		
		req.setAttribute("tarefa", "funcionario");
		
		return "/WEB-INF/funcionarios.jsp";
	}

}
/*
<select class="form-control" id="estado" name="estado" required>
<option><c:if test="${not empty feditar}">"${feditar.getEstado()}"</c:if></option>
<option>Acre</option>
<option>Alagoas</option>
<option>Amapá</option>
<option>Amazonas</option>
<option>Bahia</option>
<option>Ceará</option>
<option>Distrito Federal</option>
<option>Espírito Santo</option>
<option>Goiás</option>
<option>Maranhão</option>
<option>Mato Grosso</option>
<option>Mato Grosso do Sul</option>
<option>Minas Gerais</option>
<option>Pará</option>
<option>Paraíba</option>
<option>Paraná</option>
<option>Pernambuco</option>
<option>Piauí</option>
<option>Rio de Janeiro</option>
<option>Rio Grande do Norte</option>
<option>Rio Grande do Sul</option>
<option>Rondônia</option>
<option>Roraima</option>
<option>Santa Catarina</option>
<option>São Paulo</option>
<option>Sergipe</option>
</select>
*/