package com.eduardo.dao;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eduardo.factory.Dao;
import com.eduardo.interfaces.DaoI;
import com.eduardo.model.Pessoas;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class PessoasDao extends Dao implements DaoI<Pessoas> {

	@Override
	public boolean cadastrar(Pessoas obj) {

		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("insert into Pessoas (id) values(default)");
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean editar(Pessoas obj) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"update Pessoas set nome=?, dataNascimento=?, email=?,telefone=?,escola=? where id=?");
			stmt.setString(1, obj.getNome());
			stmt.setDate(2, obj.getDataNasc());
			stmt.setString(3, obj.getEmail());
			stmt.setString(4, obj.getTelefone());
			stmt.setInt(5, obj.getEscola().getId());
			stmt.setInt(6, obj.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	public boolean cadastrarCurso(Pessoas obj) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("insert into curso_pessoa (idCurso,idPessoa)values(?,?)");
			stmt.setInt(2, obj.getId());
			stmt.setInt(1, obj.getCursoI().getId());
			System.out.println(obj.getId() + " - " + obj.getCursoI().getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean excluir(Pessoas obj) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("delete from Pessoas where id=?");
			stmt.setInt(1, obj.getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Pessoas> listar() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"select p.id,p.dataNascimento,p.email,p.telefone,p.nome, e.nome from pessoas as p inner join escola as e on p.escola = e.id order by p.id");

			// SELECT id,nome,dataNascimento,email,telefone,escola FROM Pessoas
			ResultSet result = stmt.executeQuery();
			List<Pessoas> listPessoas = new ArrayList<>();
			while (result.next()) {
				Pessoas pessoas = new Pessoas();

				pessoas.setId(result.getInt("p.id"));

				pessoas.setNome(result.getString("p.nome"));
				pessoas.setDataNasc(result.getDate("p.dataNascimento"));
				pessoas.setEmail(result.getString("p.email"));
				pessoas.getEscola().setNome(result.getString("e.nome"));
				pessoas.setTelefone(result.getString("p.telefone"));

				listPessoas.add(pessoas);
			}
			return listPessoas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int pegaridMAX() {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement("SELECT MAX(id) as id FROM Pessoas");
			ResultSet result = stmt.executeQuery();
			Pessoas pessoas = new Pessoas();
			if (result.next()) {

				pessoas.setId(result.getInt("id"));
			}
			return pessoas.getId();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}

	}

	public List<Pessoas> buscar(String nome) {
		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"select p.id,p.dataNascimento,p.email,p.telefone,p.nome, e.nome from pessoas as p inner join escola as e on p.escola = e.id where p.nome like ? or e.nome like ?");
			stmt.setString(1, nome + "%");
			stmt.setString(2, nome + "%");
			ResultSet result = stmt.executeQuery();
			List<Pessoas> listPessoas = new ArrayList<>();
			while (result.next()) {
				Pessoas pessoas = new Pessoas();

				pessoas.setId(result.getInt("p.id"));

				pessoas.setNome(result.getString("p.nome"));
				pessoas.setDataNasc(result.getDate("p.dataNascimento"));
				pessoas.setEmail(result.getString("p.email"));
				pessoas.getEscola().setNome(result.getString("e.nome"));
				pessoas.setTelefone(result.getString("p.telefone"));

				listPessoas.add(pessoas);
			}
			return listPessoas;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void GerarPDf() {
		Document doc = new Document();
		List<Pessoas> listPessoas = listarForm();
		String arquivoPDF = "Relatório de Alunos.pdf";
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPDF));
			doc.open();
			Paragraph para = new Paragraph("Relatório de Alunos");
			para.setAlignment(1);
			doc.add(para);
			para = new Paragraph(" ");
			doc.add(para);
			PdfPTable table = new PdfPTable(6);
			PdfPCell cell1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell cell2 = new PdfPCell(new Paragraph("Data de Nascimento"));
			PdfPCell cell3 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell cell4 = new PdfPCell(new Paragraph("Email"));
			PdfPCell cell5 = new PdfPCell(new Paragraph("Escola"));
			PdfPCell cell6 = new PdfPCell(new Paragraph("Curso de Interesse"));
			
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);

			for (Pessoas p : listPessoas) {
				cell1 = new PdfPCell(new Paragraph(p.getNome()));
				cell2 = new PdfPCell(new Paragraph(p.getDataNasc().toString()));
				cell3 = new PdfPCell(new Paragraph(p.getTelefone()));
				cell4 = new PdfPCell(new Paragraph(p.getEmail()));
				cell5 = new PdfPCell(new Paragraph(p.getEscola().getNome()));
				cell6 = new PdfPCell((new Paragraph(p.getCursoI().getNome())));
				System.out.println("teste");
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
				table.addCell(cell4);
				table.addCell(cell5);
				table.addCell(cell6);
			}
			doc.add(table);
			doc.close();
			Desktop.getDesktop().open(new File(arquivoPDF));

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void GerarTexto() {
		List<Pessoas> listPessoas = listarForm();
		File arquivo = new File("Relatorio.txt");
		try {
			arquivo.createNewFile();
			FileReader leitura = new FileReader(arquivo);
			FileWriter escrever = new FileWriter(arquivo);
			BufferedWriter escre = new BufferedWriter(escrever);
			for (Pessoas p : listPessoas) {
				System.out.println(p.getId());
				System.out.println(p.getNome());
				escrever.write("+--Resultado--+%\n");
				escre.newLine();
				escre.write("Pessoa-" + p.getId());
				
				escre.write("  ");
				escre.write(p.getNome());
				escre.write(" ");
				
				escre.write(p.getDataNasc().toString());
				escre.write("  ");
				escre.write(p.getTelefone());
				escre.write("  ");
				escre.write(p.getEmail());
				escre.write("  ");
				escre.write(p.getEscola().getNome());
				escre.write("Curso de Interesse- "+p.getCursoI().getNome());
				escre.newLine();
			}
			escre.flush();
			escre.flush();
			Desktop.getDesktop().open(arquivo);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void GerarExcel() {
		List<Pessoas> listaPessoas = listarForm();
		try {
			String filename = "C:\\Users\\Aluno\\Desktop\\file.xls";
		
			
			WritableWorkbook workbook = Workbook.createWorkbook(new File(filename));
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			Label aluno = new Label(0, 0, "Aluno");
			sheet.addCell(aluno);
			Label idade = new Label(1, 0, "Idade");
			sheet.addCell(idade);
			Label escola = new Label(2, 0, "Escola");
			sheet.addCell(escola);
			Label telefone = new Label(3, 0, "Telefone");
			sheet.addCell(telefone);
			Label email = new Label(4, 0, "Email");
			sheet.addCell(email);
			Label curso = new Label(5, 0, "Curso de interesse");
			sheet.addCell(curso);
			
			int linha = 1;
			int coluna = 0;
			for (Pessoas p : listaPessoas) {
				if (coluna > 5) {
					linha++;
					coluna = 0;
				}else{
					System.out.println("tudo certo");
				}
				Label nome = new Label(coluna, linha, p.getNome());
				sheet.addCell(nome);
	
				coluna++;
	
				Label idadeA = new Label(coluna, linha, String.valueOf(p.getDataNasc()));
				sheet.addCell(idadeA);
				coluna++;
				Label escolaA = new Label(coluna, linha, p.getEscola().getNome());
				sheet.addCell(escolaA);
				coluna++;
				Label telefoneA = new Label(coluna, linha, p.getTelefone());
				sheet.addCell(telefoneA);
				coluna++;
				Label emailA = new Label(coluna, linha, p.getEmail());
				sheet.addCell(emailA);
				coluna++;
				Label cursoA = new Label(coluna, linha, p.getCursoI().getNome());
				sheet.addCell(cursoA);
				coluna++;
			}
			workbook.write();
			workbook.close();
			
			// Desktop.getDesktop().open(workbook);
		} catch (Exception e) {
		}
	
	}

	public List<Pessoas> listarForm() {

		try {
			PreparedStatement stmt;
			stmt = conexao.prepareStatement(
					"select p.id,p.nome,p.dataNascimento,p.email,e.nome,p.telefone,c.nome from pessoas as p inner join  curso_pessoa as cp on cp.idPessoa = p.id inner join escola as e on e.id = p.escola inner join Cursos as c on c.id = cp.idCurso;");
			ResultSet res = stmt.executeQuery();
			List<Pessoas> listPessoas = new ArrayList<>();
			while(res.next()) {
				Pessoas p = new Pessoas();
				p.setId(res.getInt("p.id"));
				p.setDataNasc(res.getDate("p.dataNascimento"));
				p.setNome(res.getString("p.nome"));
				p.getEscola().setNome(res.getString("e.nome"));
				p.setTelefone(res.getString("p.telefone"));
				p.setEmail(res.getString("p.email"));
				p.getCursoI().setNome(res.getString("c.nome"));
				listPessoas.add(p);

			}
			return listPessoas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
