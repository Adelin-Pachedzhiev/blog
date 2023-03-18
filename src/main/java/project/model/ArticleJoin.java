package project.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ArticleJoin extends Article{
    private SimpleStringProperty writer;
    private SimpleStringProperty type;

    public ArticleJoin() {
        super();
        this.writer = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
    }
    public String getWriter() {
        return writer.get();
    }

    public SimpleStringProperty writerProperty() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer.set(writer);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

}
