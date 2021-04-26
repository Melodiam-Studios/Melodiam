package v4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;

public class Main extends Application {

    private int row = 0;
    private int column = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("v2");

        Button b1 = new Button("Add");
        Button b2 = new Button("Remove");
        Pane element = new Pane();
        GridPane mainInputPane = new GridPane();
        mainInputPane.add(b1, 0, 0);
        mainInputPane.add(b2, 1, 0);
        column = 1;
        row = 1;

        primaryStage.setScene(new Scene(mainInputPane, 600, 400));
        Image image =  new Image("/resources/melodiam_testicon1.png");
        primaryStage.getIcons().add(image);
        //primaryStage.getIcons()
        primaryStage.show();


        //mit lambda ausdruck damit man meherer sachen durchführen kann
        b1.setOnAction(e ->{
            mainInputPane.add(element, column, row);

            column++;
            if (column%4 == 0){
                row++;
            }

            mainInputPane.addColumn(column);
            mainInputPane.addRow(row);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
