package com.eduardo.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.eduardo.control.PessoasControl;
import com.eduardo.factory.Suporte;
import com.eduardo.factory.Treino;

public class TelaPrincipalAluno extends JFrame {
	private JPanel contentPane;
	private static JDesktopPane desktopPane;
	private Cadastro t1 = new Cadastro();
	private BemVindo b = null;
	private PessoasControl pc = null;

	private Login login = null;
	private ListarAlunos lista = new ListarAlunos();
	private Sobre  sobre = new Sobre();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalAluno frame = new TelaPrincipalAluno();
					// Suporte.iniciar(desktopPane);
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
	public TelaPrincipalAluno() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipalAluno.class.getResource("/resources/icons8-webcam-26.png")));
		setResizable(false);
		setTitle("OpenCV Aluno");
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if (t1.isClosed() && lista.isClosed()) {

					desktopPane.add(b);
					b.setBounds(0, 0, desktopPane.getWidth(), desktopPane.getHeight());
					b.show();

				}
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 693);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAluno = new JMenu("Aluno");
		mnAluno.setIcon(new ImageIcon(TelaPrincipalAluno.class.getResource("/resources/icons8-people.png")));
		menuBar.add(mnAluno);

		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.setIcon(new ImageIcon(TelaPrincipalAluno.class.getResource("/resources/icons8-add-male-user.png")));
		mntmCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				desktopPane.removeAll();
				t1 = new Cadastro();
				desktopPane.add(t1);
				t1.setBounds(0, 0, desktopPane.getWidth(), t1.getHeight());
				t1.show();
				if(t1.isVisible()) {
					pc.idsalvarAction();
				}
				desktopPane.repaint();
				Suporte.iniciar();

			}
		});
		mnAluno.add(mntmCadastrar);

		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				desktopPane.removeAll();
			
				lista = new ListarAlunos();
				desktopPane.add(lista);
				lista.setBounds(0, 0, lista.getWidth(), lista.getHeight());
				lista.show();
				desktopPane.repaint();

			}
		});
		mntmListar.setIcon(new ImageIcon(TelaPrincipalAluno.class.getResource("/resources/icons8-list.png")));
		mnAluno.add(mntmListar);

		JMenu mnDocumentos = new JMenu("Documentos");
		mnDocumentos.setIcon(new ImageIcon(TelaPrincipalAluno.class.getResource("/resources/icons8-report-file.png")));
		menuBar.add(mnDocumentos);

		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				if(sobre.isVisible()) {
					sobre.dispose();
				}
				
				sobre.setVisible(true);
				
			}
		});
		mntmSobre.setIcon(new ImageIcon(TelaPrincipalAluno.class.getResource("/resources/icons8-x-file.png")));
		mnDocumentos.add(mntmSobre);

		JMenu mnProfessor = new JMenu("Professor");
		mnProfessor.setIcon(new ImageIcon(TelaPrincipalAluno.class.getResource("/resources/icons8-teacher.png")));
		menuBar.add(mnProfessor);

		JMenuItem mntmCadastrarEscolascursos = new JMenuItem("Login");
		mntmCadastrarEscolascursos
				.setIcon(new ImageIcon(TelaPrincipalAluno.class.getResource("/resources/icons8-pincode-keyboard.png")));
		mntmCadastrarEscolascursos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				login = new Login();
				login.setVisible(true);

			}
		});
		mnProfessor.add(mntmCadastrarEscolascursos);
		
		JMenuItem mntmReconhecimento = new JMenuItem("Reconhecimento(testes)");
		mntmReconhecimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					Treino.iniciar(); 
				
			}
		});
		mnProfessor.add(mntmReconhecimento);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 0, 679, 587);
		contentPane.add(desktopPane);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				pc = new PessoasControl(null, null, null, null, null, null, null, null, null, null,null);

				b = new BemVindo();
				desktopPane.add(b);
				b.setBounds(0, 0, desktopPane.getWidth(), desktopPane.getHeight());
				b.show();

			}
		});
	}
}
