package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import jdbc.ConnectionFactory;

public class DebitoDAOImplJDBC implements DebitoDAO{

	private Connection connection;

	public DebitoDAOImplJDBC() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int registrarDebito(double multa, String RA) throws SQLException {
		String sql = "INSERT INTO debito (valor,RA) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setDouble(1, multa);
			stmt.setString(2, RA);
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
	public boolean verificaDebito(String RA) {
	    String sql = "SELECT * FROM debito WHERE RA = ?";
	    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, RA);
	        try (ResultSet rs = stmt.executeQuery()) {
	            return rs.next();
	        }
	    } catch (SQLException e) {

	        e.printStackTrace();
	        return false;
	    }
	}
}