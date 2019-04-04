package com.eduardo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eduardo.factory.Dao;
import com.eduardo.model.Login;

public class LoginDao extends Dao {
	public boolean verifica(String login, String senha) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("SELECT id,login, senha FROM usuarios where login = ? and senha= md5(?)");
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet result = stmt.executeQuery();
			List<Login> list = new ArrayList<>();
			if (result.next()) {
				Login l = new Login();
				l.setId(result.getInt("id"));
				l.setSenha(result.getString("senha"));
				l.setUsuario(result.getString("login"));
				list.add(l);
				return true;
			}
			return false;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

}
