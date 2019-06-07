package br.com.univercode.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.univercode.pdv.model.Produto;

public class ItemVendaDAO {
	public void salvar(Produto p){
		Connection conn = null;
		try {
			String insert = "INSERT INTO tb_itemvenda"+ " (produtoid, produtonome, quantidade, subtotal, venda) Values (?, ?, ?, ?, ?)";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			double subtotal = p.getQuantidadecompra() * p.getValor();
			
			pstm.setInt(1, p.getId());
			pstm.setString(2, p.getNome());
			pstm.setInt(3, p.getQuantidadecompra());
			pstm.setDouble(4, subtotal);
			pstm.setInt(5, p.getVenda());
			
			pstm.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
	public ArrayList<Produto> listarPelaVenda(int id){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_itemvenda where venda=?";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			
			ArrayList<Produto> lista = new ArrayList<Produto>();
			while(rs.next()){
				Produto p = new Produto(rs.getInt("id"),rs.getInt("quantidade"), rs.getDouble("subtotal"), rs.getInt("venda"));
				
				lista.add(p);
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
