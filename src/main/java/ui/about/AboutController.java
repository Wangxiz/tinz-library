package ui.about;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import static util.LibraryAssistantUtil.setStageIcon;

public class AboutController {
    private static final String LINKED_IN = "https://www.linkedin.com/in/wangxiz";
    private static final String FACEBOOK = "https://www.facebook.com/Wangxiz";
    private static final String WEBSITE = "http://www.cnblogs.com/Wangxiz";
    private static final String GITHUB = "https://github.com/Wangxiz";

    @FXML
    public void initialize() {}

    private void loadWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
            handleWebpageLoadException(url);
        }
    }

    private void handleWebpageLoadException(String url) {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(url);
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane(browser));
        stage.setScene(scene);
        setStageIcon(stage);
        stage.setTitle("Genuine Coder");
        stage.show();
    }

    @FXML
    private void loadYoutubeChannel() {
        loadWebpage(GITHUB);
    }

    @FXML
    private void loadBlog() {
        loadWebpage(WEBSITE);
    }

    @FXML
    private void loadLinkedIN() {
        loadWebpage(LINKED_IN);
    }

    @FXML
    private void loadFacebook() {
        loadWebpage(FACEBOOK);
    }
}
