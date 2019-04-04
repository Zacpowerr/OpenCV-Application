package com.eduardo.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.eduardo.control.CursoControl;
import com.eduardo.control.PessoasControl;
import com.eduardo.dao.PessoasDao;
import com.eduardo.factory.Reconhecimento;
import com.eduardo.factory.Treino;
import com.eduardo.model.Pessoas;
import com.eduardo.view.opencv.Treinamento;

public class Cadastro extends JInternalFrame {
	private JTextField tfNome;
	private JTextField tfTel;
	private JTextField tfDTnascimento;
	private JTextField tfEmail;
	private PessoasControl pessoacontrol;
	private PessoasDao pessoaDao;
	private Pessoas pessoa;
	private JComboBox<Object> cbEscola;
	private Treinamento treinamento;
	private CursoControl cc = null;
	private JComboBox<Object> cbCurso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cadastro() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				pessoacontrol.verificaCursoAction();

			}

		});
		setTitle("Cadastro de Alunos");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 700, 522);
		getContentPane().setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(9, 80, 46, 14);
		getContentPane().add(lblNome);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 253, 69, 14);
		getContentPane().add(lblTelefone);

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(10, 141, 116, 14);
		getContentPane().add(lblDataDeNascimento);

		JLabel lblCadastroDeAluno = new JLabel("Cadastro de Aluno");
		lblCadastroDeAluno.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCadastroDeAluno.setBounds(253, 36, 178, 14);
		getContentPane().add(lblCadastroDeAluno);

		JLabel lblPreenchaOsCampos = new JLabel("Preencha os campos abaixo");
		lblPreenchaOsCampos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPreenchaOsCampos.setBounds(256, 59, 172, 14);
		getContentPane().add(lblPreenchaOsCampos);

		JLabel lblCursoDeInteresse = new JLabel("Curso de Interesse:");
		lblCursoDeInteresse.setBounds(10, 309, 116, 14);
		getContentPane().add(lblCursoDeInteresse);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 197, 55, 14);
		getContentPane().add(lblEmail);

		JLabel lblEscola = new JLabel("Escola");
		lblEscola.setBounds(9, 365, 96, 14);
		getContentPane().add(lblEscola);

		tfNome = new JTextField();
		tfNome.setBounds(9, 105, 649, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);

		tfTel = new JTextField();
		tfTel.setColumns(10);
		tfTel.setBounds(10, 278, 649, 20);
		getContentPane().add(tfTel);

		tfDTnascimento = new JTextField();
		tfDTnascimento.setColumns(10);
		tfDTnascimento.setBounds(10, 166, 649, 20);
		getContentPane().add(tfDTnascimento);

		JButton btnSalvar = new JButton("");
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setIcon(new ImageIcon(Cadastro.class.getResource("/resources/icons8-save-as-50.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				pessoacontrol.editarAction();
				Treino.iniciar();
				Reconhecimento.iniciar();
				
				

			}
		});

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(10, 222, 649, 20);
		getContentPane().add(tfEmail);
		btnSalvar.setBounds(303, 421, 55, 60);
		getContentPane().add(btnSalvar);

		cbEscola = new JComboBox<Object>();
		cbEscola.setBounds(9, 390, 649, 20);
		getContentPane().add(cbEscola);

		cbCurso = new JComboBox<Object>();
		
		cbCurso.setBounds(9, 334, 649, 20);
		getContentPane().add(cbCurso);
		pessoacontrol = new PessoasControl(tfNome, tfTel, tfDTnascimento, tfEmail, pessoaDao, pessoa, null, cbEscola,
				cbCurso, null, null);
		pessoacontrol.popularComboBoxEscolasAction();
	}

}
