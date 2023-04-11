package tcc.esucri.library.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import tcc.esucri.library.controller.modal.AutoresModalController;
import tcc.esucri.library.dao.AutorDAO;
import tcc.esucri.library.model.Autor;
import tcc.esucri.library.util.AlertUtil;

public class AutoresController implements Initializable {

	@FXML
	private TableView<Autor> table;
	@FXML
	private TableColumn<Autor, String> colNome;
	@FXML
	private TableColumn<Autor, Node> colEdit;
	@FXML
	private TableColumn<Autor, Node> colRemove;

	private AutorDAO autorDAO;

	private ObservableList<Autor> obsAutores = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepareTableView();
		autorDAO = new AutorDAO();
		obsAutores.addAll(autorDAO.getAll());
	}

	@FXML
	private void addAutor() {
		try {
			Stage stage;
			Parent root;
			stage = new Stage();
			URL url = getClass().getResource("/view/modal/AutoresModal.fxml");
			FXMLLoader fxmlloader = new FXMLLoader();
			fxmlloader.setLocation(url);
			fxmlloader.setBuilderFactory(new JavaFXBuilderFactory());
			root = (Parent) fxmlloader.load(url.openStream());
			stage.setScene(new Scene(root));
			stage.setTitle("Cadastro de autores");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(table.getScene().getWindow());
			stage.setResizable(false);
			stage.showAndWait();
			obsAutores.clear();
			obsAutores.addAll(autorDAO.getAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void editAutor(Autor autor) {
		try {
			Stage stage;
			Parent root;
			stage = new Stage();
			URL url = getClass().getResource("/view/modal/AutoresModal.fxml");
			FXMLLoader fxmlloader = new FXMLLoader();
			fxmlloader.setLocation(url);
			fxmlloader.setBuilderFactory(new JavaFXBuilderFactory());
			root = (Parent) fxmlloader.load(url.openStream());
			((AutoresModalController) fxmlloader.getController()).setContext(autor);
			stage.setScene(new Scene(root));
			stage.setTitle("Edição de autores");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(table.getScene().getWindow());
			stage.setResizable(false);
			stage.showAndWait();
			obsAutores.clear();
			obsAutores.addAll(autorDAO.getAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void removeAutor(Autor autor) {
		Optional<ButtonType> result = AlertUtil.makeConfirm("Exclusão", "Deseja realmente remover este autor?", null);
		if (result.get() == ButtonType.OK) {
			obsAutores.remove(autor);
			autorDAO.remove(autor);
			table.refresh();
		}
	}

	private void prepareTableView() {
		colNome.setCellValueFactory(new PropertyValueFactory<Autor, String>("nome"));

		Callback<TableColumn<Autor, Node>, TableCell<Autor, Node>> cellEditFactory = //
				new Callback<TableColumn<Autor, Node>, TableCell<Autor, Node>>() {
					@Override
					public TableCell<Autor, Node> call(final TableColumn<Autor, Node> param) {
						final TableCell<Autor, Node> cell = new TableCell<Autor, Node>() {

							final Button btn = new Button();

							@Override
							public void updateItem(Node item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else {
									btn.setOnAction(event -> {
										Autor autor = getTableView().getItems().get(getIndex());
										editAutor(autor);
									});
									Tooltip.install(btn, new Tooltip("Editar autor"));
									btn.setStyle("-fx-graphic: url('/icons/pencil_blue_16.png');");
									btn.setCursor(Cursor.HAND);
									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};
		colEdit.setCellFactory(cellEditFactory);

		Callback<TableColumn<Autor, Node>, TableCell<Autor, Node>> cellRemoveFactory = //
				new Callback<TableColumn<Autor, Node>, TableCell<Autor, Node>>() {
					@Override
					public TableCell<Autor, Node> call(final TableColumn<Autor, Node> param) {
						final TableCell<Autor, Node> cell = new TableCell<Autor, Node>() {

							final Button btn = new Button();

							@Override
							public void updateItem(Node item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else {
									btn.setOnAction(event -> {
										Autor autor = getTableView().getItems().get(getIndex());
										removeAutor(autor);
									});
									Tooltip.install(btn, new Tooltip("Remover autor"));
									btn.setStyle("-fx-graphic: url('/icons/trash_red_16.png');");
									btn.setCursor(Cursor.HAND);
									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};
		colRemove.setCellFactory(cellRemoveFactory);

		colNome.setStyle("-fx-alignment: CENTER;");
		colEdit.setStyle("-fx-alignment: CENTER;");
		colRemove.setStyle("-fx-alignment: CENTER;");

		table.setItems(obsAutores);
	}

}
