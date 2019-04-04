package com.eduardo.model;

import java.sql.Date;

public class Pessoas {
	private int id;
	private String nome;
	private Date dataNasc;
	private String email;
	private CursoI cursoI;
	private String telefone;
	private Escola escola;
	public Pessoas() {
		super();
		// TODO Auto-generated constructor stub
		cursoI = new CursoI();
		escola = new Escola();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public CursoI getCursoI() {
		return cursoI;
	}
	public void setCursoI(CursoI cursoI) {
		this.cursoI = cursoI;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Escola getEscola() {
		return escola;
	}
	public void setEscola(Escola escola) {
		this.escola = escola;
	}
	
	

}
