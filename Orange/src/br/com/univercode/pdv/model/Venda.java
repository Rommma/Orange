package br.com.univercode.pdv.model;

import java.util.ArrayList;

public class Venda {
	private int id;
	private String cliente, vendedor, hora, data;
	private double desconto, total, troco;
	private ArrayList<Produto> compras;
	private ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
	
	public Venda(String cliente, String vendedor, double desconto, double total, double troco, ArrayList<Produto> compras, ArrayList<Pagamento> pagamentos){
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.desconto = desconto;
		this.total = total;
		this.troco = troco;
		this.compras = compras;
		this.pagamentos = pagamentos;
	}
	
	public void addPagamento(Pagamento p) {
		this.pagamentos.add(p);
	}
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getTroco() {
		return troco;
	}
	public void setTroco(double troco) {
		this.troco = troco;
	}
	public ArrayList<Produto> getCompras() {
		return compras;
	}
	public void setCompras(ArrayList<Produto> compras) {
		this.compras = compras;
	}
	public ArrayList<Pagamento> getPagamentos() {
		return pagamentos;
	}
	public void setPagamentos(ArrayList<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}
