package ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.settings.Preferences;
import ui.main.MainController;
import util.LibraryAssistantUtil;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginController {
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;

    private Preferences preference;

    @FXML
    public void initialize() {
        preference = Preferences.getPreferences();
    }

    @FXML
    private void handleLoginButtonAction() {
        String uname = username.getText();
        String pword = DigestUtils.shaHex(password.getText());

        if (uname.equals(preference.getUsername()) && pword.equals(preference.getPassword())) {
            closeStage();
            loadMain();
        } else {
            username.getStyleClass().add("wrong-credentials");
            password.getStyleClass().add("wrong-credentials");
        }
    }

    @FXML
    private void handleCancelButtonAction() {
        System.exit(0);
    }

    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }

    private void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/main/main.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Library Assistant");
            stage.setScene(new Scene(parent));
            stage.setResizable(true);
            stage.setMaximized(true);
            stage.show();
            LibraryAssistantUtil.setStageIcon(stage);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
