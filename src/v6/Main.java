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

        Parent root2 = FXMLLoader.load(getClass().getResource("preOptionWindowView.fxml"));
        Scene scene2 = new Scene(root2 , 660, 444);

        //root2.getStylesheets().add("v6/lightmode.css");
        Image image = new Image("/resources/melodiam_testicon.png");
        primaryStage.getIcons().add(image);

        primaryStage.setResizable(true);

        primaryStage.setScene(scene2);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
