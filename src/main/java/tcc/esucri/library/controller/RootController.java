package tcc.esucri.library.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class RootController implements Initializable {

	@FXML
	private BorderPane mainPane;

	private final static String basePath = "/view/";
	private final static String extension = ".fxml";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadScreen("Consulta");
	}

	@FXML
	void toConsulta() {
		loadScreen("Consulta");
	}

	@FXML
	void toLivros() {
		loadScreen("Livros");
	}

	@FXML
	void toAutores() {
		loadScreen("Autores");
	}

	private void loadScreen(String screen) {
		Parent root = null;
		final String path = basePath.concat(screen).concat(extension);
		try {
			root = FXMLLoader.load(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainPane.setCenter(root);
	}

}
