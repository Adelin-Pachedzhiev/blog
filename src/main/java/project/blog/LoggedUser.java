package project.blog;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import project.model.User;

public class LoggedUser extends User {

    private SimpleBooleanProperty loggedIn;
    private static LoggedUser instance = new LoggedUser();

    public LoggedUser() {
        super();
        loggedIn = new SimpleBooleanProperty(false);
    }

    public void login(User user){
        this.setId(user.getId());
        this.setName(user.getName());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setLoggedIn(true);
    }

    public void logout(){
        this.setId(0);
        this.setName("");
        this.setUsername("");
        this.setPassword("");
        this.setLoggedIn(false);

    }


    public static LoggedUser getInstance() {
        return instance;
    }


    public boolean isLoggedIn() {
        return loggedIn.get();
    }

    public SimpleBooleanProperty loggedInProperty() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn.set(loggedIn);
    }

    public void setInstance(LoggedUser instance) {
        this.instance = instance;
    }
}
