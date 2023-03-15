package project.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ArticleJoin {
    private SimpleIntegerProperty id;
    private SimpleStringProperty writer;
    private SimpleIntegerProperty writer_id;
    private SimpleStringProperty title;
    private SimpleStringProperty content;
    private SimpleStringProperty dateWritten;
    private SimpleStringProperty type;
    private SimpleIntegerProperty type_id;

    public ArticleJoin() {
        this.id = new SimpleIntegerProperty();
        this.writer = new SimpleStringProperty();
        this.writer_id = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
        this.content = new SimpleStringProperty();
        this.dateWritten = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.type_id = new SimpleIntegerProperty();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public int getWriter_id() {
        return writer_id.get();
    }

    public SimpleIntegerProperty writer_idProperty() {
        return writer_id;
    }

    public void setWriter_id(int writer_id) {
        this.writer_id.set(writer_id);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getDateWritten() {
        return dateWritten.get();
    }

    public SimpleStringProperty dateWrittenProperty() {
        return dateWritten;
    }

    public void setDateWritten(String dateWritten) {
        this.dateWritten.set(dateWritten);
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

    public int getType_id() {
        return type_id.get();
    }

    public SimpleIntegerProperty type_idProperty() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id.set(type_id);
    }
}
