package ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//import javafx.event.EventHandler;
//import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.main.MainController;
import ui.settings.Preferences;

import static org.apache.commons.codec.digest.DigestUtils.shaHex;
import static util.LibraryAssistantUtil.setStageIcon;
import static ui.settings.Preferences.getPreferences;

public class LoginController {
    @FXML
    private JFXTextField validatedUserName;
    @FXML
    private JFXPasswordField validatedPassword;

    private Preferences preference;

    @FXML
    public void initialize() {
        preference = getPreferences();

        validatedUserName.textProperty().addListener((observable, oldValue, newValue) -> validatedUserName.validate());
        validatedPassword.textProperty().addListener((observable, oldValue, newValue) -> validatedPassword.validate());
    }

    @FXML
    private void handleLogin() {
        String uname = validatedUserName.getText();
        String pword = shaHex(validatedPassword.getText());

        if (uname.equals(preference.getUsername()) && pword.equals(preference.getPassword())) {
            closeStage();
            loadMain();
        } else {
            validatedPassword.clear();
            validatedUserName.getStyleClass().add("wrong-credentials");
            validatedPassword.getStyleClass().add("wrong-credentials");
        }
    }

    @FXML
    private void handleCancel() {
        System.exit(0);
    }

    private void closeStage() {
        ((Stage) validatedUserName.getScene().getWindow()).close();
    }

    private void loadMain() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/main/main.fxml"));
            Parent parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);

            MainController controller = loader.getController();
            controller.setPrimaryStage(stage);

            setStageIcon(stage);

//            EventHandler<MouseEvent> mouseEventHandler = event -> System.out.println("Mouse event handler has been called");
//            stage.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEventHandler);

            stage.setOnCloseRequest((event) -> System.exit(0));  // 当主窗口关闭时，退出程序
            stage.setTitle("Library Assistant");
            stage.setScene(new Scene(parent));
            stage.setResizable(true);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
