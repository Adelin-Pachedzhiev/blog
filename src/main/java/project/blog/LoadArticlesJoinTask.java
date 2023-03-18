package project.blog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import project.model.ArticleJoin;
import project.model.DataSource;

public class LoadArticlesJoinTask extends Task<ObservableList<ArticleJoin>> {
    @Override
    protected ObservableList<ArticleJoin> call() {

        return FXCollections.observableArrayList(DataSource.getInstance().queryArticlesJoin());
    }
}
