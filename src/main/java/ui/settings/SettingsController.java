package ui.settings;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SettingsController {
    
    @FXML
    private JFXTextField nDaysWithoutFine;
    @FXML
    private JFXTextField finePerDay;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    
    @FXML
    public void initialize() {
        initDefaultValues();
    }    
    
    @FXML
    private void handleSaveButtonAction() {
        int days = Integer.parseInt(nDaysWithoutFine.getText());
        float fine = Float.parseFloat(finePerDay.getText());
        String userName = username.getText();
        String passWord = password.getText();
        
        Preferences preferences = Preferences.getPreferences();
        preferences.setnDaysWithoutFine(days);
        preferences.setFinePerDay(fine);
        preferences.setUsername(userName);
        preferences.setPassword(passWord);

        handleCancelButtonAction();
        Preferences.writePreferenceToFile(preferences);
    }
    
    @FXML
    private void handleCancelButtonAction() {
        ((Stage)nDaysWithoutFine.getScene().getWindow()).close();
    }
    
    private void initDefaultValues() {
        Preferences preferences = Preferences.getPreferences();
        nDaysWithoutFine.setText(String.valueOf(preferences.getnDaysWithoutFine()));
        finePerDay.setText(String.valueOf(preferences.getFinePerDay()));
        username.setText(String.valueOf(preferences.getUsername()));
        password.setText(String.valueOf(preferences.getPassword()));
    }
}
