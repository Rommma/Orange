package br.com.univercode.pdv.model;

public class Gerente extends Funcionario{

	public Gerente(String nome, String rg, String cpf, String senha, String estado, String cidade, String bairro,
			String logradouro, String complemento, String dataNascimento, String img) {
		super(nome, rg, cpf, senha, estado, cidade, bairro, logradouro, complemento, dataNascimento, img);

		this.setAcesso(1);
	}	
}
