package ui.addmember;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import database.DatabaseHandler;
import ui.listmember.MemberListController;

import static util.alert.AlertMaker.showSimpleAlert;
import static util.alert.AlertMaker.showErrorMessage;

public class MemberAddController {
    private DatabaseHandler handler;
    private Boolean isInEditMode = false;

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;

    @FXML
    public void initialize() {
        handler = DatabaseHandler.getInstance();
    }

    @FXML
    private void cancel() {
        Stage stage = (Stage)name.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addMember() {
        String mName = name.getText();
        String mID = id.getText();
        String mMobile = mobile.getText();
        String mEmail = email.getText();

        Boolean flag = mName.isEmpty() || mID.isEmpty() || mMobile.isEmpty() || mEmail.isEmpty();
        if (flag) {
            showErrorMessage("Cant process member", "Please Enter in all fields");
            return;
        }
        
        if(isInEditMode) {
            cancel();
            handleUpdateMember();
            return;
        }
        
        String st = "INSERT INTO MEMBER VALUES ("
                + "'" + mID + "',"
                + "'" + mName + "',"
                + "'" + mMobile + "',"
                + "'" + mEmail + "'"
                + ")";
        System.out.println(st);
        cancel();

        if (handler.execAction(st)) {
            showSimpleAlert("Member Added", "Saved");
        }
        else {
            showErrorMessage("Member cant be added", "Error Occurred");
        }
    }
    
    public void infalteUI(MemberListController.Member member) {
        name.setText(member.getName());
        id.setText(member.getId());
        id.setEditable(false);
        mobile.setText(member.getMobile());
        email.setText(member.getEmail());
        
        isInEditMode = Boolean.TRUE;
    }

    private void handleUpdateMember() {
        MemberListController.Member member = new MemberListController.Member(name.getText(), id.getText(), mobile.getText(), email.getText());
        if (DatabaseHandler.getInstance().updateMember(member)) {
            showSimpleAlert("Success", "Member Updated");
        } else {
            showErrorMessage("Failed", "Cant update member");
        }
    }
}
