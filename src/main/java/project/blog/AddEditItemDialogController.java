package project.blog;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import project.model.Article;
import project.model.ArticleType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEditItemDialogController {

    @FXML
    private TextField titleField;
    @FXML
    private ComboBox<ArticleType> typeComboBox;
    @FXML
    private TextArea contentTextArea;
    public void loadAddForm(){

        loadArticleTypes();
        typeComboBox.setCellFactory(new Callback<ListView<ArticleType>, ListCell<ArticleType>>() {
            @Override
            public ListCell<ArticleType> call(ListView<ArticleType> articleTypeListView) {
                final ListCell<ArticleType> cell = new ListCell<ArticleType>(){
                    @Override
                    protected void updateItem(ArticleType t, boolean bln) {
                        super.updateItem(t, bln);

                        if(t != null){
                            setText(t.getName());
                        }else{
                            setText(null);
                        }
                    }
                };
                return cell;
            };
        });
    }

    private void loadArticleTypes(){
        Task<ObservableList<ArticleType>> task =  new LoadArticleTypesTask();
        typeComboBox.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    public Article createArticle(){
        Article article = new Article();
        article.setTitle(titleField.getText());
        article.setType_id(typeComboBox.getSelectionModel().getSelectedItem().getId());
        article.setWriter_id(LoggedUser.getInstance().getId());
        article.setContent(contentTextArea.getText());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        article.setDate_written(now.format(dtf));
        return article;
    }

    public void loadEditForm(Article article){
        loadArticleTypes();

        titleField.setText(article.getTitle());
        contentTextArea.setText(article.getContent());
    }

    public Article editArticle(Article article){
        article.setTitle(titleField.getText());
        article.setContent(contentTextArea.getText());
        article.setType_id(typeComboBox.getSelectionModel().getSelectedItem().getId());
        return article;

        //ADD QUERY FOR UPDATE
        // ADD DELETE

    }
}
