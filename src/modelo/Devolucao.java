package modelo;

import java.sql.SQLException;
import java.util.Date;

import DAO.DevolucaoDAO;
import DAO.DevolucaoDAOImplJDBC;

public class Devolucao {
	
	private Date dataDevolucao ;
	
	public static int realizarDevolucao (Emprestimo emprestimo) throws SQLException {
		DevolucaoDAO devolucaoDAO = new DevolucaoDAOImplJDBC();
		return devolucaoDAO.realizarDevolucao(emprestimo);
	}
}