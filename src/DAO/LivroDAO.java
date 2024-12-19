package DAO;

import java.sql.SQLException;
import java.util.List;

import modelo.Livro;

public interface LivroDAO {
	
	public List<Livro> buscarLivros(String isbn) throws SQLException;

}
