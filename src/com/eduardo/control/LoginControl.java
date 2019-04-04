package com.eduardo.control;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.eduardo.dao.LoginDao;
import com.eduardo.model.Login;

public class LoginControl {
	private JTextField tfLogin;
	private JPasswordField passwordField;
	private Login login = null;
	private LoginDao logindao = null;

	public LoginControl(JTextField tfLogin, JPasswordField passwordField, LoginDao logindao, Login login) {
		super();
		this.tfLogin = tfLogin;
		this.passwordField = passwordField;
		this.logindao = new LoginDao();
		this.login = login;
	}

	public boolean verificarAction() {
		String login = tfLogin.getText();
		String senha = new String(passwordField.getPassword());
		boolean res = logindao.verifica(login, senha);
		if (res) {
			JOptionPane.showMessageDialog(null, "Entrando...");
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Acesso negado.");
			return false;
		}

	}
}
