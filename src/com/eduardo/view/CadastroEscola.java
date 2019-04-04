package com.eduardo.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import com.eduardo.control.EscolaControl;
import com.eduardo.dao.EscolaDao;
import com.eduardo.model.Escola;

public class CadastroEscola extends JInternalFrame {
	private JTextField tfLocal;
	private JTextField tfNome;
	private JTable table;
	private Escola escola;
	private EscolaDao escoladao;
	private EscolaControl escolacontrol;
	private JTextField tfPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroEscola frame = new CadastroEscola();
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
	public CadastroEscola() {
		setClosable(true);
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				escolacontrol.listarAction();
			}
		});
		setBounds(100, 100, 700, 487);
		getContentPane().setLayout(null);

		JLabel lblCadastroDeEscolas = new JLabel("Cadastro de Escolas");
		lblCadastroDeEscolas.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCadastroDeEscolas.setBounds(251, 33, 183, 22);
		getContentPane().add(lblCadastroDeEscolas);

		JLabel label_2 = new JLabel("Preencha os campos abaixo");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBounds(256, 55, 172, 14);
		getContentPane().add(label_2);

		JLabel label = new JLabel("Nome:");
		label.setBounds(10, 93, 46, 14);
		getContentPane().add(label);

		JLabel label_3 = new JLabel("Local:");
		label_3.setBounds(10, 121, 46, 14);
		getContentPane().add(label_3);

		JLabel lblPesquisa = new JLabel("Pesquisa:");
		lblPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPesquisa.setBounds(10, 169, 63, 14);
		getContentPane().add(lblPesquisa);

		tfLocal = new JTextField();
		tfLocal.setColumns(10);
		tfLocal.setBounds(112, 118, 536, 20);
		getContentPane().add(tfLocal);

		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(112, 90, 536, 20);
		getContentPane().add(tfNome);

		JButton button = new JButton("");
		button.setIcon(new ImageIcon(CadastroEscola.class.getResource("/resources/icons8-save-as-50.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolacontrol.salvarAction();
			}
		});

		tfPesquisa = new JTextField();
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				escolacontrol.buscarAction();
			}
		});
		tfPesquisa.setColumns(10);
		tfPesquisa.setBounds(112, 167, 536, 20);
		getContentPane().add(tfPesquisa);
		button.setBounds(304, 387, 77, 59);
		getContentPane().add(button);

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(CadastroEscola.class.getResource("/resources/icons8-edit.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolacontrol.editarAction();
			}
		});
		btnEditar.setBounds(217, 387, 77, 59);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(CadastroEscola.class.getResource("/resources/icons8-delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolacontrol.excluirAction();
			}
		});
		btnExcluir.setBounds(391, 387, 77, 59);
		getContentPane().add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 199, 664, 173);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				escolacontrol.preencherForm();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "Local" }));
		scrollPane.setViewportView(table);
		escolacontrol = new EscolaControl(tfLocal, tfNome, table, escola, escoladao, tfPesquisa);

	}

}
