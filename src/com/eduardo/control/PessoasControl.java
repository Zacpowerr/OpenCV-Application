package com.eduardo.control;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.eduardo.dao.CursoDao;
import com.eduardo.dao.EscolaDao;
import com.eduardo.dao.PessoasDao;
import com.eduardo.model.CursoI;
import com.eduardo.model.Escola;
import com.eduardo.model.Pessoas;

public class PessoasControl {

	private JTextField tfNome;
	private JTextField tfTel;
	private JTextField tfDTnascimento;
	private JTextField tfEmail;
	private PessoasDao pessoaDao;
	private Pessoas pessoa;
	private List<Pessoas> listPessoas = null;
	private List<Escola> listEscolas = null;
	private List<CursoI> listCursos = null;
	private JTable table;
	private EscolaDao escoladao;
	private JComboBox<Object> cbEscola;
	private JComboBox<Object> cbCurso;
	private JTextField tfPesquisa;

	private CursoDao cursodao;
	private CursoI cursoI;
	int cont = 10;

	private List<JCheckBox> listaCheckbox = new ArrayList<JCheckBox>();

	public PessoasControl(JTextField tfNome, JTextField tfTel, JTextField tfDTnascimento, JTextField tfEmail,
			PessoasDao pessoaDao, Pessoas pessoa, JTable table, JComboBox<Object> cbEscola, JComboBox<Object> cbCurso,
			JTextField tfPesquisa, CursoI cursoI) {
		super();
		this.tfNome = tfNome;
		this.tfTel = tfTel;
		this.tfDTnascimento = tfDTnascimento;
		this.tfEmail = tfEmail;
		this.pessoaDao = new PessoasDao();
		this.pessoa = new Pessoas();
		this.table = table;
		this.cbEscola = cbEscola;
		this.escoladao = new EscolaDao();
		this.cbCurso = cbCurso;
		this.cursodao = new CursoDao();

		this.tfPesquisa = tfPesquisa;
		this.cursoI = new CursoI();

	}

	public PessoasControl(JTextField tfNome, JTextField tfTel, JTextField tfDTnascimento, JTextField tfEmail,
			PessoasDao pessoaDao, Pessoas pessoa, JComboBox<Object> cbEscola, JComboBox<Object> cbCurso) {
		super();
		this.tfNome = tfNome;
		this.tfTel = tfTel;
		this.tfDTnascimento = tfDTnascimento;
		this.tfEmail = tfEmail;
		this.pessoaDao = new PessoasDao();
		this.pessoa = new Pessoas();
		this.cbCurso = cbCurso;
		this.cbEscola = cbEscola;
		this.escoladao = new EscolaDao();

		this.cursodao = new CursoDao();

	}

	public PessoasControl(JTable table, JTextField tfPesquisa) {
		super();

		this.pessoaDao = new PessoasDao();
		this.pessoa = new Pessoas();
		this.table = table;

		this.escoladao = new EscolaDao();

		this.cursodao = new CursoDao();

		this.tfPesquisa = tfPesquisa;

	}

	public void salvarAction() {
		boolean res = pessoaDao.cadastrar(pessoa);
		if (res) {
			System.out.println("Primeiro cadastro efetuado com  sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Cadastro nao efetuado");
		}

	}

	public void listarAction() {
		listPessoas = pessoaDao.listar();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Pessoas p : listPessoas) {
			model.addRow(new Object[] { p.getId(), p.getNome(), p.getEscola().getNome(), p.getDataNasc(), p.getEmail(),
					p.getTelefone() });
		}

	}

	public void idsalvarAction() {
		pessoa = new Pessoas();
		pessoa.setNome("");
		pessoa.setDataNasc(new Date(new java.util.Date().getTime()));
		pessoa.setTelefone("");
		pessoa.getCursoI().setNome(null);
		pessoa.setEmail("");

		boolean res = pessoaDao.cadastrar(pessoa);
		if (res) {
		//	JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Cadastro nao efetuado");
		}
	}

	public int pegarIdAction() {

		try {
			return pessoaDao.pegaridMAX();

		} catch (Exception e) {
			return 0;
		}

	}

	public void editarAction() {
		pessoa.setId(pegarIdAction());
		if(tfNome.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "preencha o campo NOME com um caracteres validos");
			return;
		}else {
			pessoa.setNome(tfNome.getText());
		}
		if(tfDTnascimento.getText().equals("") ) {
			JOptionPane.showMessageDialog(null, "preencha o campo DATA com um caracteres validos");
			return;
		}else {
			pessoa.setDataNasc(dataUsuarioParaBanco(tfDTnascimento.getText()));	
		}
		if(tfTel.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "preencha o campo TELEFONE com um caracteres validos");
			return;
		}
		if(tfTel.getText().length()>13) {
			JOptionPane.showMessageDialog(null, "preencha o campo TELEFONE com menos de 13 caracteres");
			return;
			
		}else {
			pessoa.setTelefone(tfTel.getText());	
		}
		if(tfEmail.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "preencha o campo de Email");
			return;
			
		}else {
			pessoa.setEmail(tfEmail.getText());
		}		
		pessoa.setEscola((Escola) cbEscola.getSelectedItem());
		pessoa.setCursoI((CursoI) cbCurso.getSelectedItem());

		boolean res = pessoaDao.editar(pessoa);
		if (res) {
			JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");
			limparAction();
		} 
		cadastrarCursoAction();
		
	}

	private void limparAction() {
		tfDTnascimento.setText("");
		tfEmail.setText("");
		tfNome.setText("");
		tfTel.setText("");
		cbCurso.setSelectedIndex(0);
		cbEscola.setSelectedIndex(0);
		
		
	}
	public  Date dataUsuarioParaBanco(String data){
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataUtil = formatador.parse(data);
            return new java.sql.Date(dataUtil.getTime());
        } catch (ParseException ex) {
            System.out.println("Erro ao converter data");
            return null;
        }

            
        }


	public void cadastrarCursoAction() {
		
			boolean res = pessoaDao.cadastrarCurso(pessoa);
			if (res) {
				System.out.println("curso cadastrado");
			} 
		}
	

	

	public void buscarPorNomeActiob() {
		String nome = tfPesquisa.getText();
		listPessoas = pessoaDao.buscar(nome);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0);
		for (Pessoas p : listPessoas) {
			model.addRow(new Object[] { p.getId(), p.getNome(), p.getEscola().getNome(), p.getDataNasc(), p.getEmail(),
					p.getTelefone() });
		}

	}

	public void popularComboBoxEscolasAction() {
		listEscolas = escoladao.listar();
		ComboBoxModel<Object> modelCombo;
		modelCombo = new DefaultComboBoxModel<Object>(listEscolas.toArray());
		cbEscola.setModel(modelCombo);
	}

	public void verificaCursoAction() {
		listCursos = cursodao.listar();
		ComboBoxModel<Object> modelCombo;
		modelCombo = new DefaultComboBoxModel<Object>(listCursos.toArray());
		cbCurso.setModel(modelCombo);

	}
}
