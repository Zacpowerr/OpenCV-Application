package com.eduardo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eduardo.factory.Dao;
import com.eduardo.interfaces.DaoI;
import com.eduardo.model.Fotos;

public class FotosDao extends Dao implements DaoI<Fotos> {

	@Override
	public boolean cadastrar(Fotos obj) {
		
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("insert into fotos (idPessoas,foto)values (?,?)");
			stmt.setInt(1, obj.getPessoas().getId());
			stmt.setString(2, obj.getCaminho());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean editar(Fotos obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Fotos obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Fotos> listar() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("select idPessoas,id,foto from fotos");

		
			ResultSet result = stmt.executeQuery();
			List<Fotos> listFotos = new ArrayList<>();
			while (result.next()) {
				Fotos fotos = new Fotos();

				fotos.setId(result.getInt("id"));

				fotos.setCaminho(result.getString("texto"));
				fotos.getPessoas().setId(result.getInt("idPessoas"));

				listFotos.add(fotos);
			}
			return listFotos;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}	}

}
