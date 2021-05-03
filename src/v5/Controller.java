package v5;

import com.sun.javafx.geom.Point2D;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class Controller {

    GridPane mainInputPane;

    @FXML ImageView previewImage = new ImageView();
    int offset = -15;

    @FXML
    ScrollPane scrollP;

    @FXML
    AnchorPane anchorP;

    @FXML
    Button bAdd;

    private int row = 0;
    private int column = 0;
    private int lenghtPane = 275;
    private int hightPane = 200;

    private ArrayList<Pane> storeLines = new ArrayList<>();

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

    public void addPaneKeyboard(Scene scene, KeyEvent event){

        if (event.getCode() == KeyCode.LEFT){
            if (column >= 0) column--;
            if (column < 0) {
                row--;
                column = 3;
            }

            //storeLines.add(controller.createLine(100,true));
            storeLines.remove(storeLines.size()-1);
            drawPane(storeLines, mainInputPane);
        }

        if (event.getCode() == KeyCode.RIGHT){
            if (column >= 4){
                row++;
                column = 0;
            }

            mainInputPane.addColumn(column);
            mainInputPane.addRow(row);

            //gibt den einzelnen zeilen eine fixe größe so das sie sich nicht verändern können
            RowConstraints rowWith = new RowConstraints(100);
            mainInputPane.getRowConstraints().add(rowWith);

            ColumnConstraints columnWith = new ColumnConstraints(lenghtPane);
            mainInputPane.getColumnConstraints().add(columnWith);

            if (column == 0){
                Takt takt = new Takt(true);
                storeLines.add(takt.getPane());
                drawPane(storeLines, mainInputPane);
            }else{
                Takt takt = new Takt(false);
                storeLines.add(takt.getPane());
                drawPane(storeLines, mainInputPane);
            }
            column++;
        }
    }

    public void addRemPane(ActionEvent event){

        Button btn = (Button) event.getSource();
        String id = btn.getId();



        if (id.equals("bAdd")){
            if (column >= 4){
                row++;
                column = 0;

            }

            mainInputPane.addColumn(column);
            mainInputPane.addRow(row);

            RowConstraints rowWith = new RowConstraints(100);
            mainInputPane.getRowConstraints().add(rowWith);

            ColumnConstraints columnWith = new ColumnConstraints(lenghtPane);
            mainInputPane.getColumnConstraints().add(columnWith);

            if (column == 0){
                Takt takt = new Takt(false);
                storeLines.add(takt.getPane());
                drawPane(storeLines, mainInputPane);
            }else{
                Takt takt = new Takt(false);
                storeLines.add(takt.getPane());
                drawPane(storeLines, mainInputPane);
            }
            column++;
        }
        else if (id.equals("bRem")){
            if (column >= 0) column--;
            if (column < 0) {
                row--;
                column = 3;
            }
            storeLines.remove(storeLines.size()-1);
            drawPane(storeLines, mainInputPane);
        }
    }

    public void addFile(){
        mainInputPane = new GridPane();

        column = 0;
        row = 0;

        anchorP.getChildren().add(mainInputPane);
        //scrollP.setContent(mainInputPane);
    }
}
