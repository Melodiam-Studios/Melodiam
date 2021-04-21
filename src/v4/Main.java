package v4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Main extends Application {

    private int row = 0;
    private int column = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("v2");

        Button b1 = new Button("Add");
        Button b2 = new Button("Remove");
        Pane element = new Pane();
        Controller controller = new Controller();
        Pane pane = controller.createLine(600,true);

        //element.setStyle("-fx-background-color: black");
        GridPane mainInputPane = new GridPane();
        mainInputPane.add(b1, 0, 0);
        mainInputPane.add(b2, 1, 0);

        column = 0;
        row = 1;

        primaryStage.setScene(new Scene(mainInputPane, 600, 400));
        primaryStage.show();


        //mit lambda ausdruck damit man meherer sachen durchführen kann
        b1.setOnAction(e ->{
            System.out.println(column);
            System.out.println(row);
            System.out.println("Add");

            mainInputPane.addColumn(column);
            mainInputPane.addRow(row);

            mainInputPane.add(controller.createLine(100, true), column,row);

            column++;
            if (column%4 == 0){
                row++;
                column = 0;
            }

        });

        b2.setOnAction(e -> {
            if (column > 0) column--;
            if (column <= 0) {
                row--;
                column = 4;
            }

            System.out.println("Remove" + column + ", " + row);
            mainInputPane.getChildren().remove(column, row);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
