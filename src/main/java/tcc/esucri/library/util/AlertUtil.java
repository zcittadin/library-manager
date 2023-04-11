package tcc.esucri.library.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public abstract class AlertUtil {

	public static void makeInfo(String title, String message, Image icon) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(message);
		if (icon != null) {
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(icon);
		}
		alert.showAndWait();
	}

	public static void makeNone(String title, String message, Image icon) {
		Alert alert = new Alert(AlertType.NONE);
		alert.setTitle(title);
		alert.setHeaderText(message);
		if (icon != null) {
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(icon);
		}
		alert.showAndWait();
	}

	public static void makeWarning(String title, String message, Image icon) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(message);
		if (icon != null) {
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(icon);
		}
		alert.showAndWait();
	}

	public static void makeError(String title, String message, Image icon) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(message);
		if (icon != null) {
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(icon);
		}
		alert.showAndWait();
	}

	public static Optional<ButtonType> makeConfirm(String title, String message, Image icon) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add("/css/dialog.css");
		dialogPane.getStyleClass().add("myDialog");
		alert.setTitle(title);
		alert.setHeaderText(message);
		if (icon != null) {
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(icon);
		}
		Optional<ButtonType> result = alert.showAndWait();
		return result;
	}

	public static Optional<ButtonType> makeConfirmWithCheckBox(String title, String message, Image icon,
			CheckBox checkBox) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add("/css/dialog.css");
		dialogPane.getStyleClass().add("myDialog");
		dialogPane.setContent(checkBox);
		alert.setTitle(title);
		alert.setHeaderText(message);
		if (icon != null) {
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(icon);
		}
		Optional<ButtonType> result = alert.showAndWait();
		return result;
	}
}