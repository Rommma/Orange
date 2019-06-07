package br.com.univercode.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoDAO {
	public void salvar(String tipo){
		Connection conn = null;
		try {
			String insert = "INSERT INTO tb_tipo"+ " (tipo) Values (?)";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setString(1, tipo);
			
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
			String insert = "DELETE FROM tb_tipo"+ " WHERE id = ?";
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
			String insert = "SELECT * FROM tb_tipo";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			ResultSet rs = pstm.executeQuery();
			ArrayList<String> lista = new ArrayList<String>();
			
			while(rs.next()){
				lista.add(rs.getString("tipo"));
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
