package controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Debito;
import modelo.Devolucao;
import modelo.Emprestimo;

public class DevolucaoController {

	List<Emprestimo> listaEmprestimos;

	@FXML
	private TextField textFieldRA;

	@FXML
	private TableView<Emprestimo> tableViewEmprestimo;

	@FXML
	private TableColumn<Emprestimo, String> colunaLivrosEmprestimo;

	@FXML
	private TableColumn<Emprestimo, String> colunaLivrosDataEmprestimo;

	@FXML
	private TableColumn<Emprestimo, String> colunaLivrosDataDevolucao;

	@FXML
	private void onActionBuscaEmprestimo() {
		try {
			this.listaEmprestimos = Emprestimo.buscaEmprestimoPorRA(textFieldRA.getText());
			if (listaEmprestimos == null) {
				JOptionPane.showMessageDialog(null, "Nenhum emprestimo encontrado!", "Não encontrado",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				this.atualizarTableViewEmprestimo();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void onActionDevolver() {
		try {
			Emprestimo emprestimo = this.tableViewEmprestimo.getSelectionModel().getSelectedItem();
			if (emprestimo.getDataPrevista().compareTo(new Date()) < 0) {
				Debito d = new Debito();
				double multa = d.calcularDebito(emprestimo.getDataPrevista());
				d.gerarDebito(textFieldRA.getText(), multa);
				JOptionPane.showMessageDialog(null, "Multa de R$" + multa, "Item está em atraso.",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (Devolucao.realizarDevolucao(this.tableViewEmprestimo.getSelectionModel().getSelectedItem()) > 0) {
				JOptionPane.showMessageDialog(null, "Devolução realizada com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void atualizarTableViewEmprestimo() {
		colunaLivrosEmprestimo.setCellValueFactory(new PropertyValueFactory<>("idEmprestimo"));
		colunaLivrosDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
		colunaLivrosDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataPrevista"));
		tableViewEmprestimo.setItems(FXCollections.observableArrayList(this.listaEmprestimos));
	}
}