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
import tcc.esucri.library.controller.modal.LivrosModalController;
import tcc.esucri.library.dao.LivroDAO;
import tcc.esucri.library.model.Autor;
import tcc.esucri.library.model.Livro;
import tcc.esucri.library.util.AlertUtil;

public class LivrosController implements Initializable {

	@FXML
	private TableView<Livro> table;
	@FXML
	private TableColumn<Livro, String> colTitulo;
	@FXML
	private TableColumn<Livro, Autor> colAutor;
	@FXML
	private TableColumn<Livro, Node> colEdit;
	@FXML
	private TableColumn<Livro, Node> colRemove;

	private ObservableList<Livro> obsLivros = FXCollections.observableArrayList();

	private LivroDAO livroDAO;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prepareTableView();
		livroDAO = new LivroDAO();
		obsLivros.addAll(livroDAO.getAll());
	}

	@FXML
	private void addLivro() {
		try {
			Stage stage;
			Parent root;
			stage = new Stage();
			URL url = getClass().getResource("/view/modal/LivrosModal.fxml");
			FXMLLoader fxmlloader = new FXMLLoader();
			fxmlloader.setLocation(url);
			fxmlloader.setBuilderFactory(new JavaFXBuilderFactory());
			root = (Parent) fxmlloader.load(url.openStream());
			stage.setScene(new Scene(root));
			stage.setTitle("Cadastro de livros");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(table.getScene().getWindow());
			stage.setResizable(false);
			stage.showAndWait();
			obsLivros.clear();
			obsLivros.addAll(livroDAO.getAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void editLivro(Livro livro) {
		try {
			Stage stage;
			Parent root;
			stage = new Stage();
			URL url = getClass().getResource("/view/modal/LivrosModal.fxml");
			FXMLLoader fxmlloader = new FXMLLoader();
			fxmlloader.setLocation(url);
			fxmlloader.setBuilderFactory(new JavaFXBuilderFactory());
			root = (Parent) fxmlloader.load(url.openStream());
			((LivrosModalController) fxmlloader.getController()).setContext(livro);
			stage.setScene(new Scene(root));
			stage.setTitle("Cadastro de livros");
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(table.getScene().getWindow());
			stage.setResizable(false);
			stage.showAndWait();
			obsLivros.clear();
			obsLivros.addAll(livroDAO.getAll());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void removeLivro(Livro livro) {
		Optional<ButtonType> result = AlertUtil.makeConfirm("Exclusão", "Deseja realmente remover este livro?", null);
		if (result.get() == ButtonType.OK) {
			obsLivros.remove(livro);
			livroDAO.remove(livro);
			table.refresh();
		}
	}

	private void prepareTableView() {
		colTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
		colAutor.setCellValueFactory(new PropertyValueFactory<Livro, Autor>("autor"));

		Callback<TableColumn<Livro, Node>, TableCell<Livro, Node>> cellEditFactory = //
				new Callback<TableColumn<Livro, Node>, TableCell<Livro, Node>>() {
					@Override
					public TableCell<Livro, Node> call(final TableColumn<Livro, Node> param) {
						final TableCell<Livro, Node> cell = new TableCell<Livro, Node>() {
							final Button btn = new Button();
							@Override
							public void updateItem(Node item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else {
									btn.setOnAction(event -> {
										Livro livro = getTableView().getItems().get(getIndex());
										editLivro(livro);
									});
									Tooltip.install(btn, new Tooltip("Editar livro"));
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

		Callback<TableColumn<Livro, Node>, TableCell<Livro, Node>> cellRemoveFactory = //
				new Callback<TableColumn<Livro, Node>, TableCell<Livro, Node>>() {
					@Override
					public TableCell<Livro, Node> call(final TableColumn<Livro, Node> param) {
						final TableCell<Livro, Node> cell = new TableCell<Livro, Node>() {

							final Button btn = new Button();

							@Override
							public void updateItem(Node item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else {
									btn.setOnAction(event -> {
										Livro livro = getTableView().getItems().get(getIndex());
										removeLivro(livro);
									});
									Tooltip.install(btn, new Tooltip("Remover livro"));
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

		colTitulo.setStyle("-fx-alignment: CENTER;");
		colAutor.setStyle("-fx-alignment: CENTER;");
		colEdit.setStyle("-fx-alignment: CENTER;");
		colRemove.setStyle("-fx-alignment: CENTER;");

		table.setItems(obsLivros);
	}

}
