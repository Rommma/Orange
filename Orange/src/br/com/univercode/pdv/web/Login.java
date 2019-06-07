package br.com.univercode.pdv.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.univercode.pdv.dao.FuncionarioDAO;
import br.com.univercode.pdv.model.Caixa;
import br.com.univercode.pdv.model.Funcionario;
import br.com.univercode.pdv.model.ITarefa;
import br.com.univercode.pdv.repositorio.RepositorioF;

public class Login implements ITarefa{

	@Override
	public String executar(HttpServletRequest req, HttpServletResponse resp) {
		
		FuncionarioDAO fdao = new FuncionarioDAO();
		String nome = req.getParameter("nome");
		String senha = req.getParameter("senha");
		
		if(RepositorioF.getInstance().existe(nome, senha)) {
			//System.out.println("login");
			Funcionario f = fdao.achar(senha, nome);
			
			HttpSession sessao = req.getSession();
			sessao.setAttribute("funcionario", f);
			
			System.out.println(f.getNome());
			
			req.setAttribute("tarefa", "Login");
			
			return "/WEB-INF/home.jsp";
		}else {
			//System.out.println("index");
			req.setAttribute("tarefa", "Index");
			return "/WEB-INF/index.jsp";
		}		
	}
}
