package br.com.univercode.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.univercode.pdv.model.Produto;

public class SaborDAO {
	public void salvar(String sabor){
		Connection conn = null;
		try {
	
			String insert = "INSERT INTO tb_sabor"+ " (sabor) Values (?)";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setString(1, sabor);
			
			pstm.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
	public void delete(int id){
		Connection conn = null;
		try {
			String insert = "DELETE FROM tb_sabor"+ " WHERE id = ?";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setInt(1, id);
			
			pstm.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
	public ArrayList<String> listarTudo(){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_sabor";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			ResultSet rs = pstm.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			
			while(rs.next()){
				lista.add(rs.getString("sabor"));
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
