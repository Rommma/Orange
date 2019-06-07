package br.com.univercode.pdv.model;

public class Funcionario {
	private int id;
	private String[] nome;
	private String cpf, senha, img, nomecompleto;
	private String estado, cidade, bairro, logradouro, complemento, cep; //Endereço
	private String dataNascimento;
	private int acesso;
	
	public Funcionario(String nome, String cep, String cpf, String senha, String estado, String cidade,
			String bairro, String logradouro, String complemento, String dataNascimento, String img){
		
			this.nomecompleto = nome;
			this.nome = nome.split(" "); 
			this.cep = cep; this.cpf = cpf; this.senha = senha;
			this.estado = estado; this.cidade = cidade; this.bairro = bairro;
			this.logradouro = logradouro; this.complemento = complemento;
			this.dataNascimento = dataNascimento;
			this.img = img;
			acesso = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getNome() {
		return nome[0];
	}
	
	public String getNomeCompleto() {
		return nomecompleto;
	}
	
	public void setNome(String[] nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getAcesso() {
		return acesso;
	}

	public String getCargo() {
		if(acesso == 0) {
			return "vendedor";
		}else {
			return "administrador";
		}
		
	}
	
	public void setAcesso(int acesso) {
		this.acesso = acesso;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
}
