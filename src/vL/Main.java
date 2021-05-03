package vL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("v5/sample.fxml")));
        primaryStage.setTitle("Melodiam");
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add(Objects.requireNonNull(getClass()
                .getResource("style.css")).toExternalForm());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
