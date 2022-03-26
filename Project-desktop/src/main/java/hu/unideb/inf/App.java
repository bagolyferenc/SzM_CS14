package hu.unideb.inf;

import hu.unideb.dao.TAJDAOImplement;
import hu.unideb.dao.TajDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        TajDAO dao = new TAJDAOImplement();
        dao.findAll().forEach(System.out::println);

        var scene = new Scene(new StackPane(new Label("teszt")), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}