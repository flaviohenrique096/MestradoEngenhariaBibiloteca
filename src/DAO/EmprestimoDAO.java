package DAO;

import java.sql.SQLException;
import java.util.List;

public interface EmprestimoDAO<Emprestimo> {
	 public int criar(Emprestimo emprestimo) throws SQLException;
	 public List<Emprestimo> ler(String RA) throws SQLException;
}