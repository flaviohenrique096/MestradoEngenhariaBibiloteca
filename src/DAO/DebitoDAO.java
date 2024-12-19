package DAO;

import java.sql.SQLException;

public interface DebitoDAO {
	public int registrarDebito(double multa, String RA) throws SQLException;
	public boolean verificaDebito(String RA);
}
