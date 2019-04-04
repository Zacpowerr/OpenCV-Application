package com.eduardo.model;

public class CursoI {
	
	
	private int id;
	private String nome;
	private String tempoc;
	public CursoI() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getTempoc() {
		return tempoc;
	}
	public void setTempoc(String tempoc) {
		this.tempoc = tempoc;
	}
	@Override
	public String toString() {
		return  nome + ", tempo = " + tempoc ;
	}
}
