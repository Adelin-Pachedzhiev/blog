package project.blog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import project.model.ArticleType;
import project.model.DataSource;

public class LoadArticleTypesTask extends Task<ObservableList<ArticleType>> {
    @Override
    protected ObservableList<ArticleType> call() throws Exception {
        return FXCollections.observableArrayList(DataSource.getInstance().queryArticleTypes());
    }
}
