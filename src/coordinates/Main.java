package coordinates;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {

    public Pane blabla(int line_length){
        Pane pane = new Pane();

        Line l1 = new Line();
        l1.setStartX(0);
        l1.setStartY(0);
        l1.setEndX(line_length);
        l1.setEndY(0);

        Line l2 = new Line();
        l2.setStartX(0);
        l2.setStartY(10);
        l2.setEndX(line_length);
        l2.setEndY(10);

        Line l3 = new Line();
        l3.setStartX(0);
        l3.setStartY(20);
        l3.setEndX(line_length);
        l3.setEndY(20);

        Line l4 = new Line();
        l4.setStartX(0);
        l4.setStartY(30);
        l4.setEndX(line_length);
        l4.setEndY(30);

        Line l5 = new Line();
        l5.setStartX(0);
        l5.setStartY(40);
        l5.setEndX(line_length);
        l5.setEndY(40);

        pane.getChildren().add(l1);
        pane.getChildren().add(l2);
        pane.getChildren().add(l3);
        pane.getChildren().add(l4);
        pane.getChildren().add(l5);


        return pane;

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        Pane root = blabla(600);
        primaryStage.setTitle("Melodiam");
        primaryStage.setScene(new Scene(root, 600, 200));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
