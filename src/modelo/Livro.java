package modelo;

import java.sql.SQLException;
import java.util.List;

import DAO.LivroDAO;
import DAO.LivroDAOImplJDBC;


public class Livro {
	
	int idLivro;
	boolean disponivel;
	int exemplar; 
	Titulo titulo;
	
	public Livro () {
		
	}
	
	public Livro(int idLivro, boolean disponivel, int exemplar, Titulo titulo) {
		super();
		this.idLivro = idLivro; 
		this.disponivel = disponivel;
		this.exemplar = exemplar;
		this.titulo = titulo;
	}

	public static List<Livro> bucaLivro(String isbn) throws SQLException {
		LivroDAO livroDAO = new LivroDAOImplJDBC();
		List<Livro> l =  livroDAO.buscarLivros(isbn);
		return l ; 
	}
	
	public int verPrazo() {
		return titulo.getPrazo();
	}
	
	public boolean verificaDisponiblidade()
	{  
		return disponivel;
	   
	}
	
	public int getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public int getExemplar() {
		return exemplar;
	}

	public void setExemplar(int exemplar) {
		this.exemplar = exemplar;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}
	
	public String getNomeLivro() {
        return titulo != null ? titulo.getNomeLivro() : null;
    }
	
	public String getISBN() {
        return titulo != null ? titulo.getIsbn() : null;
    }
}
