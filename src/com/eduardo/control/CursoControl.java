package com.eduardo.control;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.eduardo.dao.CursoDao;
import com.eduardo.interfaces.ControlI;
import com.eduardo.model.CursoI;

public class CursoControl implements ControlI<CursoI> {

	private JTextField tfCNome;
	private JTextField tfCTempo;
	private JTable table;
	private List<CursoI> listCursoI = null;
	private CursoDao cursodao;
	private JTextField tfPesquisa;
	private CursoI curso;

	public CursoControl(JTextField tfCNome, JTextField tfCTempo, JTable table, CursoDao cursodao, CursoI curso,JTextField tfPesquisa) {
		super();
		this.tfCNome = tfCNome;
		this.tfCTempo = tfCTempo;
		this.table = table;
		this.cursodao = new CursoDao();
		this.curso = curso;
		this.tfPesquisa = tfPesquisa;
	}

	@Override
	public void salvarAction() {
		curso = new CursoI();
		if(tfCNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo NOME do curso");
			return;
		}
		if(tfCNome.getText().length()>100) {
			JOptionPane.showMessageDialog(null, "Preencha o campo NOME do curso com menos de 100 caracteres");
			return;
		}else {
			curso.setNome(tfCNome.getText());
		}
		if(tfCTempo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo TEMPO do curso");
			return;
		}else {
			curso.setTempoc(tfCTempo.getText());
		}
		
		
		boolean res = cursodao.cadastrar(curso);
		if (res) {
			JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Cadastro nao efetuado");
		}
		limparAction();
		listarAction();
	}

	private void limparAction() {
		tfCNome.setText("");
		tfCTempo.setText("");
	
		
	}

	@Override
	public void editarAction() {
		if(tfCNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo NOME do curso");
			return;
		}
		if(tfCNome.getText().length()>10) {
			JOptionPane.showMessageDialog(null, "Preencha o campo NOME do curso com menos de 100 caracteres");
			return;
		}else {
			curso.setNome(tfCNome.getText());
		}
		if(tfCTempo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo TEMPO do curso");
			return;
		}else {
			curso.setTempoc(tfCTempo.getText());
		}
			
		boolean res = cursodao.editar(curso);
		if (res) {
			JOptionPane.showMessageDialog(null, "Edicao efetuada com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Edicao nao efetuada");
		}
		listarAction();
		limparAction();
	}

	@Override
	public void excluirAction() {
		boolean res = cursodao.excluir(curso);
		if (res) {
			JOptionPane.showMessageDialog(null, "Excluido  com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Eclucao  nao efetuada");
		}
		listarAction();
		limparAction();
	}

	@Override
	public void listarAction() {
		listCursoI = cursodao.listar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (CursoI c : listCursoI) {
			model.addRow(new Object[] { c.getId(), c.getNome(), c.getTempoc() });
		}
	}

	@Override
	public CursoI getItemSelecionado() {
		int linha = table.getSelectedRow();
		return listCursoI.get(linha);
	}

	@Override
	public void preencherForm() {
		curso = getItemSelecionado();
		tfCNome.setText(curso.getNome());
		tfCTempo.setText(curso.getTempoc());

	}

	public void buscarAction() {
		String nome = tfPesquisa.getText();
		listCursoI = cursodao.buscar(nome);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (CursoI c : listCursoI) {
			model.addRow(new Object[] { c.getId(), c.getNome(), c.getTempoc() });
		}
		
	}

	public CursoControl() {
		super();
		// TODO Auto-generated constructor stub
	}


}
