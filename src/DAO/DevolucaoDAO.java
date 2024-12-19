package DAO;

import java.sql.SQLException;

import modelo.Emprestimo;

public interface DevolucaoDAO {
	public int realizarDevolucao(Emprestimo emprestimo) throws SQLException ; 
}