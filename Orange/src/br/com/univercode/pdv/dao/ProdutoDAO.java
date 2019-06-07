package br.com.univercode.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.univercode.pdv.model.Produto;

public class ProdutoDAO {

	public void salvar(Produto p){
		Connection conn = null;
		try {
			String insert = "INSERT INTO tb_produto"+ " (nome, tipo, sabor, obs, valor, peso, tipopeso, quantidade, dataValidade, dataFabricacao, img) Values (?,?,?,?,?,?,?,?,?,?,?)";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setString(1, p.getNome());
			pstm.setString(2, p.getTipo());
			pstm.setString(3, p.getSabor());
			pstm.setString(4, p.getObs());
			pstm.setDouble(5, p.getValor());
			pstm.setDouble(6, p.getPeso());
			pstm.setString(7, p.getTipopeso());
			pstm.setInt(8, p.getQuantidade());
			pstm.setString(9, String.valueOf(p.getDataValidade()));
			pstm.setString(10, String.valueOf(p.getDataFabricacao()));
			pstm.setString(11, p.getImg());
			
			pstm.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
	
	public void alterar(Produto p){
		
		Connection conn = null;
		try {
			String insert = "UPDATE tb_produto"+ " set nome=?, tipo=?, sabor=?, obs=?, valor=?, peso=?, tipopeso=?, quantidade=?, dataValidade=?, dataFabricacao=?, img=? WHERE id = "+p.getId();
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setString(1, p.getNome());
			pstm.setString(2, p.getTipo());
			pstm.setString(3, p.getSabor());
			pstm.setString(4, p.getObs());
			pstm.setDouble(5, p.getValor());
			pstm.setDouble(6, p.getPeso());
			pstm.setString(7, p.getTipopeso());
			pstm.setInt(8, p.getQuantidade());
			pstm.setString(9, String.valueOf(p.getDataValidade()));
			pstm.setString(10, String.valueOf(p.getDataFabricacao()));
			pstm.setString(11, p.getImg());
			
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
			String insert = "DELETE FROM tb_produto"+ " WHERE id = ?";
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
	
	public Produto listarPeloId(int id){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_produto WHERE id=?";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setInt(1, id);
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Produto p = new Produto(rs.getString("nome"),rs.getString("tipo"),rs.getString("sabor"),rs.getString("obs"), 
						rs.getDouble("valor"),rs.getDouble("peso"), rs.getString("tipopeso"), rs.getInt("quantidade"), rs.getString("dataValidade"),rs.getString("dataFabricacao"), rs.getString("img"));
				
				p.setId(rs.getInt("id"));
				return p;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
	
	public ArrayList<Produto> listarTudo(){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_produto";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = 
					conn.prepareStatement(insert);
			ResultSet rs = pstm.executeQuery();
			ArrayList<Produto> lista = new ArrayList<Produto>();
			while(rs.next()){
				Produto p = new Produto(rs.getString("nome"),rs.getString("tipo"),rs.getString("sabor"),rs.getString("obs"),rs.getDouble("valor"),rs.getDouble("peso"),rs.getString("tipopeso"),rs.getInt("quantidade"), rs.getString("dataValidade"),rs.getString("dataFabricacao"),rs.getString("img"));
				p.setId(rs.getInt("id"));
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
