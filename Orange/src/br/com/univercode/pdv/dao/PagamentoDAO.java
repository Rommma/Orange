package br.com.univercode.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.univercode.pdv.model.Pagamento;
import br.com.univercode.pdv.model.Produto;

public class PagamentoDAO {
	public void salvar(Pagamento p){
		Connection conn = null;
		try {
			String insert = "INSERT INTO tb_pagamento"+ " (nome, valor, venda) Values (?, ?, ?)";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setString(1, p.getNome());
			pstm.setDouble(2, p.getValor());
			pstm.setInt(3, p.getVenda());
			
			pstm.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
	public ArrayList<Pagamento> listarPelaVenda(int id){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_pagamento where venda="+id;
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			ResultSet rs = pstm.executeQuery();
			
			ArrayList<Pagamento> lista = new ArrayList<Pagamento>();
			while(rs.next()){
				Pagamento p = new Pagamento(rs.getString("nome"), rs.getDouble("valor"),rs.getInt("venda"));
				
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
	public ArrayList<Pagamento> listarTudo(){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_pagamento";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			ResultSet rs = pstm.executeQuery();
			
			ArrayList<Pagamento> lista = new ArrayList<Pagamento>();
			while(rs.next()){
				Pagamento p = new Pagamento(rs.getString("nome"), rs.getDouble("valor"),rs.getInt("venda"));
				
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
