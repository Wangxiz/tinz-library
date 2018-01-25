package ui.login;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
import ui.settings.Preferences;

import static org.apache.commons.codec.digest.DigestUtils.shaHex;
import static ui.settings.Preferences.getPreferences;

public class PasswordValidator extends ValidatorBase {
    private Preferences preference;

    public PasswordValidator() {
        preference = getPreferences();
    }

    @Override
    protected void eval() {
        if (srcControl.get() instanceof TextInputControl) {
            evalTextInputField();
        }
    }

    private void evalTextInputField() {
        TextInputControl textField = (TextInputControl) srcControl.get();
        String pword = shaHex(textField.getText());
        if (pword.equals(preference.getPassword())) {
            hasErrors.set(false);
        } else {
            hasErrors.set(true);
        }
    }
}
