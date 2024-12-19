package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionFactory;
import modelo.Titulo;

public class TituloDAOImplJDBC implements TituloDAO {
	
	private Connection connection;

	public TituloDAOImplJDBC() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Titulo buscarTitulo(String isbn) throws SQLException {
		String sql = "SELECT * FROM titulo WHERE isbn = ? ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, isbn);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Titulo titulo = new Titulo(rs.getInt("idtitulo"),rs.getInt("prazo"),rs.getString("isbn"),rs.getString("edicao"),rs.getString("ano"),rs.getString("nomeLivro"));
		return titulo;		
	}	
}