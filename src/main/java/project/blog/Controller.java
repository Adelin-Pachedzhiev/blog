package project.blog;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import project.model.ArticleJoin;

public class Controller {
    @FXML
    private TableView<ArticleJoin> articleTable;

    public void initialize(){


    }
    @FXML
    public void loadArticles(){

        Task<ObservableList<ArticleJoin>> loadTask = new LoadArticlesJoin();
        articleTable.itemsProperty().bind(loadTask.valueProperty());
        Thread thread = new Thread(loadTask);
        new Thread(loadTask).start();
    }
}


