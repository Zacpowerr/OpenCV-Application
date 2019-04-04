package com.eduardo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eduardo.factory.Dao;
import com.eduardo.interfaces.DaoI;
import com.eduardo.model.CursoI;
import com.eduardo.model.Pessoas;


public class CursoPessoaDao extends Dao  implements DaoI<CursoI>{

	
	public boolean cadastrar(CursoI obj,Pessoas pessoa) {
		
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("insert into curso_pessoa (idPessoa,idCurso) values(?,?)");
			stmt.setInt(1, obj.getId());
			stmt.setInt(2, pessoa.getId());
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(CursoI obj) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<CursoI> listar(String nome) {
		
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("select idCurso,idPessoa from curso_pessoa where idPessoa = ?");
			stmt.setInt(1, Integer.parseInt(nome));
			ResultSet res = stmt.executeQuery();
			List<CursoI> listaCursos  = new ArrayList<>();
			while(res.next()){
			CursoI c = new CursoI();
			Pessoas p = new Pessoas();
			c.setId(res.getInt("idCurso"));
			p.setId(res.getInt("idPessoa"));
			listaCursos.add(c);
			}
			return listaCursos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean cadastrar(CursoI obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CursoI> listar() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
