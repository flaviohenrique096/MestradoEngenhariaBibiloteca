package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionFactory;
import modelo.Item;

public class ItemDAOImplJDBC implements ItemDAO {
	
	private Connection connection;
	
	public ItemDAOImplJDBC() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionarItem (int idEmprestimo, List<Item> itens) throws SQLException {
		
		String sql = "INSERT INTO item (idEmprestimo, dataDevolucao, idLivro) VALUES (?, ?, ?)";
		String sqlUpdate = "UPDATE livro SET disponivel = 1 WHERE idlivro = ?";

		try (
		    PreparedStatement stmtInsert = connection.prepareStatement(sql);
		    PreparedStatement stmtUpdate = connection.prepareStatement(sqlUpdate)
		) {
		    for (Item item : itens) {
		        stmtInsert.setInt(1, idEmprestimo);
		        java.sql.Date sqlDate = new java.sql.Date(item.getDataDevolucao().getTime());
		        stmtInsert.setDate(2, sqlDate);
		        stmtInsert.setInt(3, item.getLivro().getIdLivro());
		        stmtInsert.addBatch();
		    }
		    stmtInsert.executeBatch();

		    for (Item item : itens) {
		        stmtUpdate.setInt(1, item.getLivro().getIdLivro());
		        stmtUpdate.addBatch();
		    }
		    stmtUpdate.executeBatch();
		}
		
	}
	
	public void setConnection(Connection connection) {
	    this.connection = connection;
	}
	
	public void setAutoCommit(boolean b) throws SQLException {
	    this.connection.setAutoCommit(b);
	}	
}