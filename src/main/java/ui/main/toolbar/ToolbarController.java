package ui.main.toolbar;

import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import static util.LibraryAssistantUtil.loadWindow;
import static util.Constant.mainStage;

public class ToolbarController {
    @FXML
    public void initialize() {}

    @FXML
    private void loadAddMember() {
        loadWindow(getClass().getResource("/fxml/addmember/member_add.fxml"), "Add New Member", mainStage, Modality.WINDOW_MODAL);
    }

    @FXML
    private void loadAddBook() {
        loadWindow(getClass().getResource("/fxml/addbook/add_book.fxml"), "Add New Book", mainStage, Modality.WINDOW_MODAL);
    }

    @FXML
    private void loadMemberTable() {
        loadWindow(getClass().getResource("/fxml/listmember/member_list.fxml"), "Member List", mainStage, Modality.WINDOW_MODAL);
    }

    @FXML
    private void loadBookTable() {
        loadWindow(getClass().getResource("/fxml/listbook/book_list.fxml"), "Book List", mainStage, Modality.WINDOW_MODAL);
    }

    @FXML
    private void loadSettings() {
        loadWindow(getClass().getResource("/fxml/settings/settings.fxml"), "Settings", mainStage, StageStyle.UTILITY, Modality.WINDOW_MODAL);
    }
}
