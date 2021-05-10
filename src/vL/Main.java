package vL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Melodiam");
        JMetro jMetro=new JMetro(Style.LIGHT);
        Scene scene = new Scene(root);
        root.getStylesheets().add("vL/lightstyle.css");


        jMetro.setScene(scene);
        primaryStage.setScene(jMetro.getScene());
        //primaryStage.setScene(scene);

        primaryStage.setMaximized(true);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
