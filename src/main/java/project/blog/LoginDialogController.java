package project.blog;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import project.model.DataSource;
import project.model.User;

public class LoginDialogController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    public boolean login(){
        if(LoggedUser.getInstance().isLoggedIn()){
            System.out.println("already logged in");
            return false;
        }
        User user = DataSource.getInstance().queryUserByUsernamePassword(usernameField.getText(), passwordField.getText());
        if (user == null){
            return false;
        }
        LoggedUser.getInstance().login(user);
        return true;
    }
}
