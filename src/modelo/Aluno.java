package modelo;

import java.sql.SQLException;
import java.util.List;

import DAO.AlunoDAO;
import DAO.AlunoDAOImplJDBC;

public class Aluno {

	String RA;
	String nome;

	public Aluno(String ra, String nome) {
		super();
		this.RA = ra;
		this.nome = nome;
	}
	
	public Aluno(String ra) {
		super();
		this.RA = ra;
	}

	public Aluno verificaAluno() throws SQLException {
		AlunoDAO alunoDAO = new AlunoDAOImplJDBC();
		return alunoDAO.buscaAluno(this.RA);
	}
	
	public boolean verificaDebito() {
		Debito debito = new Debito(this);
		return debito.verificaDebito();
	}

	public boolean emprestar(List<Item> itens) throws SQLException {
		Emprestimo e = new Emprestimo(this, itens);
		return e.realizaEmprestimo();
	}

	public String getRA() {
		return RA;
	}

	public void setRA(String rA) {
		RA = rA;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}