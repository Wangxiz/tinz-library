package ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.main.MainController;
import ui.settings.Preferences;
import util.Constant;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.codec.digest.DigestUtils.shaHex;
import static ui.settings.Preferences.getPreferences;
import static util.LibraryAssistantUtil.setStageIcon;

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
        Stage stage = (Stage) validatedUserName.getScene().getWindow();
        stage.close();
    }

    private void loadMain() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/main/main.fxml"));
            StackPane parent = loader.load();
            Stage stage = new Stage(StageStyle.DECORATED);
            Constant.mainStage = stage;

            setStageIcon(stage);
            stage.setTitle("Tinz-Libraries");
            stage.setScene(new Scene(parent));
            stage.setResizable(true);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
