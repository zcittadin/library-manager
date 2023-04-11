package tcc.esucri.library.controller.modal;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tcc.esucri.library.dao.AutorDAO;
import tcc.esucri.library.dao.LivroDAO;
import tcc.esucri.library.model.Autor;
import tcc.esucri.library.model.Livro;
import tcc.esucri.library.util.AlertUtil;

public class LivrosModalController implements Initializable {

	@FXML
	private TextField txtTitulo;
	@FXML
	private ComboBox<Autor> comboAutor;

	private Livro livro;

	private LivroDAO livroDAO;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		livroDAO = new LivroDAO();
		ObservableList<Autor> autores = FXCollections.observableArrayList();
		autores.addAll(new AutorDAO().getAll());
		comboAutor.setItems(autores);
	}

	@FXML
	private void save() {
		if (txtTitulo.getText().trim().isEmpty() || txtTitulo.getText() == null) {
			AlertUtil.makeWarning("Atenção", "Preencha um título para este livro.", null);
			txtTitulo.requestFocus();
			return;
		}

		if (comboAutor.getValue() == null) {
			AlertUtil.makeWarning("Atenção", "Informe o autor deste livro.", null);
			comboAutor.requestFocus();
			return;
		}

		if (livro == null) {
			livro = new Livro(0, txtTitulo.getText(), comboAutor.getValue());
			livroDAO.add(livro);
		} else {
			livro.setTitulo(txtTitulo.getText());
			livro.setAutor(comboAutor.getValue());
			livroDAO.update(livro);
		}

		cancel();
	}

	@FXML
	private void cancel() {
		Stage stage = (Stage) txtTitulo.getScene().getWindow();
		stage.close();
	}

	public void setContext(Livro livro) {
		this.livro = livro;
		txtTitulo.setText(livro.getTitulo());
		comboAutor.setValue(livro.getAutor());
	}

}
