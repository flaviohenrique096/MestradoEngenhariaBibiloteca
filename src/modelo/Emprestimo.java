package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import DAO.EmprestimoDAOImplJDBC;
import DAO.ItemDAO;
import DAO.EmprestimoDAO;
import DAO.ItemDAOImplJDBC;
import jdbc.ConnectionFactory;

public class Emprestimo {

	private int idEmprestimo;
	private Date dataEmprestimo = new Date();
	private Date dataPrevista = new Date();
	private Date data_aux = new Date();
	private Aluno aluno;
	private List<Item> item = new ArrayList<Item>();
	private int emprestimo = 0; // numeros de livros emprestado

	public Emprestimo(Aluno aluno, List<Item> item) {
		super();
		this.aluno = aluno;
		this.item = item;
	}

	public Emprestimo(int idEmprestimo, Date dataEmprestimo, Date dataPrevista) {
		this.idEmprestimo = idEmprestimo;
		this.dataEmprestimo = dataEmprestimo;
		this.dataPrevista = dataPrevista;
	}

	public boolean realizaEmprestimo() throws SQLException {
		Connection connection = null;
		try {
			ItemDAO itemDAO = new ItemDAOImplJDBC();
			EmprestimoDAOImplJDBC emprestimoDAO = new EmprestimoDAOImplJDBC();
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			emprestimoDAO.setConnection(connection);
			itemDAO.setConnection(connection);
			this.dataPrevista = this.CalculaDataDevolucao();
			int idEmprestimo = emprestimoDAO.criar(this);
			itemDAO.adicionarItem(idEmprestimo, item);
			connection.commit();
			return true;

		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			e.printStackTrace();
			return false;

		} finally {
			
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException closeEx) {
					closeEx.printStackTrace();
				}
			}
		}

	}

	public static List<Emprestimo> buscaEmprestimoPorRA(String RA) throws SQLException {
		EmprestimoDAO <Emprestimo> emprestimoDAO = new EmprestimoDAOImplJDBC();
		return emprestimoDAO.ler(RA);
	}

	private Date CalculaDataDevolucao() {
		Date date = new Date();
		for (int j = 0; j < item.size(); j++) {
			data_aux = item.get(j).calculaDataDevolucao(date);
			if (dataPrevista.compareTo(data_aux) < 0)
				dataPrevista = data_aux;
		}
		if (item.size() > -2) {
			int tam = item.size() - 2;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dataPrevista);
			calendar.add(Calendar.DATE, (tam * 2));
			dataPrevista = calendar.getTime();
		}
		for (int j = 0; j < item.size(); j++)
			item.get(j).setDataDevolucao(dataPrevista);

		return dataPrevista;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Date getData_aux() {
		return data_aux;
	}

	public void setData_aux(Date data_aux) {
		this.data_aux = data_aux;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public int getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(int emprestimo) {
		this.emprestimo = emprestimo;
	}

	public int getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(int idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}
}