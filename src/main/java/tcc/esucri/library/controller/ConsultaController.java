package tcc.esucri.library.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tcc.esucri.library.dao.AutorDAO;
import tcc.esucri.library.dao.LivroDAO;
import tcc.esucri.library.model.Autor;
import tcc.esucri.library.model.Livro;

public class ConsultaController implements Initializable {

	@FXML
	private ComboBox<Autor> comboAutores;
	@FXML
	private TableView<Livro> table;
	@FXML
	private TableColumn<Livro, String> colTitulo;
	@FXML
	private TableColumn<Livro, Autor> colAutor;

	private ObservableList<Livro> obsLivros = FXCollections.observableArrayList();

	private LivroDAO livroDAO;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		prepareTableView();

		livroDAO = new LivroDAO();

		ObservableList<Autor> autores = FXCollections.observableArrayList();
		autores.addAll(new AutorDAO().getAll());
		comboAutores.setItems(autores);
	}

	@FXML
	private void consultar() {
		obsLivros.clear();
		obsLivros.addAll(livroDAO.getByAutor(comboAutores.getValue()));
	}

	private void prepareTableView() {
		colTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
		colAutor.setCellValueFactory(new PropertyValueFactory<Livro, Autor>("autor"));

		colTitulo.setStyle("-fx-alignment: CENTER;");
		colAutor.setStyle("-fx-alignment: CENTER;");

		table.setItems(obsLivros);
	}

}
