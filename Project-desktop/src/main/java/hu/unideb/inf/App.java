package hu.unideb.inf;

import hu.unideb.dao.TAJDAOImplement;
import hu.unideb.dao.TajDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {


    private static Stage stage;

    @Override
    public void start(Stage stage) {

        App.stage = stage;
        App.loadFXML("/fxml/main_window.fxml");

        stage.show();
    }

    public static FXMLLoader loadFXML(String fxml){

        FXMLLoader Loader = new FXMLLoader(App.class.getResource(fxml));
        Scene scene = null;
        try {
           Parent root = Loader.load();
           scene = new Scene(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        return Loader;
    }


    public static void main(String[] args) {
        launch();
    }

}