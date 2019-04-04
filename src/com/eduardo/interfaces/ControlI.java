package com.eduardo.interfaces;

public interface ControlI<E> {
	public void salvarAction();
	public void editarAction();
	public void excluirAction();
	public void listarAction();
	public E getItemSelecionado();
	public void preencherForm();

}
