package v6;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.Objects;


public class Main extends Application {


    private int row = 0;
    private int column = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        CreatePreOptionWindow cPreOptionWindow = new CreatePreOptionWindow(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
