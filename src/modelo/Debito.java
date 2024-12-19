package modelo;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import DAO.DebitoDAO;
import DAO.DebitoDAOImplJDBC;
//296
public class Debito {

	int idDebito;
	double valor;
	Date data;
	Aluno aluno;
	
	public Debito (Aluno RA) {
		this.aluno = RA ; 
	}
	
	public Debito () {
		super() ; 
	}

	public boolean verificaDebito() {
		DebitoDAO debitoDAO = new DebitoDAOImplJDBC();
		return debitoDAO.verificaDebito(this.aluno.getRA());
	}

	public int gerarDebito(String RA, double multa) throws SQLException {
		DebitoDAOImplJDBC debitoDAO = new DebitoDAOImplJDBC();
		return debitoDAO.registrarDebito(multa, RA);
	}

	public double calcularDebito(Date dataPrevista) {
		Date dataAtual = new Date();
		long diferencaMillis = dataAtual.getTime() - dataPrevista.getTime();
		long diasAtraso = TimeUnit.MILLISECONDS.toDays(diferencaMillis);
		double multaPorItemPorDia = 1.0; 
		double multaTotal = (diasAtraso > 0) ? diasAtraso * multaPorItemPorDia : 0.0;
		return multaTotal;
	}

	public int getIdDebito() {
		return idDebito;
	}

	public void setIdDebito(int idDebito) {
		this.idDebito = idDebito;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}