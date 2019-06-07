package br.com.univercode.pdv.web;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.univercode.pdv.dao.ProdutoDAO;
import br.com.univercode.pdv.dao.SaborDAO;
import br.com.univercode.pdv.dao.TipoDAO;
import br.com.univercode.pdv.model.ITarefa;
import br.com.univercode.pdv.model.Produto;
import br.com.univercode.pdv.repositorio.RepositorioP;

public class IndexProduto implements ITarefa{
	@Override
	public String executar(HttpServletRequest req, HttpServletResponse resp) {
		SaborDAO sdao = new SaborDAO();
		TipoDAO tdao = new TipoDAO();
		ProdutoDAO pdao = new ProdutoDAO(); 
		
		//Cadastrando um sabor
		String novosabor = req.getParameter("novosabor");
		if(novosabor != "" && novosabor!=null) {
			sdao.salvar(novosabor);
			novosabor = null;
		}
		//Cadstrando um tipo
		String novotipo = req.getParameter("novotipo");
		if(novotipo != "" && novotipo!=null) {
			tdao.salvar(novotipo);
			novotipo = null;
		}
		
		Produto p;
		int id = 0;
		
		//Excluir produto
		String excluir= req.getParameter("excluir");
		if(excluir != "" && excluir!=null) {
			RepositorioP.getInstance().delete(Integer.parseInt(excluir));
			excluir = null;
		}
		
		ArrayList<String> sabores = sdao.listarTudo();
		ArrayList<String> tipos = tdao.listarTudo();
		
		//Editar produto
		String editar = req.getParameter("editar");
		
		if(editar != "" && editar!=null) {
			id = Integer.parseInt(editar);
			
			Produto peditar = pdao.listarPeloId(id);
			req.setAttribute("peditar", peditar);
			
			if(sabores.contains(peditar.getSabor())) {
				sabores.remove(peditar.getSabor());
			}
			if(tipos.contains(peditar.getTipo())) {
				tipos.remove(peditar.getTipo());
			}
		}
		
		
		//Cadastrar produto
		String tipo = req.getParameter("tipo");
		
		if(tipo != null) {
			//campos obrigatorios
			String sabor = req.getParameter("sabor");
			Double valor = Double.parseDouble(req.getParameter("valor").replaceAll("R", "").replaceAll("$", "").replaceAll(" ", "").replace("," ,"."));
			System.out.println(valor);
			int quantidade = Integer.parseInt(req.getParameter("quantidade"));
			String dv = req.getParameter("datavalidade");
			
			//campos não obrigatorios
			String nome;
			Double peso;
			String tipopeso;
			String df;
			String obs = null;
			
			if(req.getParameter("nome") == null || req.getParameter("nome") == "") {
				if(sabor.equalsIgnoreCase("outro")) {
					nome = tipo;
				}else if(tipo.contains("bolo")) {
					nome = "bolo de "+sabor;
				}else {
					nome = tipo+" de "+sabor;
				}
			}else {
				nome = req.getParameter("nome");
			}
			if(req.getParameter("peso") == null || req.getParameter("peso") == "") {
				peso = 0.0;
				tipopeso = "";
			}else {
				peso = Double.parseDouble(req.getParameter("peso"));
				tipopeso = req.getParameter("tipopeso");
			}
			if(req.getParameter("datafabricacao") == null || req.getParameter("datafabricacao") == "") {
				df = "--";
			}else {
				df = req.getParameter("datafabricacao");
			}
			if(req.getParameter("obs") == null || req.getParameter("obs") == "") {
				obs = "--";
			}else {
				obs = req.getParameter("obs");
			}
			
				p = new Produto(nome, tipo, sabor, obs, valor, peso,tipopeso, quantidade, dv, df, "");
				
				
				//Alterar produto
				String alterar = req.getParameter("alterar");
				
				if(alterar !=null && alterar!="") {
					p.setId(Integer.parseInt(alterar));
					RepositorioP.getInstance().alterar(p);
					alterar = null;
				}else {
					//Verificar se existe o produto
					if(RepositorioP.getInstance().existe(nome, tipo)) {
						Produto existe = RepositorioP.getInstance().achar(nome, tipo);
						req.setAttribute("existe", existe);
					}else {
						RepositorioP.getInstance().add(p);
					}
				}
				tipo = null;
			}
		
		ArrayList<Produto> produtos = pdao.listarTudo();

		req.setAttribute("produtos", produtos);
		
		req.setAttribute("sabores", sabores);
		req.setAttribute("tipos", tipos);
		
		req.setAttribute("tarefa", "produto");
		
		return "/WEB-INF/produtos.jsp";
	}

}
