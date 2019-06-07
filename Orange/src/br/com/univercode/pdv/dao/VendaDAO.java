package br.com.univercode.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.univercode.pdv.model.Caixa;
import br.com.univercode.pdv.model.Pagamento;
import br.com.univercode.pdv.model.Produto;
import br.com.univercode.pdv.model.Venda;
import br.com.univercode.pdv.repositorio.RepositorioV;
import br.com.univercode.pdv.web.GerarRelatorio;

public class VendaDAO {
	ItemVendaDAO ivdao = new ItemVendaDAO();
	PagamentoDAO pdao = new PagamentoDAO();
	
	public void salvar(Venda v){
		//Inserir compras e forma de pagamento
		int id = 0;
		Connection conn = null;
		try {
			String insert = "INSERT INTO tb_venda"+ " (cliente, vendedor, desconto, total, troco, data, hora) Values (?, ?, ?, ?, ?, ?, ?)";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setString(1, v.getCliente());
			pstm.setString(2, v.getVendedor());
			pstm.setDouble(3, v.getDesconto());
			pstm.setDouble(4, v.getTotal());
			pstm.setDouble(5, v.getTroco());
			pstm.setString(6, Caixa.getInstance().getDataAtual());
			pstm.setString(7, Caixa.getInstance().getHoraAtual());
			
			pstm.execute();
			id = encontrar();
			
			RepositorioV.getInstance().setUltimavenda(id);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			new Conexao().fecharConexao(conn);
		}

		for (Produto p : v.getCompras()) {
			p.setVenda(id);
			ivdao.salvar(p);
		}
		for (Pagamento p : v.getPagamentos()) {
			p.setVenda(id);
			pdao.salvar(p);
		}
	}
	public int encontrar() {
		Connection conn = null;
		try {
			//String insert = "SELECT MAX(id) FROM tb_venda";
			String insert = "SELECT id FROM tb_venda ORDER BY id DESC LIMIT 1";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				return rs.getInt("id");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			new Conexao().fecharConexao(conn);
		}
		return 0;
	}
	public Venda listarPeloId(int id){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_venda WHERE id=?";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setInt(1, id);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				ArrayList<Produto> compras = ivdao.listarPelaVenda(rs.getInt("id"));
				ArrayList<Pagamento> pagamentos = pdao.listarPelaVenda(rs.getInt("id"));
				Venda v = new Venda(rs.getString("cliente"), rs.getString("vendedor"), rs.getDouble("desconto"), rs.getDouble("total"), rs.getDouble("troco"), compras,null);
				
				v.setData(rs.getString("data"));
				v.setHora(rs.getString("hora"));
				
				v.setId(rs.getInt("id"));
				return v;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
	public ArrayList<Venda> listarTudo(){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_venda";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			ResultSet rs = pstm.executeQuery();
			ArrayList<Venda> lista = new ArrayList<Venda>();
			while(rs.next()){
				ArrayList<Produto> compras = ivdao.listarPelaVenda(rs.getInt("id"));
				Venda v = new Venda(rs.getString("cliente"), rs.getString("vendedor"), rs.getDouble("desconto"), rs.getDouble("total"), rs.getDouble("troco"), compras,null);
				
				v.setData(rs.getString("data"));
				v.setHora(rs.getString("hora"));
				
				v.setId(rs.getInt("id"));
				lista.add(v);
			}
			return lista;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
}
