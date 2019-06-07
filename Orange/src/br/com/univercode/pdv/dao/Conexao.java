package br.com.univercode.pdv.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	public static Connection getConexao(){
		String servidor = "localhost";
		String banco = "bd_pdv";
		String user = "postgres";
		String senha = "123";
		
        String url = "jdbc:postgresql://"+servidor+":5432/"+banco;
		
		try{
			
			Class.forName("org.postgresql.Driver");
			
			Connection conn = DriverManager.getConnection(url,user,senha);
			return conn;
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static void fecharConexao(Connection conn){
		try{
			if(conn!=null)
				conn.close();
		}catch(Exception e){
			System.err.println("Erro:"+e.getMessage());
		}
	}
}
