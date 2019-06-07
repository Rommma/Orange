package br.com.univercode.pdv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.univercode.pdv.model.Entrada;
import br.com.univercode.pdv.model.Produto;

public class EntradaDAO {
	public void salvar(Entrada e){
		Connection conn = null;
		try {
			String insert = "INSERT INTO tb_caixa"+ " (entrada, tipoentrada, dataentrada, horaentrada, formapagamento, idatendente) Values (?,?,?,?,?,?)";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm = conn.prepareStatement(insert);
			
			pstm.setDouble(1, e.getEntrada());
			pstm.setString(2, e.getTipoentrada());
			pstm.setString(3, e.getDataentrada());
			pstm.setString(4, e.getHoraentrada());
			pstm.setString(5, e.getFormapagamento());
			pstm.setDouble(6, e.getIdatendente());
			
			pstm.execute();
		} catch (Exception er) {
			System.out.println(er.getMessage());
		}finally{
			new Conexao().fecharConexao(conn);
		}
	}

	public ArrayList<Entrada> listarTudo(){
		Connection conn = null;
		try {
			String insert = "SELECT * FROM tb_entrada";
			conn = new Conexao().getConexao(); 
			PreparedStatement pstm =  conn.prepareStatement(insert);
			ResultSet rs = pstm.executeQuery();
			ArrayList<Entrada> lista = new ArrayList<Entrada>();
			while(rs.next()){
				Entrada e = new Entrada(rs.getDouble("entrada"),rs.getString("tipoentrada"),rs.getString("dataentrada"),rs.getString("horaentrada"),rs.getString("formapagamento"),rs.getInt("idatendente"));
				e.setId(rs.getInt("id"));
				lista.add(e);
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
