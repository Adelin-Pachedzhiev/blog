package project.blog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import project.model.ArticleJoin;

public class ReadArticleDialogController {
    @FXML
    private Label titleLabel;
    @FXML
    private Label dateWrittenLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Text articleText;


    public void loadArticle(ArticleJoin article){
        titleLabel.setText(article.getTitle());
        dateWrittenLabel.setText(article.getDateWritten());
        typeLabel.setText(article.getType());
        articleText.setText(article.getContent());
        articleText.wrappingWidthProperty().set(400);
    }

}
