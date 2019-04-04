package com.eduardo.model;

public class Fotos {

	private int id;
	private String caminho;
	private Pessoas pessoas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	public Pessoas getPessoas() {
		return pessoas;
	}
	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}
	public Fotos() {
		super();
		pessoas = new Pessoas();
	}
	
}
