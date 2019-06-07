package br.com.univercode.pdv.repositorio;

import java.util.ArrayList;

import br.com.univercode.pdv.dao.FuncionarioDAO;
import br.com.univercode.pdv.model.Funcionario;
import br.com.univercode.pdv.model.Produto;

public class RepositorioF {
	
	static ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
	FuncionarioDAO fdao = new FuncionarioDAO();
	
	private static RepositorioF r;
	private RepositorioF(){
		this.funcionarios = this.fdao.listarTudo();
	};
	public static RepositorioF getInstance() {
		if(r == null){
			r = new RepositorioF();
		}
		return r;
	}
	
	public void add(Funcionario f) {
		f.setAcesso(0);
		fdao.salvar(f);
		funcionarios.add(f);
	}
	
	public void delete(int id) {
		funcionarios = fdao.listarTudo();
		/*for (Funcionario f : funcionarios) {
			if(id == f.getId()) {
				funcionarios.remove(f);
			}
		}*/
		fdao.delete(id);
	}
	public boolean existe(String nome, String senha) {
		funcionarios = fdao.listarTudo();
		if(funcionarios != null) {
			for (Funcionario f : funcionarios) {
				if(f.getNome().equals(nome) && f.getSenha().equals(senha)) {
					return true;
				}
			}
		}
		return false;
	}
	public void alterar(Funcionario f) {
		fdao.alterar(f);
	}
}
