package br.com.univercode.pdv.model;

public class Produto {
	private String nome, tipo, sabor, obs,img, tipopeso;
	private double valor, peso, subtotal;
	private int quantidade, id, quantidadecompra, venda;
	private String dataValidade, dataFabricacao;
	
	public Produto(String nome, String tipo, String sabor,String obs, double valor, double peso, String tipopeso, int qtd,String dv, String df, String img){
		this.nome = nome;
		this.tipo = tipo;
		this.sabor = sabor;
		this.obs = obs;
		this.valor = valor;
		this.peso = peso;
		this.tipopeso = tipopeso;
		this.quantidade = qtd;
		this.img = img;
		this.dataValidade = dv;
		this.dataFabricacao= df;
		this.quantidadecompra = 1;
	}
	public Produto(int id, int quantidade, double subtotal, int venda) {
		this.id = id;
		this.quantidadecompra = quantidade;
		this.subtotal = subtotal;
		this.venda = venda;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public double getValor() {
		String v = String.valueOf(valor);
		return valor;
	}
	public String getValorString() {
	
		String v = String.valueOf(valor * 10);
		v.replace(".", ",");
		return v;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(String dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public String getTipopeso() {
		return tipopeso;
	}

	public void setTipopeso(String tipopeso) {
		this.tipopeso = tipopeso;
	}

	public int getQuantidadecompra() {
		return quantidadecompra;
	}

	public void setQuantidadecompra(int quantidadecompra) {
		this.quantidadecompra = quantidadecompra;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public int getVenda() {
		return venda;
	}
	public void setVenda(int venda) {
		this.venda = venda;
	}
	
}
