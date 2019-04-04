package com.eduardo.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.eduardo.control.LoginControl;
import com.eduardo.dao.LoginDao;

public class Login extends JFrame {

	private JTextField tfLogin;
	private com.eduardo.model.Login login;
	private LoginDao logindao;
	private LoginControl logincontrol;
	private TelaPrincipalAdmin t1 = null;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyChar() == 13) {
					boolean res = logincontrol.verificarAction();
					if (res) {
						t1 = new TelaPrincipalAdmin();
						t1.setVisible(true);
						Login.this.dispose();

					}
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/icons8-password-32.png")));
		setTitle("Login");
		setBounds(150, 150, 251, 228);
		getContentPane().setLayout(null);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(29, 28, 46, 14);
		getContentPane().add(lblLogin);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(29, 91, 46, 14);
		getContentPane().add(lblSenha);

		tfLogin = new JTextField();
		tfLogin.setBounds(39, 53, 148, 20);
		getContentPane().add(tfLogin);
		tfLogin.setColumns(10);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean res = logincontrol.verificarAction();
				if (res) {
					t1 = new TelaPrincipalAdmin();
					t1.setVisible(true);
					Login.this.dispose();

				}

			}
		});
		btnEntrar.setBounds(73, 166, 89, 23);
		getContentPane().add(btnEntrar);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() ==10) {
					boolean res = logincontrol.verificarAction();
					if (res) {
						t1 = new TelaPrincipalAdmin();
						t1.setVisible(true);
						Login.this.dispose();

					}

				}
			}
		});

		passwordField.setBounds(39, 111, 148, 20);
		getContentPane().add(passwordField);
		logincontrol = new LoginControl(tfLogin, passwordField, logindao, login);
	}
}
