package artatawe;

import artatawe.classes.*;
import artatawe.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.ParseException;

/**
 * Main class
 *
 * @author Steve Kwok Chun Man
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load Login fxml
        Parent root = FXMLLoader.load(getClass().getResource("gui/login.fxml"));
        primaryStage.setTitle("Artatawe");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws ParseException {
        Browser.makeAll();
        launch(args);
    }
}
