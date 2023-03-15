package project.blog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.model.DataSource;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        Controller controller = fxmlLoader.getController();
        controller.loadArticles();

        stage.setTitle("Blog");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        DataSource.getInstance().open();
    }

    @Override
    public void stop() throws Exception {
        DataSource.getInstance().close();
    }

    public static void main(String[] args) {
        launch();
    }
}