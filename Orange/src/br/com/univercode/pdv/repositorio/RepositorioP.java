package br.com.univercode.pdv.repositorio;

import java.util.ArrayList;

import br.com.univercode.pdv.dao.ProdutoDAO;
import br.com.univercode.pdv.model.Funcionario;
import br.com.univercode.pdv.model.Produto;

public class RepositorioP {
	private static RepositorioP r;
	private RepositorioP(){
		//Adicionar as listas
	};
	public static RepositorioP getInstance() {
		if(r == null){
			r = new RepositorioP();
		}
		return r;
	}
	ArrayList<Produto> compras = new ArrayList<Produto>();
	ArrayList<Produto> produtos = new ArrayList<Produto>();
	ProdutoDAO pdao = new ProdutoDAO();
	
	public void add(Produto p) {
		pdao.salvar(p);
		produtos.add(p);
	}
	public void addCompra(String id) {
		Produto p = pdao.listarPeloId(Integer.parseInt(id));
		p.setId(Integer.parseInt(id));
		compras.add(p);
	}
	public ArrayList<Produto> getCompras() {
		return compras;
	}
	public void deleteCompra(String id) {
		int idd = Integer.parseInt(id);
		for (int i = 0; i < compras.size(); i++) {
			if (compras.get(i).getId() == idd) {
			      compras.remove(i);
			}
		}
	}
	public void deleteCompras() {
		this.compras = new ArrayList<Produto>();
	}
	public void delete(int id) {
		for (Produto pl : produtos) {
			if(id == pl.getId()) {
				produtos.remove(pl);
			}
		}
		pdao.delete(id);
	}
	public void alterar(Produto p) {
		pdao.alterar(p);
	}
	public boolean existe(String nome, String tipo) {
		produtos = pdao.listarTudo();
		if(produtos != null) {
			for (Produto p : produtos) {
				if(p.getNome().equalsIgnoreCase(nome) && tipo.equalsIgnoreCase(tipo)) {
					return true;
				}
			}
		}
		return false;
	}
	public Produto achar(String nome, String tipo) {
		produtos = pdao.listarTudo();
		if(produtos != null) {
			for (Produto p : produtos) {
				if(p.getNome().equalsIgnoreCase(nome) && tipo.equalsIgnoreCase(tipo)) {
					return p;
				}
			}
		}
		return null;
	}
}
