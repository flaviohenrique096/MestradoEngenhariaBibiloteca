package DAO;

import java.sql.SQLException;

import modelo.Aluno;

public interface AlunoDAO {
	public Aluno buscaAluno (String ra) throws SQLException ;
}