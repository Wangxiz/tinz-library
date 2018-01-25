package ui.about;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import static util.LibraryAssistantUtil.setStageIcon;

public class AboutController {
    private static final String LINKED_IN = "https://in.linkedin.com/in/muhammedafsalvillan";
    private static final String FACEBOOK = "http://facebook.com/afsalashyana";
    private static final String WEBSITE = "http://www.genuinecoder.com";
    private static final String YOUTUBE = "https://www.youtube.com/GenuineCoder";

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
        loadWebpage(YOUTUBE);
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
