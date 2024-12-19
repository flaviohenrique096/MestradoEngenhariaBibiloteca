package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Aluno;
import modelo.Emprestimo;
import modelo.Item;
import modelo.Livro;

public class EmprestimoController {

	Aluno aluno;

	List<Item> itensEmprestimo;

	List<Livro> livrosBusca;

	List<Livro> livrosEmprestimo;

	@FXML
	private TextField textFieldISBN;

	@FXML
	private TextField textFieldRA;

	@FXML
	private Button buttonBuscarISBN;

	@FXML
	private TableView<Livro> tableViewLivrosDisponiveis;

	@FXML
	private TableColumn<Livro, String> colunaLivrosDisponiveisISBN;

	@FXML
	private TableColumn<Livro, String> colunaLivrosDisponiveisTitulo;

	@FXML
	private TableColumn<Livro, String> colunaLivrosDisponiveisExemplar;

	@FXML
	private TableView<Livro> tableViewLivrosEmprestimo;

	@FXML
	private TableColumn<Livro, String> colunaLivrosEmprestimoISBN;

	@FXML
	private TableColumn<Livro, String> colunaLivrosEmprestimoTitulo;

	@FXML
	private TableColumn<Livro, String> colunaLivrosEmprestimoExemplar;

	@FXML
	private void onActionBuscarLivro() {
		if (!textFieldISBN.getText().isEmpty()) {
			try {
				this.livrosBusca = new ArrayList<Livro>();
				this.livrosBusca = Livro.bucaLivro(this.textFieldISBN.getText());
				this.atualizarTableViewBuscaLivro();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		} else { 
			JOptionPane.showMessageDialog(null, "Digite o ISBN do Livro!", "Atenção",
					JOptionPane.WARNING_MESSAGE);
		}	
	}

	private void atualizarTableViewBuscaLivro() {
		colunaLivrosDisponiveisISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
		colunaLivrosDisponiveisTitulo.setCellValueFactory(new PropertyValueFactory<>("nomeLivro"));
		colunaLivrosDisponiveisExemplar.setCellValueFactory(new PropertyValueFactory<>("exemplar"));
		tableViewLivrosDisponiveis.setItems(FXCollections.observableArrayList(this.livrosBusca));
	}

	private void atualizarTableViewEmprestimo() {
		colunaLivrosEmprestimoISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
		colunaLivrosEmprestimoTitulo.setCellValueFactory(new PropertyValueFactory<>("nomeLivro"));
		colunaLivrosEmprestimoExemplar.setCellValueFactory(new PropertyValueFactory<>("exemplar"));
		tableViewLivrosEmprestimo.setItems(FXCollections.observableArrayList(livrosEmprestimo));
	}

	@FXML
	private void onActionAdicionarLivro() {
		this.adicionarLivro();
		
	}

	@FXML
	private void onActionEmprestarLivro() {
		try {
			if (!textFieldRA.getText().isEmpty()) {
				this.aluno = new Aluno(textFieldRA.getText());
				this.aluno = aluno.verificaAluno();
				if (this.aluno != null) {
					if (!this.aluno.verificaDebito()) {
						if (this.itensEmprestimo.size() != 0) {
							this.aluno.emprestar(itensEmprestimo);
							this.livrosBusca.clear();
							this.itensEmprestimo.clear();
							this.livrosEmprestimo.clear();
							this.textFieldRA.clear();
							this.textFieldISBN.clear();
							this.atualizarTableViewEmprestimo();
							this.atualizarTableViewBuscaLivro();
							JOptionPane.showMessageDialog(null,
									"Empréstimo realizado para " + this.aluno.getNome() + "!", "Empréstimo",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Nenhum livro selecionado para empréstimo.", "Atenção",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Aluno possui debitos em aberto.", "Atenção",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Aluno não cadastrado!", "Não encontrado",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Digite o RA do Aluno.", "Atenção", JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void adicionarLivro () {
		this.livrosEmprestimo.add(tableViewLivrosDisponiveis.getSelectionModel().getSelectedItem());
		this.atualizarTableViewEmprestimo();
		Item item = new Item(tableViewLivrosDisponiveis.getSelectionModel().getSelectedItem());
		this.itensEmprestimo.add(item);
		this.textFieldISBN.setText("");
		this.livrosBusca.clear();
		this.atualizarTableViewBuscaLivro();
	}

	@FXML
	public void initialize() {
		this.livrosEmprestimo = new ArrayList<Livro>();
		this.itensEmprestimo = new ArrayList<Item>();
	}
}