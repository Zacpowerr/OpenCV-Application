package com.eduardo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eduardo.factory.Dao;
import com.eduardo.interfaces.DaoI;
import com.eduardo.model.CursoI;

public class CursoDao extends Dao implements DaoI<CursoI> {

	@Override
	public boolean cadastrar(CursoI obj) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("insert into cursos (nome,tempo) values(?,?)");
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getTempoc());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean editar(CursoI obj) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("update cursos set nome=?,tempo=? where id=?");
			stmt.setString(1, obj.getNome());
			stmt.setString(2, obj.getTempoc());
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
	public boolean excluir(CursoI obj) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("delete from cursos where id=?");
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
	public List<CursoI> listar() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("SELECT id,nome,tempo FROM cursos");
			ResultSet result = stmt.executeQuery();
			List<CursoI> listCursos = new ArrayList<>();
			while (result.next()) {
				CursoI curso = new CursoI();

				curso.setId(result.getInt("id"));
				curso.setNome(result.getString("nome"));
				curso.setTempoc((result.getString("tempo")));

				listCursos.add(curso);
			}
			return listCursos;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	public List<CursoI> buscar(String nome) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("SELECT id,nome,tempo FROM cursos where nome like ? or tempo like ? ");
			stmt.setString(1, nome+"%");
			stmt.setString(2, nome+"%");
			ResultSet result = stmt.executeQuery();
			List<CursoI> listCursos = new ArrayList<>();
			while (result.next()) {
				CursoI curso = new CursoI();

				curso.setId(result.getInt("id"));
				curso.setNome(result.getString("nome"));
				curso.setTempoc((result.getString("tempo")));

				listCursos.add(curso);
			}
			return listCursos;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	

}
