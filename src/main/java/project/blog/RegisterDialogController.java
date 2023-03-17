package project.blog;

import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import project.model.DataSource;
import project.model.User;

public class RegisterDialogController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField usernameField;

    public boolean registerUser(){
        if(DataSource.getInstance().queryUsersByUsername(usernameField.getText()) != null){
            return false;
        }
        DataSource.getInstance().queryInsertUser(nameField.getText(), usernameField.getText(), passwordField.getText());
        User newUser = DataSource.getInstance().queryUsersByUsername(usernameField.getText());
        if(newUser ==  null){
            return false;
        }
        LoggedUser.getInstance().login(newUser);
        return true;
    }
}
