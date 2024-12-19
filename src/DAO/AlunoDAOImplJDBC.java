package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import modelo.Aluno;
import modelo.Titulo;

public class AlunoDAOImplJDBC implements AlunoDAO{

	private Connection connection;

	public AlunoDAOImplJDBC() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public Aluno buscaAluno (String ra) throws SQLException {
		String sql = "SELECT * FROM aluno WHERE RA = ? ";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, ra);
		ResultSet rs = stmt.executeQuery();
		 if (rs.next()) {
             return new Aluno(rs.getString("RA"), rs.getString("Nome"));
         } else {

             return null;
         }		
	}
}