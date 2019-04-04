package com.eduardo.control;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.eduardo.dao.EscolaDao;
import com.eduardo.interfaces.ControlI;
import com.eduardo.model.Escola;

public class EscolaControl implements ControlI<Escola> {
	private JTextField tfLocal;
	private JTextField tfNome;
	private JTable table;
	private Escola escola;
	private EscolaDao escoladao;
	private List<Escola> listEscola = null;
	private JTextField tfPesquisa;

	public EscolaControl(JTextField tfLocal, JTextField tfNome, JTable table, Escola escola, EscolaDao escoladao,
			JTextField tfPesquisa) {
		super();
		this.tfLocal = tfLocal;
		this.tfNome = tfNome;
		this.table = table;
		this.escola = escola;
		this.escoladao = new EscolaDao();
		this.tfPesquisa = tfPesquisa;

	}

	@Override
	public void salvarAction() {
		escola = new Escola();
		if(tfNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo NOME da escola");
			return;
		}else {
			escola.setNome(tfNome.getText());	
		}
		if(tfLocal.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo LOCAL da escola");
			return;
		}else {
			escola.setLocalidade(tfLocal.getText());	
		}
		
		
		boolean res = escoladao.cadastrar(escola);
		if (res) {
			JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Cadastro nao efetuado");
		}
		listarAction();
		limparAction();
	}

	private void limparAction() {
		tfLocal.setText("");
		tfNome.setText("");
		
	}

	@Override
	public void editarAction() {
		if(tfNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo NOME da escola");
			return;
		}else {
			escola.setNome(tfNome.getText());	
		}
		if(tfLocal.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo LOCAL da escola");
			return;
		}else {
			escola.setLocalidade(tfLocal.getText());	
		}
		boolean res = escoladao.editar(escola);
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
		boolean res = escoladao.excluir(escola);
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
		listEscola = escoladao.listar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Escola e : listEscola) {
			model.addRow(new Object[] { e.getId(), e.getNome(), e.getLocalidade() });
		}

	}

	@Override
	public Escola getItemSelecionado() {
		int linha = table.getSelectedRow();
		return listEscola.get(linha);
	}

	@Override
	public void preencherForm() {
		escola = getItemSelecionado();
		tfNome.setText(escola.getNome());
		tfLocal.setText(escola.getLocalidade());

	}
	public void buscarAction(){
		String nome = tfPesquisa.getText();
		listEscola = escoladao.buscar(nome);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Escola e : listEscola) {
			model.addRow(new Object[] { e.getId(), e.getNome(), e.getLocalidade() });
		}
	}
	

}
