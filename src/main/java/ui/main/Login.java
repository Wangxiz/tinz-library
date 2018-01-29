package ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import database.DatabaseHandler;
import ui.login.LoadingController;
import ui.login.LoginController;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import static util.LibraryAssistantUtil.setStageIcon;

public class Login extends Application {
    private double dragOffsetX;
    private double dragOffsetY;

    private Group root = new Group();

    public Parent createContent() {
        gotoLogin();
        return root;
    }

    @Override
    public void start(final Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login/login.fxml"));
        final String css = getClass().getResource("/css/dark-theme.css").toExternalForm();
        root.getStylesheets().add(css);
        Scene scene = new Scene(createContent());
        // 实现拖拽一个没有标题栏的 Stage
        scene.setOnMousePressed(event -> {
            dragOffsetX = event.getScreenX() - stage.getX();
            dragOffsetY = event.getScreenY() - stage.getY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - dragOffsetX);
            stage.setY(event.getScreenY() - dragOffsetY);
        });

        setStageIcon(stage);                       // 设置标题栏图标
        stage.initStyle(StageStyle.TRANSPARENT);   // 不显示标题栏
        stage.setResizable(false);                 // 窗口不可放大缩小
        stage.setAlwaysOnTop(true);                // 窗口永远处于最顶层
        stage.setScene(scene);
        stage.show();
        
        new Thread(DatabaseHandler::getInstance).start();
    }

    public void gotoLogin() {
        try {
            LoginController login = (LoginController)replaceSceneContent("/fxml/login/login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoLoading() {
        try {
            LoadingController loading = (LoadingController)replaceSceneContent("/fxml/login/loading.fxml");
            loading.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Login.class.getResource(fxml));
        AnchorPane page;

        try (InputStream in = Login.class.getResourceAsStream(fxml)) {
            page = loader.load(in);
        }
        root.getChildren().removeAll();
        root.getChildren().addAll(page);

        return (Initializable)loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
