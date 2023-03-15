package project.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Article {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty writer_id;
    private SimpleStringProperty title;
    private SimpleStringProperty content;
    private SimpleIntegerProperty type_id;
    private SimpleStringProperty date_written;

    public Article() {
        this.id = new SimpleIntegerProperty();
        this.writer_id = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
        this.content = new SimpleStringProperty();
        this.type_id = new SimpleIntegerProperty();
        this.date_written = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getWriter_id() {
        return writer_id.get();
    }

    public void setWriter_id(int writer_id) {
        this.writer_id.set(writer_id);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public int getType_id() {
        return type_id.get();
    }

    public void setType_id(int type_id) {
        this.type_id.set(type_id);
    }

    public String getDate_written() {
        return date_written.get();
    }

    public void setDate_written(String date_written) {
        this.date_written.set(date_written);
    }
}
