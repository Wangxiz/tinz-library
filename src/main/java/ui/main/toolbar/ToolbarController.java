package ui.main.toolbar;

import javafx.fxml.FXML;

import static util.LibraryAssistantUtil.loadWindow;

public class ToolbarController {
    @FXML
    public void initialize() {}

    @FXML
    private void loadAddMember() {
        loadWindow(getClass().getResource("/fxml/addmember/member_add.fxml"), "Add New Member", null);
    }

    @FXML
    private void loadAddBook() {
        loadWindow(getClass().getResource("/fxml/addbook/add_book.fxml"), "Add New Book", null);
    }

    @FXML
    private void loadMemberTable() {
        loadWindow(getClass().getResource("/fxml/listmember/member_list.fxml"), "Member List", null);
    }

    @FXML
    private void loadBookTable() {
        loadWindow(getClass().getResource("/fxml/listbook/book_list.fxml"), "Book List", null);
    }

    @FXML
    private void loadSettings() {
        loadWindow(getClass().getResource("/fxml/settings/settings.fxml"), "Settings", null);
    }
}
