package project.blog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import project.model.Article;
import project.model.ArticleType;

public class AddItemDialogController {

    @FXML
    private TextField titleField;
    @FXML
    private ComboBox<ArticleType> typeComboBox;
    @FXML
    private TextArea textArea;
    public Article addArticle(){
        ObservableList<ArticleType> list = FXCollections.observableArrayList();
        ArticleType article1 = new ArticleType();
        article1.setId(2);
        article1.setName("essay");
        article1.setDescription("descr");
        ArticleType article2 = new ArticleType();
        article2.setId(3);
        article2.setName("article");
        article2.setDescription("desc2");
        list.addAll(article1,article2);
        typeComboBox.setItems(list);
        typeComboBox.
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
        typeComboBox.setOnAction(e->{
            System.out.println(typeComboBox.getSelectionModel().getSelectedItem().getName());
        });
        return null;
    }
}
