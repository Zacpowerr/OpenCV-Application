package com.eduardo.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import com.eduardo.control.PessoasControl;

public class ListarAlunos extends JInternalFrame {
	private JTable table;
	private PessoasControl pc;
	private JTextField tfPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarAlunos frame = new ListarAlunos();
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
	public ListarAlunos() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				pc.listarAction();
				
			}
		});
		setTitle("Lista de Cadastros\r\n");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 536, 381);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 500, 248);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Escola", "Data de Nascimento", "Email", "Telefone"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(141);
		scrollPane.setViewportView(table);
		
		
		tfPesquisa = new JTextField();
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				pc.buscarPorNomeActiob();
			}
		});
		tfPesquisa.setBounds(137, 49, 373, 20);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
		JLabel lblPesquisa = new JLabel("Pesquisa:");
		lblPesquisa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPesquisa.setBounds(25, 52, 89, 14);
		getContentPane().add(lblPesquisa);
		pc = new PessoasControl(table, tfPesquisa);
	}
}
