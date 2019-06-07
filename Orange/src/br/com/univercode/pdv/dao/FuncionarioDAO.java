package br.com.univercode.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.univercode.pdv.model.Funcionario;

public class FuncionarioDAO {
	public void salvar(Funcionario f){
		Connection conn = null;
		try {
			String insert = "INSERT INTO tb_funcionario"+ " (nome, cep, cpf, senha, estado, cidade, bairro, logradouro, complemento, dataNascimento, img, acesso, login) Values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setString(1, f.getNomeCompleto());
			pstm.setString(2, f.getCep());
			pstm.setString(3, f.getCpf());
			pstm.setString(4, f.getSenha());
			pstm.setString(5, f.getEstado());
			pstm.setString(6, f.getCidade());
			pstm.setString(7, f.getBairro());
			pstm.setString(8, f.getLogradouro());
			pstm.setString(9, f.getComplemento());
			pstm.setString(10, String.valueOf(f.getDataNascimento()));
			pstm.setString(11, f.getImg());
			pstm.setInt(12, f.getAcesso());
			pstm.setString(13, f.getNome());
			
			pstm.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
	
	public void alterar(Funcionario f){
		
		Connection conn = null;
		try {
			String insert = "UPDATE tb_funcionario"+ " set nome=?, cep=?, cpf=?, senha=?, estado=?, cidade=?, bairro=?, logradouro=?, img=?, acesso=?, login=? WHERE id = "+f.getId();;
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setString(1, f.getNomeCompleto());
			pstm.setString(2, f.getCep());
			pstm.setString(3, f.getCpf());
			pstm.setString(4, f.getSenha());
			pstm.setString(5, f.getEstado());
			pstm.setString(6, f.getCidade());
			pstm.setString(7, f.getBairro());
			pstm.setString(8, f.getLogradouro());
			pstm.setString(9, f.getImg());
			pstm.setInt(10, f.getAcesso());
			pstm.setString(11, f.getNome());
			
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
			String insert = "DELETE FROM tb_funcionario"+" WHERE id = ?";
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
	
	public Funcionario listarPeloId(int id){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_funcionario WHERE id=?";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setInt(1, id);
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Funcionario f = new Funcionario(rs.getString("nome"), rs.getString("cep"), rs.getString("cpf"), rs.getString("senha"), 
						rs.getString("estado"), rs.getString("cidade"), rs.getString("bairro"), rs.getString("logradouro"),rs.getString("complemento"), rs.getString("dataNascimento"), rs.getString("img"));
				
				f.setId(rs.getInt("id"));
				f.setAcesso(rs.getInt("acesso"));
				return f;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
	
	public Funcionario achar(String senha, String nome){
		Connection conn = null;
		try {
			
			String insert = "SELECT * FROM tb_funcionario WHERE senha=? AND login=?";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setString(1, senha);
			pstm.setString(2, nome);
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Funcionario f = new Funcionario(rs.getString("nome"), rs.getString("cep"), rs.getString("cpf"), rs.getString("senha"), 
							rs.getString("estado"), rs.getString("cidade"), rs.getString("bairro"), rs.getString("logradouro"),rs.getString("complemento"), rs.getString("dataNascimento"), rs.getString("img"));
					
				f.setId(rs.getInt("id"));
				f.setAcesso(rs.getInt("acesso"));
				return f;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally{
			new Conexao().fecharConexao(conn);
		}
		return null;
	}
	
	public ArrayList<Funcionario> listarTudo(){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_funcionario";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			ResultSet rs = pstm.executeQuery();
			
			ArrayList<Funcionario> lista = new ArrayList<Funcionario>();
			while(rs.next()){
				Funcionario f = new Funcionario(rs.getString("nome"), rs.getString("cep"), rs.getString("cpf"), rs.getString("senha"), 
						rs.getString("estado"), rs.getString("cidade"), rs.getString("bairro"), rs.getString("logradouro"),rs.getString("complemento"), rs.getString("dataNascimento"), rs.getString("img"));
				
				f.setId(rs.getInt("id"));
				f.setAcesso(rs.getInt("acesso"));
				
				lista.add(f);
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}
}
