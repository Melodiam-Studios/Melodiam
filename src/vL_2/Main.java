package vL_2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.plaf.ButtonUI;
import java.util.ArrayList;


public class Main extends Application {

    private int row = 0;
    private int column = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();*/
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));



        //final Controller controller = loader.getController();

        //anchorPane.setContent(mainInputPane);
        //anchorP.getChildren().add(mainInputPane);

        Scene scene = new Scene(root, 600  , 400);

        primaryStage.setTitle("Melodiam");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        //controller.addFile();


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
