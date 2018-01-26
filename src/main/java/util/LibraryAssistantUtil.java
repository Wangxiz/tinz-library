package util;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import ui.main.MainController;

public class LibraryAssistantUtil {
    private static final String IMAGE_LOC = "icons/icon.png";

    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(IMAGE_LOC));
    }

    public static void loadWindow(URL loc, String title, Window owner, StageStyle style, Modality modality) {
        try {
            Parent parent = FXMLLoader.load(loc);

            Stage stage = new Stage();
            if(style != null) stage.initStyle(style);
            if(modality != null) stage.initModality(modality);
            setStageIcon(stage);
            stage.initOwner(owner);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadWindow(URL loc, String title, Window owner) {
        loadWindow(loc, title, owner, null, null);
    }

    public static void loadWindow(URL loc, String title, Window owner, StageStyle style) {
        loadWindow(loc, title, owner, style, null);
    }

    public static void loadWindow(URL loc, String title, Window owner, Modality modality) {
        loadWindow(loc, title, owner, null, modality);
    }
}
