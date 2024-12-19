package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import modelo.Livro;
import modelo.Titulo;

public class LivroDAOImplJDBC implements LivroDAO {

	private Connection connection;

	public LivroDAOImplJDBC() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public List<Livro> buscarLivros(String isbn) throws SQLException {
		
		List<Livro> livros = new ArrayList<Livro>();
		String sql = "SELECT l.idLivro, l.exemplar, l.disponivel,t.prazo, t.isbn, t.edicao, t.ano, t.nomeLivro,  t.idtitulo "
				+ "FROM livro AS l "
				+ "LEFT JOIN titulo AS t "
				+ "ON l.idTitulo = t.idtitulo "
				+ "WHERE t.isbn = ? AND l.disponivel = 0";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, isbn);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			
			Titulo t = new Titulo(rs.getInt("idtitulo"), rs.getInt("prazo"), rs.getString("isbn"), rs.getString("edicao"), rs.getString("ano"), rs.getString("nomeLivro"));
			Livro l = new Livro(rs.getInt("idLivro"), rs.getBoolean("disponivel"),rs.getInt("exemplar"),t);
			livros.add(l);
		}		
		
		return livros;	 	
	}	
}