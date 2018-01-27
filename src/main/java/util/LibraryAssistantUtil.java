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

    public static Stage loadWindow(URL loc, String title, Window owner, StageStyle style, Modality modality, boolean resizable) {
        try {
            Parent parent = FXMLLoader.load(loc);

            Stage stage = new Stage();
            if(style != null) stage.initStyle(style);
            if(modality != null) stage.initModality(modality);
            setStageIcon(stage);
            stage.setResizable(resizable);
            stage.initOwner(owner);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            return stage;
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Stage loadWindow(URL loc, String title, Window owner, StageStyle style, Modality modality) {
        return loadWindow(loc, title, owner, null, null, true);
    }

    public static Stage loadWindow(URL loc, String title, Window owner, boolean resizable) {
        return loadWindow(loc, title, owner, null, null, resizable);
    }

    public static Stage loadWindow(URL loc, String title, Window owner) {
        return loadWindow(loc, title, owner, null, null, true);
    }

    public static Stage loadWindow(URL loc, String title, Window owner, StageStyle style, boolean resizable) {
        return loadWindow(loc, title, owner, style, null, resizable);
    }

    public static Stage loadWindow(URL loc, String title, Window owner, StageStyle style) {
        return loadWindow(loc, title, owner, style, null, true);
    }

    public static Stage loadWindow(URL loc, String title, Window owner, Modality modality, boolean resizable) {
        return loadWindow(loc, title, owner, null, modality, resizable);
    }

    public static Stage loadWindow(URL loc, String title, Window owner, Modality modality) {
        return loadWindow(loc, title, owner, null, modality, true);
    }
}
