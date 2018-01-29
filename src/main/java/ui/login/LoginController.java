package ui.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.main.MainController;
import ui.settings.Preferences;
import util.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.apache.commons.codec.digest.DigestUtils.shaHex;
import static ui.settings.Preferences.getPreferences;
import static util.LibraryAssistantUtil.setStageIcon;

public class LoginController {
    @FXML
    private AnchorPane loading;
    @FXML
    private AnchorPane login;
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

        validatedUserName.setText("Wangxiz");
        validatedPassword.setText("0916");
        loading.setVisible(false);
    }

    @FXML
    private void handleLogin() {
        String uname = validatedUserName.getText();
        String pword = shaHex(validatedPassword.getText());

        if (uname.equals(preference.getUsername()) && pword.equals(preference.getPassword())) {
//            closeStage();
            loading.setVisible(true);
            login.setVisible(false);
            login.toBack();
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
            int i = 100000000;
            while (i > 0) i--;
//            TimeUnit.MILLISECONDS.sleep(500);
            stage.show();
        } catch (IOException/* | InterruptedException*/ ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
