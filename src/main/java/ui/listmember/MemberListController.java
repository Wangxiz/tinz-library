package ui.listmember;

import com.jfoenix.controls.JFXSnackbar;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import database.DatabaseHandler;
import ui.addbook.BookAddController;
import ui.addmember.MemberAddController;
import ui.main.MainController;

import static util.Constant.snackbar;
import static util.LibraryAssistantUtil.setStageIcon;
import static util.alert.AlertMaker.showSimpleAlert;
import static util.alert.AlertMaker.showErrorMessage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberListController {
    private ObservableList<Member> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member, String> nameCol;
    @FXML
    private TableColumn<Member, String> idCol;
    @FXML
    private TableColumn<Member, String> mobileCol;
    @FXML
    private TableColumn<Member, String> emailCol;

    @FXML
    public void initialize() {
        initCol();
        loadData();
    }

    private void initCol() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadData() {
        list.clear();

        DatabaseHandler handler = DatabaseHandler.getInstance();
        String qu = "SELECT * FROM MEMBER";
        ResultSet rs = handler.execQuery(qu);
        try {
            while (rs != null && rs.next()) {
                String name = rs.getString("name");
                String mobile = rs.getString("mobile");
                String id = rs.getString("id");
                String email = rs.getString("email");

                list.add(new Member(name, id, mobile, email));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookAddController.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableView.setItems(list);
    }

    @FXML
    private void handleMemberDelete() {
         //Fetch the selected row
        MemberListController.Member selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            showErrorMessage("No member selected", "Please select a member for deletion.");
            return;
        }
        if(DatabaseHandler.getInstance().isMemberHasAnyBooks(selectedForDeletion)) {
            showErrorMessage("Cant be deleted", "This member has some books.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting book");
        alert.setContentText("Are you sure want to delete " + selectedForDeletion.getName()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            Boolean result = DatabaseHandler.getInstance().deleteMember(selectedForDeletion);
            if (result) {
                snackbar.fireEvent(new JFXSnackbar.SnackbarEvent("'" + selectedForDeletion.getName()+ "' was deleted successfully."));
                list.remove(selectedForDeletion);
            }
            else {
                showSimpleAlert("Failed", selectedForDeletion.getName()+ " could not be deleted");
            }
        }
        else {
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent("Deletion process cancelled"));
        }
    }

    @FXML
    private void handleRefresh()
    {
        loadData();
    }

    @FXML
    private void handleMemberEdit() {
        //Fetch the selected row
        Member selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            showErrorMessage("No member selected", "Please select a member for edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addmember/member_add.fxml"));
            Parent parent = loader.load();

            MemberAddController controller = loader.getController();
            controller.infalteUI(selectedForEdit);
            
            Stage stage = new Stage(StageStyle.DECORATED);
            setStageIcon(stage);
            stage.setTitle("Edit Member");
            stage.setScene(new Scene(parent));
            stage.show();
            
            stage.setOnCloseRequest((e)-> handleRefresh());
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static class Member {
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
        private final SimpleStringProperty mobile;
        private final SimpleStringProperty email;

        public Member(String name, String id, String mobile, String email) {
            this.name = new SimpleStringProperty(name);
            this.id = new SimpleStringProperty(id);
            this.mobile = new SimpleStringProperty(mobile);
            this.email = new SimpleStringProperty(email);
        }

        public String getName() {
            return name.get();
        }

        public String getId() {
            return id.get();
        }

        public String getMobile() {
            return mobile.get();
        }

        public String getEmail() {
            return email.get();
        }
    }
}
