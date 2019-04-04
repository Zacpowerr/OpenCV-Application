package com.eduardo.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
	private static final String URL = "jdbc:mysql://localhost:3306/opencv";
	private static final String USER = "root";
	private static final String PASS = "";
	private static Connection conexao;
	
	/**
	 * Chamada singlethon da conexão
	 * @return
	 */
	public static Connection getConexao(){
		if(conexao==null){
			try {
				conexao = DriverManager.getConnection(URL, USER, PASS);
				System.out.println("Conectou...");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conexao;
	}
	
}






