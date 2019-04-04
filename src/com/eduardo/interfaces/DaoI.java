package com.eduardo.interfaces;

import java.util.List;

public interface DaoI<E> {
	public boolean cadastrar (E obj);
	public boolean editar(E obj);
	public boolean excluir(E obj);
	public List<E> listar();

}
