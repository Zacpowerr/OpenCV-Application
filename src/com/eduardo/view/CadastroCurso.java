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

import com.eduardo.control.CursoControl;
import com.eduardo.dao.CursoDao;
import com.eduardo.model.CursoI;

public class CadastroCurso extends JInternalFrame {
	private JTextField tfCNome;
	private JTextField tfCTempo;
	private JTable table;
	private CursoDao cursodao;
	private CursoControl cursocontrol;
	private CursoI curso;
	private JTextField tfPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CadastroCurso frame = new CadastroCurso();
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
	public CadastroCurso() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				cursocontrol.listarAction();
			}
		});
		setClosable(true);
		setBounds(100, 100, 700, 487);
		getContentPane().setLayout(null);

		JLabel lblCadastroDeCursos = new JLabel("Cadastro de Cursos ");
		lblCadastroDeCursos.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCadastroDeCursos.setBounds(245, 30, 185, 22);
		getContentPane().add(lblCadastroDeCursos);

		JLabel label = new JLabel("Preencha os campos abaixo");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(251, 52, 172, 14);
		getContentPane().add(label);

		JLabel lblNome_1 = new JLabel("Nome:");
		lblNome_1.setBounds(10, 95, 46, 14);
		getContentPane().add(lblNome_1);

		JLabel lblTempo = new JLabel("Tempo:");
		lblTempo.setBounds(10, 123, 46, 14);
		getContentPane().add(lblTempo);

		JLabel lblPsquisa = new JLabel("Pesquisa:");
		lblPsquisa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPsquisa.setBounds(10, 171, 63, 14);
		getContentPane().add(lblPsquisa);

		tfCNome = new JTextField();
		tfCNome.setColumns(10);
		tfCNome.setBounds(109, 92, 536, 20);
		getContentPane().add(tfCNome);

		tfCTempo = new JTextField();
		tfCTempo.setColumns(10);
		tfCTempo.setBounds(109, 120, 536, 20);
		getContentPane().add(tfCTempo);

		JButton btSalvarC = new JButton("");
		btSalvarC.setIcon(new ImageIcon(CadastroCurso.class.getResource("/resources/icons8-save-as-50.png")));
		btSalvarC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cursocontrol.salvarAction();
				
			}
		});

		tfPesquisa = new JTextField();
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				cursocontrol.buscarAction();
			}
		});
		tfPesquisa.setColumns(10);
		tfPesquisa.setBounds(109, 169, 536, 20);
		getContentPane().add(tfPesquisa);
		btSalvarC.setBounds(301, 387, 77, 59);
		getContentPane().add(btSalvarC);

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(CadastroCurso.class.getResource("/resources/icons8-edit.png")));
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cursocontrol.editarAction();
			}
		});
		btnEditar.setBounds(214, 387, 77, 59);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(CadastroCurso.class.getResource("/resources/icons8-delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cursocontrol.excluirAction();
			}
		});
		btnExcluir.setBounds(388, 387, 77, 59);
		getContentPane().add(btnExcluir);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 203, 664, 173);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				cursocontrol.preencherForm();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "Tempo" }));
		scrollPane.setViewportView(table);
		cursocontrol = new CursoControl(tfCNome, tfCTempo, table, cursodao, curso, tfPesquisa);
	}

}
