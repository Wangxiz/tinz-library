package ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import database.DatabaseHandler;
import util.LibraryAssistantUtil;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login/login.fxml"));

        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Library Assistant Login");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();

        LibraryAssistantUtil.setStageIcon(stage);
        
        new Thread(DatabaseHandler::getInstance).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
