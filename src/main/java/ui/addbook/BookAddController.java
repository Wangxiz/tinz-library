package ui.addbook;

import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import database.DatabaseHandler;
import javafx.stage.WindowEvent;

import static util.Constant.snackbar;
import static ui.listbook.BookListController.Book;
import static util.alert.AlertMaker.showErrorMessage;


public class BookAddController {
    private DatabaseHandler handler;
    private Boolean isInEditMode = Boolean.FALSE;

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField author;
    @FXML
    private JFXTextField publisher;
    @FXML
    private AnchorPane rootPane;

    @FXML
    public void initialize() {
        handler = DatabaseHandler.getInstance();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        stage.close();
    }

    @FXML
    private void handleSave() {
        String bookID = id.getText();
        String bookAuthor = author.getText();
        String bookName = title.getText();
        String bookPublisher = publisher.getText();

        if (bookID.isEmpty() || bookAuthor.isEmpty() || bookName.isEmpty() || bookPublisher.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
        }

        if (isInEditMode) {
            handleEditOperation();
            handleCancel();
            return;
        }

        String qu = "INSERT INTO BOOK VALUES ("
                + "'" + bookID + "',"
                + "'" + bookName + "',"
                + "'" + bookAuthor + "',"
                + "'" + bookPublisher + "',"
                + "" + "true" + ""
                + ")";
        System.out.println(qu);
        handleCancel();

        if (handler.execAction(qu)) {
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent("Successfully add a new book"));
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
    }

    private void checkData() {
        String qu = "SELECT title FROM BOOK";
        ResultSet rs = handler.execQuery(qu);
        try {
            if (rs != null) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    System.out.println(title);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inflateUI(Book book) {
        title.setText(book.getTitle());
        id.setText(book.getId());
        author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());
        id.setEditable(false);
        isInEditMode = Boolean.TRUE;
    }

    private void handleEditOperation() {
        Book book = new Book(title.getText(), id.getText(), author.getText(), publisher.getText(), true);
        if (handler.updateBook(book)) {
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent("Book updated"));
        }
        else {
            showErrorMessage("Failed", "Cant update book");
        }
    }
}
