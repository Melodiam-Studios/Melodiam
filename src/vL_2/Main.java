package vL_2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import v4_2.Controller;

import java.util.ArrayList;


public class Main extends Application {

    @FXML
    ScrollPane scrollP;

    private int row = 0;
    private int column = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        GridPane mainInputPane = new GridPane();
        Controller controller = new Controller();

        scrollP.setContent(mainInputPane);

        ArrayList<Pane> storeLines = new ArrayList<>();         //alle Panes werden hier gespeichert und es werden dann immer die aufgerufen die es benötigt.

        column = 0;
        row = 0;


        Scene scene = new Scene(root, 600  , 400);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
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
                    System.out.println("Addbefor" + column + ", " + row);

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

                    if (column == 0){
                        storeLines.add(controller.createLine(100,true));
                        drawPane(storeLines, mainInputPane);

                        //mainInputPane.add(controller.createLine(100,true), column,row);
                    }else{

                        storeLines.add(controller.createLine(100,false));
                        drawPane(storeLines, mainInputPane);
                        //mainInputPane.add(controller.createLine(100,false), column,row);
                    }
                    column++;
                }
            }
        });


        primaryStage.setTitle("Melodiam");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();


    }

    private void drawPane(ArrayList<Pane> arr, GridPane root) {
        int column = 0, row = 0;

        root.getChildren().clear();
        for (Pane line : arr) {
            root.add(line, column, row);

            column++;
            if (column % 4 == 0) {
                row++;
                column = 0;

            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
