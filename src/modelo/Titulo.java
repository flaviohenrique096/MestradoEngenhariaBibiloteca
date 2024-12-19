package modelo;

import java.sql.SQLException;

import DAO.TituloDAO;
import DAO.TituloDAOImplJDBC;

public class Titulo {
	
	private int idtitulo;
	private int prazo;
	private String isbn;
	private String edicao;
	private String ano;
	private String nomeLivro;
	
	public static Titulo bucaTitulo(String isbn) throws SQLException {
		TituloDAO tituloDAO = new TituloDAOImplJDBC();
		return tituloDAO.buscarTitulo(isbn);
	}
	
	public Titulo(int idtitulo, int prazo, String isbn, String edicao, String ano, String nomeLivro) {
		super();
		this.idtitulo = idtitulo;
		this.prazo = prazo;
		this.isbn = isbn;
		this.edicao = edicao;
		this.ano = ano;
		this.nomeLivro = nomeLivro ; 
	}
	
	public int getIdtitulo() {
		return idtitulo;
	}

	public void setIdtitulo(int idtitulo) {
		this.idtitulo = idtitulo;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
}