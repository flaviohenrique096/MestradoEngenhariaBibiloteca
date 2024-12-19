package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import modelo.Item;

public interface ItemDAO {
	//VER COMO MELHORAR ISTO
	public void setConnection(Connection connection);
	public void adicionarItem (int idEmprestimo, List<Item> itens) throws SQLException;
}
