package v4_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Main extends Application {

    private int row = 0;
    private int column = 0;

    @Override
    //versuch mit liste und visible
    public void start(Stage primaryStage) {
        // TO Do: making size to variable
        //      : add und remove in methode bringen

        GridPane root = new GridPane();
        GridPane mainInputPane = new GridPane();

        Button b1 = new Button("Add");
        Button b2 = new Button("Remove");
        //Pane element = new Pane();
        //Controller controller =
        new Controller();

        root.add(b1, 0, 0);
        root.add(b2, 1, 0);
        root.add(mainInputPane, 1,1);

        ArrayList<Pane> storeLines = new ArrayList<>();         //alle Panes werden hier gespeichert und es werden dann immer die aufgerufen die es benötigt.

        column = 0;
        row = 0;


        Scene scene = new Scene(root, 600  , 400);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT){
                if (column >= 0) column--;
                if (column < 0) {
                    row--;
                    column = 3;
                }

                System.out.println("Remove" + column + ", " + row);
                //storeLines.add(controller.createLine(100,true));
                storeLines.remove(storeLines.size()-1);
                drawPane(storeLines, mainInputPane);
            }

            if (event.getCode() == KeyCode.RIGHT){
                System.out.println("Addbefore" + column + ", " + row);

                if (column >= 4){
                    row++;
                    column = 0;

                }

                System.out.println("Add" + column + ", " + row);

                mainInputPane.addColumn(column);
                mainInputPane.addRow(row);

                //gibt den einzelnen zeilen eine fixe größe so das sie sich nicht verändern können
                RowConstraints rowWith = new RowConstraints(100);
                mainInputPane.getRowConstraints().add(rowWith);

                ColumnConstraints columnWith = new ColumnConstraints(100);
                mainInputPane.getColumnConstraints().add(columnWith);

                Takt takt;
                if (column == 0){
                    takt = new Takt(true);

                    //mainInputPane.add(controller.createLine(100,true), column,row);
                }else{
                    takt = new Takt(false);
                    //mainInputPane.add(controller.createLine(100,false), column,row);
                }
                storeLines.add(takt.getPane());
                drawPane(storeLines, mainInputPane);
                column++;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("v4_2");



        //mit lambda ausdruck damit man mehrere sachen durchführen kann
        b1.setOnAction(e ->{

            if (column%4 == 0){
                row++;
                column = 0;

            }

            System.out.println(column);
            System.out.println(row);
            System.out.println("Add");

            mainInputPane.addColumn(column);
            mainInputPane.addRow(row);

            //gibt den einzelnen zeilen eine fixe größe so das sie sich nicht verändern können
            RowConstraints rowWith = new RowConstraints(100);
            mainInputPane.getRowConstraints().add(rowWith);

            ColumnConstraints columnWith = new ColumnConstraints(100);
            mainInputPane.getColumnConstraints().add(columnWith);

            Takt takt;
            if (column == 0){
                takt = new Takt(true);

                //mainInputPane.add(controller.createLine(100,true), column,row);
            }else{

                takt = new Takt(false);
                //mainInputPane.add(controller.createLine(100,false), column,row);
            }
            storeLines.add(takt.getPane());
            drawPane(storeLines, mainInputPane);

            column++;

        });

        b2.setOnAction(e -> {
            if (column > 0) column--;
            if (column <= 0) {
                row--;
                column = 4;
            }

            System.out.println("Remove" + column + ", " + row);
            //storeLines.add(controller.createLine(100,true));
            storeLines.remove(storeLines.size()-1);
            drawPane(storeLines, mainInputPane);
            //mainInputPane.getChildren().remove(column, row);
        });
    }

    //panes in grid pane geben
    private void drawPane(ArrayList<Pane> arr, GridPane root) {
        int column = 0, row = 0;

        root.getChildren().clear();
        for(Pane line : arr){
            root.add(line, column, row);

            column++;
            if (column%4 == 0){
                row++;
                column = 0;

            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
