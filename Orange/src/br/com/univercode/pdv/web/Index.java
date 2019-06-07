package br.com.univercode.pdv.web;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.univercode.pdv.model.ITarefa;

public class Index implements ITarefa{
	@Override
	public String executar(HttpServletRequest req, HttpServletResponse resp) {
		
		//Produto p = new Produto("bolo de chocolate cremoso","inteiro", "chocolate",null, 45.50,10.0, 10,"12/12/12", "12/12/12", null);
		
		//Funcionario f = new Funcionario("adm", "4874587", "456747","123", "SP","SP", "Guaianases", "rua planicie dos goitacazes", "15", "", "");
//		RepositorioF.getInstance().add(f);
		
		Date d = new Date();
		
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		
		req.setAttribute("dia", dStr);
		
		HttpSession sessao = req.getSession();
		if(sessao.getAttribute("funcionario") == null) {
			req.setAttribute("tarefa", "index");
			return "/WEB-INF/index.jsp";
		}else {
			return "/WEB-INF/home.jsp";
		}
	}
}