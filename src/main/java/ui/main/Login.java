package ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import database.DatabaseHandler;

import static util.LibraryAssistantUtil.setStageIcon;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login/login.fxml"));

        Scene scene = new Scene(root);

        setStageIcon(stage);                       // 设置标题栏图标
        stage.initStyle(StageStyle.TRANSPARENT);   // 不显示标题栏
        stage.setTitle("Library Assistant Login");
        stage.setResizable(false);                 // 窗口不可放大缩小
        stage.setAlwaysOnTop(true);                // 窗口永远处于最顶层
        stage.setScene(scene);
        stage.show();
        
        new Thread(DatabaseHandler::getInstance).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
