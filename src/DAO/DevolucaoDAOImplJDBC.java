package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.ConnectionFactory;
import modelo.Emprestimo;

public class DevolucaoDAOImplJDBC implements DevolucaoDAO{

	private Connection connection;

	public DevolucaoDAOImplJDBC() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int realizarDevolucao(Emprestimo emprestimo) throws SQLException {
		String updateSql = "UPDATE livro " + "SET disponivel = 0 " + "WHERE idLivro IN ( " + "    SELECT idLivro "
				+ "    FROM item " + "    WHERE idEmprestimo = ? " + ");";

		String deleteSql = "DELETE FROM emprestimo WHERE idemprestimo = ?";
		connection.setAutoCommit(false);
		try (PreparedStatement updateStmt = connection.prepareStatement(updateSql);
				PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
			updateStmt.setInt(1, emprestimo.getIdEmprestimo());
			int updatedRows = updateStmt.executeUpdate();
			deleteStmt.setInt(1, emprestimo.getIdEmprestimo());
			int deletedRows = deleteStmt.executeUpdate();
			connection.commit();
			return updatedRows + deletedRows;
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} finally {

			connection.setAutoCommit(true);
		}
	}
}