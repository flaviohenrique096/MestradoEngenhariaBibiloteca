package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;
import modelo.Emprestimo;

public class EmprestimoDAOImplJDBC implements EmprestimoDAO<Emprestimo>{

	private Connection connection;
	
	public EmprestimoDAOImplJDBC()  {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int criar(Emprestimo t) throws SQLException {
		String sql = "INSERT INTO emprestimo (RA,dataPrevista) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, t.getAluno().getRA());
			java.sql.Date sqlDate = new java.sql.Date(t.getDataPrevista().getTime());
			stmt.setDate(2, sqlDate);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new SQLException("Falha ao criar empréstimo, ID não gerado.");
			}
		}
	}

	@Override
	public List<Emprestimo> ler(String RA) throws SQLException {
		List<Emprestimo> emprestimos = new ArrayList<>();
		String sql = "SELECT * FROM emprestimo WHERE RA = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, RA);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Emprestimo e = new Emprestimo(rs.getInt("idemprestimo"), rs.getDate("dataCriacao"),
					rs.getDate("dataPrevista"));
			emprestimos.add(e);
		}
		rs.close();
		stmt.close();
		return emprestimos.isEmpty() ? null : emprestimos;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setAutoCommit(boolean b) throws SQLException {
		this.connection.setAutoCommit(b);
	}	
}