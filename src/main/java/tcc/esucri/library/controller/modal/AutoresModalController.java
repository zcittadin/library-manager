package tcc.esucri.library.controller.modal;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tcc.esucri.library.dao.AutorDAO;
import tcc.esucri.library.model.Autor;
import tcc.esucri.library.util.AlertUtil;

public class AutoresModalController implements Initializable {

	@FXML
	private TextField txtNome;

	private Autor autor;

	private AutorDAO autorDAO;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		autorDAO = new AutorDAO();
	}

	@FXML
	private void save() {
		if (txtNome.getText().trim().isEmpty() || txtNome.getText() == null) {
			AlertUtil.makeWarning("Atenção", "Preencha um nome para identificar este autor.", null);
			txtNome.requestFocus();
			return;
		}
		if (autor == null) {
			Autor autor = new Autor(0, txtNome.getText(), null);
			autorDAO.add(autor);
		} else {
			autor.setNome(txtNome.getText());
			autorDAO.update(autor);
		}
		cancel();
	}

	@FXML
	private void cancel() {
		Stage stage = (Stage) txtNome.getScene().getWindow();
		stage.close();
	}

	public void setContext(Autor autor) {
		this.autor = autor;
		txtNome.setText(autor.getNome());
	}

}
