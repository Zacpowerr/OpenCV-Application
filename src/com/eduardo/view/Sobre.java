package com.eduardo.view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Sobre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre frame = new Sobre();
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
	public Sobre() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/resources/icons8-about-filled-50.png")));
		setTitle("Sobre");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAutorEduardoKempfer = new JLabel("Autor: Eduardo Kempfer Bastos");
		lblAutorEduardoKempfer.setBounds(10, 11, 214, 14);
		contentPane.add(lblAutorEduardoKempfer);
		
		JLabel lblAlunoDa = new JLabel("Aluno da 3 fase do Curso de ADS na instituicao Senac SC");
		lblAlunoDa.setBounds(10, 36, 337, 14);
		contentPane.add(lblAlunoDa);
		
		JLabel lblTecnologiaUtilizada = new JLabel("Tecnologia Utilizada:");
		lblTecnologiaUtilizada.setBounds(10, 81, 166, 14);
		contentPane.add(lblTecnologiaUtilizada);
		
		JLabel lblOpencv = new JLabel("OpenCV");
		lblOpencv.setBounds(20, 106, 46, 14);
		contentPane.add(lblOpencv);
		
		JLabel lblJavaSwing = new JLabel("Java Swing");
		lblJavaSwing.setBounds(20, 131, 69, 14);
		contentPane.add(lblJavaSwing);
		
		JLabel lblContato = new JLabel("Contato: ");
		lblContato.setBounds(10, 236, 56, 14);
		contentPane.add(lblContato);
		
		JLabel lblEkempferhotmailcom = new JLabel("e_kempfer@hotmail.com");
		lblEkempferhotmailcom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				URL web = null;
				try {
					web = new URL("https://www.instagram.com/eduardokbastos/");
				
				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					web.openConnection();
					Desktop.getDesktop().browse(web.toURI());
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		lblEkempferhotmailcom.setBounds(76, 236, 148, 14);
		contentPane.add(lblEkempferhotmailcom);
		
		JLabel lblItext = new JLabel("IText");
		lblItext.setBounds(20, 156, 69, 14);
		contentPane.add(lblItext);
		
		JLabel lblMysqlConnector = new JLabel("mysql connector");
		lblMysqlConnector.setBounds(20, 181, 96, 14);
		contentPane.add(lblMysqlConnector);
	}
}
