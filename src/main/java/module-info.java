module project.blog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens project.blog to javafx.fxml;
    opens project.model to javafx.fxml;
    exports project.model;
    exports project.blog;
}