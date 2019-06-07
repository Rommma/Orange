package br.com.univercode.pdv.model;

public class Pagamento {
	private double valor;
	private String nome;
	private int venda;
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getVenda() {
		return venda;
	}
	public void setVenda(int venda) {
		this.venda = venda;
	}
	public Pagamento(String nome, double valor){
		this.valor = valor;
		this.nome = nome;
	}
	public Pagamento(String nome, double valor, int venda){
		this.valor = valor;
		this.nome = nome;
		this.venda = venda;
	}
}
