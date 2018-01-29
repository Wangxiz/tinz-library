package ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.main.Login;
import ui.main.MainController;
import ui.settings.Preferences;
import util.Constant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.codec.digest.DigestUtils.shaHex;
import static ui.settings.Preferences.getPreferences;
import static util.LibraryAssistantUtil.setStageIcon;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane login;
    @FXML
    private JFXTextField validatedUserName;
    @FXML
    private JFXPasswordField validatedPassword;

    private Preferences preference;

    private Login mainApp;

    public void setApp(Login mainApp){
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preference = getPreferences();

        validatedUserName.textProperty().addListener((observable, oldValue, newValue) -> validatedUserName.validate());
        validatedPassword.textProperty().addListener((observable, oldValue, newValue) -> validatedPassword.validate());
    }

    @FXML
    private void handleLogin() {
        String uname = validatedUserName.getText();
        String pword = shaHex(validatedPassword.getText());

        if (uname.equals(preference.getUsername()) && pword.equals(preference.getPassword())) {
//            closeStage();
            mainApp.gotoLoading();
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
            stage.showingProperty().addListener((observable) -> {
                closeStage();
            });

            Constant.mainStage = stage;
            Constant.mainController = loader.getController();

            setStageIcon(stage);
            stage.setFullScreenExitHint("按 Esc 或 Ctrl+F 可退出全屏模式");
            stage.setTitle("Tinz-Libraries");
            stage.setScene(new Scene(parent));
            stage.setResizable(true);
            stage.setMaximized(true);

            stage.show();
        } catch (IOException/* | InterruptedException*/ ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
