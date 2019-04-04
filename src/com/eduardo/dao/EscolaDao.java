package com.eduardo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eduardo.factory.Dao;
import com.eduardo.interfaces.DaoI;
import com.eduardo.model.Escola;

public class EscolaDao extends Dao implements DaoI<Escola> {

	@Override
	public boolean cadastrar(Escola obj) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("insert into escola (nome,localidade) values(?,?)");
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getLocalidade());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean editar(Escola obj) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("update escola set nome=?,localidade=? where id=?");
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getLocalidade());
			stmt.setInt(3, obj.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean excluir(Escola obj) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("delete from escola where id=?");
			stmt.setInt(1, obj.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Escola> listar() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("SELECT id,nome,localidade FROM escola");
			ResultSet result = stmt.executeQuery();
			List<Escola> listEscolas = new ArrayList<>();
			while (result.next()) {
				Escola escola = new Escola();

				escola.setId(result.getInt("id"));

				escola.setNome(result.getString("nome"));
				escola.setLocalidade(result.getString("localidade"));

				listEscolas.add(escola);
			}
			return listEscolas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Escola> buscar(String nome) {
		try {
			PreparedStatement stmt;
			stmt = conexao
					.prepareStatement("SELECT id,nome,localidade FROM escola where nome like ? or localidade like ?");
			stmt.setString(1, nome + "%");
			stmt.setString(2, nome + "%");
			ResultSet result = stmt.executeQuery();
			List<Escola> listEscolas = new ArrayList<>();
			while (result.next()) {
				Escola escola = new Escola();

				escola.setId(result.getInt("id"));

				escola.setNome(result.getString("nome"));
				escola.setLocalidade(result.getString("localidade"));

				listEscolas.add(escola);
			}
			return listEscolas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public List<Escola> poluparCB() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("SELECT id,nome,localidade FROM escola");
			ResultSet result = stmt.executeQuery();
			List<Escola> listEscolas = new ArrayList<>();
			while (result.next()) {
				Escola escola = new Escola();

				escola.setId(result.getInt("id"));
				escola.setNome(result.getString("nome"));
				escola.setLocalidade(result.getString("localidade"));

				listEscolas.add(escola);
			}
			return listEscolas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
