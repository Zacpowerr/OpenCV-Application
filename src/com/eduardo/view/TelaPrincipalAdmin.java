
package com.eduardo.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.eduardo.dao.PessoasDao;

public class TelaPrincipalAdmin extends JFrame {
	private CadastroCurso t1 = null;
	private CadastroEscola t2 = null;
	private JPanel contentPane;
	private static JDesktopPane desktopPane;
	// private TelaPrincipalAluno t =null;
	private PessoasDao pdao = new PessoasDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalAdmin frame = new TelaPrincipalAdmin();
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
	public TelaPrincipalAdmin() {
		setTitle("OpenCV Admin");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipalAdmin.class.getResource("/resources/icons8-webcam-26.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(719 + 100, 100, 719, 610);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastros = new JMenu("Gerenciamentos");
		mnCadastros.setIcon(new ImageIcon(TelaPrincipalAdmin.class.getResource("/resources/icons8-add.png")));
		menuBar.add(mnCadastros);

		JMenuItem mntmGerenciarCursos = new JMenuItem("Gerenciar Cursos");
		mntmGerenciarCursos.setIcon(new ImageIcon(TelaPrincipalAdmin.class.getResource("/resources/icons8-scholar.png")));
		mntmGerenciarCursos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				t1 = new CadastroCurso();
				desktopPane.removeAll();
				desktopPane.add(t1);
				t1.setBounds(0, 0, desktopPane.getWidth(), desktopPane.getHeight());
				t1.show();
			}
		});
		mnCadastros.add(mntmGerenciarCursos);

		JMenuItem mntmGerenciarEscolas = new JMenuItem("Gerenciar Escolas");
		mntmGerenciarEscolas.setIcon(new ImageIcon(TelaPrincipalAdmin.class.getResource("/resources/icons8-elementary-school.png")));
		mntmGerenciarEscolas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				t2 = new CadastroEscola();
				desktopPane.removeAll();
					desktopPane.add(t2);
					t2.setBounds(0, 0, desktopPane.getWidth(), desktopPane.getHeight());
					t2.show();
				

			}
		});
		mnCadastros.add(mntmGerenciarEscolas);

		JMenu mnDocumentos = new JMenu("Documentos");
		mnDocumentos.setIcon(new ImageIcon(TelaPrincipalAdmin.class.getResource("/resources/icons8-open-document.png")));
		menuBar.add(mnDocumentos);

		JMenu mnGerarRelatorio = new JMenu("Gerar Relatorio");
		mnDocumentos.add(mnGerarRelatorio);

		JMenuItem mntmPdf = new JMenuItem("PDF");
		mntmPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				pdao.GerarPDf();
			}
		});
		mntmPdf.setIcon(new ImageIcon(TelaPrincipalAdmin.class.getResource("/resources/icons8-report-file.png")));
		mnGerarRelatorio.add(mntmPdf);

		JMenuItem mntmTexto = new JMenuItem("Texto");
		mntmTexto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				pdao.GerarTexto();
			}
		});
		mntmTexto.setIcon(new ImageIcon(TelaPrincipalAdmin.class.getResource("/resources/icons8-type.png")));
		mnGerarRelatorio.add(mntmTexto);

		JMenuItem mntmPlanilha = new JMenuItem("Planilha");
		mntmPlanilha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				pdao.GerarExcel();
			}
		});
		mntmPlanilha.setIcon(new ImageIcon(TelaPrincipalAdmin.class.getResource("/resources/icons8-statistics-64.png")));
		mnGerarRelatorio.add(mntmPlanilha);
		getContentPane().setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 11, 683, 493);
		getContentPane().add(desktopPane);

	}
}
