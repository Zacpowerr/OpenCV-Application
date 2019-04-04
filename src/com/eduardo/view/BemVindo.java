package com.eduardo.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class BemVindo extends JInternalFrame  {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BemVindo frame = new BemVindo();
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
	public BemVindo() {

		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 681, 379);
		getContentPane().setLayout(null);

		JLabel lblSejaBemVindo = new JLabel("Seja bem vindo a vis\u00E3o computacional");
		lblSejaBemVindo.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSejaBemVindo.setBounds(95, 43, 504, 46);
		getContentPane().add(lblSejaBemVindo);

		JLabel lblEscolhaOqueVoce = new JLabel("Escolha o que voc\u00EA deseja fazer acima");
		lblEscolhaOqueVoce.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEscolhaOqueVoce.setBounds(162, 147, 370, 22);
		getContentPane().add(lblEscolhaOqueVoce);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(BemVindo.class.getResource("/resources/icons8-upward-arrow.png")));
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
		label.setBounds(10, 11, 65, 58);
		getContentPane().add(label);

	}
}
