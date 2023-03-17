package project.blog;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import project.model.Article;
import project.model.ArticleJoin;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private TableView<ArticleJoin> articleTable;

    @FXML
    private BorderPane borderPane;
    @FXML
    private Button loadArticlesButton;
    @FXML
    private Button newArticleButton;
    @FXML
    private Button editArticleButton;
    @FXML
    private Button deleteArticleButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button logoutButton;
    @FXML
    private VBox buttonsVBox;


    public void initialize() {
        buttonsOnLoggedOut();

    }

    private void buttonsOnLoggedOut() {
        if (!buttonsVBox.getChildren().contains(loginButton)) {
            buttonsVBox.getChildren().add(loginButton);
        }
        if (!buttonsVBox.getChildren().contains(registerButton)) {
            buttonsVBox.getChildren().add(registerButton);
        }
        buttonsVBox.getChildren().remove(logoutButton);
        buttonsVBox.getChildren().remove(newArticleButton);
        buttonsVBox.getChildren().remove(editArticleButton);
        buttonsVBox.getChildren().remove(deleteArticleButton);
    }

    private void buttonsOnLoggedIn() {
        buttonsVBox.getChildren().remove(loginButton);
        buttonsVBox.getChildren().remove(registerButton);
        buttonsVBox.getChildren().add(newArticleButton);
        buttonsVBox.getChildren().add(editArticleButton);
        buttonsVBox.getChildren().add(deleteArticleButton);
        buttonsVBox.getChildren().add(logoutButton);
    }

    @FXML
    public void loadArticles() {

        Task<ObservableList<ArticleJoin>> loadTask = new LoadArticlesJoin();
        articleTable.itemsProperty().bind(loadTask.valueProperty());
        Thread thread = new Thread(loadTask);
        new Thread(loadTask).start();
    }

    @FXML
    public void handleNewItem() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Add new Article");
        dialog.setHeaderText("Write your article here: ");
        FXMLLoader dialogLoader = new FXMLLoader();
        dialogLoader.setLocation(getClass().getResource("addItemDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(dialogLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            AddItemDialogController dialogController = dialogLoader.getController();
            Article newArticle = dialogController.addArticle();
        }
    }

    @FXML
    public void handleLogin() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Login");
        dialog.setHeaderText("Login");
        FXMLLoader dialogLoader = new FXMLLoader();
        dialogLoader.setLocation(getClass().getResource("loginDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(dialogLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            LoginDialogController dialogController = dialogLoader.getController();
            boolean isLoginSuccessful = dialogController.login();
            if (isLoginSuccessful) {
                buttonsOnLoggedIn();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
                alert.setTitle("Login failed");
                alert.setHeaderText("Wrong username or password");
                alert.showAndWait();
            }

        }
    }

    @FXML
    public void handleLogout() {
        LoggedUser.getInstance().logout();
        buttonsOnLoggedOut();

    }

    @FXML
    public void handleRegister() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Register");
        dialog.setHeaderText("Register");
        FXMLLoader dialogLoader = new FXMLLoader();
        dialogLoader.setLocation(getClass().getResource("registerDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(dialogLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            RegisterDialogController dialogController = dialogLoader.getController();
            boolean isRegisterSuccessful = dialogController.registerUser();
            if (isRegisterSuccessful) {
                buttonsOnLoggedIn();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
                alert.setTitle("Register failed");
                alert.setHeaderText("Username is already taken ot there is an internal problem. \nPlease try again.");
                alert.showAndWait();
            }

        }
    }

    @FXML
    public void showArticle(){
        ArticleJoin article = articleTable.getSelectionModel().getSelectedItem();
        if(article == null){
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle(article.getTitle());
        FXMLLoader dialogLoader = new FXMLLoader();
        dialogLoader.setLocation(getClass().getResource("readArticleDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(dialogLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        ReadArticleDialogController dialogController = dialogLoader.getController();
        dialogController.loadArticle(article);
        dialog.showAndWait();
    }

}


