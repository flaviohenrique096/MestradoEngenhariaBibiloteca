package DAO;

import java.sql.SQLException;

import modelo.Titulo;

public interface TituloDAO {
	
	public Titulo buscarTitulo(String isbn) throws SQLException ;

}