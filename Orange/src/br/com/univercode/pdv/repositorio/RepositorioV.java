package br.com.univercode.pdv.repositorio;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.univercode.pdv.dao.ProdutoDAO;
import br.com.univercode.pdv.dao.VendaDAO;
import br.com.univercode.pdv.model.Pagamento;
import br.com.univercode.pdv.model.Produto;
import br.com.univercode.pdv.model.Venda;

public class RepositorioV {
	private String cliente, vendedor;
	private double desconto, total, troco;
	private ArrayList<Produto> compras;
	private ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
	private String ultimavenda;
	private String ativo = "0";
	
	VendaDAO vdao = new VendaDAO();
	
	private static RepositorioV r;
	public static RepositorioV getInstance() {
		if(r == null){
			r = new RepositorioV();
		}
		return r;
	}
	
	ArrayList<Venda> vendas = new ArrayList<Venda>();
	
	public void addVenda(double troco, ArrayList<Pagamento> pagamentos ){
		ProdutoDAO pdao = new ProdutoDAO();
		for (Produto compra : compras) {
			compra.setQuantidade(compra.getQuantidade() - compra.getQuantidadecompra());
			
			pdao.alterar(compra);
		}
		
		
		Venda v = new Venda(cliente,vendedor,desconto, total, troco, compras, pagamentos);

		vendas.add(v);
		
		vdao.salvar(v);
		
		this.cliente = "";
		this.vendedor = "";
		this.desconto = 0;
		this.total = 0;
		this.troco = 0;
		this.compras = null;
		this.pagamentos = null;
		
		this.ativo = "0";
	}
	public void addInfo(String cliente, String vendedor, double desconto, double total, ArrayList<Produto> compras) {
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.desconto = desconto;
		this.total = total;
		this.compras = compras;
		this.ativo = "1";
	}
	public void cancela() {
		this.cliente = "";
		this.vendedor = "";
		this.desconto = 0;
		this.total = 0;
		this.troco = 0;
		this.compras = null;
		this.pagamentos = null;
	}
	public String getUltimavenda() {
		return ultimavenda;
	}
	public void deleteultimavenda() {
		this.ultimavenda = null;
	}
	public void setUltimavenda(int ultimavenda) {
		this.ultimavenda = String.valueOf(ultimavenda);
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
}

