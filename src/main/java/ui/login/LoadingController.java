package ui.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import ui.main.Login;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {
    @FXML
    private AnchorPane loading;

    private Login mainApp;

    public void setApp(Login mainApp){
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
